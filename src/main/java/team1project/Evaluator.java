package team1project;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import javax.swing.JOptionPane;

import java.util.regex.Matcher;
import java.util.HashMap;


public class Evaluator implements EvaluatorSubject{

    private ZipFile compositeTreeRoot; // Root of the composite tree
    private File zipFile;
    private DummyJavaFileGenerator dummyJavaFileGenerator;   
    private EvaluatorObserver zipObserver;
    private EvaluatorObserver scoreObserver;
    private EvaluatorObserver feedbackObserver;
    private EvaluatorObserver pdfObserver;
    private Submission submission;
    private static String mainDirectory = "src/main/java/team1project";
    private ArrayList<String> requiredFiles; 
    private JavaFileCompiler javaFileCompiler;
    private TestRunner testRunner;  

    public Evaluator() {       
        requiredFiles = new ArrayList<>();
        requiredFiles.add("Passenger.java");
        requiredFiles.add("Flight.java");
        requiredFiles.add("LuggageSlip.java");
        requiredFiles.add("LuggageManifest.java");
        requiredFiles.add("LuggageManagmentSystem.java"); 

        javaFileCompiler = new JavaFileCompiler();
        testRunner = new TestRunner();
        dummyJavaFileGenerator = new DummyJavaFileGenerator();

    }

