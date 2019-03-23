package com.assignment.alt_shift_cs991.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.assignment.alt_shift_cs991.R;
import com.assignment.alt_shift_cs991.adapters.CurrentShifterAdapter;
import com.assignment.alt_shift_cs991.model.Application;
import com.assignment.alt_shift_cs991.model.CalendarManager;
import com.github.sundeepk.compactcalendarview.CompactCalendarView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import androidx.appcompat.app.ActionBar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class ManagerCalendarActivity extends CalendarActivity {
    /**
     * Calendar activity for Manager users
     */
    public CompactCalendarView calendarView;
    private SimpleDateFormat dateformat = new SimpleDateFormat("MMMM - yyyy", Locale.getDefault());
    private CalendarManager calendarManager = new CalendarManager();
    public RecyclerView recyclerView;
    private CurrentShifterAdapter shiftAdapter;
    protected Application model;
    private FloatingActionButton fab;

    /**
     * Initialises activity with all shifts
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.manager_calendar_view);
        initToolbar();

        model = (Application) getApplication();
        fab = findViewById(R.id.floatingActionButton);
        final ActionBar actionBar = getSupportActionBar();
        calendarView = findViewById(R.id.compactcalendar_view);
        calendarView.setUseThreeLetterAbbreviation(true);
        actionBar.setTitle(dateformat.format(new Date()));
        calendarManager.shiftPopulate(calendarView, model.shiftManager.getAllShiftsDates());
        shiftAdapter = new CurrentShifterAdapter(model.shiftManager.getAllShiftsByDate(model.getDateClicked()));
        recyclerView = findViewById(R.id.shifter_shifts);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        recyclerView.setAdapter(shiftAdapter);

        calendarView.setListener(new CompactCalendarView.CompactCalendarViewListener() {
            /**
             * Shows all shifts on clicked date
             * @param dateClicked
             */
            @Override
            public void onDayClick(Date dateClicked) {
                model.setDateClicked(dateClicked.toString());
                shiftAdapter.setItems(model.shiftManager.getAllShiftsByDate(dateClicked.toString()));
            }

            /**
             * Changes month on the calendar when scrolled
             * @param firstDayOfNewMonth
             */
            @Override
            public void onMonthScroll(Date firstDayOfNewMonth) {
                actionBar.setTitle(dateformat.format(firstDayOfNewMonth));
            }
        });
        fab.setOnClickListener(new View.OnClickListener() {
            /**
             * Takes user to activity to create a new shift on clicked date
             * @param v
             */
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ShiftAddingActivity.class);
                startActivity(intent);
            }
        });
    }

    /**
     * Populates the calendar with shifts as events on dates
     */
    @Override
    protected void onResume() {
        super.onResume();
        calendarManager.shiftPopulate(calendarView, model.shiftManager.getAllShiftsDates());
        shiftAdapter.setItems(model.shiftManager.getAllShiftsByDate(model.getDateClicked()));
    }
}
