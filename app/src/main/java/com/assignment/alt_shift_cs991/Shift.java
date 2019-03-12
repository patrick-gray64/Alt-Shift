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
	 * @param date Date of Shift
	 * @param shifter Employee on Shift
	 */
	public Shift(Date date, Shifter shifter) {
		this.date = date;
		this.shifter = shifter;
		shifter.getMyShifts().add(this);
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
	 * @param date date
	 */
	public void setDate(Date date) {
		this.date = date;
	}

	/**
	 * Setter for Shifter
	 * @param shifter shifter
	 */
	public void setShifter(Shifter shifter) {
		this.shifter = shifter;
	}
	
}
