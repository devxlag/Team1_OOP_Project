package team1project;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Observer;
import java.util.zip.*;
// class ZipSubmissionProcessor implements SubmissionProcessor{

//   private Observer observer;
//   private Submission submission;


//   public ZipSubmissionProcessor(Submission submission) {
//     this.submission = submission;
//   }

//   @Override
//   public void registerObserver(Observer o) {
//     this.observer = o;
//   }

//   @Override
//   public void removeObserver(Observer o) {
//     this.observer = null;
//   }

//   @Override
//   public void notifyObservers() {
//     observer.update(submission);
//   }

  
//   public void processSubmission(File submissionFile) {
//     try (FileInputStream fis = new FileInputStream(submissionFile)) {
//       processZipStream(fis); 
//     } catch (IOException e) {
//       e.printStackTrace();
//     }
//   }

//   private void processZipStream(InputStream inputStream) throws IOException {
//     ZipInputStream zipInputStream = new ZipInputStream(inputStream);
//     ZipEntry entry;
//     while ((entry = zipInputStream.getNextEntry()) != null) {
//       if (!entry.isDirectory() && entry.getName().endsWith(".zip")) {
//         // Extract and process each student submission zip file
//         System.out.println("Processing student submission: " + entry.getName());
//         extractAndProcessJavaFiles(zipInputStream);
//       }
//       zipInputStream.closeEntry();
//     }
//     zipInputStream.close();
//   }

 

//   private void extractAndProcessJavaFiles(InputStream inputStream) throws IOException {
//     ZipInputStream zipInputStream = new ZipInputStream(inputStream); 
//     ZipEntry entry;
//     while ((entry = zipInputStream.getNextEntry()) != null) {
//       if (!entry.isDirectory() && entry.getName().endsWith(".java")) {
//          processJavaFile(zipInputStream, entry.getName());
//           writeJavaFile(zipInputStream, entry, "src/test/java/team1project");
       
//       }
//       zipInputStream.closeEntry(); 
//     }
    
//   }

    // private void writeJavaFile(InputStream inputStream, ZipEntry entry, String tmpDir) throws IOException {

    //   // Create temp file for this Java file
    //   File tempFile = new File(tmpDir, entry.getName());
    
    //   // Write java file contents to temp file
    //   try (BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(tempFile))) {
    //     byte[] buffer = new byte[1024];
    //     byte[] buffer2 = new byte[1024];
    //     buffer2 = "package team1project;\n".getBytes();
    //     int len;
    //     bos.write(buffer2, 0, buffer2.length);
    //     while((len = inputStream.read(buffer)) > 0) {
    //       bos.write(buffer, 0, len);
    //     }
    //   } 
    
    // }




//  private void processJavaFile(InputStream inputStream, String fileName) {
//         // Add your Java file processing logic here
//         System.out.println("Processing Java file: " + fileName);

//     }
// }



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



public class ZipSubmissionProcessor implements SubmissionProcessor {

    private SubmissionProcessorObserver observer;
    private ZipFile compositeTreeRoot; // Root of the composite tree

    public ZipSubmissionProcessor() {
        this.compositeTreeRoot = new ZipFile("Composite Root");
    }

    @Override
    public void registerObserver(SubmissionProcessorObserver o) {
        this.observer = o;
    }

    @Override
    public void removeObserver(SubmissionProcessorObserver o) {
        this.observer = null;
    }

    @Override
    public void notifyObservers() {
        observer.update(compositeTreeRoot);
    }

    public void processSubmission(File submissionFile) {
        try (FileInputStream fis = new FileInputStream(submissionFile)) {
            processZipStream(fis);
            // Display the composite tree
            compositeTreeRoot.display();
            notifyObservers();
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
                //System.out.println("Processing student submission: " + entry.getName());
                ZipFile studentZipFile = extractJavaFiles(zipInputStream, entry.getName());
                compositeTreeRoot.addComponent(studentZipFile);
            }
            zipInputStream.closeEntry();
        }
        zipInputStream.close();
    }

    private ZipFile extractJavaFiles(InputStream inputStream, String filename) throws IOException {
        ZipInputStream zipInputStream = new ZipInputStream(inputStream);
        ZipEntry entry;
        ZipFile studentZipFile = new ZipFile(filename);
        while ((entry = zipInputStream.getNextEntry()) != null) {
            if (!entry.isDirectory() && entry.getName().endsWith(".java")) {
                System.out.println("Processing Java file: " + entry.getName());
                JavaFile javaFile = processJavaFile(zipInputStream, entry);
                studentZipFile.addComponent(javaFile);
            }
            zipInputStream.closeEntry();
        }
        return studentZipFile;
    }

    private JavaFile processJavaFile(InputStream inputStream, ZipEntry entry) throws IOException {

       // Process the Java file if needed
      System.out.println("Processing Java file: " + entry.getName());
      // System.out.println("Contents:\n" + javaFile.getContents());

        JavaFile javaFile = new JavaFile(entry.getName());

      // Read entry into byte array
      try (ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
        byte[] buffer = new byte[1024];
        byte[] groupID = "package team1project;\n".getBytes();
        
        baos.write(groupID, 0, groupID.length);
        int length;
        while ((length = inputStream.read(buffer)) != -1) {
          baos.write(buffer, 0, length);
        }
        byte[] fileBytes = baos.toByteArray();

        // Decode bytes to String and save in JavaFile
        String fileContents = new String(fileBytes, StandardCharsets.UTF_8); 
        javaFile.appendContents(fileContents);
      }

      return javaFile;
  }
}