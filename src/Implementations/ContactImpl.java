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


    public ContactImpl( String name, String notes){
     //   this.Id = NextId;
     //   NextId = NextId + 1;
        this.name = name;
        this.notes = notes;
     //   System.out.println("here list size is " + IdList.size());

        /**
         * code to assign a unique Id using a static linked list
         */
        int j = 0;
        for (int i = 0; i < IdList.size(); i++) {
            if (i != IdList.get(i)) {
                this.Id = i;
                IdList.add(i, i);
                break;
            }
            j=i+1;
        }
            if (j == IdList.size()){
                this.Id = j;
                IdList.add(j);
            }
        }


    public ContactImpl(int Id, String name, String notes){
        this.name = name;
        this.notes = notes;

        if (!IdList.contains(Id)){
            this.Id = Id;
            for (int i = 0; i < IdList.size();i++){
                if (IdList.get(i) > Id){
                    IdList.add(i-1, Id);
                    break;
                }
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
