package conch.magic.smallbusiness.digisb2;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class ExpenseActivity extends Activity {

    private static ExpenseListAdapter mAdapter;
    private static ArrayList<ExpenseObject> expenseList;
    ListView lv;
    Context context;
    private static float expenseTotal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.expense_main);
        context = this;

        expenseList = new ArrayList<ExpenseObject>();
        fillExpenseList();

        System.out.println(expenseList.size());
        lv = (ListView)  findViewById(R.id.test_list);

        TextView totalExpense = (TextView) findViewById(R.id.expense_total);
        totalExpense.setText(Float.toString(expenseTotal));
        ExpenseListAdapter mAdapter = new ExpenseListAdapter(this, expenseList);
        lv.setAdapter(mAdapter);

    }

    public static void fillExpenseList()
    {
        Random rand = new Random();
        for(int x = 0; x < 25; x++)
        {
            float nextNum = rand.nextFloat() * 100;
            ExpenseObject test = new ExpenseObject("Expense" + x, nextNum);
            expenseList.add(test);
            expenseTotal = expenseTotal + nextNum;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_expense, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
