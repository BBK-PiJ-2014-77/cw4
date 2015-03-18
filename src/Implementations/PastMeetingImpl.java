package Implementations;

import cw4.Contact;
import cw4.PastMeeting;

import java.util.Calendar;

/**
 * Created by digibrose on 16/03/2015.
 */
public class PastMeetingImpl extends MeetingImpl implements PastMeeting {

    private String Notes;


    public PastMeetingImpl(Calendar date, String Notes, Contact... present) {
        super(date, present);
        this.Notes = Notes;
    }

    public PastMeetingImpl(int id, Calendar date, String Notes, Contact... present){
        super(date, present);
        this.Notes = Notes;
        this.Id = id;
    }

    @Override
    public String getNotes() {
        return Notes;
    }


  //  public void addNotes(String notes) {
  //      this.Notes = notes;
  //  }
}