package team1project;
import java.io.*;
import java.lang.reflect.Array;
import java.nio.charset.StandardCharsets;
import java.util.zip.*;
import org.apache.commons.io.IOUtils;
import org.apache.commons.io.FilenameUtils;
import java.util.ArrayList;
public class ZipSubmissionProcessor implements EvaluatorObserver {

    
    private ZipFile compositeTreeRoot; // Root of the composite tree
    private ArrayList<String> requiredFiles; // List of Java files in the submission

    public ZipSubmissionProcessor(ArrayList<String> requiredFiles) {

        this.requiredFiles = requiredFiles;
    }

    public boolean update(Evaluator evaluator) {
        boolean processed = false;
        if(evaluator instanceof Evaluator){
            evaluator = (Evaluator) evaluator;

            if(processSubmission(evaluator.getZipFile())){
                evaluator.setCompositeTreeRoot(compositeTreeRoot);
                processed = true;
            }                
            else{
                System.out.println("ZipSubmissionProcessor: " + evaluator.getZipFile().getName() + " has not been processed.");
            
            }
        }

        if(processed)
            return true;
        return false;
    }

    public boolean processSubmission(File submissionFile) {
        compositeTreeRoot = new ZipFile(submissionFile.getName());
        String outputFolder = submissionFile.getParent();
        try (FileInputStream fis = new FileInputStream(submissionFile)) {
            processZipStream(fis, outputFolder);

            if(compositeTreeRoot != null)
                return true;
        } catch (IOException e) {          
            e.printStackTrace();
        }
        return false;
    }

    private void processZipStream(InputStream inputStream, String outputFolder) throws IOException {
        ZipInputStream zipInputStream = new ZipInputStream(inputStream);
        ZipEntry entry;
        while ((entry = zipInputStream.getNextEntry()) != null) {
            if (!entry.isDirectory() && entry.getName().endsWith(".zip")) {
                // Extract and process each student submission zip file
                System.out.println("Processing student submission: " + entry.getName());
                String innerZipFileName = entry.getName(); 
                String submissionName = FilenameUtils.getBaseName( innerZipFileName);
                String submissionFolder = outputFolder + File.separator + submissionName;
                System.out.println("Submission Name: " + submissionName);
                System.out.println("Submission Folder: " + submissionFolder);

                    if(!new File(submissionFolder).exists()){
                        new File(submissionFolder).mkdirs();
                      }


                ZipFile studentZipFile = extractJavaFiles(zipInputStream,  innerZipFileName, submissionFolder);               
                compositeTreeRoot.addComponent(studentZipFile);
            }
            zipInputStream.closeEntry();
        }
        zipInputStream.close();
    }

    private ZipFile extractJavaFiles(InputStream inputStream, String filename, String submissionFolder) throws IOException {
        ZipInputStream zipInputStream = new ZipInputStream(inputStream);
        ZipEntry entry;
        ZipFile studentZipFile = new ZipFile(filename);
        studentZipFile.setPath(submissionFolder); // Set the path of the zip file
        while ((entry = zipInputStream.getNextEntry()) != null) {
            if (!entry.isDirectory() && entry.getName().endsWith(".java") && requiredFiles.contains(entry.getName())) {               
                JavaFile javaFile = processJavaFile(zipInputStream, entry.getName(), submissionFolder);
                studentZipFile.getSubmittedFileNames().add(entry.getName());
                studentZipFile.addComponent(javaFile);
            }
            zipInputStream.closeEntry();
        }
        return studentZipFile;
    }

    private JavaFile processJavaFile(InputStream inputStream, String javaFilename, String submissionFolder) throws IOException {

    
      //System.out.println("Processing Java file: " + entry.getName());     

    //     JavaFile javaFile = new JavaFile(javaFilename);

    //   // Read entry into byte array
    //   try (ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
    //     byte[] buffer = new byte[1024];
    //     byte[] groupID = "package team1project; \n".getBytes();
        
    //     baos.write(groupID, 0, groupID.length);
    //     int length;
    //     while ((length = inputStream.read(buffer)) != -1) {
    //       baos.write(buffer, 0, length);
    //     }
    //     byte[] fileBytes = baos.toByteArray();

    //     // Decode bytes to String and save in JavaFile
    //     String fileContents = new String(fileBytes, StandardCharsets.UTF_8); 
    //     javaFile.appendContents(fileContents);
    //   }InputStream entryInputStream = inputStream;
        JavaFile javaFile = new JavaFile(javaFilename);
        // Copy input stream to buffer
        ByteArrayOutputStream baos= new ByteArrayOutputStream();
        byte[] groupID = "package team1project;\n".getBytes();
        baos.write(groupID, 0, groupID.length);

        IOUtils.copy(inputStream,baos);
        //    System.out.println("Buffer: " + buffer.toString());
        // Write buffer to file in submission folder
        File newFile = new File(submissionFolder, javaFilename);
        FileOutputStream outputStream = new FileOutputStream(newFile);
        baos.writeTo(outputStream);
        outputStream.close();

        byte[] fileBytes = baos.toByteArray();

        // Decode bytes to String and save in JavaFile
        String fileContents = new String(fileBytes, StandardCharsets.UTF_8); 
        javaFile.appendContents(fileContents);        
       // javaFile.display();

      return javaFile;
  }
}