package example.codeclan.com.handzup;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by user on 05/03/2017.
 */

public class StudentAdapter extends BaseAdapter{

    ArrayList<String> data;
    Context context;
    LayoutInflater layoutInflater;


    public StudentAdapter(Context context, ArrayList<String> values) {
        super();
        Log.d("DEBUG", "StudentAdapter initialised");

        this.context = context;
        this.data = values;
        layoutInflater = LayoutInflater.from(context);
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Log.d("DEBUG", "Trying to get view number: " + Integer.toString(position));
        View rowView = layoutInflater.inflate(R.layout.student_view, parent, false);
        TextView textView = (TextView) rowView.findViewById(R.id.txtName);
        CheckBox attendanceCheckbox = (CheckBox) rowView.findViewById(R.id.chkAttended);
        textView.setText(data.get(position));

        attendanceCheckbox.setChecked(false);

        return rowView;
    }

    @Override
    public int getCount() {
        return this.data.size();
    }

    public Object getItem(int position)
    {
        return position;
    }

    @Override
    public long getItemId(int position) {

        return position;
    }


    @Override
    public boolean hasStableIds() {
        return true;
    }

}
