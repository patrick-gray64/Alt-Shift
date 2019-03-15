package com.assignment.alt_shift_cs991;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.github.sundeepk.compactcalendarview.CompactCalendarView;
import com.github.sundeepk.compactcalendarview.domain.Event;

import java.text.DateFormat;
import java.text.DateFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import androidx.appcompat.app.ActionBar;

import static android.content.Intent.EXTRA_TEXT;

public class CalendarActivity extends Toolbar_activity {


    public CompactCalendarView calendarView;
    private SimpleDateFormat dateformat = new SimpleDateFormat("MMMM - yyyy", Locale.getDefault());
    private CalendarManager calendarManager = new CalendarManager();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calendar_view);
        initToolbar();
        final ActionBar actionBar = getSupportActionBar();
        //actionBar.setDisplayHomeAsUpEnabled(false);
        calendarView = (CompactCalendarView) findViewById(R.id.compactcalendar_view);
        calendarView.setUseThreeLetterAbbreviation(true);
        actionBar.setTitle(dateformat.format(new Date()));
        Shifter testShifter = model.getShiftersList().get(1);
        //add events
        calendarManager.shiftPopulate(calendarView, testShifter);



        // listener
        calendarView.setListener(new CompactCalendarView.CompactCalendarViewListener() {
            @Override
            public void onDayClick(Date dateClicked) {
                Context context = getApplicationContext();
                List<Event> events = calendarView.getEvents(dateClicked);
                if (events.size() != 0) {
                   // Toast.makeText(context, "Event today:" + events.get(events.size() - 1), Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(), SwapActivity.class);
                    intent.putExtra(EXTRA_TEXT, dateClicked);
                    startActivity(intent);
                    //
                }
            /*    if (dateClicked.toString().compareTo("Mar 14 09:00:00 GMT 2019") == 0){
                    Toast.makeText(context, "This is the event", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(context, "Not the day", Toast.LENGTH_SHORT).show();
                }*/
            }

            @Override
            public void onMonthScroll(Date firstDayOfNewMonth) {
                actionBar.setTitle(dateformat.format(firstDayOfNewMonth));
            }
        });
       // CalendarView calendarView = findViewById(R.id.calendarView);
/*        calendarView.setOnDateChangeListener((view, year, month, dayOfMonth) -> {

            Intent intent = new Intent(getApplicationContext(), SwapActivity.class);
            intent.putExtra(EXTRA_TEXT, getResources().getString(R.string.tool_bar_title_date, new DateFormatSymbols().getMonths()[month], String.valueOf(dayOfMonth), String.valueOf(year)));
            startActivity(intent);

        });*/


    }


}
