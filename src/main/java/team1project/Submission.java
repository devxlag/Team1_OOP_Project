package team1project;

import java.util.ArrayList;

public class Submission {

    private String studentName;
    private String studentID;
    private int passengerClassScore = 0;
    private int flightClassScore = 0;
    private int luggageSlipClassScore = 0;
    private int luggageManifestClassScore = 0;
    private int compileScore = 0;
    private int totalScore = 0;
    private String feedback;
    private ArrayList<String> filesSubmitted;
    private ArrayList<String> missingFiles;
    private ArrayList<TestResult> results;
    private String submissionPath;
    

    public Submission() {
        filesSubmitted = new ArrayList<>();
        missingFiles = new ArrayList<>();
        results = new ArrayList<>();
    }

    public String getStudentName() {
        return studentName;
    }
    

    public String getStudentID() {
        return studentID;
    }

    public int getTotalScore() {
        return totalScore;
    }

    public String getFeedback() {
        return feedback;
    }

    public int getPassengerClassScore() {
        return passengerClassScore;        
    }

    public int getFlightClassScore() {
        return flightClassScore;
    }

    public int getLuggageSlipClassScore() {
        return luggageSlipClassScore;
    }

    public int getLuggageManifestClassScore() {
        return luggageManifestClassScore;
    }

    public int getCompileScore() {
        return compileScore;
    }

    public void setCompileScore(int compileScore) {
        this.compileScore = compileScore;
    }

    public void setPassengerClassScore(int passengerClassScore) {
        this.passengerClassScore = passengerClassScore;
    }

    public void setFlightClassScore(int flightClassScore) {
        this.flightClassScore = flightClassScore;
    }

    public void setLuggageSlipClassScore(int luggageSlipClassScore) {
        this.luggageSlipClassScore = luggageSlipClassScore;
    }

    public void setLuggageManifestClassScore(int luggageManifestClassScore) {
        this.luggageManifestClassScore = luggageManifestClassScore;
    }

    public ArrayList<String> getFilesSubmitted() {
        return filesSubmitted;
    }

    public ArrayList<String> getMissingFiles() {
        return missingFiles;
    }

    public ArrayList<TestResult> getResults(){
        return results;
    }

    public boolean setTotalScore(int score) {
        this.totalScore = score;
        return true;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public void setStudentID(String studentID) {
        this.studentID = studentID;
    }

    public void addFileSubmitted(String fileName) {
        filesSubmitted.add(fileName);
    }

     public void addMissingFile(String fileName) {
        missingFiles.add(fileName);
    }
    
    public void setResults(ArrayList<TestResult> results){
        this.results = results;
    }

    public void setSubmissionPath(String submissionPath){
        this.submissionPath = submissionPath;
    }

    public String getSubmissionPath(){
        return submissionPath;
    }

    
    
    public String toString(){
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
        //str += "Test Results: " + results + "\n";
        return str;

    }
}
