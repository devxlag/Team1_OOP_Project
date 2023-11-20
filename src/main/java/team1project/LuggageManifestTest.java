package team1project;

import org.junit.After;
import org.junit.Before;
import static org.junit.Assert.*;
import static org.junit.Assume.assumeFalse;
import static org.junit.Assume.assumeTrue;

import org.junit.Test;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 * The LuggageManifestTest class contains unit tests for the LuggageManifest class.
 * It ensures that the LuggageManifest class is implemented correctly and its
 * attributes and methods behave as expected.
 */
public class LuggageManifestTest {

    private LuggageManifest luggageManifest;
    private Flight flight;
    private Flight flight1;
    private Passenger passenger;
    private Passenger passenger2;

    /**
     * Set up the test environment before each test case.
     */
    @Before    
    public void setUp() {
        luggageManifest = new LuggageManifest();
        LocalDateTime flightDate = LocalDateTime.now();
        flight = new Flight("ABC123", "Destination1", "Origin1", flightDate);
        flight1 = new Flight("XYZ456", "Destination2", "Origin2", flightDate);
        passenger = new Passenger("123456", "Joe", "Biden", "ABC123");
        passenger2 = new Passenger("789012", "Jane", "Smith", "ABC123");
    }

    /**
     * Tear down the test environment after each test case.
     */
    @After
    public void tearDown() {
        luggageManifest = null;       
        flight = null;
        passenger = null;
        passenger2 = null;
    }

    /**
     * Tests the 'slips' attribute type of the LuggageManifest class.
     * It checks if the 'slips' attribute is of type ArrayList<LuggageSlip>.
     *
     * @throws NoSuchFieldException if the field is not found
     */
    @Test
    public void testLuggageSlipType() throws NoSuchFieldException {
         // Create an instance of MyClass
        ArrayList<LuggageSlip> expectedList = new ArrayList<>();
        LuggageManifest myInstance = new LuggageManifest();

        // Use reflection to access the private field
        Field privateSlipsField = LuggageManifest.class.getDeclaredField("slips");

        // Get the generic type of the ArrayList
        Type genericType = privateSlipsField.getGenericType();

        // Ensure that the field is of type ArrayList
        assertTrue(privateSlipsField.getType() == ArrayList.class);

        // Ensure that the generic type parameter is LuggageSlip
        assertTrue(genericType instanceof ParameterizedType);
        ParameterizedType parameterizedType = (ParameterizedType) genericType;
        Type[] typeArguments = parameterizedType.getActualTypeArguments();
        assertTrue(typeArguments.length == 1); // Only one type argument for ArrayList
        assertTrue(typeArguments[0] == LuggageSlip.class);
    }

    /**
     * Tests the LuggageManifest class constructor.
     * It checks if the constructor initializes the 'slips' attribute to a non-null ArrayList.
     */
    @Test
    public void testLuggageManifestConstructor() {
         LuggageManifest luggageManifest = new LuggageManifest();
        assertNotNull(luggageManifest.getSlips()); 
    }

    /**
     * Tests the 'addLuggageSlip' method of the LuggageManifest class.
     * It checks if the method adds luggage correctly and returns the expected result.
     */
    @Test
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
    }

    /**
     * Tests the 'getExcessLuggageCostByPassenger' method of the LuggageManifest class.
     * It checks if the method calculates the excess luggage cost correctly based on the passenger's details.
     */
    @Test    
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
        assumeTrue(passenger.getNumLuggage() > numAllowedPieces); // Assuming that the passenger has excess luggage (i.e. numLuggage > numAllowedPieces
        String excessCost = flight.getManifest().getExcessLuggageCostByPassenger("123456");
        assertEquals(expectedExcessCostString, excessCost);

        // Test case: Passenger with no excess luggage
        String noExcessCost = flight.getManifest().getExcessLuggageCostByPassenger("NonExistentPassport");
        assertEquals("No Cost", noExcessCost);
    }

    /**
     * Tests the 'getExcessLuggageCost' method of the LuggageManifest class.
     * It checks if the method calculates the excess luggage cost correctly based on the number of pieces and allowed pieces.
     */
    @Test
    public void testGetExcessLuggageCost() {
        // Create an instance of LuggageManifest
        LuggageManifest luggageManifest = new LuggageManifest();

        // Test case: numPieces is less than or equal to numAllowedPieces
        double cost1 = luggageManifest.getExcessLuggageCost(3, 5);
        assertEquals(0, cost1, 0.01); // Assuming a small delta for double comparison

        // Test case: numPieces is greater than numAllowedPieces
        double cost2 = luggageManifest.getExcessLuggageCost(8, 5);
        assertEquals(3 * 35, cost2, 0.01); // Excess pieces multiplied by cost per piece
    }

    /**
     * Tests the 'printLuggageManifest' method of the LuggageManifest class.
     * It checks if the method generates the expected string representation of the luggage manifest.
     */
    @Test   
    public void testLuggageManifestToString() {
        flight1.checkInLuggage(passenger);
        flight1.checkInLuggage(passenger2);

        String result = flight.printLuggageManifest();

        assumeTrue(passenger.getNumLuggage() == 0);
        assertTrue(!result.contains(passenger.toString()));

        assumeTrue(passenger2.getNumLuggage() != 0);  
        assumeFalse(result.contains(passenger2.toString()));           
    }
}
