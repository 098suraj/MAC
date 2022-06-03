package com.example.mac.Repository;

import android.app.Application;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.mac.Model.Zones;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

public class ChartRepo {
    dataBaseEventListner dataBaseEventListner;
    FirebaseDatabase fdb;
    DatabaseReference dbr;
    List<Zones> zonesitems;
    List<String>zones;
    Application application;

    public ChartRepo(ChartRepo.dataBaseEventListner dataBaseEventListner, Application application) {
        this.dataBaseEventListner = dataBaseEventListner;
        this.fdb = FirebaseDatabase.getInstance("https://covizone-9c238-default-rtdb.asia-southeast1.firebasedatabase.app/");
        this.application = application;
    }
    public void getData(String zone) {

        fdb.getReference("placeA").child(zone).child("data").limitToLast(3).addValueEventListener(new ValueEventListener() {
            @Override

            public void onDataChange(@NonNull DataSnapshot snapshot) {
                try {

                    for (DataSnapshot qo: snapshot.getChildren()
                    ) {
                        Zones zi=qo.getValue(Zones.class);
                        zonesitems.add(zi);

                    }
                    System.out.println(zonesitems.size()+"suraj22");
                    dataBaseEventListner.OnComplete(zonesitems);
                }catch (Exception e ){
                    Toast.makeText(application.getApplicationContext(), e.getMessage().toString(), Toast.LENGTH_SHORT).show();
                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }

    public interface dataBaseEventListner {
        void uploadStatus(boolean complete);
        public void OnComplete(List<Zones> list);
        public void OnCompleteZones(List<String> list);
        void onError(Exception e);
    }
}
