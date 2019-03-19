package com.assignment.alt_shift_cs991.model;

import androidx.room.RoomDatabase;

@androidx.room.Database(entities = {Shifter.class}, version = 14, exportSchema = false)
public abstract class Database extends RoomDatabase {
    public abstract DaoAccess daoAccess();
}
