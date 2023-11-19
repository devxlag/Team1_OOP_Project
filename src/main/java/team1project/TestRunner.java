package team1project;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import org.junit.runner.Description;
import org.junit.runner.notification.Failure;
import org.junit.runner.notification.RunListener;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;

/**
 * The TestRunner class is responsible for running JUnit tests and collecting their results.
 */
public class TestRunner {

    private boolean testRunnerCalled = false;

    public boolean isTestRunnerCalled() {
        return testRunnerCalled;
    }

    public void setTestRunnerCalled(boolean testRunnerCalled) {
        this.testRunnerCalled = testRunnerCalled;
    }

    /**
     * Runs JUnit tests for the given submission.
     *
     * @param submission The submission for which tests are run.
     * @return True if the tests are run successfully, false otherwise.
     */
    public void runTests(Submission submission, Class<?>[] testClasses) {
        setTestRunnerCalled(true);
        
        // Create a JUnitCore instance and add a custom RunListener to collect test results
        JUnitCore junit = new JUnitCore();
        TestResultListener listener = new TestResultListener();
        junit.addListener(listener);

        System.out.println("Running tests... for " + submission.getStudentID());

        Result result = junit.run(testClasses);

        ArrayList<TestResult> finalResults = listener.getTestResults();
        System.out.println("Number of tests found: " + finalResults.size());
        submission.setResults(finalResults);        
    }

    /**
     * A custom RunListener that collects JUnit test results.
     */
    static class TestResultListener extends RunListener {
        private Map<String, TestResult> testResults = new HashMap<>();
        private String currentTest;

        /**
         * Called when a test is started.
         *
         * @param description The description of the test.
         * @throws Exception If an exception occurs.
         */
        @Override
        public void testStarted(Description description) throws Exception {
            currentTest = description.getClassName() + "#" + description.getMethodName();
        }

        /**
         * Called when a test fails.
         *
         * @param failure The failure information.
         * @throws Exception If an exception occurs.
         */
        @Override
        public void testFailure(Failure failure) throws Exception {
            TestResult testResult = new TestResult();
            Description description = failure.getDescription();
            testResult.setTestName(description.getDisplayName());
            testResult.setClassName(description.getClassName());
            testResult.setMethodName(description.getMethodName());
            testResult.setStatus("FAILED");
            testResult.setErrorMessage(failure.getException().getMessage());
            testResults.put(currentTest, testResult);
        }

        /**
         * Called when a test is finished.
         *
         * @param description The description of the test.
         * @throws Exception If an exception occurs.
         */
        @Override
        public void testFinished(Description description) throws Exception {
            if (!testResults.containsKey(currentTest)) {
                TestResult testResult = new TestResult();
                testResult.setTestName(description.getDisplayName());
                testResult.setClassName(description.getClassName());
                testResult.setMethodName(description.getMethodName());
                testResult.setStatus("PASSED");
                testResults.put(currentTest, testResult);
            }
        }

        /**
         * Gets the name of the currently processed test.
         *
         * @return The current test name.
         */
        public String getCurrentTest() {
            return currentTest;
        }

        /**
         * Gets the map of test results.
         *
         * @return The map of test results.
         */
        public Map<String, TestResult> getTestResultsMap() {
            return testResults;
        }

        /**
         * Gets the list of test results.
         *
         * @return The list of test results.
         */
        public ArrayList<TestResult> getTestResults() {
            return new ArrayList<>(testResults.values());
        }
    }
}
