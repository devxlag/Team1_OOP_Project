package team1project;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

// SubmissionProcessorClient
public class SubmissionProcessorClient {
    public static void main(String[] args) throws Exception{
        // Replace with the path to the zip file containing student submissions
        String zipFilePath ="C:\\Users\\Devon Murray\\OneDrive - The University of the West Indies, St. Augustine\\UWI Courses\\Year 3 Semester 1\\COMP 3607\\Assignments\\Project\\zipfiles\\zipfiles.zip";
        
        try {
            File zipFile = new File(zipFilePath);

            if (!zipFile.exists()) {
                System.err.println("The zip file does not exist.");
                return;
            }
            
            Evaluator evaluator = new Evaluator();
            evaluator.registerObservers();

            evaluator.processSubmissionFile(zipFile);
              
            
           
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
