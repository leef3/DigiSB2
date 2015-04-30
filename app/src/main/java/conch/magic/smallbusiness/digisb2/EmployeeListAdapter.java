package conch.magic.smallbusiness.digisb2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Fred Lee on 4/30/2015.
 */
public class EmployeeListAdapter extends BaseAdapter
{
    Context context;
    protected ArrayList<Employee> employeeList;
    LayoutInflater inflater;

    public EmployeeListAdapter(Context context, ArrayList<Employee> employeeList) {
        this.employeeList = employeeList;
        this.inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.context = context;
    }

    public int getCount() {
        return employeeList.size();
    }

    public Employee getItem(int position) {
        return employeeList.get(position);
    }
    public long getItemId(int position)
    {
        return position;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = this.inflater.inflate(R.layout.employee_list_item, parent, false);

            holder.pay = (TextView) convertView.findViewById(R.id.employee_list_item_pay);
            holder.name = (TextView) convertView.findViewById(R.id.employee_list_item_name);

            holder.monday = (TextView) convertView.findViewById(R.id.employee_list_item_monday);
            holder.tuesday = (TextView) convertView.findViewById(R.id.employee_list_item_tuesday);
            holder.wednesday = (TextView) convertView.findViewById(R.id.employee_list_item_wednesday);
            holder.thursday = (TextView) convertView.findViewById(R.id.employee_list_item_thursday);
            holder.friday = (TextView) convertView.findViewById(R.id.employee_list_item_friday);


            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        Employee employeeItem = employeeList.get(position);

        holder.pay.setText(Double.toString(employeeItem.getPay()));
        holder.name.setText(employeeItem.getName());

        holder.monday.setText(employeeItem.getMonday());
        holder.tuesday.setText(employeeItem.getTuesday());
        holder.wednesday.setText(employeeItem.getWednesday());
        holder.thursday.setText(employeeItem.getThursday());
        holder.friday.setText(employeeItem.getFriday());


        /*
        if(employeeItem.getMonday())
        {
            //holder.monday.setVisibility(View.INVISIBLE);
            holder.monday.setText("M");
        }
        if(employeeItem.getTuesday())
        {
            //holder.tuesday.setVisibility(View.INVISIBLE);
            holder.monday.setText("T");
        }
        if(employeeItem.getWednesday())
        {
           // holder.wednesday.setVisibility(View.INVISIBLE);
            holder.wednesday.setText("W");
        }
        if(employeeItem.getThursday())
        {
           // holder.thursday.setVisibility(View.INVISIBLE);
            holder.thursday.setText("R");
        }
        if(employeeItem.getFriday())
        {
          //  holder.friday.setVisibility(View.INVISIBLE);
            holder.friday.setText("F");
        }
*/


        return convertView;
    }

    private class ViewHolder {
        TextView pay;
        TextView name;
        TextView monday;
        TextView tuesday;
        TextView wednesday;
        TextView thursday;
        TextView friday;
    }

}