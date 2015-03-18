package Implementations;

import cw4.*;

import java.io.*;
import java.util.*;

/**
 * Created by digibrose on 16/03/2015.
 */
public class ContactManagerImpl implements ContactManager {

    private File ContactManagerFile;
    private LinkedList<Contact> Contacts = new LinkedList<Contact>();
    private LinkedList<ContactImpl> Meetings = new LinkedList<ContactImpl>();

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
          //      System.out.println(line + "11");
          //      System.out.println(line.length());
          //      System.out.println(line.charAt(0));
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
                //    System.out.println(con.getNotes());
                    System.out.println(con.getName() +" " + con.getId());
                    Contacts.add(con);
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
        return null;
    }

    @Override
    public FutureMeeting getFutureMeeting(int id) {
        return null;
    }

    @Override
    public Meeting getMeeting(int id) {
        return null;
    }

    @Override
    public List<Meeting> getFutureMeetingList(Contact contact) {
        return null;
    }

    @Override
    public List<Meeting> getFutureMeetingList(Calendar date) {
        return null;
    }

    @Override
    public List<PastMeeting> getPastMeetingList(Contact contact) {
        return null;
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
