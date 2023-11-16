package team1project;
import org.junit.runner.Description;
import org.junit.runner.JUnitCore;
import org.junit.runner.Request;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;
import org.junit.runner.notification.RunListener;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.net.URL;

public class TestRunner {

    public static ArrayList<TestResult> runTests(Submission submission) throws ClassNotFoundException, MalformedURLException {
        JUnitCore junit = new JUnitCore();
        TestResultListener listener = new TestResultListener();
        junit.addListener(listener);
      
        System.out.println("Running tests... for " + submission.getStudentID());
       
        Result result = junit.run(FlightTest.class, PassengerTest.class,LuggageManifestTest.class, LuggageSlipTest.class);//LuggageManagementSystemTest.class); 

        ArrayList<TestResult> testResults = listener.getTestResults();

        return testResults;
    }   

    static class TestResultListener extends RunListener {
        private Map<String, TestResult> testResults = new HashMap<>();
        private String currentTest;

        @Override
        public void testStarted(Description description) throws Exception {
            currentTest = description.getClassName() + "#" + description.getMethodName();
        }

        @Override
        public void testFailure(Failure failure) throws Exception {
            TestResult testResult = new TestResult();
            Description description = failure.getDescription();
            testResult.setTestName(description.getDisplayName());
            testResult.setClassName(description.getClassName());
            testResult.setMethodName(description.getMethodName());            
            testResult.setStatus("FAILED");
            testResult.setScore(0);
            testResult.setErrorMessage(failure.getException().getMessage());
            testResults.put(currentTest, testResult);
        }

        @Override
        public void testFinished(Description description) throws Exception {
            if (!testResults.containsKey(currentTest)) {
                TestResult testResult = new TestResult();
                testResult.setTestName(description.getDisplayName());
                testResult.setClassName(description.getClassName());
                testResult.setMethodName(description.getMethodName());
                testResult.setStatus("PASSED");
                testResult.setScore(1);
                testResults.put(currentTest, testResult);
            }
        }

        public ArrayList<TestResult> getTestResults() {
            return new ArrayList<>(testResults.values());
        }
    }
}