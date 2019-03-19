package com.assignment.alt_shift_cs991.model;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

@Dao
public interface DaoAccess {

    @Insert
    void insertShifter (Shifter shifter);
    @Insert
    void insertMultipleShifters (List<Shifter> shifters);
    @Query("SELECT * FROM shifters WHERE userID = :userID")
    Shifter fetchOneShifterByUserID (int userID);
    @Update
    void updateShifter (Shifter shifter);
    @Delete
    void deleteShifter (Shifter shifter);

}
