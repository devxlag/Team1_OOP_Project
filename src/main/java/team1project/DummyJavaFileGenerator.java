package team1project;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class DummyJavaFileGenerator {

  public static void main(String[] args) throws Exception {


    generateFiles("src/main/java/team1project/dummyClasses.txt", "src/test/java/team1project");
    

  }
    public static void generateFiles(String inputFile, String outputDir) throws IOException {

    BufferedReader reader = new BufferedReader(new FileReader(inputFile));
    
        String line;
        String className = "";
        StringBuilder classCode = new StringBuilder();

        while ((line = reader.readLine()) != null) {

            if(line.startsWith("Class:")) {
            // New class
            if(!className.isEmpty()) {
                // Write out previous class
                writeClassFile(className, classCode.toString(), outputDir);
                classCode.setLength(0); 
            }
            className = line.split(":")[1].trim();

            } else if (line.equals("End of Class")) {
            // End of current class
            writeClassFile(className, classCode.toString(), outputDir);
            className = "";
            classCode.setLength(0);

            } else {
            // Append class code line 
            classCode.append(line).append("\n");
            }

        }
        
        reader.close();

    }

    public static void writeClassFile(String className, String content, String outputDir) throws IOException {
        File file = new File(outputDir + "/" + className + ".java");
        FileWriter writer = new FileWriter(file); 
        writer.write(content);
        writer.close();
    }
  
}
