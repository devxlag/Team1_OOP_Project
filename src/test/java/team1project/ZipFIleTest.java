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
