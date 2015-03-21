package AutoTests;

import Implementations.ContactGetter;
import Implementations.ContactImpl;
import Implementations.ContactManagerImpl;
import cw4.Contact;
import cw4.ContactManager;
import cw4.Meeting;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.*;
import java.util.*;

import static org.junit.Assert.*;

public class ContactManagerImplTest {

    static File filein;
    static ContactManager CM;


    @BeforeClass
    public static void setup() {
        filein = new File("/Users/digibrose/PiJ-work/day18/cw4/ContactManager.csv");
        CM = new ContactManagerImpl(filein);

    }

    /**
     * Initial tests to show successful reading of csv file with 2 contacts and 1 meeting which need to be initialised
     * on starting up manager
     */

    @Test
    public void testRead() {

        int expected = 1;

        //  System.out.println("CM is " + CM.getContacts("Zed").size() );
        Contact[] observed = new ContactImpl[CM.getContacts("Zed").size()];
        CM.getContacts("Zed").toArray(observed);
        assertEquals(expected, observed[0].getId());

    }

    /**
     * Initial test to see whether Id is recorded from csv file
     */

    @Test
    public void testReadId() {
        int expected = 1;
        Contact[] observed = new ContactImpl[CM.getContacts("Zed").size()];
        CM.getContacts("Zed").toArray(observed);
        assertEquals(expected, observed[0].getId());
    }

    /**
     * Since we added 1 and 2 to the record the first new contact should be 0 and then 3
     */

    @Test
    public void testAddNewContact() {
        CM.addNewContact("Harry", "Met Sally");
        int expected = 0;
        Contact[] observed = new ContactImpl[CM.getContacts("Harry").size()];
        CM.getContacts("Harry").toArray(observed);
        assertEquals(expected, observed[0].getId());
        CM.addNewContact("John", "Being John M");
        int expected2 = 3;
        Contact[] observed2 = ContactGetter.ConGet(CM.getContacts("John"));
        // Contact[] observed2 = new ContactImpl[CM.getContacts("John").size()];
        // CM.getContacts("John").toArray(observed2);
        assertEquals(expected2, observed2[0].getId());

    }

    /**
     * Basic test of AddFuture Meeting return value
     */


    @Test
    public void testAddFutureMeeting() {
        Set<Contact> contacts = CM.getContacts("Zed");
        Contact[] Gordadd = ContactGetter.ConGet(CM.getContacts("Gordon"));
        contacts.add(Gordadd[0]);
        Calendar cal = new GregorianCalendar(2015, 11, 2, 14, 30);
        int expected = 1;
        int observed = CM.addFutureMeeting(contacts, cal);
        assertEquals(expected, observed);
    }

    /**
     * Test of GetFuture Meeting which should take a meeting Id and then return that future meeting
     */

    @Test
    public void testGetFutureMeeting() {
        int expected = 0;
        int observed = CM.getFutureMeeting(0).getId();
        assertEquals(expected, observed);
    }


    @Test
    public void testGetPastMeeting() {
        int expected = 1;
        int observed = CM.getPastMeeting(1).getId();
        assertEquals(expected, observed);
    }


    @Test
    public void testGetMeeting() {
        int expected = 1;
        int observed = CM.getMeeting(1).getId();
   //     Calendar cal = new GregorianCalendar(2016, 11, 2, 14, 30);
  //      CM.addNewPastMeeting(CM.getContacts(1),cal, " " );
  //      CM.addFutureMeeting(CM.getContacts(1), cal);
  //      System.out.println("Find meet 1 " + CM.getMeeting(1).getId());
        CM.flush();
        assertEquals(expected, observed);
    }

    @Test
    public void testGetFutureMeetingList() {
        Calendar cal = new GregorianCalendar(2015, 7, 2);
        int expected = 1;
        int observed = CM.getFutureMeetingList(cal).size();
        assertEquals(expected, observed);
    }

    @Test
    public void testGetFutureMeetingList1() {
        int expected = 1;
        int observed = CM.getFutureMeetingList(ContactGetter.ConGet(CM.getContacts("Zed"))[0]).size();
        assertEquals(expected, observed);

    }

    @Test
    public void testGetPastMeetingList() {
        int expected = 1;
        int observed = CM.getPastMeetingList(ContactGetter.ConGet(CM.getContacts("Gordon"))[0]).size();
        assertEquals(expected, observed);
        //    System.out.println(CM.getContacts("Zed").size());
    }


    @Test
    public void testAddNewPastMeeting() {
        Set<Contact> contacts = CM.getContacts("Zed");
        Calendar cal = new GregorianCalendar(2014, 10, 2, 14, 30);
        int expected = 2;
        CM.addNewPastMeeting(contacts, cal, "");
        int observed = CM.getPastMeetingList(ContactGetter.ConGet(CM.getContacts("Zed"))[0]).size();
        assertEquals(expected, observed);
    }


    @Test
    public void testAddMeetingNotes() {
        String expected = "TaDah";
        CM.addMeetingNotes(1, "TaDah");
        String observed = CM.getPastMeeting(1).getNotes();
        assertEquals(expected, observed);

    }


    @Test
    public void testGetContacts() {

        int expected = 2;
        int observed = CM.getContacts(1, 2).size();
        assertEquals(expected, observed);

    }


    @Test
    public void testGetContacts1() {
        int expected = 1;
        int observed = CM.getContacts("Zed").size();
        assertEquals(expected, observed);
    }


    @Test
    public void testFlush() {
     //   CM.addNewContact("Harry", "Met Sally");
        CM.flush();
        File Filein2 = new File("/Users/digibrose/PiJ-work/day18/cw4/ContactManager.csv");
        boolean linechecker = false;
        try {
            BufferedReader in = new BufferedReader(new FileReader(Filein2));
            String line;
            linechecker = true;
            while ((line = in.readLine()) != null) {
                if (!(line.charAt(0) == 'M' || line.charAt(0) == 'C')) {
                    linechecker = false;
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        boolean expected = true;
        boolean observed = linechecker;
        assertEquals(expected, observed);
    }
}