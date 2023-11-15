package team1project;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.Test;

public class TestResultTest {

    @Test
    public void testSetAndGetClassName() {
        TestResult testResult = new TestResult();
        assertNull(testResult.getClassName());

        testResult.setClassName("TestClass");
        assertEquals("TestClass", testResult.getClassName());
    }

    @Test
    public void testSetAndGetMethodName() {
        TestResult testResult = new TestResult();
        assertNull(testResult.getMethodName());

        testResult.setMethodName("testMethod");
        assertEquals("testMethod", testResult.getMethodName());
    }

    @Test
    public void testSetAndGetStatus() {
        TestResult testResult = new TestResult();
        assertNull(testResult.getStatus());

        testResult.setStatus("PASS");
        assertEquals("PASS", testResult.getStatus());

        testResult.setStatus("FAIL");
        assertEquals("FAIL", testResult.getStatus());
    }

    @Test
    public void testSetAndGetErrorMessage() {
        TestResult testResult = new TestResult();
        assertNull(testResult.getErrorMessage());

        testResult.setErrorMessage("Error occurred");
        assertEquals("Error occurred", testResult.getErrorMessage());
    }

    @Test
    public void testSetAndGetScore() {
        TestResult testResult = new TestResult();
        assertEquals(0, testResult.getScore());

        testResult.setScore(85);
        assertEquals(85, testResult.getScore());
    }

    @Test
    public void testToString() {
        TestResult testResult = new TestResult();
        testResult.setClassName("TestClass");
        testResult.setMethodName("testMethod");
        testResult.setStatus("FAIL");
        testResult.setErrorMessage("Error occurred");

        String expectedToString = "TestResult{" +
                                  "className='TestClass', " +
                                  "methodName='testMethod', " +
                                  "status='FAIL', " +
                                  "errorMessage='Error occurred'}";

        assertEquals(expectedToString, testResult.toString());
    }

    @Test
    public void testSetAndGetTestName() {
        TestResult testResult = new TestResult();
        assertNull(testResult.getTestName());

        testResult.setTestName("SampleTest");
        assertEquals("SampleTest", testResult.getTestName());
    }
}
