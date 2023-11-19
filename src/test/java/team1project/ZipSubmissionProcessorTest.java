package team1project;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.Rule;
import org.junit.jupiter.api.Test;
import org.junit.rules.TemporaryFolder;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

public class ZipSubmissionProcessorTest {

    @Rule
    public TemporaryFolder tempFolder = new TemporaryFolder();

     @Test
    void testUpdate() throws IOException {
        // Create a temporary directory for testing
        File tempDir = Files.createTempDirectory("tempTestDir").toFile();

        try {
            // Create a temporary zip file with nested zip files containing Java files
            File nestedZipFile1 = createNestedZipFile(tempDir, "NestedZip1.zip", "File1.java");
            File nestedZipFile2 = createNestedZipFile(tempDir, "NestedZip2.zip", "File2.java");

            File mainZipFile = createMainZipFile(tempDir, nestedZipFile1, nestedZipFile2);

            // Create a mock required files list
            ArrayList<String> requiredFiles = new ArrayList<>();
            requiredFiles.add("File1.java");
            requiredFiles.add("File2.java");

            // Create a ZipSubmissionProcessor
            ZipSubmissionProcessor processor = new ZipSubmissionProcessor(requiredFiles);

            // Create a mock evaluator for testing
            Evaluator evaluator = new Evaluator();
            evaluator.setZipFile(mainZipFile);

            // Call the update method
            boolean result = processor.update(evaluator);

            // Verify that the submission is processed successfully
            assertTrue(result);

            // Verify that the composite tree root is set in the evaluator
            assertNotNull(evaluator.getCompositeTreeRoot());
            if(evaluator.getCompositeTreeRoot() instanceof ZipFile){
                ZipFile compositeTreeRoot = (ZipFile) evaluator.getCompositeTreeRoot();
                assertEquals(2, compositeTreeRoot.getChildren().size());
            }
            // Verify that the nested zip files are added as components
            

        } finally {
            // Cleanup: Delete the temporary directory
            deleteDirectory(tempDir);
        }
    }


    private File createNestedZipFile(File parentDir, String zipFileName, String javaFileName) throws IOException {
        File nestedZipFile = new File(parentDir, zipFileName);

        try (ZipOutputStream zipOutputStream = new ZipOutputStream(Files.newOutputStream(nestedZipFile.toPath()))) {
            ZipEntry javaFileEntry = new ZipEntry(javaFileName);
            zipOutputStream.putNextEntry(javaFileEntry);
            zipOutputStream.write("Test Java Code".getBytes());
            zipOutputStream.closeEntry();
        }

        return nestedZipFile;
    }

    private File createMainZipFile(File parentDir, File... nestedZipFiles) throws IOException {
        File mainZipFile = new File(parentDir, "MainZipFile.zip");

        try (ZipOutputStream zipOutputStream = new ZipOutputStream(Files.newOutputStream(mainZipFile.toPath()))) {
            for (File nestedZipFile : nestedZipFiles) {
                ZipEntry nestedZipEntry = new ZipEntry(nestedZipFile.getName());
                zipOutputStream.putNextEntry(nestedZipEntry);
                Files.copy(nestedZipFile.toPath(), zipOutputStream);
                zipOutputStream.closeEntry();
            }
        }

        return mainZipFile;
    }

    private void deleteDirectory(File directory) {
        if (directory.exists()) {
            File[] files = directory.listFiles();
            if (files != null) {
                for (File file : files) {
                    if (file.isDirectory()) {
                        deleteDirectory(file);
                    } else {
                        file.delete();
                    }
                }
            }
            directory.delete();
        }
    }
}

