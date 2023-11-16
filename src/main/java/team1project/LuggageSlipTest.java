
package team1project;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.time.LocalDateTime;



public class LuggageSlipTest{

    @Test
    @DisplayName("Test String attributes types")
    public void testStringAttributes() {
        assertStringAttribute("luggageSlipID");
        assertStringAttribute("label");
    }

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

    @Test
    @DisplayName("Test Integer attribute type")
    public void testIntegerAttribute() {
        assertField("luggageSlipIDCounter", int.class, true, true);
    }

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

    @Test
    @DisplayName("Test LuggageSlip constructor")
    public void testLuggageSlipConstructor() {
        // Create instances of Passenger and Flight classes
        Passenger passenger = new Passenger("AB123456", "John", "Doe", "ABC123");
        LocalDateTime flightDate = LocalDateTime.now();
        Flight flight = new Flight("FL123", "New York", "London", flightDate);

        // Create an instance of the LuggageSlip class using the constructor
        LuggageSlip luggageSlip = new LuggageSlip(passenger, flight);

        // Assert that the state is set correctly using the input parameters
        assertEquals(passenger, luggageSlip.getOwner());
        assertEquals(flight, luggageSlip.getOwner().getFlightNo());
        assertEquals("", luggageSlip.getLabel());
    }

    @Test
    @DisplayName("Test LuggageSlip constructor with label")
    public void testLuggageSlipConstructorWithLabel() {
        // Create instances of Passenger and Flight classes
        Passenger passenger = new Passenger("AB123456", "John", "Doe", "ABC123");
        LocalDateTime flightDate = LocalDateTime.now();
        Flight flight = new Flight("FL123", "New York", "London", flightDate);

        // Create an instance of the LuggageSlip class using the constructor with label
        String label = "Fragile";
        LuggageSlip luggageSlip = new LuggageSlip(passenger, flight, label);

        // Assert that the state is set correctly using the input parameters (including label)
         assertEquals(passenger, luggageSlip.getOwner());
        assertEquals(flight, luggageSlip.getOwner().getFlightNo());
        assertEquals("", luggageSlip.getLabel());
    }

    @Test
    @DisplayName("Test hasOwner method")
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

    @Test
    @DisplayName("Test toString method")
    public void testToString() {
        // Create instances of Passenger and Flight classes
        Passenger passenger = new Passenger("AB123456", "John", "Doe", "ABC123");
        LocalDateTime flightDate = LocalDateTime.now();
        Flight flight = new Flight("FL123", "New York", "London", flightDate);

        // Create an instance of the LuggageSlip class
        LuggageSlip luggageSlip = new LuggageSlip(passenger, flight);

        // Set the luggageSlipIDCounter to 1 (assuming it's an instance variable)
        //luggageSlip.setLuggageSlipID(passenger, flight); not needed constructor does this

        // Test the toString method
        String expectedToString = "FL123_Doe_1 PP NO. AB123456 NAME: J.Doe LUGGAGE: 0 CLASS: $";
        assertEquals(expectedToString, luggageSlip.toString());
    }



}
