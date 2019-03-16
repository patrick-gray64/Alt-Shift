package com.assignment.alt_shift_cs991;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import java.util.List;

public class AltShift_Application extends Application {

    private ShiftManager shiftManager;
    private LoggedInStore loggedInStore;

    @Override
    public void onCreate(){

        super.onCreate();
        shiftManager = new ShiftManager();
        loggedInStore = new LoggedInStore(getApplicationContext());
        fillTheModel();
    }
    public ShiftManager getShiftManager() {
        return shiftManager;
    }
    public List<Shifter> getShifters(){
        return shiftManager.getShifters();
    }
    public String accessGetShifterLogin(String userID, String password){
        return shiftManager.getShifterLogin(userID, password);
    }
    public Shifter accessGetShifter(String userID, String password){
        return shiftManager.getShifter(userID, password);
    }
    public List<Shift> getShiftList(){
        return shiftManager.getShifts();
    }
    public List<Shift> getmyShifts(Shifter shifter){
        return shiftManager.getmyShifts(shifter);
    }
    public List<Shift> getSwappableShifts(Shift swappableShift){
        return shiftManager.getSwapableShifts(swappableShift);
    }
    public void setLoggedInUser(Boolean a){
        loggedInStore.setUserLoggedIn(a);
    }
    public Shifter getLoggedInUser(){
        return loggedInStore.getLoggedInShifter();
    }
    public void storedLoggedInUser(Shifter shifter){
        loggedInStore.storedLoggedInUser(shifter);
    }
    public void clearLoggedIn(){
        loggedInStore.clearUserData();
    }
    public Shift getShift(Shifter shifter, String date ){
        return shiftManager.getShift(shifter, date);
    }
    public void swapShifts(ShiftSwap s){
        shiftManager.swapShifts(s);
    }
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

        Shift shift1 = new Shift("Mar 21 09:00:00 GMT 2019", two);
        Shift shift2 = new Shift("Mar 22 09:00:00 GMT 2019", two);
        Shift shift3 = new Shift("Mar 23 09:00:00 GMT 2019", two);
        Shift shift4 = new Shift("Mar 24 09:00:00 GMT 2019", four);
        Shift shift5 = new Shift("Mar 25 09:00:00 GMT 2019", five);
        Shift shift6 = new Shift("Mar 26 09:00:00 GMT 2019", six);
        Shift shift7 = new Shift("Mar 21 09:00:00 GMT 2019", seven);
        Shift shift8 = new Shift("Mar 22 09:00:00 GMT 2019", eight);
        Shift shift9 = new Shift("Mar 23 09:00:00 GMT 2019", nine);
        Shift shift10 = new Shift("Mar 23 09:00:00 GMT 2019", ten);

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
    }

    public class LoggedInStore {

        public static final String LI_NAME = "shifterDetails";
        SharedPreferences localData;

        public LoggedInStore(Context context) {
            localData = context.getSharedPreferences(LI_NAME, 0);
        }

        public void storedLoggedInUser(Shifter shifter){

            SharedPreferences.Editor spEditor = localData.edit();
            spEditor.putString("name", shifter.getFirstName());
            spEditor.putString("surname", shifter.getSurname());
            spEditor.putString("password", shifter.getPassword());
            spEditor.putString("username", shifter.getUserID());
            spEditor.commit();

        }

        public Shifter getLoggedInShifter(){


            String name = localData.getString("name", "");
            String surname = localData.getString("surname", "");
            String password = localData.getString("password", "");
            String username = localData.getString("username", "");

            Shifter storedShifter = shiftManager.getShifter(username, password);

            return storedShifter;
        }

        public void setUserLoggedIn(Boolean loggedIn){

            SharedPreferences.Editor spEditor = localData.edit();
            spEditor.putBoolean("LoggedIn", loggedIn);
            spEditor.commit();

        }
        public void clearUserData(){
            SharedPreferences.Editor spEditor = localData.edit();
            spEditor.clear();
            spEditor.commit();
        }
    }

}
