package AutoTests;

import Implementations.ContactImpl;
import Implementations.PastMeetingImpl;
import cw4.Contact;
import cw4.Meeting;
import cw4.PastMeeting;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.*;

public class PastMeetingImplTest {

    static Contact somebody;
    static Contact somebody2;
    static List<Integer> uniquecheck;
    static Calendar cal;
    static String Notes;
    static Meeting M1;

    @BeforeClass
            public static void Setup() {
        somebody = new ContactImpl("John", "");
        somebody2 = new ContactImpl("Pete", "");
        uniquecheck = new LinkedList<Integer>();
        cal = new GregorianCalendar(2015, 1, 2, 12, 13);
        Notes = "Buy, Buy, Sell, Sell";
        M1 = new PastMeetingImpl(cal, Notes, somebody, somebody2);
    }

    @Test
    public void testGetNotes() {
        String Expected = "Buy, Buy, Sell, Sell";
        PastMeeting M2 = (PastMeeting) M1;
        String Observed = M2.getNotes();
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