package team1project;
import java.io.*;
import java.util.zip.*;
import java.nio.file.*;
import java.util.*;
import java.util.regex.*;
// import org.junit.runner.JUnitCore;
// import org.junit.runner.Result;
// import org.junit.runner.notification.Failure;
class ZipSubmissionProcessor implements SubmissionProcessor {

  @Override
  public void processSubmission(File submissionFile) {
    try (FileInputStream fis = new FileInputStream(submissionFile)) {
      processZipStream(fis); 
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  private void processZipStream(InputStream inputStream) throws IOException {
    ZipInputStream zipInputStream = new ZipInputStream(inputStream);
    ZipEntry entry;
    while ((entry = zipInputStream.getNextEntry()) != null) {
      if (!entry.isDirectory() && entry.getName().endsWith(".zip")) {
        // Extract and process each student submission zip file
        System.out.println("Processing student submission: " + entry.getName());
        extractAndProcessJavaFiles(zipInputStream);
      }
      zipInputStream.closeEntry();
    }
    zipInputStream.close();
  }

  private void extractAndProcessJavaFiles(InputStream inputStream) throws IOException {
    ZipInputStream zipInputStream = new ZipInputStream(inputStream); 
    ZipEntry entry;
    while ((entry = zipInputStream.getNextEntry()) != null) {
      if (!entry.isDirectory() && entry.getName().endsWith(".java")) {
         processJavaFile(zipInputStream, entry.getName());
          extractJavaFile(zipInputStream, entry, "src/main/java/team1project");
       
      }
      zipInputStream.closeEntry(); 
    }
    
  }

    private void extractJavaFile(InputStream inputStream, ZipEntry entry, String tmpDir) throws IOException {

      // Create temp file for this Java file
      File tempFile = new File(tmpDir, entry.getName());
    
      // Write java file contents to temp file
      try (BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(tempFile))) {
        byte[] buffer = new byte[1024];
        int len;
        while((len = inputStream.read(buffer)) > 0) {
          bos.write(buffer, 0, len);
        }
      } 
    
    }

    // private void runTests() {
    //     System.out.println("Running tests...");

    //     // Replace "FlightTest" with the actual name of your test class
    //     Result result = JUnitCore.runClasses(FlightTest.class);

    //     if (result.wasSuccessful()) {
    //         System.out.println("All tests passed!");
    //     } else {
    //         System.out.println("Tests failed:");

    //         for (Failure failure : result.getFailures()) {
    //             System.out.println(failure.toString());
    //         }
    //     }
    // }


 private void processJavaFile(InputStream inputStream, String fileName) {
        // Add your Java file processing logic here
        System.out.println("Processing Java file: " + fileName);

        // // Extracted Java file path
        // String extractedFilePath = "src/main/java/team1project/test/" + fileName;

        // // Compile the extracted Java file
        // boolean compilationSuccess = JavaCompilerHelper.compileFiles(extractedFilePath);

        // if (compilationSuccess) {
        //     // If compilation is successful, run the tests
        //     runTests();

        //     // Delete the extracted Java file and the compiled class file
        //     JavaCompilerHelper.deleteFiles(extractedFilePath, "target/classes/team1project/test/" + fileName.replace(".java", ".class"));
        // } else {
        //     System.out.println("Compilation failed for: " + fileName);
        // }
    }
    
  // Java file processing method


// private static void processJavaFile(InputStream inputStream, String fileName) throws IOException {
//         try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
//             String line;
//             System.out.println("Processing Java file: " + fileName + " from submission: ");
//             while ((line = reader.readLine()) != null) {
//                 // Process the contents of the Java file here
//                 // You can add your custom processing logic
//                 System.out.println(line);
//             }
            
//         } catch (IOException e) {
//             e.printStackTrace();
//         }
//     }
}



// class ZIPParser {
//     public static void main(String[] args) {
//         // Replace "C:\\Your\\Zip\\File.zip" with the path to your zip file
//         String zipFilePath = "C:\\Users\\Devon Murray\\OneDrive - The University of the West Indies, St. Augustine\\UWI Courses\\Year 3 Semester 1\\COMP 3607\\Tutorial or Labs\\Lab5\\app.zip";
        
//         int fileCount = 0;
//         int folderCount = 0;

//         try {
//             FileInputStream fis = new FileInputStream(zipFilePath);
//             ZipInputStream zipInputStream = new ZipInputStream(fis);

//             ZipEntry entry;
//             while ((entry = zipInputStream.getNextEntry()) != null) {
//                 if (entry.isDirectory()) {
// 			System.out.println("Directory: " + entry.getName());
//                     folderCount++;
//                 } else {
// 			System.out.println("File: " + entry.getName());
//                     fileCount++;
//                 }
//                 zipInputStream.closeEntry();
//             }

//             zipInputStream.close();
//             fis.close();
//         } catch (IOException e) {
//             e.printStackTrace();
//         }

//         System.out.println("Number of files in the zip: " + fileCount);
//         System.out.println("Number of folders in the zip: " + folderCount);
//     }
// }
