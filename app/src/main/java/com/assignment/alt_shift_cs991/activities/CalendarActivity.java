package com.assignment.alt_shift_cs991.activities;

import android.os.Bundle;

import com.assignment.alt_shift_cs991.R;
import com.assignment.alt_shift_cs991.adapters.CurrentShifterAdapter;
import com.assignment.alt_shift_cs991.model.Application;
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


    public CompactCalendarView calendarView;
    private SimpleDateFormat dateformat = new SimpleDateFormat("MMMM - yyyy", Locale.getDefault());
    private CalendarManager calendarManager = new CalendarManager();
    public static final String EXTRA_SHIFTER = "com.assignment.alt_shift_cs991.SHIFTER";
    public RecyclerView recyclerView;
    private CurrentShifterAdapter shiftAdapter;
    protected Application model;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calendar_view);
        initToolbar();

        model = (Application)getApplication();
        final ActionBar actionBar = getSupportActionBar();
        //actionBar.setDisplayHomeAsUpEnabled(false);
        calendarView = findViewById(R.id.compactcalendar_view);
        calendarView.setUseThreeLetterAbbreviation(true);
        actionBar.setTitle(dateformat.format(new Date()));
        Shifter shifter = model.getLoggedInShifter(); // This is calling Shifter two for any login
        //add events
        calendarManager.shiftPopulate(calendarView, model.shiftManager.getmyShiftsDates(shifter));

       // TextView shift_descp = findViewById(R.id.shiftInfoDesc);
       // shift_descp.setVisibility(View.GONE);
        // listener
        calendarView.setListener(new CompactCalendarView.CompactCalendarViewListener() {
            @Override
            public void onDayClick(Date dateClicked) {

                shiftAdapter = new CurrentShifterAdapter(model.shiftManager.getMyShiftsByDate(model.getLoggedInShifter(), dateClicked.toString()));
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

            @Override
            public void onMonthScroll(Date firstDayOfNewMonth) {
                actionBar.setTitle(dateformat.format(firstDayOfNewMonth));
              //  shift_descp.setVisibility(View.GONE);

            }
        });


    }


}
