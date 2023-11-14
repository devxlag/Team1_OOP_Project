package team1project;



public class PDFBasicPrinter implements EvaluatorObserver {

    private PDFManager pdfManager;

    public PDFBasicPrinter(PDFManager pdfManager) {
        this.pdfManager = pdfManager;
    }

    public boolean update(Submission submission) {
        pdfManager.createNewPage();

        // Add content to the page
        String[] basicContent = {"Student Name: " + submission.getStudentName(), "Student ID: " + submission.getStudentID()};
        pdfManager.addBasicContent(basicContent);

        // Add other basic information as needed

        // Close the content stream
        pdfManager.closeContentStream();
        return true;
    }

    
}
