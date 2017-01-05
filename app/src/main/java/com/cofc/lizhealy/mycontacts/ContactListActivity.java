package com.cofc.lizhealy.mycontacts;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class ContactListActivity extends ActionBarActivity {

    private ContactList mContacts;
    private ContactAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_list);

        mContacts = ContactList.getInstance();

        for (int i = 0; i < 30; i++) {
            Contact contact1 = new Contact();
            contact1.setName("Liz Healy");
            contact1.emails = new ArrayList<String>();
            contact1.emails.add("lizhealy89@gmail.com");
            contact1.emails.add("lizhealy88@gmail.com");
            contact1.phoneNumbers = new ArrayList<String>();
            contact1.phoneNumbers.add("412-953-8000");
            contact1.phoneNumbers.add("412-953-8001");
            mContacts.add(contact1);
        }

        ListView listView = (ListView)findViewById(R.id.contact_list_view);
        mAdapter = new ContactAdapter(mContacts);
        listView.setAdapter(mAdapter);
        listView.setOnScrollListener(new AbsListView.OnScrollListener() {
            int previousFirstItem = 0;
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {

            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                if (firstVisibleItem > previousFirstItem) {
                    getSupportActionBar().hide();
                }
                else if (firstVisibleItem < previousFirstItem) {
                    getSupportActionBar().show();
                }
                previousFirstItem = firstVisibleItem;
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent(ContactListActivity.this, ContactViewActivity.class);
                i.putExtra(ContactViewActivity.EXTRA, position);
                startActivity(i);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        mAdapter.notifyDataSetChanged();
    }

    private class ContactAdapter extends ArrayAdapter<Contact> {
        ContactAdapter(ArrayList<Contact> contacts) {
            super(ContactListActivity.this, R.layout.contact_list_row, R.id.contact_row_name, contacts);
        }

        @NonNull
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            convertView = super.getView(position, convertView, parent);

            Contact contact = getItem(position);

            TextView nameTextView = (TextView)findViewById(R.id.contact_row_name);
            if (nameTextView != null) {
                nameTextView.setText(contact.getName());
            }
            return convertView;
        }
    }
}
