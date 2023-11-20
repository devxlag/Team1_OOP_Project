package team1project;

import org.junit.Before;
import static org.junit.Assert.*;
import org.junit.Test;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.time.LocalDateTime;

/**
 * The LuggageSlipTest class contains unit tests for the LuggageSlip class.
 * It ensures that the LuggageSlip class is implemented correctly and its
 * attributes and methods behave as expected.
 */
public class LuggageSlipTest {

    /**
     * Tests the 'owner' attribute of the LuggageSlip class.
     * It checks if the 'owner' attribute is of type Passenger, private, and non-static.
     */
    @Test
    public void testPassengerAttribute() {
        assertPassengerAttribute("owner");
    }

    /**
     * Asserts that a specified attribute in the LuggageSlip class has the correct type,
     * visibility, and non-static nature.
     *
     * @param attributeName The name of the attribute to be tested.
     */
    private void assertPassengerAttribute(String attributeName) {
       // Create an instance of the Passenger class
        Passenger passenger = new Passenger("AB123456", "John", "Doe", "ABC123");

        Flight flight = new Flight("FL123", "New York", "London", LocalDateTime.now());

        // Create an instance of the LuggageSlip class
        LuggageSlip luggageSlip = new LuggageSlip(passenger, flight);

        // Use reflection to get the field
        try {
            Field field = LuggageSlip.class.getDeclaredField(attributeName);

            // Assert that the field is of type Passenger
            assertEquals(Passenger.class, field.getType());

            // Assert that the field is private
            assertTrue(Modifier.isPrivate(field.getModifiers()));

            // Assert that the field is an instance variable (not static)
            assertFalse(Modifier.isStatic(field.getModifiers()));
        } catch (NoSuchFieldException e) {
            fail(attributeName + " field not found in LuggageSlip class");
        }
    }

    /**
     * Tests string attributes ('luggageSlipID' and 'label') of the LuggageSlip class.
     * It checks if the string attributes are of type String, private, and non-static.
     */
    @Test
    public void testLuggageSlipStringAttributes() {
        assertStringAttribute("luggageSlipID");
        assertStringAttribute("label");
    }

    /**
     * Asserts that a specified string attribute in the LuggageSlip class has the correct type,
     * visibility, and non-static nature.
     *
     * @param attributeName The name of the string attribute to be tested.
     */
    private void assertStringAttribute(String attributeName) {
         // Create an instance of the Passenger and Flight classes
        Passenger passenger = new Passenger("AB123456", "John", "Doe", "ABC123");
        LocalDateTime flightDate = LocalDateTime.now();
        Flight flight = new Flight("FL123", "New York", "London", flightDate);

        // Create an instance of the LuggageSlip class
        LuggageSlip luggageSlip = new LuggageSlip(passenger, flight);

        // Use reflection to get the field
        try {
            Field field = LuggageSlip.class.getDeclaredField(attributeName);

            // Assert that the field is of type String
            assertEquals(String.class, field.getType());

            // Assert that the field is private
            assertTrue(Modifier.isPrivate(field.getModifiers()));

            // Assert that the field is an instance variable (not static)
            assertFalse(Modifier.isStatic(field.getModifiers()));
        } catch (NoSuchFieldException e) {
            fail(attributeName + " field not found in LuggageSlip class");
        }
    }

    /**
     * Tests the 'luggageSlipIDCounter' attribute of the LuggageSlip class.
     * It checks if the 'luggageSlipIDCounter' attribute is of type int, private, and static.
     */
    @Test
    public void testIntegerAttribute() {
        assertField("luggageSlipIDCounter", int.class, true, true);
    }

    /**
     * Asserts that a specified attribute in the LuggageSlip class has the correct type,
     * visibility, and static nature.
     *
     * @param fieldName      The name of the attribute to be tested.
     * @param expectedType   The expected type of the attribute.
     * @param shouldBePrivate True if the attribute should be private, false otherwise.
     * @param shouldBeStatic  True if the attribute should be static, false otherwise.
     */
    private void assertField(String fieldName, Class<?> expectedType, boolean shouldBePrivate, boolean shouldBeStatic) {
       try {
            Field field = LuggageSlip.class.getDeclaredField(fieldName);
            assertEquals(expectedType, field.getType());
            assertEquals(shouldBePrivate, Modifier.isPrivate(field.getModifiers()));
            assertEquals(shouldBeStatic, Modifier.isStatic(field.getModifiers()));
        } catch (NoSuchFieldException e) {
            fail(fieldName + " field not found in LuggageSlip class");
        }
    }

