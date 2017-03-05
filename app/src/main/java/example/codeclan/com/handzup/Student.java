package example.codeclan.com.handzup;

/**
 * Created by user on 04/03/2017.
 */

public class Student {
    int ID;
    String name;
    boolean attended;

    public Student(String name)
    {
        this.name = name;
        this.attended = false;
    }

    public void setAttended(boolean attended)
    {
        this.attended = attended;
    }

    public boolean getAttended()
    {
        return this.attended;
    }
}
