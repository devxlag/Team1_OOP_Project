package team1project;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class ScoreCalculator implements EvaluatorObserver {

    private static int n = 0;
    private HashMap <String, Integer> scoreScheme;
    ArrayList<String> classNames;
    private static String mainDirectory = "src/main/java/team1project";
    private static ArrayList<String> requiredFiles;

    public ScoreCalculator(ArrayList<String> requiredFiles) {
       scoreScheme = new HashMap<String, Integer>();
       populateScoreScheme();
        this.requiredFiles = requiredFiles;

    }

    @Override
    public boolean update(Evaluator evaluator) {

        Submission submission = evaluator.getSubmission();
        ArrayList<TestResult> testResults = submission.getResults();
        boolean done1 = false, done2 = false;

        // read in test data from file to populate hashmap   
                    
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

        if(calculateCleanCodeScore(submission)){
            System.out.println("Clean code score calculated successfully.");
            done2 = true;
        }
        else{
            System.out.println("Clean code score calculation failed.");
            done2 = false;
        }

        determineCompileScore(submission);

        int totalScore = 0;       

        totalScore += submission.getCompileScore();
        totalScore += submission.getPassengerClassScore();
        totalScore += submission.getFlightClassScore();
        totalScore += submission.getLuggageManifestClassScore();
        totalScore += submission.getLuggageSlipClassScore();
        totalScore += submission.getTotalCleanCodeScore();   
                       
        if(submission.setTotalScore(totalScore))
            return true;
        
        return false;
                                      
    }

    private void determineCompileScore(Submission submission){
        if (containsAll(submission.getFilesSubmitted(), requiredFiles.toArray(new String[0]))) {
            System.out.println("All required files found.");            
            setCompiledScore(submission, 5);
            
        } else {

                System.out.println("Missing required files:");
                for (String file : requiredFiles) {
                    if (!submission.getFilesSubmitted().contains(file)) {
                        submission.addMissingFile(file);
                        System.out.println(file);
                    }
                }
            // Check for specific conditions
            if (containsAll(submission.getFilesSubmitted(), "Passenger.java", "Flight.java", "LuggageSlip.java", "LuggageManifest.java")) {
                ArrayList<String> filesToRun = new ArrayList<>(Arrays.asList("Passenger.java", "Flight.java", "LuggageSlip.java", "LuggageManifest.java"));
                setCompiledScore(submission, 4);
                
            } else if (submission.getFilesSubmitted().contains("Passenger.java")
                    && containsAny(submission.getFilesSubmitted(), "Flight.java", "LuggageSlip.java", "LuggageManifest.java", "LuggageManagmentSystem.java")) {
                    setCompiledScore(submission, 1);
                
            } 
        }       
    }

    private boolean containsAll(List<String> list, String... elements) {
        return list.containsAll(Arrays.asList(elements));
    }

    private boolean containsAny(List<String> list, String... elements) {
        for (String element : elements) {
            if (list.contains(element)) {
                return true;
            }
        }
        return false;
    }

    private void setCompiledScore(Submission submission, int score) {
        if(submission.isCompiled()){
                System.out.println("All required files found.");
                submission.setCompileScore(score);
            }
            else{
                System.out.println("All required files found but did not compile.");
                submission.setCompileScore(0);
            }
    }

    private boolean calculateCleanCodeScore(Submission submission){
        int sc = 0;
        for(Double score : submission.getCleanCodeScoreMap().values()){
            sc += score;                       
        }
        int cleanCodeScoreAvg = sc / submission.getCleanCodeScoreMap().size();
        
        submission.setTotalCleanCodeScore(cleanCodeScoreAvg);
        if(submission.getTotalCleanCodeScore() > 0){

            return true;

        }
        else{
            return false;
        }
    }


    private void populateScoreScheme(){

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
