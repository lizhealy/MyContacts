package com.cofc.lizhealy.mycontacts;

import java.util.ArrayList;

/**
 * Created by lizhealy on 1/4/17.
 */

public class ContactList extends ArrayList<Contact> {
    private static ContactList sInstance = null;
    private ContactList(){}

    public static ContactList getInstance() {
        if (sInstance == null) {
            sInstance = new ContactList();
        }
        return sInstance;
    }
}
