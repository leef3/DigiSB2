package conch.magic.smallbusiness.digisb2;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.provider.ContactsContract.Contacts;
import android.provider.ContactsContract.CommonDataKinds.Email;
import android.util.Log;

import java.lang.reflect.Type;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;

/**
 * Created by Moo on 4/8/15.
 */
public class ContactListActivity extends Activity {

    private static final int CONTACT_PICKER_RESULT = 1001;
    private static final String DEBUG_TAG = "Digisb2";
    public static final String CONTACT_SAVE_NAME = "CONTACT_LIST_DATA";
    public static final int ADD_NEW_CONTACT = 100;
    private HashSet<Contact> contacts;

    public void doLaunchContactPicker(View view) {
        Intent contactPickerIntent = new Intent(Intent.ACTION_PICK,
                Contacts.CONTENT_URI);
        startActivityForResult(contactPickerIntent, CONTACT_PICKER_RESULT);

    }

    @Override protected void onPause(){
        super.onPause();
        SharedPreferences.Editor settings = this.getPreferences(MODE_PRIVATE).edit();
        String data = new Gson().toJson(contacts);
        System.out.println("Data!: " + data);
        settings.putString(CONTACT_SAVE_NAME, data);
        settings.commit();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_list);
        contacts = new HashSet<Contact>();
        loadData();
    }

    void loadData(){
        SharedPreferences settings = this.getPreferences(MODE_PRIVATE);
        String objectData = settings.getString(CONTACT_SAVE_NAME, "");
        if (!objectData.equals("")){
            System.out.println("Object Data: " + objectData);
            Gson gson = new Gson();
            Type collectionType = new TypeToken<HashSet<Contact>>() { }.getType();
            JsonArray jArray = new JsonParser().parse(objectData).getAsJsonArray();
            HashSet<Contact> g = new HashSet<Contact>();
            for (JsonElement e : jArray){
                Contact c = gson.fromJson(e, Contact.class);
                contacts.add(c);
                addContactToTable(c);

            }
        }
        else { System.out.println("No Objects!"); }
    }

    public boolean displayContactPicker(View b){
        doLaunchContactPicker(null);
        return true;
    }

    void addContactToTable(Contact c){
        TableLayout table = (TableLayout) findViewById(R.id.contact_table);
        TableRow row = new TableRow(this);
        TextView nameView = new TextView(this);
        nameView.setText(c.name);

        row.setBackgroundColor(0xFFFFFF);
        row.setPadding(10,10,10,10);
        row.setTag(c);

        row.addView(nameView);
        row.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Contact c = (Contact)v.getTag();
                Intent editIntent = new Intent(Intent.ACTION_VIEW);
                editIntent.setDataAndType(c.getUri(),Contacts.CONTENT_ITEM_TYPE);
                startActivity(editIntent);
            }
        });
        table.addView(row);
    }

    // Called from the contactPicker Intent when it finds a contact.
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            switch (requestCode) {

                case ADD_NEW_CONTACT:
                    Uri contactData = data.getData();
                    System.out.println(contactData);
                    Cursor cur =  getContentResolver().query(contactData, null, null, null, null);
                    System.out.println(cur);

                    if (cur.moveToFirst()) {
                        System.out.println(cur);
                        String name = cur.getString(cur.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
                        Contact c = new Contact(name, contactData);
                        if (!contacts.contains(c))
                            contacts.add(c);
                        addContactToTable(c);
                        finish();
                    }
                    break;


                case CONTACT_PICKER_RESULT:
                    Cursor cursor = null;
                    String name = "";
                        try {
                            Uri result = data.getData();
                            String id = result.getLastPathSegment();

                            //Get Name
                            cursor = getContentResolver().query(result, null, null, null, null);
                            if (cursor.moveToFirst()) {
                                name = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
                                String ident = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts._ID));
                                Contact c = new Contact(name, result);
                                if (!contacts.contains(c))
                                    contacts.add(c);
                                    addContactToTable(c);

                            } }
                        catch(Exception e){
                        }
                    break;
                  }

        } else {
            Log.w(DEBUG_TAG, "Warning: activity result not ok");
            System.out.println(resultCode);
        }
    }

    // Button OnClickHandler in interface for adding new contact.
    public void onCreateContact(View v){
        Intent contactInserter = new Intent(Intent.ACTION_INSERT,Contacts.CONTENT_URI);

        startActivityForResult(contactInserter, ADD_NEW_CONTACT);

    }



}
