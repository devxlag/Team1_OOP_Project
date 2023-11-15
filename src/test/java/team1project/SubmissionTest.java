package team1project;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.Test;

public class SubmissionTest {

    @Test
    public void testDefaultConstructor() {
        Submission submission = new Submission();

        assertNotNull(submission.getFilesSubmitted());
        assertNotNull(submission.getResults());
        assertTrue(submission.getFilesSubmitted().isEmpty());
        assertTrue(submission.getResults().isEmpty());
    }

    @Test
    public void testSetAndGetStudentName() {
        Submission submission = new Submission();
        assertNull(submission.getStudentName());

        submission.setStudentName("John Doe");
        assertEquals("John Doe", submission.getStudentName());
    }

    @Test
    public void testSetAndGetStudentID() {
        Submission submission = new Submission();
        assertNull(submission.getStudentID());

        submission.setStudentID("123456");
        assertEquals("123456", submission.getStudentID());
    }

    @Test
    public void testSetAndGetScore() {
        Submission submission = new Submission();
        assertEquals(0, submission.getScore());

        submission.setScore(85);
        assertEquals(85, submission.getScore());
    }

    @Test
    public void testSetAndGetFeedback() {
        Submission submission = new Submission();
        assertNull(submission.getFeedback());

        submission.setFeedback("Good job!");
        assertEquals("Good job!", submission.getFeedback());
    }

    @Test
    public void testAddFileSubmitted() {
        Submission submission = new Submission();
        assertTrue(submission.getFilesSubmitted().isEmpty());

        submission.addFileSubmitted("file1.java");
        submission.addFileSubmitted("file2.java");

        assertEquals(Arrays.asList("file1.java", "file2.java"), submission.getFilesSubmitted());
    }

    @Test
    public void testSetAndGetResults() {
        Submission submission = new Submission();
        assertTrue(submission.getResults().isEmpty());

        TestResult testResult1 = new TestResult();
        TestResult testResult2 = new TestResult();

        ArrayList<TestResult> results = new ArrayList<>(Arrays.asList(testResult1, testResult2));

        submission.setResults(results);

        assertEquals(results, submission.getResults());
    }
}
