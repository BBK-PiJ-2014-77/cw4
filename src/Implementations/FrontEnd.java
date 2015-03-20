package Implementations;

import cw4.Contact;
import cw4.ContactManager;
import cw4.Meeting;
import cw4.PastMeeting;

import java.io.File;
import java.util.*;

/**
 * Created by digibrose on 19/03/2015.
 */
public class FrontEnd {

    static ContactManager CM;

    public static void main(String[] args) {
        launch();
    }

    private static void launch() {


        System.out.println("Hello Master, What is you Pleasure");
        System.out.println("Do you want to load up a CM file?");
        Scanner in = new Scanner(System.in);
        String Input1 = in.nextLine();
        if (Input1.charAt(0) == 'y' || Input1.charAt(0) == 'Y') {
            System.out.println("What is the files location?");
            String Input2 = in.nextLine();
            File filein = new File(Input2);
            CM = new ContactManagerImpl(filein);
        }
        boolean close = false;

        /**
         * Basic loop
         */

        while (!close){
            System.out.println("What would you like to do?");
            System.out.println("Type C to add a new contact or M to add a new meeting or G to get info on a meeting or N to add notes or X to exit" );
            String Input3 = in.nextLine();
            if (Input3.charAt(0) == 'C'){
             ContactInput(in);
            }
            if (Input3.charAt(0) == 'M'){
                MeetingInput(in);
            }
            if (Input3.charAt(0) =='G'){
                MeetingInfo(in);
            }
            if (Input3.charAt(0) == 'N'){
                Notes(in);
            }
            if (Input3.charAt(0)  == 'X'){
                close =  true;
            }
        }
    }

    /**
     * Method to point to and set up contact input
     * @param in
     */

    static private void ContactInput(Scanner in){
        System.out.println("What is there name?");
        String Input1 = in.nextLine();
        System.out.println("What notes on them?");
        String Input2 = in.nextLine();
        CM.addNewContact(Input1, Input2);
        System.out.println(Input1 + " added");
        flushrequest(in);
    }

    /**
     * Method to set up Meeting input
     * @param in
     */

    static private void MeetingInput(Scanner in){

        /**
         * Set up date
         */

        Calendar cal = CalQuestions(in);

        /**
         * Set up Contacts
         */

        boolean concount = false;
        Set<Contact> ConSet = new HashSet<Contact>();
        Set<Contact> SiCon;
        Contact[] SiConArray;
        while (!concount){
            System.out.println("Who is going?");
            String Input6 = in.nextLine();
            SiCon = CM.getContacts(Input6);

            /**
             *if more than one id with same name then ask which one
             */

            if (SiCon.size() > 1){
                System.out.println("Which one? please choose an Id");
                SiConArray = ContactGetter.ConGet(SiCon);
                for (int i = 0;i < SiCon.size();i++){
                    System.out.println(SiConArray[i].getId() + " " + SiConArray[i].getName() + " " + SiConArray[i].getNotes());
                }
                Input6 = in.nextLine();
                int In6 = Integer.parseInt(Input6);
                SiCon = CM.getContacts(In6);
            }
            ConSet.addAll(SiCon);
            System.out.println("Do you want to add another? no to finish");
            String Input7 = in.nextLine();
            if (Input7.charAt(0) == 'n' || Input7.charAt(0) == 'N'){
                concount = true;
            }
        }

        if (cal.after(Calendar.getInstance())) {
            CM.addFutureMeeting(ConSet, cal);
            System.out.println("Meeting added");
        }
        else{

            /**
             * if a past meeting then add notes
             */

            System.out.println("This meeting was in the past, do you have notes to add?");
            String Input8 = in.nextLine();
            String Notes = "";
            if (Input8.charAt(0) == 'y' || Input8.charAt(0) == 'Y'){
                System.out.println("Please write note");
                Notes = in.nextLine();
            }
            CM.addNewPastMeeting(ConSet, cal, Notes);
            System.out.println("Meeting Added");
        }
        flushrequest(in);
    }

    /**
     * Method to obtain meeting info
     * @param in
     */

