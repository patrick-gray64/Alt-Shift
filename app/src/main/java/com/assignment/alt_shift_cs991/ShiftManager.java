package com.assignment.alt_shift_cs991;

import java.util.ArrayList;
import java.util.Date;

public class ShiftManager{
	/**
	 * ShiftManager holds all the Shifts and ShiftSwaps in ArrayLists for search and retrieval
	 * on the front end
	 */
	private ArrayList<Shift> allShifts;
	private ArrayList<ShiftSwap> shiftSwaps;

	/**
	 * Constructor for ShiftManager
	 */
	public ShiftManager() {
		allShifts = new ArrayList<Shift>();
		shiftSwaps = new ArrayList<ShiftSwap>();
	}

	/**
	 * Adds a shift to allShifts list
	 * @param shift shift
	 */
	public void addShift(Shift shift) {
		allShifts.add(shift);
	}

	/**
	 * Adds a shift to allShifts list
	 * @param date date
	 * @param shifter shifter
	 */
	public void addShift(Date date, Shifter shifter) {
		allShifts.add(new Shift(date, shifter));
	}

	/**
	 * Getter for allShifts list
	 * @return allShifts
	 */
	public ArrayList<Shift> getAllShifts(){
		return allShifts;
	}

//	/**
//	 * Finds all shifts and dates of a Shifter
//	 * @param s A shifter
//	 */
//	public ArrayList<Shift> getMyShifts(Shifter s){
//		ArrayList<Shift> myShifts = new ArrayList<Shift>();
//		for (Shift shift: allShifts) {
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
//		for (Shift shift: allShifts) {
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
	public void addShiftSwap(Date d1, Shifter s1, Date d2, Shifter s2) {
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
	 * Returns a list of allShifts that can be swapped with the given shift
	 * @param s A shift.
	 * @return The list of allShifts that are swapable with the given shift
	 */
	public ArrayList<Shift> getSwapableShifts(Shift s){
		ArrayList<Shift> swapableShifts = allShifts;
		Date date = s.getDate();
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
	 * swapShifts(ShiftSwap) swaps the shifters in the two allShifts in the given shift swap.
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
}
