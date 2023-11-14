package team1project;

import java.io.File;

public interface SubmissionProcessor{
     public void registerObserver(SubmissionProcessorObserver o);
     public void removeObserver(SubmissionProcessorObserver o);
     public void  notifyObservers();
     void processSubmission(File submissionFile);
}
