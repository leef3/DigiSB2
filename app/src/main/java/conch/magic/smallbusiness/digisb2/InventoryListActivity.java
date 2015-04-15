package conch.magic.smallbusiness.digisb2;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.SparseArray;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.TableRow;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


/**
 * Created by Moo on 3/19/15.
 */
public class InventoryListActivity extends Activity {
    SparseArray<Group> groups = new SparseArray<Group>();
    public static final String GROUP_SAVE_NAME = "GROUPDATA";
    @Override protected void onCreate(Bundle state){
        super.onCreate(state);
        setContentView(R.layout.list);
        createData();
        onResume();
        ExpandableListView listView = (ExpandableListView) findViewById(R.id.listView);
        MyExpandableListAdapter adapter = new MyExpandableListAdapter(this,
                groups);
        listView.setAdapter(adapter);
    }

    @Override protected void onResume(){
        super.onResume();
        loadData();
    }

    public void inc(View view){
        InventoryGroupItem item = (InventoryGroupItem)view.getTag();
        item.increase();
        TableRow r = (TableRow)view.getParent();
        TextView v = (TextView) r.getChildAt(1);
        v.setText("" + item.getValue());
    }

    public void dec(View view){
        InventoryGroupItem item = (InventoryGroupItem)view.getTag();
        item.decrease();
        TableRow r = (TableRow)view.getParent();
        TextView v = (TextView) r.getChildAt(1);
        v.setText("" + item.getValue());
    }

    public void createData() {
        for (int j = 0; j < 5; j++) {
            Group group = new Group("Test " + j, j);
            for (int i = 0; i < 5; i++) {
                InventoryGroupItem item = new InventoryGroupItem("Sub Item" + i, i);
                group.children.add(item);
            }
            groups.append(j, group);
        }
    }



    @Override protected void onPause(){
        super.onPause();
        SharedPreferences.Editor settings = this.getPreferences(MODE_PRIVATE).edit();
        String data = new Gson().toJson(groups);
        System.out.println("Data!: " + data);
        settings.putString(GROUP_SAVE_NAME, data);
        settings.commit();
    }

    void loadData(){
/*
        SharedPreferences settings = this.getPreferences(MODE_PRIVATE);
        String objectData = settings.getString(GROUP_SAVE_NAME, "");
        if (!objectData.equals("")){
            System.out.println("Object Data: " + objectData);
            System.out.println("{" +"{" + objectData.substring(objectData.indexOf("[{\"children"), objectData.indexOf(",\"mGarbage")));
            Gson gson = new Gson();
            Type collectionType = new TypeToken<Collection<Group>>(){}.getType();
            Collection<Group> g = gson.fromJson("{" + objectData.substring(objectData.indexOf("[{\"children"), objectData.indexOf(",\"mGarbage")) + "}", collectionType);
            System.out.println(g);
            for (Group group : g){
                groups.append(groups.size(), group);
            }
        }
        else { System.out.println("No Objects!"); }
    }

*/
}
