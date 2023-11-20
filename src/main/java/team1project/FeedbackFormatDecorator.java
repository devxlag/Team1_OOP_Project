package team1project;

import java.util.ArrayList;

/**
 * FeedbackFormatDecorator extends PDFBaseDecorator and represents a decorator for formatting feedback in a PDF document.
 * It adds specific content to the PDF related to a student's submission and test results.
 */

public class FeedbackFormatDecorator extends PDFBaseDecorator{
    
private String feedbackContent;


    /**
     * Constructs a new FeedbackFormatDecorator object. Calls the constructor of the superclass.
     */
    public FeedbackFormatDecorator() {
        super();
    }


    @Override

      /**
     * Updates the PDF document with feedback information based on the evaluator's submission.
     * 
     * @param evaluator The evaluator providing information about the student's submission and test results.
     * @return True if the update was successful, false otherwise.
     */

    public boolean update(Evaluator evaluator) {
        try{
            
            // Extract submission details from the evaluator 
            Submission submission = evaluator.getSubmission();
                    
            getPDFManager().createNewDocument();
            
            // Generate basic content about the student's submission
            String basicContent = "Student ID: " + submission.getStudentID() + "\n" + "Student Name: " + submission.getStudentName() + "\n" + "Assignment: " + submission.getAssignmentNo() +"\n" + "Files Submitted: " + submission.getFilesSubmitted() + "\n" + "Missing Files: " + submission.getMissingFiles() + "\n" + "Overall Score: " + submission.getTotalScore();
            
            
            // Generate class-wise scores
            String passengerScore = "Passenger Class Score: " + submission.getPassengerClassScore() + "/16" + "\n";
            String flightScore = "Flight Class Score: " + submission.getFlightClassScore()  + "/16"+ "\n";
            String luggageSlipScore = "Luggage Slip Class Score: " + submission.getLuggageSlipClassScore()  + "/14"+ "\n";
            String luggageManifestScore = "Luggage Manifest Class Score: " + submission.getLuggageManifestClassScore()  + "/20"+ "\n";
            String compileScore = "Compile Score: " + submission.getCompileScore()  + "/5"+ "\n";
            String cleanCodeScore = "Has clean Code: " + submission.getTotalCleanCodeScore()  + "/5"+ "\n";
            String junitScore = "Passes JUnit Tests: " + submission.getPassesAllJunit()  + "/14"+ "\n";
            String runs = "Runs (LuggageManagementSystem): " + submission.getLuggageSystemScore()  + "/10"+ "\n";
            String classScores = "\n" + passengerScore +  "\n" + flightScore +  "\n" +luggageSlipScore + "\n" + luggageManifestScore + "\n" + compileScore + "\n" + cleanCodeScore + "\n" + junitScore + "\n" + runs;

            basicContent += "\n" + classScores;

            // Generate corrective feedback and test case feedback
            String correctiveFeedback = " Corrective Feedback: \n";
            String testCaseFeedBack = " Test Cases Feedback: \n";
            String correctiveFeed = "";
            String fTestFeed = "";
            String lTestFeed = "";
            String pTestFeed = "";
            String mTestFeed = "";

            // Iterate over test results to gather feedback
            for(TestResult tr : submission.getResults())
            {
                if(tr.getStatus().equals("FAILED"))
                {
                    correctiveFeed = "  " + tr.getMethodName() + " " + "\n  Comment: " + tr.getFeedback() + "\n";
                    correctiveFeedback += "\n" + correctiveFeed;
                }

                if(tr.getStatus().equals("PASSED"))
                {
                    tr.setErrorMessage("No Error");
                }

                // Group test feedback by class name
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

            // Include a message if no corrective feedback is given

            if(!(submission.getResults().size() > 1))
            {
                correctiveFeedback += "\n  No corrective feedback to give. You passed all the tests!";
            }
            else{
                correctiveFeedback += "\n  You did not adhere to the requirements of the assignment or your files failed to compile.";
            }


            // Concatenate test case feedback for different class types
            testCaseFeedBack += "\n" + "\n Passenger Class Tests:\n  " + pTestFeed + "\n\n Flight Class Tests:\n  " + fTestFeed + "\n\n Luggage Slip Class Tests:\n  " +  lTestFeed + "\n\n Luggage Manifest Class Tests:\n  " + mTestFeed;

            // Add content to the PDF document and close content streams
            getPDFManager().createNewPage();
            getPDFManager().addContentToStream(basicContent);
            getPDFManager().closeContentStream();
            
            getPDFManager().createNewPage();
            getPDFManager().addContentToStream(testCaseFeedBack);
            getPDFManager().closeContentStream();

            getPDFManager().createNewPage();
            getPDFManager().addContentToStream( correctiveFeedback);
            getPDFManager().closeContentStream();
            
            //Get feedback stored in pdf
            feedbackContent=getPDFManager().getGeneratedContent();

            // Call the superclass update method
            super.update(evaluator);

        
        
            return true;
        }catch (Exception e) {
                e.printStackTrace();
                return false; 
            }
    }

    
    
    /**
     * Retrieves common information for each test result.
     * 
     * @param tr The TestResult object containing information about a specific test.
     * @return A formatted string containing test information.
     */
    private static String getTestInfo(TestResult tr) 
    {
        return tr.getMethodName() + " " + tr.getStatus() + " " + tr.getScore() + " Error: " + tr.getErrorMessage();
    }


     /**
     * Gets the feedback content stored in the PDF document.
     * 
     * @return The feedback content as a string.
     */
    public String getFeedbackContent() 
    {
        return feedbackContent;
    }

}
