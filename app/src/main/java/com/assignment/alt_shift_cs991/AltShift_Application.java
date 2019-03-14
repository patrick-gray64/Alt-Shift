package com.assignment.alt_shift_cs991;

import android.app.Application;

import java.util.ArrayList;
import java.util.List;

public class AltShift_Application extends Application {

    private ShiftManager shiftManager;

    @Override
    public void onCreate(){

        super.onCreate();
        shiftManager = new ShiftManager();
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
    public List<Shift> getShiftList(){
        return shiftManager.getShifts();
    }

    public List<Shift> thisShifterList(String userID, String password){
        List<Shift> shiftList1 = getShiftList();
        List myShiftList = new ArrayList();
        for (int i = 0; i < shiftList1.size()-1; i++){
            if (userID == shiftList1.get(i).getShifter().getUserID()){
                myShiftList.add(shiftList1.get(i));
            }
        }
        return myShiftList;
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
        Shift shift3 = new Shift("Mar 23 09:00:00 GMT 20199", two);
        Shift shift4 = new Shift("Mar 24 09:00:00 GMT 2019", four);
        Shift shift5 = new Shift("Mar 25 09:00:00 GMT 2019", five);
        Shift shift6 = new Shift("Mar 26 09:00:00 GMT 20199", six);
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
}
