package team1project;

public class Evaluator implements SubmissionProcessorObserver {

    public void update(ZipFile zipFile) {
        System.out.println("Evaluator: " + zipFile.getFileName() + " has been processed.");
    }
    
}
