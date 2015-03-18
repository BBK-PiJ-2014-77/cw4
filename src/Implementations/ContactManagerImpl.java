package Implementations;

import cw4.*;

import java.io.*;
import java.util.*;

/**
 * Created by digibrose on 16/03/2015.
 */
public class ContactManagerImpl implements ContactManager {

    private File ContactManagerFile;
    private List<Contact> Contacts = new LinkedList<Contact>();
    private List<Meeting> Meetings = new LinkedList<Meeting>();

    public ContactManagerImpl(){
        String Filename = "ContactManagerFile";
        ContactManagerFile = new File(Filename);
    }

    /**
     * Constructor with File input of a contactManager CSV file
     * @param ContactManagerFile
     */

    public ContactManagerImpl(File ContactManagerFile) {
        this.ContactManagerFile =  ContactManagerFile;

        try {
            BufferedReader in = new BufferedReader(new FileReader(ContactManagerFile));
            String line;
            while ((line = in.readLine()) != null) {
                /**
                 * code to extract Contacts
                 */
                if (line.charAt(0) == 'C'){
                    String[] split = new String[4];
                    int i = 0;
                    int j = 0;
                    for (int k = 0; k < line.length(); k++) {
                        if (line.charAt(k) == ',') {
                            split[i] = line.substring(j, k);
                            i++;
                            j = k + 1;
                        }
                        if (i == 3){
                            split[i] = line.substring(j, line.length());
                            break;
                        }
                    }
                    int conId = Integer.parseInt(split[1]);
                    Contact con = new ContactImpl(conId, split[2], split[3]);
                    System.out.println(con.getName() +" " + con.getId());
                    Contacts.add(con);
              //      System.out.println("inputId " + con.getId());
                }
                /**
                 * code to extract Meetings
                 */
                if (line.charAt(0) == 'M'){
                    String[] split = new String[4];
                    int i = 0;
                    int j = 0;
                    for (int k = 0; k < line.length(); k++) {
                        if (line.charAt(k) == ',') {
                            split[i] = line.substring(j, k);
                            i++;
                            j = k + 1;
                        }
                        if (i == 3){
                            split[i] = line.substring(j, line.length());
                            break;
                        }
                    }
                    int conId = Integer.parseInt(split[1]);
                    String[] date = new String[5];
                    date = split[2].split("-");
                    int[] cdate = new int[5];
                    for (int k = 0; k < date.length; k++) {
                        cdate[k] = Integer.parseInt(date[k]);
                    }
                    Calendar cal = new GregorianCalendar(cdate[0], cdate[1], cdate[2], cdate[3], cdate[4]);
                    String[] conmeet = new String[(split[3].length()+1)/2];
                    conmeet = split[3].split(" ");
                    int[] conmeetint = new int[conmeet.length];
                    Contact[] ContactMeet = new ContactImpl[conmeet.length];
                    for (int l=0; l<conmeet.length; l++){
                        conmeetint[l] = Integer.parseInt(conmeet[l]);
                        for (int m = 0; m < Contacts.size();m++){
                            if (conmeetint[l] == Contacts.get(l).getId()){
                                ContactMeet[l] = Contacts.get(l);
                                break;
                            }
                        }
                    }
                    if (cal.after(Calendar.getInstance())) {
                        Meeting Meet = new FutureMeetingsImpl(cal, ContactMeet);
                        Meetings.add(Meet);
                    }
                    else{
                        Meeting Meet = new PastMeetingImpl(cal, "", ContactMeet);
                        Meetings.add(Meet);
                    }
                    //  System.out.println(con.getName() +" " + con.getId());
                    //  Contacts.add(con);
                }
            }
            in.close();
        } catch (FileNotFoundException e) {
            System.out.println("file does not exist");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public int addFutureMeeting(Set<Contact> contacts, Calendar date) {
        Contact[] MeetCon = ContactGetter.ConGet(contacts);
        Meeting NewFMeet = new FutureMeetingsImpl(date, MeetCon );
        return 1;
    }

    @Override
    public PastMeeting getPastMeeting(int id) {
        PastMeeting PM = (PastMeetingImpl) Meetings.get(id);
        return PM;
    }

    @Override
    public FutureMeeting getFutureMeeting(int id) {


        FutureMeeting FM = (FutureMeetingsImpl) Meetings.get(id);
        return FM;
    }

    @Override
    public Meeting getMeeting(int id) {
        return Meetings.get(id);
    }

    @Override
    public List<Meeting> getFutureMeetingList(Contact contact) {
        List<Meeting> FutureMeetingList = new LinkedList<Meeting>();

        for (int i=0;i< Meetings.size();i++){
            if (Meetings.get(i).getContacts().contains(contact) && Meetings.get(i).getDate().after(Calendar.getInstance())){
                FutureMeetingList.add(Meetings.get(i));
            }
        }

        return FutureMeetingList;
    }

    @Override
    public List<Meeting> getFutureMeetingList(Calendar date) {
        List<Meeting> FutureMeetingsList = new LinkedList<Meeting>();
        boolean sameday;
        for (int i=0; i < Meetings.size(); i++){
            sameday = Meetings.get(i).getDate().getWeekYear() == date.getWeekYear() && Meetings.get(i).getDate().get(Calendar.DAY_OF_YEAR) == date.get(Calendar.DAY_OF_YEAR);
            System.out.println("Sameday is " + Meetings.get(i).getDate().get(Calendar.DAY_OF_YEAR));
            if (sameday){
                FutureMeetingsList.add(Meetings.get(i));
            }
        }
        return FutureMeetingsList;
    }

    @Override
    public List<PastMeeting> getPastMeetingList(Contact contact) {
        List<PastMeeting> PastMeetingList = new LinkedList<PastMeeting>();

        for (int i=0;i< Meetings.size();i++){
            if (Meetings.get(i).getContacts().contains(contact) && Meetings.get(i).getDate().before(Calendar.getInstance())){
                PastMeetingList.add((PastMeeting) Meetings.get(i));
            }
        }

        return PastMeetingList;
    }

    @Override
    public void addNewPastMeeting(Set<Contact> contacts, Calendar date, String text) {

    }

    @Override
    public void addMeetingNotes(int id, String text) {

    }

    @Override
    public void addNewContact(String name, String notes) {
        Contact NewContact = new ContactImpl(name, notes);
        Contacts.add(NewContact);

    }

    @Override
    public Set<Contact> getContacts(int... ids) {
        return null;
    }

    @Override
    public Set<Contact> getContacts(String name) {
        Set<Contact> reqContacts = new HashSet<Contact>();
        for (int i = 0; i < Contacts.size(); i++ ){
         //   System.out.println("getCon -" + Contacts.get(i).getName()+ "-");
         //   System.out.println(name);
            if (Contacts.get(i).getName().equals(name)){

                reqContacts.add(Contacts.get(i));
            }
        }
        return reqContacts;
    }

    @Override
    public void flush() {

    }
}
