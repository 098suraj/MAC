package com.example.mac.ViewMModel;

import android.app.Application;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.mac.Model.Users;
import com.example.mac.Repository.DbRepo;

import java.util.List;

public class DbViewModel extends AndroidViewModel implements DbRepo.dataBaseEventListner  {
    private DbRepo repo;
    MutableLiveData<List<Users>> listMutableLiveData=new MutableLiveData<>();
    public DbViewModel(@NonNull Application application) {
        super(application);
        repo=new DbRepo(this,application);
    }
    public  void addUsers(String firstName, String lastName, String rollNo,String email ){
        //repo.addUsers(firstName, lastName, rollNo, email);
    }
   public void getData(){
        repo.getData();
   }

    public MutableLiveData<List<Users>> getListMutableLiveData() {
        return listMutableLiveData;
    }

    @Override
    public void uploadStatus(boolean complete) {
        if (complete){
            Toast.makeText(getApplication(),"Success",Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void OnComplete(List<Users> list) {
        listMutableLiveData.setValue(list);
    }

    @Override
    public void onError(Exception e) {
        Toast.makeText(getApplication(),e.getMessage(),Toast.LENGTH_SHORT).show();
    }
}
