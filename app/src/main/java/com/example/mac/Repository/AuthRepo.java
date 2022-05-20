package com.example.mac.Repository;

import android.app.Application;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.example.mac.R;
import com.example.mac.View.MainActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.messaging.FirebaseMessaging;

public class AuthRepo {
    Application application;
    FirebaseAuth firebaseAuth;
    MutableLiveData<FirebaseUser> mutableLiveData;

    public AuthRepo(Application application) {
        this.application = application;
        this.firebaseAuth = FirebaseAuth.getInstance();
        this.mutableLiveData = new MutableLiveData<>();
    }

    public FirebaseAuth getFirebaseAuth() {
        return firebaseAuth;
    }

    public MutableLiveData<FirebaseUser> getMutableLiveData() {
        return mutableLiveData;
    }

    public void signUp(String email, String password) {
        firebaseAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            mutableLiveData.postValue(firebaseAuth.getCurrentUser());
                            Toast.makeText(application.getApplicationContext(), "Registered Successfully", Toast.LENGTH_SHORT).show();

                        } else {
                            Toast.makeText(application.getApplicationContext(), task.getException().getMessage(), Toast.LENGTH_LONG).show();
                        }
                    }
                });



    }
     public  void token(){

     }

    public void signIn(String email, String password) {
         firebaseAuth.signInWithEmailAndPassword(email, password)
                 .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                     @Override
                     public void onComplete(@NonNull Task<AuthResult> task) {
                         if (task.isSuccessful()){
                             mutableLiveData.postValue(firebaseAuth.getCurrentUser());
                             Toast.makeText(application.getApplicationContext(), "Login Successfully", Toast.LENGTH_SHORT).show();

                         }else{
                             Toast.makeText(application.getApplicationContext(), task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                         }
                     }
                 });
    }
    public void signOut(){
        firebaseAuth.signOut();
    }

    public void FirestoreData(){

    }
}
