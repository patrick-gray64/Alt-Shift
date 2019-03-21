package com.assignment.alt_shift_cs991.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity
public class Shifter implements Parcelable {
    /**
     * A Shifter is a person that works shifts.
     *
     * @author pcolr
     */
    @NonNull
    @PrimaryKey(autoGenerate = true)
    private int keyID;

    private int userID;
    private String password;
    private String firstName;
    private String surname;

    @Ignore
    private String newShiftDate;

    @Ignore
    private List<Shift> myShifts;

    @Ignore
    private List<String> myShiftDates;

    /**
     * Constructor for a Shifter Employee
     *
     * @param userID    userID
     * @param password  password
     * @param firstName first name
     * @param surname   surname
     */
    public Shifter(int userID, String password, String firstName, String surname) {
        this.userID = userID;
        this.password = password;
        this.firstName = firstName;
        this.surname = surname;
        newShiftDate = "";
        myShifts = new ArrayList<Shift>();
        myShiftDates = new ArrayList<String>();
    }

    /**
     * Getter for userID
     *
     * @return
     */
    @NonNull
    public int getKeyID() {
        return keyID;
    }

    public void setKeyID(@NonNull int keyID) {
        this.keyID = keyID;
    }

    /**
     * Getter for userID
     *
     * @return
     */
    public int getUserID() {
        return userID;
    }

    public void setNewShiftDate(String newShiftDate) {
        this.newShiftDate = newShiftDate;
    }

    /**
     * Getter for userID
     *
     * @return
     */
    public String getNewShiftDate() {
        return newShiftDate;
    }

    /**
     * Getter for password
     *
     * @return password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Getter for first name
     *
     * @return firstName
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Getter for surname
     *
     * @return surname
     */
    public String getSurname() {
        return surname;
    }

    /**
     * Setter for user ID
     *
     * @param userID userID
     */
    public void setUserID(int userID) {
        this.userID = userID;
    }

    /**
     * Setter for password
     *
     * @param password password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Setter for first name
     *
     * @param firstName firstName
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Setter for surname
     *
     * @param surname surname
     */
    public void setSurname(String surname) {
        this.surname = surname;
    }

    /**
     * Getter for myShifts
     *
     * @return List of Shifts that this Shifter is assigned
     */
    @Ignore
    public List<Shift> getMyShifts() {
        return myShifts;
    }

    /**
     * Setter for myShifts
     *
     * @param myShifts List of Shifts that this Shifter is assigned
     */
    @Ignore
    public void setMyShifts(List<Shift> myShifts) {
        this.myShifts = myShifts;
    }

    /**
     * Returns a list of shift dates for a Shifter
     *
     * @return
     */
    @Ignore
    public List<String> getMyShiftDates() {
        for (Shift shift : myShifts) {
            myShiftDates.add(shift.getDate());
        }
        return myShiftDates;
    }

    @Ignore
    protected Shifter(Parcel in) {
        firstName = in.readString();
        surname = in.readString();
        userID = in.readInt();
        password = in.readString();
        newShiftDate = in.readString();

    }

    @Ignore
    @Override
    public int describeContents() {
        return 0;
    }

    @Ignore
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(firstName);
        dest.writeString(surname);
        dest.writeString(password);
        dest.writeInt(userID);
        dest.writeString(newShiftDate);
    }

    @Ignore
    public static final Creator<Shifter> CREATOR = new Creator<Shifter>() {
        @Override
        public Shifter createFromParcel(Parcel in) {
            return new Shifter(in);
        }

        @Override
        public Shifter[] newArray(int size) {
            return new Shifter[size];
        }
    };

}
