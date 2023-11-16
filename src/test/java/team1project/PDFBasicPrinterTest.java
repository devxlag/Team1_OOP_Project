package team1project;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;


public class PDFBasicPrinterTest {

    
     private PDFPrinter pdfPrinter;  

    @Before
    public void setUp() {
        // Create an instance of PDFManager for testing
        
        
        pdfPrinter = new PDFPrinter();
        pdfPrinter.createNewDocument();
    }

    @Test
    public void testUpdate() {
        // Create a submission with relevant data
        Submission submission = new Submission();
        submission.setStudentID("12345");
        submission.setStudentName("John Doe");
      
        pdfPrinter.createNewPage();
        // Add content to the page
        String[] basicContent = {"Student Name: " + submission.getStudentName(), "Student ID: " + submission.getStudentID()};
        pdfPrinter.addBasicContent(basicContent);
        // Close the content stream
        pdfPrinter.closeContentStream();

        // Check if the PDFManager was called and if the result is as expected and if data in pdfFile matches test data
        assertNotNull(pdfPrinter.getCurrentPage());
        String generatedContent = pdfPrinter.getGeneratedContent();
        assertTrue(generatedContent.contains("Student ID: 12345"));
        assertTrue(generatedContent.contains("Student Name: John Doe"));
   
    }
}
