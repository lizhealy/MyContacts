package com.cofc.lizhealy.mycontacts;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class ContactEditActivity extends AppCompatActivity {

    public static final String EXTRA = "CEA_EXTRA";
    public static final String TAG = "ContactEditActivity";
    private Contact mContact;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_edit);

        int position = getIntent().getIntExtra(EXTRA, 0);
        mContact = ContactList.getInstance().get(position);

        Toolbar toolbar = (Toolbar)findViewById(R.id.contact_edit_toolbar);
        toolbar.setTitle(getResources().getString(R.string.edit_contact));
        toolbar.setNavigationIcon(R.drawable.ic_done);
        toolbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText editName = (EditText)findViewById(R.id.contact_edit_name);
                mContact.setName(editName.getText().toString());

                mContact.phoneNumbers = getSectionValues(R.id.phonenumber_section);
                mContact.emails = getSectionValues(R.id.email_section);

                Toast.makeText(ContactEditActivity.this, "Saved Contact", Toast.LENGTH_SHORT)
                        .show();

                finish();

            }
        });

        EditText editName = (EditText)findViewById(R.id.contact_edit_name);
        editName.setText(mContact.getName());

        addToSection(R.id.phonenumber_section, mContact.phoneNumbers);
        addToSection(R.id.email_section, mContact.emails);

        TextView addNewPhoneNumber = (TextView)findViewById(R.id.add_new_phonenumber);
        addNewPhoneNumber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addToSection(R.id.phonenumber_section, "");
            }
        });

        TextView addNewEmail = (TextView)findViewById(R.id.add_new_email);
        addNewEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addToSection(R.id.email_section, "");
            }
        });

    }

    private ArrayList<String> getSectionValues(int sectionID) {
        ArrayList<String> values = new ArrayList<String>();
        LinearLayout section = (LinearLayout)findViewById(sectionID);
        for (int i = 0; i < section.getChildCount(); i++) {
            EditText editNumber = (EditText)section.getChildAt(i);
            values.add(editNumber.getText().toString());
        }
        return values;
    }

    private void addToSection(int sectionID, String value) {
        LinearLayout section = (LinearLayout)findViewById(sectionID);
        EditText et = new EditText(this);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        et.setLayoutParams(lp);
        et.setText(value);
        section.addView(et);
    }

    private void addToSection(int sectionID, ArrayList<String> values) {
        LinearLayout section = (LinearLayout)findViewById(sectionID);
        for (int i = 0; i < values.size(); i++) {
            EditText et = new EditText(this);
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            et.setLayoutParams(lp);
            et.setText(values.get(i));
            section.addView(et);
        }
    }
}
