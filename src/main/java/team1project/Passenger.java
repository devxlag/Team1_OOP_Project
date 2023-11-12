package team1project;

    import java.util.Scanner;
    import java.io.File;
    import java.io.FileNotFoundException;
    import java.io.*;
    import java.util.*;
    import java.lang.Math;
    import java.lang.String;
    import java.util.ArrayList;
    import java.util.Random;



    /**
     * Passenger class, details on passenger.
     *
     * @author (Stevie Harrypersad Singh)
     * @version (30/01/2023)
     */


    public class Passenger{


        //Attributes

        private String passportNumber;
        private String flightNo;
        private String firstName;
        private String lastName;
        private static int numLuggage;
        private static char cabinClass;



        //Accessors

        public String GetPassportNumber(){
            return passportNumber;
        }



        public String GetFlightNo(){
            return flightNo;
        }



        public String GetFirstName(){
            return firstName;
        }



        public String GetLastName(){
            return lastName;
        }



        public int GetNumLuggage(){
            return numLuggage;
        }



        public char GetCabinClass(){
            return cabinClass;
        }





        //Mutator

        public void SetNumLuggage(){

        Random Rand= new Random();
        int NL= 10;

        numLuggage= Rand.nextInt(NL);

        }





        //Methods

        public void assignRandomCabinClass(){


            String Cabin= "FBPE";
            StringBuilder b = new StringBuilder();
            char randChar;

            int randIdx = new Random().nextInt(Cabin.length());
            randChar= Cabin.charAt(randIdx);
            b.append(randChar);
            cabinClass= randChar;



        }


        public Passenger(String passportNumber, String firstName, String lastName, String flightNo){


        this.passportNumber= passportNumber;
        this.firstName= firstName;
        this.lastName= lastName;
        this.flightNo= flightNo;

    SetNumLuggage();
    assignRandomCabinClass();


        }





        public String toString(){

            Passenger P1;
            P1= new Passenger(passportNumber, firstName,  lastName, flightNo);

            String S= "PP NO. " + P1.GetPassportNumber() + " NAME: " + P1.GetFirstName().substring(0,1) + "." + P1.GetLastName() + " LUGGAGE: " + P1.GetNumLuggage();
            return S;

        }


}
