package com.assignment.alt_shift_cs991;

import android.content.Intent;
import android.os.Bundle;
import android.widget.CalendarView;
import android.widget.TextView;

import com.google.android.material.appbar.AppBarLayout;

import java.text.DateFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import androidx.appcompat.app.AppCompatActivity;

import static android.content.Intent.EXTRA_TEXT;

public class CalendarActivity extends AppCompatActivity {

    boolean isExpanded = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calendar_view);

        setSupportActionBar(findViewById(R.id.toolbar));
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayShowTitleEnabled(false);
        }

        TextView dateButton = findViewById(R.id.date);

        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat month_date = new SimpleDateFormat("MMMM dd yyyy", Locale.getDefault());
        String month_name = month_date.format(calendar.getTime());
        dateButton.setText(month_name);

        dateButton.setOnClickListener(v -> {
            isExpanded = !isExpanded;
            ((AppBarLayout) findViewById(R.id.appbarlayout)).setExpanded(isExpanded, true);
        });

        CalendarView calendarView = findViewById(R.id.calendarView);
        calendarView.setOnDateChangeListener((view, year, month, dayOfMonth) -> {
            dateButton.setText(getResources().getString(R.string.tool_bar_title_date, new DateFormatSymbols().getMonths()[month], String.valueOf(dayOfMonth), String.valueOf(year)));
            isExpanded = !isExpanded;
            ((AppBarLayout) findViewById(R.id.appbarlayout)).setExpanded(isExpanded, true);


            Intent intent = new Intent(getApplicationContext(), SwapActivity.class);
            intent.putExtra(EXTRA_TEXT, getResources().getString(R.string.tool_bar_title_date, new DateFormatSymbols().getMonths()[month], String.valueOf(dayOfMonth), String.valueOf(year)));
            startActivity(intent);

        });

    }
}
