package com.assignment.alt_shift_cs991.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.assignment.alt_shift_cs991.R;
import com.assignment.alt_shift_cs991.model.Application;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class ToolbarActivity extends AppCompatActivity {
    private TextView textSwapItemCount;
    protected Application model;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_toolbar_activity);
        initToolbar();
        model = (Application)getApplication();


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
        textSwapItemCount = actionView.findViewById(R.id.cart_badge);

        setupBadge(model.shiftManager.getCountAvailableSwaps(model.getLoggedInShifter()));

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
            Intent intent = new Intent(this, CalendarActivity.class);
            startActivity(intent);
            return false;
        }
        else if (id == R.id.action_more) {
            Intent intent = new Intent(ToolbarActivity.this, PendingSwapsEmp.class);
            startActivity(intent);
            return false;
        }
        return super.onOptionsItemSelected(item);
    }

    private void setupBadge(int mItemCount) {

        if (textSwapItemCount != null) {
            if (mItemCount == 0) {
                if (textSwapItemCount.getVisibility() != View.GONE) {
                    textSwapItemCount.setVisibility(View.GONE);
                }
            } else {
                textSwapItemCount.setText(String.valueOf(Math.min(mItemCount, 99)));
                if (textSwapItemCount.getVisibility() != View.VISIBLE) {
                    textSwapItemCount.setVisibility(View.VISIBLE);
                }
            }
        }
    }


}



