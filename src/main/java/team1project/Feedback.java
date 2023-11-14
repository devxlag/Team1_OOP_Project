package team1project;

import java.util.ArrayList;

public class Feedback implements EvaluatorObserver {

    @Override
    public boolean update(Submission submission) {
        ArrayList<TestResult> results = submission.getResults();
        boolean done = false;

        
        for (TestResult result : results) 
        {
                if (result.getStatus() == "FAILED")
                {
                    String feedback = result.getErrorMessage();
                    result.setFeedback(feedback);                                    
                }
                else
                {
                    result.setFeedback(result.getStatus());                                                         
                }
                done = true;
        }
        return done;
              
    }
    
}
