package team1project;

public class TestResult {

  private String testName;
  private String className;
  private String methodName;
  private String status;
  private String errorMessage;
  private String feedback;  
  private int score;

  // Getters and setters

  public String getClassName() {
      return className;
  }

  public void setClassName(String className) {
      this.className = className;
  }

  public String getMethodName() {
      return methodName;
  }

  public void setMethodName(String methodName) {
      this.methodName = methodName;
  }

  public String getStatus() {
      return status;
  }

  public void setStatus(String status) {
      this.status = status;
  }

  public String getErrorMessage() {
      return errorMessage;
  }

  public void setErrorMessage(String errorMessage) {
      this.errorMessage = errorMessage;
  }

  public int getScore() {
      return score;
  }

  public void setScore(int score) {
      this.score = score;
  }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }

  @Override
  public String toString() {
      return "TestResult{" +
              "className='" + className + '\'' +
              ", methodName='" + methodName + '\'' +
              ", status='" + status + '\'' +
              ", errorMessage='" + errorMessage + '\'' +
              '}';
  }

  public String getTestName() {
    return testName;
  }

  public void setTestName(String testName) {
    this.testName = testName;
  }


}