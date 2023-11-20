package team1project;

import org.junit.After;
import org.junit.Before;
import static org.junit.Assert.*;
import org.junit.Test;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

/**
 * This class contains JUnit tests for the Passenger class.
 * It validates the behavior of Passenger class attributes and methods.
 */
public class PassengerTest {

    private Passenger passenger;

    /**
     * Sets up the test fixture.
     * Called before every test case method.
     */
    @Before
    public void setUp() {
        passenger = new Passenger("TA890789", "Joe", "Bean", "ABC123");
    }

    /**
     * Tears down the test fixture.
     * Called after every test case method.
     */
    @After
    public void tearDown() {
        passenger = null;
    }

    /**
     * Test for Passenger class constructor.
     */
    @Test
    public void testPassengerConstructor() {

        // Assert that the state is set correctly using the input parameters
        assertEquals("TA890789", passenger.getPassportNumber());
        assertEquals("Joe", passenger.getFirstName());
        assertEquals("Bean", passenger.getLastName());
        assertEquals("ABC123", passenger.getFlightNo());

        // Assert that numLuggage is set randomly and is within the expected range
        assertTrue(passenger.getNumLuggage() >= 0 && passenger.getNumLuggage() < 10);

        // Assert that cabinClass is set randomly and is a valid cabin class
        String validCabinClasses = "FBPE";
        assertTrue(validCabinClasses.indexOf(passenger.getCabinClass()) >= 0);
    }

    /**
     * Test for string attributes in the Passenger class.
     */
    @Test
    public void testPassengerStringAttributes() {
        assertStringAttribute("passportNumber");
        assertStringAttribute("flightNo");
        assertStringAttribute("firstName");
        assertStringAttribute("lastName");
    }

    /**
     * Helper method to assert characteristics of string attributes.
     *
     * @param attributeName The name of the attribute to be tested.
     */
    private void assertStringAttribute(String attributeName) {
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

    /**
     * Test for char attribute in the Passenger class.
     */
    @Test
    public void testCharAttribute() {
        assertCharAttribute("cabinClass");
    }

    /**
     * Helper method to assert characteristics of char attributes.
     *
     * @param attributeName The name of the attribute to be tested.
     */
    private void assertCharAttribute(String attributeName) {
        // Use reflection to get the field
        try {
            Field field = Passenger.class.getDeclaredField(attributeName);

            // Assert that the field is of type char
            assertEquals(char.class, field.getType());

            // Assert that the field is private
            assertTrue(Modifier.isPrivate(field.getModifiers()));

            // Assert that the field is an instance variable (not static)
            assertFalse(Modifier.isStatic(field.getModifiers()));
        } catch (NoSuchFieldException e) {
            fail(attributeName + " field not found in Passenger class");
        }
    }

    /**
     * Test for numLuggage attribute in the Passenger class.
     */
    @Test
    public void testNumLuggageAttribute() {
        assertField("numLuggage", int.class, true, false);
    }

    /**
     * Helper method to assert characteristics of numLuggage attribute.
     *
     * @param fieldName The name of the attribute to be tested.
     * @param expectedType The expected data type of the attribute.
     * @param shouldBePrivate Whether the attribute should be private.
     * @param shouldBeStatic Whether the attribute should be static.
     */
    private void assertField(String fieldName, Class<?> expectedType, boolean shouldBePrivate, boolean shouldBeStatic) {
        // Use reflection to get the field
        try {
            Field field = Passenger.class.getDeclaredField(fieldName);

            // Assert that the field is of the expected type
            assertEquals(expectedType, field.getType());

            // Assert that the field is private
            assertEquals(shouldBePrivate, Modifier.isPrivate(field.getModifiers()));

            // Assert that the field is an instance variable (not static)
            assertEquals(shouldBeStatic, Modifier.isStatic(field.getModifiers()));
        } catch (NoSuchFieldException e) {
            fail(fieldName + " field not found in Passenger class");
        }
    }

    /**
     * Test for toString method format in the Passenger class.
     */
    @Test
    public void testToStringMethodFormat() {
        // Call the toString method and check if it produces the expected result
        String expectedToString = "PP NO. TA890789" + " NAME: J.BEAN" +
                " NUMLUGGAGE: " + passenger.getNumLuggage() + " CLASS: " + passenger.getCabinClass();

        assertEquals(expectedToString, passenger.toString());
    }

    /**
     * Test for assigning a random cabin class to the Passenger class.
     */
    @Test
    public void testAssignRandomCabinClass() {
        // Check if the cabinClass is one of the valid values: 'F', 'B', 'P', 'E'
        String validCabinClasses = "FBPE";
        char actualCabinClass = passenger.getCabinClass();
        assertTrue(validCabinClasses.indexOf(actualCabinClass) >= 0);
    }
}
