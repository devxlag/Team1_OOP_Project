package team1project;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;


public class PDFBasicPrinterTest {

    
     private PDFManager pdfManager;
    private PDFBasicPrinter pdfBasicPrinter;

    @Before
    public void setUp() {
        // Create an instance of PDFManager for testing
        pdfManager = new PDFManager();
        pdfBasicPrinter = new PDFBasicPrinter(pdfManager);
        pdfManager.createNewDocument();
    }

    @Test
    public void testUpdate() {
        // Create a submission with relevant data
        Submission submission = new Submission();
        submission.setStudentID("12345");
        submission.setStudentName("John Doe");
      

        // Call the update method
        boolean result = pdfBasicPrinter.update(submission);

        // Check if the PDFManager was called and if the result is as expected and if data in pdfFile matches test data
        assertTrue(result);
        assertNotNull(pdfManager.getCurrentPage());
        String generatedContent = pdfManager.getGeneratedContent();
        assertTrue(generatedContent.contains("Student ID: 12345"));
        assertTrue(generatedContent.contains("Student Name: John Doe"));
   
      

    }
}
