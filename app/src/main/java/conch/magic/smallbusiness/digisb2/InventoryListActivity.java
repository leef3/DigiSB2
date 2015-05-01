package conch.magic.smallbusiness.digisb2;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.SparseArray;
import android.view.View;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.TableRow;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;


/**
    Activity for Inventory Screen
 */
//Main activity for InventoryList
public class InventoryListActivity extends Activity {
    //Stores the list of Groups (Inventory Categories)
    HashMap<Integer, Group> groups = new HashMap<Integer, Group>();

    //Key for saving to SharedPreferences
    public static final String GROUP_SAVE_NAME = "GROUPDATA";

    //Adapter for showing the Groups
    public static MyExpandableListAdapter adapter;

    @Override protected void onCreate(Bundle state) {
        super.onCreate(state);
        setContentView(R.layout.list);
        loadData(); // Read data from document store

        //Find the UI element for the Listview and set the adapter to it to populate
        ExpandableListView listView = (ExpandableListView) findViewById(R.id.listView);
        MyExpandableListAdapter adapter = new MyExpandableListAdapter(this,
                groups);
        listView.setAdapter(adapter);
        this.adapter = adapter;
    }

    @Override protected void onResume(){
        super.onResume();
    }
    // Increase an inventory Item supply
    public void inc(View view){
        GroupItem item = (GroupItem)view.getTag();
        item.increase();
        TableRow r = (TableRow)view.getParent();
        TextView v = (TextView) r.getChildAt(1);
        v.setText("" + item.getValue());
    }
    // Decrease an inventory Item supply
    public void dec(View view){
        GroupItem item = (GroupItem)view.getTag();
        item.decrease();
        TableRow r = (TableRow)view.getParent();
        TextView v = (TextView) r.getChildAt(1);
        v.setText("" + item.getValue());
    }

    // OnPause saves data -> document store
    @Override protected void onPause(){
        super.onPause();
        SharedPreferences.Editor settings = this.getPreferences(MODE_PRIVATE).edit();
        String data = new Gson().toJson(groups);
        System.out.println("Data!: " + data);
        settings.putString(GROUP_SAVE_NAME, data);
        settings.commit();
    }

    // Load data from document store
    void loadData() {
        SharedPreferences settings = this.getPreferences(MODE_PRIVATE);
        String objectData = settings.getString(GROUP_SAVE_NAME, "");
        if (!objectData.equals("")){
            groups.clear();
            System.out.println("Object Data: " + objectData);
            Gson gson = new Gson();
            Type collectionType = new TypeToken<HashMap<Integer, Group>>() { }.getType();
            HashMap<Integer, Group> m = gson.fromJson(objectData, collectionType);
            groups.clear();
            System.out.println(m);
            for (Map.Entry<Integer, Group> e : m.entrySet()){
                groups.put(e.getKey(), e.getValue());
            }
        }

    }

    //Button Method for adding a new Group to the list of groups, users can change the name later but by default it is "New Category"
    public void addNewCategory(View v){
        Group g = new Group("New Category", groups.size());
        groups.put(groups.size(), g);
        adapter.notifyDataSetChanged();
    }
}
