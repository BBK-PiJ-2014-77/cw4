package Implementations;

import cw4.Contact;

/**
 * Created by digibrose on 25/02/2015.
 */
public class ContactImpl implements Contact {

    static int NextId = 0;
    private int Id;
    private String name;
    private String notes;


    public ContactImpl( String name, String notes){
        this.Id = NextId;
        NextId = NextId + 1;
        this.name = name;
        this.notes = notes;
    }

    @Override
    public int getId() {
        return Id;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getNotes() {
        return notes;
    }

    @Override
    public void addNotes(String note) {
        this.notes = note;
    }
}
