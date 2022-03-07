package com.example.mac.View;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.mac.Adapter.RecyclerAdapter;
import com.example.mac.Model.Users;
import com.example.mac.R;
import com.example.mac.ViewMModel.AuthViewModel;
import com.example.mac.ViewMModel.DbViewModel;
import com.example.mac.databinding.FragmentUserDataBinding;

import java.util.List;


public class UserData extends Fragment {
    DbViewModel viewModel;
    AuthViewModel authViewModel;
    FragmentUserDataBinding binding;
    RecyclerAdapter recyclerAdapter;
    ProgressBar progressBar;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory
                .getInstance(getActivity().getApplication())).get(DbViewModel.class);
        authViewModel= new ViewModelProvider(this,ViewModelProvider.AndroidViewModelFactory
                .getInstance(getActivity().getApplication())).get(AuthViewModel.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentUserDataBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerAdapter=new RecyclerAdapter();
        binding.recycelrUserData.setHasFixedSize(true);
        binding.recycelrUserData.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.recycelrUserData.setAdapter(recyclerAdapter);
        viewModel.getData();
        viewModel.getListMutableLiveData().observe(getViewLifecycleOwner(), new Observer<List<Users>>() {
            @Override
            public void onChanged(List<Users> list) {
                binding.listProgressbar.setVisibility(View.GONE);
                recyclerAdapter.setUsersList(list);
                recyclerAdapter.notifyDataSetChanged();
            }
        });
        binding.signout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                authViewModel.signOut();
                Navigation.findNavController(view).navigate(R.id.action_userData_to_secondFragment);
            }
        });
    }


}