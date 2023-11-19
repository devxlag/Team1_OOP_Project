package team1project;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.Rule;
import org.junit.jupiter.api.Test;
import org.junit.rules.TemporaryFolder;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * The ZipSubmissionProcessorTest class contains unit tests for the ZipSubmissionProcessor class.
 * Other methods are tested indirectly.
 */
public class ZipSubmissionProcessorTest {

    @Rule
    public TemporaryFolder tempFolder = new TemporaryFolder();

    /**
     * Tests the update method of the ZipSubmissionProcessor class.
     * Verifies that the submission is processed successfully, and the composite tree root is set.
     */
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
            if (evaluator.getCompositeTreeRoot() instanceof ZipFile) {
                ZipFile compositeTreeRoot = (ZipFile) evaluator.getCompositeTreeRoot();
                assertEquals(2, compositeTreeRoot.getChildren().size());
            }
        } finally {
            // Cleanup: Delete the temporary directory
            deleteDirectory(tempDir);
        }
    }

    /**
     * Creates a nested zip file with a Java file inside.
     *
     * @param parentDir    The parent directory for the nested zip file.
     * @param zipFileName  The name of the nested zip file.
     * @param javaFileName The name of the Java file inside the nested zip file.
     * @return The created nested zip file.
     * @throws IOException If an I/O error occurs.
     */
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

    /**
     * Creates a main zip file containing the provided nested zip files.
     *
     * @param parentDir       The parent directory for the main zip file.
     * @param nestedZipFiles  The nested zip files to include in the main zip file.
     * @return The created main zip file.
     * @throws IOException If an I/O error occurs.
     */
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

    /**
     * Recursively deletes a directory and its contents.
     *
     * @param directory The directory to delete.
     */
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
