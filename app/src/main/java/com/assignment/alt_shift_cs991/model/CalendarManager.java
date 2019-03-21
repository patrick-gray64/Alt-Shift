package com.assignment.alt_shift_cs991.model;

import com.assignment.alt_shift_cs991.R;
import com.github.sundeepk.compactcalendarview.CompactCalendarView;
import com.github.sundeepk.compactcalendarview.domain.Event;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


public class CalendarManager {
    /**
     * Manages the Calendar
     */

    /**
     * Constructor for CalendarManager
     */
    public CalendarManager() {
    }

    /**
     * Adds a Shift date to the calendar
     *
     * @param calendarView
     * @param shiftDate    date of shift
     */
    public void addStringToShift(CompactCalendarView calendarView, String shiftDate) {
        long eShiftDate = epochGen(shiftDate);
        Event shiftEvent = new Event(R.color.paddysgreen, eShiftDate, shiftDate);
        calendarView.addEvent(shiftEvent);
    }

    /**
     * Populates the calendar with multiple shifts
     *
     * @param calendarView
     * @param shiftDates   list of shift dates
     */
    public void shiftPopulate(CompactCalendarView calendarView, List<String> shiftDates) {
        addMultiStringtoShift(calendarView, shiftDates);
    }

    /**
     * Adds multiple shifts to the calendar
     *
     * @param calendarView
     * @param shiftList
     */
    public void addMultiStringtoShift(CompactCalendarView calendarView, List<String> shiftList) {
        for (int i = 0; i < (shiftList.size()); i++) {
            addStringToShift(calendarView, shiftList.get(i));
        }
    }

    /**
     * Converts a string to date format
     *
     * @param timeDate
     * @return
     */
    public long epochGen(String timeDate) {
        String str = timeDate;
        SimpleDateFormat df = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy");
        Date date = null;
        try {
            date = df.parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        long epoch = date.getTime();
        return epoch;
    }

}
