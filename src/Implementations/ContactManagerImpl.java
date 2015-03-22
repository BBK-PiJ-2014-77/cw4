package Implementations;

import cw4.*;

import java.io.*;
import java.util.*;

/**
 * Created by digibrose on 16/03/2015.
 */
public class ContactManagerImpl implements ContactManager {

    private File ContactManagerFile = null;
    private List<Contact> Contacts = new LinkedList<Contact>();
    private List<Meeting> Meetings = new LinkedList<Meeting>();


    /**
     * Basic Constructor
     */

    public ContactManagerImpl(){

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
                    String[] split = line.split(",");
                    int conId = Integer.parseInt(split[1]);
                    boolean conpresent = false;
                    for (int i = 0; i< Contacts.size(); i++){
                        if (conId == Contacts.get(i).getId()){
                            System.out.println("Repeated Id " + conId + " Ignored");
                            conpresent = true;
                        }
                    }
                    if (!conpresent) {
                        Contact con = new ContactImpl(conId, split[2], split[3]);
                        Contacts.add(con);
                    }

                }
                /**
                 * code to extract Meetings
                 */
                if (line.charAt(0) == 'M') {
                    String[] split = line.split(",");
                    int MId = Integer.parseInt(split[1]);
                    boolean meetpresent = false;
                    for (int i = 0; i < Meetings.size(); i++) {
                        if (MId == Meetings.get(i).getId()) {
                            System.out.println("Repeated Id " + MId + " Ignored");
                            meetpresent = true;
                        }
                    }

                    if (!meetpresent) {

                        /**
                         * code to extract the date
                         */

                        String[] date = new String[5];
                        date = split[2].split("-");
                        int[] cdate = new int[5];
                        for (int k = 0; k < date.length; k++) {
                            cdate[k] = Integer.parseInt(date[k]);
                        }
                        Calendar cal = new GregorianCalendar(cdate[0], cdate[1], cdate[2], cdate[3], cdate[4]);

                        /**
                         * code to extract the contacts
                         */

                        String[] conmeet = split[3].split(" ");
                        int[] conmeetint = new int[conmeet.length];
                        Contact[] ContactMeet = new ContactImpl[conmeet.length];
                        String MeetNotes = "";
                        for (int l = 0; l < conmeet.length; l++) {
                            conmeetint[l] = Integer.parseInt(conmeet[l]);
                            for (int m = 0; m < Contacts.size(); m++) {
                                if (conmeetint[l] == Contacts.get(m).getId()) {
                                    ContactMeet[l] = Contacts.get(m);
                                    break;
                                }
                            }
                        }

                        /**
                         * code to decide presence of Notes
                         */

                        if (cal.after(Calendar.getInstance())) {
                            Meeting Meet = new FutureMeetingImpl(MId, cal, ContactMeet);
                            Meetings.add(Meet);
                        } else {
                            if (split.length == 5) {
                                MeetNotes = split[4];
                            }
                            Meeting Meet = new PastMeetingImpl(MId, cal, MeetNotes, ContactMeet);
                            Meetings.add(Meet);
                        }
                    }
                }
            }
            in.close();
        } catch (FileNotFoundException e) {
            System.out.println("file does not exist");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Code to add a future Meeting to the Contact Manager List
     * @param contacts a list of contacts that will participate in the meeting
     * @param date the date on which the meeting will take place
     * @return int a value of 1 if successful
     * @throws java.lang.IllegalArgumentException
     */

    @Override
    public int addFutureMeeting(Set<Contact> contacts, Calendar date) throws IllegalArgumentException {

        if (date.after(Calendar.getInstance())) {
            Contact[] MeetCon = ContactGetter.ConGet(contacts);
            Meeting NewFMeet = new FutureMeetingImpl(date, MeetCon);
            Meetings.add(NewFMeet);
            return 1;
        }else{
            System.out.println("You can't have a past date for a future meeting");
            throw new IllegalArgumentException() ;
        }
    }

    /**
     * Code to return a past Meeting
     * @param id the ID for the meeting
     * @return PastMeeting
     * @throws java.lang.IllegalArgumentException
     */

    @Override
    public PastMeeting getPastMeeting(int id) throws IllegalArgumentException {

        PastMeeting PM = null;

        for (int i = 0; i < Meetings.size(); i++) {
            if (id == Meetings.get(i).getId()) {
                PM = (PastMeetingImpl) Meetings.get(i);
                break;
            }
        }
        if (PM == null){
            throw new IllegalArgumentException();
        }

        return PM;
    }

    /**
     * Code to return a future Meeting
     * @param id the ID for the meeting
     * @return FutureMeeting
     * @throws java.lang.IllegalArgumentException
     */

    @Override
    public FutureMeeting getFutureMeeting(int id) throws IllegalArgumentException  {

        FutureMeeting FM = null;

        for (int i = 0; i< Meetings.size(); i++) {
            if (id == Meetings.get(i).getId()) {
                FM = (FutureMeetingImpl) Meetings.get(i);
                break;
            }
        }
        if (FM == null){
            throw new IllegalArgumentException();
        }
        return FM;
    }

    /**
     * Code to get any Meeting
     * @param id the ID for the meeting
     * @return Meeting
     * @throws java.lang.IllegalArgumentException
     */

    @Override
    public Meeting getMeeting(int id)throws IllegalArgumentException{

        Meeting retMeet = null;
        for (int i = 0; i < Meetings.size(); i++) {
            if (id == Meetings.get(i).getId()) {
                retMeet = Meetings.get(i);
                break;
            }
        }
        if (retMeet == null){
            throw new IllegalArgumentException();
        }

        return retMeet;
    }

    /**
     * Method that takes a Contact as parameter and then return a list of future meetings with that contact
     * @param contact one of the user’s contacts
     * @return List
     * @throws java.lang.IllegalArgumentException
     */

    @Override
    public List<Meeting> getFutureMeetingList(Contact contact) throws IllegalArgumentException{
        List<Meeting> FutureMeetingList = new LinkedList<Meeting>();

        for (int i=0;i< Meetings.size();i++){
            if (Meetings.get(i).getContacts().contains(contact) && Meetings.get(i).getDate().after(Calendar.getInstance())){
                FutureMeetingList.add(Meetings.get(i));
            }
        }
        if (FutureMeetingList.isEmpty()){
            throw new IllegalArgumentException();
        }

        return FutureMeetingList;
    }

    /**
     * Method to retrieve a list of future meetings from a specific calender day
     * @param date the date
     * @return List
     * @throws java.lang.IllegalArgumentException
     */

    @Override
    public List<Meeting> getFutureMeetingList(Calendar date) throws IllegalArgumentException {
        List<Meeting> FutureMeetingsList = new LinkedList<Meeting>();
        boolean sameday;
        for (int i=0; i < Meetings.size(); i++){
            sameday = Meetings.get(i).getDate().getWeekYear() == date.getWeekYear() && Meetings.get(i).getDate().get(Calendar.DAY_OF_YEAR) == date.get(Calendar.DAY_OF_YEAR);
            if (sameday){
                FutureMeetingsList.add(Meetings.get(i));
            }
        }
        if (FutureMeetingsList.isEmpty()){
            throw new IllegalArgumentException();
        }

        return FutureMeetingsList;
    }

    /**
     * Method to retrieve a list of past meetings for a specific Contact
     * @param contact one of the user’s contacts
     * @return List
     * @throws java.lang.IllegalArgumentException
     */


    @Override
    public List<PastMeeting> getPastMeetingList(Contact contact) throws IllegalArgumentException {
        List<PastMeeting> PastMeetingList = new LinkedList<PastMeeting>();

        for (int i=0;i< Meetings.size();i++){
            if (Meetings.get(i).getContacts().contains(contact) && Meetings.get(i).getDate().before(Calendar.getInstance())){
                PastMeetingList.add((PastMeeting) Meetings.get(i));
            }
        }
        if (PastMeetingList.isEmpty()){
            throw new IllegalArgumentException();
        }

        return PastMeetingList;
    }

    /**
     * Method to add a new Past Meeting
     * @param contacts a list of participants
     * @param date the date on which the meeting took place
     * @param text messages to be added about the meeting.
     * @throws java.lang.NullPointerException
     */

    @Override
    public void addNewPastMeeting(Set<Contact> contacts, Calendar date, String text) throws NullPointerException{

        if (contacts == null || date == null || text == null){
            throw new NullPointerException();
        }

        if (date.before(Calendar.getInstance())) {
            Contact[] PMContacts = ContactGetter.ConGet(contacts);
            Meeting NPM = new PastMeetingImpl(date, text, PMContacts);
            Meetings.add(NPM);
        }else{
            System.out.println("You can't have a past meeting with a future date");
        }

    }

    /**
     * Method to add meeting notes to a past Meeting
     * @param id the ID of the meeting
     * @param text messages to be added about the meeting.
     * @throws java.lang.NullPointerException
     */

    @Override
    public void addMeetingNotes(int id, String text) throws NullPointerException {


        if (text == null){
            throw new NullPointerException();
        }

            for (int i = 0; i<Meetings.size();i++){
                if (Meetings.get(i).getId() == id){
                    Meetings.set(i, new PastMeetingImpl(Meetings.get(i).getId(), Meetings.get(i).getDate(), text, ContactGetter.ConGet(Meetings.get(i).getContacts())));
                }
            }
    }

    /**
     * Method to add a new contact
     * @param name the name of the contact.
     * @param notes notes to be added about the contact.
     * @throws java.lang.NullPointerException
     */

    @Override
    public void addNewContact(String name, String notes) throws NullPointerException {

        if (name == null || notes == null){
            throw new NullPointerException();
        }

        Contact NewContact = new ContactImpl(name, notes);
        Contacts.add(NewContact);

    }

    /**
     * Method to get a list of contacts from their ID's
     * @param ids an arbitrary number of contact IDs
     * @return Set
     * @throws java.lang.IllegalArgumentException
     */

    @Override
    public Set<Contact> getContacts(int... ids) throws IllegalArgumentException {

        Set<Integer> idset = new HashSet<Integer>();
        for (Integer item : ids ) {
            idset.add(item);
        }
        Set<Contact> getConReturn = new HashSet<Contact>();
        for (int i=0; i<Contacts.size();i++){
            if (idset.contains(Contacts.get(i).getId())){
                getConReturn.add(Contacts.get(i));
            }
        }

        if (getConReturn.isEmpty()){
            throw new IllegalArgumentException();
        }

        return getConReturn;
    }

    /**
     * Method to get a set of Contacts with the same name
     * @param name the string to search for
     * @return Set
     * @throws java.lang.IllegalArgumentException
     */

    @Override
    public Set<Contact> getContacts(String name) throws IllegalArgumentException {
        Set<Contact> reqContacts = new HashSet<Contact>();
        for (int i = 0; i < Contacts.size(); i++ ){
            if (Contacts.get(i).getName().equals(name)){

                reqContacts.add(Contacts.get(i));
            }
        }
        if (reqContacts.isEmpty()){
            throw new IllegalArgumentException();
        }

        return reqContacts;
    }

    /**
     * Method to write back to the file csv file
     */

    @Override
    public void flush() {

        PrintWriter out = null;
        try {
            int id;
            int idM;
            String name;
            String notes;
            String[] Date = new String[5];

            if (ContactManagerFile == null){
                System.out.println("Please give path for new csv file");
                Scanner in = new Scanner(System.in);
                String Input = in.nextLine();
                ContactManagerFile = new File(Input);
            }

            File outfile = ContactManagerFile;
            out = new PrintWriter(outfile);

            /**
             * code to write out the contacts
             */
            for (int i = 0; i < Contacts.size(); i++) {
                id = Contacts.get(i).getId();
                name = Contacts.get(i).getName();
                notes = Contacts.get(i).getNotes();
                out.println("C," + id + "," + name + "," + notes);
            }

            /**
             * Code to write out the Meetings
             */

            for (int j = 0; j< Meetings.size();j++){
                idM = Meetings.get(j).getId();

                /**
                 * Set up the date
                 */

                Date[0]= String.valueOf(Meetings.get(j).getDate().get(Calendar.YEAR));
                Date[1]=String.valueOf(Meetings.get(j).getDate().get(Calendar.MONTH));
                Date[2]=String.valueOf(Meetings.get(j).getDate().get(Calendar.DAY_OF_MONTH));
                Date[3]=String.valueOf(Meetings.get(j).getDate().get(Calendar.HOUR_OF_DAY));
                Date[4]=String.valueOf(Meetings.get(j).getDate().get(Calendar.MINUTE));
                String Cal = Date[0] + "-" + Date[1] + "-" + Date[2] + "-" + Date[3] + "-" + Date[4];

                /**
                 * Set up meeting contacts
                 */

                String MCString = "";

                Contact[] MeetCon = ContactGetter.ConGet(Meetings.get(j).getContacts());
                for (int k = 0; k < MeetCon.length; k++){
                    if (MCString.length() == 0){
                        MCString = MCString + MeetCon[k].getId();
                    }
                    else {
                        MCString = MCString + " " + MeetCon[k].getId();
                    }
                }
                if (Meetings.get(j) instanceof FutureMeeting) {
                    out.println("M," + idM + "," + Cal + "," + MCString);
                }

                /**
                 * add notes as well if past meeting
                 */

                else {
                    PastMeetingImpl oldmeet = (PastMeetingImpl) Meetings.get(j);
                    String MNotes = oldmeet.getNotes();
                    if (MNotes.length() > 0) {
                        out.println("M," + idM + "," + Cal + "," + MCString + "," + MNotes);
                    }
                    else {
                        out.println("M," + idM + "," + Cal + "," + MCString);
                    }
                }
            }
        } catch (FileNotFoundException ex) {
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            out.close();
        }


    }
}
