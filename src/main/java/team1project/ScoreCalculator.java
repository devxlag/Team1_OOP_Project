package team1project;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

/**
 * The ScoreCalculator class represents an observer that calculates scores for a submission based on test results.
 * It implements the EvaluatorObserver interface to receive updates from an Evaluator.
 * The class determines scores for compilation, individual test cases, and clean code.
 */
public class ScoreCalculator implements EvaluatorObserver {
   

    /**
     * A map to store the score scheme for individual test cases.
     */
    private HashMap<String, Integer> scoreScheme;

    /**
     * The main directory where utility files are located.
     */
    private static String mainDirectory = "src/main/java/team1project";

    /**
     * A list of required files for the submission.
     */
    private static ArrayList<String> requiredFiles;

    /**
     * Constructs a ScoreCalculator object with a list of required files.
     *
     * @param requiredFiles The list of required files for the submission.
     */
    public ScoreCalculator(ArrayList<String> requiredFiles) {
        scoreScheme = new HashMap<>();
        populateScoreScheme();
        this.requiredFiles = requiredFiles;
    }

    /**
     * Updates the scores for a submission based on test results.
     *
     * @param evaluator The Evaluator triggering the update.
     * @return True if the update was successful, false otherwise.
     */
    @Override
    public boolean update(Evaluator evaluator) {
        Submission submission = evaluator.getSubmission();
        ArrayList<TestResult> testResults = submission.getResults();
        boolean done1 = false, done2 = false;

        // read in test data from file to populate hashmap
        double junitScore = 0;
        for (TestResult testResult : testResults) {
            if (scoreScheme.containsKey(testResult.getMethodName())) {
                if (testResult.getStatus().equals("PASSED")){
                    testResult.setScore(scoreScheme.get(testResult.getMethodName()));
                    junitScore += 0.52; //Max score for each passed test is 0.52
                }
                else
                    testResult.setScore(0);
            }
            done1 = true;
        }        
        
        submission.setPassesAllJunit((int) junitScore);

        for (TestResult t : testResults) {
            if (t.getClassName().contains("FlightTest")) {
                submission.setFlightClassScore(submission.getFlightClassScore() + t.getScore());
            } else if (t.getClassName().contains("PassengerTest")) {
                submission.setPassengerClassScore(submission.getPassengerClassScore() + t.getScore());
            } else if (t.getClassName().contains("LuggageManifestTest")) {
                submission.setLuggageManifestClassScore(submission.getLuggageManifestClassScore() + t.getScore());
            } else if (t.getClassName().contains("LuggageSlipTest")) {
                submission.setLuggageSlipClassScore(submission.getLuggageSlipClassScore() + t.getScore());
            }

            done2 = true;
        }

        if (calculateCleanCodeScore(submission)) {
            System.out.println("Clean code score calculated successfully.");
            done2 = true;
        } else {
            System.out.println("Clean code score calculation failed.");
            done2 = false;
        }

        determineCompileScore(submission);

        int totalScore = 0;
        totalScore += submission.getLuggageSystemScore();
        totalScore += submission.getPassesAllJunit();
        totalScore += submission.getCompileScore();
        totalScore += submission.getPassengerClassScore();
        totalScore += submission.getFlightClassScore();
        totalScore += submission.getLuggageManifestClassScore();
        totalScore += submission.getLuggageSlipClassScore();
        totalScore += submission.getTotalCleanCodeScore();

        if (submission.setTotalScore(totalScore))
            return true;

        return false;
    }

    /**
     * Determines the compilation score for the submission.
     *
     * @param submission The submission to evaluate.
     */
    private void determineCompileScore(Submission submission) {
        if (containsAll(submission.getFilesSubmitted(), requiredFiles.toArray(new String[0]))) {
            System.out.println("All required files found.");
            setCompiledScore(submission, 5);
            if(submission.isCompiled())
                submission.setLuggageSystemScore(10);

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

    /**
     * Checks if a list contains all specified elements.
     *
     * @param list     The list to check.
     * @param elements The elements to check for.
     * @return True if the list contains all elements, false otherwise.
     */
    private boolean containsAll(List<String> list, String... elements) {
        return list.containsAll(Arrays.asList(elements));
    }

    /**
     * Checks if a list contains any of the specified elements.
     *
     * @param list     The list to check.
     * @param elements The elements to check for.
     * @return True if the list contains any of the elements, false otherwise.
     */
    private boolean containsAny(List<String> list, String... elements) {
        for (String element : elements) {
            if (list.contains(element)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Sets the compilation score for the submission based on specific conditions.
     *
     * @param submission The submission to evaluate.
     * @param score      The score to set.
     */
    private void setCompiledScore(Submission submission, int score) {
        if (submission.isCompiled()) {
            System.out.println("All required files found.");           
            submission.setCompileScore(score);
        } else {
            System.out.println("All required files found but did not compile.");
            submission.setCompileScore(0);
        }
    }

    /**
     * Calculates the clean code score for the submission.
     *
     * @param submission The submission to evaluate.
     * @return True if the clean code score is calculated successfully, false otherwise.
     */
    private boolean calculateCleanCodeScore(Submission submission) {
        int sc = 0;
        for (Double score : submission.getCleanCodeScoreMap().values()) {
            sc += score;
        }
        if (submission.getCleanCodeScoreMap().size() == 0) {
            return false;
        }
        int cleanCodeScoreAvg = sc / submission.getCleanCodeScoreMap().size();

        submission.setTotalCleanCodeScore(cleanCodeScoreAvg);
        return submission.getTotalCleanCodeScore() > 0;
    }

    /**
     * Populates the score scheme map by reading data from the testscores.txt file.
     */
    private void populateScoreScheme() {

        try {
            File myObj = new File(mainDirectory + "/utilityFiles/testscores.txt");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                String result[] = data.split(" "); // split data into key and value

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
