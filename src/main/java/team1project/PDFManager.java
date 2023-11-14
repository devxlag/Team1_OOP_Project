package team1project;


    
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

import java.io.IOException;

public class PDFManager {
    private PDDocument document;
    private PDPage currentPage;
    private PDPageContentStream currentContentStream;

    public PDFManager() {
        document = new PDDocument();
    }

    public void createNewPage() {
        currentPage = new PDPage();
        document.addPage(currentPage);

        try {
            currentContentStream = new PDPageContentStream(document, currentPage, PDPageContentStream.AppendMode.APPEND, true);
        } catch (IOException e) {
            e.printStackTrace();
            // Handle the exception appropriately
        }
    }
    public void addBasicContent(String[] lines) {
        try {
            float margin = 50;
            float yPosition = currentPage.getMediaBox().getHeight() - margin;

            currentContentStream.beginText();
            currentContentStream.setFont(PDType1Font.COURIER_BOLD, 12);
             currentContentStream.newLineAtOffset(margin, yPosition);
            float lineHeight = 12;

            for (String line : lines) {

                currentContentStream.newLineAtOffset(0, -lineHeight);
                currentContentStream.showText(line);
                
            }

            currentContentStream.endText();
        } catch (IOException e) {
            e.printStackTrace();
            // Handle the exception appropriately
        }
    }

    public void addContentToStream(String additionalContent) {
        try {
            float margin = 50;
            float yPosition = currentPage.getMediaBox().getHeight() - margin;

            currentContentStream.beginText();
            currentContentStream.setFont(PDType1Font.COURIER_BOLD, 12); 
            currentContentStream.newLineAtOffset(margin, yPosition);
            currentContentStream.showText(additionalContent);
            currentContentStream.endText();
            //currentContentStream.close();
        } catch (IOException e) {
            e.printStackTrace();
            // Handle the exception appropriately
        }
    }
    public void closeContentStream() {
        try {
            if (currentContentStream != null) {
                currentContentStream.close();
                currentContentStream = null; // Set it to null after closing
            }
        } catch (IOException e) {
            e.printStackTrace();
            // Handle the exception appropriately
        }
    }
    
    public void saveAndClose() {
        try {
            document.save("C:\\Users\\myahs\\OneDrive\\Documents\\COMP3607Proj\\final_submission.pdf");
        } catch (IOException e) {
            e.printStackTrace();
            // Handle the exception appropriately
        } finally {
            closeDocument();
        }
    }

    private void closeDocument() {
        if (document != null) {
            try {
                document.close();
            } catch (IOException e) {
                e.printStackTrace();
                // Handle the exception appropriately
            }
        }
    }
}


