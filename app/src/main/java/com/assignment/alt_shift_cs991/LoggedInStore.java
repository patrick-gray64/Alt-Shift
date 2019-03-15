package com.assignment.alt_shift_cs991;


import android.content.Context;
import android.content.SharedPreferences;

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

        Shifter storedShifter = new Shifter(username, password, name, surname);

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

