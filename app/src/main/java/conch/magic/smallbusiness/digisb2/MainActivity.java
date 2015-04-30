package conch.magic.smallbusiness.digisb2;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.view.MenuItem;
import android.content.Intent;

//Android Landing Page Main Activity - Only Purpose is to redirect to sub-menus via the buttons

public class MainActivity extends ActionBarActivity {

    //Needed for each activity
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    //Start the StockRoom Sub Menu
    public boolean OnStockRoomItemSelected(View item){
        startActivity(new Intent(MainActivity.this, InventoryListActivity.class));
        return true;
    }

    //Start the Contact List Sub Menu
    public boolean OnContactListItemSelected(View item){
        startActivity(new Intent(MainActivity.this, ContactListActivity.class));
        return true;
    }
    //Start the Expense List Sub Menu
    public boolean OnExpenseActivityItemSelected(View item){
        startActivity(new Intent(MainActivity.this, ExpenseActivity.class));
        return true;
    }
    //Start the Employee List Sub Menu
    public boolean OnEmployeeListItemSelected(View item){
        startActivity(new Intent(MainActivity.this, EmployeeActivity.class));
        return true;
    }
}
