package com.assignment.alt_shift_cs991.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.assignment.alt_shift_cs991.model.Application;
import com.assignment.alt_shift_cs991.R;
import com.assignment.alt_shift_cs991.model.Shifter;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

public class RegisterActivity extends AppCompatActivity {

    private EditText name, surname, userName, password;
    private CardView registerButton;
    private CheckBox isManagerCheck;
    protected Application model;
    private Shifter shifter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_new_shifter);

        model = (Application) getApplication();
        name = findViewById(R.id.editText9);
        surname = findViewById(R.id.editText10);
        userName = findViewById(R.id.editText11);
        password = findViewById(R.id.editText12);
        registerButton = findViewById(R.id.cardButton);
        isManagerCheck = findViewById(R.id.isManger);

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (userName.getText().toString().isEmpty() || password.getText().toString().isEmpty() || name.getText().toString().isEmpty() || surname.getText().toString().isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Please fill in missing information!", Toast.LENGTH_SHORT).show();
                } else {
                    shifter = new Shifter(Integer.parseInt(userName.getText().toString()), password.getText().toString(), name.getText().toString(), surname.getText().toString());
                    if(isManagerCheck.isChecked()){
                        shifter.setManager(true);
                    }
                    model.db.daoAccess().insertShifter(shifter);
                    model.shiftManager.addShifter(shifter);
                    Toast.makeText(getApplicationContext(), "Shifter added to database", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                    startActivity(intent);
                }
            }
        });
    }
}
