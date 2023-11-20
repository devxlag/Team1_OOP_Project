package team1project;

import org.junit.Test;
import static org.junit.Assert.*;
/**
 * JUnit test class for {@link CleanCodeChecker}.
 * Tests various scenarios for the checkCode method.
 */
public class CleanCodeCheckerTest {

    /**
     * Test the checkCode method with clean code having no issues.
     */
    @Test
    public void testCheckCode() {
        // Test with code that has no issues
        String cleanCode = "public class CleanCodeExample {\n" +
                "\tpublic static void main(String[] args) {\n" +
                "\t\tSystem.out.println(\"Clean code example.\");\n" +
                "\t}\n" +
                "}";
        double cleanCodeScore = CleanCodeChecker.checkCode(cleanCode);
        assertEquals(5.0, cleanCodeScore, 0.01);

        // Test with code that has indentation issues
        String codeWithIndentationIssues = "public class CodeWithIndentationIssues {\n" +
                "public static void main(String[] args) {\n" + // Incorrect indentation
                "System.out.println(\"Code with indentation issues.\");\n" +
                "}\n" +
                "}";
        double indentationIssuesScore = CleanCodeChecker.checkCode(codeWithIndentationIssues);
        assertEquals(5.0, indentationIssuesScore, 0.01);

        // Test with code that has syntax issues
        String codeWithSyntaxIssues = "public class CodeWithSyntaxIssues {\n" +
                "}\n" +
                "{\n" + // Syntax issue
                "public static void main(String[] args) {\n" +
                "System.out.println(\"Code with syntax issues.\");\n" +
                "}\n" +
                "}";
        double syntaxIssuesScore = CleanCodeChecker.checkCode(codeWithSyntaxIssues);
        assertEquals(5, syntaxIssuesScore, 0.01);

        // Test with code that has long methods
        String codeWithLongMethods = "public class CodeWithLongMethods {\n" +
                "\tpublic void longMethod1() {\n" +
                "\t\t// Long method code...\n".repeat(20) + // 20 lines to exceed the limit
                "\t}\n" +
                "\tpublic void longMethod2() {\n" +
                "\t\t// Long method code...\n".repeat(20) +
                "\t}\n" +
                "}";
        double longMethodsScore = CleanCodeChecker.checkCode(codeWithLongMethods);
        assertEquals(5, longMethodsScore, 0.01);

        // Test with code that has camel case naming issues
        String codeWithCamelCaseIssues = "public class CodeWithCamelCaseIssues {\n" +
                "\tint INCORRECT_variableName;\n" + // Incorrect camel case
                "\tpublic void incorrectMethodName() {\n" +
                "\t\t// Method code...\n" +
                "\t}\n" +
                "}";
        double camelCaseIssuesScore = CleanCodeChecker.checkCode(codeWithCamelCaseIssues);
        assertEquals(5, camelCaseIssuesScore, 0.01);

        // Test with code that has comments (comment score should be positive)
        String codeWithComments = "public class CodeWithComments {\n" +
                "\t// This is a single-line comment\n" +
                "\t/*\n" +
                "\t * This is a multi-line comment\n" +
                "\t */\n" +
                "\tpublic void methodWithComments() {\n" +
                "\t\t// Method code...\n" +
                "\t}\n" +
                "}";
        double commentScore = CleanCodeChecker.checkCode(codeWithComments);
        assertEquals(5, commentScore, 0.01);
    }
}

