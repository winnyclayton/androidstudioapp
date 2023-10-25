package au.edu.ait.nextapplication;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import au.edu.ait.nextapplication.events.Event;
import au.edu.ait.nextapplication.events.EventsDAO;

public class NextRepository {

    private NextRoomDatabase db;
    //My repository needs to provide the DAOs, so here list all DAO's requested by the application.
    private EventsDAO eventsDAO;
    /*
      The LiveData is done in order to use this list in a Recycler View.
        Therefore any changes in the database will be immediately reflected in the recyclerview */
    private LiveData<List<Event>> allEvents;
    private Event event;

    public NextRepository(Application application) {

        /*  We use the application passed as argument and get the database  */
        db = NextRoomDatabase.getDatabase(application);
        /*  then we request a dao from the database */
        eventsDAO = db.eventsDAO();
        /*  We retrieve all monsters registered in the database, but wrapped in a liveData object to listen for changes in realtime */
        allEvents = eventsDAO.findAll();
    }

    public void insert(Event event) {
        //we have to execute these operations not in the main thread since it will freeze our app. (It won't be allowed if you try)
        NextRoomDatabase.databaseWriteExecutor.execute(() -> {
            eventsDAO.insert(event);
        });
    }

    public void update(Event event) {
        NextRoomDatabase.databaseWriteExecutor.execute(() -> {
            eventsDAO.update(event);
        });
    }

    public void delete(Event event) {
        NextRoomDatabase.databaseWriteExecutor.execute(() -> {
            eventsDAO.delete(event);
        });
    }

    public LiveData<List<Event>> getAllEvents() {
        //In this case Room takes care of the LiveData execution, so we won't have to the databaseWriteExecutor asn in the previous methods.
        return allEvents;
    }

    public Event findById(int id) {

        Callable c = () -> {   // Lambda Expression
            Event event = eventsDAO.findById(id);
            return event;
        };
        Future<Event> future = NextRoomDatabase.databaseWriteExecutor.submit(c);
        try {
            event = future.get();
            /*
            if(monster != null) {
                Log.i("XYZ", "Monster is NOT null in MonsterRepository, " + monster.toString());
            }else{
                Log.i("XYZ", "Monster is null in MonsterRepository");
            }*/
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return event;

    }
}
