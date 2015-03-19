package AutoTests;

import Implementations.ContactGetter;
import Implementations.ContactManagerImpl;
import cw4.Contact;
import cw4.ContactManager;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.*;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;

public class ContactManagerImplFlushTest {

    static File filein;
    static ContactManager CM;


    @BeforeClass
    public static void setup() {
        filein = new File("/Users/digibrose/PiJ-work/day18/cw4/ContactManager.csv");
        CM = new ContactManagerImpl(filein);

    }

    @Test
    public void testTestFlush() {
        CM.addNewContact("Harry", "Met Sally");
        Set<Contact> FC = new HashSet<Contact>();
        FC.add(ContactGetter.ConGet(CM.getContacts("Zed"))[0]);
        FC.add(ContactGetter.ConGet(CM.getContacts("Gordon"))[0]);
        FC.add(ContactGetter.ConGet(CM.getContacts("Harry"))[0]);
        Calendar Cal = new GregorianCalendar(2015, 5, 14, 12, 15);
        CM.addFutureMeeting(FC, Cal);
        Calendar Cal2 = new GregorianCalendar(2014, 3, 12, 13, 30);
        CM.addNewPastMeeting(FC, Cal2, " ");
   //     CM.addMeetingNotes(CM.);
        CM.flush();
        File Filein2 = new File("/Users/digibrose/PiJ-work/day18/cw4/ContactManager2.csv");
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