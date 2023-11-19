package team1project;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class ScoreCalculator implements EvaluatorObserver {

    private static int n = 0;
    private HashMap <String, Integer> scoreScheme;
    ArrayList<String> classNames;
    private static String mainDirectory = "src/main/java/team1project";

    public ScoreCalculator() {
       scoreScheme = new HashMap<String, Integer>();
       populateScoreScheme();

    }

    @Override
    public boolean update(Evaluator evaluator) {

        Submission submission = evaluator.getSubmission();
        ArrayList<TestResult> testResults = submission.getResults();
        boolean done1 = false, done2 = false;

        // read in test data from file to populate hashmap   
        
        submission.setCompileScore(5);
        
       for(TestResult testResult : testResults){

           if (scoreScheme.containsKey(testResult.getMethodName())){
               if (testResult.getStatus() == "PASSED")
                   testResult.setScore(scoreScheme.get(testResult.getMethodName()));
               else
                   testResult.setScore(0);
           }
           done1 = true;
       }

                                
        for(TestResult t: testResults){
                if(t.getClassName().contains("FlightTest")){
                    submission.setFlightClassScore(submission.getFlightClassScore() + t.getScore());
                }
                else if(t.getClassName().contains("PassengerTest")){
                    submission.setPassengerClassScore(submission.getPassengerClassScore() + t.getScore());
                }
                else if(t.getClassName().contains("LuggageManifestTest")){
                    submission.setLuggageManifestClassScore(submission.getLuggageManifestClassScore() + t.getScore());
                }
                else if(t.getClassName().contains("LuggageSlipTest")){
                    submission.setLuggageSlipClassScore(submission.getLuggageSlipClassScore() + t.getScore());
                }

            done2 = true;            
        }

        int score = 0;
        if (done1 && done2) {
            for(TestResult testResult : submission.getResults()){
                score += testResult.getScore();             
            }           
        }
                       
        if(submission.setTotalScore(score))
            return true;
        
        return false;
                                      
    }


    public void populateScoreScheme(){

         try {
            File myObj = new File(mainDirectory + "/utilityFiles/testscores.txt");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()){
                String data = myReader.nextLine(); 
                String result[] = data.split(" "); //split data into key and value

                String key = result[0];
                int value = Integer.parseInt(result[1]);

                scoreScheme.put(key, value); // add key value pair to hashmap
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred while reading from testscores.txt.");
            e.printStackTrace();
        }
    }
        
}
