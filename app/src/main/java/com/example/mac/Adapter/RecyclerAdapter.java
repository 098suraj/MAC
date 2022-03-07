package com.example.mac.Adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mac.Model.Users;
import com.example.mac.databinding.ItemBinding;

import java.util.ArrayList;
import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {
    private List<Users>usersList;
    private static final String TAG = "RecyclerAdapter";
    private List<Users> stringList = new ArrayList<>();

    public void updateList(List<Users> stringList) {
        this.stringList = stringList;
    }

    public void setUsersList(List<Users> usersList) {
        this.usersList = usersList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        ItemBinding recyclerRowBinding = ItemBinding.inflate(layoutInflater, parent, false);
        return new ViewHolder(recyclerRowBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String firstname=usersList.get(position).getRandom1().toString();
        String lasttname=usersList.get(position).getRandom2().toString();
        holder.bindView(firstname,lasttname);

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

        public void bindView(String firstname,String lastname) {
            recyclerRowBinding.textView.setText(firstname);
            recyclerRowBinding.lastnamercy.setText(lastname);

        }
    }
}