package conch.magic.smallbusiness.digisb2;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;


public class ExpenseActivity extends Activity {

    private static ExpenseListAdapter mAdapter;
    private static ArrayList<ExpenseObject> expenseList;
    private static ListView lv;
    private static Context context;
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
                //ExpenseObject toAdd = new ExpenseObject("TEST ADD", 69.69);

                LayoutInflater li = LayoutInflater.from(context);
                View promptsView = li.inflate(R.layout.expense_add_dialog, null);
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setView(promptsView);
                //builder.setTitle("Add New Expense");

                // Set up the input
                final EditText inputName = (EditText) promptsView.findViewById(R.id.expense_dialog_name_input);
                final EditText inputAmount = (EditText) promptsView.findViewById(R.id.expense_dialog_amount_input);

                // Set up the buttons
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String userInputName = inputName.getText().toString();
                        double userInputAmount = Double.parseDouble(inputAmount.getText().toString());
                        ExpenseObject toAdd = new ExpenseObject(userInputName, userInputAmount);
                        addNewExpense(toAdd);
                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                builder.show();
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
