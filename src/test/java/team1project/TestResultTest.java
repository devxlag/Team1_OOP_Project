package team1project;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.Test;

/**
 * The TestResultTest class contains unit tests for the TestResult class.
 */
public class TestResultTest {

    /**
     * Tests the set and get methods for the class name in the TestResult class.
     */
    @Test
    public void testSetAndGetClassName() {
        TestResult testResult = new TestResult();
        assertNull(testResult.getClassName());

        testResult.setClassName("TestClass");
        assertEquals("TestClass", testResult.getClassName());
    }

    /**
     * Tests the set and get methods for the method name in the TestResult class.
     */
    @Test
    public void testSetAndGetMethodName() {
        TestResult testResult = new TestResult();
        assertNull(testResult.getMethodName());

        testResult.setMethodName("testMethod");
        assertEquals("testMethod", testResult.getMethodName());
    }

    /**
     * Tests the set and get methods for the status in the TestResult class.
     */
    @Test
    public void testSetAndGetStatus() {
        TestResult testResult = new TestResult();
        assertNull(testResult.getStatus());

        testResult.setStatus("PASS");
        assertEquals("PASS", testResult.getStatus());

        testResult.setStatus("FAIL");
        assertEquals("FAIL", testResult.getStatus());
    }

    /**
     * Tests the set and get methods for the error message in the TestResult class.
     */
    @Test
    public void testSetAndGetErrorMessage() {
        TestResult testResult = new TestResult();
        assertNull(testResult.getErrorMessage());

        testResult.setErrorMessage("Error occurred");
        assertEquals("Error occurred", testResult.getErrorMessage());
    }

    /**
     * Tests the set and get methods for the score in the TestResult class.
     */
    @Test
    public void testSetAndGetScore() {
        TestResult testResult = new TestResult();
        assertEquals(0, testResult.getScore());

        testResult.setScore(85);
        assertEquals(85, testResult.getScore());
    }

    /**
     * Tests the toString method in the TestResult class.
     * Verifies that the method returns the expected string representation of the TestResult object.
     */
    @Test
    public void testToString() {
        TestResult testResult = new TestResult();
        testResult.setTestName("testMethod(TestClass)");
        testResult.setClassName("TestClass");
        testResult.setMethodName("testMethod");
        testResult.setStatus("FAIL");
        testResult.setErrorMessage("Error occurred");
        testResult.setScore(0);
        testResult.setFeedback(" ");

        String expectedToString = "TestResult{" +
                                    "testName='" + "testMethod(TestClass)" + '\'' +
                                    ", className='" + "TestClass" + '\'' +
                                    ", methodName='" + "testMethod" + '\'' +
                                    ", status='" + "FAIL" + '\'' +
                                    ", errorMessage='" + "Error occurred" + '\'' +
                                    ", score=" + "0" +
                                    ", feedback='" + " " + '\'' +
                                    '}';

        assertEquals(expectedToString, testResult.toString());
    }

    /**
     * Tests the set and get methods for the test name in the TestResult class.
     */
    @Test
    public void testSetAndGetTestName() {
        TestResult testResult = new TestResult();
        assertNull(testResult.getTestName());

        testResult.setTestName("SampleTest");
        assertEquals("SampleTest", testResult.getTestName());
    }
}

