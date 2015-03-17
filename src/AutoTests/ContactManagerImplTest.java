package AutoTests;

import Implementations.ContactImpl;
import Implementations.ContactManagerImpl;
import cw4.Contact;
import cw4.ContactManager;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.*;
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

        Contact expected = new ContactImpl("Zed", "Zed's Dead");
        Contact[] observed = new ContactImpl[CM.getContacts("Zed").size()];
      //  System.out.println("CM is " + CM.getContacts("Zed").size() );
        CM.getContacts("Zed").toArray(observed);
        assertEquals(expected.getName(), observed[0].getName());

    }

    /**
     * Initial test to see whether Id is recorded from csv file
     */

    @Test
    public void testReadId(){
        int expected = 1;
        Contact[] observed = new ContactImpl[CM.getContacts("Zed").size()];
        CM.getContacts("Zed").toArray(observed);
        System.out.println(observed[0].getId());
        assertEquals(expected, observed[0].getId());
    }

/**
    @Test
    public void testAddFutureMeeting() {

    }

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

    @Test
    public void testAddNewContact() {

    }

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