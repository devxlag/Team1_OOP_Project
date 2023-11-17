package team1project;
/*Student ID: 234*/
public class Passenger
{
    private String passportNumber;
    private String flightNo;
    private String firstName;
    private String lastName;
    private int numLuggage;
    private char cabinClass;
    
    public Passenger(String passportNumber, String firstName, String lastName, String flightNo){
        this.passportNumber = passportNumber;
        this.firstName = firstName;
        this.lastName = lastName;
        this.flightNo = flightNo;        
        setNumLuggage();
        assignRandomCabinClass();
    }
    
    public String getPassportNumber(){
        return passportNumber ;
    }
    
    public String getFlightNo(){
        return flightNo;
    }
    
    public String getFirstName(){
        return firstName;
    }
    
    public String getLastName(){
        return lastName;
    }
    
    public int getNumLuggage(){
        return numLuggage;
    }
    
    public char getCabinClass(){
        return cabinClass;
    }
    
    private void setNumLuggage(){
        this.numLuggage = getRandomNumber(0,4);
    }
    
    private void assignRandomCabinClass(){
        int randomNumber = getRandomNumber(1,5);        
        switch(randomNumber){
            case 1: this.cabinClass = 'F';
                break;
            case 2: this.cabinClass = 'B';
                break;
            case 3: this.cabinClass = 'P';
                break;
            case 4: this.cabinClass = 'E';
                break;
        }
    }
    
    private static int getRandomNumber(int min, int max){
        return (int) ((Math.random() * (max - min)) + min);
    }
    
    public String toString(){
        String results = "PP NO. " + getPassportNumber() + 
        " NAME: " + getFirstName().substring(0,1)+ "." + getLastName().toUpperCase() +
        " NUMLUGGAGE: " + getNumLuggage() + " CLASS: " + getCabinClass();
        return results;
    }    
}
