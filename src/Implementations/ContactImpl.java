package Implementations;

import cw4.Contact;

import java.util.LinkedList;

/**
 * Created by digibrose on 25/02/2015.
 */
public class ContactImpl implements Contact {

    private int Id;
    private String name;
    private String notes;

    private static LinkedList<Integer> IdList = new LinkedList<Integer>();


    public ContactImpl( String name, String notes) {
        this.name = name;
        this.notes = notes;

        /**
         * code to assign a unique Id using a static linked list
         */

        int j = IdList.size();
        if (IdList.isEmpty()){
            this.Id = 0;
            IdList.add(0);
        }
        for (int i = 0; i < j; i++) {
            if (i != IdList.get(i )) {
                this.Id = i;
                IdList.add(i, i);
                break;
            }
            if (i == j - 1) {
                this.Id = IdList.size();
                IdList.add(j);
                break;
            }

        }
    }

    /**
     * Second Constructor Method to create Contacts with Id's assigned, only to be used on startup to read from file
     * @param Id
     * @param name
     * @param notes
     */

    public ContactImpl(int Id, String name, String notes){
        this.name = name;
        this.notes = notes;

        this.Id = Id;
        if (!IdList.contains(Id)) {
            int ILength = IdList.size();
            if (!IdList.isEmpty()) {
                for (int i = 0; i < ILength; i++) {
                    if (IdList.get(i) > Id) {
                        IdList.add(i, Id);
                        break;
                    }
                    if (i == ILength - 1) {
                        IdList.add(Id);
                    }
                }
            } else {
                IdList.add(Id);
            }
        }
    }

    /**
     * Method to return ContactId
     * @return int Id
     */

    @Override
    public int getId() {
        return Id;
    }

    /**
     * Method to return Contact Name
     * @return String Name
     */

    @Override
    public String getName() {
        return name;
    }

    /**
     * Method to return Contact Notes
     * @return String Notes
     */

    @Override
    public String getNotes() {
        return notes;
    }

    /**
     * Method to add notes to Contact
     * @param note the notes to be added
     */

    @Override
    public void addNotes(String note) {
        this.notes = note;
    }
}
