package com.example.myapplication;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class QuickCalcActivity extends AppCompatActivity implements View.OnClickListener {

    TextView resultDisplay, calDisplay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_quick_calc);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        calDisplay = findViewById(R.id.calcDisplay);
        resultDisplay = findViewById(R.id.resultDisplay);

        callButton(R.id.button0);
        callButton(R.id.button1);
        callButton(R.id.button2);
        callButton(R.id.button3);
        callButton(R.id.button4);
        callButton(R.id.button5);
        callButton(R.id.button6);
        callButton(R.id.button7);
        callButton(R.id.button8);
        callButton(R.id.button9);
        callButton(R.id.buttonPlus);
        callButton(R.id.buttonMinus);
        callButton(R.id.buttonBack);
        callButton(R.id.buttonEquals);

}

    private void callButton(int id) {
        Button button = findViewById(id);
        button.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Button button = (Button) v;
        String displayText = calDisplay.getText().toString();
        String buttonText = button.getText().toString();

        switch (buttonText) {
            case "x":
                if (!displayText.isEmpty()){
                    displayText = displayText.substring(0,displayText.length()-1);
                    break;
                }
            case "=":
                int result = calculation(displayText);
                resultDisplay.setText(String.valueOf(result));
                break;

            default:
                displayText = displayText + buttonText;
                break;
        }
        calDisplay.setText(displayText);
    }

    private int calculation(String input) {
        String[] temps;
        int result = 0;

        if (input.contains("+")) {
            temps = input.split("\\+");
            if (temps.length == 2 && isNumeric(temps[0]) && isNumeric(temps[1])) {
                result = Integer.parseInt(temps[0]) + Integer.parseInt(temps[1]);
            } else {
                resultDisplay.setText(getString(R.string.invalid_input));
            }
        } else if (input.contains("-")) {
            temps = input.split("-");
            if (temps.length == 2 && isNumeric(temps[0]) && isNumeric(temps[1])) {
                result = Integer.parseInt(temps[0]) - Integer.parseInt(temps[1]);
            } else {
                resultDisplay.setText(getString(R.string.invalid_input));
            }
        }

        return result;
    }

    private boolean isNumeric(String str) {
        if (str == null || str.isEmpty()) {
            return false;
        }
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}