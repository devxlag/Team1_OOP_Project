package team1project;
import java.io.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.tools.*;
import org.junit.runner.JUnitCore; 
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

public class JavaFileCompiler {

    public static boolean compileJavaFiles() throws Exception{
        // Compile Classes
        String outputDirectory = "target/classes";
        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
        StandardJavaFileManager fileManager = compiler.getStandardFileManager(null, null, null);
        cleanTargetDirectory(outputDirectory);
        Iterable<? extends JavaFileObject> compilationUnits = fileManager.getJavaFileObjectsFromFiles(
                Arrays.asList(
                        new File("src/main/java/team1project/Flight.java"),
                        new File("src/main/java/team1project/Passenger.java"),
                        new File("src/main/java/team1project/LuggageManifest.java"),
                        new File("src/main/java/team1project/LuggageSlip.java"),
                        new File("src/main/java/team1project/FlightTest.java"),
                        new File("src/main/java/team1project/PassengerTest.java"),
                        new File("src/main/java/team1project/LuggageManifestTest.java"),
                        new File("src/main/java/team1project/LuggageSlipTest.java"),
                        new File("src/main/java/team1project/LuggageManagementSystem.java"),
                        new File("src/main/java/team1project/LuggageManagementSystemTest.java")
                )
        );

        Iterable<String> options = Arrays.asList("-d", outputDirectory);
        JavaCompiler.CompilationTask task = compiler.getTask(null, fileManager, null, options, null, compilationUnits);
        boolean compResult = task.call();

        if (compResult) {
            System.out.println("Compilation successful");
            try {
                fileManager.close();
                
            } catch (Exception e) {
            // e.printStackTrace();
            }
            return true;
        } else {
            System.err.println("Compilation failed");
            return false;
        }

    }

    private static void cleanTargetDirectory(String targetDirectory) {
        File directory = new File(targetDirectory+ "/team1project");
        ArrayList<String> filesToDelete = new ArrayList<String>();
        filesToDelete.add("Flight.class");
        filesToDelete.add("FlightTest.class");
        filesToDelete.add("LuggageManifest.class");
        filesToDelete.add("LuggageSlip.class");
        filesToDelete.add("Passenger.class");
        filesToDelete.add("PassengerTest.class");
        filesToDelete.add("LuggageManifestTest.class");
        filesToDelete.add("LuggageSlipTest.class");
        filesToDelete.add("LuggageManagementSystem.class");
        filesToDelete.add("LuggageManagementSystemTest.class");

        if (directory.exists()) {
            File[] files = directory.listFiles();
            if (files != null) {
                for (File file : files) {                                       
                    if (!file.isDirectory() && file.getName().endsWith(".class") && filesToDelete.contains(file.getName())) {
                        System.out.println("Deleting file: " + file.getName());
                        if(file.getName() == "Flight.class"){
                            printFileContents(file);
                        }
                       
                        file.delete();
                     }
                }
            }
        }
    }

    private static void printFileContents(File file) {
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            System.out.println("Contents of " + file.getName() + ":");
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}