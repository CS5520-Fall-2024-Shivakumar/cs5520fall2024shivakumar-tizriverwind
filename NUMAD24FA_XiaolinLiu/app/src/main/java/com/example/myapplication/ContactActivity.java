package com.example.myapplication;

import android.os.Bundle;
import android.widget.Button;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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
        fab.setOnClickListener(view -> {
            ContactModel newContact = new ContactModel("New Contact", "123456");
            contactAdapter.addContact(newContact);
            Snackbar.make(view, "Contact added", Snackbar.LENGTH_LONG)
                    .setAction("Undo", v -> {
                        contactAdapter.removeContact(newContact);
                    }).show();
        });

        Button backButton = findViewById(R.id.buttonBackToMain);
        backButton.setOnClickListener(v -> finish());
    }

    private void displayItems() {
        contactModelList = new ArrayList<>();
        recyclerView = findViewById(R.id.rvContacts);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(this,1));
        contactModelList.add(new ContactModel("Godric Griffindor", "001"));
        contactModelList.add(new ContactModel("Helga Hufflepuff", "002"));
        contactModelList.add(new ContactModel("Rowena Ravenclaw", "003"));
        contactModelList.add(new ContactModel("Salazar Slytherin", "004"));
        contactAdapter = new ContactAdapter(contactModelList, this);
        recyclerView.setAdapter(contactAdapter);
    }

}