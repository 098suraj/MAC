package com.example.mac.Repository;

import android.app.Application;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.mac.Model.Users;
import com.example.mac.Model.Zones;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class DbRepo {
    dataBaseEventListner dataBaseEventListner;
    FirebaseDatabase fdb;
    DatabaseReference dbr;
    List<Zones> zonesitems;
    List<String>zones;
    Application application;

    //------------------------Constructor---------------------------------------------------//
    public DbRepo(dataBaseEventListner dataBaseEventListner1, Application application) {
        this.dataBaseEventListner = dataBaseEventListner1;
        this.application = application;
        this.zonesitems = new ArrayList<>();
        this.zones=new ArrayList<>();
        this.fdb = FirebaseDatabase.getInstance("https://covizone-9c238-default-rtdb.asia-southeast1.firebasedatabase.app/");
        this.dbr = fdb.getReference("covizone-9c238-default-rtdb").child("name");
    }

    //---------------------------Adding-Users---------------------------------------------------//
//    public  void addUsers(String firstName, String lastName, String rollNo,String email ){
//        Users users=new Users(email, );
//
//        dbr.child(firstName).setValue(users).addOnCompleteListener(new OnCompleteListener<Void>() {
//            @Override
//            public void onComplete(@NonNull Task<Void> task) {
//                if (task.isSuccessful()){
//                    dataBaseEventListner.uploadStatus(true);
//                }else
//                    dataBaseEventListner.onError(task.getException());
//            }
//        });
//    }
    //-------------------------xxx--------------------------------------------------------------//

    public void getData() {

        fdb.getReference("placeA").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for (DataSnapshot ds : snapshot.getChildren()) {

                    zones.add(ds.getKey());
                  //  System.out.println(ds);
                    dataBaseEventListner.OnCompleteZones(zones);
                    fdb.getReference("placeA").child(ds.getKey()).child("data").limitToLast(1).addValueEventListener(new ValueEventListener() {
                        @Override

                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            try {
                                
                                for (DataSnapshot qo: snapshot.getChildren()
                                     ) {
                                    Zones zi=qo.getValue(Zones.class);
                                    zonesitems.add(zi);


                                }
                                System.out.println(zonesitems.size());
                                System.out.println(zonesitems.get(0).getZone());
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

            }


            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

            for (int i =0;i<zonesitems.size();i++){
                System.out.println(zonesitems.get(i).getSocialD());
            }

    }


    public interface dataBaseEventListner {
        void uploadStatus(boolean complete);

        public void OnComplete(List<Zones> list);
        public void OnCompleteZones(List<String> list);

        void onError(Exception e);
    }

}
