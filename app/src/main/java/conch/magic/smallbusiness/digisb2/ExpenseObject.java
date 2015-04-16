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
    private String date, name;
    private double amount;
    //CONSTRUCTOR USED LATER ON IF NEED TO ADD CUSTOM DATE
    ExpenseObject(String date, String name, double amount)
    {
        this.date = date;
        this.name = name;
        this.amount = amount;
    }
    //CONSTRUCTOR USED FOR SIMPLER VERSION WHERE DATE = DATE/TIME ADDED
    ExpenseObject(String name, double amount)
    {
        NumberFormat numberFormat = NumberFormat.getInstance();
        numberFormat.setRoundingMode(RoundingMode.HALF_UP);
        numberFormat.setMaximumFractionDigits(2);
        Calendar c = Calendar.getInstance();
        SimpleDateFormat dateForm = new SimpleDateFormat("dd-MM-yyyy");
        this.date = dateForm.format(c.getTime());
        this.name = name;
        this.amount = amount;
    }

    String getName(){ return name;}
    double getAmount(){ return amount;}
    String getDate(){ return date;}
}
