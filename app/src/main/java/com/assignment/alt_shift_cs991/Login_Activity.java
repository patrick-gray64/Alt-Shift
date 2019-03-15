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
    protected AltShift_Application model;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_layout);

        model = (AltShift_Application)getApplication();
        userName = findViewById(R.id.editText3);
        password = findViewById(R.id.editText4);
        loginButton = findViewById(R.id.cardButton);



        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                model.clearLoggedIn();
                model.setLoggedInUser(false);
                if (model.accessGetShifterLogin(userName.getText().toString(), password.getText().toString()) != null){
                    model.setLoggedInUser(true);
                    model.storedLoggedInUser(model.accessGetShifter(userName.getText().toString(), password.getText().toString()));
                    model.getLoggedInUser();
                    Intent intent = new Intent(getApplicationContext(), CalendarActivity.class);
                    intent.putExtra("SHIFTER1", model.getLoggedInUser().getFirstName());
                    startActivity(intent);
                    Toast.makeText(getApplicationContext(), "Hello " + model.getLoggedInUser().getFirstName() + "!", Toast.LENGTH_SHORT).show();
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
