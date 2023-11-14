package team1project;

import org.junit.Test;
import static org.junit.Assert.assertEquals;
import java.time.LocalDateTime;

public class PassengerTest {
    
    @Test
    public void testPassengerConstructor(){

        Passenger passenger = new Passenger("AB12345", "John", "Doe", "FL123");
        assertEquals("AB12345", passenger.getPassportNumber());
        //assertEquals("John", passenger.getFirstName());
        //assertEquals("Doe", passenger.getLastName());
        //assertEquals("FL123", passenger.getFlightNo());
    }
}
