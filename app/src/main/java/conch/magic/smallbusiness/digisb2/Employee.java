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
    private String monday, tuesday, wednesday, thursday, friday;

    //Constructor
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

    String getName(){ return name;}
    double getPay(){ return pay;}
    String getMonday(){ return monday;}
    String getTuesday(){ return tuesday;}
    String getWednesday(){ return wednesday;}
    String getThursday(){ return thursday;}
    String getFriday(){ return friday;}
}
