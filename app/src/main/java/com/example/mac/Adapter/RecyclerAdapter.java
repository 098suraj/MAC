package com.example.mac.Adapter;

import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mac.Model.Users;
import com.example.mac.Model.Zones;
import com.example.mac.R;
import com.example.mac.databinding.ItemBinding;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {
    private List<Zones>usersList;
    private static final String TAG = "RecyclerAdapter";
    private Map<String,Integer> stringList = new HashMap<>();
    int[] colors = {Color.rgb(255,200,255), Color.rgb(255,242,0),Color.rgb(225,245,255),Color.rgb(200,255,255)};


    public void setUsersList(List<Zones> usersList) {
        this.usersList = usersList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        ItemBinding recyclerRowBinding = ItemBinding.inflate(layoutInflater, parent, false);
        return new ViewHolder(recyclerRowBinding);
    }

    public RecyclerAdapter(List<Zones> usersList) {
        this.usersList = usersList;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String s=usersList.get(position).getZone();
        int t=usersList.get(position).getSocialD();
        System.out.println(usersList.get(position).getZone());
        holder.bindView(s,t,position);
    }

    @Override
    public int getItemCount() {
        Log.d(TAG, "getItemCount: " + stringList.size());
        if (usersList==null)
            return 0;
        else
        return usersList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        ItemBinding recyclerRowBinding;

        public ViewHolder(@NonNull ItemBinding recyclerRowBinding) {
            super(recyclerRowBinding.getRoot());
            this.recyclerRowBinding = recyclerRowBinding;

            recyclerRowBinding.textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.d(TAG, "onClick: " + getAdapterPosition());
                }
            });
        }

        public void bindView(String s,int t,int k) {
            if (k==0){
                recyclerRowBinding.textView.setText("Cafeteria");
            }
            if (k==1){
                recyclerRowBinding.textView.setText("Library");
            }if (k==2){
                recyclerRowBinding.textView.setText("E-Block");
            }if (k==3){
                recyclerRowBinding.textView.setText("Ground");
            }

           recyclerRowBinding.lastnamercy.setText(String.valueOf(t));
            if (k<4){
                recyclerRowBinding.textView.setBackgroundColor(colors[k]);
            }



        }
    }
}