package com.assignment.alt_shift_cs991;

import java.util.ArrayList;
import java.util.Date;

public class Shifter{
	/**
	 * A Shifter is a person that works shifts.
	 * @author pcolr
	 */
	
	private String userID;
	private String password;
	private String firstName;
	private String surname;
	private ArrayList<Shift> myShifts;
	private ArrayList<Date> myShiftDates;

	/**
	 * Constructor for a Shifter Employee
	 * @param userID userID
	 * @param password password
	 * @param firstName first name
	 * @param surname surname
	 */
	public Shifter(String userID, String password, String firstName, String surname) {
		this.userID = userID;
		this.password = password;
		this.firstName = firstName;
		this.surname = surname;
		myShifts = new ArrayList<Shift>();
	}

	/**
	 * Getter for userID
	 * @return
	 */
	public String getUserID() {
		return userID;
	}

	/**
	 * Getter for password
	 * @return password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * Getter for first name
	 * @return firstName
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * Getter for surname
	 * @return surname
	 */
	public String getSurname() {
		return surname;
	}

	/**
	 * Setter for user ID
	 * @param userID userID
	 */
	public void setUserID(String userID) {
		this.userID = userID;
	}

	/**
	 * Setter for password
	 * @param password password
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * Setter for first name
	 * @param firstName firstName
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * Setter for surname
	 * @param surname surname
	 */
	public void setSurname(String surname) {
		this.surname = surname;
	}

	/**
	 * Getter for myShifts
	 * @return List of Shifts that this Shifter is assigned
	 */
	public ArrayList<Shift> getMyShifts() {
		return myShifts;
	}

	/**
	 * Setter for myShifts
	 * @param myShifts List of Shifts that this Shifter is assigned
	 */
	public void setMyShifts(ArrayList<Shift> myShifts) {
		this.myShifts = myShifts;
	}

	public ArrayList<Date> getMyShiftDates() {
		for (Shift shift: myShifts) {
			myShiftDates.add(shift.getDate());
		}
		return myShiftDates;
	}

}
