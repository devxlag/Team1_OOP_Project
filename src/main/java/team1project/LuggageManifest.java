package team1project;
/*Student ID: 816029614*/
import java.util.ArrayList;
public class LuggageManifest
{
    ArrayList<LuggageSlip> slips;

    public LuggageManifest(){
        slips = new ArrayList<LuggageSlip>();
    }

    public ArrayList<LuggageSlip> getLuggageSlips(){
        return slips;
    }

    public String addLuggage(Passenger p, Flight f){
        String results = "";
        String label = "";
        int excessLuggage = 0;

        int numAllowedPieces = f.getAllowedLuggage(p.getCabinClass());
        double excessLuggageCost = getExcessLuggageCost(p.getNumLuggage(), numAllowedPieces);

        if(excessLuggageCost != 0)
            label = "$" + excessLuggageCost;                

        for(int i = 0; i < p.getNumLuggage(); i++){                       
            getLuggageSlips().add(new LuggageSlip(p, f, label));
        }

        if(p.getNumLuggage() > numAllowedPieces ){
            excessLuggage = p.getNumLuggage() - numAllowedPieces;
        }  

        if (p.getNumLuggage() == 0)
            results += p.getNumLuggage() + " pieces of luggage\n"; 
        else if (p.getNumLuggage() == 1)
            results += p.getNumLuggage() + " piece of luggage, " + excessLuggage + " excess\n";
        else
            results += p.getNumLuggage() + " pieces of luggage, " + excessLuggage + " excess\n";

        results += p.toString();

        if(p.getNumLuggage() != 0)
            results += "\nPieces Added: (" + p.getNumLuggage() + "). Excess Cost: $" + excessLuggageCost;
        else
            results += "\nNo Luggage to add";

        return results;
    }

    private double getExcessLuggageCost(int numPieces, int numAllowedPieces){
        double excessLuggageCost = 0;
        if(numPieces > numAllowedPieces){
            excessLuggageCost= (numPieces - numAllowedPieces) * 35 ;            
        }
        return excessLuggageCost;
    }

    public String getExcessLuggageCostByPassenger(String passportNumber){
        for(LuggageSlip slip: getLuggageSlips()){
            if(slip.hasOwner(passportNumber)){
                //if(!slip.getLabel().equals(""))
                 //   return slip.getLabel(); 
                int numAllowedPieces = Flight.getAllowedLuggage(slip.getOwner().getCabinClass());
                double excessLuggageCost = getExcessLuggageCost(slip.getOwner().getNumLuggage(), numAllowedPieces);
                 return Double.toString(excessLuggageCost);
            }
        }
        return "No Cost";
    }

    public String toString(){
        String results = "";
        for(LuggageSlip slip: getLuggageSlips()){
            results += slip.toString() + "\n";
        }
        return results;
    }
}
