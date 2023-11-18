package team1project;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class FeedbackFormatDecoratorTest {

    private FeedbackFormatDecorator feedbackDecorator;
    private Evaluator evaluator;

    @Before
    public void setUp() {
        feedbackDecorator = new FeedbackFormatDecorator();
        evaluator = new Evaluator(); 
    }

    @Test
    public void testFeedbackFormatDecoratorUpdate() {
        // Create a submission and set it in the evaluator
        Submission submission = createMockSubmission();
        evaluator.setSubmission(submission);

        // Set up mock data 
  
        TestResult testResult1 = createMockTestResult("PassengerTest", "testMethod1", "PASSED", 10, "");
        TestResult testResult2 = createMockTestResult("FlightTest", "testMethod2", "FAILED", 5, "Some feedback");

        // Add test results to the submission
        submission.getResults().add(testResult1);
        submission.getResults().add(testResult2);

        // Call the update method
        boolean result = feedbackDecorator.update(evaluator);

        // Check if the update was successful
        assertTrue(result);

        // Check if the PDF content contains expected information 
       
        String pdfContent = feedbackDecorator.getFeedbackContent();
        assertTrue(pdfContent.contains("Student ID: " + submission.getStudentID()));
        assertTrue(pdfContent.contains("Files Submitted: " + submission.getFilesSubmitted()));
        assertTrue(pdfContent.contains("Missing Files: " + submission.getMissingFiles()));
        assertTrue(pdfContent.contains("Overall Score: " + submission.getTotalScore()));
        assertTrue(pdfContent.contains("Passenger Class Score: " + submission.getPassengerClassScore() + "/16"));
        assertTrue(pdfContent.contains("Corrective Feedback:"));
        assertTrue(pdfContent.contains("Test Cases Feedback:"));
        assertTrue(pdfContent.contains("Passenger Class Tests:"));
        assertTrue(pdfContent.contains("Flight Class Tests:"));
        assertTrue(pdfContent.contains("Luggage Slip Class Tests:"));
        assertTrue(pdfContent.contains("Luggage Manifest Class Tests:"));
    }

    // Helper method to create a mock submission 
    private Submission createMockSubmission() {
        Submission submission = new Submission();
        submission.setStudentID("123");
        submission.setStudentName("John Doe");
        submission.addFileSubmitted("Passenger.java");
        submission.addFileSubmitted("Flight.java");
        submission.addMissingFile("LuggageSlip.java");
        submission.setTotalScore(80);
        submission.setPassengerClassScore(16);
        submission.setFlightClassScore(20);
        submission.setLuggageSlipClassScore(14);
        submission.setLuggageManifestClassScore(30);
        return submission;
    }

    // Helper method to create a mock TestResult
    private TestResult createMockTestResult(String className, String methodName, String status, int score, String feedback) {
        TestResult testResult = new TestResult();
        testResult.setClassName(className);
        testResult.setMethodName(methodName);
        testResult.setStatus(status);
        testResult.setScore(score);
        testResult.setFeedback(feedback);
        return testResult;
    }
}
