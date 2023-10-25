package au.edu.ait.nextapplication.login;

import android.nfc.Tag;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.snackbar.Snackbar;

import au.edu.ait.nextapplication.R;
import au.edu.ait.nextapplication.databinding.LoginFragmentBinding;


public class LoginFragment extends Fragment {

    private LoginFragmentBinding binding;

    public LoginFragment() {
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
        binding = LoginFragmentBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //WHEN CLICKING ON LOGON - TAKE YOU TO HOME PAGE
        binding.logonButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


// Do something no matter if username is empty or not

                NavController navController = Navigation.findNavController(view);
                //somethings to perform
                String username = binding.usernameEditText.getText().toString();
                String password = binding.passwordEditText.getText().toString();


                // creating a user object we will pass by using a bundle
                User user = new User();
                //setting data into an object
                user.setUsername(username);
                user.setPassword(password);
                //user a bundle object to pass our user object to the next fragment
                Bundle bundle = new Bundle();
                //LoggedInUser is the KEY
                bundle.putSerializable("LoggedInUser" , user);

                //when clicking login in... it will go to main fragment (home page)
                navController.navigate(R.id.action_loginFragment_to_homeFragment, bundle);
            }
        });

        //WHEN CLICKING ON REGISTER
        binding.registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //TAKE YOU BACK TO REGISTER PAGE
                NavController navController = Navigation.findNavController(view);
                //when clicking upload events... it will go to upload events fragment
                navController.navigate(R.id.action_loginFragment_to_registerFragment);
            }
        });

    }
}