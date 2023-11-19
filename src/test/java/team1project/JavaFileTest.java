package team1project;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.Test;

public class JavaFileTest {

    @Test
    public void testJavaFileCreation() {
        JavaFile javaFile = new JavaFile("TestFile.java");
        assertNotNull(javaFile);
        assertEquals("TestFile.java", javaFile.getFileName());
        assertNotNull(javaFile.getContents());
        assertEquals("", javaFile.getContents());
    }

    @Test
    public void testAppendContents() {
        JavaFile javaFile = new JavaFile("TestFile.java");

        javaFile.appendContents("public class Test {\n");
        javaFile.appendContents("    public static void main(String[] args) {\n");
        javaFile.appendContents("        System.out.println(\"Hello, World!\");\n");
        javaFile.appendContents("    }\n");
        javaFile.appendContents("}\n");

        String expectedContents = "public class Test {\n" +
                                  "    public static void main(String[] args) {\n" +
                                  "        System.out.println(\"Hello, World!\");\n" +
                                  "    }\n" +
                                  "}\n";

        assertEquals(expectedContents, javaFile.getContents());
    }

//     @Test
//     public void testDisplay() {
//         JavaFile javaFile = new JavaFile("TestFile.java");

//         // Redirect standard out for testing
//         ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
//         System.setOut(new PrintStream(outputStream));

//         javaFile.appendContents("public class Test {\n");
//         javaFile.appendContents("    public static void main(String[] args) {\n");
//         javaFile.appendContents("        System.out.println(\"Hello, World!\");\n");
//         javaFile.appendContents("    }\n");
//         javaFile.appendContents("}");

//         javaFile.display();

//         String expectedOutput = "Java File: TestFile.java\n" +
//                                 "Contents:\n" +
//                                 "public class Test {\n" +
//                                 "    public static void main(String[] args) {\n" +
//                                 "        System.out.println(\"Hello, World!\");\n" +
//                                 "    }\n" +
//                                 "}";

//         assertEquals(expectedOutput, outputStream.toString());

        
//         // Reset standard out
//         System.setOut(System.out);
//     }
}
