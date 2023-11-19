package team1project;


import static org.junit.Assert.*;
import org.junit.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

/**
 * Test class for {@link LuggageManagementSystem}.
 * This class contains a test case for the main method of the LuggageManagementSystem class.
 * It redirects System.out to capture printed output, creates an instance of LuggageManagementSystem,
 * calls the main method, and asserts that the printed output is not null or empty.
 *
 * @see LuggageManagementSystem
 */


public class LuggageManagementSystemTest
 {

    /**
     * Test case for the main method of LuggageManagementSystem.
     * Redirects System.out to capture printed output, creates an instance of LuggageManagementSystem,
     * calls the main method, and asserts that the printed output is not null or empty.
     */

    @Test
    public void testLuggageManagementSystem()
     {
        // Redirect System.out to capture printed output
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        // Create an instance of LuggageManagementSystem
        LuggageManagementSystem luggageManagementSystem = new LuggageManagementSystem();

        // Call the main method 
        luggageManagementSystem.main(null);

        // Restore normal System.out
        System.setOut(System.out);

        // Assert that the printed output is not null or empty (indicating success)
        assertNotNull(outContent.toString());
        assertFalse(outContent.toString().isEmpty());

    }

  
}
