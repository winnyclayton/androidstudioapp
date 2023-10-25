package au.edu.ait.nextapplication.events;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

import au.edu.ait.nextapplication.NextRepository;
import au.edu.ait.nextapplication.events.Event;

public class ViewEventsViewModel extends AndroidViewModel {
    private NextRepository nextRepository;
    private LiveData<List<Event>> allEvents;

    public ViewEventsViewModel(@NonNull Application application) {
        super(application);

        nextRepository = new NextRepository(application);

        allEvents = nextRepository.getAllEvents();
    }

    public void insert(Event event){ nextRepository.insert(event);}

    public void delete(Event event){ nextRepository.delete(event);}

    public void update(Event event){ nextRepository.update(event);}

    public LiveData<List<Event>> getAllEvents(){
        return allEvents;
    }

    public Event findById(int id){
        return nextRepository.findById(id);
    }
}