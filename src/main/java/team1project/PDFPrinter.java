package team1project;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.text.PDFTextStripper;

import java.io.IOException;
import java.io.StringWriter;

/**
 * Utility class for creating and managing PDF documents.
 * Provides methods to create, modify, and extract content from PDFs.
 */


public class PDFPrinter {
    private PDDocument document;
    private PDPage currentPage;
    private PDPageContentStream currentContentStream;
    private static int filenum = 1;

 
      /**
     * Creates a new PDF document.
     */

    public void createNewDocument() {
        document = new PDDocument();
    }
    
     /**
     * Creates a new page within the PDF document.
     */

    public void createNewPage() {
        currentPage = new PDPage();
        document.addPage(currentPage);

        try {
            currentContentStream = new PDPageContentStream(document, currentPage, PDPageContentStream.AppendMode.APPEND, true);
        } catch (IOException e) {
            e.printStackTrace();
         
        }
    }

     /**
     * Gets the current page of the PDF document.
     * @return The current page.
     */

    public PDPage getCurrentPage()
    {
        return currentPage;
    }


     /**
     * Gets the current content stream of the PDF document.
     * @return The current content stream.
     */
    public PDPageContentStream getCurrentContentStream()
    {
        return currentContentStream;
    }

     /**
     * Adds basic content to the PDF document.
     * @param lines The lines of content to add.
     */
    public void addBasicContent(String[] lines) {
        try {
            float margin = 50;
            float yPosition = currentPage.getMediaBox().getHeight() - margin;

            currentContentStream.beginText();
            currentContentStream.setFont(PDType1Font.COURIER_BOLD, 5);
             currentContentStream.newLineAtOffset(margin, yPosition);
            float lineHeight = 12;

            for (String line : lines) {

                currentContentStream.newLineAtOffset(0, -lineHeight);
                currentContentStream.showText(line);
                
            }

            currentContentStream.endText();
        } catch (IOException e) {
            e.printStackTrace();
            
        }
    }

    /**
     * Adds additional content to the PDF document.
     * @param additionalContent The additional content to add.
     */

    public void addContentToStream(String additionalContent) {
        try {
            float margin = 50;
            float yPosition = currentPage.getMediaBox().getHeight() - margin;

            currentContentStream.beginText();
            currentContentStream.setFont(PDType1Font.COURIER_BOLD, 5); 
            currentContentStream.newLineAtOffset(margin, yPosition);
                    

            String[] lines = additionalContent.split("\n");
            for (String line : lines) {
                currentContentStream.showText(line);
                currentContentStream.newLineAtOffset(0, -12); // Adjust the offset based on font size
            }
            //currentContentStream.showText(additionalContent);
            currentContentStream.endText();
            //currentContentStream.close();
        } catch (IOException e) {
            e.printStackTrace();
            
        }
    }

     /**
     * Closes the current content stream of the PDF document.
     */
    public void closeContentStream() {
        try {
            if (currentContentStream != null) {
                currentContentStream.close();
                currentContentStream = null; // Set it to null after closing
            }
        } catch (IOException e) {
            e.printStackTrace();
           
        }
    }
    

     /**
     * Saves and closes the PDF document.
     * @param savePath The path where the PDF document should be saved.
     */
    
    public void saveAndClose(String savePath) {
        try {
            
            // document.save("C:\\Users\\Devon Murray\\Desktop\\submission_results\\" + filename);
            document.save(savePath);
        } catch (IOException e) {
            e.printStackTrace();
           
        } finally {
            closeDocument();
        }
    }

    /**
     * Closes the PDF document.
     * Called internally when saving and closing.
     */
    private void closeDocument() {
        if (document != null) {
            try {
                document.close();
            } catch (IOException e) {
                e.printStackTrace();
               
            }
        }
    }
   
     /**
     * Gets the generated content of the PDF document.
     * @return The generated content.
     * @throws IllegalStateException If the PDF document is not created.
     */

    public String getGeneratedContent() 
    {
        // Check if the document is null
        if (document == null) 
        {
            throw new IllegalStateException("PDF document is not created.");
        }

        try {
            
            PDFTextStripper pdfTextStripper = new PDFTextStripper();
            StringWriter writer = new StringWriter();
            pdfTextStripper.writeText(document, writer);

            return writer.toString();
        } catch (IOException e) {
            e.printStackTrace();
            
            return null;
        }
    }
}




