package team1project;

import java.io.*;
import java.nio.file.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class SubmissionProcessor {

    public static void main(String[] args) {
        // Specify the path to the zipped file containing student submissions
        String zipFilePath = "C:\\\\Users\\\\Devon Murray\\\\OneDrive - The University of the West Indies, St. Augustine\\\\UWI Courses\\\\Year 3 Semester 1\\\\COMP 3607\\\\Assignments\\\\Project\\\\zipfiles\\\\one.zip";

        try {
            // Open the zip file
            FileInputStream fis = new FileInputStream(zipFilePath);
            ZipInputStream zis = new ZipInputStream(new BufferedInputStream(fis));

            // Process each submission
            ZipEntry submissionEntry;
            while ((submissionEntry = zis.getNextEntry()) != null) {
                String submissionName = submissionEntry.getName();
                System.out.println("Processing Submission: " + submissionName);

                // Create a directory to extract the submission files
                Path submissionDirectory = Paths.get("output", submissionName);
                Files.createDirectories(submissionDirectory);

                // Process each file in the submission
                ZipEntry fileEntry;
                boolean submissionEmpty = true; // Flag to track empty submissions
                while ((fileEntry = zis.getNextEntry()) != null) {
                    String fileName = fileEntry.getName();
                    System.out.println("Processing File: " + fileName);

                    // Create the path for the extracted file
                    Path filePath = submissionDirectory.resolve(fileName);

                    // Write the content to the extracted file
                    Files.createDirectories(filePath.getParent());
                    Files.copy(zis, filePath, StandardCopyOption.REPLACE_EXISTING);

                    // Set the submissionEmpty flag to false since there is at least one file
                    submissionEmpty = false;

                    // Close the entry
                    zis.closeEntry();
                }

                // Handle empty submissions
                if (submissionEmpty) {
                    System.out.println("Empty Submission: " + submissionName);
                }

                // Close the submission entry
                zis.closeEntry();
            }

            // Close the zip file
            zis.close();

        } catch (FileNotFoundException e) {
            System.err.println("Error: The specified zip file was not found.");
            e.printStackTrace();
        } catch (IOException e) {
            System.err.println("Error while processing submissions.");
            e.printStackTrace();
        }
    }
}