    /**
     * Tests the LuggageSlip class constructor.
     * It checks if the constructor sets the state correctly using the input parameters.
     */
    @Test
    public void testLuggageSlipConstructor() {
        // Create instances of Passenger and Flight classes
        Passenger passenger = new Passenger("AB123456", "John", "Doe", "FL123");
        LocalDateTime flightDate = LocalDateTime.now();
        Flight flight = new Flight("FL123", "New York", "London", flightDate);

        // Create an instance of the LuggageSlip class using the constructor
        LuggageSlip luggageSlip = new LuggageSlip(passenger, flight);

        // Assert that the state is set correctly using the input parameters
        assertEquals(passenger, luggageSlip.getOwner());
        assertEquals(flight.getFlightNo(), luggageSlip.getOwner().getFlightNo());
        assertEquals("", luggageSlip.getLabel());
    }

    /**
     * Tests the LuggageSlip class constructor with a label.
     * It checks if the constructor with a label sets the state correctly using the input parameters.
     */
    @Test
    public void testLuggageSlipConstructorWithLabel() {
         // Create instances of Passenger and Flight classes
        Passenger passenger = new Passenger("AB123456", "John", "Doe", "FL123");
        LocalDateTime flightDate = LocalDateTime.now();
        Flight flight = new Flight("FL123", "New York", "London", flightDate);

        // Create an instance of the LuggageSlip class using the constructor with label
        String label = "Fragile";
        LuggageSlip luggageSlip = new LuggageSlip(passenger, flight, label);

        // Assert that the state is set correctly using the input parameters (including label)
        assertEquals(passenger, luggageSlip.getOwner());
        assertEquals(flight.getFlightNo(), luggageSlip.getOwner().getFlightNo());
        assertEquals("Fragile", luggageSlip.getLabel());
    }

    /**
     * Tests the 'hasOwner' method of the LuggageSlip class.
     * It checks if the 'hasOwner' method correctly determines if a passenger has ownership.
     */
    @Test
    public void testHasOwner() {
        // Create instances of Passenger and Flight classes
        Passenger passenger = new Passenger("AB123456", "John", "Doe", "ABC123");
        LocalDateTime flightDate = LocalDateTime.now();
        Flight flight = new Flight("FL123", "New York", "London", flightDate);

        // Create an instance of the LuggageSlip class
        LuggageSlip luggageSlip = new LuggageSlip(passenger, flight);

        // Test hasOwner method
        assertTrue(luggageSlip.hasOwner("AB123456"));
        assertFalse(luggageSlip.hasOwner("XYZ789"));
    }

    /**
     * Tests the 'toString' method of the LuggageSlip class.
     * It checks if the 'toString' method generates the expected string representation.
     */
    @Test
    public void testLuggageSlipToString() {
         // Create instances of Passenger and Flight classes
        Passenger passenger = new Passenger("AB123456", "John", "Doe", "ABC123");
        LocalDateTime flightDate = LocalDateTime.now();
        Flight flight = new Flight("FL123", "New York", "London", flightDate);

        // Create an instance of the LuggageSlip class
        LuggageSlip luggageSlip = new LuggageSlip(passenger, flight);

        // Set the luggageSlipIDCounter to 1 (assuming it's an instance variable)
        //luggageSlip.setLuggageSlipID(passenger, flight); not needed constructor does this
        int current_slip = luggageSlip.getLuggageSlipIDCounter() - 1;
        String cs = Integer.toString(current_slip);
        String expectedToString = "FL123_Doe_" + cs + " PP NO. AB123456 NAME: J.DOE NUMLUGGAGE: "+ passenger.getNumLuggage() + " CLASS: " + passenger.getCabinClass();
        // Test the toString method
        //String expectedToString = "FL123_Doe_1 PP NO. AB123456 NAME: J.DOE NUMLUGGAGE: "+ passenger.getNumLuggage() + " CLASS: " + passenger.getCabinClass();
        assertEquals(expectedToString, luggageSlip.toString());
    }
}
