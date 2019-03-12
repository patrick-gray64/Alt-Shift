package com.assignment.alt_shift_cs991;

import java.util.ArrayList;

public class ShifterManager extends AltShift_Application {
	/**
	 * Security registers new users and retrieves
	 * @author pcolr
	 *
	 */
	private ArrayList<Shifter> shifters;

	/**
	 * Constructor for ShifterManager
	 */
	public ShifterManager() {
	    super();
		shifters = new ArrayList<Shifter>();
	}
	
	/**
	 * getShifter searches in the database of Shifters for the shifter with the given user id and password, 
	 * returns a reference to the shifter if found and null if not found. 
	 * @param userID User ID of shifter or null.
	 * @param password Password of shifter.
	 * @return Shifter with given user ID and password.
	 */
	public Shifter getShifter(String userID, String password) {
		for (Shifter s : shifters) {
			if (s.getUserID().equals(userID) && s.getPassword().equals(password)) {
				return s;
			}
		}
		return null;
	}

	/**
	 * Adds a new Shifter to shifters list if userID and password are not taken
	 * @param userID userID
	 * @param password password
	 * @param firstName first name
	 * @param surname surname
	 * @return success
	 */
	public int addShifter(String userID, String password, String firstName, String surname) {
		if (getShifter(userID, password) == null){
			shifters.add(new Shifter(userID, password, firstName, surname));
			return 0;
		}
		return -1;
	}

	/**
	 * Adds a shifter to the shifters list
	 * @param shifter shifter
	 */
	public void addShifter(Shifter shifter) {
		shifters.add(shifter);
	}

	public ArrayList<Shifter> getShifterList(){

	    return shifters;
    }
	
}
