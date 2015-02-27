package AutoTests;

import Implementations.ContactImpl;
import Implementations.MeetingImpl;
import cw4.Contact;
import cw4.Meeting;
import org.junit.Before;
import org.junit.Test;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.*;

import static java.time.Month.AUGUST;
import static org.junit.Assert.*;

public class MeetingImplTest {

    Contact somebody = new ContactImpl("John", "");
    Contact somebody2 = new ContactImpl("Pete", "");
    private List<Integer> uniquecheck = new LinkedList<Integer>();
    private Calendar cal = new GregorianCalendar();
    Meeting M1 = new MeetingImpl(cal, somebody, somebody2 );

    @Before
    public void setup(){
        cal.set(2015,1,2,12,13);

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
    public void testGetDate() throws Exception {

    }

    @Test
    public void testGetContacts() {
        Contact[] returned = new ContactImpl[M1.getContacts().size()];
        M1.getContacts().toArray(returned);
        for (int i = 0 ; i < returned.length; i++){
            System.out.println(returned[i].getName());
            //WTF???
        }
    }
}