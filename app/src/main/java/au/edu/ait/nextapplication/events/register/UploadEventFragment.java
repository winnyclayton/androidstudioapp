package au.edu.ait.nextapplication.events.register;

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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;

import au.edu.ait.nextapplication.R;
import au.edu.ait.nextapplication.databinding.UploadEventFragmentBinding;
import au.edu.ait.nextapplication.events.Event;
import au.edu.ait.nextapplication.events.ViewEventsViewModel;

public class UploadEventFragment extends Fragment {

    private UploadEventFragmentBinding binding;
    private ViewEventsViewModel mViewModel;
    private String selectedEventType;
    private final String SPINNER_HINT = "Select the Event Type";

    public UploadEventFragment() {
        // Required empty public constructor - always needs this - just a java rule
    }

    String[] eventType = { "Ticketed Event", "Free Event", SPINNER_HINT};//The last element is the text that will be displayed as hint.

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = UploadEventFragmentBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(ViewEventsViewModel.class);

        Bundle bundle = getArguments();//we will use the passed bundle to know which fragment opened this one (UploadEventFragment)

        NavController navController = Navigation.findNavController(view);

        //Spinner Adapter (too many lines of code for a dropdown, anyway Android!)
        ArrayAdapter arrayAdapter = new ArrayAdapter(getActivity(), androidx.navigation.ui.R.layout.support_simple_spinner_dropdown_item, eventType){
            @NonNull
            @Override
            public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                View v = super.getView(position, convertView, parent);
                if(position == getCount()){
                    ((TextView)v.findViewById(android.R.id.text1)).setText("");
                    ((TextView)v.findViewById(android.R.id.text1)).setHint((String)getItem(getCount()));
                }
                return v;
            }

            @Override
            public int getCount() {
                return super.getCount() - 1;// you don't display last item. It is used as hint.
            }
        };

        binding.uploadEventTypeDropdown.setAdapter(arrayAdapter);
        binding.uploadEventTypeDropdown.setSelection(arrayAdapter.getCount()); //set the hint the default selection so it appears on launch.
        binding.uploadEventTypeDropdown.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
//                Toast.makeText(getContext(), eventType[i] , Toast.LENGTH_SHORT).show();
                selectedEventType = eventType[i];
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        binding.uploadPostButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String name = binding.uploadEventNameEditText.getText().toString();
                if(name.isEmpty()){
                    Snackbar.make(view, "Event Name is required", Snackbar.LENGTH_SHORT);
                   // binding.uploadEventNameEditText.requestFocus();
                    return;
                }
                //TODO: please validate that Event start & end date time, address, suburb, state & postcode have been input by the user
                String startDate = binding.uploadStartDateEditText.getText().toString();
                String startTime = binding.uploadStartTimeEditText.getText().toString();
                String endDate = binding.uploadEndDateEditText.getText().toString();
                String endTime = binding.uploadEndTimeEditText.getText().toString();
                String street = binding.uploadAddressEditText.getText().toString();
                String suburb = binding.uploadSuburbEditText.getText().toString();
                String state = binding.uploadStateEditText.getText().toString();
                String postcode = binding.uploadPostcodeEditText.getText().toString();

                //validate the user select a Event Type
                if(selectedEventType.equals(SPINNER_HINT)){
                    Snackbar.make(view, "Please select Event Type", Snackbar.LENGTH_SHORT);
                    binding.uploadEventTypeDropdown.requestFocus(); //put the focus in the spinner
                    return;
                }
                //String name, String startDate, String startTime, String endDate, String endTime, String address, String suburb, String state, String postcode, String type, String poster
                Event event = new Event(name, startDate, startTime, endDate, endTime, street, suburb, state, postcode, selectedEventType, "poster_6");
                mViewModel.insert(event);
                //Check who opened this fragment, to go back to it
                if(bundle != null && bundle.containsKey("OPENED_FROM_MAIN_FRAGMENT")){
                    navController.navigate(R.id.action_uploadEventFragment_to_homeFragment);
                }
                if(bundle != null && bundle.containsKey("OPENED_FROM_VIEW_EVENTS_FRAGMENT")){
                    navController.navigate(R.id.action_uploadEventFragment_to_viewEventsFragment);
                }


            }
        });

        binding.uploadCancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Check who opened this fragment, to go back to it
                if(bundle != null && bundle.containsKey("OPENED_FROM_MAIN_FRAGMENT")){
                    navController.navigate(R.id.action_uploadEventFragment_to_homeFragment);
                }
                if(bundle != null && bundle.containsKey("OPENED_FROM_VIEW_EVENTS_FRAGMENT")){
                    navController.navigate(R.id.action_uploadEventFragment_to_viewEventsFragment);
                }
            }
        });

    }

}

