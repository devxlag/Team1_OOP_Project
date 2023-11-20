package team1project;

import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

/**
 * The Submission class represents a student's submission in a programming project evaluation.
 * It includes information about the student, scores for different components, feedback,
 * and details about the submitted files and test results.
 */
public class Submission {

    private String studentName;
    private String studentID;
    private String assignmentNo;
    private int passengerClassScore = 0;
    private int flightClassScore = 0;
    private int luggageSlipClassScore = 0;
    private int luggageManifestClassScore = 0;
    private int compileScore = 0;
    private int totalScore = 0;
    private int passesAllJunit = 0;
    private int luggageSystemScore = 0;
    private String feedback;
    private ArrayList<String> filesSubmitted;
    private ArrayList<String> missingFiles;
    private ArrayList<TestResult> results;
    private String submissionPath;
    private boolean isCompiled = false;
    private HashMap<String, Double> cleanCodeScoreMap;
    private int totalCleanCodeScore = 0;

    /**
     * Default constructor for the Submission class.
     * Initializes lists for files submitted, missing files, and test results.
     */
    public Submission() {
        filesSubmitted = new ArrayList<>();
        missingFiles = new ArrayList<>();
        results = new ArrayList<>();
        cleanCodeScoreMap = new HashMap<String, Double>();
    }

    public boolean isSubmissionInitialized(){
        return (studentName != null && studentID != null && assignmentNo != null);
    }

    /**
     * Gets the student's name.
     *
     * @return The student's name.
     */
    public String getStudentName() {
        return studentName;
    }

    /**
     * Gets the student's ID.
     *
     * @return The student's ID.
     */
    public String getStudentID() {
        return studentID;
    }

    /**
     * Gets the assignment number.
     *
     * @return The assignment number.
     */
    public String getAssignmentNo() {
        return assignmentNo;
    }

    /**
     * Gets the total score for the submission.
     *
     * @return The total score.
     */
    public int getTotalScore() {
        return totalScore;
    }

    /**
     * Gets the feedback for the submission.
     *
     * @return The feedback.
     */
    public String getFeedback() {
        return feedback;
    }

    /**
     * Gets the score for the Passenger class.
     *
     * @return The Passenger class score.
     */
    public int getPassengerClassScore() {
        return passengerClassScore;
    }

    /**
     * Gets the score for the Flight class.
     *
     * @return The Flight class score.
     */
    public int getFlightClassScore() {
        return flightClassScore;
    }

    /**
     * Gets the score for the Luggage Slip class.
     *
     * @return The Luggage Slip class score.
     */
    public int getLuggageSlipClassScore() {
        return luggageSlipClassScore;
    }

    /**
     * Gets the score for the Luggage Manifest class.
     *
     * @return The Luggage Manifest class score.
     */
    public int getLuggageManifestClassScore() {
        return luggageManifestClassScore;
    }

    /**
     * Gets the compilation score.
     *
     * @return The compilation score.
     */
    public int getCompileScore() {
        return compileScore;
    }

    /**
     * Gets the number of tests passed.
     *
     * @return The number of tests passed.
     */
    public int getPassesAllJunit() {
        return passesAllJunit;
    }

    /**
     * Sets the number of tests passed.
     *
     * @param passesAllJunit The number of tests passed to set.
     */

    public void setPassesAllJunit(int passesAllJunit) {
        this.passesAllJunit = passesAllJunit;

    }

    /**
     * Sets the compilation score.
     *
     * @param compileScore The compilation score to set.
     */
    public void setCompileScore(int compileScore) {
        this.compileScore = compileScore;
    }

    /**
     * Gets the compilation status.
     *
     * @return True if the submission is compiled, false otherwise.
     */
    public boolean isCompiled() {
        return isCompiled;
    }

    /**
     * Gets the score for the Luggage System.
     *
     * @return The Luggage System score.
     */
    public int getLuggageSystemScore() {
        return luggageSystemScore;
    }

    /**
     * Sets the score for the Luggage System.
     *
     * @param luggageSystemScore The Luggage System score to set.
     */
    public void setLuggageSystemScore(int luggageSystemScore) {
        this.luggageSystemScore = luggageSystemScore;
    }

    /**
     * Sets the compilation status.
     *
     * @param compiled The compilation status to set.
     */
    public void setCompiled(boolean compiled) {
        isCompiled = compiled;
    }

    /**
     * Sets the score for the Passenger class.
     *
     * @param passengerClassScore The Passenger class score to set.
     */
    public void setPassengerClassScore(int passengerClassScore) {
        this.passengerClassScore = passengerClassScore;
    }

    /**
     * Sets the score for the Flight class.
     *
     * @param flightClassScore The Flight class score to set.
     */
    public void setFlightClassScore(int flightClassScore) {
        this.flightClassScore = flightClassScore;
    }

