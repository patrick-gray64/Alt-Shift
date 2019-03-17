package com.assignment.alt_shift_cs991;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.github.sundeepk.compactcalendarview.CompactCalendarView;
import com.github.sundeepk.compactcalendarview.domain.Event;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import androidx.appcompat.app.ActionBar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import static android.content.Intent.EXTRA_TEXT;


public class CalendarActivity extends Toolbar_activity {


    public CompactCalendarView calendarView;
    private SimpleDateFormat dateformat = new SimpleDateFormat("MMMM - yyyy", Locale.getDefault());
    private CalendarManager calendarManager = new CalendarManager();
    public static final String EXTRA_SHIFTER = "com.assignment.alt_shift_cs991.SHIFTER";
    public RecyclerView recyclerView;
    private CurrentShifterAdapter shiftAdapter;
    protected AltShift_Application model;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calendar_view);

        initToolbar();
        model = (AltShift_Application)getApplication();
        final ActionBar actionBar = getSupportActionBar();

        recyclerView = findViewById(R.id.shifter_shifts);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        calendarView = findViewById(R.id.compactcalendar_view);
        calendarView.setUseThreeLetterAbbreviation(true);

        actionBar.setTitle(dateformat.format(new Date()));

        //add shifts to calendar
        calendarManager.shiftPopulate(calendarView, model.getLoggedInUser());

        // listener for calendar days
        calendarView.setListener(new CompactCalendarView.CompactCalendarViewListener() {
            @Override
            public void onDayClick(Date dateClicked) {
                /**
                 * Retrieves shift data for date clicked and displays in recyclerview
                 * Displays message if there are no shifts on the selected day
                 */

                Context context = getApplicationContext();
                List<Event> events = calendarView.getEvents(dateClicked);
                TextView emptyView = (TextView) findViewById(R.id.empty_view);
                //TextView yourShifts = (TextView) findViewById(R.id.your_shifts);

                if (events.size() != 0) {
                    //ensures recyclerview is visible
                    recyclerView.setVisibility(View.VISIBLE);
                    emptyView.setVisibility(View.GONE);
                   // yourShifts.setVisibility(View.VISIBLE);

                    //Gets shifts of user based on date
                    ArrayList myShifts = model.getShifts(model.getLoggedInUser(), String.valueOf(dateClicked));
                    ArrayList<Shift> shiftArrayList = new ArrayList<>();
                    shiftArrayList.addAll(myShifts);

                    //Sets adapter
                    shiftAdapter = new CurrentShifterAdapter(shiftArrayList);
                    recyclerView.setAdapter(shiftAdapter);
                    shiftAdapter.notifyDataSetChanged();
                    recyclerView.smoothScrollToPosition(0);
                }
                else{
                    //Displays empty message via view
                        //yourShifts.setVisibility(View.GONE);
                        recyclerView.setVisibility(View.GONE);
                        emptyView.setVisibility(View.VISIBLE);
                    }
            }


            @Override
            public void onMonthScroll(Date firstDayOfNewMonth) {
                /**
                 * Sets the title bar to new month when calendar is scrolled
                 */
                actionBar.setTitle(dateformat.format(firstDayOfNewMonth));
            }
        });


    }

}
