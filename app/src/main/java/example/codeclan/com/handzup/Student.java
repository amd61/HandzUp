package example.codeclan.com.handzup;

/**
 * Created by user on 05/03/2017.
 */

public class Student {
    int ID;
    String name;
    boolean attended;

    public Student(String name) {
        this.name = name;
    }

    public void setAttended(boolean attended)
    {
        this.attended = attended;
    }

    public void setID(int ID){
        this.ID = ID;

    }

    public boolean getAttended()
    {
        return this.attended;
    }

    public int getID()
    {
        return this.ID;
    }


    public String getName() {
        return this.name;
    }
}
