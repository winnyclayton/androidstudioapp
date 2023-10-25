package au.edu.ait.nextapplication;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import au.edu.ait.nextapplication.events.Event;
import au.edu.ait.nextapplication.events.EventsDAO;

@Database(entities = {Event.class}, version = 1, exportSchema = false)
public abstract class NextRoomDatabase extends RoomDatabase {

    /*  The database will expose the DAOs here. Declare one method per dao, the method will just return the dao  */
    //============= BEGIN DAO's methods section ===========
    // declare here as many DAO's as you app requires..
    public abstract EventsDAO eventsDAO();
    // public abstract ...


    //============= END DAO's methods section ===========

    private static volatile NextRoomDatabase INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;

    //This is a fixed thread pool we will use to run database operations asynchronously on a background thread
    //operations such as Insert, Delete, Update
    public static  final ExecutorService databaseWriteExecutor = Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    //This method is what is called Singleton, this avoid creating more than 1 instance of the Database
    public static NextRoomDatabase getDatabase(final Context context){
        if(INSTANCE == null){
            synchronized (NextRoomDatabase.class){
                if(INSTANCE == null){
                    INSTANCE = Room
                            .databaseBuilder(context.getApplicationContext(), NextRoomDatabase.class, "next_database")
                            .addCallback(roomCallback)
                            .fallbackToDestructiveMigration()
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    public static RoomDatabase.Callback roomCallback = new RoomDatabase.Callback(){
        //onCreate will be call only the first time the database is created.
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            populateInitialData(INSTANCE);
            Log.i("XYZ", "onCreate Called");
        }

        //onOpen will be called every time the database is opened
        @Override
        public void onOpen(@NonNull SupportSQLiteDatabase db) {
            super.onOpen(db);
            Log.i("XYZ", "onOpen Called");
        }
    };

    /**
     * method used to set up initial data for us to play with it.
     * @param instance
     */
    private static void populateInitialData(NextRoomDatabase instance) {
        //we execute database operations in threads
        NextRoomDatabase.databaseWriteExecutor.execute( () -> {
            EventsDAO eventsDAO = INSTANCE.eventsDAO();
            eventsDAO.insert(new Event( "Halloween Party" , "01/01/2022" , "22:00" , "02/01/2022" , "06:00" , "50 Kent St" , "Sydney" , "NSW" , "2000", "Ticketed Event" , "image_1" ));
            eventsDAO.insert(new Event( "Pool Party" , "01/01/2022" , "13:00" , "01/01/2022" , "22:00" , "50 Kent St" , "Sydney" , "NSW" , "2000", "Free Event" , "image_3" ));
            eventsDAO.insert(new Event("Flume Live" , "01/01/2022" , "16:00" , "01/01/2022" , "23:00" , "67 Night St" , "Newtown" , "NSW" , "2011", "Ticketed Event" , "image_5" ));

        });


    }

}
