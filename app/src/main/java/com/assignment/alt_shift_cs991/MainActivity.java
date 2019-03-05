package com.assignment.alt_shift_cs991;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void clickSwap(View v){
        Intent launchSwap = new Intent(getApplicationContext(), SwapActivity.class);
        startActivity(launchSwap);
    }

    public void clickCalendar(View v){
        Intent launchCalendar = new Intent(getApplicationContext(), CalendarActivity.class);
        startActivity(launchCalendar);
    }

}

