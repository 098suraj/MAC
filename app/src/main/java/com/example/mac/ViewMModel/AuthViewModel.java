package com.example.mac.ViewMModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.mac.Repository.AuthRepo;
import com.google.firebase.auth.FirebaseUser;

public class AuthViewModel extends AndroidViewModel {
    FirebaseUser currentUser;
    MutableLiveData<FirebaseUser> mutableLiveData;
    AuthRepo authRepo;

    public FirebaseUser getCurrentUser() {
        return currentUser;
    }

    public MutableLiveData<FirebaseUser> getMutableLiveData() {
        return mutableLiveData;
    }

    public AuthViewModel(@NonNull Application application) {
        super(application);
        authRepo = new AuthRepo(application);
        this.currentUser = authRepo.getFirebaseAuth().getCurrentUser();
        this.mutableLiveData = authRepo.getMutableLiveData();
    }

    public void signUp(String email, String password) {
        authRepo.signUp(email, password);
    }

    public void singIn(String email, String password) {
        authRepo.signIn(email, password);
    }

    public void signOut() {
        authRepo.signOut();
    }
}
