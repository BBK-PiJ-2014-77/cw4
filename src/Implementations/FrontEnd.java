package Implementations;

import cw4.ContactManager;

import java.io.File;
import java.util.Scanner;

/**
 * Created by digibrose on 19/03/2015.
 */
public class FrontEnd {

    static ContactManager CM;

    public static void main(String[] args){
        launch();
    }

    private static void launch(){


    //    System.out.println("Hello Master, What is you Pleasure");
    //    System.out.println("Do you want to load up a CM file?");
    //    Scanner in = new Scanner(System.in);
    //    String Input1 = in.nextLine();
    //    if (Input1.charAt(0) == 'y' || Input1.charAt(0) == 'Y'){
    //        System.out.println("What is the files loaction?");
    //        String Input2 = in.nextLine();

            File filein = new File("/Users/digibrose/CM2.csv");
            CM = new ContactManagerImpl(filein);
     //   System.out.println("front End " + CM.getFutureMeeting(0).getContacts());
        CM.addNewContact("Test", "Test");
            CM.flush();
        }


    }


