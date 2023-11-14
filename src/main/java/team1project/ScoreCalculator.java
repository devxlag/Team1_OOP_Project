package team1project;

import java.util.HashMap;

public class ScoreCalculator implements EvaluatorObserver {

    private static int n = 0;
    private HashMap <String, Integer> scoreScheme = new HashMap<String, Integer>();

    @Override
    public boolean update(Submission submission) {
       
        scoreScheme.put("testFlightConstructor", 1);
        scoreScheme.put("testPassengerConstructor", 2);
        boolean done = false;
        for(TestResult testResult : submission.getResults()){

            if (scoreScheme.containsKey(testResult.getMethodName())){
                if (testResult.getStatus() == "PASSED")
                    testResult.setScore(scoreScheme.get(testResult.getMethodName()));
                else
                    testResult.setScore(0);  
            }
            done = true;
        }

        int score = 0;
        for(TestResult testResult : submission.getResults()){
             score += testResult.getScore();
             
        }
        submission.setScore(score);
                       
        return done;
        
    }

    
    
}
