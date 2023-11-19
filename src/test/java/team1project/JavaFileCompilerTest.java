package team1project;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import org.junit.Test;
import org.junit.Rule;
import org.junit.rules.TemporaryFolder;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/**
 * The JavaFileCompilerTest class contains unit tests for the JavaFileCompiler class.
 */
public class JavaFileCompilerTest {

    @Rule
    public TemporaryFolder tempFolder = new TemporaryFolder();

    /**
     * Tests the compileJavaFiles method of JavaFileCompiler by setting up temporary directories,
     * creating temporary Java files, calling the method, and verifying the compilation result.
     *
     * @throws Exception If an exception occurs during the test.
     */
    @Test
    public void testCompileJavaFiles() throws Exception {
        // Set up temporary output directory
        File outputDir = tempFolder.newFolder("target", "classes", "team1project");

        // Set up temporary main directory
        File mainDirectory = tempFolder.newFolder("src", "main", "java", "team1project");

        // Create temporary Java files with sample class information
        List<String> fileContents = Arrays.asList(
                "Flight.java",
                "public class Flight { /* class content */ }",
                "Passenger.java",
                "public class Passenger { /* class content */ }",
                "LuggageManifest.java",
                "public class LuggageManifest { /* class content */ }",
                "LuggageSlip.java",
                "public class LuggageSlip { /* class content */ }",
                "FlightTest.java",
                "public class FlightTest { /* class content */ }",
                "PassengerTest.java",
                "public class PassengerTest { /* class content */ }",
                "LuggageManifestTest.java",
                "public class LuggageManifestTest { /* class content */ }",
                "LuggageSlipTest.java",
                "public class LuggageSlipTest { /* class content */ }",
                "LuggageManagementSystem.java",
                "public class LuggageManagementSystem { /* class content */ }",
                "LuggageManagementSystemTest.java",
                "public class LuggageManagementSystemTest { /* class content */ }"
        );

        createTempFiles(mainDirectory, fileContents);

        // Instantiate the JavaFileCompiler
        JavaFileCompiler fileCompiler = new JavaFileCompiler();

        // Call the compileJavaFiles method
        try {
            boolean compilationResult = fileCompiler.compileJavaFiles(mainDirectory.getAbsolutePath(),outputDir.getAbsolutePath(),Arrays.asList(
                    "/Flight.java",
                    "/Passenger.java",
                    "/LuggageManifest.java",
                    "/LuggageSlip.java",
                    "/FlightTest.java",
                    "/PassengerTest.java",
                    "/LuggageManifestTest.java",
                    "/LuggageSlipTest.java",
                    "/LuggageManagementSystem.java",
                    "/LuggageManagementSystemTest.java"
            ));

            // Verify that compilation was successful
            assertTrue(compilationResult);

            // Verify that the compiled class files exist in the output directory
            assertTrue(new File(outputDir, "Flight.class").exists());
            assertTrue(new File(outputDir, "Passenger.class").exists());
            assertTrue(new File(outputDir, "LuggageManifest.class").exists());
            assertTrue(new File(outputDir, "LuggageSlip.class").exists());
            assertTrue(new File(outputDir, "FlightTest.class").exists());
            assertTrue(new File(outputDir, "PassengerTest.class").exists());
            assertTrue(new File(outputDir, "LuggageManifestTest.class").exists());
            assertTrue(new File(outputDir, "LuggageSlipTest.class").exists());
            assertTrue(new File(outputDir, "LuggageManagementSystem.class").exists());
            assertTrue(new File(outputDir, "LuggageManagementSystemTest.class").exists());
        } catch (Exception e) {
            e.printStackTrace();
            // Fail the test if an exception occurs during compilation
            assertFalse("Compilation threw an exception", true);
        }
    }
    /**
     * Tests the compileJavaFiles method of JavaFileCompiler with an intentionally invalid Java file
     * to trigger compilation failure. Verifies that the compilation fails and the invalid class file is not generated.
     *
     * @throws Exception If an exception occurs during the test.
     */
    @Test
    public void testCompileJavaFilesFailure() throws Exception {
        // Set up temporary output directory
        File outputDir = tempFolder.newFolder("target", "classes", "team1project");

        // Set up temporary main directory
        File mainDirectory = tempFolder.newFolder("src", "main", "java");

        // Create a temporary invalid Java file to trigger compilation failure
        File invalidFile = new File(mainDirectory, "InvalidFile.java");
        // Write invalid Java code to the file
        // This code intentionally contains an error
        // The compilation should fail due to this error
        
        writeToFile(invalidFile, "public class InvalidFile { invalid code }");

        // Instantiate the JavaFileCompiler
        JavaFileCompiler fileCompiler = new JavaFileCompiler();

        // Call the compileJavaFiles method
        try {
            boolean compilationResult = fileCompiler.compileJavaFiles(mainDirectory.getAbsolutePath(),outputDir.getAbsolutePath(), Arrays.asList(
                    "team1project/InvalidFile.java"
            ));

            // Verify that compilation failed
            assertFalse("Compilation should fail, but it succeeded", compilationResult);

            // Verify that the compiled class files do not exist in the output directory
            assertFalse(new File(outputDir, "InvalidFile.class").exists());
        } catch (Exception e) {
            e.printStackTrace();
            // Fail the test if an exception occurs during compilation
            assertFalse("Compilation threw an exception", true);
        }
    }

   /**
     * Writes the given content to the specified file.
     *
     * @param file    The file to write to.
     * @param content The content to write to the file.
     * @throws IOException If an I/O error occurs while writing to the file.
     */
    private void writeToFile(File file, String content) throws IOException {
        FileWriter writer = new FileWriter(file);
        writer.write(content);
        writer.close();
    }

    /**
     * Creates temporary files in the specified directory with the given contents.
     *
     * @param directory    The directory to create temporary files in.
     * @param fileContents The list of file names and their contents alternately.
     * @throws IOException If an I/O error occurs while creating temporary files.
     */
    private void createTempFiles(File directory, List<String> fileContents) throws IOException {
        for (int i = 0; i < fileContents.size(); i += 2) {
            String fileName = fileContents.get(i);
            String content = fileContents.get(i + 1);
            File file = new File(directory, fileName);
            writeToFile(file, content);
        }
    }
}
