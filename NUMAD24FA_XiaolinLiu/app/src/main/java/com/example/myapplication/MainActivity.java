package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    Button button_am, button_qc;

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
        button_am.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Inflate custom toast layout
                LayoutInflater inflater = getLayoutInflater();
                View layout = inflater.inflate(R.layout.custom_toast, findViewById(R.id.custom_toast_container));

                // Set text for custom toast
                TextView text = layout.findViewById(R.id.toast_text);
                text.setText("Xiaolin Liu\nliu.xiaolin@northeastern.edu");

                // Create and show the custom toast
                Toast toast = new Toast(getApplicationContext());
                toast.setGravity(Gravity.CENTER, 0, 0); // Center the toast
                toast.setDuration(Toast.LENGTH_LONG);
                toast.setView(layout);
                toast.show();
            }
        });

        button_qc = findViewById(R.id.button_quickCalc);
        button_qc.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, QuickCalcActivity.class);
            startActivity(intent);
        });
    }
}
