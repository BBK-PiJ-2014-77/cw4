package Implementations;

import cw4.Contact;
import cw4.Meeting;

import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by digibrose on 26/02/2015.
 */
public class MeetingImpl implements Meeting {

    private Set<Contact> ContactPresent = new HashSet<Contact>();
    private  static int MeetingCounter;
    private int Id;
    private Calendar date;

    public MeetingImpl(Calendar date, Contact... present){
        this.date = date;
        for (Contact item : present ) {
            ContactPresent.add(item);
        }
        this.Id = MeetingCounter;
        MeetingCounter++;
    }

    @Override
    public int getId() {
        return Id;
    }

    @Override
    public Calendar getDate() {
        return date;
    }

    @Override
    public Set<Contact> getContacts() {
        return ContactPresent;
    }
}
