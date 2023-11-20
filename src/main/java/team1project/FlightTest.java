package team1project;

import static org.junit.Assert.*;
import org.junit.Test;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.time.LocalDateTime;

/**
 * This class contains JUnit tests for the Flight class.
 * It validates the behavior of Flight class attributes and methods.
 */
public class FlightTest {

    /**
     * Test for string attributes in the Flight class.
     */
    @Test
    public void testFlightStringAttributes() {
        assertStringAttribute("flightNo");
        assertStringAttribute("destination");
        assertStringAttribute("origin");
    }

    /**
     * Helper method to assert characteristics of string attributes.
     *
     * @param attributeName The name of the attribute to be tested.
     */
    private void assertStringAttribute(String attributeName) {
        // Create an instance of the Flight class
        LocalDateTime flightDate = LocalDateTime.now(); // You can replace this with an actual date
        Flight flight = new Flight("FL123", "New York", "London", flightDate);

        // Use reflection to get the field
        try {
            Field field = Flight.class.getDeclaredField(attributeName);

            // Assert that the field is of type String
            assertEquals(String.class, field.getType());

            // Assert that the field is private
            assertTrue(Modifier.isPrivate(field.getModifiers()));

            // Assert that the field is an instance variable (not static)
            assertFalse(Modifier.isStatic(field.getModifiers()));
        } catch (NoSuchFieldException e) {
            fail(attributeName + " field not found in Flight class");
        }
    }

    /**
     * Test for luggage manifest attribute in the Flight class.
     */
    @Test
    public void testLuggageManifestAttribute() {
        assertLuggageManifestAttribute("manifest");
    }

    /**
     * Helper method to assert characteristics of luggage manifest attribute.
     *
     * @param attributeName The name of the attribute to be tested.
     */
    private void assertLuggageManifestAttribute(String attributeName) {
        // Create an instance of the Flight class       
        Flight flight = new Flight("FL123", "New York", "London", LocalDateTime.now());

        // Use reflection to get the field
        try {
            Field field = Flight.class.getDeclaredField(attributeName);

            // Assert that the field is of type Passenger
            assertEquals(LuggageManifest.class, field.getType());

            // Assert that the field is private
            assertTrue(Modifier.isPrivate(field.getModifiers()));

            // Assert that the field is an instance variable (not static)
            assertFalse(Modifier.isStatic(field.getModifiers()));
        } catch (NoSuchFieldException e) {
            fail(attributeName + " field not found in LuggageSlip class");
        }
    }

    /**
     * Test for flight date attribute in the Flight class.
     */
    @Test
    public void testFlightDateAttribute() {
        assertLocalDateAttribute("flightDate");
    }

    /**
     * Helper method to assert characteristics of flight date attribute.
     *
     * @param attributeName The name of the attribute to be tested.
     */
    private void assertLocalDateAttribute(String attributeName) {
        // Create an instance of the Passenger class       
        Flight flight = new Flight("FL123", "New York", "London", LocalDateTime.now());

        // Use reflection to get the field
        try {
            Field field = Flight.class.getDeclaredField(attributeName);

            // Assert that the field is of type Passenger
            assertEquals(LocalDateTime.class, field.getType());

            // Assert that the field is private
            assertTrue(Modifier.isPrivate(field.getModifiers()));

            // Assert that the field is an instance variable (not static)
            assertFalse(Modifier.isStatic(field.getModifiers()));
        } catch (NoSuchFieldException e) {
            fail(attributeName + " field not found in LuggageSlip class");
        }
    }

    /**
     * Test for Flight class constructor.
     */
    @Test
    public void testFlightConstructor() {
        // Create an instance of the Flight class
        LocalDateTime flightDate = LocalDateTime.now(); // You can replace this with an actual date
        Flight flight = new Flight("FL123", "New York", "London", flightDate);

        // Assert that the state is set correctly using the input parameters
        assertEquals("FL123", flight.getFlightNo());
        assertEquals("New York", flight.getDestination());
        assertEquals("London", flight.getOrigin());
        assertEquals(flightDate, flight.getFlightDate());

        // Assert that the manifest is initialized
        assertNotNull(flight.getManifest());
    }

    /**
     * Test for checkInLuggage method in the Flight class.
     */
    @Test
    public void testCheckInLuggage() {
        // Create a Flight instance
        LocalDateTime flightDate = LocalDateTime.now(); // You can replace this with an actual date
        Flight flight = new Flight("FL123", "New York", "London", flightDate);

        // Create a sample Passenger for testing checkInLuggage method
        Passenger passenger = new Passenger("AB123456", "John", "Doe", "ABC123"); // Assuming you have a Passenger class

        // Test checkInLuggage method
        String checkInResult = flight.checkInLuggage(passenger);
        
        assertEquals("Invalid Flight", checkInResult);
    }

    /**
     * Test for getAllowedLuggage method in the Flight class.
     */
    @Test
    public void testGetAllowedLuggage() {
        // Create a Flight instance
        LocalDateTime flightDate = LocalDateTime.now(); // You can replace this with an actual date
        Flight flight = new Flight("FL123", "New York", "London", flightDate);

        // Test getAllowedLuggage method
        char cabinClass = 'F'; // Replace with the desired cabin class
        int allowedLuggage = flight.getAllowedLuggage(cabinClass);
        assertEquals(3, allowedLuggage);
    }

    /**
     * Test for printLuggageManifest method in the Flight class.
     */
    @Test
    public void testPrintLuggageManifest() {
        // Create a Flight instance
        LocalDateTime flightDate = LocalDateTime.now(); // You can replace this with an actual date
        Flight flight = new Flight("FL123", "New York", "London", flightDate);

        // Test printLuggageManifest method
        String luggageManifest = flight.printLuggageManifest();
        assertNotNull(luggageManifest);
    }

    /**
     * Test for toString method in the Flight class.
     */
    @Test
    public void testFlightToString() {
        // Create a Flight instance
        LocalDateTime flightDate = LocalDateTime.now(); // You can replace this with an actual date
        Flight flight = new Flight("FL123", "New York", "London", flightDate);

        // Test toString method
        String expectedToString = "FL123" + " DESTINATION: " + "New York" + " ORIGIN: " + "London" + " " + flightDate;
        assertEquals(expectedToString, flight.toString());
    }
}
