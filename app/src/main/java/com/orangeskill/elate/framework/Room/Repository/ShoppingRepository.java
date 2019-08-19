package com.orangeskill.elate.framework.Room.Repository;

import com.orangeskill.elate.framework.Room.Database.ElateRoomDatabase;



public class ShoppingRepository {
    private static ShoppingRepository sInstance;
    private final ElateRoomDatabase mDatabase;


    private ShoppingRepository(final ElateRoomDatabase database) {
        mDatabase = database;


    }

    public static ShoppingRepository getInstance(final ElateRoomDatabase database) {
        synchronized (ShoppingRepository.class) {
            if (sInstance == null) {
                sInstance = new ShoppingRepository(database);
            }
        }

        return sInstance;
    }






}
