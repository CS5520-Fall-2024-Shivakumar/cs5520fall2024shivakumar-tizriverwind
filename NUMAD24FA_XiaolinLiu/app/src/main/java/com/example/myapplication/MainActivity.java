package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;

import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.myapplication.Contact.ContactActivity;

public class MainActivity extends AppCompatActivity {

    Button button_am, button_qc, button_contact;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        button_am = findViewById(R.id.button_aboutMe);
        button_am.setOnClickListener(v -> {
           Intent intent = new Intent(MainActivity.this, AboutMeActivity.class);
           startActivity(intent);
        });

        button_qc = findViewById(R.id.button_quickCalc);
        button_qc.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, QuickCalcActivity.class);
            startActivity(intent);
        });

        button_contact = findViewById(R.id.button_contacts);
        button_contact.setOnClickListener(v->{
            Intent intent = new Intent(MainActivity.this, ContactActivity.class);
            startActivity(intent);
        });
    }
}
