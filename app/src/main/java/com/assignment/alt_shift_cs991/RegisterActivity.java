package com.assignment.alt_shift_cs991;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

public class RegisterActivity extends AppCompatActivity {

    private EditText name, surname, userName, password;
    private CardView registerButton;
    protected AltShift_Application model;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_new_shifter);

        model = (AltShift_Application) getApplication();
        name = findViewById(R.id.editText9);
        surname = findViewById(R.id.editText10);
        userName = findViewById(R.id.editText11);
        password = findViewById(R.id.editText12);
        registerButton = findViewById(R.id.cardButton);


        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (userName.getText().toString().isEmpty() || password.getText().toString().isEmpty() || name.getText().toString().isEmpty() || surname.getText().toString().isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Please fill in missing information!", Toast.LENGTH_SHORT).show();
                } else {
                    model.addShifter(userName.getText().toString(), password.getText().toString(), name.getText().toString(), surname.getText().toString());
                    Intent intent = new Intent(getApplicationContext(), Login_Activity.class);
                    startActivity(intent);
                }
            }
        });
    }
}
