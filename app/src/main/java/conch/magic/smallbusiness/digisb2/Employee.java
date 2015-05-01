package conch.magic.smallbusiness.digisb2;


/**
 * Created by Fred Lee on 4/30/2015.
 */
public class Employee
{
    //Private variables
    private String name;
    private double pay;
    //Initially used Booleans but there was problems with overwriting so Strings used instead ("M", "T", "W", "R", "F",)
    private String monday, tuesday, wednesday, thursday, friday;

    //Employee Constructor
    Employee(String name, double pay, String mon, String tues, String wed, String thurs, String fri)
    {
        this.name = name;
        this.pay = pay;
        this.monday = mon;
        this.tuesday = tues;
        this.wednesday = wed;
        this.thursday = thurs;
        this.friday = fri;
    }

    //Getters for the private variables
    String getName(){ return name;}
    double getPay(){ return pay;}
    String getMonday(){ return monday;}
    String getTuesday(){ return tuesday;}
    String getWednesday(){ return wednesday;}
    String getThursday(){ return thursday;}
    String getFriday(){ return friday;}
}
