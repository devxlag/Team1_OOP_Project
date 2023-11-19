package team1project;



import org.junit.After;
import org.junit.Before;
import static org.junit.Assert.*;
import static org.junit.Assume.assumeFalse;
import static org.junit.Assume.assumeTrue;

import org.junit.Test;
// import org.junit.jupiter.api.DisplayName;
// import static org.junit.jupiter.api.Assertions.*;
import java.time.LocalDateTime;

public class LuggageManifestTest {

    private LuggageManifest luggageManifest;
    private Flight flight;
    private Flight flight1;
    private Passenger passenger;
    private Passenger passenger2;

    @Before    
    public void setUp() {
        luggageManifest = new LuggageManifest();
        LocalDateTime flightDate = LocalDateTime.now();
        flight = new Flight("ABC123", "Destination1", "Origin1", flightDate);
        flight1 = new Flight("XYZ456", "Destination2", "Origin2", flightDate);
        passenger = new Passenger("123456", "Joe", "Biden", "ABC123");
        passenger2 = new Passenger("789012", "Jane", "Smith", "ABC123");
    }

    @After
    public void tearDown() {
        luggageManifest = null;
       // flight.getManifest().getSlips().clear();
        flight = null;
        passenger = null;
        passenger2 = null;
    }

    @Test
    //@DisplayName("Test addLuggageSlip method")
    public void testAddLuggage() {
        

        String result1 = flight.checkInLuggage(passenger);

        // Calculate excess luggage cost
        int numAllowedPieces = flight.getAllowedLuggage(passenger.getCabinClass());
        int excessLuggage = Math.max(0, passenger.getNumLuggage() - numAllowedPieces);
        String excessLuggageCost = flight.getManifest().getExcessLuggageCostByPassenger(passenger.getPassportNumber());
        
        if (excessLuggageCost == "No Cost")
            excessLuggageCost = "0.0";
        
        // Calculate the actual number of luggage after check-in
        
        int numLuggage = passenger.getNumLuggage();

        String text1= "";
        if (passenger.getNumLuggage() == 0)
            text1 += passenger.getNumLuggage() + " pieces of luggage\n"; 
        else if (passenger.getNumLuggage() == 1)
            text1 += passenger.getNumLuggage() + " piece of luggage, " + excessLuggage + " excess\n";
        else
            text1 += passenger.getNumLuggage() + " pieces of luggage, " + excessLuggage + " excess\n";

        String text2 = "";
        if(passenger.getNumLuggage() != 0)
           text2  += "\nPieces Added: (" + passenger.getNumLuggage() + "). Excess Cost: $" + excessLuggageCost;
        else
           text2  += "\nNo Luggage to add";        
        
        String expectedResult = text1 
        + "PP NO. 123456 NAME: J.BIDEN NUMLUGGAGE: " + numLuggage
        + " CLASS: " + passenger.getCabinClass()
        + text2;

        assertEquals(expectedResult, result1);

        //assertEquals(1, flight.getManifest().getSlips().size());

        // Flight flight2 = new Flight("XYZ456", "Destination2", "Origin2", flightDate);
        // Passenger passenger2 = new Passenger("789012", "Jada", "Smith", "XYZ456");

        // String result2 = flight2.checkInLuggage(passenger2);

        // assertEquals("2 pieces of luggage, 0 excess\nPP NO. 789012 NAME: J.SMITH NUMLUGGAGE: 2 CLASS: B\nPieces Added: (2). Excess Cost: $0.0", result2);
        //assertEquals(2, flight2.getManifest().getSlips().size());
    }

    @Test
    //@DisplayName("Test getExcessLuggageCostByPassenger method")
    public void testGetExcessLuggageCostByPassenger() {
       

        flight.checkInLuggage(passenger);

         // Calculate the expected excess cost based on the specific values
        int numAllowedPieces = flight.getAllowedLuggage(passenger.getCabinClass());
        double expectedExcessCost = 0;
        
        if(passenger.getNumLuggage() > numAllowedPieces){
            expectedExcessCost = (passenger.getNumLuggage() - numAllowedPieces) * 35 ;            
        }
        String expectedExcessCostString = "";
        if (expectedExcessCost == 0)
            expectedExcessCostString = "No Cost";
        else
            expectedExcessCostString = Double.toString(expectedExcessCost);


        // Test case: Passenger with excess luggage
        String excessCost = flight.getManifest().getExcessLuggageCostByPassenger("123456");
        assertEquals(expectedExcessCostString, excessCost);

        // Test case: Passenger with no excess luggage
        String noExcessCost = flight.getManifest().getExcessLuggageCostByPassenger("NonExistentPassport");
        assertEquals("No Cost", noExcessCost);
    }

    @Test
    //@DisplayName("Test printLuggageManifest method")
    public void testPrintLuggageManifest() {
        
               

        flight1.checkInLuggage(passenger);
        flight1.checkInLuggage(passenger2);

        String result = flight.printLuggageManifest();

        assumeTrue(passenger.getNumLuggage() == 0);
        assertTrue(result.contains(passenger.toString()));

        assumeTrue(passenger2.getNumLuggage() != 0);  
        assumeFalse(result.contains(passenger2.toString()));
    
        // if(passenger.getNumLuggage() == 0)
        //     assertTrue(result.contains(passenger.toString()));
        // else
        //     assumeFalse(result.contains(passenger.toString()));

        // if(passenger2.getNumLuggage() == 0)
        //     assertTrue(result.contains(passenger2.toString()));
        // else
        //     assumeFalse(result.contains(passenger2.toString()));        
    }
}
