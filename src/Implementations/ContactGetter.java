package Implementations;

import cw4.Contact;

import java.util.Set;

/**
 * Created by digibrose on 17/03/2015.
 */
public class ContactGetter {

    /**
     * Static Method to extract an array from a set of contacts which is easier to handle
     * @param ConSet
     * @return
     */

    public static Contact[] ConGet(Set<Contact> ConSet){
        Contact[] Con = new ContactImpl[ConSet.size()];
        ConSet.toArray(Con);
        return Con;
    }

}
