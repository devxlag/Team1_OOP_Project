package team1project;
import java.io.*;
import java.util.Arrays;
import javax.tools.*;
import org.junit.runner.JUnitCore; 
import org.junit.runner.Result;

public class JavaFileCompiler {

  private JavaFileCompiler() {}

  public static void compile(String srcDir, String className) throws IOException {
    
    JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
    try (StandardJavaFileManager fileManager = compiler.getStandardFileManager(null, null, null)) {

      Iterable<? extends JavaFileObject> compilationUnits = fileManager.getJavaFileObjectsFromFiles(Arrays.asList(new File(srcDir + className + ".java")));

      compiler.getTask(null, fileManager, null, null, null, compilationUnits).call();
      
      System.out.println(className + " compiled successfully");
      
    } catch (Exception e) {
      System.out.println("Error compiling " + className);
    }
  }

  public static void main(String[] args) throws Exception {

    // Compile Flight class
    compile("src/main/java/team1project/", "Flight");
    
    // Compile Passenger class 
    //compile("src/main/java/team1project/", "Passenger");

    // Compile FlightTest test class
    JavaFileCompiler.compile("src/test/java/", "FlightTest");

    // Run the FlightTest 
    ClassLoader classLoader = JavaFileCompiler.class.getClassLoader();
    Class flightTestClass = classLoader.loadClass("FlightTest");

    Result result = JUnitCore.runClasses(flightTestClass);

    // Check if all tests passed
    if (!result.wasSuccessful()) {
    // Failed tests found
      System.out.println("Tests failed:");

      for (org.junit.runner.notification.Failure failure : result.getFailures()) {
        System.out.println(failure.toString());
      }
    } else {
      // All tests passed
      System.out.println("All tests passed!");
    }

    // Delete extracted files
    File flightFile = new File("src/main/java/team1project/Flight.java"); 
    flightFile.delete();
    
    // File passengerFile = new File("src/main/java/team1project/Passenger.java");
    // passengerFile.delete();
    
  }
}