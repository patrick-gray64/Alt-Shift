package com.assignment.alt_shift_cs991;

import java.util.ArrayList;
import java.util.Date;

public class Shifter {
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
	 * @param u userID
	 * @param p password
	 * @param f first name
	 * @param s surname
	 */
	public Shifter(String u, String p, String f, String s) {
		userID = u;
		password = p;
		firstName = f;
		surname = s;
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
	 * @param u userID
	 */
	public void setUserID(String u) {
		userID = u;
	}

	/**
	 * Setter for password
	 * @param p password
	 */
	public void setPassword(String p) {
		password = p;
	}

	/**
	 * Setter for first name
	 * @param f firstName
	 */
	public void setFirstName(String f) {
		firstName = f;
	}

	/**
	 * Setter for surname
	 * @param s surname
	 */
	public void setSurname(String s) {
		surname = s;
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
