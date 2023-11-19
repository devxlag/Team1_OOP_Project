package team1project;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * The DummyJavaFileGenerator class is responsible for generating Java files based on a given input file.
 * Each class in the input file is identified by the "Class:" marker, and the class content is extracted
 * until the "End of Class" marker is encountered. The generated Java files are written to the specified output directory.
 */
public class DummyJavaFileGenerator {

    /**
     * Generates Java files based on the input file and writes them to the specified output directory.
     *
     * @param inputFile  The path to the input file containing class information.
     * @param outputDir  The directory where the generated Java files will be written.
     * @throws IOException If an I/O error occurs during file reading or writing.
     */
    public void generateFiles(String inputFile, String outputDir) throws IOException {

        BufferedReader reader = new BufferedReader(new FileReader(inputFile));

        String line;
        String className = "";
        StringBuilder classCode = new StringBuilder();

        while ((line = reader.readLine()) != null) {

            if (line.startsWith("Class:")) {
                // New class
                if (!className.isEmpty()) {
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

    /**
     * Writes the content of a class to a Java file in the specified output directory.
     *
     * @param className  The name of the class.
     * @param content    The content of the class.
     * @param outputDir  The directory where the Java file will be written.
     * @throws IOException If an I/O error occurs during file writing.
     */
    private static void writeClassFile(String className, String content, String outputDir) throws IOException {
        File file = new File(outputDir + "/" + className + ".java");
        FileWriter writer = new FileWriter(file);
        writer.write(content);
        writer.close();
    }

}

