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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;

import au.edu.ait.nextapplication.R;
import au.edu.ait.nextapplication.databinding.MoreEventinfoFragmentBinding;
import au.edu.ait.nextapplication.databinding.MoreEventinfoFragmentBinding;
import au.edu.ait.nextapplication.events.Event;
import au.edu.ait.nextapplication.events.ViewEventsViewModel;
import au.edu.ait.nextapplication.login.User;

public class moreEventInfoFragment extends Fragment {

    private MoreEventinfoFragmentBinding binding;
    private ViewEventsViewModel mViewModel;
    private Event event;

    public moreEventInfoFragment() {
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
        binding = MoreEventinfoFragmentBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(ViewEventsViewModel.class);


        //to get event information - new bundle with new key
        Bundle bundle = getArguments();
        if(bundle != null && bundle.containsKey("MORE_INFO_EVENT")){
            //then I am sure I have an event passed in the bundle
            event = (Event) getArguments().getSerializable("MORE_INFO_EVENT");

            binding.moreInfoNameTextView.setText(event.getName());
            binding.moreInfoDateTextView.setText(event.getStartDate());
            binding.moreInfoTimeTextView.setText(event.getStartTime() + " - " + event.getEndTime());
            binding.moreInfoEventTypeTextView.setText(event.getType());
            binding.moreInfoAddressStreet.setText(event.getAddress());
            binding.moreInfoAddressSuburb.setText(event.getSuburb());
            binding.moreInfoAddressState.setText(event.getState());
            binding.moreInfoAddressPostcode.setText(event.getPostcode());

            int resID = binding.getRoot().getResources().getIdentifier(event.getPoster(), "drawable", binding.getRoot().getContext().getPackageName());
            this.binding.moreInfoEventImageView.setImageResource(resID);
        }

        //ONCE AN EVENT HAS BEEN EDITED
        if (bundle != null && bundle.containsKey("EDIT_EVENT")){
            event = (Event) getArguments().getSerializable("EDIT_EVENT");
            mViewModel.update(event);
            binding.moreInfoNameTextView.setText(event.getName());
            binding.moreInfoDateTextView.setText(event.getStartDate());
            binding.moreInfoTimeTextView.setText(event.getStartTime() + " - " + event.getEndTime());
            binding.moreInfoEventTypeTextView.setText(event.getType());
            binding.moreInfoAddressStreet.setText(event.getAddress());
            binding.moreInfoAddressSuburb.setText(event.getSuburb());
            binding.moreInfoAddressState.setText(event.getState());
            binding.moreInfoAddressPostcode.setText(event.getPostcode());
            int resID = binding.getRoot().getResources().getIdentifier(event.getPoster(), "drawable", binding.getRoot().getContext().getPackageName());
            this.binding.moreInfoEventImageView.setImageResource(resID);

        }


        binding.backEventButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //getActivity().onBackPressed();
                NavController navController = Navigation.findNavController(view);
                navController.navigate(R.id.action_moreEventInfoFragment_to_viewEventsFragment);
            }
        });


        binding.deleteEventButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                mViewModel.delete(event);
                //TAKE YOU BACK TO EVENT LIST
                NavController navController = Navigation.findNavController(view);
                navController.navigate(R.id.action_moreEventInfoFragment_to_viewEventsFragment);

            }
        });

        binding.editEventButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Bundle bundle = new Bundle();
                bundle.putSerializable("EDIT_EVENT", event);

                //navigation
                NavController navController = Navigation.findNavController(view);
                navController.navigate(R.id.action_moreEventInfoFragment_to_editEventFragment, bundle);

            }
        });




    }
}