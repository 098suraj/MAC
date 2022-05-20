package com.example.mac.View;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.support.v4.media.session.PlaybackStateCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.Toast;

import com.example.mac.Model.Users;
import com.example.mac.R;
import com.example.mac.ViewMModel.AuthViewModel;
import com.example.mac.databinding.FragmentFirstBinding;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Objects;
import java.util.Timer;
import java.util.TimerTask;


public class FirstFragment extends Fragment {
    FragmentFirstBinding binding;
    NavController navController;
    AuthViewModel authViewModel;
    Timer timer;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //---------------------------ViewModel--------------------------------//
        authViewModel= new ViewModelProvider(this,ViewModelProvider.AndroidViewModelFactory
                .getInstance(getActivity().getApplication())).get(AuthViewModel.class);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentFirstBinding.inflate(inflater, container, false);
        return binding.getRoot();


    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //-----------------------------Binding-----------------------------??
        binding.animationview.setAnimation(R.raw.go);
        binding.animationview.playAnimation();
        navController = Navigation.findNavController(view);

        //--------------------------Shake-Animation-----------------------------//
        Animation shake = AnimationUtils.loadAnimation(getContext(), R.anim.shake);
      //------------------------------click-Listeners------------------------------//
        binding.buttonFirst.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (authViewModel.getCurrentUser()!=null){
                    navController.navigate(R.id.action_firstFragment_to_userData);
                }else
                navController.navigate(R.id.action_firstFragment_to_secondFragment);
            }
        });
        ((MainActivity) requireActivity()).updateStatusBarColor("#00000000");

        binding.animationview.addAnimatorListener(new AnimatorListenerAdapter() {
            public void onAnimationEnd(Animator animation, boolean isReverse) {
                binding.buttonFirst.startAnimation(shake);

            }
        });








    }



}