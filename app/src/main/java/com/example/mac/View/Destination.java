package com.example.mac.View;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.mac.Adapter.RecyclerAdapter;
import com.example.mac.Model.Zones;
import com.example.mac.R;
import com.example.mac.ViewMModel.AuthViewModel;
import com.example.mac.ViewMModel.ChartViewModel;
import com.example.mac.ViewMModel.DbViewModel;
import com.example.mac.databinding.FragmentDestinationBinding;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Destination#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Destination extends Fragment {

    DbViewModel chartViewModel;
    ProgressBar progressBar;
    FirebaseDatabase fdb;
    NavController navController;
    String q;
    BarDataSet barDataSet;
    FragmentDestinationBinding DB;

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";


    private String mParam1;
    private String mParam2;

    public Destination() {
        // Required empty public constructor
    }


    public static Destination newInstance(String param1, String param2) {
        Destination fragment = new Destination();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
        chartViewModel=new ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory
                .getInstance(getActivity().getApplication())).get(DbViewModel.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        DB= FragmentDestinationBinding.inflate(inflater, container, false);
        return  DB.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

       DB.caf.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               chartViewModel.getData();
               chartViewModel.getListMutableLiveData().observe(getViewLifecycleOwner(), new Observer<List<Zones>>() {
                   @Override
                   public void onChanged(List<Zones> zones) {

                       DB.listProgressbar.setVisibility(View.GONE);
                       DB.chart.setVisibility(View.VISIBLE);
                       System.out.println(zones.size()+ "suraj");
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
                               q="Cafeteria";
                           }
                           if(zones.get(2).getSocialD()>zones.get(1).getSocialD() && zones.get(2).getSocialD()>zones.get(0).getSocialD() && zones.get(2).getSocialD()>zones.get(3).getSocialD()){
                               q="Cafeteria";
                           }
                           if(zones.get(3).getSocialD()>zones.get(1).getSocialD() && zones.get(3).getSocialD()>zones.get(2).getSocialD() && zones.get(3).getSocialD()>zones.get(0).getSocialD()){
                               q="Cafeteria";
                           }
                       }

                       barDataSet = new BarDataSet(k, "zones");
                       barDataSet.setColor(R.color.black);
                       barDataSet.setValueTextColor(Color.BLACK);
                       barDataSet.setValueTextSize(16f);
                       BarData br = new BarData(barDataSet);
                       br.setBarWidth(0.5f);
                       DB.chart.setData(br);
                       DB.chart.getAxisLeft().setAxisMaximum(24);
                       DB.chart.getAxisRight().setAxisMaximum(24);
                       DB.chart.getXAxis().setAxisMinimum(0);
                       DB.chart.getXAxis().setAxisMaximum(5);
                       DB.chart.getXAxis().setGranularityEnabled(true);
                       DB.chart.getXAxis().setGranularity(1);

                       DB.chart.getDescription().setText("Covizone");
                       DB.chart.animateY(0);


                       if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                           CharSequence name = "Suraj";

                           int importance = NotificationManager.IMPORTANCE_DEFAULT;
                           NotificationChannel channel = new NotificationChannel("com.example.notific", name, importance);

                           // Register the channel with the system; you can't change the importance
                           // or other notification behaviors after this
                           System.out.println(q);
                           NotificationManager notificationManager = (NotificationManager) getActivity().getSystemService(getActivity().NOTIFICATION_SERVICE);
                           assert notificationManager != null;
                           notificationManager.createNotificationChannel(channel);
                           NotificationCompat.Builder builder = new NotificationCompat.Builder(getActivity().getApplicationContext(), "com.example.notific");
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
           }
       });



    }

}