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
import android.widget.ToggleButton;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Fred on 4/30/2015.
 */

public class EmployeeActivity extends Activity {

    private static EmployeeListAdapter mAdapter;
    private static ArrayList<Employee> employeeList;
    private static ListView lv;
    private static Context context;
    private static int toRemoveId;

    public static final String EMPLOYEE_SAVE_NAME = "DIGI_SB_2_EMPLOYEE_SAVE_NAME";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.employee_main);
        context = this;

        employeeList = new ArrayList<Employee>();
        loadData();


        //Create the custom list adapter and populate it with the arraylist
        lv = (ListView)  findViewById(R.id.employee_list);
        mAdapter = new EmployeeListAdapter(this, employeeList);
        lv.setAdapter(mAdapter);

        //OnItemLongClick used for deleting. Creates a dialog to confirm
        lv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener()
        {
            public boolean onItemLongClick(AdapterView parent, View view, int position, long id) {
                toRemoveId = position;
                AlertDialog.Builder confirmBuilder = new AlertDialog.Builder(EmployeeActivity.this);
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

        //Button for Adding an employee.
        final Button addButton = (Button) findViewById(R.id.add_employee_button);
        addButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                LayoutInflater li = LayoutInflater.from(context);
                View promptsView = li.inflate(R.layout.employee_add_dialog, null);
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setView(promptsView);

                // Set up the input
                final EditText inputName = (EditText) promptsView.findViewById(R.id.employee_dialog_name_input);
                final EditText inputPay = (EditText) promptsView.findViewById(R.id.employee_dialog_pay_input);

                //Booleans for Days

                final ToggleButton mondayToggle = (ToggleButton) promptsView.findViewById(R.id.employee_dialog_monday);
                final ToggleButton tuesdayToggle = (ToggleButton) promptsView.findViewById(R.id.employee_dialog_tuesday);
                final ToggleButton wednesdayToggle = (ToggleButton) promptsView.findViewById(R.id.employee_dialog_wednesday);
                final ToggleButton thursdayToggle = (ToggleButton) promptsView.findViewById(R.id.employee_dialog_thursday);
                final ToggleButton fridayToggle = (ToggleButton) promptsView.findViewById(R.id.employee_dialog_friday);

                // Set up the buttons
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String userInputName = inputName.getText().toString();
                        DecimalFormat decFormat = new DecimalFormat("0.00");

                        double userInputAmount = Double.parseDouble(decFormat.format(Double.parseDouble(inputPay.getText().toString())));
                        Employee toAdd = new Employee(userInputName, userInputAmount, mondayToggle.isChecked(), tuesdayToggle.isChecked(), wednesdayToggle.isChecked(), thursdayToggle.isChecked(), fridayToggle.isChecked());
                        addNewEmployee(toAdd);
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
    }
    //Remove from List
    public static void removeItem()
    {
        employeeList.remove(toRemoveId);
        mAdapter.notifyDataSetChanged();
    }

    //Adds new employee
    public static void addNewEmployee(Employee toAdd)
    {
        employeeList.add(0, toAdd);
        mAdapter.notifyDataSetChanged();
    }

    void loadData() {
        SharedPreferences settings = this.getPreferences(MODE_PRIVATE);
        String objectData = settings.getString(EMPLOYEE_SAVE_NAME, "");
        if (!objectData.equals("")) {
            System.out.println("Object Data: " + objectData);
            Gson gson = new Gson();
            Type collectionType = new TypeToken<ArrayList<Employee>>() {
            }.getType();
            JsonArray jArray = new JsonParser().parse(objectData).getAsJsonArray();
            for (JsonElement e : jArray) {
                Employee c = gson.fromJson(e, Employee.class);
                employeeList.add(c);
            }
        }
    }

    @Override protected void onPause(){
        super.onPause();
        SharedPreferences.Editor settings = this.getPreferences(MODE_PRIVATE).edit();
        String data = new Gson().toJson(employeeList);
        System.out.println("Data!: " + data);
        settings.putString(EMPLOYEE_SAVE_NAME, data);
        settings.commit();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.menu_employee, menu);
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
