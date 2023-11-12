package team1project;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Evaluator implements SubmissionProcessorObserver, EvaluatorSubject{

    private ZipFile compositeTreeRoot; // Root of the composite tree
    private DummyJavaFileGenerator dummyJavaFileGenerator;
    private EvaluatorObserver observer1;
    private EvaluatorObserver observer2;
    private EvaluatorObserver observer3;
    private Submission submission;

    public Evaluator() {       
        this.dummyJavaFileGenerator = new DummyJavaFileGenerator();
    }

    public void update(ZipFile zipFile) {
        this.compositeTreeRoot = zipFile;
        System.out.println("Evaluator: " + zipFile.getFileName() + " has been processed.");  
        try {
            createTempJavaFiles();
            System.out.println("Temp Java files created.");
            reset();
            System.out.println("Temp Java files reset.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void createTempJavaFiles() throws IOException {

        File tempDir = new File("src/test/java/team1project");
        if(!tempDir.exists()) {
            tempDir.mkdirs(); 
        }

        traverseAndWriteFiles(compositeTreeRoot, tempDir);

    }

    private void traverseAndWriteFiles(AbstractFile node, File directory) throws IOException {

        if(node instanceof ZipFile) {
          ZipFile zipFile = (ZipFile) node;
          
          for(AbstractFile child : zipFile.getChildren()) {
            traverseAndWriteFiles(child, directory); 
          }
      
        } else if(node instanceof JavaFile) {
          
          JavaFile file = (JavaFile) node;
          
          // write file contents to temp file
          File tempFile = new File(directory, file.getFileName());
            try (FileWriter writer = new FileWriter(tempFile)) {
            writer.write(file.getContents());
            }
          
        }      
      }

      public void reset(){
        try {
            dummyJavaFileGenerator.generateFiles("src/main/java/team1project/dummyClasses.txt", "src/test/java/team1project");
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        };
      }

    @Override
    public void registerObserver(EvaluatorObserver observer) {
        
        // if (observer instance of ScoreCalcualtor){
        //     observer1 = observer;
        // }
        // else if (observer instance of Feedback){
        //     observer2 = observer;
        // }
        // else if (observer instance of PDFDecorator){
        //     observer3 = observer;
        // }
    }

    @Override
    public void removeObserver(EvaluatorObserver observer) {
        // if (observer instance of ScoreCalcualtor){
        //     observer1 = null;
        // }
        // else if (observer instance of Feedback){
        //     observer2 = null;
        // }
        // else if (observer instance of PDFDecorator){
        //     observer3 = null;
        // }
    }

    @Override
    public void notifyObservers(EvaluatorObserver observer) {
        // if (observer instance of ScoreCalcualtor){
        //     observer1.update(submission);
        // }
        // else if (observer instance of Feedback){
        //     observer2.update(submission);
        // }
        // else if (observer instance of PDFDecorator){
        //     observer3.update(submission);
        // }
    }
    
}
