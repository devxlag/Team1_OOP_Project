package team1project;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.Test;

/**
 * Unit tests for the {@link FeedBack} class.
 * 
 */
public class FeedBackTest {

    /**
     * Tests the 'update'  method of the FeedBack class.
     * It checks if the 'update' method updates the submission's feedback correctly.
     */
    @Test
    public void testUpdate() {

        // Create a submission and set it in the evaluator
        Submission submission = createMockSubmission();
        Evaluator evaluator = new Evaluator();
        

        // Set up mock data
        TestResult testResult1 = createMockTestResult("FlightTest", "testFlightConstructor", "FAILED", 10, "org.junit.ComparisonFailure");
        

        // Add test result to the submission
        submission.getResults().add(testResult1);

        Feedback feedback = new Feedback();
        evaluator.setSubmission(submission);
        // Call the update method
        boolean result = feedback.update(evaluator);

        // Check if the update was successful
        assertTrue(result);
        String expected = "Review the Flight constructor. \n" + " Check your string comparisons. The expected and actual strings do not match.";
        
        assertEquals(expected, submission.getResults().get(0).getFeedback());
           
    }

    /**
     * Helper method to create a mock submission for testing purposes.
     *
     * @return A mock Submission object.
     */
    private Submission createMockSubmission()
     {
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

   /**
     * Helper method to create a mock TestResult for testing purposes.
     *
     * @param className The class name of the test.
     * @param methodName The method name of the test.
     * @param status The status of the test (PASSED/FAILED).
     * @param score The score achieved in the test.
     * @param feedback The feedback provided for the test.
     * @return A mock TestResult object.
     */

    private TestResult createMockTestResult(String className, String methodName, String status, int score, String error)
     {
        TestResult testResult = new TestResult();
        testResult.setClassName(className);
        testResult.setMethodName(methodName);
        testResult.setStatus(status);
        testResult.setScore(score);
        testResult.setRawErrorMessage(error);        
        return testResult;
    }
    
}
