package team1project;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.tools.*;

/**
 * The JavaFileCompiler class is responsible for compiling Java files and managing the compilation process.
 * It uses the Java Compiler API to compile specific Java files and cleans the target directory before compilation.
 */
public class JavaFileCompiler {

    private boolean isCompileJavaFilesCalled = false;


    public boolean isCompileJavaFilesCalled() {
        return isCompileJavaFilesCalled;
    }


    public void setCompileJavaFilesCalled(boolean compileJavaFilesCalled) {
        isCompileJavaFilesCalled = compileJavaFilesCalled;
    }

    /**
     * Compiles the specified Java files using the Java Compiler API.
     *
     * @return True if compilation is successful, false otherwise.
     * @throws Exception If an exception occurs during the compilation process.
     */
    public boolean compileJavaFiles(String mainDirectory, String outputDirectory, List<String> filesToCompile) throws Exception {
        // Compile Classes
        setCompileJavaFilesCalled(true);
        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
        StandardJavaFileManager fileManager = compiler.getStandardFileManager(null, null, null);
        cleanTargetDirectory(outputDirectory);

        List<File> fileList = new ArrayList<>();
            for (int i = 0; i < filesToCompile.size(); i++) {
                if (i == filesToCompile.size() - 1) {
                    fileList.add(new File(mainDirectory + "/" + filesToCompile.get(i)));
                } else {
                    fileList.add(new File(mainDirectory + "/" + filesToCompile.get(i)));
                }
            }

        Iterable<? extends JavaFileObject> compilationUnits = fileManager.getJavaFileObjectsFromFiles(fileList);


        Iterable<String> options = Arrays.asList("-d", outputDirectory);
        JavaCompiler.CompilationTask task = compiler.getTask(null, fileManager, null, options, null, compilationUnits);
        boolean compResult = task.call();

        if (compResult) {
            System.out.println("Compilation successful");
            try {
                fileManager.close();
            } catch (Exception e) {
                // Handle exception, if needed
                // e.printStackTrace();
            }
            return true;
        } else {
            System.err.println("Compilation failed");
            return false;
        }
    }

    /**
     * Cleans the target directory by deleting specific class files.
     *
     * @param targetDirectory The target directory to clean.
     */
    private static void cleanTargetDirectory(String targetDirectory) {
        File directory = new File(targetDirectory + "/team1project");
        ArrayList<String> filesToDelete = new ArrayList<>();
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
                        // System.out.println("Deleting file: " + file.getName());
                        file.delete();
                    }
                }
            }
        }
    }
}