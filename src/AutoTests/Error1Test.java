package AutoTests;

import Implementations.ContactManagerImpl;
import cw4.Contact;
import cw4.ContactManager;
import cw4.Meeting;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;

import java.io.*;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Set;

import static org.junit.Assert.*;

/**
 * This is a test to see whether it ignores a second read in Id
 */

public class Error1Test {

    private static File inFile;
    private static ContactManager CM;


    @BeforeClass
    public static void setup(){
        inFile = new File("/Users/digibrose/PiJ-work/day18/cw4/CMerror1.csv");
        CM = new ContactManagerImpl(inFile);
    }

    @Test
    public void ErrorReadTest() {

        int observed = CM.getContacts(1).size();
        int expected = 1;
        assertEquals(expected, observed);

        Calendar cal = new GregorianCalendar(2015, 7, 2, 12, 13);
        int observed2 = CM.getFutureMeetingList(cal).size();
        int expexted2 = 1;
        assertEquals(expexted2, observed2);

    }

    /**
     * Test to see whether you can input a future date into a pastmeeting
     */

    @Test
    public void ErrorInputTest() {

        Calendar cal = new GregorianCalendar(2016, 3, 12, 12, 45);
        CM.addNewPastMeeting(CM.getContacts(1), cal, "" );
        Meeting expected = null;
        Meeting observed = CM.getPastMeeting(1);
        assertEquals(expected, observed);

    }

    /**
     * Test to see whether you can add a past date to a future meeting
     */
    @Test
    public void ErrorInputTest2(){

        Calendar cal = new GregorianCalendar(2014, 2, 15, 12, 45);
        int expected = 0;
        int observed = CM.addFutureMeeting(CM.getContacts(1), cal);
    //    System.out.println("Value is " + observed);
        assertEquals(expected, observed);
    }

    /**
     * Test to see what happens when you try to use a nonexistent contact
     */

    @Test
    public void ErrorInputTest3(){



    }

}