package team1project;
import org.junit.runner.Description;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;
import org.junit.runner.notification.RunListener;
import java.util.ArrayList;
import java.util.List;

public class TestRunner {

    public static ArrayList<TestResult> runTests() {
        JUnitCore junit = new JUnitCore();
        TestResultListener listener = new TestResultListener();
        junit.addListener(listener);

        //Result result = junit.run(FlightTest.class); giving error

        ArrayList<TestResult> testResults = listener.getTestResults();

        System.out.println("Test Results:");
        for (TestResult testResult : testResults) {
            System.out.println(testResult);
        }
        return testResults;
    }

    static class TestResultListener extends RunListener {
        private ArrayList<TestResult> testResults = new ArrayList<>();

        @Override
        public void testFailure(Failure failure) throws Exception {
            Description description = failure.getDescription();
            TestResult testResult = new TestResult();
            testResult.setClassName(description.getClassName());
            testResult.setMethodName(description.getMethodName());
            testResult.setStatus("FAILED");
            testResult.setErrorMessage(failure.getException().getMessage());
            testResults.add(testResult);
        }

        @Override
        public void testFinished(Description description) throws Exception {
            TestResult testResult = new TestResult();
            testResult.setClassName(description.getClassName());
            testResult.setMethodName(description.getMethodName());
            testResult.setStatus("PASSED");
            testResults.add(testResult);
        }

        public ArrayList<TestResult> getTestResults() {
            return testResults;
        }
    }
}
