package conch.magic.smallbusiness.digisb2;

/**
 * Created by Michael on 3/5/2015.
 */
import android.app.Activity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.CheckedTextView;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MyExpandableListAdapter extends BaseExpandableListAdapter {

    private final SparseArray<Group> groups;
    public LayoutInflater inflater;
    public Activity activity;

    public MyExpandableListAdapter(Activity act, SparseArray<Group> groups) {
        activity = act;
        this.groups = groups;
        inflater = act.getLayoutInflater();
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return groups.get(groupPosition).children.get(childPosition);
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return 0;
    }

    @Override
    public View getChildView(int groupPosition, final int childPosition,
                             boolean isLastChild, View convertView, ViewGroup parent) {
        final GroupItem children = (GroupItem) getChild(groupPosition, childPosition);
        TextView text = null;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.list_row_details, null);
        }
        text = (TextView) convertView.findViewById(R.id.nameView);
        text.setText(children.getName());

        final EditText count = (EditText)convertView.findViewById(R.id.countView);
        count.setTag(children);
        count.addTextChangedListener(new TextWatcher() {

            public void afterTextChanged(Editable s) {

                InventoryGroupItem i = (InventoryGroupItem)count.getTag();
                i.setValue(Integer.parseInt(count.getText().toString()));
            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
        });
        Button inc = (Button)convertView.findViewById(R.id.inc);
        inc.setTag(children);
        Button dec = (Button)convertView.findViewById(R.id.dec);
        dec.setTag(children);
        count.setText("" + children.getValue());
        convertView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(activity, children.getName(),
                        Toast.LENGTH_SHORT).show();
            }
        });

        convertView.setTag(children);

        return convertView;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return groups.get(groupPosition).children.size();
    }

    public void inc(Button button){
        InventoryGroupItem item = (InventoryGroupItem) button.getTag();
        item.increase();
        button.setText( "" + item.getValue() );
    }
    public void dec(Button button){
        InventoryGroupItem item = (InventoryGroupItem) button.getTag();
        item.decrease();
        button.setText( "" + item.getValue() );
    }

    @Override
    public Object getGroup(int groupPosition) {
        return groups.get(groupPosition);
    }

    @Override
    public int getGroupCount() {
        return groups.size();
    }

    @Override
    public void onGroupCollapsed(int groupPosition) {
        super.onGroupCollapsed(groupPosition);
    }

    @Override
    public void onGroupExpanded(int groupPosition) {
        super.onGroupExpanded(groupPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return 0;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded,
                             View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.list_row_group, null);
        }
        Group group = (Group) getGroup(groupPosition);
        ((CheckedTextView) convertView).setText(group.string);
        ((CheckedTextView) convertView).setChecked(isExpanded);
        return convertView;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }
}
