package team1project;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class Evaluator implements SubmissionProcessorObserver, EvaluatorSubject{

    private ZipFile compositeTreeRoot; // Root of the composite tree
    private DummyJavaFileGenerator dummyJavaFileGenerator;    
    private EvaluatorObserver observer1;
    private EvaluatorObserver observer2;
    private EvaluatorObserver observer3;
    private Submission submission;

    public Evaluator() {       
        this.dummyJavaFileGenerator = new DummyJavaFileGenerator(); 
        registerObserver(new ScoreCalculator()); 
        registerObserver(new Feedback()); 
        PDFManager pdfManager = new PDFManager();
        registerObserver(new FeedbackFormatDecorator(pdfManager));    
    }

    public void update(ZipFile zipFile) {
        this.compositeTreeRoot = zipFile;
        System.out.println("Evaluator: " + zipFile.getFileName() + " has been processed.");  
        try {
            createTempJavaFiles();
            System.out.println("Temp Java files created.");
            //reset();
           
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void createTempJavaFiles() throws IOException {

        File tempDir = new File("src/main/java/team1project");
        if(!tempDir.exists()) {
            tempDir.mkdirs(); 
        }

        if(compositeTreeRoot !=null){
            traverseFiles(compositeTreeRoot.getChildren(), tempDir);
        }

       // traverseAndWriteFiles(compositeTreeRoot, tempDir);

    }

    private void traverseFiles(List<AbstractFile> children, File directory) throws IOException {

        String studentID = "";
        for(AbstractFile node : children) {
            if(node instanceof ZipFile) {
                ZipFile zipFile = (ZipFile) node;
                setSubmission();
                studentID = extractIdFromFileName(zipFile.getFileName());
                boolean written = false;
                
                for(AbstractFile child : zipFile.getChildren()) {
                    written = writeJavaFile(child, directory); 
                }
                if(written){
                    System.out.println("\nJava files written to temp directory.");
                    
                    submission.setStudentID(studentID);
                    System.out.println("Running Test for: " + submission.getStudentID());
                    try {
                        runTest(submission);
                        //reset();
                    } catch (Exception e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                    
                     System.out.println("Temp Java files reset.");
                }
            } 
        }
            
    }


    // private String checkFileName(AbstractFile node) {
    //     String[] filenames = "Flight.java, Passenger.java, TestFlight.java, TestPassenger.java".split(", ");
        
       

    //     if (node instanceof JavaFile) {
    //         JavaFile file = (JavaFile) node;
    //         if( file.getFileName() )


    //         return file.getFileName();
    //     }
    //     return "";
    // }

    private static String extractIdFromFileName(String fileName) {
        // Define the pattern for extracting the ID
        Pattern pattern = Pattern.compile("(\\d+)_.*");

        // Create a matcher with the input file name
        Matcher matcher = pattern.matcher(fileName);

        // Check if the pattern matches
        if (matcher.matches()) {
            // Group 1 contains the extracted ID
            return matcher.group(1);
        } else {
            // Return an empty string or handle the case where no match is found
            return "";
        }
    }

    public boolean writeJavaFile(AbstractFile node, File directory) throws IOException{
        if(node instanceof JavaFile) {
            
        JavaFile file = (JavaFile) node;
        
        // write file contents to temp file
        File tempFile = new File(directory, file.getFileName());
            try (FileWriter writer = new FileWriter(tempFile)) {
            writer.write(file.getContents());
            }
        
        }
            return true; 
    }

    private void setSubmission(){
        this.submission = null;
        this.submission = new Submission();
    }

    public void runTest(Submission submission) throws Exception {

        ArrayList<TestResult> results = new ArrayList<>();
        if (JavaFileCompiler.compileJavaFiles()){
            
            results = TestRunner.runTests(submission);
                     
            submission.setResults(results); 
            System.out.println("Test Results: " + submission.getResults());                
            notifyObservers(observer1);

        }        
    }
    public String getTempDirectoryPath() {
        return "src/main/java/team1project"; // Adjust this path accordingly
    }

    public void reset(){
        try {
            dummyJavaFileGenerator.generateFiles("src/main/java/team1project/dummyClasses.txt", "src/main/java/team1project");
        } catch (IOException e) {
            // TODO Auto-generated catch block
            //e.printStackTrace();
        };
    }

    @Override
    public void registerObserver(EvaluatorObserver observer) {
        
        if (observer instanceof ScoreCalculator){
            observer1 = observer;
        }
        else if (observer instanceof Feedback){
            observer2 = observer;
        }
       else if (observer instanceof PDFBaseDecorator){
            observer3 = observer;
        }
    }

    @Override
    public void removeObserver(EvaluatorObserver observer) {
        if (observer instanceof ScoreCalculator){
            observer1 = null;
        }
        else if (observer instanceof Feedback){
            observer2 = null;
        }
        else if (observer instanceof PDFBaseDecorator){
            observer3 = null;
        }
    }

    @Override
    public void notifyObservers(EvaluatorObserver observer) {
        if (observer instanceof ScoreCalculator){
            if(observer1.update(submission)){
                System.out.println("ScoreCalculator updated Submission: Scores Appended" );
            
                if(observer2.update(submission)){
                    System.out.println("FeedBack updated Submission: Feedback Appended");
                    if(observer3.update(submission)){
                        System.out.println("PDFDecorator updated Submission: PDF Created");
                    }
                 }
            }
        }       
    }
    
}
