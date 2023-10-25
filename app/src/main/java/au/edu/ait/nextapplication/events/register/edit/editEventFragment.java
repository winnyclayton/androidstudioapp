package au.edu.ait.nextapplication.events.register.edit;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import au.edu.ait.nextapplication.R;
import au.edu.ait.nextapplication.databinding.EditEventFragmentBinding;
import au.edu.ait.nextapplication.databinding.MoreEventinfoFragmentBinding;
import au.edu.ait.nextapplication.events.Event;
import au.edu.ait.nextapplication.events.ViewEventsViewModel;


public class editEventFragment extends Fragment {

    private EditEventFragmentBinding binding;
    private ViewEventsViewModel mViewModel;
    private Event event;


    public editEventFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.edit_event_fragment, container, false);
        binding = EditEventFragmentBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(ViewEventsViewModel.class);

        //to get event information - new bundle with new key
        Bundle bundle = getArguments();



        if (bundle != null && bundle.containsKey("EDIT_EVENT")) {
            //then I am sure I have an event passed in the bundle
            event = (Event) getArguments().getSerializable("EDIT_EVENT");

            binding.editEventName.setText(event.getName());
            binding.editStartDate.setText(event.getStartDate());
            binding.editStartTime.setText(event.getStartTime());
            binding.editEndDate.setText(event.getEndDate());
            binding.editEndTime.setText(event.getEndTime());
            binding.editAddressStreet.setText(event.getAddress());
            binding.editAddressSuburb.setText(event.getSuburb());
            binding.editAddressState.setText(event.getState());
            binding.editAddressPostcode.setText(event.getPostcode());
        }


        //saving the data from the edit screen
        binding.editEventSaveChanges.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                    Bundle modifiedBundle = new Bundle();
                    String editedEventName = binding.editEventName.getText().toString();
                    event.setName(editedEventName);
                     String editedStartDate = binding.editStartDate.getText().toString();
                     event.setStartDate(editedStartDate);
                     String editedStartTime = binding.editStartTime.getText().toString();
                    event.setStartTime(editedStartTime);
                    String editedEndDate = binding.editEndDate.getText().toString();
                    event.setEndDate(editedEndDate);
                    String editedEndTime = binding.editEndTime.getText().toString();
                    event.setEndTime(editedEndTime);
                    String editedAddressStreet = binding.editAddressStreet.getText().toString();
                    event.setAddress(editedAddressStreet);
                    String editedAddressSuburb = binding.editAddressSuburb.getText().toString();
                    event.setSuburb(editedAddressSuburb);
                    String editedAddressState = binding.editAddressState.getText().toString();
                    event.setState(editedAddressState);
                    String editedAddressPostcode = binding.editAddressPostcode.getText().toString();
                    event.setPostcode(editedAddressPostcode);

                    modifiedBundle.putSerializable("EDIT_EVENT", event);
                    NavController navController = Navigation.findNavController(view);
                    navController.navigate(R.id.action_editEventFragment_to_moreEventInfoFragment, modifiedBundle);
                    //END TEST
            }

        });




        //WHEN CLICKING CANCEL BUTTON - WILL TAKE YOU BACK TO PREVIOUS PAGE
        binding.editEventCancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().onBackPressed();
            }
        });

    }
}