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
import android.widget.Toast;

import java.util.ArrayList;


/**
 * Created by user on 05/03/2017.
 */

public class StudentAdapter extends BaseAdapter{

    ArrayList<String> data;
    Context context;
    LayoutInflater layoutInflater;
    StudentDB db;


    public StudentAdapter(Context context) {
        super();
        Log.d("DEBUG", "StudentAdapter initialised");

        this.context = context;
        layoutInflater = LayoutInflater.from(context);
        db = new StudentDB(this.context);
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Log.d("DEBUG", "Trying to get view number: " + Integer.toString(position));
        View rowView = layoutInflater.inflate(R.layout.student_view, parent, false);
        TextView textView = (TextView) rowView.findViewById(R.id.txtName);
        CheckBox attendanceCheckbox = (CheckBox) rowView.findViewById(R.id.chkAttended);
        //textView.setText(data.get(position));
        Log.d("DEBUG", "getting for position " + position);

        Student s = db.getStudent((int) getItemId(position));
        textView.setText(s.getName());
        attendanceCheckbox.setChecked(s.getAttended());

        //attendanceCheckbox.setChecked(false);

        rowView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Toast.makeText(v.getContext(), "Long press",
                        Toast.LENGTH_LONG).show();
                return true;
            }
        });

        return rowView;
    }

    @Override
    public int getCount() {
        return db.getAllStudents().size();
    }

    public Student getItem(int position)
    {
        return db.getAllStudents().get(position);
    }

    @Override
    public long getItemId(int position) {

        return db.getAllStudents().get(position).getID();
    }


    @Override
    public boolean hasStableIds() {
        return true;
    }

}
