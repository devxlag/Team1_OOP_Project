package team1project;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import java.io.File;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;



/**
 * Unit tests for the {@link PDFPrinter} class.
 */

public class PDFPrinterTest {
    private PDFPrinter pdfPrinter;

    @Rule
    public TemporaryFolder tempFolder = new TemporaryFolder();

    @Before
    public void setUp() 
    {
        pdfPrinter = new PDFPrinter();
    }

  /**
     * Tests the {@link PDFPrinter#createNewDocument()} method.
     */
    @Test
    public void testCreateNewDocument() 
    {
        pdfPrinter.createNewDocument();
        assertNull("Document is not initially empty", pdfPrinter.getCurrentPage());
    }

    
    /**
     * Tests the {@link PDFPrinter#createNewPage()} method.
     */

    @Test
    public void testCreateNewPage()
     {
        pdfPrinter.createNewDocument();
        pdfPrinter.createNewPage();
        assertNotNull(pdfPrinter.getCurrentPage());
    }

    /**
     * Tests the {@link PDFPrinter#addBasicContent(String[])} method.
     */
    @Test
    public void testAddBasicContent() 
    {
        pdfPrinter.createNewDocument();
        pdfPrinter.createNewPage();
        String[] lines = { "Line 1", "Line 2", "Line 3" };
        pdfPrinter.addBasicContent(lines);
        
        assertNotNull(pdfPrinter.getCurrentContentStream());
        pdfPrinter.closeContentStream();
        String generatedContent = pdfPrinter.getGeneratedContent();
        assertTrue("Generated content should contain 'Line 1'", generatedContent.contains("Line 1"));
        assertTrue("Generated content should contain 'Line 2'", generatedContent.contains("Line 2"));
        assertTrue("Generated content should contain 'Line 3'", generatedContent.contains("Line 3"));
    }


      /**
     * Tests the {@link PDFPrinter#addContentToStream(String)} method.
     */
    @Test
    public void testAddContentToStream() 
    {
        pdfPrinter.createNewDocument();
        pdfPrinter.createNewPage();
        String additionalContent = "Additional Content\nLine 1\nLine 2";
        pdfPrinter.addContentToStream(additionalContent);
        
        assertNotNull(pdfPrinter.getCurrentContentStream());
        pdfPrinter.closeContentStream();
        
        String generatedContent = pdfPrinter.getGeneratedContent();
        assertTrue("Generated content should contain 'Line 1'", generatedContent.contains("Line 1"));
        assertTrue("Generated content should contain 'Line 2'", generatedContent.contains("Line 2"));

    }

    /**
     * Tests the {@link PDFPrinter#saveAndClose(String)} method.
     */
    @Test
    public void testSaveAndClose() throws Exception{

        File outputDir = tempFolder.newFolder("temp", "dir", "team1project");

        pdfPrinter.createNewDocument();
        pdfPrinter.createNewPage();
         pdfPrinter.addBasicContent(new String[]{"Test Content"});
        pdfPrinter.closeContentStream();         
         assertDoesNotThrow(() -> pdfPrinter.saveAndClose(outputDir.getAbsolutePath()));
    
    
        assertTrue(java.nio.file.Paths.get(outputDir.getAbsolutePath()).toFile().exists());
      
    }
    
     /**
     * Tests the {@link PDFPrinter#getGeneratedContent()} method.
     */
    
    @Test
    public void testGetGeneratedContent() 
    {
        pdfPrinter.createNewDocument();
        pdfPrinter.createNewPage();
        pdfPrinter.closeContentStream();
        assertNotNull(pdfPrinter.getGeneratedContent());
    }
}