    /**
     * Sets the score for the Luggage Slip class.
     *
     * @param luggageSlipClassScore The Luggage Slip class score to set.
     */
    public void setLuggageSlipClassScore(int luggageSlipClassScore) {
        this.luggageSlipClassScore = luggageSlipClassScore;
    }

    /**
     * Sets the score for the Luggage Manifest class.
     *
     * @param luggageManifestClassScore The Luggage Manifest class score to set.
     */
    public void setLuggageManifestClassScore(int luggageManifestClassScore) {
        this.luggageManifestClassScore = luggageManifestClassScore;
    }

    /**
     * Gets the list of files submitted.
     *
     * @return The list of files submitted.
     */
    public ArrayList<String> getFilesSubmitted() {
        return filesSubmitted;
    }

    /**
     * Gets the list of missing files.
     *
     * @return The list of missing files.
     */
    public ArrayList<String> getMissingFiles() {
        return missingFiles;
    }

    /**
     * Gets the list of test results.
     *
     * @return The list of test results.
     */
    public ArrayList<TestResult> getResults() {
        return results;
    }

    /**
     * Gets the score for the Clean Code Checker.
     *
     * @return The Clean Code Checker score.
     */
    public HashMap<String, Double> getCleanCodeScoreMap() {
        return cleanCodeScoreMap;
    }

    /**
     * Sets the score for the Clean Code Checker.
     *
     * @param cleanCodeScoreMap The Clean Code Checker score to set.
     */
    public void setCleanCodeScoreMap(HashMap<String, Double> cleanCodeScoreMap) {
        this.cleanCodeScoreMap = cleanCodeScoreMap;
    }

    /**
     * Gets the total score for the Clean Code Checker.
     *
     * @return The total Clean Code Checker score.
     */
    public int getTotalCleanCodeScore() {
        return totalCleanCodeScore;
    }

    /**
     * Sets the total score for the Clean Code Checker.
     *
     * @param totalCleanCodeScore The total Clean Code Checker score to set.
     */
    public void setTotalCleanCodeScore(int totalCleanCodeScore) {
        this.totalCleanCodeScore = totalCleanCodeScore;
    }

    /**
     * Sets the total score for the submission.
     *
     * @param score The total score to set.
     * @return True if the total score is set successfully.
     */
    public boolean setTotalScore(int score) {
        this.totalScore = score;
        return true;
    }

    /**
     * Sets the feedback for the submission.
     *
     * @param feedback The feedback to set.
     */
    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }

    /**
     * Sets the student's name.
     *
     * @param studentName The student's name to set.
     */
    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    /**
     * Sets the student's ID.
     *
     * @param studentID The student's ID to set.
     */
    public void setStudentID(String studentID) {
        this.studentID = studentID;
    }

    /**
     * Sets the assignment number.
     *
     * @param assignmentNo The assignment number to set.
     */

    public void setAssignmentNo(String assignmentNo) {
        this.assignmentNo = assignmentNo;
    }

    /**
     * Adds a file to the list of files submitted.
     *
     * @param fileName The name of the file to add.
     */
    public void addFileSubmitted(String fileName) {
        filesSubmitted.add(fileName);
    }

    /**
     * Sets the list of files submitted.
     *
     * @param filesSubmitted The list of files submitted to set.
     */
    public void setFilesSubmitted(ArrayList<String> filesSubmitted) {
        this.filesSubmitted = filesSubmitted;
    }

    /**
     * Sets the list of missing files.
     *
     * @param missingFiles The list of missing files to set.
     */
    public void setMissingFiles(ArrayList<String> missingFiles) {
        this.missingFiles = missingFiles;
    }

    /**
     * Adds a missing file to the list of missing files.
     *
     * @param fileName The name of the missing file to add.
     */
    public void addMissingFile(String fileName) {
        missingFiles.add(fileName);
    }

    /**
     * Sets the list of test results.
     *
     * @param results The list of test results to set.
     */
    public void setResults(ArrayList<TestResult> results) {
        this.results = results;
    }

    /**
     * Sets the path of the submission.
     *
     * @param submissionPath The path of the submission to set.
     */
    public void setSubmissionPath(String submissionPath) {
        this.submissionPath = submissionPath;
    }

    /**
     * Gets the path of the submission.
     *
     * @return The path of the submission.
     */
    public String getSubmissionPath() {
        return submissionPath;
    }

    /**
     * Generates a string representation of the Submission object.
     *
     * @return A string representation of the Submission object.
     */
    @Override
    public String toString() {
        String str = "";
        str += "Student Name: " + studentName + "\n";
        str += "Student ID: " + studentID + "\n";
        str += "Total Score: " + totalScore + "\n";
        str += "Feedback: " + feedback + "\n";
        str += "Files Submitted: " + filesSubmitted + "\n";
        str += "Missing Files: " + missingFiles + "\n";

        for (TestResult result : results) {
            str += result.toString() + "\n";
        }

        return str;
    }
}
