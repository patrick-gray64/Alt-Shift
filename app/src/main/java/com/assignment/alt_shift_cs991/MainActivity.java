package com.assignment.alt_shift_cs991;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private FragmentManager fm;
    private String tag;
    private ShiftSwapDialogue swap;

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
    public void pending (View view){
        Intent intent = new Intent(this, Pending_Swaps_EMP.class);
        startActivity(intent);
    }

}
