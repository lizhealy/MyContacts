package com.cofc.lizhealy.mycontacts;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by lizhealy on 11/29/16.
 */

public class Contact implements Serializable {
    private String mName;
    public ArrayList<String> emails;
    public ArrayList<String> phoneNumbers;

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        this.mName = name;
    }


}
