package au.edu.ait.nextapplication.events.view;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.util.EventLog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.snackbar.Snackbar;

import java.io.Serializable;
import java.util.List;

import au.edu.ait.nextapplication.R;
import au.edu.ait.nextapplication.databinding.ViewEventsFragmentBinding;
import au.edu.ait.nextapplication.events.Event;
import au.edu.ait.nextapplication.events.ViewEventsViewModel;

public class ViewEventsFragment extends Fragment implements OnItemClickListener{

    private ViewEventsFragmentBinding binding;
    private ViewEventsViewModel mViewModel;

    public static ViewEventsFragment newInstance() {
        return new ViewEventsFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        //return inflater.inflate(R.layout.view_events_fragment, container, false);
        binding = ViewEventsFragmentBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(ViewEventsViewModel.class);

        binding.eventsRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.eventsRecyclerView.setHasFixedSize(true);

        EventRecyclerViewAdapter adapter = new  EventRecyclerViewAdapter(this);
        binding.eventsRecyclerView.setAdapter(adapter);

        final Observer<List<Event>> allEventsObserver = new Observer<List<Event>>() {
            @Override
            public void onChanged(List<Event> events) {
                adapter.submitList(events);
            }
        };

        mViewModel.getAllEvents().observe(getViewLifecycleOwner(), allEventsObserver);

        binding.addEventFloatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Bundle bundle = new Bundle();
                bundle.putString("OPENED_FROM_VIEW_EVENTS_FRAGMENT", "OPENED_FROM_VIEW_EVENTS_FRAGMENT");

                NavController navController = Navigation.findNavController(view);
                navController.navigate(R.id.action_viewEventsFragment_to_uploadEventFragment, bundle);
            }
        });


    }

    @Override
    public void onClick(Event event, View view) {
        //here we code what to do when we click or tap on an event
        ;

        //navigate to another fragment when I can edit or delete the event
        Bundle bundle = new Bundle();
        bundle.putSerializable("MORE_INFO_EVENT", event);
        //navigation
        NavController navController = Navigation.findNavController(view);
        navController.navigate(R.id.action_viewEventsFragment_to_moreEventInfoFragment, bundle);


        //TODO: here code what is going to happen if the user tap one of the events in the recycler view.
    }
}
