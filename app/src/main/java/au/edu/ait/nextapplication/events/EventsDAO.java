package au.edu.ait.nextapplication.events;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface EventsDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Event event);
    @Update
    void update(Event event);
    @Delete
    void delete(Event event);

    @Query("SELECT * FROM EVENT WHERE ID = :id")
    Event findById(int id);

    @Query("SELECT * FROM EVENT")
    LiveData<List<Event>> findAll();
}
