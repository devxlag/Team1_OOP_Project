package team1project;
/*Student ID: 816029614*/
public class LuggageSlip
{
    private Passenger owner;
    private static int luggageSlipIDCounter = 1;
    private String luggageSlipID;
    private String label;

    public LuggageSlip(Passenger p, Flight f){
        owner = p;
        label = "";   
        setLuggageSlipID(p,f);        
    }

    public LuggageSlip(Passenger p, Flight f, String label){
        owner = p;
        this.label = label;
        setLuggageSlipID(p,f);
    }

    public String getLuggageSlipID(){
        return luggageSlipID;
    }

    public String getLabel(){
        return label;
    }

    public int getLuggageSlipIDCounter(){
        return luggageSlipIDCounter;
    }

    public Passenger getOwner(){
        return owner;
    }

    private void setLuggageSlipID(Passenger p, Flight f){
        this.luggageSlipID = f.getFlightNo() + "_" + p.getLastName() + "_" + getLuggageSlipIDCounter();
        luggageSlipIDCounter++;
    }

    public boolean hasOwner(String passportNumber){
        if(getOwner().getPassportNumber().equals(passportNumber))
            return true;
        return false;
    }

    public String toString(){
        String results = getLuggageSlipID() + " " + getOwner().toString();
        results += " " + getLabel();
        return results;
    }
}
