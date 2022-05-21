package com.example.mac.View;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;

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
import java.util.Objects;


public class UserData extends Fragment {
    DbViewModel viewModel;
    AuthViewModel authViewModel;
    FragmentUserDataBinding binding;
    RecyclerAdapter recyclerAdapter;
    ProgressBar progressBar;
    NavController navController;
    List<Zones> s;
    List<String> m = new ArrayList<>();
    BarDataSet barDataSet;
    String q;

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
        navController = Navigation.findNavController(view);
        binding.rl.setVisibility(View.GONE);
        binding.chart.setVisibility(View.GONE);
        binding.cafeteria.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                navController.navigate(R.id.action_userData_to_cafeteria2);

            }
        });
        binding.destination.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                navController.navigate(R.id.action_userData_to_destination2);
            }
        });
        binding.eblock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                navController.navigate(R.id.action_userData_to_eblock2);
            }
        });
        binding.library.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                navController.navigate(R.id.action_userData_to_library2);
            }
        });
        binding.ground.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                navController.navigate(R.id.action_userData_to_ground2);
            }
        });
        binding.events.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                navController.navigate(R.id.action_userData_to_event);
            }
        });


            viewModel.getData();
            viewModel.getListMutableLiveData().observe(getViewLifecycleOwner(), new Observer<List<Zones>>() {
                @Override
                public void onChanged(List<Zones> zones) {
                    binding.listProgressbar.setVisibility(View.GONE);
                    binding.rl.setVisibility(View.VISIBLE);
                    binding.chart.setVisibility(View.VISIBLE);
                    ArrayList<BarEntry> k = new ArrayList<>();
                    if (zones.size() == 1) {
                        k.add(new BarEntry(0.25f, zones.get(0).getSocialD()));
                    }
                    if (zones.size() == 2) {
                        k.add(new BarEntry(0.25f, zones.get(0).getSocialD()));
                        k.add(new BarEntry(1.25f, zones.get(1).getSocialD()));
                    }
                    if (zones.size() == 3) {
                        k.add(new BarEntry(0.25f, zones.get(0).getSocialD()));
                        k.add(new BarEntry(1.25f, zones.get(1).getSocialD()));
                        k.add(new BarEntry(2.25f, zones.get(2).getSocialD()));
                    }
                    if (zones.size() == 4) {
                        k.add(new BarEntry(0.25f, zones.get(0).getSocialD()));
                        k.add(new BarEntry(1.25f, zones.get(1).getSocialD()));
                        k.add(new BarEntry(2.25f, zones.get(2).getSocialD()));
                        k.add(new BarEntry(3.25f, zones.get(3).getSocialD()));
                        if(zones.get(0).getSocialD()>zones.get(1).getSocialD() && zones.get(0).getSocialD()>zones.get(2).getSocialD() && zones.get(0).getSocialD()>zones.get(3).getSocialD()){
                            q="Cafeteria";
                        }
                        if(zones.get(1).getSocialD()>zones.get(0).getSocialD() && zones.get(1).getSocialD()>zones.get(2).getSocialD() && zones.get(1).getSocialD()>zones.get(3).getSocialD()){
                            q="Library";
                        }
                        if(zones.get(2).getSocialD()>zones.get(1).getSocialD() && zones.get(2).getSocialD()>zones.get(0).getSocialD() && zones.get(2).getSocialD()>zones.get(3).getSocialD()){
                            q="E-Block";
                        }
                        if(zones.get(3).getSocialD()>zones.get(1).getSocialD() && zones.get(3).getSocialD()>zones.get(2).getSocialD() && zones.get(3).getSocialD()>zones.get(0).getSocialD()){
                            q="Ground";
                        }
                    }

                    barDataSet = new BarDataSet(k, "zones");
                    barDataSet.setColor(R.color.black);
                    barDataSet.setValueTextColor(Color.BLACK);
                    barDataSet.setValueTextSize(16f);
                    BarData br = new BarData(barDataSet);
                    br.setBarWidth(0.5f);
                    binding.chart.setData(br);
                    binding.chart.getAxisLeft().setAxisMaximum(24);
                    binding.chart.getAxisRight().setAxisMaximum(24);
                    binding.chart.getXAxis().setAxisMinimum(0);
                    binding.chart.getXAxis().setAxisMaximum(5);
                    binding.chart.getXAxis().setGranularityEnabled(true);
                    binding.chart.getXAxis().setGranularity(1);

                    binding.chart.getDescription().setText("Covizone");
                    binding.chart.animateY(0);
                    recyclerAdapter = new RecyclerAdapter(zones);
                    binding.recv.hasFixedSize();
                    binding.recv.setLayoutManager(new LinearLayoutManager(getContext()));
                    binding.recv.setAdapter(recyclerAdapter);

                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                        CharSequence name = "Suraj";

                        int importance = NotificationManager.IMPORTANCE_DEFAULT;
                        NotificationChannel channel = new NotificationChannel("com.example.notification", name, importance);

                        // Register the channel with the system; you can't change the importance
                        // or other notification behaviors after this
                        NotificationManager notificationManager = (NotificationManager) getActivity().getSystemService(getActivity().NOTIFICATION_SERVICE);
                        assert notificationManager != null;
                        notificationManager.createNotificationChannel(channel);
                        NotificationCompat.Builder builder = new NotificationCompat.Builder(getActivity().getApplicationContext(), "com.example.notification");
                        Notification notification=builder
                                .setSmallIcon(R.drawable.noti_foreground)
                                .setContentTitle(q)
                                .setContentText("Busy please avoid this zone")
                                .setPriority(NotificationManager.IMPORTANCE_DEFAULT)
                                .setAutoCancel(true)
                                .setCategory(Notification.CATEGORY_SERVICE)
                                .build();
                        notificationManager.notify(0,notification);

                    }
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
