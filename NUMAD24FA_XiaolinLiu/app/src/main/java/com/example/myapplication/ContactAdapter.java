package com.example.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

public class ContactAdapter extends RecyclerView.Adapter<ContactViewHolder> {
    private List<ContactModel> contactsList = new ArrayList<>();
    private Context context;

    public ContactAdapter(List<ContactModel> contactsList, Context context) {
        this.contactsList = contactsList;
        this.context = context;
    }


    @NonNull
    @Override
    public ContactViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context)
                .inflate(R.layout.single_contact, parent, false);
        return new ContactViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ContactViewHolder holder, int position) {
        ContactModel contact = contactsList.get(position);
        holder.name.setText(contact.getName());
        holder.phoneNum.setText(contact.getPhoneNum());
    }

    @Override
    public int getItemCount() {
        return contactsList.size();
    }

    public void addContact(ContactModel contact) {
        contactsList.add(contact);
        notifyItemInserted(contactsList.size() - 1);
    }


    public void removeContact(ContactModel contact) {
        int position = contactsList.indexOf(contact);
        if (position>=0) {
            contactsList.remove(position);
            notifyItemRemoved(position);

    }
}
}