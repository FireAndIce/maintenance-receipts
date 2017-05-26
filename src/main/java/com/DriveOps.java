package com;

import com.google.api.services.drive.Drive;
import com.google.api.services.drive.model.File;
import com.google.api.services.drive.model.FileList;

import java.io.*;

/**
 * Created by root on 11/5/17.
 */
public class DriveOps {
    public static String EXPORT_DESTINATION_FOLDER = "/home/hkshatriya/Documents/Ganga Jamna/drive/";
    String fileId;

    void searchFile(Drive service) {
        try {
            Drive.Files.List request = service.files().list().setQ("copyTitle");

            FileList files = request.execute();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    String copyFile(Drive service, String originFileId, String copyTitle) {
        Drive.Files.Copy request = null;
        File copiedFile = new File();
        copiedFile.setName(copyTitle);

        try {
            request = service.files().copy(WriteSheet.spreadsheetId, copiedFile)
            .setFields("id");
            copiedFile = request.execute();

        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(copiedFile.getId());
        return copiedFile.getId();
    }

    void moveFile(Drive service, String fileId, String folderId) {


        // Retrieve the existing parents to remove
        try {
            File file = service.files().get(fileId)
                    .setFields("parents")
                    .execute();


            StringBuilder previousParents = new StringBuilder();
            for(String parent: file.getParents()) {
                previousParents.append(parent);
                previousParents.append(',');
            }

            // Move the file to the new folder
            file = service.files().update(fileId, null)
                    .setAddParents(folderId)
                    .setRemoveParents(previousParents.toString())
                    .setFields("id, parents")
                    .execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void exportFileToPDF(Drive service, String fileId, String fileName) {
        // List all the files in folder.
        Drive.Files.Export export;
        OutputStream outputStream = null;
        try {
            outputStream = new FileOutputStream(EXPORT_DESTINATION_FOLDER + fileName);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        try {
            service.files().export(fileId, "application/pdf")
                    .executeMediaAndDownloadTo(outputStream);


        } catch (IOException e) {
            e.printStackTrace();
        }


        // Export all the files to PDF.


    }
}

