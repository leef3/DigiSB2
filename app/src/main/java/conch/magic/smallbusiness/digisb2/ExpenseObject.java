package conch.magic.smallbusiness.digisb2;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by Fred Lee on 4/14/2015.
 */
public class ExpenseObject
{
    //Expense has date added, name which is to whom or for what, and amount which is a double
    private String date, name;
    private double amount;

    //CONSTRUCTOR
    ExpenseObject(String name, double amount)
    {
        //Convert Date to DD/MM/YYYY
        Calendar c = Calendar.getInstance();
        SimpleDateFormat dateForm = new SimpleDateFormat("dd-MM-yyyy");

        //Set the date to add date, name and amount
        this.date = dateForm.format(c.getTime());
        this.name = name;
        this.amount = amount;
    }

    //Getters (we dont need setters because that is done a single time in the constructor
    String getName(){ return name;}
    double getAmount(){ return amount;}
    String getDate(){ return date;}
}
