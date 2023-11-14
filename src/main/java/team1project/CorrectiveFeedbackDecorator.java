package team1project;



public class CorrectiveFeedbackDecorator extends PDFBaseDecorator {
    
    public CorrectiveFeedbackDecorator(EvaluatorObserver parent, PDFManager pdfManager) {
        super(parent, pdfManager);
    }

    @Override
    public void update(Submission submission) {
        super.update(submission);
        getPDFManager().createNewPage();
        getPDFManager().addContentToStream(" Corrective Feedback: " + submission.getFeedback());
        getPDFManager().closeContentStream();
    }
}
