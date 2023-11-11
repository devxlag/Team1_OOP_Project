package team1project;

public class ScoreCalculator implements Subscriber {


    public void update(double score) {

        String grade;
        if (score < 50) {
            grade = "Unsatisfactory";
        }

        else if (score < 70) {
            grade = "Fair";
        }

        else if (score < 90) {
            grade = "Good";
        }

        else {
            grade = "Excellent";
        }

        System.out.println("Score: " + score + ", Grade: " + grade);
    }

}