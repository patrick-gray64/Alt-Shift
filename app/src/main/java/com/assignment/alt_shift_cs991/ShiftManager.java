package com.assignment.alt_shift_cs991;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ShiftManager implements Serializable {

	private List<Shifter> shifters;
	private List<Shift> shifts;
	private List<ShiftSwap> shiftSwaps;

	public ShiftManager() {
		shifters = new ArrayList<>();
		shifts = new ArrayList<>();
		shiftSwaps = new ArrayList<>();
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

	public List<Shift> getShifts(){
		return shifts;
	}

	/**
	 * getShifts(Shifter) returns a list of the given shifter's shifts.
	 * @param shifter A shifter.
	 * @return The shifter's list of shifts.
	 */
	public List<Shift> getmyShifts(Shifter shifter){
		// code to get a list of the given shifter's shifts
		List<Shift> myShifts = new ArrayList<Shift>();
		for (Shift shift : shifts){
			//System.out.println("" + );
			//if (shifter.getFirstName().equals(shift.getShifter().getFirstName())){
			if (shifter.getUserID().equals(shift.getShifter().getUserID())){
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

	public List<ShiftSwap> getShiftSwaps(){
		return shiftSwaps;
	}

	/**
	 * getSwapableShifts() returns a list of shifts that can be swapped with the given shift,
	 * according to certain rules.
	 * @param unwantedShift A shift.
	 * @return The list of shifts that are swapable with the given shift.
	 */
	public List<Shift> getSwapableShifts(Shift unwantedShift){
		// currently, the swapable shifts are all shifts of all other shifters (not the shifter of the given shift),
		// excluding those shifts having the same date as any of the shifter's shifts.
		List<Shift> myShifts = getmyShifts(unwantedShift.getShifter());
		List<Shift> swapableShifts = new ArrayList<Shift>();
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
	public List<ShiftSwap> getRequestedSwaps(Shifter shifter){
		// return the list of shift swaps requested by the the given shifter (shifter1 = s).
		List<ShiftSwap> requestedSwaps = new ArrayList<ShiftSwap>();
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
	public List<ShiftSwap> getAvailableSwaps(Shifter shifter){
		// return the list of shift swaps available to the given shifter (shifter2 = s).
		List<ShiftSwap> requestedSwaps = new ArrayList<ShiftSwap>();
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

	public List<Shifter> getShifters() {
		return shifters;
	}

	public void setShifters(List<Shifter> shifters) {
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

