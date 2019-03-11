package com.assignment.alt_shift_cs991;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class Toolbar_activity extends AppCompatActivity {
    private TextView textCartItemCount;
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

        final MenuItem menuItem = menu.findItem(R.id.action_more);

        View actionView = menuItem.getActionView();
        textCartItemCount = actionView.findViewById(R.id.cart_badge);

/** REPLACE 2 WITH VALUE OF PENDING SHIFTS FROM BACKEND!!!!!*/
        setupBadge(2);

        actionView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onOptionsItemSelected(menuItem);
            }
        });

        return true;

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

    private void setupBadge(int mItemCount) {

        if (textCartItemCount != null) {
            if (mItemCount == 0) {
                if (textCartItemCount.getVisibility() != View.GONE) {
                    textCartItemCount.setVisibility(View.GONE);
                }
            } else {
                textCartItemCount.setText(String.valueOf(Math.min(mItemCount, 99)));
                if (textCartItemCount.getVisibility() != View.VISIBLE) {
                    textCartItemCount.setVisibility(View.VISIBLE);
                }
            }
        }
    }


}



