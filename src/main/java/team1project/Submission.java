package team1project;

import java.util.ArrayList;

public class Submission {

    private String studentName;
    private String studentID;
    private int score;
    private String feedback;
    private ArrayList<String> filesSubmitted;
    private ArrayList<String> missingFiles;
    private ArrayList<String> results;
    

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

    public int getScore() {
        return score;
    }

    public String getFeedback() {
        return feedback;
    }

    public ArrayList<String> getFilesSubmitted() {
        return filesSubmitted;
    }

    public void setScore(int score) {
        this.score = score;
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
    
}
