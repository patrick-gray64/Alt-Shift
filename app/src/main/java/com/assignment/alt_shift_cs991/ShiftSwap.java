package com.assignment.alt_shift_cs991;

public class ShiftSwap {
	/**
	 * A ShiftSwap is a pair of shifts proposed to swap the dates
	 * @author pcolr
	 */
	private Shift shift1;
	private Shift shift2;
	private int status;

	/**
	 * Constructor for a ShiftSwap
	 * @param s1 unwanted shift
	 * @param s2 wanted shift
	 */
	public ShiftSwap(Shift s1, Shift s2){
		shift1 = s1;
		shift2 = s2;
		status = 0;
	}

	/**
	 * Getter for the unwanted shift
	 * @return unwanted shift
	 */
	public Shift getUnwantedShift() {
		return shift1;
	}

	/**
	 * Getter for the wanted shift
	 * @return wanted shift
	 */
	public Shift getWantedShift() {
		return shift2;
	}

	/**
	 * Setter for the unwanted shift
	 * @param s unwanted shift
	 */
	public void setUnwantedShift(Shift s) {
		shift1 = s;
	}

	/**
	 * Setter for the wanted shift
	 * @param s wanted shift
	 */
	public void setWantedShift(Shift s) {
		shift2 = s;
	}

	/**
	 * Getter for the swap status
	 * @return swap status
	 */
	public int getStatus() {
		return status;
	}

	/**
	 * Setter for the swap status if status value is between -1 and 1
	 * @param status swap status
	 */
	public void setStatus(int status) {
		if(status >= -1 && status <=1) {
			this.status = status;
		}
	}

	/**
	 * Sets the status to pending
	 */
	public void setStatusPending() {
		this.status = 0;
	}

	/**
	 * Sets the status to accepted
	 */
	public void setStatusAccepted() {
		this.status = 1;
	}

	/**
	 * Sets the status to rejected
	 */
	public void setStatusRejected() {
		this.status = -1;
	}
}
