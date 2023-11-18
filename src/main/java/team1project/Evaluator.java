package team1project;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import java.util.regex.Matcher;


public class Evaluator implements EvaluatorSubject{

    private ZipFile compositeTreeRoot; // Root of the composite tree
    private File zipFile;
    private DummyJavaFileGenerator dummyJavaFileGenerator = new DummyJavaFileGenerator();   
    private EvaluatorObserver zipObserver;
    private EvaluatorObserver scoreObserver;
    private EvaluatorObserver feedbackObserver;
    private EvaluatorObserver pdfObserver;
    private Submission submission;
    private static String mainDirectory = "src/main/java/team1project";
    private ArrayList<String> requiredFiles;

    public Evaluator() {       
        requiredFiles = new ArrayList<>();
        requiredFiles.add("Passenger.java");
        requiredFiles.add("Flight.java");
        requiredFiles.add("LuggageSlip.java");
        requiredFiles.add("LuggageManifest.java");
        requiredFiles.add("LuggageManagmentSystem.java");  
    }

    public void registerObservers(){
        registerObserver(new ZipSubmissionProcessor(requiredFiles));
        registerObserver(new ScoreCalculator()); 
        registerObserver(new Feedback());            
        registerObserver(new FeedbackFormatDecorator());
    }


    public void setCompositeTreeRoot(ZipFile compositeTreeRoot) {
        this.compositeTreeRoot = compositeTreeRoot;
    }

    public AbstractFile getCompositeTreeRoot() {
        return compositeTreeRoot;
    }

    public File getZipFile() {
        return zipFile;
    }

    private void setSubmission(){
        this.submission = null;
        this.submission = new Submission();
    }
    public void setSubmission(Submission submission)
   { 
    this.submission=submission;
   }

    public Submission getSubmission(){
        return submission;
    }

    
    public void processSubmissionFile(File zipFile) throws IOException { 
        
        //reset();
        //test with mock data 

        if(zipFile != null){
            this.zipFile = zipFile;
            notifyObservers(zipObserver);
            if(compositeTreeRoot!=null){
                evaluate();
            }
            else{
                System.out.println("Zip File not processed.");
            }
        }else{
            System.out.println("No zip file found.");
        }      
    }

    public boolean evaluate() throws IOException {

        File tempDir = new File(mainDirectory);
        if(!tempDir.exists()) {
            tempDir.mkdirs(); 
        }

        if(compositeTreeRoot !=null){         
            if (traverseFiles(compositeTreeRoot.getChildren(), tempDir))
                return true;
        }
        return false;
    }

    private boolean traverseFiles(List<AbstractFile> children, File directory) throws IOException {

        String studentID = "";
        boolean compelted = false;

        for(AbstractFile node : children) {
            if(node instanceof ZipFile) {
                ZipFile zipFile = (ZipFile) node;
                setSubmission();
                getSubmission().setSubmissionPath(zipFile.getPath());
                studentID = extractIdFromFileName(zipFile.getFileName());
                boolean written = false;
               
                System.out.print("\nWriting Java files to temp directory: for Student ID: " + studentID);
                
                for(AbstractFile child : zipFile.getChildren()) {
                   // child.display();
                    written = writeJavaFile(child, directory); 
                }
                if(written){
                    System.out.println("\n Java files written to temp directory.");
                    
                    submission.setStudentID(studentID);
                    System.out.println("Running Test for: " + submission.getStudentID());
                    try {
                     runTest();
                        
                    } catch (Exception e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                    //reset();
                    System.out.println("Temp Java files reset.");
                    
                }
                compelted = true; 
            }                                  
        }         
        if(compelted)
            return true;
        return false;
    }

    private static void cleanTargetDirectory(String targetDirectory) {
        System.out.println("Cleaning target directory: " + targetDirectory);
        File directory = new File(targetDirectory);
        ArrayList<String> filesToDelete = new ArrayList<String>();
        filesToDelete.add("Flight.java");
        filesToDelete.add("Passenger.java");        
        filesToDelete.add("LuggageManifest.java");
        filesToDelete.add("LuggageSlip.java");
        filesToDelete.add("LuggageManagementSystem.java");
       

        if (directory.exists()) {
            File[] files = directory.listFiles();
            if (files != null) {
                for (File file : files) {   
                    if (!file.isDirectory() && file.getName().endsWith(".java") && filesToDelete.contains(file.getName())) {
                        System.out.println("Deleting file: " + file.getName());                                    
                        file.delete();
                     }                                                       
                }
            }
        }
    }

    public void runTest()  {

        ArrayList<TestResult> results = new ArrayList<>();
        try {
            if (JavaFileCompiler.compileJavaFiles()){

            
                if (TestRunner.runTests(submission)){
                    if(submission.getResults() != null){
                        
                        System.out.println("Test Results: " + submission.getResults());
                    notifyObservers(scoreObserver);
                    }
                    else{
                        System.out.println("No test results found.");
                    }        

                 }
             }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
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
            

    @Override
    public void notifyObservers(EvaluatorObserver observer) {

        if(observer instanceof ZipSubmissionProcessor){
            if(zipObserver.update(this)){
                System.out.println("ZipSubmissionProcesser: Zip File Processed" );
            }
        }

        if (observer instanceof ScoreCalculator){
            if(scoreObserver.update(this)){
                System.out.println("ScoreCalculator updated Submission: Scores Appended" );
            
                if(feedbackObserver.update(this)){
                    System.out.println("FeedBack updated Submission: Feedback Appended");
                    
                    if(pdfObserver.update(this)){
                        System.out.println("PDFDecorator updated Submission: PDF Created");
                    }
                 }
            }
        }
        
        if(observer instanceof FeedbackFormatDecorator){
            if(pdfObserver.update(this)){
                System.out.println("PDFDecorator updated Submission: PDF Created");
            }
        }
    }

    @Override
    public void registerObserver(EvaluatorObserver observer) {
        if (observer instanceof ZipSubmissionProcessor){
            zipObserver = observer;
        }
        else if (observer instanceof ScoreCalculator){
            scoreObserver = observer;
        }
        else if (observer instanceof Feedback){
            feedbackObserver = observer;
        }
       else if (observer instanceof PDFBaseDecorator){
            pdfObserver = observer;
        }
        
    }

    @Override
    public void removeObserver(EvaluatorObserver observer) {
        if (observer instanceof ZipSubmissionProcessor){
            zipObserver = null;
        }
        else if (observer instanceof ScoreCalculator){
            scoreObserver = null;
        }
        else if (observer instanceof Feedback){
            feedbackObserver = null;
        }
       else if (observer instanceof PDFBaseDecorator){
            pdfObserver = null;
        }
    }

    public void reset(){
        try {
            dummyJavaFileGenerator.generateFiles(mainDirectory + "/utilityFiles/dummyClasses.txt", mainDirectory);
            System.out.println("Dummy Java files written to temp directory.");
        } catch (IOException e) {
            // TODO Auto-generated catch block
            //e.printStackTrace();
        };
    }

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

    public void mockDataTest(){

        ArrayList<Submission> submissions = new ArrayList<Submission>();

        try {
            submissions = PopulateSubmission.populateAll();
        } catch (Exception e) {
        
            // TODO: handle exception
        }
    }
    
}
