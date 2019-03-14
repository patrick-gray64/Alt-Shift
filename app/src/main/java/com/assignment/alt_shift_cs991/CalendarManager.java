package com.assignment.alt_shift_cs991;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;

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

    public void shiftPopulate(CompactCalendarView calendarView, Shifter shifter){
       addMultiStringtoShift(calendarView, shifter.getMyShiftDates());
    }

    public void addMultiStringtoShift (CompactCalendarView calendarView, List<String> shiftList){
        for (int i = 0; i < (shiftList.size()); i++){
           addStringToShift(calendarView, shiftList.get(i));
        }
    }

    public long epochGen (String timeDate){
        String str = timeDate;
        SimpleDateFormat df = new SimpleDateFormat("MMMM dd HH:mm:ss zzz yyyy");
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
