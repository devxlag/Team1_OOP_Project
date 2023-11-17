package team1project;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

import java.io.File;

// SubmissionProcessorClient
// public class SubmissionProcessorClient {
//     public static void main(String[] args) throws Exception{
//         // Replace with the path to the zip file containing student submissions
        // String zipFilePath ="C:\\Users\\Devon Murray\\OneDrive - The University of the West Indies, St. Augustine\\UWI Courses\\Year 3 Semester 1\\COMP 3607\\Assignments\\Project\\zipfiles\\badgood.zip";
        
//         try {
//             File zipFile = new File(zipFilePath);

//             if (!zipFile.exists()) {
//                 System.err.println("The zip file does not exist.");
//                 return;
//             }
            
//             Evaluator evaluator = new Evaluator();
//             evaluator.registerObservers();

//             evaluator.processSubmissionFile(zipFile);
              
            
           
//         } catch (Exception e) {
//             e.printStackTrace();
//         }
//     }
// }


import javax.swing.*;
import java.io.File;

public class SubmissionProcessorClient {

  public static void main(String[] args) {
    
    // Create file chooser
    JFileChooser fileChooser = new JFileChooser();
    fileChooser.setFileFilter(new FileNameExtensionFilter("ZIP files", "zip"));
    // Show open file dialog
    int result = fileChooser.showOpenDialog(null);

    if (result == JFileChooser.APPROVE_OPTION) {
       File zipFile = fileChooser.getSelectedFile();
       
       if(!zipFile.exists()) {
         JOptionPane.showMessageDialog(null, "Error: File not found");
         return;
       }

        System.out.println("Selected file: " + zipFile.getAbsolutePath());


       // Process zip file submissions
       processSubmissions(zipFile);

    }

  }

  private static void processSubmissions(File zipFile) {
    // Code to process zip submissions 

    Evaluator evaluator = new Evaluator();
    evaluator.registerObservers();

    try {
        evaluator.processSubmissionFile(zipFile);
    } catch (IOException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
    }
  }

}


