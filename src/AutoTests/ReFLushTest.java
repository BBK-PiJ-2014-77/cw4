package AutoTests;

import Implementations.ContactManagerImpl;
import cw4.ContactManager;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.File;

import static org.junit.Assert.*;

public class ReFLushTest {

    static ContactManager CM = new ContactManagerImpl();

    @BeforeClass
  static  public void setup(){

        File filein = new File("/Users/digibrose/CM2.csv");
        CM = new ContactManagerImpl(filein);

    }

    @Test
    public void reread1(){

        int expected = 2;
        int observed = CM.getFutureMeeting(0).getContacts().size();
        assertEquals(expected, observed);
    }

    public void reread2(){
        String expected = "Notes Test";
        String observed = CM.getPastMeeting(1).getNotes();
        assertEquals(expected, observed);
    }




}