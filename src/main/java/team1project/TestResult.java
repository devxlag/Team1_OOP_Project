package team1project;

/**
 * The TestResult class represents the result of a test executed on a specific class method.
 * It includes information such as test name, class name, method name, status, error message,
 * feedback, and the obtained score.
 */
public class TestResult {

    private String testName;
    private String className;
    private String methodName;
    private String status;
    private String rawErrorMessage;
    private String errorMessage;
    private String feedback;
    private int score;

    /**
     * Gets the class name associated with the test result.
     *
     * @return The class name.
     */
    public String getClassName() {
        return className;
    }

    /**
     * Sets the class name associated with the test result.
     *
     * @param className The class name to set.
     */
    public void setClassName(String className) {
        this.className = className;
    }

    /**
     * Gets the method name associated with the test result.
     *
     * @return The method name.
     */
    public String getMethodName() {
        return methodName;
    }

    /**
     * Sets the method name associated with the test result.
     *
     * @param methodName The method name to set.
     */
    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    /**
     * Gets the status of the test result (e.g., "passed" or "failed").
     *
     * @return The test result status.
     */
    public String getStatus() {
        return status;
    }

    /**
     * Sets the status of the test result.
     *
     * @param status The status to set.
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * Gets the raw error message associated with a failed test result.
     *
     * @return The raw error message.
     */
    public String getRawErrorMessage() {
        return rawErrorMessage;
    }

    /**
     * Sets the raw error message associated with a failed test result.
     *
     * @param rawErrorMessage The raw error message to set.
     */

    public void setRawErrorMessage(String rawErrorMessage) {
        this.rawErrorMessage = rawErrorMessage;
    }

    /**
     * Gets the error message associated with a failed test result.
     *
     * @return The error message.
     */
    public String getErrorMessage() {
        return errorMessage;
    }

    /**
     * Sets the error message associated with a failed test result.
     *
     * @param errorMessage The error message to set.
     */
    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    /**
     * Gets the score obtained for the test.
     *
     * @return The test score.
     */
    public int getScore() {
        return score;
    }

    /**
     * Sets the score obtained for the test.
     *
     * @param score The test score to set.
     */
    public void setScore(int score) {
        this.score = score;
    }

    /**
     * Gets the feedback associated with the test result.
     *
     * @return The feedback.
     */
    public String getFeedback() {
        return feedback;
    }

    /**
     * Sets the feedback associated with the test result.
     *
     * @param feedback The feedback to set.
     */
    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }

    /**
     * Gets the name of the test.
     *
     * @return The test name.
     */
    public String getTestName() {
        return testName;
    }

    /**
     * Sets the name of the test.
     *
     * @param testName The test name to set.
     */
    public void setTestName(String testName) {
        this.testName = testName;
    }

    /**
     * Generates a string representation of the TestResult object.
     *
     * @return A string representation of the TestResult object.
     */
    @Override
    public String toString() {
        return "TestResult{" +
                "testName='" + testName + '\'' +
                ", className='" + className + '\'' +
                ", methodName='" + methodName + '\'' +
                ", status='" + status + '\'' +
                ", errorMessage='" + errorMessage + '\'' +
                ", score=" + score +
                ", feedback='" + feedback + '\'' +
                '}';
    }
}
