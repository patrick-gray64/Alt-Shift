package com.assignment.alt_shift_cs991.activities;

import android.content.Intent;
import android.icu.util.Calendar;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.assignment.alt_shift_cs991.R;
import com.assignment.alt_shift_cs991.adapters.CalendarManager;
import com.assignment.alt_shift_cs991.adapters.ManagerAdapter;
import com.github.sundeepk.compactcalendarview.CompactCalendarView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import androidx.appcompat.app.ActionBar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

/**
 * Calendar activity for Manager users.
 */
public class ManagerCalendarActivity extends CalendarActivity {

    public CompactCalendarView calendarView;
    private SimpleDateFormat dateformat = new SimpleDateFormat("MMMM - yyyy", Locale.getDefault());
    private CalendarManager calendarManager = new CalendarManager();
    public RecyclerView recyclerView;
    private ManagerAdapter shifterAdapter;
    protected Application model;
    private FloatingActionButton fab;
    private Boolean isShowing;

    /**
     * Initialises activity with all shifts.
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
        shifterAdapter = new ManagerAdapter(model.shiftManager.getAllShiftsByDate(model.getDateClicked()));
        recyclerView = findViewById(R.id.shifter_shifts);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        recyclerView.setAdapter(shifterAdapter);
        isShowing = true;

        calendarView.setListener(new CompactCalendarView.CompactCalendarViewListener() {
            /**
             * Shows all shifts on clicked date.
             * @param dateClicked
             */
            @Override
            public void onDayClick(Date dateClicked) {
                model.setDateClicked(dateClicked.toString());
                shifterAdapter.setItems(model.shiftManager.getAllShiftsByDate(dateClicked.toString()));
            }

            /**
             * Changes month on the calendar when scrolled.
             * @param firstDayOfNewMonth
             */
            @Override
            public void onMonthScroll(Date firstDayOfNewMonth) {
                actionBar.setTitle(dateformat.format(firstDayOfNewMonth));
            }
        });
        fab.setOnClickListener(new View.OnClickListener() {
            /**
             * Takes user to activity to create a new shift on clicked date.
             * @param v
             */
            @Override
            public void onClick(View v) {
                SimpleDateFormat dateFormat = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy", new Locale("en_GB"));
                Date dateClicked = new Date();
                Date today = Calendar.getInstance().getTime();
                try {
                    dateClicked = dateFormat.parse(model.getDateClicked());
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                if(dateClicked.after(today)) {
                    Intent intent = new Intent(getApplicationContext(), ShiftAddingActivity.class);
                    startActivity(intent);
                }
                else{
                    Toast.makeText(getApplicationContext(), "You cannot assign a shift to a date that has passed.", Toast.LENGTH_LONG).show();
                }
            }
        });

        Button hideShow = findViewById(R.id.hideShowCal);
        hideShow.setOnClickListener(v -> {
            if(isShowing){
                calendarView.hideCalendar();
                isShowing = false;
                hideShow.setText(R.string.Show_Calender);
            }
            else{
                calendarView.showCalendar();
                isShowing = true;
                hideShow.setText(R.string.Hide_Calender);
            }
        });
    }

    /**
     * Populates the calendar with shifts as events on dates.
     */
    @Override
    protected void onResume() {
        super.onResume();
        calendarManager.shiftPopulate(calendarView, model.shiftManager.getAllShiftsDates());
        shifterAdapter.setItems(model.shiftManager.getAllShiftsByDate(model.getDateClicked()));
    }
}
