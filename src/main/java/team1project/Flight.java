
package team1project;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.time.LocalDateTime;

/**
 *
 * Name:Myah Small
 * I.D: 816026988
 *
 */
public class Flight
{
    // instance variables - replace the example below with your own
    private String flightNo;
    private String destination;
    private String origin;
    private LocalDateTime flightDate;
    private LuggageManifest manifest;
    
    

    /**
     * Constructor for objects of class Flight
     */
    public Flight(String flightNo, String destination, String origin,LocalDateTime flightDate)
    {
        // initialise instance variables
        this.flightNo=flightNo;
        this.destination=destination;
        this.origin=origin;
        this.flightDate=flightDate;
        manifest= new LuggageManifest();
       
    }
    
    //Accesors for all attributes
    
    public String getFlightNo()
    {
        return flightNo;
    }
    
    public String getDestination()
    {
        return destination;
    }
    
    public String getOrigin()
    {
        return origin;
    }
    
    public LocalDateTime getFlightDate()
    {
        return flightDate;
    }
    
    public LuggageManifest getManifest()
    {
        return manifest;
    }
    
    public String checkInLuggage (Passenger p)
    {
        String checkInDetails=" ";
        if (getFlightNo().equals(p.getFlightNo()))
        {
          checkInDetails=getManifest().addLuggage(p,this);
            
        }else
         
        checkInDetails="Invalid flight";
        
        return checkInDetails;
    }
    
    public String printLuggageManifest( )
    {
        
        return "Luggage Manifest for Flight " + getFlightNo() + "\n"+ getManifest().toString();
    }
    
    public static int getAllowedLuggage( char cabinClass)
    {
        int numAllowedLuggage;
        
        switch(cabinClass)
        {
            case 'F': 
                numAllowedLuggage=3;
                return numAllowedLuggage;
            case 'B':
                numAllowedLuggage=2;
                return numAllowedLuggage;
            case 'P':
                numAllowedLuggage=1;
                return numAllowedLuggage;
            case 'E':
                numAllowedLuggage=0;
                return numAllowedLuggage;
        }
        
        
          return -1;          
        
    }
    
    public String toString()
    {
        String flightDetails= "FLIGHT NUMBER:" + getFlightNo() + "DESTINATION: "+ getDestination()+ "ORIGIN: " + getOrigin() + " DATE: "+ getFlightDate() + "\n";
          
        return flightDetails;
    }

   
   
}
