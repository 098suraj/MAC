package com.example.mac.Repository;

import android.app.Application;

import androidx.annotation.NonNull;

import com.example.mac.Model.Users;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
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
    List<Users>usersList;
    Application application;

    //------------------------Constructor---------------------------------------------------//
    public DbRepo(dataBaseEventListner dataBaseEventListner1, Application application) {
       this.dataBaseEventListner=dataBaseEventListner1;
       this.application=application;
       this.usersList=new ArrayList<>();
       this.fdb=FirebaseDatabase.getInstance("https://covizone-9c238-default-rtdb.asia-southeast1.firebasedatabase.app/");
       this.dbr=fdb.getReference("covizone-9c238-default-rtdb").child("name");
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

    public void getData(){

        dbr.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot dsnapshot) {
                usersList.clear();
                for (DataSnapshot dataSnapshot:dsnapshot.getChildren()
                     ) {
                    Users users=new Users();
                     users.setRandom1(dataSnapshot.getKey());
                     if (dataSnapshot.hasChild("count")){
                     users.setRandom2(dataSnapshot.child("count").child("count").getValue().toString());}
                    usersList.add(users);

                    dataBaseEventListner.OnComplete( usersList);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public interface dataBaseEventListner{
        void uploadStatus(boolean complete);
        public void OnComplete(List<Users> list);
        void onError(Exception e);
    }
}
