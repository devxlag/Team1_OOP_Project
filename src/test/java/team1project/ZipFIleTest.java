package team1project;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.Test;

public class ZipFIleTest {

    @Test
    public void testZipFileCreation() {
        ZipFile zipFile = new ZipFile("test.zip");
        assertNotNull(zipFile);
        assertEquals("test.zip", zipFile.getFileName());
        assertNotNull(zipFile.getChildren());
    }

    @Test
    public void testAddComponent() {
        ZipFile zipFile = new ZipFile("test.zip");
        JavaFile fileComponent = new JavaFile("file.txt");

        zipFile.addComponent(fileComponent);

        assertEquals(1, zipFile.getChildren().size());
        assertEquals(fileComponent, zipFile.getChildren().get(0));
    }

    // @Test
    // public void testDisplay() throws Exception{
    //     ZipFile zipFile = new ZipFile("test.zip");
    //     JavaFile fileComponent = new JavaFile("file.txt");
    //     zipFile.addComponent(fileComponent);

    //     // Redirect standard out for testing
    //     ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    //     System.setOut(new PrintStream(outputStream));

    //     zipFile.display();

    //     assertEquals("Zip File: test.zip\nJava File: file.txt\nContents:".trim(), outputStream.toString().);

    //     // Reset standard out
    //     System.setOut(System.out);
    // }


//     @Test
//     public void testDisplay() {
//         ZipFile zipFile = new ZipFile("test.zip");
        
//         JavaFile javaFile = new JavaFile("SampleFile.java");
//         javaFile.appendContents("public class SampleFile {\n    // File contents\n}");
//         zipFile.addComponent(javaFile);
//         // Redirect the standard output to capture it for testing
//         ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
//         System.setOut(new PrintStream(outputStream));

//         // Call the display method
//         zipFile.display();

//         // Reset the standard output
//         System.setOut(System.out);

//         // Verify the printed output
//         String expectedOutput = "Zip File: test.zip\nJava File: SampleFile.java\nContents:\npublic class SampleFile {\n    // File contents\n}";
//         assertEquals(expectedOutput, outputStream.toString().trim());
//     }


    @Test
    public void testGetFileName() {
        ZipFile zipFile = new ZipFile("test.zip");
        assertEquals("test.zip", zipFile.getFileName());
    }


    @Test
    public void testGetChildren() {
        ZipFile zipFile = new ZipFile("test.zip");
        assertEquals(zipFile.getChildren().size(), 0); 
    }

    @Test
    public void testSetPath() {
        ZipFile zipFile = new ZipFile("test.zip");
        zipFile.setPath("testPath");
        assertEquals("testPath", zipFile.getPath());
    }

    @Test
    public void testGetSubbmittedFileNames() {
        ZipFile zipFile = new ZipFile("test.zip");
        assertEquals(zipFile.getSubmittedFileNames().size(), 0); 
    }

}
