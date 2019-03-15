package com.assignment.alt_shift_cs991;

import android.app.Application;

import java.util.ArrayList;
import java.util.List;

public class AltShift_Application extends Application {

    private ShifterManager shifterManager;
    private ShiftManager shiftManager;

    @Override
    public void onCreate(){

        super.onCreate();
        shifterManager = new ShifterManager();
        shiftManager = new ShiftManager();
        fillTheModel();
    }
    public List<Shifter> getShiftersList(){
        return shifterManager.getShifterList();
    }
    public String accessGetShifterLogin(String userID, String password){
        return shifterManager.getShifterLogin(userID, password);
    }
    public List<Shift> getShiftList(){
        return shiftManager.getAllShifts();
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
        Shifter eleven = new Shifter("6565", "qwerty9", "George", "Ten");

        shifterManager.addShifter(one);
        shifterManager.addShifter(two);
        shifterManager.addShifter(three);
        shifterManager.addShifter(four);
        shifterManager.addShifter(five);
        shifterManager.addShifter(six);
        shifterManager.addShifter(seven);
        shifterManager.addShifter(eight);
        shifterManager.addShifter(nine);
        shifterManager.addShifter(ten);
        shifterManager.addShifter(eleven);

        Shift shift1 = new Shift("Mar 21 09:00:00 GMT 2019", one);
        Shift shift2 = new Shift("Mar 21 09:00:00 GMT 2019", two);
        Shift shift3 = new Shift("March 23 2019", three);
        Shift shift4 = new Shift("March 24 2019", four);
        Shift shift5 = new Shift("March 25 2019", five);
        Shift shift6 = new Shift("March 26 2019", six);
        Shift shift7 = new Shift("March 27 2019", seven);
        Shift shift8 = new Shift("March 28 2019", eight);
        Shift shift9 = new Shift("March 29 2019", nine);
        Shift shift10 = new Shift("March 30 2019", ten);

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
