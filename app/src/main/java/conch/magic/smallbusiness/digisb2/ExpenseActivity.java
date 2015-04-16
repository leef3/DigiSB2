package conch.magic.smallbusiness.digisb2;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Fred on 4/15/2015.
 */

public class ExpenseActivity extends Activity {

    private static ExpenseListAdapter mAdapter;
    private static ArrayList<ExpenseObject> expenseList;
    private static ListView lv;
    private static Context context;
    private static double expenseTotal;
    private static TextView totalExpense;

    private static int toRemoveId;

    public static final String EXPENSE_SAVE_NAME = "DIGI_SB_2_EXPENSE_SAVE_NAME";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.expense_main);
        context = this;

        expenseList = new ArrayList<ExpenseObject>();
        //fillExpenseList calls loadData() but was originally used to generate test objects
        //fillExpenseList();
        loadData();

        //System.out.println(expenseList.size());
        //Create the custom list adapter and populate it with the arraylist
        lv = (ListView)  findViewById(R.id.test_list);
        totalExpense = (TextView) findViewById(R.id.expense_total);
        totalExpense.setText(Double.toString(expenseTotal));
        mAdapter = new ExpenseListAdapter(this, expenseList);
        lv.setAdapter(mAdapter);

        //OnItemLongClick used for deleting. Creates a dialog to confirm
        lv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener()
        {
            public boolean onItemLongClick(AdapterView parent, View view, int position, long id) {
                toRemoveId = position;
                AlertDialog.Builder confirmBuilder = new AlertDialog.Builder(ExpenseActivity.this);
                confirmBuilder.setMessage("Confirm Delete");
                // Set up the buttons
                confirmBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                       removeItem();
                    }
                });
                confirmBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                confirmBuilder.show();
                return true;
            }
        });

        //Button for Adding and expense. Opens up a dialog with layout expense_add_dialog.xml
        //User enters Name of Expense and Amount
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

        //Reset button clears the list of expenses
        final Button resetButton = (Button) findViewById(R.id.reset_expense_button);
        resetButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                clearExpenseList();
            }
        });
    }
    //Remove from List and subtract from total expense owed
    public static void removeItem()
    {
        expenseTotal = expenseTotal - expenseList.get(toRemoveId).getAmount();
        expenseList.remove(toRemoveId);
        totalExpense.setText(Double.toString(expenseTotal));
        mAdapter.notifyDataSetChanged();
    }
    //Used for testing but now calls loadData for loading stored info
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
    //Adds new expense object to arraylist and adds it to total expense
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

    void loadData() {
        SharedPreferences settings = this.getPreferences(MODE_PRIVATE);
        String objectData = settings.getString(EXPENSE_SAVE_NAME, "");
        if (!objectData.equals("")) {
            System.out.println("Object Data: " + objectData);
            Gson gson = new Gson();
            Type collectionType = new TypeToken<ArrayList<ExpenseObject>>() {
            }.getType();
            JsonArray jArray = new JsonParser().parse(objectData).getAsJsonArray();
            for (JsonElement e : jArray) {
                ExpenseObject c = gson.fromJson(e, ExpenseObject.class);
                expenseList.add(c);
                expenseTotal = expenseTotal + c.getAmount();
            }
        }
    }

    @Override protected void onPause(){
        super.onPause();
        SharedPreferences.Editor settings = this.getPreferences(MODE_PRIVATE).edit();
        String data = new Gson().toJson(expenseList);
        System.out.println("Data!: " + data);
        settings.putString(EXPENSE_SAVE_NAME, data);
        settings.commit();
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
