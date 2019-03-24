package com.assignment.alt_shift_cs991.activities;

import android.os.Bundle;
import android.widget.Button;

import com.assignment.alt_shift_cs991.R;
import com.assignment.alt_shift_cs991.adapters.CalendarManager;
import com.assignment.alt_shift_cs991.adapters.CurrentShifterAdapter;
import com.assignment.alt_shift_cs991.model.Shifter;
import com.github.sundeepk.compactcalendarview.CompactCalendarView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import androidx.appcompat.app.ActionBar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

/**
 * Home activity displaying a Calendar with shifts.
 */
public class CalendarActivity extends ToolbarActivity {

    public CompactCalendarView calendarView;
    private SimpleDateFormat dateFormat = new SimpleDateFormat("MMMM - yyyy", Locale.getDefault());
    private CalendarManager calendarManager = new CalendarManager();
    public RecyclerView recyclerView;
    private CurrentShifterAdapter shiftAdapter;
    protected Application model;
    private Shifter shifter;
    private Boolean isShowing = true;

    /**
     * Initialises Calendar with shifts for the logged in user.
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calendar_view);
        initToolbar();

        model = (Application) getApplication();
        final ActionBar actionBar = getSupportActionBar();
        calendarView = findViewById(R.id.compactcalendar_view);
        calendarView.setUseThreeLetterAbbreviation(true);
        actionBar.setTitle(dateFormat.format(new Date()));
        shifter = model.getLoggedInShifter();
        calendarManager.shiftPopulate(calendarView, model.shiftManager.getMyShiftsDates(shifter));
        calendarManager.swapPopulate(calendarView, model.shiftManager.getRequestedSwaps(shifter));
        shiftAdapter = new CurrentShifterAdapter(model.shiftManager.getMyShiftsByDate(shifter, model.getDateClicked()));
        recyclerView = findViewById(R.id.shifter_shifts);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        recyclerView.setAdapter(shiftAdapter);


        calendarView.setListener(new CompactCalendarView.CompactCalendarViewListener() {
            /**
             * Shows a shift in a recycler list below the calendar when date is clicked.
             * @param dateClicked
             */
            @Override
            public void onDayClick(Date dateClicked) {

                model.setDateClicked(dateClicked.toString());
                shiftAdapter.setItems(model.shiftManager.getMyShiftsByDate(shifter, dateClicked.toString()));

            }

            /**
             * Changes month on the calendar when scrolled.
             * @param firstDayOfNewMonth
             */
            @Override
            public void onMonthScroll(Date firstDayOfNewMonth) {
                actionBar.setTitle(dateFormat.format(firstDayOfNewMonth));
            }
        });

        Button hideShow = findViewById(R.id.hideShowCal);
        hideShow.setOnClickListener(v -> {
            if (isShowing) {
                calendarView.hideCalendar();
                isShowing = false;
                hideShow.setText(R.string.Show_Calender);
            } else {
                calendarView.showCalendar();
                isShowing = true;
                hideShow.setText(R.string.Hide_Calender);
            }
        });

        CallBack callback = new CallBack() {
            @Override
            public Application getModel() {

                return model;
            }
        }; shiftAdapter.setCallBack(callback);
    }


    /**
     * Populates the calendar with shifts as events on dates. Ensures the calendar is refreshed with any
     * new changes.
     */
    @Override
    protected void onResume() {
        super.onResume();
        calendarView.removeAllEvents();
        calendarManager.shiftPopulate(calendarView, model.shiftManager.getMyShiftsDates(shifter));
        calendarManager.swapPopulate(calendarView, model.shiftManager.getRequestedSwaps(shifter));

    }
    public interface CallBack {

        Application getModel();
    }
}
