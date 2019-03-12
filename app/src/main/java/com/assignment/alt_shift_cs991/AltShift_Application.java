package com.assignment.alt_shift_cs991;

import android.app.Application;

public class AltShift_Application extends Application {

    private ShifterManager shifterManager;

    @Override
    public void onCreate(){

        super.onCreate();
        shifterManager = new ShifterManager();
        fillTheModel();
    }
    public void getShiftersList(){
        shifterManager.getShifterList();
    }
    public Shifter accessGetShifter(String userID, String password){
        return shifterManager.getShifter(userID, password);
    }

    public void fillTheModel() {


        Shifter one = new Shifter("1234", "qwerty", "Steve", "One");
        Shifter two = new Shifter("5678", "qwerty1", "Anne", "Two");
        Shifter three = new Shifter("9012", "qwerty2", "Mike", "Three");
        Shifter four = new Shifter("3456", "qwerty3", "Laura", "Four");
        Shifter five = new Shifter("7890", "qwerty4", "Sam", "Five");
        Shifter six = new Shifter("1212", "qwerty5", "Oscar", "Six");
        Shifter seven = new Shifter("3234", "qwerty6", "Ryan", "Seven");
        Shifter eight = new Shifter("4345", "qwerty7", "Tina", "Eight");
        Shifter nine = new Shifter("5456", "qwerty8", "Tom", "Nine");
        Shifter ten = new Shifter("6565", "qwerty9", "George", "Ten");

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
    }
}
