package Implementations;

import cw4.Contact;
import cw4.Meeting;

import java.util.Calendar;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

/**
 * Created by digibrose on 26/02/2015.
 */
public class MeetingImpl implements Meeting {

    protected Set<Contact> ContactPresent = new HashSet<Contact>();
    protected int Id;
    protected Calendar date;
    private static LinkedList<Integer> IdList = new LinkedList<Integer>();

    /**
     * Constructor Method
     * @param date
     * @param present
     */

    public MeetingImpl(Calendar date, Contact... present){
        this.date = date;
        for (Contact item : present ) {
            ContactPresent.add(item);
        }
        int j = IdList.size();
        if (IdList.isEmpty()){
            this.Id = 0;
            IdList.add(0);
        }
        for (int i = 0; i < j; i++) {
            if (i != IdList.get(i )) {
                this.Id = i;
                IdList.add(i, i);
                break;
            }
            if (i == j - 1) {
                this.Id = IdList.size();
                IdList.add(j);
                break;
            }
        }
    }

    public MeetingImpl(int Id, Calendar date, Contact... present){
        this.date = date;
        for (Contact item : present ) {
            ContactPresent.add(item);
        }
        this.Id = Id;
        if (!IdList.contains(Id)) {
            int ILength = IdList.size();
            if (!IdList.isEmpty()) {
                for (int i = 0; i < ILength; i++) {
                    if (IdList.get(i) > Id) {
                        IdList.add(i, Id);
                        break;
                    }
                    if (i == ILength - 1) {
                        IdList.add(Id);
                    }
                }
            } else {
                IdList.add(Id);
            }
        }
    }

    /**
     * Method to return Id
     * @return int
     */

    @Override
    public int getId() {
        return Id;
    }

    /**
     * method to return date
     * @return Calendar
     */

    @Override
    public Calendar getDate() {
        return date;
    }

    /**
     * Method to return Contact Set
     * @return Set
     */

    @Override
    public Set<Contact> getContacts() {
        return ContactPresent;
    }
}
