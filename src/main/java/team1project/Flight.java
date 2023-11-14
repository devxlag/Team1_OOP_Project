package team1project;
/*Student ID: 816029614*/
import java.time.LocalDateTime;

public class Flight{
    private String flightNo;
    private String destination;
    private String origin;
    private LocalDateTime flightDate;
    private LuggageManifest manifest;

    public Flight(String flightNo, String destination, String origin, LocalDateTime flightDate){
        this.flightNo = flightNo;
        this.destination = destination;
        this.origin = origin;
        this.flightDate = flightDate;
        this.manifest = new LuggageManifest();

    }

    public String getFlightNo(){
        return destination; 
    }

    public String getDestination(){
        return destination; 
    }

    public String getOrigin(){
        return origin; 
    }

    public LocalDateTime getFlightDate(){
        return flightDate; 
    }

    public LuggageManifest getManifest(){
        return manifest; 
    }

    public String checkInLuggage(Passenger p){

        if(p.getFlightNo().equals(this.getFlightNo())){           
            return getManifest().addLuggage(p,this);
        }
        return "Invalid Flight";
    }

    public String printLuggageManifest(){           
        return getManifest().toString();
    }

    public static int getAllowedLuggage(char cabinClass){
        switch(cabinClass){ 
            case 'F': return 3;
            case 'B': return 2;
            case 'P': return 1;
            case 'E': return 0; 
            default: return -1;
        }
    }

    public String toString(){
        String results = getFlightNo() + " DESTINATION: " + getDestination() + " ORGIN: " + getOrigin() + " " + getFlightDate();
        return results;
    }
}

    
    
   