    public void registerObservers(){
        registerObserver(new ZipSubmissionProcessor(requiredFiles));
        registerObserver(new ScoreCalculator(requiredFiles)); 
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

    public void setZipFile(File zipFile) {
        this.zipFile = zipFile;
        notifyObservers(zipObserver);
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

    public JavaFileCompiler getJavaFileCompiler(){
        return javaFileCompiler;
    }

    public TestRunner getTestRunner(){
        return testRunner;
    }

    public DummyJavaFileGenerator getDummyJavaFileGenerator(){
        return dummyJavaFileGenerator;
    }

    public void setDummyJavaFileGenerator(DummyJavaFileGenerator dummyJavaFileGenerator){
        this.dummyJavaFileGenerator = dummyJavaFileGenerator;
    }

    public void setJavaFileCompiler(JavaFileCompiler javaFileCompiler){
        this.javaFileCompiler = javaFileCompiler;
    }

    public void setTestRunner(TestRunner testRunner){
        this.testRunner = testRunner;
    }


    
    public void processSubmissionFile(File zipFile) throws IOException { 
        
       //reset();
        if(zipFile != null){
            setZipFile(zipFile);
            if(compositeTreeRoot!=null){
                if(evaluate()){
                    System.out.println("\nSubmissions evaluated.");
                    notifyClient("Submissions Evaluated. Check your zip file directory for the results.", "Success");
                }else{
                    System.out.println("\nSubmissions not evaluated.");
                    notifyClient("Submissions not evaluated.", "Error");                
                }
            }
            else{
                System.out.println("\nZip File not processed.");
            }
        }else{
            System.out.println("\nNo zip file found.");
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
        boolean compelted = false;

        for(AbstractFile node : children) {
            if(node instanceof ZipFile) {
                ZipFile zipFile = (ZipFile) node;

               
                if(initializeSubmission(zipFile.getPath(), zipFile.getFileName(), zipFile.getSubmittedFileNames())){
                    System.out.println("\nSubmission initialized for Student ID: " + getSubmission().getStudentID());
                }
                else{
                    System.out.println("\nSubmission not initialized for Student ID: " + getSubmission().getStudentID());
                }

                checkCleanCode(zipFile.getSubmittedFileNames(), zipFile.getChildren());

                boolean written = false;
               
                System.out.print("\nWriting Java files to temp directory: for Student ID: " + getSubmission().getStudentID());
                
                cleanTargetDirectory(mainDirectory + "/target/classes");
                //reset();

                for(AbstractFile child : zipFile.getChildren()) { 
                    //child.display();

                    written = writeJavaFile(child, directory); 
                }

                if(written){
                    runTests();
                    
                    compelted = true;
                }
                else{
                    System.out.println("\nJava files not written to temp directory.");
                }                

            }                                
        }
        if(compelted){
            reset();
            return true;
        }
            
        return false;
                                     
    }     
    
    private boolean initializeSubmission(String directory, String fileName, ArrayList<String> submittedFileNames){
        setSubmission();
        getSubmission().setSubmissionPath(directory);
        getSubmission().setFilesSubmitted(submittedFileNames);

        String[] extractedInfo = extractInfoFromFileName(fileName);
        if (extractedInfo.length == 5) {
            getSubmission().setStudentName(extractedInfo[0] + " " + extractedInfo[1]);
            getSubmission().setStudentID(extractedInfo[2]);
            getSubmission().setAssignmentNo(extractedInfo[4]);
           
        } else {
            System.out.println("Invalid file name format");
            getSubmission().setStudentName("Invalid file name format");
            getSubmission().setStudentID("Invalid file name format");
            getSubmission().setAssignmentNo("Invalid file name format");
        } 
        
        if(getSubmission().isSubmissionInitialized())
            return true;
        return false;
    }
       

    private static void cleanTargetDirectory(String targetDirectory) {
        System.out.println("Cleaning target directory: " + targetDirectory);
        File directory = new File(targetDirectory);
        ArrayList<String> filesToDelete = new ArrayList<String>();
        filesToDelete.add("Flight.class");
        filesToDelete.add("Passenger.class");        
        filesToDelete.add("LuggageManifest.class");
        filesToDelete.add("LuggageSlip.class");
        filesToDelete.add("LuggageManagementSystem.class");
       

        if (directory.exists()) {
            File[] files = directory.listFiles();
            if (files != null) {
                for (File file : files) {   
                    if (!file.isDirectory() && file.getName().endsWith(".java") && filesToDelete.contains(file.getName())) {
                        //System.out.println("Deleting file: " + file.getName());                                    
                        file.delete();
                     }                                                       
                }
            }
        }
    }

    
    private void runTests()  {
        Class<?>[] testClasses = new Class<?>[4];
        testClasses[0] = FlightTest.class;
        testClasses[1] = PassengerTest.class;
        testClasses[2] = LuggageManifestTest.class;
        testClasses[3] = LuggageSlipTest.class;
        //testClasses[4] = LuggageManagementSystemTest.class;


        try {
            String outputDirectory = "target/classes";
            if (getJavaFileCompiler().compileJavaFiles(mainDirectory,outputDirectory, getFilesToCompile())){
                getSubmission().setCompiled(true);
                getTestRunner().runTests(submission, testClasses);
                    if(submission.getResults() != null){
                        
                        System.out.println("Test results found.");                       
                    }
                    else{
                        System.out.println("No test results found.");
                    } 
                           

            }
             else{
                getSubmission().setCompiled(false);                
                System.out.println("Submission not compiled.");
                
             }
             notifyObservers(scoreObserver); 
             notifyObservers(feedbackObserver);
             notifyObservers(pdfObserver);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            //e.printStackTrace();
            System.out.println("Error running tests.");
        }
        finally{
            cleanTargetDirectory(mainDirectory + "/target/classes");
        }        
    }  
    
    private boolean writeJavaFile(AbstractFile node, File directory) throws IOException{
        if(node instanceof JavaFile) {
            
            JavaFile file = (JavaFile) node;
            // write file contents to temp file
            File tempFile = new File(directory, file.getFileName());
            try (FileWriter writer = new FileWriter(tempFile)) {
            writer.write(file.getContents());
            }
             return true;
        }
        return false;
         
    }

    private void checkCleanCode(ArrayList<String> submittedFileNames, List<AbstractFile> children){
        
        HashMap<String, Double> cleanCodeScoreMap = getSubmission().getCleanCodeScoreMap();
        for(AbstractFile child : children) { 
            if(child instanceof JavaFile) {
                JavaFile file = (JavaFile) child;
                if(submittedFileNames.contains(file.getFileName())){
                    double score = CleanCodeChecker.checkCode(file.getContents());                    
                    cleanCodeScoreMap.put(file.getFileName(), score);
                    getSubmission().setCleanCodeScoreMap(cleanCodeScoreMap);
                }
            }
        }
    }


    public List<String> getFilesToCompile() {
        
        List<String> filesToCompile = new ArrayList<>();
        filesToCompile.add("Flight.java");
        filesToCompile.add("Passenger.java");
        filesToCompile.add("LuggageManifest.java");
        filesToCompile.add("LuggageSlip.java");
        filesToCompile.add("LuggageManagementSystem.java");
        filesToCompile.add("FlightTest.java");
        filesToCompile.add("PassengerTest.java");
        filesToCompile.add("LuggageManifestTest.java");
        filesToCompile.add("LuggageSlipTest.java");
        filesToCompile.add("LuggageManagementSystemTest.java");        
        return filesToCompile;
    }
            

    @Override
    public void notifyObservers(EvaluatorObserver observer) {

        if(observer instanceof ZipSubmissionProcessor){
            if(zipObserver.update(this)){
                System.out.println("ZipSubmissionProcesser: Zip File Processed" );
            }
            else{
                notifyClient("The uploaded zip file is empty or doesn't contain any zip files.","Error" );
                
            }
        }

        if (observer instanceof ScoreCalculator){
            if(scoreObserver.update(this)){
                System.out.println("ScoreCalculator updated Submission: Scores Appended" );
            
                // if(feedbackObserver.update(this)){
                //     System.out.println("FeedBack updated Submission: Feedback Appended");
                    
                //     if(pdfObserver.update(this)){
                //         System.out.println("PDFDecorator updated Submission: PDF Created");
                //     }
                //  }
                //  else{
                //     pdfObserver.update(this);
                //  }
            }
        }

        if(observer instanceof Feedback){
            if(feedbackObserver.update(this)){
                System.out.println("FeedBack updated Submission: Feedback Appended");
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

    private void reset(){
        try {
            getDummyJavaFileGenerator().generateFiles(mainDirectory + "/utilityFiles/dummyClasses.txt", mainDirectory);
            System.out.println("\nDummy Java files written to temp directory.");
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
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


    private static String[] extractInfoFromFileName(String fileName) {
        // Define the pattern for extracting First Name, Last Name, ID, Assignment Letter, and Assignment Number
        Pattern pattern = Pattern.compile("([^_]+)_([^_]+)_(\\d+)_(A)(\\d+)\\.zip");

        // Create a matcher with the input file name
        Matcher matcher = pattern.matcher(fileName);

        // Check if the pattern matches
        if (matcher.matches()) {
            // Group 1 contains the First Name
            // Group 2 contains the Last Name
            // Group 3 contains the ID
            // Group 4 contains the Assignment Letter (e.g., 'A')
            // Group 5 contains the Assignment Number
            return new String[]{
                    matcher.group(1),
                    matcher.group(2),
                    matcher.group(3),
                    matcher.group(4),
                    matcher.group(5)
            };
        } else {
            // Return an empty array or handle the case where no match is found
            return new String[]{};
        }
    }   

    private static void  notifyClient(String infoMessage, String titleBar)
    {
        JOptionPane.showMessageDialog(null, infoMessage, "InfoBox: " + titleBar, JOptionPane.INFORMATION_MESSAGE);
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
