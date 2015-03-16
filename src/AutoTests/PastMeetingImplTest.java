package AutoTests;

import Implementations.ContactImpl;
import Implementations.MeetingImpl;
import Implementations.PastMeetingImpl;
import cw4.Contact;
import cw4.Meeting;
import cw4.PastMeeting;
import org.junit.Test;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.*;

public class PastMeetingImplTest {


    Contact somebody = new ContactImpl("John", "");
    Contact somebody2 = new ContactImpl("Pete", "");
    private List<Integer> uniquecheck = new LinkedList<Integer>();
    private Calendar cal = new GregorianCalendar(2015, 1, 2, 12, 13);
    String Notes = "Buy, Buy, Sell, Sell";
    PastMeeting M1 = new PastMeetingImpl(cal, Notes, somebody, somebody2);

    @Test
    public void testGetNotes() {
        String Expected = "Buy, Buy, Sell, Sell";
        String Observed = M1.getNotes();
        assertEquals(Expected, Observed);
    }

    @Test
    public void testGetId() {
        boolean expected = false;
        boolean observed = uniquecheck.contains(M1.getId());
        assertEquals(expected, observed);
        uniquecheck.add(M1.getId());
        boolean expected2 = true;
        boolean observed2 = uniquecheck.contains(M1.getId());
        assertEquals(expected2, observed2);
    }

    @Test
    public void testGetDate() {
        Calendar expected = new GregorianCalendar(2015, 1, 2, 12, 13);
        Calendar observed = M1.getDate();
        assertEquals(expected, observed);
    }

    @Test
    public void testGetContacts() {
        Contact[] observed = new ContactImpl[M1.getContacts().size()];
        Contact[] expected = {somebody, somebody2};
        M1.getContacts().toArray(observed);
        for (int i = 0; i < expected.length; i++) {
            assertEquals(expected[i].getId(), observed[i].getId());
        }
    }
}