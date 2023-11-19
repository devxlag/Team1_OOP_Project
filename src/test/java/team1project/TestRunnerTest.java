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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Map;

import org.junit.jupiter.api.Test;

public class TestRunnerTest {

    @Test
    void testRunTests() {
        // Create a mock submission for testing
        Submission submission = new Submission();
        submission.setStudentID("123456");

        // Run tests using the TestRunner
        TestRunner testRunner = new TestRunner();
        boolean result = testRunner.runTests(submission);

        // Ensure that tests are executed successfully
        assertTrue(result);

        // Verify that results are set in the submission
        ArrayList<TestResult> testResults = submission.getResults();
        assertNotNull(testResults);

        // Verify that at least one test result is present
        assertTrue(testResults.size() > 0);

        // Verify that each test result has valid information
        for (TestResult testResult : testResults) {
            assertNotNull(testResult.getTestName());
            assertNotNull(testResult.getClassName());
            assertNotNull(testResult.getMethodName());
            assertNotNull(testResult.getStatus());
            assertNotNull(testResult.getScore());
        }
    }

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
