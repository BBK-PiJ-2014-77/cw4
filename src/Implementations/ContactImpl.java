package Implementations;

import cw4.Contact;

import java.util.LinkedList;

/**
 * Created by digibrose on 25/02/2015.
 */
public class ContactImpl implements Contact {

    static int NextId = 0;
    private int Id;
    private String name;
    private String notes;

    static LinkedList<Integer> IdList = new LinkedList<Integer>();


    public ContactImpl( String name, String notes) {
        //   this.Id = NextId;
        //   NextId = NextId + 1;
        this.name = name;
        this.notes = notes;
        //   System.out.println("here list size is " + IdList.size());

        /**
         * code to assign a unique Id using a static linked list
         */
        int j = IdList.size();
        System.out.println("present size is " + IdList.size());
        for (int i = 0; i < j; i++) {
            if (i != IdList.get(i)) {
                this.Id = i;
                IdList.add(i, i);
                System.out.println("New Id is " + this.Id);
                break;
            }
            if (i == j - 1) {
                this.Id = IdList.size();
                IdList.add(i);
                System.out.println("New Id2 is " + this.Id);
                break;
            }
        }
    }


    public ContactImpl(int Id, String name, String notes){
        this.name = name;
        this.notes = notes;

        if (!IdList.contains(Id)){
            this.Id = Id;
            System.out.println("This Id is" + Id);
            for (int i = 0; i < IdList.size();i++) {
                if (IdList.get(i) > Id) {
                    IdList.add(i - 1, Id);
                    System.out.println("IdList is " + IdList.size());
                    break;

                }
            }
                if (Id >= IdList.size()){
                    IdList.add(Id);
                    System.out.println("IDlist2point " + IdList.size());
                }
            }
    }

    @Override
    public int getId() {
        return Id;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getNotes() {
        return notes;
    }

    @Override
    public void addNotes(String note) {
        this.notes = note;
    }
}
