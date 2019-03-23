package com.assignment.alt_shift_cs991.model;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class ShiftManagerTest {
    /**
     * Unit tests for ShiftManager
     */
    private ShiftManager shiftManager;
    private Shifter shifter1;
    private Shifter shifter2;
    private Shift shift1;
    private Shift shift2;
    private ShiftSwap shiftSwap;

    /**
     * Sets up the shiftManager with shifts, shifers and shiftSwaps
     * @throws Exception
     */
    @Before
    public void setUp() throws Exception {
        shiftManager = new ShiftManager();
        shifter1 = new Shifter("1","1","f","s");
        shifter2 = new Shifter("2","2","f","s");
        shift1 = new Shift("date1", shifter1);
        shift2 = new Shift("date2", shifter2);
        shiftSwap = new ShiftSwap(shift1, shift2);
        shiftManager.addShifter(shifter1);
        shiftManager.addShifter(shifter2);
        shiftManager.addShift(shift1);
        shiftManager.addShift(shift2);
        shiftManager.addShiftSwap(shiftSwap);
    }

    /**
     * Tests ShiftManager Getters for shiftManager lists
     */
    @Test
    public void testGetters() {
        assertEquals(shiftManager.getShifters().size(), 2);
        assertEquals(shiftManager.getShifts().size(), 2);
        assertEquals(shiftManager.getShiftSwaps().size(), 1);

        //getSwapableShifts
        //getRequestedSwaps
        //getAvailableSwaps
        //getCountAvailableSwaps
        //getShift
    }

    /**
     * Tests ShiftManager Setters for shiftManager lists
     */
    @Test
    public void testSetters() {
        ArrayList<Shifter> newShifters = new ArrayList<Shifter>();
        ArrayList<Shift> newShifts = new ArrayList<Shift>();
        ArrayList<ShiftSwap> newSwaps = new ArrayList<ShiftSwap>();
        ShiftSwap swap = new ShiftSwap(shift2, shift1);

        newShifters.add(shifter2);
        newShifts.add(shift2);
        newSwaps.add(swap);

        shiftManager.setShifters(newShifters);
        assertEquals(shiftManager.getShifters().get(0), shifter2);
        shiftManager.setShifts(newShifts);
        assertEquals(shiftManager.getShifts().get(0), shift2);
        shiftManager.setShiftSwaps(newSwaps);
        assertEquals(shiftManager.getShiftSwaps().get(0), swap);

    }

    /**
     * Tests getShifterLogin when searching with correct details
     */
    @Test
    public void testGetShifterLoginSuccess() {
        assertEquals(shiftManager.getShifterLogin("1", "1"), "f");
    }

    /**
     * Tests getShifterLogin when searching with incorrect details
     */
    @Test
    public void testGetShifterLoginNull() {
        assertNull(shiftManager.getShifterLogin("x", "y"));
    }

    /**
     * Tests getShifter when searching with correct details
     */
    @Test
    public void testGetShifterSuccess() {
        assertEquals(shiftManager.getShifter("1", "1"), shifter1);
    }

    /**
     * Tests getShifter when searching with incorrect details
     */
    @Test
    public void testGetShifterNull() {
        assertNull(shiftManager.getShifter("x", "y"));
    }

    /**
     * Tests getNumberShifters
     */
    @Test
    public void testGetNumberShifter() {
        assertEquals(shiftManager.getNumberShifters(), 2);
    }

    /**
     * Tests getMyShifts
     */
    @Test
    public void testGetMyShifts() {
        assertEquals(shiftManager.getMyShifts(shifter1).get(0), shift1);
    }

    /**
     * Tests getMyShiftsByDate
     */
    @Test
    public void testGetMyShiftsByDate() {
        assertEquals(shiftManager.getMyShiftsByDate(shifter1, "date").get(0), shift1);
    }

    /**
     * Tests getMyShiftsDates
     */
    @Test
    public void testGetMyShiftsDates() {
        assertEquals(shiftManager.getMyShiftsDates(shifter1).get(0), "date");
    }

    /**
     * Tests getAllShiftsDates
     */
    @Test
    public void testGetAllShiftsDates() {
        assertEquals(shiftManager.getAllShiftsDates().get(0), "date");
    }

    /**
     * Tests getAllShiftsByDates
     */
    @Test
    public void testGetAllShiftsByDates() {
        assertEquals(shiftManager.getAllShiftsByDate("date").get(0), shift1);
    }

//    /**
////     * Tests getSwapableShifts
////     */
////    @Test
////    public void testGetSwapableShifts() {
////
////    }
}