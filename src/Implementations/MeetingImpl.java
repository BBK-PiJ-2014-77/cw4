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

    private Set<Contact> ContactPresent = new HashSet<Contact>();
 //   private  static int MeetingCounter;
    private int Id;
    private Calendar date;

    static LinkedList<Integer> IdList = new LinkedList<Integer>();

    public MeetingImpl(Calendar date, Contact... present){
        this.date = date;
        for (Contact item : present ) {
            ContactPresent.add(item);
        }
      //  this.Id = MeetingCounter;
      //  MeetingCounter++;

        int j = IdList.size();
        System.out.println("present size is " + IdList.size());
        if (IdList.isEmpty()){
            this.Id = 0;
            IdList.add(0);
            System.out.println("New Id0M is " + this.Id);
        }
        for (int i = 0; i < j; i++) {
            if (i != IdList.get(i )) {
                System.out.println("compare " + i + " " + IdList.get(i));
                this.Id = i;
                IdList.add(i, i);
                System.out.println("New Id is " + this.Id);
                break;
            }
            if (i == j - 1) {
                this.Id = IdList.size();
                IdList.add(j);
                System.out.println("New Id2 is " + this.Id);
                break;
            }

        }

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
