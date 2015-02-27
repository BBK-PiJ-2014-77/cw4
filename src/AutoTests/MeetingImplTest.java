package AutoTests;

import Implementations.ContactImpl;
import Implementations.MeetingImpl;
import cw4.Contact;
import cw4.Meeting;
import org.junit.Test;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.*;

import static java.time.Month.AUGUST;
import static org.junit.Assert.*;

public class MeetingImplTest {

    List<Integer> uniquecheck = new LinkedList<Integer>();

    @Test
    public void testGetId() {

        Contact somebody = new ContactImpl("John", "");
        Calendar cal = new GregorianCalendar();
        cal.set(2015,1,2,12,13);
        Meeting M1 = new MeetingImpl(cal, somebody );
        boolean expected = false;
        boolean observed = uniquecheck.contains(M1.getId());
        assertEquals(expected, observed);
        uniquecheck.add(M1.getId());
        boolean expected2 = true;
        boolean observed2 = uniquecheck.contains(M1.getId());
        assertEquals(expected2, observed2);

    }

    @Test
    public void testGetDate() throws Exception {

    }

    @Test
    public void testGetContacts() throws Exception {

    }
}