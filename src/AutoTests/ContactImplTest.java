package AutoTests;

import cw4.Contact;
import Implementations.ContactImpl;
import org.junit.Test;

import static org.junit.Assert.*;

public class ContactImplTest {




    @Test
    public void testGetId() {
        Contact somebody = new ContactImpl( "John");
        System.out.println(somebody.getId() +"first");
        int expected = 0;
        int observed = somebody.getId();
        assertEquals(expected, observed);
        Contact somebody2 = new ContactImpl( "Fred" );
        System.out.println(somebody2.getId() + "second");
        int expected2 = 1;
        int observed2 = somebody2.getId();
        assertEquals(expected2, observed2);
    }

    @Test
    public void testGetName() {
        Contact somebody3 = new ContactImpl( "Tony");
        System.out.println(somebody3.getId() + "third");
        String expected = "Tony";
        String observed = somebody3.getName();
        assertEquals(expected, observed);

    }

    @Test
    public void testGetNotes() {

    }

    @Test
    public void testAddNotes() throws Exception {

    }
}