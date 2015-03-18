package Implementations;

import cw4.Contact;
import cw4.FutureMeeting;
import cw4.Meeting;

import java.util.Calendar;

/**
 * Created by digibrose on 16/03/2015.
 */
public class FutureMeetingsImpl extends MeetingImpl implements FutureMeeting, Meeting {

    public FutureMeetingsImpl(Calendar date, Contact... present) {
        super(date, present);
    }
}
