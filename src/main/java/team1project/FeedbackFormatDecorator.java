package team1project;

import java.util.ArrayList;

public class FeedbackFormatDecorator extends PDFBaseDecorator{
    

    public FeedbackFormatDecorator() {
        super();
    }


    @Override
    public boolean update(Evaluator evaluator) {
        Submission submission = evaluator.getSubmission();
                 
        getPDFManager().createNewDocument();
        
        String basicContent = "Student ID: " + submission.getStudentID() + "\n" + "Student Name: " + submission.getStudentName() + "\n" + "Files Submitted: " + submission.getFilesSubmitted() + "\n" + "Missing Files: " + submission.getMissingFiles() + "\n" + "Overall Score: " + submission.getTotalScore();
        
        
        String passengerScore = "Passenger Class Score: " + submission.getPassengerClassScore() + "/16" + "\n";
        String flightScore = "Flight Class Score: " + submission.getFlightClassScore()  + "/16"+ "\n";
        String luggageSlipScore = "Luggage Slip Class Score: " + submission.getLuggageSlipClassScore()  + "/14"+ "\n";
        String luggageManifestScore = "Luggage Manifest Class Score: " + submission.getLuggageManifestClassScore()  + "/20"+ "\n";
        String classScores = "\n" + passengerScore +  "\n" + flightScore +  "\n" +luggageSlipScore + "\n" + luggageManifestScore;

        basicContent += "\n" + classScores;

        String correctiveFeedback = " Corrective Feedback: \n";
        String testCaseFeedBack = " Test Cases Feedback: \n";
        String correctiveFeed = "";
        String fTestFeed = "";
        String lTestFeed = "";
        String pTestFeed = "";
        String mTestFeed = "";
        for(TestResult tr : submission.getResults())
        {
            if(tr.getStatus().equals("FAILED"))
            {
                correctiveFeed = "  " + tr.getTestName() + " " + "\n  Comment: " + tr.getFeedback() + "\n";
                correctiveFeedback += "\n" + correctiveFeed;
            }

            if (tr.getClassName().contains("PassengerTest")){
                pTestFeed += "\n  " + getTestInfo(tr);
            } else if(tr.getClassName().contains("FlightTest")){
                fTestFeed += "\n  " + getTestInfo(tr);
            } else if(tr.getClassName().contains("LuggageSlipTest")){
                lTestFeed += "\n  " + getTestInfo(tr);
            } else if(tr.getClassName().contains("LuggageManifestTest")){
                mTestFeed += "\n  " + getTestInfo(tr);
            }                                                           
        }

        if(correctiveFeed.equals("")){
            correctiveFeedback += "\n  No corrective feedback to give. You passed all the tests!";
        }

            testCaseFeedBack += "\n" + "\n Passenger Class Tests:\n  " + pTestFeed + "\n\n Flight Class Tests:\n  " + fTestFeed + "\n\n Luggage Slip Class Tests:\n  " +  lTestFeed + "\n\n Luggage Manifest Class Tests:\n  " + mTestFeed;

        getPDFManager().createNewPage();
        getPDFManager().addContentToStream(basicContent);
        getPDFManager().closeContentStream();
        
        getPDFManager().createNewPage();
        getPDFManager().addContentToStream(testCaseFeedBack);
        getPDFManager().closeContentStream();

        getPDFManager().createNewPage();
        getPDFManager().addContentToStream( correctiveFeedback);
        getPDFManager().closeContentStream();
        super.update(evaluator);
        
        return true;
    }

    
    
    // Define a method to retrieve common information for each test
    private static String getTestInfo(TestResult tr) {
        return tr.getMethodName() + " " + tr.getStatus() + " " + tr.getScore() + " Error: " + tr.getErrorMessage();
    }
}