    static private void MeetingInfo(Scanner in){

        String Input1 = "";
        String Input2 = "";
        int in2;

        System.out.println("Would you Like to search by Id, Date or Contact?");
        Input1 = in.nextLine();

        /**
         * If request is by Id
         */

        if (Input1.charAt(0) == 'I'){
            System.out.println("What is the Id?");
            Input2 = in.nextLine();
            in2 = Integer.parseInt(Input2);
            if (CM.getMeeting(in2).getDate().before(Calendar.getInstance())){
                PastMeeting Meet = CM.getPastMeeting(in2);
                Contact[] Conlist = ContactGetter.ConGet(Meet.getContacts());
                String CL = "";
                for (int i = 0; i < Conlist.length; i++){
                    CL = CL + Conlist[i].getName() + " ";
                }
                System.out.println(Meet.getId() + " " + Meet.getDate().getTime() + " " + CL + " " + Meet.getNotes());
            }
            else {
                Meeting Meet = CM.getMeeting(in2);
                Contact[] Conlist = ContactGetter.ConGet(Meet.getContacts());
                String CL = "";
                for (int i = 0; i < Conlist.length; i++){
                    CL = CL + Conlist[i].getName() + " ";
                }
                System.out.println(Meet.getId() + " " + Meet.getDate() + " " + CL);
            }
        }

        /**
         * if request is by date
         */

        if (Input1.charAt(0) == 'D'){
            System.out.println("This will only find future meetings");
            Calendar cal = CalQuestions(in);
            List<Meeting> Meetings = CM.getFutureMeetingList(cal);
            for (int i = 0;i < Meetings.size(); i++) {
                Contact[] Conlist = ContactGetter.ConGet(Meetings.get(i).getContacts());
                String CL = "";
                for (int j = 0; j < Conlist.length; j++){
                    CL = CL + Conlist[j].getName() + " ";
                }
                System.out.println(Meetings.get(i).getId() + " " + Meetings.get(i).getDate().getTime() + " " + CL);
                CL = "";
            }
        }

        /**
         *if request is by contact
         */

        if (Input1.charAt(0) == 'C'){
            System.out.println("Do you want to search for Future of Past meetings?");
            Input2 = in.nextLine();
            if (Input2.charAt(0) == 'F' || Input2.charAt(0) == 'f'){
                System.out.println("What Id?");
                String Input3 = in.nextLine();
                int in3 = Integer.parseInt(Input3);
                List<Meeting> Meetings = CM.getFutureMeetingList(ContactGetter.ConGet(CM.getContacts(in3))[0]);
                for (int i = 0;i < Meetings.size(); i++) {
                    Contact[] Conlist = ContactGetter.ConGet(Meetings.get(i).getContacts());
                    String CL = "";
                    for (int j = 0; j < Conlist.length; j++){
                        CL = CL + Conlist[j].getName() + " ";
                    }
                    System.out.println(Meetings.get(i).getId() + " " + Meetings.get(i).getDate().getTime() + " " + CL);
                }
            }
            if (Input2.charAt(0) == 'P' || Input2.charAt(0) == 'p'){
                System.out.println("What Id?");
                String Input3 = in.nextLine();
                int in3 = Integer.parseInt(Input3);
                List<PastMeeting> Meetings = CM.getPastMeetingList(ContactGetter.ConGet(CM.getContacts(in3))[0]);
                for (int i = 0;i < Meetings.size(); i++) {
                    Contact[] Conlist = ContactGetter.ConGet(Meetings.get(i).getContacts());
                    String CL = "";
                    for (int j = 0; j < Conlist.length; j++){
                        CL = CL + Conlist[j].getName() + " ";
                    }
                    System.out.println(Meetings.get(i).getId() + " " + Meetings.get(i).getDate().getTime() + " " + CL + Meetings.get(i).getNotes());
                }
            }
        }
    }

    /**
     * Method to add note to past meeting
     * @param in
     */

    static private void Notes(Scanner in){
        System.out.println("What Meeting Id would you like to add notes for?");
        String Input1 = in.nextLine();
        int in1 = Integer.parseInt(Input1);
        System.out.println("Please write notes");
        String Input2 = in.nextLine();
        CM.addMeetingNotes(in1, Input2);
        flushrequest(in);
    }

    /**
     * Method to request a flush
     * @param in
     */

    static private void flushrequest(Scanner in){
        System.out.println("Would you like to save this to file");
        String Input1 = in.nextLine();
        if (Input1.charAt(0) == 'y' || Input1.charAt(0) == 'Y'){
            CM.flush();
            System.out.println("Saved");
        }
        else {
            System.out.println("ok");
        }
    }

    /**
     * Method to setp a calendar entry
     * @param in
     * @return Calendar
     */

    static private Calendar CalQuestions(Scanner in){
        System.out.println("What year?");
        String Input1 = in.nextLine();
        int in1 = Integer.parseInt(Input1);
        System.out.println("What Month?");
        String Input2 = in.nextLine();
        int in2 = Integer.parseInt(Input2);
        System.out.println("What Day?");
        String Input3 = in.nextLine();
        int in3 = Integer.parseInt(Input3);
        System.out.println("What Hour?");
        String Input4 = in.nextLine();
        int in4 = Integer.parseInt(Input4);
        System.out.println("What Minute?");
        String Input5 = in.nextLine();
        int in5 = Integer.parseInt(Input5);
        Calendar cal = new GregorianCalendar(in1, in2, in3, in4, in5);
        return cal;
    }

}
