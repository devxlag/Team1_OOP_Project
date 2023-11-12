package team1project;

import org.junit.Test;
import static org.junit.Assert.*;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

public class Evaluator{



    //Test cases for the Passenger file.

    @Test
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
    public void testToStringMethodFormat() {
        // Create an instance of the Passenger class with specific attributes
        Passenger passenger = new Passenger("TA890789", "Joe", "Bean", "ABC123");
        passenger.assignRandomCabinClass();  // Assuming the method to assign cabinClass is called
        passenger.SetNumLuggage();

        // Call the toString method and check if it produces the expected result
        String expectedToString = "PP NO. " + passenger.GetPassportNumber() + " NAME: " + passenger.GetFirstName().substring(0,1) + "." + passenger.GetLastName() + " LUGGAGE: " + passenger.GetNumLuggage();
        assertEquals(expectedToString, passenger.toString());
    }



    @Test
    public void testAssignRandomCabinClass() {
        // Create an instance of the Passenger class
        Passenger passenger = new Passenger("TA890789", "Joe", "Bean", "ABC123");

        // Call the assignRandomCabinClass method
        passenger.assignRandomCabinClass();

        // Check if the cabinClass is one of the valid values: 'F', 'B', 'P', 'E'
        String validCabinClasses = "FBPE";
        char actualCabinClass = passenger.GetCabinClass();
        assertTrue(validCabinClasses.indexOf(actualCabinClass) >= 0);
    }





    @Test
    public void testPassengerConstructor() {
        // Create multiple instances of the Passenger class with the same input parameters
        Passenger passenger1 = new Passenger("TA890789", "Joe", "Bean", "ABC123");

        // Assert that the state is set correctly using the input parameters
        assertEquals("TA890789", passenger1.GetPassportNumber());
        assertEquals("Joe", passenger1.GetFirstName());
        assertEquals("Bean", passenger1.GetLastName());
        assertEquals("ABC123", passenger1.GetFlightNo());

        // Assert that numLuggage is set randomly and is within the expected range
        assertTrue(passenger1.GetNumLuggage() >= 0 && passenger1.GetNumLuggage() < 10);

        // Assert that cabinClass is set randomly and is a valid cabin class
        String validCabinClasses = "FBPE";
        assertTrue(validCabinClasses.indexOf(passenger1.GetCabinClass()) >= 0);
    }

}
