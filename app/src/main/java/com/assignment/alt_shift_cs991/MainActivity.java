package com.assignment.alt_shift_cs991;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private FragmentManager fm;
    private String tag;
    private ShiftSwapDialogue swap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        swap = new ShiftSwapDialogue();
        FragmentManager fm = getSupportFragmentManager();

        swap.show(fm, tag);
    }

}
