package conch.magic.smallbusiness.digisb2;

/**
 * Created by Michael on 3/5/2015.
 */
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.CheckedTextView;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.TextView;
import java.util.HashMap;

/* Manages the Inventory List */

public class MyExpandableListAdapter extends BaseExpandableListAdapter {
    private final HashMap<Integer, Group> groups; //Holds Category
    public LayoutInflater inflater;
    public Activity activity;

    //Constructor
    public MyExpandableListAdapter(Activity act, HashMap<Integer,Group> groups) {
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
    /* Get and setup the actual inventory item in a group */
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
                if (count.getTag() == null) return;
                GroupItem i = (GroupItem)count.getTag();
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

                AlertDialog.Builder builder = new AlertDialog.Builder(activity);
                builder.setTitle("Change Inventory Name");

        // Set up the input
                final EditText convertInput = new EditText(activity);
                convertInput.setTag(v.findViewById(R.id.nameView));
        // Specify the type of input expected; this, for example, sets the input as a password, and will mask the text
                convertInput.setInputType(InputType.TYPE_CLASS_TEXT);
                builder.setView(convertInput);

        // Set up the buttons
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        ((TextView)convertInput.getTag()).setText(convertInput.getText().toString());
                        children.name = convertInput.getText().toString();
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

        convertView.setTag(children);

        return convertView;
    }
    /* Get and setup the category */
    @Override
    public View getGroupView(int groupPosition, boolean isExpanded,
                             View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.list_row_group, null);
        }
        convertView.setTag(new Integer(groupPosition));
        Group group = (Group) getGroup(groupPosition);

        Button plusButton = (Button)convertView.findViewById(R.id.addToCat);
        plusButton.setTag(group);
        plusButton.setOnClickListener(new OnClickListener() {
                                          @Override
                                          public void onClick(View v) {
                                              ((Group)((Button)v).getTag()).children.add(new GroupItem("New Item", ((Group)((Button)v).getTag()).children.size() ));
                                              notifyDataSetChanged();
                                              ((ExpandableListView)activity.findViewById(R.id.listView)).expandGroup(((Group)((Button)v).getTag()).row);
                                          }
                                      }

        );

        convertView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                ExpandableListView listView = (ExpandableListView) activity.findViewById(R.id.listView);
                System.out.println(listView);
                CheckedTextView ctv = (CheckedTextView)v.findViewById(R.id.categoryNameView);
                System.out.println(ctv);
                if (!ctv.isChecked())
                    listView.expandGroup((int)v.getTag());
                else listView.collapseGroup((int)v.getTag());
            }
        });
        CheckedTextView checkedView = (CheckedTextView)convertView.findViewById(R.id.categoryNameView);
        checkedView.setTag(group);
        checkedView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(activity);
                builder.setTitle("Change Category Name");

// Set up the input
                final EditText input = new EditText(activity);
                input.setTag(v);
// Specify the type of input expected; this, for example, sets the input as a password, and will mask the text
                input.setInputType(InputType.TYPE_CLASS_TEXT);
                builder.setView(input);

// Set up the buttons
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        ((CheckedTextView) input.getTag()).setText(input.getText().toString());
                        ((Group) ((CheckedTextView) input.getTag()).getTag()).string = input.getText().toString();
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
        checkedView.setText(group.string);
        checkedView.setChecked(isExpanded);
        return convertView;
    }
    @Override
    public int getChildrenCount(int groupPosition) {
        return groups.get(groupPosition).children.size();
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
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }
}
