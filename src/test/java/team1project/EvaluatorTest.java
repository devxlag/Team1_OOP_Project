package team1project;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class EvaluatorTest {

    private Evaluator evaluator;
    private ZipFile mockCompositeTreeRoot;

    @Before
    public void setUp() {
        evaluator = new Evaluator();
        mockCompositeTreeRoot = createMockCompositeTree();
    }

    @Test
    public void testEvaluate() throws IOException {
        // Set up a mock composite tree with a ZipFile object containing JavaFile objects
        evaluator.setCompositeTreeRoot(mockCompositeTreeRoot);

        // Mock the cleanTargetDirectory method to avoid actual file deletion
        Evaluator spyEvaluator = spy(evaluator);
        //doNothing().when(spyEvaluator).cleanTargetDirectory(anyString());

        // Call the evaluate method
        boolean result = spyEvaluator.evaluate();

        // Verify that the cleanTargetDirectory method is called once
       // verify(spyEvaluator, times(1)).cleanTargetDirectory(anyString());

        // Verify the result
        assertTrue(result);
    }

    private ZipFile createMockCompositeTree() {
        // Create a mock ZipFile
        ZipFile mockZipFile = mock(ZipFile.class);
        when(mockZipFile.getPath()).thenReturn("mock/path");
        when(mockZipFile.getFileName()).thenReturn("mock_file.zip");

        // Create mock JavaFile objects
        JavaFile mockJavaFile1 = mock(JavaFile.class);
        when(mockJavaFile1.getFileName()).thenReturn("Flight.java");
        when(mockJavaFile1.getContents()).thenReturn("mock Java file contents 1");

        JavaFile mockJavaFile2 = mock(JavaFile.class);
        when(mockJavaFile2.getFileName()).thenReturn("Passenger.java");
        when(mockJavaFile2.getContents()).thenReturn("mock Java file contents 2");

        // Set up the mock composite tree
        List<AbstractFile> children = Arrays.asList(mockJavaFile1, mockJavaFile2);
        when(mockZipFile.getChildren()).thenReturn(children);

        return mockZipFile;
    }
}


// package team1project;

// import org.junit.jupiter.api.Test;
// import static org.junit.jupiter.api.Assertions.*;

// import java.io.File;
// import java.io.FileOutputStream;
// import java.io.FileWriter;
// import java.io.IOException;
// import java.nio.file.Files;
// import java.util.zip.ZipOutputStream;
// import java.util.List;
// import java.util.zip.ZipEntry;
// import java.util.zip.ZipInputStream;
// import java.util.zip.ZipOutputStream;

// public class EvaluatorTest {

//     @Test
//     void testProcessSubmissionFile() throws IOException {
//         // Create a temporary directory for testing
//         File tempDir = createTempDirectory();

//         try {
//             // Create a temporary zip file for testing
//             File zipFile = createTempZipFile(tempDir, "TestSubmission.zip");

//             // Create an Evaluator instance
//             Evaluator evaluator = new Evaluator();
//             evaluator.registerObservers();

//             // Call the processSubmissionFile method
//             evaluator.processSubmissionFile(zipFile);

//             // Verify that the zipFile attribute is set
//             assertEquals(zipFile, evaluator.getZipFile());

//             // Verify that the submission attribute is created
//             assertNotNull(evaluator.getSubmission());
//             assertEquals(zipFile.getAbsolutePath(), evaluator.getSubmission().getSubmissionPath());
//             assertNotNull(evaluator.getSubmission().getStudentID());

//             // Verify that the evaluate method is called
//             assertTrue(evaluator.evaluate());

//         } finally {
//             // Cleanup: Delete the temporary directory
//             deleteDirectory(tempDir);
//         }
//     }

//     @Test
//     void testTraverseFiles() throws IOException {
//         // Create a temporary directory for testing
//         File tempDir = createTempDirectory();

//         try {
//             // Create a temporary zip file with nested zip files containing Java files
//             File nestedZipFile1 = createNestedZipFile(tempDir, "NestedZip1.zip", "File1.java");
//             File nestedZipFile2 = createNestedZipFile(tempDir, "NestedZip2.zip", "File2.java");

//             File mainZipFile = createMainZipFile(tempDir, nestedZipFile1, nestedZipFile2);

//             // Create an Evaluator instance
//             Evaluator evaluator = new Evaluator();
//             evaluator.registerObservers();

//             // Set the compositeTreeRoot
//             evaluator.setCompositeTreeRoot(new ZipFile(mainZipFile.getName()));

//             ZipFile compositeTreeRoot = (ZipFile) evaluator.getCompositeTreeRoot();
//             // Call the traverseFiles method
//             assertTrue(evaluator.traverseFiles(compositeTreeRoot.getChildren(), tempDir));

//         } finally {
//             // Cleanup: Delete the temporary directory
//             deleteDirectory(tempDir);
//         }
//     }

//     // @Test
//     // void testCleanTargetDirectory() {
//     //     // Create a temporary directory for testing
//     //     File tempDir = createTempDirectory();

//     //     try {
//     //         // Create some dummy .java files in the target directory
//     //         createDummyFiles(tempDir, "Flight.java", "Passenger.java", "LuggageManifest.java");

//     //         // Create an Evaluator instance
//     //         Evaluator evaluator = new Evaluator();

//     //         // Call the cleanTargetDirectory method
//     //         evaluator.cleanTargetDirectory(tempDir.getAbsolutePath());

