package lal.adhish.aacdemo.repository.room;


import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

@Database(entities = {Word.class}, version = 2 )
public abstract class WordRoomDatabase extends RoomDatabase{

    public abstract WordDao wordDao();


    private static WordRoomDatabase INSTANCE;

    public static WordRoomDatabase getDatabase(final Context context)
    {

        if(INSTANCE == null)
        {

            synchronized (WordRoomDatabase.class)
            {
                if(INSTANCE == null) {

                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(), WordRoomDatabase.class, "word_database")
                            .fallbackToDestructiveMigration()
                            .build();
                }
            }}


        return INSTANCE;

    }



}
