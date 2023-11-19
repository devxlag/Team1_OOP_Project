

package team1project;

/**
 *
 * Name:Myah Small
 * I.D: 816026988
 *
 */
import java.util.Random;

public class Passenger
{
    private String passportNumber;
    private String flightNo;
    private String firstName;
    private String lastName;
    public int numLuggage;
    private char cabinClass;

    /**
     * Constructor for objects of class Passenger
     */
    public Passenger(String passportNumber,String firstName, String lastName,String flightNo)
    {
        // initialise instance variables
        this.passportNumber=passportNumber;
        this.flightNo=flightNo;
        this.firstName=firstName;
        this.lastName=lastName;
        numLuggage=assignRandomNumLuggage(0,3);
        assignRandomCabinClass();
    }
    /**
     *Accessors  for objects of class Passenger
     */
    public String getPassportNumber()
    {
        return passportNumber;
    }
    
    public String getFlightNo()
    {
        return flightNo;
    }
    
    public String getFirstName()
    {
        return firstName;
    }
    
    public String getLastName()
    {
        return lastName;
    }
    
    public int getNumLuggage()
    {
        return numLuggage;
    }
    public char getCabinClass()
    {
        return cabinClass;
    }
    
    /*
     * Methods 
     */

    private void assignRandomCabinClass( )
    {
        
        String cabinClassValue="FBPE";
        Random r=new Random();
               
        cabinClass=cabinClassValue.charAt(r.nextInt(cabinClassValue.length()));
        
    }
   
    
    private static int assignRandomNumLuggage(int min, int max)
    {
        return (int) ((Math.random()* (max-min)) + min );
    }
    
    
    public String toString()
    {
        String passengerDetails="PP NO. " + getPassportNumber() + " NAME: " + getFirstName().substring(0,1)+". "+ getLastName()+" NUMLUGGAGE: " + getNumLuggage() + " CLASS: " + getCabinClass();
    
        return passengerDetails;
        
    }

}
