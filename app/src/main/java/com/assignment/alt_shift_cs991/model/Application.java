package com.assignment.alt_shift_cs991.model;

import android.content.SharedPreferences;

public class Application extends android.app.Application {

    public ShiftManager shiftManager;
    public CalendarManager calendarManager;
    public static final String LI_NAME = "shifterDetails";
    SharedPreferences localData;

    /**
     * Initialises the Application
     */
    @Override
    public void onCreate(){

        super.onCreate();
        shiftManager = new ShiftManager();
        calendarManager = new CalendarManager();
        fillTheModel();
        localData = getSharedPreferences(LI_NAME, 0);
    }

    /**
     * Fills the backend with 11 Shifters and Shifts
     */
    public void fillTheModel() {
        Shifter one = new Shifter("jsb18181", "qwerty", "James", "Mackenzie");
        Shifter two = new Shifter("5678", "qwerty1", "Anne", "Two");
        Shifter three = new Shifter("9012", "qwerty2", "Mike", "Three");
        Shifter four = new Shifter("3456", "qwerty3", "Laura", "Four");
        Shifter five = new Shifter("7890", "qwerty4", "Sam", "Five");
        Shifter six = new Shifter("1212", "qwerty5", "Oscar", "Six");
        Shifter seven = new Shifter("3234", "qwerty6", "Ryan", "Seven");
        Shifter eight = new Shifter("4345", "qwerty7", "Tina", "Eight");
        Shifter nine = new Shifter("5456", "qwerty8", "Tom", "Nine");
        Shifter ten = new Shifter("6565", "qwerty9", "George", "Ten");
        Shifter eleven = new Shifter("6565", "qwerty9", "George", "Eleven");

        shiftManager.addShifter(one);
        shiftManager.addShifter(two);
        shiftManager.addShifter(three);
        shiftManager.addShifter(four);
        shiftManager.addShifter(five);
        shiftManager.addShifter(six);
        shiftManager.addShifter(seven);
        shiftManager.addShifter(eight);
        shiftManager.addShifter(nine);
        shiftManager.addShifter(ten);
        shiftManager.addShifter(eleven);

        Shift shift1 = new Shift("Thu Mar 21 00:00:00 GMT 2019", two);
        Shift shift2 = new Shift("Fri Mar 22 00:00:00 GMT 2019", two);
        Shift shift3 = new Shift("Sat Mar 23 00:00:00 GMT 2019", two);
        Shift shift4 = new Shift("Sun Mar 24 00:00:00 GMT 2019", four);
        Shift shift5 = new Shift("Mon Mar 25 00:00:00 GMT 2019", five);
        Shift shift6 = new Shift("Tue Mar 26 00:00:00 GMT 2019", six);
        Shift shift7 = new Shift("Thu Mar 21 00:00:00 GMT 2019", seven);
        Shift shift8 = new Shift("Fri Mar 22 00:00:00 GMT 2019", eight);
        Shift shift9 = new Shift("Sat Mar 23 00:00:00 GMT 2019", nine);
        Shift shift10 = new Shift("Sat Mar 23 00:00:00 GMT 2019", ten);
        Shift shift11 = new Shift("Sat Mar 23 00:00:00 GMT 2019", two);

        shiftManager.addShift(shift1);
        shiftManager.addShift(shift2);
        shiftManager.addShift(shift3);
        shiftManager.addShift(shift4);
        shiftManager.addShift(shift5);
        shiftManager.addShift(shift6);
        shiftManager.addShift(shift7);
        shiftManager.addShift(shift8);
        shiftManager.addShift(shift9);
        shiftManager.addShift(shift10);

        ShiftSwap swap1 = new ShiftSwap(shift5, shift1);
        ShiftSwap swap2 = new ShiftSwap(shift1, shift8);
        ShiftSwap swap3 = new ShiftSwap(shift8, shift1);
        ShiftSwap swap4 = new ShiftSwap(shift7, shift1);
        ShiftSwap swap5 = new ShiftSwap(shift1, shift7);
        ShiftSwap swap6 = new ShiftSwap(shift1, shift10);

        shiftManager.addShiftSwap(swap1);
        shiftManager.addShiftSwap(swap2);
        shiftManager.addShiftSwap(swap3);
        shiftManager.addShiftSwap(swap4);
        shiftManager.addShiftSwap(swap5);
        shiftManager.addShiftSwap(swap6);


    }

    /**
     * Stores data of the logged in user
     * @param shifter logged in used
     */
    public void storedLoggedInUser(Shifter shifter){

        SharedPreferences.Editor spEditor = localData.edit();
        spEditor.putString("name", shifter.getFirstName());
        spEditor.putString("surname", shifter.getSurname());
        spEditor.putString("password", shifter.getPassword());
        spEditor.putString("username", shifter.getUserID());
        spEditor.commit();
    }

    /**
     * Getter for the logged in user
     * @return logged in Shifter
     */
    public Shifter getLoggedInShifter(){
        String name = localData.getString("name", "");
        String surname = localData.getString("surname", "");
        String password = localData.getString("password", "");
        String username = localData.getString("username", "");
        Shifter storedShifter = shiftManager.getShifter(username, password);
        return storedShifter;
    }

    /**
     * Setter for the logged in user
     * @param loggedIn
     */
    public void setUserLoggedIn(Boolean loggedIn){
        SharedPreferences.Editor spEditor = localData.edit();
        spEditor.putBoolean("LoggedIn", loggedIn);
        spEditor.commit();
    }

    /**
     * Clears the data of the logged in user
     */
    public void clearUserData(){
        SharedPreferences.Editor spEditor = localData.edit();
        spEditor.clear();
        spEditor.commit();
    }


    public void removeSwap ( ShiftSwap s){
        shiftManager.removeSwap(s);
    }

}
