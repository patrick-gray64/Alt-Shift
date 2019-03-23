package com.assignment.alt_shift_cs991.model;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

/**
 * Required data access object interface which enables us top query the SQLite database.
 */
@Dao
public interface DaoAccess {

    /**
     * Adds a shifter
     *
     * @param shifter
     */
    @Insert
    void insertShifter(Shifter shifter);

    @Insert
    void insertMultipleShifters(List<Shifter> shifters);

    @Query("SELECT * FROM Shifter WHERE userID = :userID and password = :password")
    Shifter getShifter(String userID, String password);

    @Query("SELECT * FROM Shifter")
    List<Shifter> getAllShifters();

    @Update
    void updateShifter(Shifter shifter);

    @Delete
    void deleteShifter(Shifter shifter);

}
