package conch.magic.smallbusiness.digisb2;

import android.app.Application;
import android.content.Intent;
import android.test.*;

import android.net.Uri;
import android.test.suitebuilder.annotation.SmallTest;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;


import conch.magic.smallbusiness.digisb2.*;

/**
 * <a href="http://d.android.com/tools/testing/testing_android.html">Testing Fundamentals</a>
 */
public class ContactListActivityTest extends ActivityUnitTestCase<ContactListActivity> {
    private ContactListActivity activity;

    public ContactListActivityTest() {
        super(ContactListActivity.class);
        setName("ContactListActivity");


    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        Intent intent = new Intent(getActivity().getApplicationContext(),
                ContactListActivity.class);

        startActivity(intent, null, null);
        activity = getActivity();
        testAddContact();
    }
    @SmallTest
    void testAddContact(){
        String name = "Mikey";
        Uri uri = Uri.parse("mailto:mikey@me.com");
        Contact c = new Contact(name,uri);

        activity.addContactToTable(c);
        TableLayout table = (TableLayout) activity.findViewById(R.id.contact_table);
        TableRow row = (TableRow)table.getChildAt(table.getChildCount() - 1);
        TextView text = (TextView)row.getChildAt(0);
        assert(text.getText().equals(name));

    }




}