package com.assignment.alt_shift_cs991;

import android.os.Parcel;
import android.os.Parcelable;


public class Shift implements Parcelable {
    /**
     * A Shift is a date and a person (shifter) working on that date.
     * @author pcolr
     */

    private String date;
    private Shifter shifter;
    private String name;
    private String surname;
    private String swapDate;

    /**
     * Constructor for a Shift
     * @param date Date of Shift
     * @param shifter Employee on Shift
     */
    public Shift(String date, Shifter shifter) {
        this.date = date;
        this.shifter = shifter;
        name = shifter.getFirstName();
        surname = shifter.getSurname();
        shifter.getMyShifts().add(this);
        swapDate = "";
    }

    /**
     * Getter for Date
     * @return date
     */
    public String getDate(){
        return date;
    }


    public String getSwapDate() {
        return swapDate;
    }

    public void setSwapDate(String swapDate) {
        this.swapDate = swapDate;
    }

    /**
     * Getter for Shifter
     * @return shifter
     */
    public Shifter getShifter() {
        return shifter;
    }

    public String getName(){
        return name;
    }

    public String getSurname(){
        return surname;
    }

    /**
     * Setter for Date
     * @param date date
     */
    public void setDate(String date) {
        this.date = date;
    }

    /**
     * Setter for Shifter
     * @param shifter shifter
     */
    public void setShifter(Shifter shifter) {
        this.shifter = shifter;
    }

    protected Shift(Parcel in) {
        date = in.readString();
        name = in.readString();
        surname = in.readString();
        swapDate = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(date);
        dest.writeString(name);
        dest.writeString(surname);
        dest.writeString(swapDate);
    }

    public static final Creator<Shift> CREATOR = new Creator<Shift>() {
        @Override
        public Shift createFromParcel(Parcel in) {
            return new Shift(in);
        }

        @Override
        public Shift[] newArray(int size) {
            return new Shift[size];
        }
    };

}
