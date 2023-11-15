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
        MockFile fileComponent = new MockFile("file.txt");

        zipFile.addComponent(fileComponent);

        assertEquals(1, zipFile.getChildren().size());
        assertEquals(fileComponent, zipFile.getChildren().get(0));
    }

    @Test
    public void testDisplay() {
        ZipFile zipFile = new ZipFile("test.zip");
        MockFile fileComponent = new MockFile("file.txt");
        zipFile.addComponent(fileComponent);

        // Redirect standard out for testing
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        zipFile.display();

        assertEquals("Zip File: test.zip\nFile: file.txt\n", outputStream.toString());

        // Reset standard out
        System.setOut(System.out);
    }
}
