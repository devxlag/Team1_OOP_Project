package team1project;


import static org.junit.Assert.*;
import org.junit.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;


public class LuggageManagementSystemTest
 {

    @Test
    public static void testLuggageManagementSystem() {
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


    // @Test
    // public void testCreatePassenger() {
      
    //     String[] passengerInfo = {"123456789", "John", "Doe", "ABC123"};

        
    //     Passenger result = LuggageManagementSystem.createPassenger(passengerInfo);

    //     assertNotNull(result);

    //     assertEquals("123456789", result.getPassportNumber());
    //     assertEquals("John", result.getFirstName());
    //     assertEquals("Doe", result.getLastName());
    //     assertEquals("ABC123", result.getFlightNo());
    // }

    // @Test
    // public void testCreateFlight() {
    //     // Test data for createFlight
    //     String[] flightInfo = {"ABC123", "Destination", "Origin", "2023", "11", "16", "12", "30"};

    //     Flight result = LuggageManagementSystem.createFlight(flightInfo);

    //     assertNotNull(result);

    //     assertEquals("ABC123", result.getFlightNo());
    //     assertEquals("Destination", result.getDestination());
    //     assertEquals("Origin", result.getOrigin());
    //     assertEquals(2023, result.getFlightDate().getYear());
    //     assertEquals(11, result.getFlightDate().getMonthValue());
    //     assertEquals(16, result.getFlightDate().getDayOfMonth());
    //     assertEquals(12, result.getFlightDate().getHour());
    //     assertEquals(30, result.getFlightDate().getMinute());

    // }
}
