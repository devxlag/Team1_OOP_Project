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

// import junit.framework.TestResult;

public class JavaFileCompiler {

  // private JavaFileCompiler() {}

  // public static void compile(String srcDir, String className) throws IOException {
    
  //   JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
  //   try (StandardJavaFileManager fileManager = compiler.getStandardFileManager(null, null, null)) {

  //     Iterable<? extends JavaFileObject> compilationUnits = fileManager.getJavaFileObjectsFromFiles(Arrays.asList(new File(srcDir + className + ".java")));

  //     compiler.getTask(null, fileManager, null, null, null, compilationUnits).call();
      
  //     System.out.println(className + " compiled successfully");
      
  //   } catch (Exception e) {
  //     System.out.println("Error compiling " + className);
  //   }
  // }

  // public static void main(String[] args) throws Exception {

  //   // Compile Flight class
  //   compile("src/main/java/team1project/", "Flight");
    
  //   // Compile Passenger class 
  //   //compile("src/main/java/team1project/", "Passenger");

  //   // Compile FlightTest test class
  //   JavaFileCompiler.compile("src/test/java/", "FlightTest");

  //   // Run the FlightTest 
  //   ClassLoader classLoader = JavaFileCompiler.class.getClassLoader();
  //   Class flightTestClass = classLoader.loadClass("FlightTest");

  //   Result result = JUnitCore.runClasses(flightTestClass);

  //   // Check if all tests passed
  //   if (!result.wasSuccessful()) {
  //   // Failed tests found
  //     System.out.println("Tests failed:");

  //     for (org.junit.runner.notification.Failure failure : result.getFailures()) {
  //       System.out.println(failure.toString());
  //     }
  //   } else {
  //     // All tests passed
  //     System.out.println("All tests passed!");
  //   }

  //   // Delete extracted files
  //   File flightFile = new File("src/main/java/team1project/Flight.java"); 
  //   flightFile.delete();
    
  //   // File passengerFile = new File("src/main/java/team1project/Passenger.java");
  //   // passengerFile.delete();
    
  // }

  public static ArrayList<TestResult> compileAndRunTests() throws Exception{

   
  
    // Compile Classes
    JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
    StandardJavaFileManager fileManager = compiler.getStandardFileManager(null, null, null);

    Iterable<? extends JavaFileObject> compilationUnits = fileManager.getJavaFileObjectsFromFiles(
            Arrays.asList(
                    new File("src/test/java/team1project/Flight.java"),
                    new File("src/test/java/team1project/Passenger.java"),
                    new File("src/test/java/team1project/LuggageManifest.java"),
                    new File("src/test/java/team1project/LuggageSlip.java"),
                    new File("src/test/java/team1project/FlightTest.java")
            )
    );

    JavaCompiler.CompilationTask task = compiler.getTask(null, fileManager, null, null, null, compilationUnits);
    boolean compResult = task.call();

    if (compResult) {
        System.out.println("Compilation successful");
    } else {
        System.err.println("Compilation failed");
    }

    try {
        fileManager.close();
    } catch (Exception e) {
        e.printStackTrace();
    }

    // Run the FlightTest
    ArrayList<TestResult> results = TestRunner.runTests();

    return results;
  
    // ArrayList<TestResult> results = new ArrayList<>();

    // ClassLoader classLoader = JavaFileCompiler.class.getClassLoader();
    // Class flightTestClass = classLoader.loadClass("FlightTest");

    // //Result result = JUnitCore.runClasses(flightTestClass);
        
    // Result junitResult = JUnitCore.runClasses(flightTestClass);//FlightTest.class);
      
    //   for(Failure failure : junitResult.getFailures()) {
    //     TestResult result = new TestResult();
    //     result.setTestName(failure.getTestHeader());
    //     result.setResult(false);
    //     results.add(result);
    //   }

    //   if(results.isEmpty()) {
    //     // All tests passed
    //     TestResult result = new TestResult();
    //     result.setTestName("All Tests");
    //     result.setResult(true);
    //     results.add(result);
    //   }

    //   // Reset and delete extracted files

    //   return results;

    }
}