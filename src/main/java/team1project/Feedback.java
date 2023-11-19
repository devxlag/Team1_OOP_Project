package team1project;

import java.util.ArrayList;
import java.util.HashMap;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Feedback implements EvaluatorObserver {

    private HashMap<String, String> feedbackMap = new HashMap<String, String>(); // store test name and test feedback
    private static String mainDirectory = "src/main/java/team1project";

    @Override
    public boolean update(Evaluator evaluator) {
        
        Submission submission = evaluator.getSubmission();
        ArrayList<TestResult> results = submission.getResults();
        boolean done = false;

        // read in test data from file to populate hashmap
           
        for (TestResult result : results) 
        {
                for (String key : feedbackMap.keySet())
                {
                    if (result.getMethodName() == key && result.getStatus() == "FAILED")
                    {
                        String feedback = feedbackMap.get(key);
                        result.setFeedback(feedbackMap.get(key));
                        done = true;                
                    }
                }
        }
        return done;
    }


    public void populateFeedBackMap(){
            try {
            File myObj = new File(mainDirectory + "/utilityFiles/testscores.txt");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine())
            {
                String data = myReader.nextLine();
                String result[] = data.split(" ", 2); //split data into key and value

                String key = result[0];
                String value = result[1];

                feedbackMap.put(key, value); // add key value pair to hashmap
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred while reading from testfeedback.txt.");
            e.printStackTrace();
        }
    }
}
