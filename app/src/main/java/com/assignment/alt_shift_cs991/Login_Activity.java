package com.assignment.alt_shift_cs991;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

public class Login_Activity extends AppCompatActivity {

    private EditText userName, password;
    private CardView loginButton;
    private int passwordCount;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_layout);

        userName = findViewById(R.id.editText3);
        password = findViewById(R.id.editText4);
        loginButton = findViewById(R.id.cardButton);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (userName.getText().toString().equals("admin") && password.getText().toString().equals("password")) {

                    Intent intent = new Intent(getApplicationContext(), CalendarActivity.class);
                    startActivity(intent);
                } else {

                    Toast.makeText(getApplicationContext(), "Wrong Username or Password, please try again", Toast.LENGTH_SHORT).show();

                    passwordCount++;
                    if(passwordCount > 2){
                        loginButton.setEnabled(false);
                        Toast.makeText(getApplicationContext(),"Attempt limit reached, please try again later", Toast.LENGTH_SHORT).show();

                    }
                }
            }
        });
    }
}
