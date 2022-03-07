package com.example.mac.View;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mac.R;
import com.example.mac.Repository.DbRepo;
import com.example.mac.ViewMModel.AuthViewModel;
import com.example.mac.ViewMModel.DbViewModel;
import com.example.mac.databinding.FragmentAddUserBinding;

public class AddUser extends Fragment {
  FragmentAddUserBinding binding;
    DbViewModel dbViewModel;
    AuthViewModel authViewModel;
    public AddUser() {
        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       dbViewModel=new ViewModelProvider(this,ViewModelProvider.AndroidViewModelFactory
       .getInstance(getActivity().getApplication())).get(DbViewModel.class);
        authViewModel= new ViewModelProvider(this,ViewModelProvider.AndroidViewModelFactory
                .getInstance(getActivity().getApplication())).get(AuthViewModel.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding=FragmentAddUserBinding.inflate(inflater,container,false);
        return binding.getRoot();


    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

          binding.addbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = binding.usernameEt.getText().toString().trim();
                String firstname = binding.firstnameet.getText().toString().trim();
                String lastname = binding.lastnameet.getText().toString().trim();
                String rollno = binding.rollNo.getText().toString().trim();
                if (!email.isEmpty() && !firstname.isEmpty() && !lastname.isEmpty() && !rollno.isEmpty()) {
                    dbViewModel.addUsers(firstname, lastname, rollno, email);

                }
            }
        });
          binding.signout.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View view) {
                  authViewModel.signOut();
                  Navigation.findNavController(view).navigate(R.id.action_addUser_to_firstFragment);
              }
          });
        binding.viewData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i("View Data", "onClick: ");
                Navigation.findNavController(view).navigate(R.id.action_addUser_to_userData);
            }
        });
    }
}