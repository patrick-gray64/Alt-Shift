package com.assignment.alt_shift_cs991.model;

import androidx.room.RoomDatabase;

/**
 * The database class which extends the Room database class.
 */
@androidx.room.Database(entities = {Shifter.class}, version = 18, exportSchema = false)
public abstract class Database extends RoomDatabase {
    public abstract DaoAccess daoAccess();
}
