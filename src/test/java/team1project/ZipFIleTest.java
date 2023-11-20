package team1project;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import org.junit.Test;

/**
 * Unit tests for the {@link ZipFile} class.
 * 
 */
public class ZipFIleTest {
    /**
     * Tests the creation of a ZipFile object.
     */
    @Test
    public void testZipFileCreation() {
        ZipFile zipFile = new ZipFile("test.zip");
        assertNotNull(zipFile);
        assertEquals("test.zip", zipFile.getFileName());
        assertNotNull(zipFile.getChildren());
    }
   /**
     * Tests the addComponent method of the ZipFile class.
     */
    @Test
    public void testAddComponent() {
        ZipFile zipFile = new ZipFile("test.zip");
        JavaFile fileComponent = new JavaFile("file.txt");

        zipFile.addComponent(fileComponent);

        assertEquals(1, zipFile.getChildren().size());
        assertEquals(fileComponent, zipFile.getChildren().get(0));
    }

    /**
     * Tests the getFileName method of the ZipFile class.
     */
    @Test
    public void testGetFileName() {
        ZipFile zipFile = new ZipFile("test.zip");
        assertEquals("test.zip", zipFile.getFileName());
    }

    /**
     * Tests the getChildren method of the ZipFile class.
     */
    @Test
    public void testGetChildren() {
        ZipFile zipFile = new ZipFile("test.zip");
        assertEquals(zipFile.getChildren().size(), 0); 
    }

    /**
     * Tests the setPath method of the ZipFile class.
     */
    @Test
    public void testSetPath() {
        ZipFile zipFile = new ZipFile("test.zip");
        zipFile.setPath("testPath");
        assertEquals("testPath", zipFile.getPath());
    }

    /**
     * Tests the getSubmittedFileNames method of the ZipFile class.
     */
    @Test
    public void testGetSubmittedFileNames() {
        ZipFile zipFile = new ZipFile("test.zip");
        assertEquals(zipFile.getSubmittedFileNames().size(), 0); 
    }

}
