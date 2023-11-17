package team1project;
import org.junit.platform.engine.TestDescriptor;
import org.junit.platform.engine.TestExecutionResult;
import org.junit.platform.launcher.Launcher;
import org.junit.platform.launcher.LauncherDiscoveryRequest;
import org.junit.platform.launcher.TestExecutionListener;
import org.junit.platform.launcher.core.LauncherDiscoveryRequestBuilder;
import org.junit.platform.launcher.core.LauncherFactory;
import org.junit.runner.Description;
import org.junit.runner.JUnitCore;
import org.junit.runner.Request;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;
import org.junit.runner.notification.RunListener;

// import java.io.File;
import java.net.MalformedURLException;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.net.URL;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtensionContext;
// import org.junit.jupiter.engine.descriptor.TestDescriptor;
// import org.junit.platform.engine.TestDescriptor.State;
import org.junit.platform.engine.support.hierarchical.Node;
import org.junit.platform.launcher.Launcher;
import org.junit.platform.launcher.LauncherDiscoveryRequest;
import org.junit.platform.launcher.TestExecutionListener;
import org.junit.platform.launcher.core.LauncherDiscoveryRequestBuilder;
import org.junit.platform.launcher.core.LauncherFactory;
import static org.junit.platform.engine.discovery.DiscoverySelectors.selectClass;
public class TestRunner {

//     public static void  runTests(Submission submission) throws ClassNotFoundException, MalformedURLException {
        

//         LauncherDiscoveryRequest request = LauncherDiscoveryRequestBuilder.request()
//         .selectors(selectClass(PassengerTest.class))
//         .build();      

//         Launcher launcher = LauncherFactory.create();

//         TestExecutionListener listener = new TestExecutionListener() {

//       @Override
//       public void executionStarted(TestDescriptor testDescriptor) {
//         System.out.println("Running tests... for " + submission.getStudentID());
//       }

//       @Override
//       public void executionFinished(TestDescriptor testDescriptor, TestExecutionResult testExecutionResult) {
//         Node root = testDescriptor.getTreeRoot();
//         int testsFound = root.getChildren().size();
//         int testsStarted = getTestsStarted(root); 
//         int testsPassed = getTestsSucceeded(root);
//         int testsFailed = getTestsFailed(root);
//         int testsSkipped = getTestsSkipped(root);

//         System.out.println("Number of tests found: " + testsFound);
//         System.out.println("Number of tests started: " + testsStarted);
//         System.out.println("Number of tests succeeded: " + testsPassed);
//         System.out.println("Number of tests failed: " + testsFailed);
//         System.out.println("Number of tests skipped: " + testsSkipped);
//         System.out.println("Test run was successful: " + wasSuccessful(root));
//       }
//     };

//     launcher.registerTestExecutionListeners(listener);
//     launcher.execute(request);

    public static boolean runTests(Submission submission) throws ClassNotFoundException, MalformedURLException {
        JUnitCore junit = new JUnitCore();
        TestResultListener listener = new TestResultListener();
        junit.addListener(listener);
      
        System.out.println("Running tests... for " + submission.getStudentID());
        Result result = junit.run(PassengerTest.class);
        //Result result = junit.run(FlightTest.class, PassengerTest.class,LuggageManifestTest.class, LuggageSlipTest.class);//LuggageManagementSystemTest.class);

        ArrayList<TestResult> finalResults = listener.getTestResults();
        System.out.println("Number of tests found: " + finalResults.size());
        submission.setResults(finalResults);
        return true;
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
            //ArrayList<TestResult> finalResults = testResults.values(); 
            return new ArrayList<>(testResults.values());
        }
    }
}
        

//     // public static void  runTests(Submission submission) throws ClassNotFoundException, MalformedURLException {
        

//     //     LauncherDiscoveryRequest request = LauncherDiscoveryRequestBuilder.request()
//     //     .selectors(DiscoverySelectors.selectClass(PassengerTest.class);)
//     //     .build();

//     //     //DiscoverySelectors.selectClass(PassengerTest.class);

//     // Launcher launcher = LauncherFactory.create();

//     //         JUnitCore junit = new JUnitCore();
//     //     System.out.println(junit);
//     //     System.out.println("Running tests... for " + submission.getStudentID());
//     //     Result result = junit.run(PassengerTest.class);

    
//     //     // Get and print the number of run tests
//     //     int runCount = result.getRunCount();
//     //     System.out.println("Number of tests run: " + runCount);
    
//     //     // Get and print the number of failures
//     //     int failureCount = result.getFailureCount();
//     //     System.out.println("Number of failures: " + failureCount);
    
//     //     // Get and print the number of ignored tests
//     //     int ignoreCount = result.getIgnoreCount();
//     //     System.out.println("Number of ignored tests: " + ignoreCount);
    
//     //     // Get and print the overall result of the test run
//     //     boolean wasSuccessful = result.wasSuccessful();
//     //     System.out.println("Test run was successful: " + wasSuccessful);
    
//     //     // You can also get the list of failures and print details about each failure
//     //     List<Failure> failures = result.getFailures();
//     //     for (Failure failure : failures) {
//     //         System.out.println("Failure: " + failure.toString());
//     //     }
//     //     //junit.release();
//     //     junit = null;
//     //     result = null;
//     // }
//         // Now, you can use the information from the 'result' object to create and return test results
//         // Assuming you have a TestResult class and want to populate it with relevant information
    
       
//         // Populate your testResults list based on the information from the 'result' object
    


       
//     }
       
// }
    
