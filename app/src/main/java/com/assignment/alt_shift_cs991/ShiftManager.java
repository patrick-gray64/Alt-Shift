//package com.assignment.alt_shift_cs991;
//
//import java.util.ArrayList;
//
//public class ShiftManager{
//	/**
//	 * ShiftManager holds all the Shifts and ShiftSwaps in ArrayLists for search and retrieval
//	 * on the front end
//	 */
//	private ArrayList<Shift> allShifts;
//	private ArrayList<ShiftSwap> shiftSwaps;
//
//	/**
//	 * Constructor for ShiftManager
//	 */
//	public ShiftManager() {
//		allShifts = new ArrayList<Shift>();
//		shiftSwaps = new ArrayList<ShiftSwap>();
//	}
//
//	/**
//	 * Adds a shift to allShifts list
//	 * @param shift shift
//	 */
//	public void addShift(Shift shift) {
//		allShifts.add(shift);
//	}
//
//	/**
//	 * Adds a shift to allShifts list
//	 * @param date date
//	 * @param shifter shifter
//	 */
//	public void addShift(String date, Shifter shifter) {
//		allShifts.add(new Shift(date, shifter));
//	}
//
//	/**
//	 * Getter for allShifts list
//	 * @return allShifts
//	 */
//	public ArrayList<Shift> getAllShifts(){
//		return allShifts;
//	}
//
////	/**
////	 * Finds all shifts and dates of a Shifter
////	 * @param s A shifter
////	 */
////	public ArrayList<Shift> getMyShifts(Shifter s){
////		ArrayList<Shift> myShifts = new ArrayList<Shift>();
////		for (Shift shift: allShifts) {
////			if(shift.getShifter() == s) {
////				myShifts.add(shift);
////			}
////		}
////		return myShifts;
////	}
////
////	/**
////	 * Finds all shifts and dates of a Shifter
////	 * @param s A shifter
////	 */
////	public ArrayList<Date> getMyShiftDates(Shifter s){
////		ArrayList<Date> myShiftDates = new ArrayList<Date>();
////		for (Shift shift: allShifts) {
////			if(shift.getShifter() == s) {
////				myShiftDates.add(shift.getDate());
////			}
////		}
////		return myShiftDates;
////	}
//
//	/**
//	 * Adds a shiftSwap to shiftSwaps list
//	 * @param s
//	 */
//	public void addShiftSwap(ShiftSwap s) {
//		shiftSwaps.add(s);
//	}
//
//	/**
//	 * Adds a shiftSwap to shiftSwaps list
//	 * @param s1 first shift to swap
//	 * @param s2 second shift to swap
//	 */
//	public void addShiftSwap(Shift s1, Shift s2) {
//		shiftSwaps.add(new ShiftSwap(s1, s2));
//	}
//
//	/**
//	 * Adds a shiftSwap to shiftSwaps list
//	 * @param d1 first date to swap
//	 * @param s1 first shifter to swap
//	 * @param d2 second date to swap
//	 * @param s2 second shifter to swap
//	 */
//	public void addShiftSwap(String d1, Shifter s1, String d2, Shifter s2) {
//		shiftSwaps.add(new ShiftSwap(new Shift(d1, s1), new Shift(d2, s2)));
//	}
//
//	/**
//	 * Getter for shiftSwaps list
//	 * @return shiftSwaps
//	 */
//	public ArrayList<ShiftSwap> getShiftSwaps(){
//		return shiftSwaps;
//	}
//
//	/**
//	 * Returns a list of allShifts that can be swapped with the given shift
//	 * @param s A shift.
//	 * @return The list of allShifts that are swapable with the given shift
//	 */
//	public ArrayList<Shift> getSwapableShifts(Shift s){
//		ArrayList<Shift> swapableShifts = allShifts;
//		String date = s.getDate();
//		for (Shift shift: swapableShifts) {
//			if(s.getShifter().getMyShiftDates().contains(shift.getDate()))
//			swapableShifts.remove(shift);
//			else if(shift.getShifter().getMyShiftDates().contains(date))
//				swapableShifts.remove(shift);
//		}
//		return swapableShifts;
//	}
//
//	/**
//	 * getRequestedSwaps(Shifter) gets a list of shift swaps requested by the given shifter.
//	 * @param s A Shifter.
//	 * @return The list of shift swaps requested by the shifter.
//	 */
//	public ArrayList<ShiftSwap> getRequestedSwaps(Shifter s){
//		// code to be added here to return the list of shift swaps requested by the the given shifter (shifter1 = s).
//		ArrayList<ShiftSwap> requestedShifts = new ArrayList<ShiftSwap>();
//		for (ShiftSwap swap: shiftSwaps) {
//			if(swap.getUnwantedShift().getShifter() == s) {
//				requestedShifts.add(swap);
//			}
//		}
//		return requestedShifts;
//	}
//
//	/**
//	 * getRequestedSwaps(Shifter) gets a list of shift swaps requested by the given shifter.
//	 * @param s A Shifter.
//	 * @return The list of shift swaps requested by the shifter.
//	 */
//	public ArrayList<ShiftSwap> getAvailableSwaps(Shifter s){
//		// code to be added here to return the list of shift swaps requested by the the given shifter (shifter1 = s).
//		ArrayList<ShiftSwap> availableShifts = new ArrayList<ShiftSwap>();
//		for (ShiftSwap swap: shiftSwaps) {
//			if(swap.getWantedShift().getShifter() == s) {
//				availableShifts.add(swap);
//			}
//		}
//		return availableShifts;
//	}
//
//	/**
//	 * swapShifts(ShiftSwap) swaps the shifters in the two allShifts in the given shift swap.
//	 * @param s A shift swap.
//	 */
//	public void swapShifts(ShiftSwap s) {
//		// code to be checked
//		Shift unwantedShift = s.getUnwantedShift();
//		Shift wantedShift = s.getWantedShift();
//		Shifter requestingShifter = unwantedShift.getShifter();
//		Shifter respondingShifter = wantedShift.getShifter();
//		unwantedShift.setShifter(respondingShifter);
//		wantedShift.setShifter(requestingShifter);
//	}
//}

