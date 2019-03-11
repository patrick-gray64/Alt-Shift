package com.assignment.alt_shift_cs991;

import java.util.ArrayList;

public class ShifterManager {
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
		shifters = new ArrayList<Shifter>();
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
			if (s.getUserID() == u && s.getPassword() == p) {
				return s;
			}
		}
		return null;
	}

	/**
	 * Adds a new Shifter to shifters list if userID and password are not taken
	 * @param u userID
	 * @param p password
	 * @param f first name
	 * @param s surname
	 * @return success
	 */
	public int addShifter(String u, String p, String f, String s) {
		if (getShifter(u, p) == null){
			shifters.add(new Shifter(u, p, f, s));
			return 0;
		}
		return -1;
	}

	/**
	 * Adds a shifter to the shifters list
	 * @param s shifter
	 */
	public void addShifter(Shifter s) {
		shifters.add(s);
	}
	
}
