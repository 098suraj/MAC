package com.example.mac.View;

import android.graphics.Color;
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

import com.example.mac.Adapter.RecyclerAdapter;
import com.example.mac.Model.Zones;
import com.example.mac.R;
import com.example.mac.ViewMModel.AuthViewModel;
import com.example.mac.ViewMModel.DbViewModel;
import com.example.mac.databinding.FragmentUserDataBinding;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;

import java.util.ArrayList;
import java.util.List;


public class UserData extends Fragment {
    DbViewModel viewModel;
    AuthViewModel authViewModel;
    FragmentUserDataBinding binding;
    RecyclerAdapter recyclerAdapter;
    ProgressBar progressBar;
    List<Zones> s = new ArrayList<>();
    List<String> m = new ArrayList<>();
    BarDataSet barDataSet;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory
                .getInstance(getActivity().getApplication())).get(DbViewModel.class);
        authViewModel = new ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory
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

        viewModel.getData();
        viewModel.getListMutableLiveData().observe(getViewLifecycleOwner(), new Observer<List<Zones>>() {
            @Override
            public void onChanged(List<Zones> zones) {
                binding.listProgressbar.setVisibility(View.GONE);
                ArrayList<BarEntry> k = new ArrayList<>();
               if (zones.size()==1){
                   k.add(new BarEntry(1,zones.get(0).getSocialD()));
                   System.out.println(zones.get(0).getZone());
               }
                if (zones.size()==2){
                    k.add(new BarEntry(1,zones.get(0).getSocialD()));
                    k.add(new BarEntry(2,zones.get(1).getSocialD()));
                }
                if (zones.size()==3){
                    k.add(new BarEntry(1,zones.get(0).getSocialD()));
                    k.add(new BarEntry(2,zones.get(1).getSocialD()));
                    k.add(new BarEntry(3,zones.get(2).getSocialD()));
                }
                if (zones.size()==4){
                    k.add(new BarEntry(1,zones.get(0).getSocialD()));
                    k.add(new BarEntry(2,zones.get(1).getSocialD()));
                    k.add(new BarEntry(3,zones.get(2).getSocialD()));
                    k.add(new BarEntry(4,zones.get(3).getSocialD()));
                }
                barDataSet = new BarDataSet(k, "zones");
                barDataSet.setColor(R.color.black);
                barDataSet.setValueTextColor(Color.BLACK);
                barDataSet.setValueTextSize(16f);
                BarData  br = new BarData(barDataSet);
                br.setBarWidth(1f);
                binding.chart.setData(br);
                binding.chart.getAxisLeft().setAxisMaximum(24);
                binding.chart.getAxisRight().setAxisMaximum(24);
                binding.chart.setFitBars(true);
                binding.chart.getDescription().setText("Covizone");
                binding.chart.animateY(200);
            }

        });

        viewModel.getListMutableLive().observe(getViewLifecycleOwner(), new Observer<List<String>>() {
            @Override
            public void onChanged(List<String> strings) {
                m = strings;
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