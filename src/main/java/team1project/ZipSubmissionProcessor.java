package team1project;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.zip.*;
import org.apache.commons.io.IOUtils;
import org.apache.commons.io.FilenameUtils;
import java.util.ArrayList;

/**
 * The ZipSubmissionProcessor class processes zip submissions, extracts relevant Java files,
 * and creates a composite tree structure to represent the submission hierarchy.
 */
public class ZipSubmissionProcessor implements EvaluatorObserver {

    private ZipFile compositeTreeRoot; // Root of the composite tree
    private ArrayList<String> requiredFiles; // List of Java files in the submission

    /**
     * Constructs a ZipSubmissionProcessor with the specified list of required files.
     *
     * @param requiredFiles The list of required Java files.
     */
    public ZipSubmissionProcessor(ArrayList<String> requiredFiles) {
        this.requiredFiles = requiredFiles;
    }

    /**
     * Gets the root of the composite tree.
     *
     * @return The root of the composite tree.
     */
    public ZipFile getCompositeTreeRoot() {
        return compositeTreeRoot;
    }

    /**
     * Sets the root of the composite tree.
     *
     * @param compositeTreeRoot The root of the composite tree to set.
     */
    public void setCompositeTreeRoot(ZipFile compositeTreeRoot) {
        this.compositeTreeRoot = compositeTreeRoot;
    }

    /**
     * Updates the ZipSubmissionProcessor with the provided evaluator.
     *
     * @param evaluator The evaluator to update with.
     * @return True if the update is successful, false otherwise.
     */
    public boolean update(Evaluator evaluator) {
        boolean processed = false;
        if (evaluator instanceof Evaluator) {
            evaluator = (Evaluator) evaluator;

            if (processSubmission(evaluator.getZipFile())) {
                evaluator.setCompositeTreeRoot(compositeTreeRoot);
                processed = true;
            } else {
                System.out.println("ZipSubmissionProcessor: " + evaluator.getZipFile().getName() + " has not been processed.");
            }
        }

        return processed;
    }

    /**
     * Processes the submission by extracting Java files and creating a composite tree.
     *
     * @param submissionFile The submission file to process.
     * @return True if the submission is processed successfully, false otherwise.
     */
    public boolean processSubmission(File submissionFile) {
        compositeTreeRoot = new ZipFile(submissionFile.getName());
        String outputFolder = submissionFile.getParent();
        try (FileInputStream fis = new FileInputStream(submissionFile)) {
            processZipStream(fis, outputFolder);

            return compositeTreeRoot != null;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Processes the zip stream by extracting inner zip files and appends return ZipFile to the composite tree.
     *
     * @param inputStream  The input stream of the zip file.
     * @param outputFolder The output folder to store extracted files.
     * @throws IOException If an I/O error occurs.
     */
    public void processZipStream(InputStream inputStream, String outputFolder) throws IOException {
        ZipInputStream zipInputStream = new ZipInputStream(inputStream);
        ZipEntry entry;
        while ((entry = zipInputStream.getNextEntry()) != null) {
            if (!entry.isDirectory() && entry.getName().endsWith(".zip")) {
                // Extract and process each student submission zip file
                System.out.println("Processing student submission: " + entry.getName());
                String innerZipFileName = entry.getName();
                String submissionName = FilenameUtils.getBaseName(innerZipFileName);
                String submissionFolder = outputFolder + File.separator + submissionName;
                System.out.println("Submission Name: " + submissionName);
                System.out.println("Submission Folder: " + submissionFolder);

                if (!new File(submissionFolder).exists()) {
                    new File(submissionFolder).mkdirs();
                }

                ZipFile studentZipFile = extractJavaFiles(zipInputStream, innerZipFileName, submissionFolder);
                compositeTreeRoot.addComponent(studentZipFile);
            }
            zipInputStream.closeEntry();
        }
        zipInputStream.close();
    }

    /**
     * Extracts Java files from the inner zip file and creates a ZipFile representing the student's submission.
     *
     * @param inputStream      The input stream of the inner zip file.
     * @param filename         The name of the inner zip file.
     * @param submissionFolder The folder to store extracted files.
     * @return The ZipFile representing the student's submission.
     * @throws IOException If an I/O error occurs.
     */
    public ZipFile extractJavaFiles(InputStream inputStream, String filename, String submissionFolder) throws IOException {
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

    /**
     * Processes a Java file by copying its contents to a new file and creating a JavaFile object.
     *
     * @param inputStream      The input stream of the Java file.
     * @param javaFilename     The name of the Java file.
     * @param submissionFolder The folder to store the Java file.
     * @return The JavaFile object representing the processed Java file.
     * @throws IOException If an I/O error occurs.
     */
    public JavaFile processJavaFile(InputStream inputStream, String javaFilename, String submissionFolder) throws IOException {
        JavaFile javaFile = new JavaFile(javaFilename);

        // Copy input stream to buffer
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        byte[] groupID = "package team1project;\n".getBytes();
        baos.write(groupID, 0, groupID.length);

        IOUtils.copy(inputStream, baos);

        // Write buffer to file in submission folder
        File newFile = new File(submissionFolder, javaFilename);
        FileOutputStream outputStream = new FileOutputStream(newFile);
        baos.writeTo(outputStream);
        outputStream.close();

        byte[] fileBytes = baos.toByteArray();

        // Decode bytes to String and save in JavaFile
        String fileContents = new String(fileBytes, StandardCharsets.UTF_8);
        javaFile.appendContents(fileContents);

        return javaFile;
    }
}
