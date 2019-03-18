package com.assignment.alt_shift_cs991.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.assignment.alt_shift_cs991.model.Application;
import com.assignment.alt_shift_cs991.R;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

public class LoginActivity extends AppCompatActivity {

    private EditText userName, password;
    private CardView loginButton;
    private int passwordCount;
    protected Application model;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_layout);

        model = (Application)getApplication();
        userName = findViewById(R.id.editText3);
        password = findViewById(R.id.editText4);
        loginButton = findViewById(R.id.cardButton);



        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                model.clearUserData();
                model.setUserLoggedIn(false);
                if (model.shiftManager.getShifterLogin(userName.getText().toString(), password.getText().toString()) != null){
                    model.setUserLoggedIn(true);
                    model.storedLoggedInUser(model.shiftManager.getShifter(userName.getText().toString(), password.getText().toString()));
                    model.getLoggedInShifter();
                    Intent intent = new Intent(getApplicationContext(), CalendarActivity.class);
                    intent.putExtra("SHIFTER1", model.getLoggedInShifter().getFirstName());
                    startActivity(intent);
                    Toast.makeText(getApplicationContext(), "Hello " + model.getLoggedInShifter().getFirstName() + "!", Toast.LENGTH_SHORT).show();
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
    public void register(View v){

        Intent intent = new Intent(getApplicationContext(), RegisterActivity.class);
        startActivity(intent);
    }
}
