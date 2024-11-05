package com.example.myapplication.Contact;

import android.os.Bundle;
import android.widget.Button;

import com.example.myapplication.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.InputType;
import android.widget.EditText;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

public class ContactActivity extends AppCompatActivity {
    private ContactAdapter contactAdapter;
    RecyclerView recyclerView;
    List<ContactModel> contactModelList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts);
        displayItems();

        FloatingActionButton fab = findViewById(R.id.fabAddContact);
        fab.setOnClickListener(view -> showAddContactDialog());

        Button backButton = findViewById(R.id.buttonBackToMain);
        backButton.setOnClickListener(v -> finish());
    }

    public void showAddContactDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Add New Contact");

        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);
        layout.setPadding(50,20,50,20);

        final EditText nameInput = new EditText(this);
        nameInput.setHint("Name");
        nameInput.setInputType(InputType.TYPE_CLASS_TEXT);
        layout.addView(nameInput);

        final EditText phoneInput = new EditText(this);
        phoneInput.setHint("Phone Number");
        phoneInput.setInputType(InputType.TYPE_CLASS_PHONE);
        layout.addView(phoneInput);

        builder.setView(layout);

        // FAB add button
        builder.setPositiveButton("Add",((dialog, which) -> {
            String name = nameInput.getText().toString().trim();
            String phone = phoneInput.getText().toString().trim();
            if (!name.isEmpty() && !phone.isEmpty()) {
                ContactModel newContact = new ContactModel(name, phone);
                contactAdapter.addContact(newContact);
                Snackbar.make(findViewById(R.id.fabAddContact), "Contact added! 🎉",
                                Snackbar.LENGTH_LONG)
                        .setAction("Undo", v -> contactAdapter.removeContact(newContact))
                        .show();
            }}));

        // Cancel button to dismiss dialog
        builder.setNegativeButton("Cancel",((dialog, which) -> dialog.cancel()));

        builder.show();
    }

    public void showEditContactDialog(ContactModel contact, int position) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Edit Contact");


        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);
        layout.setPadding(50,20,50,20);

        final EditText nameInput = new EditText(this);
        nameInput.setHint("Name");
        nameInput.setText(contact.getName());
        nameInput.setInputType(InputType.TYPE_CLASS_TEXT);
        layout.addView(nameInput);

        final EditText phoneInput = new EditText(this);
        phoneInput.setHint("Phone Number");
        phoneInput.setText(contact.getPhoneNum());
        phoneInput.setInputType(InputType.TYPE_CLASS_PHONE);
        layout.addView(phoneInput);

        builder.setView(layout);

        builder.setPositiveButton("Save", (dialog, which) -> {
            String newName = nameInput.getText().toString().trim();
            String newPhone = phoneInput.getText().toString().trim();

            if (!newName.isEmpty() && !newPhone.isEmpty()) {
                contact.setName(newName);
                contact.setPhoneNum(newPhone);
                contactAdapter.notifyItemChanged(position);
                Snackbar.make(findViewById(R.id.fabAddContact), "Contact updated!",
                        Snackbar.LENGTH_SHORT).show();
            } else {
                Snackbar.make(findViewById(R.id.fabAddContact), "Fields cannot be empty",
                        Snackbar.LENGTH_SHORT).show();
            }});

        builder.setNegativeButton("Cancel", (dialog, which) -> dialog.cancel());
        builder.show();

    }

    public void deleteContact(ContactModel contact, int position) {
        new AlertDialog.Builder(this).setTitle("Delete")
                .setMessage("Confirm to delete this contact?")
                .setPositiveButton("Yes", ((dialog, which) -> {
                    contactAdapter.removeContact(contact);
                    Snackbar.make(findViewById(R.id.fabAddContact),
                    "Contact deleted", Snackbar.LENGTH_SHORT).show();
                }))
                .setNegativeButton("No", (dialog, which) -> dialog.cancel()).show();
    }

    private void displayItems() {
        contactModelList = new ArrayList<>();
        recyclerView = findViewById(R.id.rvContacts);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(this,1));

        contactModelList.add(new ContactModel("Godric Griffindor", "001-001-1234"));
        contactModelList.add(new ContactModel("Helga Hufflepuff", "002-002-1234"));
        contactModelList.add(new ContactModel("Rowena Ravenclaw", "003-003-1234"));
        contactModelList.add(new ContactModel("Salazar Slytherin", "004-004-1234"));
        contactModelList.add(new ContactModel("Me", "412-320-9274"));

        contactAdapter = new ContactAdapter(contactModelList, this);
        recyclerView.setAdapter(contactAdapter);
    }

}