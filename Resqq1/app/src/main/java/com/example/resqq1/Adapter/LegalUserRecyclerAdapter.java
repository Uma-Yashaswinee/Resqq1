package com.example.resqq1.Adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.resqq1.Model.LegalUser;
import com.example.resqq1.R;


import java.util.List;

public class LegalUserRecyclerAdapter extends RecyclerView.Adapter<LegalUserRecyclerAdapter.LegalUserViewHolder> {


    private List<LegalUser> legalUserList;
    OnItemClickListener onItemClickListener;

    public LegalUserRecyclerAdapter(List<LegalUser> legalUserList) {
        this.legalUserList = legalUserList;
    }


    @Override
    public LegalUserViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_legal_user_recycler, parent, false);
        return new LegalUserViewHolder(itemView);
    }



    @Override
    public void onBindViewHolder(LegalUserViewHolder holder, int position) {
        holder.legalusername.setText(legalUserList.get(position).getName());
        holder.legaluseremail.setText(legalUserList.get(position).getEmail());
        holder.legaluserphone.setText(legalUserList.get(position).getPhone());
        holder.legaluserlocation.setText(legalUserList.get(position).getLocation());

    }

    @Override
    public int getItemCount() {
        Log.v(LegalUserRecyclerAdapter.class.getSimpleName(),""+legalUserList.size());
        return legalUserList.size();
    }

        public void setClickListener(OnItemClickListener itemClickListener) {
            this.onItemClickListener = itemClickListener;
        }



    public interface OnItemClickListener {
        public void onClick(View view, int position);
    }

    public class LegalUserViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView legalusername, legaluseremail, legaluserphone, legaluserlocation;

        public LegalUserViewHolder(View view) {
            super(view);
            legalusername =  view.findViewById(R.id.legalusername);
            legaluseremail =  view.findViewById(R.id.legaluseremail);
            legaluserphone =  view.findViewById(R.id.legaluserphone);
            legaluserlocation = view.findViewById(R.id.legaluserlocation);


            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (onItemClickListener != null) onItemClickListener.onClick(v, getAdapterPosition());        }
    }
}