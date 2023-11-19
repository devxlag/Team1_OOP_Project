

package team1project;
 


/**
 *
 * Name:Myah Small
 * I.D: 816026988
 *
 */
import java.util.Scanner;
import java.io.File;
import java.time.LocalDateTime;
import java.util.ArrayList;
public class LuggageManagementSystem
{
 
    public static void main (String [] args )
    {
        String passengerFile="PassengerList.txt";
        String flightFile="FlightList.txt";
        String detailsP=" ";
        String detailsF=" ";
        
        ArrayList<Passenger> passengerList = new ArrayList<Passenger>();
        ArrayList<Flight> flights = new ArrayList<>();

      
       try
       {
         File file= new File(passengerFile);
         Scanner scanner= new Scanner (file);
         
         while (scanner.hasNextLine())
         {
             detailsP=scanner.nextLine();
             
             Passenger p=createPassenger(detailsP.split(" "));
             passengerList.add(p);
            
         }
         
         scanner.close();
         
         
       }
       catch (Exception e)
       {
           System.out.println("Passenger File not found");
       }
       
         try
       {
         File file2= new File(flightFile);
         Scanner scanner2= new Scanner (file2);
         
         while (scanner2.hasNextLine())
         {
           detailsF=scanner2.nextLine();
           Flight f=createFlight(detailsF.split(" "));
           System.out.println(f);
           flights.add(f);
         
         }
         
         scanner2.close();
        
         
       }
       catch (Exception e)
       {
           System.out.println("Flight File not found");
       }
                
             
             
             for (Passenger p : passengerList) 
                  {
                      boolean flightFound= false;
                       for (Flight f : flights) 
                       { 
                            if (p.getFlightNo().equals(f.getFlightNo())) 
                            {
                              System.out.println(f.checkInLuggage(p));
                            
                               flightFound = true;
                        
                               break;
                            }
                         }
                        if (!flightFound) 
                        {
                            System.out.println("ERROR---Passenger: "+ p.getFirstName()+ " "+ p.getLastName()+ " has Invalid Flight Info----\n");
                        }
                                        }
                    
                    for (Flight f: flights)
                    {
                        System.out.println(f.printLuggageManifest( ));
                    }
                    

    }
    
       public static Passenger createPassenger (String [] PassengerInfo ){
       
        String passportNumber= PassengerInfo[0];
       String firstName =PassengerInfo[1];
        String lastName=PassengerInfo[2];
        String flightNo=PassengerInfo[3];
        
       
        
       
       
       Passenger p= new Passenger(passportNumber,firstName, lastName, flightNo);
    
       return p;
    }
    
     public static Flight createFlight (String [] FlightInfo ){
       
        String flightNo= FlightInfo[0];
        String destination =FlightInfo[1];
        String origin=FlightInfo[2];
        int year= Integer.parseInt(FlightInfo[3]);
        int month=Integer.parseInt(FlightInfo[4]);
        int day=Integer.parseInt(FlightInfo[5]);
        int hour=Integer.parseInt(FlightInfo[6]);
        int minutes=Integer.parseInt(FlightInfo[7]);
           
       
      LocalDateTime d= LocalDateTime.of(year,month,day,hour,minutes);
       Flight f = new Flight(flightNo,destination, origin, d);
    
       return f;
    }
}
