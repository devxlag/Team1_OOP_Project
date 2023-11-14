package team1project;
public class Main {

    public static void main( String[] args )
    {
       // Create a PDFPrinter object
       Submission submission = new Submission();
       submission.setStudentName( "John Doe");
       submission.setStudentID( "12345");
       submission.setFeedback("Good work, but could improve.");
       submission.setScore(85);
       PDFManager pdfManager = new PDFManager();
      EvaluatorObserver pdfBasicPrinter = new PDFBasicPrinter(pdfManager);
   
           

       // Update each decorator individually with the sample submission
         // Create decorator objects and chain them in the desired order
        
    EvaluatorObserver testCaseStatusDecorator = new TestCaseStatusDecorator(pdfBasicPrinter, pdfManager);
    EvaluatorObserver correctiveFeedbackDecorator = new CorrectiveFeedbackDecorator(testCaseStatusDecorator, pdfManager);
    EvaluatorObserver overallScoreDecorator = new OverallScoreDecorator(correctiveFeedbackDecorator, pdfManager);

    // Update the overallScoreDecorator with the sample submission
    overallScoreDecorator.update(submission);
    pdfManager.saveAndClose();


    }
    
}
