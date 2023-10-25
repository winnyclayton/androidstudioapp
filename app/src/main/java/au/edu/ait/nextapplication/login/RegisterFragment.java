package au.edu.ait.nextapplication.login;

import static android.content.Context.LAYOUT_INFLATER_SERVICE;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

import com.google.android.material.snackbar.Snackbar;

import au.edu.ait.nextapplication.R;
import au.edu.ait.nextapplication.databinding.RegisterFragmentBinding;
import au.edu.ait.nextapplication.login.User;


public class RegisterFragment extends Fragment {


    private RegisterFragmentBinding binding;

    public RegisterFragment() {
        // Required empty public constructor - always needs this - just a java rule
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.login_fragment, container, false);
        binding = RegisterFragmentBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);



        //WHEN CLICKING CANCEL BUTTON - WILL TAKE YOU BACK TO PREVIOUS PAGE
        binding.registerCancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //this is calling the same method as the android phones 'back' action
                getActivity().onBackPressed();
            }

        });

        //WHEN CLICKING ON REGISTER - TAKE YOU BACK TO LOGIN PAGE
        binding.completeRegisterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                NavController navController = Navigation.findNavController(view);

                //CODE HERE TO STORE USER DETAILS INTO DATABASE

                String registerFirstName = binding.registerFirstNameEditText.getText().toString();
                String registerLastName = binding.registerLastNameEditText.getText().toString();
                String registerUsername = binding.registerUsernameEditText.getText().toString();
                String registerPassword = binding.registerPasswordEditText.getText().toString();
                String registerEmail = binding.registerEmailEditText.getText().toString();

                // creating a user object we will pass by using a bundle
                User user = new User();

                //setting data into an object
                user.setRegisterFirstName(registerFirstName);
                user.setRegisterLastName(registerLastName);
                user.setRegisterUsername(registerUsername);
                user.setRegisterPassword(registerPassword);
                user.setRegisterEmail(registerEmail);

                //user a bundle object to pass our user object to the next fragment
                Bundle bundle = new Bundle();
                //LoggedInUser is the KEY
                bundle.putSerializable("Registered User" , user);

                Snackbar.make(view, "Thank you for registering with us!", Snackbar.LENGTH_SHORT);

                //CLICK REGISTER AND WILL TAKE YOU BACK TO LOGIN WITH NEW ACCOUNT - WITH DETAILS STORED
                navController.navigate(R.id.action_registerFragment_to_loginFragment);
            }
        });





    }
}