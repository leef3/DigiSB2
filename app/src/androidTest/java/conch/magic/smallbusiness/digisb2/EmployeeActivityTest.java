package conch.magic.smallbusiness.digisb2;

import android.content.Intent;
import android.net.Uri;
import android.test.ActivityUnitTestCase;
import android.test.suitebuilder.annotation.SmallTest;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

/**
 * <a href="http://d.android.com/tools/testing/testing_android.html">Testing Fundamentals</a>
 */
public class EmployeeActivityTest extends ActivityUnitTestCase<EmployeeActivity> {
    private EmployeeActivity activity;

    public EmployeeActivityTest() {
        super(EmployeeActivity.class);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        Intent intent = new Intent(getInstrumentation().getTargetContext(),
                EmployeeActivityTest.class);
        startActivity(intent, null, null);
        activity = getActivity();
        testEmployeeClass();
    }
    @SmallTest

    void testEmployeeClass(){

        Employee emp = new Employee("Mikey", 10.00, "m","","w","t","");
        assert(emp.getPay() > 9.99 && emp.getPay() < 10.01);
        assert(emp.getName().equals("Mikey"));
    }

    


}