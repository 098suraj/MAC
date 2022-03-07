package com.example.mac.View;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import com.example.mac.Adapter.RecyclerAdapter;
import com.example.mac.Model.Users;
import com.example.mac.R;
import com.example.mac.ViewMModel.AuthViewModel;
import com.example.mac.databinding.FragmentSecondBinding;
import com.google.firebase.auth.FirebaseUser;


public class SecondFragment extends Fragment {
    FragmentSecondBinding binding;
    AuthViewModel viewModel;
    RecyclerAdapter recyclerAdapter;
    Users users;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new ViewModelProvider(this, ViewModelProvider
                .AndroidViewModelFactory
                .getInstance(getActivity().getApplication())).get(AuthViewModel.class);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentSecondBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //----------------------------buttonClick-------------------------------------//
        binding.registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String age = binding.rollNo.getText().toString();
                String userName = binding.usernameEt.getText().toString();
                if (!userName.isEmpty() && !age.isEmpty()) {
                    viewModel.signUp(userName, age);

                    viewModel.getMutableLiveData().observe(getViewLifecycleOwner(), new Observer<FirebaseUser>() {
                        @Override
                        public void onChanged(FirebaseUser firebaseUser) {
                            if (firebaseUser != null) {
                                Toast.makeText(getContext(), "Now Login", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(getContext(), "Please Enter Email and Pass", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });

                }

            }
        });
        binding.buttonSecond.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                String age = binding.rollNo.getText().toString();
                String userName = binding.usernameEt.getText().toString();
                if (!userName.isEmpty() && !age.isEmpty()) {
                    viewModel.singIn(userName, age);


                    viewModel.getMutableLiveData().observe(getViewLifecycleOwner(), new Observer<FirebaseUser>() {
                        @Override
                        public void onChanged(FirebaseUser firebaseUser) {
                            if (firebaseUser != null) {
                                Log.i("Firebaselogin", "onChanged: ");
                                Navigation.findNavController(view).navigate(R.id.action_secondFragment_to_userData);
                            }
                        }
                    });
                } else {
                    Toast.makeText(getContext(), "Please Enter Email and Pass", Toast.LENGTH_SHORT).show();
                }
            }
        });


        //-----------------------------------------------------------temp code----------------------------------//.

        //--------------------------------------------------------------000-----------------------------------//
    }
}

