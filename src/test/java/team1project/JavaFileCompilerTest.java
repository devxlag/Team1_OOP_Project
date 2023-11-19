
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

public class JavaFileCompilerTest {

    @Rule
    public TemporaryFolder tempFolder = new TemporaryFolder();

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
        // Modify the content as needed to create a valid test case
        // For example: writeToFile(invalidFile, "public class InvalidFile { invalid code }");
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

    private void writeToFile(File file, String content) throws IOException {
        FileWriter writer = new FileWriter(file);
        writer.write(content);
        writer.close();
    }

    private void createTempFiles(File directory, List<String> fileContents) throws IOException {
        for (int i = 0; i < fileContents.size(); i += 2) {
            String fileName = fileContents.get(i);
            String content = fileContents.get(i + 1);
            File file = new File(directory, fileName);
            writeToFile(file, content);
        }
    }
}
