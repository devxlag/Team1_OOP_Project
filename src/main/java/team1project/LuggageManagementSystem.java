package team1project;
/*Student ID: 816029614*/
import java.time.LocalDateTime;
import java.io.File;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.ArrayList;
public class LuggageManagementSystem
{
    public static void main(String[] args){
        Passenger p;
        Flight f;
        ArrayList<Flight> flights = new ArrayList<Flight>(); 
        ArrayList<Passenger> passengers = new ArrayList<Passenger>();
        
        try{
            Scanner sc1 = new Scanner(new File("flights.txt"));
            Scanner sc2 = new Scanner(new File("passengers.txt"));
            String flightInfo = "";
            while(sc1.hasNextLine()){
                flightInfo = sc1.nextLine();
                StringTokenizer stk = new StringTokenizer (flightInfo);
                f = createFlight(stk);
                flights.add(f);
            }
            sc1.close();
            String passengerInfo = "";
            while(sc2.hasNextLine()){
                passengerInfo = sc2.nextLine();
                String[] dimensions = passengerInfo.split(" ");
                p = new Passenger (dimensions[0],dimensions[1], dimensions[2], dimensions[3]);
                passengers.add(p);
            }
            sc2.close();
        }
        catch(Exception e){
            System.out.println("Error opening files");
        }  
        
        int j = 0;
        for(Flight flight : flights){
            int i = 0;            
            System.out.println("Flight " + (j+1) + ": " + flight + "\n");
            for(Passenger passenger: passengers){
                System.out.print("Passenger " + (i+1) + ": ");
                System.out.println(flight.checkInLuggage (passenger));
                i++;
            }            
            System.out.println("\nLuggage Manifest:");
            System.out.println(flight.printLuggageManifest());
            System.out.println(flight.getManifest().getExcessLuggageCostByPassenger("TA890789"));
            System.out.print("\n");
            j++;
        }
    }

    private static Flight createFlight(StringTokenizer stk){
        Flight f;
        String flightNo = stk.nextToken();
        String origin = stk.nextToken();
        String destination = stk.nextToken();
        int year = Integer.parseInt(stk.nextToken());
        int month = Integer.parseInt(stk.nextToken());
        int day = Integer.parseInt(stk.nextToken());
        int hr = Integer.parseInt(stk.nextToken());
        int min = Integer.parseInt(stk.nextToken());
        int sec = Integer.parseInt(stk.nextToken());
        LocalDateTime d = LocalDateTime.of(year,month,day,hr,min,sec);
        f = new Flight(flightNo, origin, destination, d);
        return f;
    }
}
