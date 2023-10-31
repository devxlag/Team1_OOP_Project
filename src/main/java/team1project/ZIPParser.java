package team1project;
import java.io.*;
import java.util.zip.*;

class ZIPParser {
    public static void main(String[] args) {
        // Replace "C:\\Your\\Zip\\File.zip" with the path to your zip file
        String zipFilePath = "C:\\Users\\Devon Murray\\OneDrive - The University of the West Indies, St. Augustine\\UWI Courses\\Year 3 Semester 1\\COMP 3607\\Tutorial or Labs\\Lab5\\app.zip";
        
        int fileCount = 0;
        int folderCount = 0;

        try {
            FileInputStream fis = new FileInputStream(zipFilePath);
            ZipInputStream zipInputStream = new ZipInputStream(fis);

            ZipEntry entry;
            while ((entry = zipInputStream.getNextEntry()) != null) {
                if (entry.isDirectory()) {
			System.out.println("Directory: " + entry.getName());
                    folderCount++;
                } else {
			System.out.println("File: " + entry.getName());
                    fileCount++;
                }
                zipInputStream.closeEntry();
            }

            zipInputStream.close();
            fis.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Number of files in the zip: " + fileCount);
        System.out.println("Number of folders in the zip: " + folderCount);
    }
}
