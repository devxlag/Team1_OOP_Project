package team1project;

import org.junit.Before;
// import org.junit.jupiter.api.Test;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.time.LocalDateTime;

public class PassengerTest {

    @Test
    @DisplayName("Test String attributes types")
    public void testStringAttributes() {
        assertStringAttribute("passportNumber");
        assertStringAttribute("flightNo");
        assertStringAttribute("firstName");
        assertStringAttribute("lastName");                        
    }

    private void assertStringAttribute(String attributeName) {
        // Create an instance of the Passenger class
        Passenger passenger = new Passenger("AB123456", "John", "Doe", "ABC123");

        // Use reflection to get the field
        try {
            Field field = Passenger.class.getDeclaredField(attributeName);

            // Assert that the field is of type String
            assertEquals(String.class, field.getType());

            // Assert that the field is private
            assertTrue(Modifier.isPrivate(field.getModifiers()));

            // Assert that the field is an instance variable (not static)
            assertFalse(Modifier.isStatic(field.getModifiers()));
        } catch (NoSuchFieldException e) {
            fail(attributeName + " field not found in Passenger class");
        }
    }



    @Test
    @DisplayName("Test numLuggage attribute type")
    public void testNumLuggageAttribute() {
        assertField("numLuggage", int.class, true, true);
    }

    private void assertField(String fieldName, Class<?> expectedType, boolean shouldBePrivate, boolean shouldBeStatic) {
        try {
            Field field = Passenger.class.getDeclaredField(fieldName);
            assertEquals(expectedType, field.getType());
            assertEquals(shouldBePrivate, Modifier.isPrivate(field.getModifiers()));
            assertEquals(shouldBeStatic, Modifier.isStatic(field.getModifiers()));
        } catch (NoSuchFieldException e) {
            fail(fieldName + " field not found in Passenger class");
        }
    }


    @Test
    @DisplayName("Test toStringMethodFormat type")
    public void testToStringMethodFormat() {
        // Create an instance of the Passenger class with specific attributes
        Passenger passenger = new Passenger("TA890789", "Joe", "Bean", "ABC123");


        // Call the toString method and check if it produces the expected result
        
        String expectedToString = "PP NO. " + passenger.getPassportNumber() +
                " NAME: " + passenger.getFirstName().substring(0, 1) + "." + passenger.getLastName() +
                " NUMLUGGAGE: " + passenger.getNumLuggage() + " CLASS: " + passenger.getCabinClass();

        assertEquals(expectedToString, passenger.toString());
    }



    @Test
    @DisplayName("Test assignRandomCabinClass method")
    public void testAssignRandomCabinClass() {
        // Create an instance of the Passenger class
        Passenger passenger = new Passenger("TA890789", "Joe", "Bean", "ABC123");
    

        // Check if the cabinClass is one of the valid values: 'F', 'B', 'P', 'E'
        String validCabinClasses = "FBPE";
        char actualCabinClass = passenger.getCabinClass();
        assertTrue(validCabinClasses.indexOf(actualCabinClass) >= 0);
    }





    @Test
     @DisplayName("Test passengerConstructor method") 
    public void testPassengerConstructor() {
        // Create multiple instances of the Passenger class with the same input parameters
        Passenger passenger1 = new Passenger("TA890789", "Joe", "Bean", "ABC123");

        // Assert that the state is set correctly using the input parameters
        assertEquals("TA890789", passenger1.getPassportNumber());
        assertEquals("Joe", passenger1.getFirstName());
        assertEquals("Bean", passenger1.getLastName());
        assertEquals("ABC123", passenger1.getFlightNo());

        // Assert that numLuggage is set randomly and is within the expected range
        assertTrue(passenger1.getNumLuggage() >= 0 && passenger1.getNumLuggage() < 10);

        // Assert that cabinClass is set randomly and is a valid cabin class
        String validCabinClasses = "FBPE";
        assertTrue(validCabinClasses.indexOf(passenger1.getCabinClass()) >= 0);
    }

}

