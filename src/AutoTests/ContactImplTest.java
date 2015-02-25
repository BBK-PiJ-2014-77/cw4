package AutoTests;

import cw4.Contact;
import Implementations.ContactImpl;
import org.junit.Test;

import static org.junit.Assert.*;

public class ContactImplTest {

    private Contact somebody = new ContactImpl();

    @Test
    public void testGetId() {
        int expected = 1;
        int observed = somebody.getId();
        assertEquals(expected, observed);
    }

    @Test
    public void testGetName() throws Exception {

    }

    @Test
    public void testGetNotes() throws Exception {

    }

    @Test
    public void testAddNotes() throws Exception {

    }
}