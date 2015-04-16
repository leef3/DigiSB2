package conch.magic.smallbusiness.digisb2;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.math.RoundingMode;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Random;


public class ExpenseActivity extends Activity {

    private static ExpenseListAdapter mAdapter;
    private static ArrayList<ExpenseObject> expenseList;
    private static ListView lv;
    Context context;
    private static double expenseTotal;
    private static TextView totalExpense;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.expense_main);
        context = this;

        expenseList = new ArrayList<ExpenseObject>();
        fillExpenseList();

        System.out.println(expenseList.size());
        lv = (ListView)  findViewById(R.id.test_list);

        totalExpense = (TextView) findViewById(R.id.expense_total);
        totalExpense.setText(Double.toString(expenseTotal));
        mAdapter = new ExpenseListAdapter(this, expenseList);
        lv.setAdapter(mAdapter);

        final Button addButton = (Button) findViewById(R.id.add_expense_button);
        addButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                ExpenseObject toAdd = new ExpenseObject("TEST ADD EXPENSE", 69.69);
                addNewExpense(toAdd);
            }
        });

        final Button resetButton = (Button) findViewById(R.id.reset_expense_button);
        resetButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                clearExpenseList();
            }
        });

    }

    public static void fillExpenseList()
    {
        Random rand = new Random();
        for(int x = 0; x < 25; x++)
        {
            double nextNum = rand.nextDouble() * 100;
            ExpenseObject test = new ExpenseObject("Expense" + x, nextNum);
            expenseList.add(test);
            expenseTotal = expenseTotal + test.getAmount();
        }
    }
    public static void addNewExpense(ExpenseObject toAdd)
    {
        expenseList.add(0, toAdd);
        expenseTotal = expenseTotal + toAdd.getAmount();
        totalExpense.setText(Double.toString(expenseTotal));
        mAdapter.notifyDataSetChanged();
    }
    public static void clearExpenseList()
    {
        expenseList.clear();
        expenseTotal = 0.00;
        totalExpense.setText(Double.toString(expenseTotal));
        mAdapter.notifyDataSetChanged();
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
