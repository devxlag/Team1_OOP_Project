package team1project;

// import java.io.File;
// import java.util.ArrayList;
// import java.util.Scanner;

// public class PopulateSubmission {

//   public static Submission populate() throws Exception {
    
//     Submission submission = new Submission();
    
//     // Read data from file
//     File file = new File("src/main/java/team1project/submission_data.txt");
//     Scanner scanner = new Scanner(file);
    
//     // Populate Submission object
//     submission.setStudentName(scanner.nextLine());
//     submission.setStudentID(scanner.nextLine());
//     submission.setScore(scanner.nextInt());
//     scanner.nextLine(); // consume newline
//     submission.setFeedback(scanner.nextLine());
    
//     // Populate files submitted
//     int numFiles = scanner.nextInt();
//     scanner.nextLine(); // consume newline
//     for (int i = 0; i < numFiles; i++) {
//       submission.addFileSubmitted(scanner.nextLine()); 
//     }
    
//     // Populate missing files
//     int numMissing = scanner.nextInt();
//     scanner.nextLine(); // consume newline
//     for (int i = 0; i < numMissing; i++) {
//       submission.addMissingFile(scanner.nextLine());
//     }
    
//     // Populate test results
//     ArrayList<TestResult> results = new ArrayList<>();
//     int numResults = scanner.nextInt();
//     scanner.nextLine(); // consume newline
//     for (int i = 0; i < numResults; i++) {
//       TestResult result = new TestResult();
//       result.setTestName(scanner.nextLine());
//       result.setClassName(scanner.nextLine());
//       result.setMethodName(scanner.nextLine());
//       result.setStatus(scanner.nextLine());
//       result.setErrorMessage(scanner.nextLine());
//       result.setScore(scanner.nextLine());
//      // scanner.nextLine(); // consume newline
//       result.setFeedback(scanner.nextLine());
//       results.add(result);
//       //submission.getResults().add(result);
//     }
//     submission.setResults(results);
//     scanner.close();
//     return submission;
//     // Print populated objects
//     // System.out.println(submission);
//     // for (TestResult result : submission.getResults()) {
//     //   System.out.println(result);
//     // }
//   }

// }

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

public class PopulateSubmission {

    public static ArrayList<Submission> populateAll() throws Exception {
        ArrayList<Submission> submissions = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader("src/main/java/team1project/submission_data.txt"))) {
            while (true) {
                Submission submission = readSingleSubmission(reader);
                if (submission == null) {
                    break; // End of file
                }
                submissions.add(submission);

                // Skip the separator line
                reader.readLine();
            }
        }

        return submissions;
    }

    private static Submission readSingleSubmission(BufferedReader reader) throws Exception {
    Submission submission = new Submission();

    // Populate Submission object
    String line;
    if ((line = reader.readLine()) == null) {
        return null; // End of file
    }
    submission.setStudentName(line);

    submission.setStudentID(reader.readLine());
    submission.setTotalScore(Integer.parseInt(reader.readLine()));
    submission.setFeedback(reader.readLine());

    // Populate files submitted
    int numFiles = Integer.parseInt(reader.readLine());
    for (int i = 0; i < numFiles; i++) {
        submission.addFileSubmitted(reader.readLine());
    }

    // Populate missing files
    int numMissing = Integer.parseInt(reader.readLine());
    for (int i = 0; i < numMissing; i++) {
        submission.addMissingFile(reader.readLine());
    }

    // Populate test results
    ArrayList<TestResult> results = new ArrayList<>();
    int numResults = Integer.parseInt(reader.readLine());
    for (int i = 0; i < numResults; i++) {
        TestResult result = new TestResult();
        result.setTestName(reader.readLine());
        result.setClassName(reader.readLine());
        result.setMethodName(reader.readLine());
        result.setStatus(reader.readLine());
        result.setErrorMessage(reader.readLine());
        result.setScore(Integer.parseInt(reader.readLine())); // Assuming score is always present
        result.setFeedback(reader.readLine());
        results.add(result);
    }
    submission.setResults(results);

    return submission;
}
}


