package au.edu.ait.nextapplication.home;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import au.edu.ait.nextapplication.R;
import au.edu.ait.nextapplication.databinding.FragmentMainBinding;
import au.edu.ait.nextapplication.login.User;

public class HomeFragment extends Fragment {

    private HomeViewModel mViewModel;
    private FragmentMainBinding binding;

//    public static HomeFragment newInstance() {
//        return new HomeFragment();
//    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
         //return inflater.inflate(R.layout.fragment_main, container, false);
        binding = FragmentMainBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(HomeViewModel.class);

        //this code is to pass the user's name to the homepage eg. Welcome, Winny!
        User loggedInUser;
        //get the bundle passed through the action
        Bundle bundle = getArguments();
        //if the bundle is not zero and the bundle contains the key LoggedInUser
        if (bundle != null && bundle.containsKey("LoggedInUser")){
            loggedInUser = (User) getArguments().getSerializable("LoggedInUser");
            //then set the text to Welcome, User!
            binding.welcomeTextView.setText("Welcome " + loggedInUser.getUsername() + "!");
        }

        //WHEN CLICKING ON UPLOAD EVENT - TAKE YOU TO UPLOAD EVENT PAGE
        binding.uploadEventButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putString("OPENED_FROM_MAIN_FRAGMENT", "OPENED_FROM_MAIN_FRAGMENT");

                NavController navController = Navigation.findNavController(view);
                //when clicking upload events... it will go to upload events fragment
                navController.navigate(R.id.action_homeFragment_to_uploadEventFragment, bundle);
            }
        });

        //WHEN CLICKING ON VIEW EVENTS - TAKE YOU TO VIEW EVENTS PAGE
        binding.viewEventsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavController navController = Navigation.findNavController(view);
                navController.navigate(R.id.action_homeFragment_to_viewEventsFragment);
                //when clicking upload events... it will go to upload events fragment
//                navController.navigate(R.id.action_mainFragment_to_viewEventsFragment);
            }
        });


        //WHEN CLICKING ON SIGN OUT - TAKES YOU BACK TO SIGN IN
        binding.signOutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavController navController = Navigation.findNavController(view);
                navController.navigate(R.id.action_homeFragment_to_loginFragment);
            }
        });
    }


    }
