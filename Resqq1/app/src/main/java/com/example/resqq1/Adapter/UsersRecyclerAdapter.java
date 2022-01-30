package com.example.resqq1.Adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.resqq1.Model.User;
import com.example.resqq1.R;

import java.util.List;

public class UsersRecyclerAdapter extends RecyclerView.Adapter<UsersRecyclerAdapter.UserViewHolder> {

    private List<User> listUsers;


    public UsersRecyclerAdapter(List<User> listUsers) {
        this.listUsers = listUsers;
    }


    @Override
    public UserViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_user_recycler, parent, false);
        return new UserViewHolder(itemView);
    }



    @Override
    public void onBindViewHolder(UserViewHolder holder, int position) {
        holder.username.setText(listUsers.get(position).getName());
        holder.useremail.setText(listUsers.get(position).getEmail());
        holder.userphone.setText(listUsers.get(position).getPhone());
    }

    @Override
    public int getItemCount() {
        Log.v(UsersRecyclerAdapter.class.getSimpleName(),""+ listUsers.size());
        return listUsers.size();
    }


    public class UserViewHolder extends RecyclerView.ViewHolder {
        public TextView username, useremail, userphone;

        public UserViewHolder(View view) {
            super(view);
            username =  view.findViewById(R.id.username);
            useremail =  view.findViewById(R.id.useremail);
            userphone =  view.findViewById(R.id.userphone);
        }
    }
}