package team1project;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.zip.*;
public class ZipSubmissionProcessor implements EvaluatorObserver {

    
    private ZipFile compositeTreeRoot; // Root of the composite tree

    public ZipSubmissionProcessor() {}

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
        
        try (FileInputStream fis = new FileInputStream(submissionFile)) {
            processZipStream(fis);

            if(compositeTreeRoot != null)
                return true;
        } catch (IOException e) {          
            e.printStackTrace();
        }
        return false;
    }

    private void processZipStream(InputStream inputStream) throws IOException {
        ZipInputStream zipInputStream = new ZipInputStream(inputStream);
        ZipEntry entry;
        while ((entry = zipInputStream.getNextEntry()) != null) {
            if (!entry.isDirectory() && entry.getName().endsWith(".zip")) {
                // Extract and process each student submission zip file
                System.out.println("Processing student submission: " + entry.getName());
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
                JavaFile javaFile = processJavaFile(zipInputStream, entry);
                studentZipFile.addComponent(javaFile);
            }
            zipInputStream.closeEntry();
        }
        return studentZipFile;
    }

    private JavaFile processJavaFile(InputStream inputStream, ZipEntry entry) throws IOException {

    
      //System.out.println("Processing Java file: " + entry.getName());     

        JavaFile javaFile = new JavaFile(entry.getName());

      // Read entry into byte array
      try (ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
        byte[] buffer = new byte[1024];
        byte[] groupID = "package team1project; \n".getBytes();
        
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