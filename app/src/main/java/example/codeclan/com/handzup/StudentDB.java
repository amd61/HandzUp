package example.codeclan.com.handzup;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.icu.text.DateFormat;

import java.util.ArrayList;

/**
 * Created by user on 04/03/2017.
 */

public class StudentDB extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "StudentDB";
    private static final String TABLE_STUDENTS = "students";
    private static final String[] COLUMNS = {"id", "name", "attended"};


    public StudentDB(Context ctx) {
        super(ctx, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public void addStudent(Student newstudent) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("name", newstudent.getName());
        values.put("attended", newstudent.getAttended());

        db.insert(TABLE_STUDENTS, // table
                null,
                values); // the values
        db.close();
    }

    public Student getStudent(int id){

        // get db
        SQLiteDatabase db = this.getReadableDatabase();

        // make db query
        Cursor cursor =
                db.query(TABLE_STUDENTS, // table
                        COLUMNS, // column names we want to get back
                        " id = ?", // selection id
                        new String[] { String.valueOf(id) }, // the id
                        null, // group by
                        null, // having
                        null, // order by
                        null); // limit

        // get the first result
        if (cursor != null)
            cursor.moveToFirst();

        // make a student
        Student thisStudent = new Student(cursor.getString(1)); // name
        thisStudent.setID(Integer.parseInt(cursor.getString(0)));
        thisStudent.setAttended(cursor.getInt(2) == 1);

        return thisStudent;
    }

    public ArrayList<Student> getAllStudents() {
        ArrayList<Student> students = new ArrayList<Student>();

        // get all
        String query = "SELECT  * FROM " + TABLE_STUDENTS;

        // open db
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        // get each student out db
        Student student = null;
        if (cursor.moveToFirst()) {
            do {
                student = new Student(cursor.getString(1));
                student.setID(Integer.parseInt(cursor.getString(0)));
                student.setAttended(cursor.getInt(2) == 1);

                // Add student to list
                students.add(student);
            } while (cursor.moveToNext());
        }

    return students;
    }

    public void updateStudent(Student student) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("name", student.getName());
        values.put("attended", student.getAttended());

        // update
        db.update(TABLE_STUDENTS, //table
                values, // column/value
                "id = ?", // selections
                new String[] { String.valueOf(student.getID()) }); //selection args

        // finished with db
        db.close();

    }

    public void deleteStudent(Student student) {

        // get db
        SQLiteDatabase db = this.getWritableDatabase();

        // delete
        db.delete(TABLE_STUDENTS, //table name
                "id = ?",  // selections
                new String[] { String.valueOf(student.getID()) }); //selections args

        // close the db
        db.close();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_STUDENT_TABLE = "CREATE TABLE students ( " +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "name TEXT, "+
                "attended INTEGER NOT NULL )";

        // create student table
        db.execSQL(CREATE_STUDENT_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS students");
        this.onCreate(db);
    }
}
