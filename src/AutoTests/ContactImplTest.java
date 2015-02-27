package AutoTests;

import cw4.Contact;
import Implementations.ContactImpl;
import org.junit.Before;
import org.junit.Test;

import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.*;

public class ContactImplTest {


    List<Integer> uniquecheck = new LinkedList<Integer>();



    @Test
    public void testGetId() {
        Contact somebody = new ContactImpl( "John", "");
        boolean expected = false;
        boolean observed = uniquecheck.contains(somebody.getId());
        assertEquals(expected, observed);
        uniquecheck.add(somebody.getId());
        boolean expected2 = true;
        boolean observed2 = uniquecheck.contains(somebody.getId());
        assertEquals(expected2, observed2);
        System.out.println(somebody.getId() + "first");
    }

    @Test
    public void testGetName() {
        Contact somebody2 = new ContactImpl( "Tony", "");
        uniquecheck.add(somebody2.getId());
        String expected = "Tony";
        String observed = somebody2.getName();
        assertEquals(expected, observed);
        System.out.println(somebody2.getId() + "second");

    }

    @Test
    public void testGetNotes() {
        Contact somebody3 = new ContactImpl( "Andrew", "Blah Blah");
        uniquecheck.add(somebody3.getId());
        String expected = "Blah Blah";
        String observed = somebody3.getNotes();
        assertEquals(expected, observed);
        System.out.println(somebody3.getId() + "third");
    }

    @Test
    public void testAddNotes() {
        Contact somebody4 = new ContactImpl( "John", "");
        somebody4.addNotes("Blah and more Blah");
        String expected = ("Blah and more Blah");
        String observed = somebody4.getNotes();
        assertEquals(expected, observed);
        System.out.println(somebody4.getId() + "fourth");
    }
}