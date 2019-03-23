package com.assignment.alt_shift_cs991.activities;

import android.os.Bundle;

import com.assignment.alt_shift_cs991.R;
import com.assignment.alt_shift_cs991.adapters.CurrentShifterAdapter;
import com.assignment.alt_shift_cs991.model.CalendarManager;
import com.assignment.alt_shift_cs991.model.Shifter;
import com.github.sundeepk.compactcalendarview.CompactCalendarView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import androidx.appcompat.app.ActionBar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


public class CalendarActivity extends ToolbarActivity {
    /**
     * Home activity displaying a Calendar with shifts
     */
    public CompactCalendarView calendarView;
    private SimpleDateFormat dateformat = new SimpleDateFormat("MMMM - yyyy", Locale.getDefault());
    private CalendarManager calendarManager = new CalendarManager();
    public static final String EXTRA_SHIFTER = "com.assignment.alt_shift_cs991.SHIFTER";
    public RecyclerView recyclerView;
    private CurrentShifterAdapter shiftAdapter;
    protected Application model;
    private Shifter shifter;

    /**
     * Initialises Calendar with shifts for the logged in user
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calendar_view);
        initToolbar();

        model = (Application) getApplication();
        final ActionBar actionBar = getSupportActionBar();
        //actionBar.setDisplayHomeAsUpEnabled(false);
        calendarView = findViewById(R.id.compactcalendar_view);
        calendarView.setUseThreeLetterAbbreviation(true);
        actionBar.setTitle(dateformat.format(new Date()));
        shifter = model.getLoggedInShifter();
        //add events
        calendarManager.shiftPopulate(calendarView, model.shiftManager.getmyShiftsDates(shifter));

        // TextView shift_descp = findViewById(R.id.shiftInfoDesc);
        // shift_descp.setVisibility(View.GONE);
        // listener
        calendarView.setListener(new CompactCalendarView.CompactCalendarViewListener() {
            /**
             * Shows a shift in a recycler list below the calendar when date is clicked
             * @param dateClicked
             */
            @Override
            public void onDayClick(Date dateClicked) {
                shiftAdapter = new CurrentShifterAdapter(model.shiftManager.getMyShiftsByDate(shifter, dateClicked.toString()));
                recyclerView = findViewById(R.id.shifter_shifts);
                recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                recyclerView.setAdapter(shiftAdapter);
                // if(shiftAdapter.getItemCount() > 0){
                // shift_descp.setVisibility(View.VISIBLE);}
                // else {
                //   shift_descp.setVisibility(View.GONE);
                // }
                // shiftAdapter.notifyDataSetChanged();
            }

            /**
             * Changes month on the calendar when scrolled
             * @param firstDayOfNewMonth
             */
            @Override
            public void onMonthScroll(Date firstDayOfNewMonth) {
                actionBar.setTitle(dateformat.format(firstDayOfNewMonth));
                //  shift_descp.setVisibility(View.GONE);

            }
        });
    }

    /**
     * Populates the calendar with shifts as events on dates
     */
    @Override
    protected void onResume() {
        super.onResume();
        calendarView.removeAllEvents();
        calendarManager.shiftPopulate(calendarView, model.shiftManager.getmyShiftsDates(shifter));
    }
}
