package sms;

import com.Unit;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by root on 26/5/17.
 */
public class SMS {


    public final String USER_NAME = "harshalkshatriya@gmail.com";
    public final String API_HASH = "d3ed8dd231394419b5420bf8302148e88e6019238194018ba0c35b7bd0577eb1";
    public final String GROUP_ID = "740323";


    public void sendSMS() {

    }

    public String createBulkContacts(List<Unit> listOfUnits) {
        try {
            // Construct data
            String user = "username=" + USER_NAME;
            String hash = "&hash=" + API_HASH;

            String groupid = "&group_id=" + GROUP_ID;
//            int numberOfContacts = 3;
//            Person[] person = new Person[numberOfContacts];
            List<Person> personList = new ArrayList<>();
            Gson gson = new Gson();
            String finalData = "";

            for (int i = 0; i < listOfUnits.size(); i++) {
                Unit unit = listOfUnits.get(i);
                Person p = new Person();
                p.number = unit.getPhoneNumbers()[0];
                p.first_name = splitName(unit.getOwnerName())[0];
                p.last_name = splitName(unit.getOwnerName())[1];
                //p.custom1 = unit.getPhoneNumbers()[1];

                if(!p.number.isEmpty())
                    personList.add(p);

            }

            finalData = gson.toJson(personList);
            finalData = "&contacts=" + finalData;
            String data = user + hash + groupid + finalData;

            HttpURLConnection conn = (HttpURLConnection) new URL("http://api.textlocal.in/create_contacts_bulk/?").openConnection();
            conn.setDoOutput(true);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Length", Integer.toString(data.length()));
            conn.getOutputStream().write(data.getBytes("UTF-8"));
            final BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            final StringBuffer stringBuffer = new StringBuffer();
            String line;
            while ((line = rd.readLine()) != null) {
                stringBuffer.append(line);
            }
            rd.close();

            return stringBuffer.toString();
        } catch (Exception e) {
            System.out.println("Error SMS " + e);
            return "Error " + e;
        }

    }

    private String[] splitName(String ownerName) {
        String[] name = ownerName.split("\\s+");
        String[] finalName = new String[2];

        if(name.length == 2) {
            finalName[0] = name[0];
            finalName[1] = name[1];
        }
        else if(name.length > 2) {
            finalName[0] = name[0];
            finalName[1] = name[2];
        }
        return finalName;
    }


    class Person {
        public String number;
        public String first_name;
        public String last_name;
        public String custom1;
        public String custom2;
        public String custom3;
    }
}
