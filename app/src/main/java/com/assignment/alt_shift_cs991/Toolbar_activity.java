package com.assignment.alt_shift_cs991;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class Toolbar_activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_toolbar_activity);
        initToolbar();


    }
    public void initToolbar (){
        Toolbar myToolbar = findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
    }

    // create an action bar button
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    // handle button activities
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_home) {
            Intent intent = new Intent(Toolbar_activity.this, CalendarActivity.class);
            startActivity(intent);
            return false;
        }
        else if (id == R.id.action_more) {
            Intent intent = new Intent(Toolbar_activity.this, Pending_Swaps_EMP.class);
            startActivity(intent);
            return false;
        }
        return super.onOptionsItemSelected(item);


    }

}
