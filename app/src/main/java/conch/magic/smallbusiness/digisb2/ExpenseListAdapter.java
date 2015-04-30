package conch.magic.smallbusiness.digisb2;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Fred Lee on 4/14/2015.
 */
public class ExpenseListAdapter extends BaseAdapter
{
    Context context;
    protected ArrayList<ExpenseObject> expenseList;
    LayoutInflater inflater;

    public ExpenseListAdapter(Context context, ArrayList<ExpenseObject> expenseList) {
        this.expenseList = expenseList;
        this.inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.context = context;
    }
    public void checkRecurring()
    {
        //CALCULATE DATE DIFFERENCE WHETHER TO ADD OR NOT
    }

    public int getCount() {
        return expenseList.size();
    }

    public ExpenseObject getItem(int position) {
        return expenseList.get(position);
    }
    public long getItemId(int position)
    {
        return position;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = this.inflater.inflate(R.layout.expense_list_item, parent, false);

            holder.date = (TextView) convertView.findViewById(R.id.expense_list_item_date);
            holder.name = (TextView) convertView.findViewById(R.id.expense_list_item_name);
            holder.amount = (TextView) convertView.findViewById(R.id.expense_list_item_amount);
            holder.amount.setTextColor(Color.RED);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        ExpenseObject expenseItem = expenseList.get(position);
        holder.date.setText(expenseItem.getDate());
        holder.name.setText(expenseItem.getName());
        holder.amount.setText(Double.toString(expenseItem.getAmount()));

        return convertView;
    }

    private class ViewHolder {
        TextView date;
        TextView name;
        TextView amount;
    }

}