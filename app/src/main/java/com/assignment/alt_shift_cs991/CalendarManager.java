package com.assignment.alt_shift_cs991;

import com.github.sundeepk.compactcalendarview.CompactCalendarView;
import com.github.sundeepk.compactcalendarview.domain.Event;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


public class CalendarManager {


    public CalendarManager(){
    }

    public void addStringToShift (CompactCalendarView calendarView, String shiftDate){
        long eShiftDate = epochGen(shiftDate);
        Event shiftEvent = new Event(R.color.paddysgreen, eShiftDate, shiftDate);
        calendarView.addEvent(shiftEvent);
    }

    public void shiftPopulate(CompactCalendarView calendarView, List<String> shiftDates){
       addMultiStringtoShift(calendarView, shiftDates);
    }

    public void addMultiStringtoShift (CompactCalendarView calendarView, List<String> shiftList){
        for (int i = 0; i < (shiftList.size()); i++){
           addStringToShift(calendarView, shiftList.get(i));
        }
    }

    public long epochGen (String timeDate){
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
