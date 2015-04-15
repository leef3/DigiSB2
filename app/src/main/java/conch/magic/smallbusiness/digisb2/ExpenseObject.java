package conch.magic.smallbusiness.digisb2;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by Fred Lee on 4/14/2015.
 */
public class ExpenseObject
{
    String date, name;
    float amount;
    //CONSTRUCTOR USED LATER ON IF NEED TO ADD CUSTOM DATE
    ExpenseObject(String date, String name, float amount)
    {
        this.date = date;
        this.name = name;
        this.amount = amount;
    }
    //CONSTRUCTOR USED FOR SIMPLER VERSION WHERE DATE = DATE/TIME ADDED
    ExpenseObject(String name, float amount)
    {
        Calendar c = Calendar.getInstance();
        SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
        this.date = df.format(c.getTime());
        this.name = name;
        this.amount = amount;
    }

    String getName(){ return name;}
    float getAmount(){ return amount;}
    String getDate(){ return date;}
}
