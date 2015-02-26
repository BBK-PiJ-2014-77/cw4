package Implementations;

import cw4.Contact;

/**
 * Created by digibrose on 25/02/2015.
 */
public class ContactImpl implements Contact {

    static int NextId = 0;
    private int Id;
    private String name;


    public ContactImpl( String name){
        this.Id = NextId;
        NextId = NextId + 1;
        this.name = name;
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
        return null;
    }

    @Override
    public void addNotes(String note) {

    }
}
