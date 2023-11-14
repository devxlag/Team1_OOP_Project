package team1project;

import java.io.File;

// SubmissionProcessorClient
public class SubmissionProcessorClient {
    public static void main(String[] args) {
        // Replace with the path to the zip file containing student submissions
        String zipFilePath ="C:\\Users\\Devon Murray\\OneDrive - The University of the West Indies, St. Augustine\\UWI Courses\\Year 3 Semester 1\\COMP 3607\\Assignments\\Project\\submissions.zip";

        //SubmissionProcessorFactory processorFactory = new SubmissionProcessorFactory();

        try {
            File zipFile = new File(zipFilePath);

            if (!zipFile.exists()) {
                System.err.println("The zip file does not exist.");
                return;
            }
            
            
            SubmissionProcessor processor = new ZipSubmissionProcessor();
            processor.registerObserver(new Evaluator());
                        
            processor.processSubmission(zipFile);
           
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
