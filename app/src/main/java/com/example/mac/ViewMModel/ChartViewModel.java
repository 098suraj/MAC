package com.example.mac.ViewMModel;

import android.app.Application;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.mac.Model.Zones;
import com.example.mac.Repository.ChartRepo;
import com.example.mac.Repository.DbRepo;

import java.util.List;

public class ChartViewModel extends AndroidViewModel implements ChartRepo.dataBaseEventListner {
    private ChartRepo chartRepo;
    MutableLiveData<List<Zones>> listMutableLiveData = new MutableLiveData<>();
    MutableLiveData<List<String >> listMutableLive = new MutableLiveData<>();

    public MutableLiveData<List<String>> getListMutableLive() {
        return listMutableLive;
    }

    public MutableLiveData<List<Zones>> getListMutableLiveData() {
        return listMutableLiveData;
    }

    public ChartViewModel(@NonNull Application application) {
        super(application);
        chartRepo = new ChartRepo(this, application);
    }

    public void getData(String s1) {
        chartRepo.getData(s1);
    }

    @Override
    public void uploadStatus(boolean complete) {

    }

    @Override
    public void OnComplete(List<Zones> list) {
        listMutableLiveData.setValue(list);
    }

    @Override
    public void OnCompleteZones(List<String> list) {
        listMutableLive.setValue(list);
    }

    @Override
    public void onError(Exception e) {
        Toast.makeText(getApplication(), e.getMessage(), Toast.LENGTH_SHORT).show();
    }


}
