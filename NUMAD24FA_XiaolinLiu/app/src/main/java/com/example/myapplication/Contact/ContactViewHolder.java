package com.example.myapplication.Contact;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;

public class ContactViewHolder extends RecyclerView.ViewHolder {
    public TextView name, phoneNum;
    public ContactViewHolder(@NonNull View itemView) {
        super(itemView);
        name = itemView.findViewById(R.id.contactsName);
        phoneNum = itemView.findViewById(R.id.contactsPhone);
    }
}
