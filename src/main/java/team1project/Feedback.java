package team1project;

import java.util.ArrayList;
import java.util.HashMap;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * The Feedback class represents an observer that provides feedback based on test results.
 * It implements the EvaluatorObserver interface to receive updates from an Evaluator.
 * The feedback is retrieved from a file and associated with specific test cases.
 * The feedback is then added to the test results of a submission. 
 */
public class Feedback implements EvaluatorObserver {

    /**
     * A map to store test names and corresponding feedback messages.
     */
    private HashMap<String, String> feedbackMap;

    /**
     * The main directory where utility files are located.
     */
    private static String mainDirectory = "src/main/java/team1project";

    /**
     * Constructs a Feedback object and populates the feedback map from a file.
     */
    public Feedback() {
        feedbackMap = new HashMap<>();
        populateFeedbackMap();
    }

    /**
     * Updates the feedback for each failed test result in the submission.
     *
     * @param evaluator The Evaluator triggering the update.
     * @return True if the update was successful, false otherwise.
     */
    @Override
    public boolean update(Evaluator evaluator) {
        Submission submission = evaluator.getSubmission();
        ArrayList<TestResult> results = submission.getResults();

        System.out.println("Running feedback...");

        for (TestResult result : results) {
            for (String key : feedbackMap.keySet()) {
                if (key.contains(result.getMethodName()) && result.getStatus().equals("FAILED")) {
                    String feedback = feedbackMap.get(key);
                    result.setFeedback(feedback);
                }
                if (result.getRawErrorMessage() != null && result.getStatus().equals("FAILED")) {
                    if (key.contains(result.getRawErrorMessage())) {
                        String feedback = feedbackMap.get(key);
                        result.setFeedback(result.getFeedback() + "\n " + feedback);
                    }
                }
            }
        }

        return true;
    }

    /**
     * Populates the feedback map by reading data from the testFeedback.txt file.
     */
    public void populateFeedbackMap() {
        try {
            File myObj = new File(mainDirectory + "/utilityFiles/testFeedback.txt");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                String result[] = data.split(",", 2); // split data into key and value

                String key = result[0];
                String value = result[1];

                feedbackMap.put(key, value); // add key-value pair to hashmap
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred while reading from testfeedback.txt.");
            e.printStackTrace();
        }
    }
}
