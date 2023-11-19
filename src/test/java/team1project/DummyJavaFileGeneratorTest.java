package team1project;

import static org.junit.Assert.assertTrue;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * The DummyJavaFileGeneratorTest class contains unit tests for the DummyJavaFileGenerator class.
 */
public class DummyJavaFileGeneratorTest {

    @Rule
    public TemporaryFolder tempFolder = new TemporaryFolder();

    /**
     * Tests the generateFiles method of DummyJavaFileGenerator by creating a temporary input file,
     * setting up an output directory, calling the method, and verifying the generated file.
     *
     * @throws IOException If an I/O error occurs during the test.
     */
    @Test
    public void testGenerateFiles() throws IOException {
        // Create a temporary input file with sample class information
        File inputFile = tempFolder.newFile("input.txt");
        FileWriter inputWriter = new FileWriter(inputFile);
        inputWriter.write("Class: MyClass\n");
        inputWriter.write("public class MyClass {\n");
        inputWriter.write("    // Class content\n");
        inputWriter.write("}\n");
        inputWriter.write("End of Class\n");
        inputWriter.close();

        // Set up the output directory
        File outputDir = tempFolder.newFolder("output");

        // Instantiate the DummyJavaFileGenerator
        DummyJavaFileGenerator fileGenerator = new DummyJavaFileGenerator();

        // Call the generateFiles method
        fileGenerator.generateFiles(inputFile.getAbsolutePath(), outputDir.getAbsolutePath());

        // Verify that the output file was generated
        File generatedFile = new File(outputDir, "MyClass.java");
        assertTrue(generatedFile.exists());

        // Verify the content of the generated file
        BufferedReader reader = new BufferedReader(new FileReader(generatedFile));
        StringBuilder fileContent = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            fileContent.append(line).append("\n");
        }
        reader.close();

        String expectedContent = "public class MyClass {\n    // Class content\n}\n";
        assertTrue(fileContent.toString().contains(expectedContent));
    }
}
