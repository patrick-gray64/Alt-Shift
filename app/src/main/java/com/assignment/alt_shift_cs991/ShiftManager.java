package com.assignment.alt_shift_cs991;

import java.util.ArrayList;

public class ShiftManager{
	/**
	 * ShiftManager holds all the Shifts and ShiftSwaps in ArrayLists for search and retrieval
	 * on the front end
	 */
	private ArrayList<Shifter> shifters;
	private ArrayList<Shift> shifts;
	private ArrayList<ShiftSwap> shiftSwaps;

	/**
	 * Constructor for ShiftManager
	 */
	public ShiftManager() {
		shifters = new ArrayList<Shifter>();
		shifts = new ArrayList<Shift>();
		shiftSwaps = new ArrayList<ShiftSwap>();
	}

	/**
	 * Adds a shifter to the shifters list
	 * @param shifter shifter
	 */
	public void addShifter(Shifter shifter) {
		shifters.add(shifter);
	}

	/**
	 * Adds a shift to shifts list
	 * @param shift shift
	 */
	public void addShift(Shift shift) {
		shifts.add(shift);
	}

	/**
	 * Adds a shift to shifts list
	 * @param date date
	 * @param shifter shifter
	 */
	public void addShift(String date, Shifter shifter) {
		shifts.add(new Shift(date, shifter));
	}

	/**
	 * Getter for shifts list
	 * @return shifts
	 */
	public ArrayList<Shift> getShifts(){
		return shifts;
	}

	public ArrayList<Shifter> getShifters() {
		return shifters;
	}

	public void setShifters(ArrayList<Shifter> shifters) {
		this.shifters = shifters;
	}

//	/**
//	 * Finds all shifts and dates of a Shifter
//	 * @param s A shifter
//	 */
//	public ArrayList<Shift> getMyShifts(Shifter s){
//		ArrayList<Shift> myShifts = new ArrayList<Shift>();
//		for (Shift shift: shifts) {
//			if(shift.getShifter() == s) {
//				myShifts.add(shift);
//			}
//		}
//		return myShifts;
//	}
//
//	/**
//	 * Finds all shifts and dates of a Shifter
//	 * @param s A shifter
//	 */
//	public ArrayList<Date> getMyShiftDates(Shifter s){
//		ArrayList<Date> myShiftDates = new ArrayList<Date>();
//		for (Shift shift: shifts) {
//			if(shift.getShifter() == s) {
//				myShiftDates.add(shift.getDate());
//			}
//		}
//		return myShiftDates;
//	}

	/**
	 * Adds a shiftSwap to shiftSwaps list
	 * @param s
	 */
	public void addShiftSwap(ShiftSwap s) {
		shiftSwaps.add(s);
	}

	/**
	 * Adds a shiftSwap to shiftSwaps list
	 * @param s1 first shift to swap
	 * @param s2 second shift to swap
	 */
	public void addShiftSwap(Shift s1, Shift s2) {
		shiftSwaps.add(new ShiftSwap(s1, s2));
	}

	/**
	 * Adds a shiftSwap to shiftSwaps list
	 * @param d1 first date to swap
	 * @param s1 first shifter to swap
	 * @param d2 second date to swap
	 * @param s2 second shifter to swap
	 */
	public void addShiftSwap(String d1, Shifter s1, String d2, Shifter s2) {
		shiftSwaps.add(new ShiftSwap(new Shift(d1, s1), new Shift(d2, s2)));
	}

	/**
	 * Getter for shiftSwaps list
	 * @return shiftSwaps
	 */
	public ArrayList<ShiftSwap> getShiftSwaps(){
		return shiftSwaps;
	}
	
	/**
	 * Returns a list of shifts that can be swapped with the given shift
	 * @param s A shift.
	 * @return The list of shifts that are swapable with the given shift
	 */
	public ArrayList<Shift> getSwapableShifts(Shift s){
		ArrayList<Shift> swapableShifts = shifts;
		String date = s.getDate();
		for (Shift shift: swapableShifts) {
			if(s.getShifter().getMyShiftDates().contains(shift.getDate()))
			swapableShifts.remove(shift);
			else if(shift.getShifter().getMyShiftDates().contains(date))
				swapableShifts.remove(shift);
		}
		return swapableShifts;
	}
	
	/**
	 * getRequestedSwaps(Shifter) gets a list of shift swaps requested by the given shifter.
	 * @param s A Shifter.
	 * @return The list of shift swaps requested by the shifter.
	 */
	public ArrayList<ShiftSwap> getRequestedSwaps(Shifter s){
		// code to be added here to return the list of shift swaps requested by the the given shifter (shifter1 = s).
		ArrayList<ShiftSwap> requestedShifts = new ArrayList<ShiftSwap>();
		for (ShiftSwap swap: shiftSwaps) {
			if(swap.getUnwantedShift().getShifter() == s) {
				requestedShifts.add(swap);
			}
		}
		return requestedShifts;
	}

	/**
	 * getRequestedSwaps(Shifter) gets a list of shift swaps requested by the given shifter.
	 * @param s A Shifter.
	 * @return The list of shift swaps requested by the shifter.
	 */
	public ArrayList<ShiftSwap> getAvailableSwaps(Shifter s){
		// code to be added here to return the list of shift swaps requested by the the given shifter (shifter1 = s).
		ArrayList<ShiftSwap> availableShifts = new ArrayList<ShiftSwap>();
		for (ShiftSwap swap: shiftSwaps) {
			if(swap.getWantedShift().getShifter() == s) {
				availableShifts.add(swap);
			}
		}
		return availableShifts;
	}
	
	/**
	 * swapShifts(ShiftSwap) swaps the shifters in the two shifts in the given shift swap.
	 * @param s A shift swap.
	 */
	public void swapShifts(ShiftSwap s) {
		// code to be checked
		Shift unwantedShift = s.getUnwantedShift();
		Shift wantedShift = s.getWantedShift();
		Shifter requestingShifter = unwantedShift.getShifter();
		Shifter respondingShifter = wantedShift.getShifter();
		unwantedShift.setShifter(respondingShifter);
		wantedShift.setShifter(requestingShifter);
	}

	/**
	 * getShifter searches in the database of Shifters for the shifter with the given user id and password,
	 * returns a reference to the shifter if found and null if not found.
	 * @param userID User ID of shifter or null.
	 * @param password Password of shifter.
	 * @return Shifter with given user ID and password.
	 */
	public String getShifterLogin(String userID, String password) {
		for (Shifter s : shifters) {
			if (s.getUserID().equals(userID) && s.getPassword().equals(password)) {
				return s.getFirstName();
			}
		}
		return null;
	}

	/**
	 * getShifter searches in the database of Shifters for the shifter with the given user id and password,
	 * returns a reference to the shifter if found and null if not found.
	 * @param u User ID of shifter or null.
	 * @param p Password of shifter.
	 * @return Shifter with given user ID and password.
	 */
	public Shifter getShifter(String u, String p) {
		for (Shifter s : shifters) {
			if ((s.getUserID().equals(u)) && (s.getPassword().equals(p))) {
				return s;
			}
		}
		return null;
	}
}
