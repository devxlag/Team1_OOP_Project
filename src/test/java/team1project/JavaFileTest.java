package team1project;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;


import org.junit.Test;
/**
 * Unit tests for the {@link JavaFile} class.
 * 
 */
public class JavaFileTest {
    /**
     * Tests the creation of a JavaFile object.
     */
    @Test
    public void testJavaFileCreation() {
        JavaFile javaFile = new JavaFile("TestFile.java");
        assertNotNull(javaFile);
        assertEquals("TestFile.java", javaFile.getFileName());
        assertNotNull(javaFile.getContents());
        assertEquals("", javaFile.getContents());
    }
    /**
     * Tests the appendContents method of the JavaFile class.
     */
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
}
