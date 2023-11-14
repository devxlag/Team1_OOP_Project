package team1project;



public class TestCaseStatusDecorator extends PDFBaseDecorator {

    public TestCaseStatusDecorator(EvaluatorObserver parent, PDFManager pdfManager) {
        super(parent, pdfManager);
    }

    @Override
    public void update(Submission submission) {
        super.update(submission);
        getPDFManager().createNewPage();
        getPDFManager().addContentToStream(" Test Cases Feedback: " + submission.getFeedback());
        getPDFManager().closeContentStream();
    }
}

