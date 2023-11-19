package team1project;

import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Map;
import org.junit.jupiter.api.Test;
import org.junit.runner.Description;
import org.junit.runner.notification.Failure;

/**
 * The TestRunnerTest class contains unit tests for the TestRunner class.
 */
public class TestRunnerTest {

    /**
     * Tests the runTests method of the TestRunner class.
     * Verifies that tests are executed successfully, and results are set in the submission.
     * This test uses the SubmissionTest class as the test class.
     */
    @Test
    public void testRunTests() {
        Submission submission = new Submission(); // Assuming there's a constructor that takes a student ID
        Class<?>[] testClasses = {SubmissionTest.class}; // Use the current class as the test class

        TestRunner testRunner = new TestRunner();
        // Run the tests
       testRunner.runTests(submission, testClasses);

        // Assert the expected results based on the test logic
        assertEquals(7, submission.getResults().size());
        // Add more assertions as needed
    }

    /**
     * Tests the TestResultListener class within the TestRunner.
     * Verifies that test events (start, failure, finish) are handled correctly.
     */
    @Test
    void testTestResultListener() {
        TestRunner.TestResultListener listener = new TestRunner.TestResultListener();

        // Simulate a test start event
        Description startDescription = Description.createTestDescription(TestRunnerTest.class, "testRunTests");
        try {
            listener.testStarted(startDescription);
        } catch (Exception e) {
            // Handle the exception if needed
            e.printStackTrace();
        }

        // Verify that the currentTest is set
        assertEquals("team1project.TestRunnerTest#testRunTests", listener.getCurrentTest());

        // Simulate a test failure event
        Failure failure = new Failure(startDescription, new RuntimeException("Test failure message"));
        try {
            listener.testFailure(failure);
        } catch (Exception e) {
            // Handle the exception if needed
            e.printStackTrace();
        }

        // Verify that the failure information is recorded
        Map<String, TestResult> testResults = listener.getTestResultsMap();
        assertTrue(testResults.containsKey("team1project.TestRunnerTest#testRunTests"));
        TestResult testResult = testResults.get("team1project.TestRunnerTest#testRunTests");
        assertEquals("testRunTests(team1project.TestRunnerTest)", testResult.getTestName());
        assertEquals("team1project.TestRunnerTest", testResult.getClassName());
        assertEquals("testRunTests", testResult.getMethodName());
        assertEquals("FAILED", testResult.getStatus());
        assertNotNull(testResult.getErrorMessage());

        // Simulate a test finish event
        Description finishDescription = Description.createTestDescription(TestRunnerTest.class, "testTestResultListener");
        try {
            listener.testStarted(finishDescription);
            listener.testFinished(finishDescription);
        } catch (Exception e) {
            // Handle the exception if needed
            e.printStackTrace();
        }

        // Verify that the finished test is recorded with a passing result
        assertTrue(testResults.containsKey("team1project.TestRunnerTest#testTestResultListener"));
        TestResult finishedTestResult = testResults.get("team1project.TestRunnerTest#testTestResultListener");
        assertEquals("testTestResultListener(team1project.TestRunnerTest)", finishedTestResult.getTestName());
        assertEquals("team1project.TestRunnerTest", finishedTestResult.getClassName());
        assertEquals("testTestResultListener", finishedTestResult.getMethodName());
        assertEquals("PASSED", finishedTestResult.getStatus());
        assertNull(finishedTestResult.getErrorMessage());
    }
}
