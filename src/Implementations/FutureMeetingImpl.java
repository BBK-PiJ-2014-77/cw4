package Implementations;

import cw4.Contact;
import cw4.FutureMeeting;
import cw4.Meeting;

import java.util.Calendar;

/**
 * Created by digibrose on 16/03/2015.
 */
public class FutureMeetingImpl extends MeetingImpl implements FutureMeeting, Meeting {

    /**
     * Basic Constructor
     * @param date
     * @param present
     */

    public FutureMeetingImpl(Calendar date, Contact... present) {
        super(date, present);
    }

    /**
     * Contstructor with Id added as well to be used on startup
     * @param id
     * @param date
     * @param present
     */

    public FutureMeetingImpl(int id, Calendar date, Contact... present){
        super(id, date, present);
     //   this.Id = id;
    }

}
