package AutoTests;

import Implementations.ContactGetter;
import Implementations.ContactImpl;
import Implementations.ContactManagerImpl;
import cw4.Contact;
import cw4.ContactManager;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.*;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.Set;

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
    public void testRead(){

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
    public void testReadId(){
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


    @Test
    public void testAddFutureMeeting() {
        Set<Contact> contacts = new HashSet<Contact>();
        contacts = CM.getContacts("Zed");
        Contact[] Gordadd = ContactGetter.ConGet(CM.getContacts("Gordon"));
        contacts.add(Gordadd[0]);
        Calendar cal = new GregorianCalendar(2015,11, 2, 14, 30);
        int expected = 1;
        int observed = CM.addFutureMeeting(contacts, cal);
        assertEquals(expected, observed);
    }

    /**

    @Test
    public void testGetPastMeeting() {

    }

    @Test
    public void testGetFutureMeeting() {

    }

    @Test
    public void testGetMeeting() {

    }

    @Test
    public void testGetFutureMeetingList() {

    }

    @Test
    public void testGetFutureMeetingList1() {

    }

    @Test
    public void testGetPastMeetingList() {

    }

    @Test
    public void testAddNewPastMeeting() {

    }

    @Test
    public void testAddMeetingNotes() {

    }





/**

    @Test
    public void testGetContacts() {

    }

    @Test
    public void testGetContacts1() {

    }

    @Test
    public void testFlush() {

    }

*/
}