package team1project;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import java.time.LocalDateTime;

public class TestLuggageManifest {

    private LuggageManifest luggageManifest;

    @Before
    public void setUp() {
        luggageManifest = new LuggageManifest();
    }

    @Test
    public void testAddLuggage() {
        LocalDateTime flightDate = LocalDateTime.now();
        
        Flight flight1 = new Flight("ABC123", "Destination1", "Origin1", flightDate);
        Passenger passenger1 = new Passenger("123456", "Joe", "Biden", "ABC123");

        String result1 = flight1.checkInLuggage(passenger1);

        assertEquals("3 pieces of luggage, 1 excess\nPP NO. 123456 NAME: J.D NUMLUGGAGE: 3 CLASS: F\nPieces Added: (3). Excess Cost: $35.0", result1);
        assertEquals(3, flight1.getManifest().getSlips().size());

        Flight flight2 = new Flight("XYZ456", "Destination2", "Origin2", flightDate);
        Passenger passenger2 = new Passenger("789012", "Jada", "Smith", "XYZ456");

        String result2 = flight2.checkInLuggage(passenger2);

        assertEquals("2 pieces of luggage, 0 excess\nPP NO. 789012 NAME: J.S NUMLUGGAGE: 2 CLASS: B\nPieces Added: (2). Excess Cost: $0.0", result2);
        assertEquals(2, flight2.getManifest().getSlips().size());
    }

    @Test
    public void testGetExcessLuggageCostByPassenger() {
        LocalDateTime flightDate = LocalDateTime.now();
        
        Flight flight = new Flight("ABC123", "Destination", "Origin", flightDate);
        Passenger passenger = new Passenger("123456", "John", "Doe", "ABC123");

        flight.checkInLuggage(passenger);

        String excessCost = flight.getManifest().getExcessLuggageCostByPassenger("123456");

        assertEquals("$35.0", excessCost);

        String noExcessCost = flight.getManifest().getExcessLuggageCostByPassenger("NonExistentPassport");

        assertEquals("No Cost", noExcessCost);
    }

    @Test
    public void testPrintLuggageManifest() {
        LocalDateTime flightDate = LocalDateTime.now();
        
        Flight flight = new Flight("ABC123", "Destination", "Origin", flightDate);
        Passenger passenger1 = new Passenger("123456", "John", "Doe", "ABC123");
        Passenger passenger2 = new Passenger("789012", "Jane", "Smith", "ABC123");

        flight.checkInLuggage(passenger1);
        flight.checkInLuggage(passenger2);

        String result = flight.printLuggageManifest();

        assertTrue(result.contains("PP NO. 123456 NAME: J.D NUMLUGGAGE: 3 CLASS: F"));
        assertTrue(result.contains("PP NO. 789012 NAME: J.S NUMLUGGAGE: 2 CLASS: B"));
    }
}
