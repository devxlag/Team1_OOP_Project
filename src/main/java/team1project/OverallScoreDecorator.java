package team1project;



public class OverallScoreDecorator extends PDFBaseDecorator{
    

    public OverallScoreDecorator(EvaluatorObserver parent, PDFManager pdfManager) {
        super(parent, pdfManager);
    }

    @Override
    public void update(Submission submission) {
        super.update(submission);
        getPDFManager().createNewPage();
        getPDFManager().addContentToStream("Overall Score: " + submission.getScore() );
        getPDFManager().closeContentStream();
    }
}
