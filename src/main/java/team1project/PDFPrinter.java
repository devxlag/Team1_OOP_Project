package team1project;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.text.PDFTextStripper;

import java.io.IOException;
import java.io.StringWriter;



public class PDFPrinter {
    private PDDocument document;
    private PDPage currentPage;
    private PDPageContentStream currentContentStream;
    private static int filenum = 1;

    public PDFPrinter() {
        //document = new PDDocument();
    }

    public void createNewDocument() {
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
    public PDPage getCurrentPage()
    {
        return currentPage;
    }
    public PDPageContentStream getCurrentContentStream()
    {
        return currentContentStream;
    }
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
            // Handle the exception appropriately
        }
    }

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

            String filename = "submission_results_" + filenum + ".pdf";
            filenum++;
            // document.save("C:\\Users\\Devon Murray\\Desktop\\submission_results\\" + filename);
            document.save("src/main/java/team1project/reports/" + filename);
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
   

    public String getGeneratedContent() {
        // Check if the document is null
        if (document == null) {
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




