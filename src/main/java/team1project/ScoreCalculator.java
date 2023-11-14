package team1project;

public class ScoreCalculator implements EvaluatorObserver {

    @Override
    public void update(Submission submission) {
       
        System.out.println(submission.getResults());
    }

    
    
}