//     //         // Verify that the dummy files are deleted
//     //         assertFalse(new File(tempDir, "Flight.java").exists());
//     //         assertFalse(new File(tempDir, "Passenger.java").exists());
//     //         assertFalse(new File(tempDir, "LuggageManifest.java").exists());

//     //     } finally {
//     //         // Cleanup: Delete the temporary directory
//     //         deleteDirectory(tempDir);
//     //     }
//     // }

//     @Test
//     void testRunTests() {
//         // Create an Evaluator instance
//         Evaluator evaluator = new Evaluator();

//         // Set the compositeTreeRoot
//         evaluator.setCompositeTreeRoot(new ZipFile("TestZipFile.zip"));

//         // Set the zipFile attribute
//         evaluator.setZipFile(new File("TestZipFile.zip"));

//         // Mock the required files
//         List<String> requiredFiles = evaluator.getFilesToCompile();

//         // Mock the JavaFileCompiler
//         JavaFileCompiler mockCompiler = new JavaFileCompiler() {
//             @Override
//             public boolean compileJavaFiles(String mainDirectory, String outputDirectory, List<String> filesToCompile) {
//                 // Mock the compilation process
//                 return true;
//             }
//         };

//         // Set the mock compiler
//         evaluator.setJavaFileCompiler(mockCompiler);

//         // Mock the TestRunner
//         TestRunner mockTestRunner = new TestRunner() {
//             @Override
//             public boolean runTests(Submission submission) {
//                 // Mock the test running process
//                 return true;
//             }
//         };

//         // Set the mock TestRunner
//         evaluator.setTestRunner(mockTestRunner);

//         // Call the runTests method
//         evaluator.runTests();

//         // Verify that the required methods are called
//         assertTrue(mockCompiler.isCompileJavaFilesCalled());
//         assertTrue(mockTestRunner.isTestRunnerCalled());
//     }

//     @Test
//     void testWriteJavaFile() throws IOException {
//         // Create a temporary directory for testing
//         File tempDir = createTempDirectory();

//         try {
//             // Create a JavaFile for testing
//             JavaFile javaFile = new JavaFile("TestFile.java");
//             javaFile.appendContents("Test Java Code");

//             // Create an Evaluator instance
//             Evaluator evaluator = new Evaluator();

//             // Call the writeJavaFile method
//             assertTrue(evaluator.writeJavaFile(javaFile, tempDir));

//             // Verify that the JavaFile is written to the temporary directory
//             File writtenFile = new File(tempDir, "TestFile.java");
//             assertTrue(writtenFile.exists());

//         } finally {
//             // Cleanup: Delete the temporary directory
//             deleteDirectory(tempDir);
//         }
//     }

//     private File createTempDirectory() {
//         try {
//             return File.createTempFile("tempDir", Long.toString(System.nanoTime()));
//         } catch (IOException e) {
//             throw new RuntimeException("Failed to create temp directory", e);
//         }
//     }

//     private void deleteDirectory(File directory) {
//         if (directory != null && directory.exists()) {
//             File[] files = directory.listFiles();
//             if (files != null) {
//                 for (File file : files) {
//                     if (file.isDirectory()) {
//                         deleteDirectory(file);
//                     } else {
//                         file.delete();
//                     }
//                 }
//             }
//             directory.delete();
//         }
//     }

//     private File createTempZipFile(File tempDir, String zipFileName, String... javaFiles) throws IOException {
//         File zipFile = new File(tempDir, zipFileName);
//         try (ZipOutputStream zipOutputStream = new ZipOutputStream(new FileOutputStream(zipFile))) {
//             for (String javaFile : javaFiles) {
//                 zipOutputStream.putNextEntry(new ZipEntry(javaFile));
//                 zipOutputStream.write("Test Java Code".getBytes());
//                 zipOutputStream.closeEntry();
//             }
//         }
//         return zipFile;
//     }

//     private File createMainZipFile(File tempDir, File... nestedZipFiles) throws IOException {
//         File mainZipFile = new File(tempDir, "MainZipFile.zip");
//         try (ZipOutputStream zipOutputStream = new ZipOutputStream(new FileOutputStream(mainZipFile))) {
//             for (File nestedZipFile : nestedZipFiles) {
//                 zipOutputStream.putNextEntry(new ZipEntry(nestedZipFile.getName()));
//                 Files.copy(nestedZipFile.toPath(), zipOutputStream);
//                 zipOutputStream.closeEntry();
//             }
//         }
//         return mainZipFile;
//     }

//     private void createDummyFiles(File directory, String... fileNames) throws IOException {
//         for (String fileName : fileNames) {
//             File dummyFile = new File(directory, fileName);
//             try (FileWriter writer = new FileWriter(dummyFile)) {
//                 writer.write("Dummy Java Code");
//             }
//         }
//     }

//     private File createNestedZipFile(File parentDir, String zipFileName, String javaFileName) throws IOException {
//         File nestedZipFile = new File(parentDir, zipFileName);

//         try (ZipOutputStream zipOutputStream = new ZipOutputStream(Files.newOutputStream(nestedZipFile.toPath()))) {
//             ZipEntry javaFileEntry = new ZipEntry(javaFileName);
//             zipOutputStream.putNextEntry(javaFileEntry);
//             zipOutputStream.write("Test Java Code".getBytes());
//             zipOutputStream.closeEntry();
//         }

//         return nestedZipFile;
//     }
// }

