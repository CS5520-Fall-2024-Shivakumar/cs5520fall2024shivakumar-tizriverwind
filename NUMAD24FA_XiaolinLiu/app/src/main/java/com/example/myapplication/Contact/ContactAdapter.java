package com.example.myapplication.Contact;

import android.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

import android.content.Intent;
import android.net.Uri;

import com.example.myapplication.R;


public class ContactAdapter extends RecyclerView.Adapter<ContactViewHolder> {
    private final List<ContactModel> contactsList;
    private final ContactActivity activity;

    public ContactAdapter(List<ContactModel> contactsList, ContactActivity activity) {
        this.contactsList = contactsList;
        this.activity = activity;
    }


    @NonNull
    @Override
    public ContactViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(activity)
                .inflate(R.layout.single_contact, parent, false);
        return new ContactViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ContactViewHolder holder, int position) {
        ContactModel contact = contactsList.get(position);
        holder.name.setText(contact.getName());
        holder.phoneNum.setText(contact.getPhoneNum());

        holder.itemView.setOnClickListener(v->{
            Intent callIntent = new Intent(Intent.ACTION_DIAL);
            callIntent.setData(Uri.parse("tel:" + contact.getPhoneNum()));
            activity.startActivity(callIntent);

        });

        // Long click to edit or delete existing contacts
        holder.itemView.setOnLongClickListener( v-> {
            new AlertDialog.Builder(activity).setTitle("Choose an action")
                    .setItems(new String[]{"Edit","Delete"},
                    (dialog, which) ->{
                if (which == 0) {
                    activity.showEditContactDialog(contact, position);
                } else if (which == 1) {
                    activity.deleteContact(contact, position);
                }
            }).show();
            return true;
        });
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