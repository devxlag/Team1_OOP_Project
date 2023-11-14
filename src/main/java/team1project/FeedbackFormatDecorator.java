package team1project;



public class FeedbackFormatDecorator extends PDFBaseDecorator{
    

    public FeedbackFormatDecorator(PDFManager pdfManager) {
        super(pdfManager);
    }


    @Override
    public boolean update(Submission submission){
        getPDFManager().createNewDocument();
        getPDFManager().createNewPage();
        String[] basicContent = {"Student ID: " + submission.getStudentID()};
        getPDFManager().addBasicContent(basicContent);        
        getPDFManager().addContentToStream("Overall Score: " + submission.getScore() );
        getPDFManager().closeContentStream();
        getPDFManager().createNewPage();
        getPDFManager().addContentToStream(" Corrective Feedback: " + submission.getFeedback());
        getPDFManager().closeContentStream();
        getPDFManager().createNewPage();

        String testCaseFeedBack = "";
        for(TestResult tr : submission.getResults())
        {
            testCaseFeedBack += "\n" + tr.getFeedback();
        }
        
        getPDFManager().addContentToStream(" Test Cases Feedback: " + testCaseFeedBack);


        getPDFManager().closeContentStream();
        super.update(submission);
        return true;
    }
}
