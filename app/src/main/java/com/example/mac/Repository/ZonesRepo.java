package com.example.mac.Repository;

import android.app.Application;

import com.example.mac.Model.Zones;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

public class ZonesRepo {
    databaseEventListner del;
    FirebaseDatabase fdb;
    DatabaseReference dr;
    List<Zones> ZoneDetailsList;
    Application application;
    String zone;

    public ZonesRepo(ZonesRepo.databaseEventListner dl ,Application application,String zone){
        this.application=application;
        this.del=dl;
        fdb= FirebaseDatabase.getInstance("https://covizone-9c238-default-rtdb.asia-southeast1.firebasedatabase.app/");
        this.dr= fdb.getReference("placeA").child(zone);
    }



    public interface databaseEventListner {
        public void Oncomplete(List<Zones> ZoneDataList);
        void error(Exception e);
    }
}
