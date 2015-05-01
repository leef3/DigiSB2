package conch.magic.smallbusiness.digisb2;

import android.content.Intent;
import android.test.ActivityUnitTestCase;
import android.test.suitebuilder.annotation.SmallTest;

/**
 * <a href="http://d.android.com/tools/testing/testing_android.html">Testing Fundamentals</a>
 */
public class InventoryListActivityTest extends ActivityUnitTestCase<InventoryListActivity> {
    private InventoryListActivity activity;

    public InventoryListActivityTest() {
        super(InventoryListActivity.class);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();

        Intent intent = new Intent(getActivity().getApplicationContext(),
                InventoryListActivityTest.class);
        startActivity(intent, null, null);
        activity = getActivity();
        testAddAndIncrementInventory();
    }
    @SmallTest

    void testAddAndIncrementInventory(){

        Group group = new Group("Test Group", 0);
        GroupItem item = new GroupItem("Test Item",0);
        group.children.add(item);
        activity.groups.put(0, group);
        activity.adapter.notifyDataSetChanged();

        Group groupFromInterface = (Group)activity.adapter.getGroup(0);

        assert(groupFromInterface.string == "Test Group" && groupFromInterface.children.get(0).getName() == "Test Item");
        groupFromInterface.children.get(0).increase();
        assert(groupFromInterface.children.get(0).getValue() == 1);

    }

    


}