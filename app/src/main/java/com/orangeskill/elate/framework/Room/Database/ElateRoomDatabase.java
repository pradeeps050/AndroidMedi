package com.orangeskill.elate.framework.Room.Database;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;
import android.content.Context;
import android.support.annotation.VisibleForTesting;

import com.orangeskill.elate.framework.model.WinBotEntity;


@Database(entities =
        {WinBotEntity.class}
        , version = 1, exportSchema = false)
@TypeConverters({Converters.class})
public abstract class ElateRoomDatabase extends RoomDatabase {
    @VisibleForTesting
    private static final String DATABASE_NAME = "WindStream-Database";
    private static ElateRoomDatabase sInstance;
    private final MutableLiveData<Boolean> mIsDatabaseCreated = new MutableLiveData<>();

    private static ElateRoomDatabase getDatabase(final Context context) {
        synchronized (ElateRoomDatabase.class) {
            if (sInstance == null) {
                sInstance = Room.databaseBuilder(context.getApplicationContext(),
                        ElateRoomDatabase.class, DATABASE_NAME)
                        .allowMainThreadQueries()
                        //  for version increased, fallback to destructive migration enabled — database is cleared
                        .fallbackToDestructiveMigration()
                        .build();

            }
        }

        return sInstance;
    }

    public static ElateRoomDatabase getInstance(final Context context) {
        synchronized (ElateRoomDatabase.class) {
            if (sInstance == null) {
                sInstance = getDatabase(context.getApplicationContext());
              //  sInstance.updateDatabaseCreated(context.getApplicationContext());
            }
        }

        return sInstance;
    }




    /**
     * Check whether the database already exists and expose it via {@link #getDatabaseCreated()}
     */
    private void updateDatabaseCreated(final Context context) {
        if (context.getDatabasePath(DATABASE_NAME).exists()) {
            setDatabaseCreated();
        }
    }

    private void setDatabaseCreated() {
        mIsDatabaseCreated.postValue(true);
    }

    public LiveData<Boolean> getDatabaseCreated() {
        return mIsDatabaseCreated;
    }

}