package com.assignment.alt_shift_cs991;
//

import com.assignment.alt_shift_cs991.Shifter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

public class ShiftManager implements Serializable {

	private ArrayList<Shifter> shifters;
	private ArrayList<Shift> shifts;
	private ArrayList<ShiftSwap> shiftSwaps;

	public ShiftManager() {
		shifters = new ArrayList<Shifter>();
		shifts = new ArrayList<Shift>();
		shiftSwaps = new ArrayList<ShiftSwap>();
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

	public int getNumberShifters(){
		return shifters.size();
	}


	public int addShifter(String u, String p, String f, String s) {
		if (getShifter(u, p) == null){
			shifters.add(new Shifter(u, p, f, s));
			return 0;
		}
		return -1;
	}

	public void addShifter(Shifter s) {
		shifters.add(s);
	}

	public void addShift(Shift s) {
		shifts.add(s);
	}

	public void addShift(String d, Shifter s) {
		shifts.add(new Shift(d, s));
	}

	public ArrayList<Shift> getShifts(){
		return shifts;
	}

	/**
	 * getShifts(Shifter) returns a list of the given shifter's shifts.
	 * @param shifter A shifter.
	 * @return The shifter's list of shifts.
	 */
	public ArrayList<Shift> getShifts(Shifter shifter){
		// code to get a list of the given shifter's shifts
		ArrayList<Shift> myShifts = new ArrayList<Shift>();
		for (Shift shift : shifts){
			//System.out.println("" + );
			//if (shifter.getFirstName().equals(shift.getShifter().getFirstName())){
			if (shifter.equals(shift.getShifter())){
				myShifts.add(shift);
			}
		}
		return myShifts;
	}

	public void addShiftSwap(ShiftSwap s) {
		shiftSwaps.add(s);
	}

	public void addShiftSwap(Shift s1, Shift s2) {
		shiftSwaps.add(new ShiftSwap(s1, s2));
	}

	public void addShiftSwap(String d1, Shifter s1, String d2, Shifter s2) {
		shiftSwaps.add(new ShiftSwap(new Shift(d1, s1), new Shift(d2, s2)));
	}

	public ArrayList<ShiftSwap> getShiftSwaps(){
		return shiftSwaps;
	}

	/**
	 * getSwapableShifts() returns a list of shifts that can be swapped with the given shift,
	 * according to certain rules.
	 * @param unwantedShift A shift.
	 * @return The list of shifts that are swapable with the given shift.
	 */
	public ArrayList<Shift> getSwapableShifts(Shift unwantedShift){
		// currently, the swapable shifts are all shifts of all other shifters (not the shifter of the given shift),
		// excluding those shifts having the same date as any of the shifter's shifts.
		ArrayList<Shift> myShifts = getShifts(unwantedShift.getShifter());
		ArrayList<Shift> swapableShifts = new ArrayList<Shift>();
		for (Shift shift : shifts) {
			if (!unwantedShift.getShifter().equals(shift.getShifter())) {
				boolean clash = false;
				for (Shift shift2 : myShifts) {
					if (shift2.getDate().equals(shift.getDate())){
						clash = true;
					}
				}
				if (!clash) swapableShifts.add(shift);
			}
		}
		return swapableShifts;
	}

	/**
	 * getRequestedSwaps(Shifter) gets a list of shift swaps requested by the given shifter.
	 * @param shifter A Shifter.
	 * @return The list of shift swaps requested by the shifter.
	 */
	public ArrayList<ShiftSwap> getRequestedSwaps(Shifter shifter){
		// return the list of shift swaps requested by the the given shifter (shifter1 = s).
		ArrayList<ShiftSwap> requestedSwaps = new ArrayList<ShiftSwap>();
		for (ShiftSwap shiftSwap : shiftSwaps){
			if (shifter == shiftSwap.getUnwantedShift().getShifter()) requestedSwaps.add(shiftSwap);
		}
		return requestedSwaps;
	}

	/**
	 * getAvailableSwaps(Shifter) gets a list of shift swaps shift swaps available to the the given shifter.
	 * @param shifter A Shifter.
	 * @return The list of shift swaps available to the shifter.
	 */
	public ArrayList<ShiftSwap> getAvailableSwaps(Shifter shifter){
		// return the list of shift swaps available to the given shifter (shifter2 = s).
		ArrayList<ShiftSwap> requestedSwaps = new ArrayList<ShiftSwap>();
		for (ShiftSwap shiftSwap : shiftSwaps){
			if (shifter == shiftSwap.getWantedShift().getShifter()) requestedSwaps.add(shiftSwap);
		}
		return requestedSwaps;
	}

	/**
	 * swapShifts(ShiftSwap) swaps the shifters in the two shifts in the given shift swap.
	 * @param s A shift swap.
	 */
	public void swapShifts(ShiftSwap s) {
		Shift unwantedShift = s.getUnwantedShift();
		Shift wantedShift = s.getWantedShift();
		Shifter requestingShifter = unwantedShift.getShifter();
		Shifter respondingShifter = wantedShift.getShifter();
		unwantedShift.setShifter(respondingShifter);
		wantedShift.setShifter(requestingShifter);
	}

	public ArrayList<Shifter> getShifters() {
		return shifters;
	}

	public void setShifters(ArrayList<Shifter> shifters) {
		this.shifters = shifters;
	}

	public Shift getShift(Shifter shifter, String date) {
		for (Shift shift: shifter.getMyShifts()) {
			if(shift.getDate().equals(date)) {
				return shift;
			}
		}
		return null;
	}
}

