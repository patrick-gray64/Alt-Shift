package com.assignment.alt_shift_cs991;

import java.util.Date;

public class Shift {
	/**
	 * A Shift is a date and a person (shifter) working on that date.
	 * @author pcolr
	 */

	private Date date;
	private Shifter shifter;

	/**
	 * Constructor for a Shift
	 * @param d Date of Shift
	 * @param s Employee on Shift
	 */
	public Shift(Date d, Shifter s) {
		date = d;
		shifter = s;
	}

	/**
	 * Getter for Date
	 * @return date
	 */
	public Date getDate(){
		return date;
	}

	/**
	 * Getter for Shifter
	 * @return shifter
	 */
	public Shifter getShifter() {
		return shifter;
	}

	/**
	 * Setter for Date
	 * @param d date
	 */
	public void setDate(Date d) {
		date = d;
	}

	/**
	 * Setter for Shifter
	 * @param s shifter
	 */
	public void setShifter(Shifter s) {
		shifter = s;
	}
	
}
