package team1project;

// import org.junit.jupiter.api.Test;
// import static org.junit.jupiter.api.Assertions.*;
// import org.junit.Before;
// import org.junit.Test;
// import static org.junit.Assert.*;


import org.junit.Before;
// import org.junit.jupiter.api.Test;
import static org.junit.Assert.*;
import org.junit.Test;
// import org.junit.jupiter.api.DisplayName;
// import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.time.LocalDateTime;

public class FlightTest{


    @Test
   // @DisplayName("Test String attributes types")
    public void testStringAttributes() {
        assertStringAttribute("flightNo");
        assertStringAttribute("destination");
        assertStringAttribute("origin");
    }

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

    @Test
   // @DisplayName("Test Flight Constructor")
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

    @Test
    //@DisplayName("Test Check in Luggage methdod")
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

    @Test
    //@DisplayName("Test Get Allowed Luggage method")
    public void testGetAllowedLuggage() {
        // Create a Flight instance
        LocalDateTime flightDate = LocalDateTime.now(); // You can replace this with an actual date
        Flight flight = new Flight("FL123", "New York", "London", flightDate);

        // Test getAllowedLuggage method
        char cabinClass = 'F'; // Replace with the desired cabin class
        int allowedLuggage = flight.getAllowedLuggage(cabinClass);
        assertEquals(3, allowedLuggage);
    }

    @Test
    //@DisplayName("Test Print Luggage Manifest method")
    public void testPrintLuggageManifest() {
        // Create a Flight instance
        LocalDateTime flightDate = LocalDateTime.now(); // You can replace this with an actual date
        Flight flight = new Flight("FL123", "New York", "London", flightDate);

        // Test printLuggageManifest method
        String luggageManifest = flight.printLuggageManifest();
        assertNotNull(luggageManifest);
    }

    @Test
    //@DisplayName("Test toString method")
    public void testToString() {
        // Create a Flight instance
        LocalDateTime flightDate = LocalDateTime.now(); // You can replace this with an actual date
        Flight flight = new Flight("FL123", "New York", "London", flightDate);

        // Test toString method
        //String expectedToString = "FL123 DESTINATION: New York ORIGIN: London " + flightDate;
        String expectedToString = "FL123" + " DESTINATION: " + "New York" + " ORGIN: " + "London" + " " + flightDate;
        assertEquals(expectedToString, flight.toString());
    }


}
