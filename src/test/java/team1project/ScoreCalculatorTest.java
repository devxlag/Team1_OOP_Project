package team1project;

import java.lang.reflect.Array;

import org.junit.Test;
import java.util.ArrayList;
import static org.junit.Assert.*;
/**
 * Unit tests for the {@link ScoreCalculator} class.
 * 
 */
public class ScoreCalculatorTest {
    
    /**
     * Tests the 'update' method of the ScoreCalculator class.
     * It checks if the 'update' method updates the submission's scores correctly.
     */
    @Test
    public void testUpdate(){
        Submission submission = createMockSubmission();
        TestResult testResult1 = createMockTestResult("PassengerTest", "testMethod1", "PASSED", 10, "");
        TestResult testResult2 = createMockTestResult("FlightTest", "testMethod2", "FAILED", 0, "Some feedback");
        TestResult testResult3 = createMockTestResult("LuggageManifestTest", "testMethod3", "PASSED", 10, "");
        Evaluator evaluator = new Evaluator();

        evaluator.setSubmission(submission);

        submission.getResults().add(testResult1);
        submission.getResults().add(testResult2);
        submission.getResults().add(testResult3);
        ArrayList<String> requiredFiles = new ArrayList<>();
        requiredFiles.add("Passenger.java");
        requiredFiles.add("Flight.java");
        requiredFiles.add("LuggageSlip.java");
        

        ScoreCalculator scoreCalculator = new ScoreCalculator(requiredFiles);

        boolean result = scoreCalculator.update(evaluator);

        assertTrue(result);

        assertEquals(10, submission.getPassengerClassScore());
        assertEquals(0, submission.getFlightClassScore());
        assertEquals(10, submission.getLuggageManifestClassScore());
        assertEquals(0, submission.getLuggageSlipClassScore());

        assertEquals(20, submission.getTotalScore());
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
        submission.setTotalScore(0);
        submission.setPassengerClassScore(0);
        submission.setFlightClassScore(0);
        submission.setLuggageSlipClassScore(0);
        submission.setLuggageManifestClassScore(0);
        
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

     private TestResult createMockTestResult(String className, String methodName, String status, int score, String feedback)
     {
        TestResult testResult = new TestResult();
        testResult.setClassName(className);
        testResult.setMethodName(methodName);
        testResult.setStatus(status);
        testResult.setScore(score);
        testResult.setFeedback(feedback);
        return testResult;
    }
}
