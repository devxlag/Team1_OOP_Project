 package team1project;

import org.junit.Test;
// import org.junit.jupiter.api.Test;
import static org.junit.Assert.*;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.time.LocalDateTime;

public class FlightTest {

   
      
    @Test
    public void testFlightCreation() {

    
        Flight flight = new Flight("FL123", "Destination", "Origin", LocalDateTime.now());

        assertEquals("FL123", flight.getFlightNo());
        assertEquals("Destination", flight.getDestination());
        assertEquals("Origin", flight.getOrigin());
    }

    // @Test
    // public void testCheckInLuggage() {
    //     Flight flight = new Flight("FL123", "Destination", "Origin", LocalDateTime.now());
    //     Passenger passenger = new Passenger("AB12345", "John", "Doe", "FL123");

    //     String result = flight.checkInLuggage(passenger);

    //     assertTrue(result.contains("PP NO. AB12345 NAME: J. DOE NUMLUGGAGE:"));
    // }

    // @Test
    // public void testPrintLuggageManifest() {
    //     Flight flight = new Flight("FL123", "Destination", "Origin", LocalDateTime.now());
    //     Passenger passenger = new Passenger("AB12345", "John", "Doe", "FL123");
    //     LuggageManifest luggageManifest = flight.getManifest();

    //     luggageManifest.addLuggage(passenger, flight);

    //     String result = flight.printLuggageManifest();

    //     assertTrue(result.contains("FL123_DOE_1 PP NO. AB12345 NAME: J. DOE NUMLUGGAGE:"));
    // }
}
