package AutoTests;

import Implementations.ContactManagerImpl;
import cw4.Contact;
import cw4.ContactManager;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;

import java.io.*;
import java.util.Calendar;
import java.util.GregorianCalendar;

import static org.junit.Assert.*;

/**
 * This is a test to see whether it ignores a second read in Id
 */

public class Error1Test {

    @Test
    public void ErrorReadTest(){

        File inFile = new File("/Users/digibrose/PiJ-work/day18/cw4/CMerror1.csv");
        ContactManager CM = new ContactManagerImpl(inFile);

        int observed = CM.getContacts(1).size();
        int expected = 1;
        assertEquals(expected, observed);

        Calendar cal = new GregorianCalendar(2015, 7, 2, 12, 13);
        int observed2 = CM.getFutureMeetingList(cal).size();
        int expexted2 = 1;
        assertEquals(expexted2, observed2 );

    }

}