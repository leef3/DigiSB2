package conch.magic.smallbusiness.digisb2;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by Fred Lee on 4/30/2015.
 */
public class Employee
{
    private String name;
    private double pay;
    private boolean monday, tuesday, wednesday, thursday, friday;

    //Constructor
    Employee(String name, double pay, boolean mon, boolean tues, boolean wed, boolean thurs, boolean fri)
    {
        this.name = name;
        this.pay = pay;
        this.monday = mon;
        this.tuesday = tues;
        this.wednesday = wed;
        this.thursday = thurs;
        this.friday = fri;
    }

    String getName(){ return name;}
    double getPay(){ return pay;}
    boolean getMonday(){ return monday;}
    boolean getTuesday(){ return tuesday;}
    boolean getWednesday(){ return wednesday;}
    boolean getThursday(){ return thursday;}
    boolean getFriday(){ return friday;}
}
