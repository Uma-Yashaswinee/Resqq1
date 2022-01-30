package com.example.resqq1.LegalUserActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.resqq1.Adapter.LegalUserRecyclerAdapter;
import com.example.resqq1.DatabaseHelper.LegalUserDatabaseHelper;
import com.example.resqq1.Model.LegalUser;
import com.example.resqq1.R;

import java.util.ArrayList;

public class LegalUserListActivity extends AppCompatActivity implements LegalUserRecyclerAdapter.OnItemClickListener {

    private AppCompatActivity activity = LegalUserListActivity.this;
    private TextView textViewName1;
    private RecyclerView recyclerViewLegalUsers;
    private ArrayList<LegalUser> legalUserslist;
    private LegalUserDatabaseHelper legalUserDatabaseHelper;
    private LegalUserRecyclerAdapter legalUserRecyclerAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.legal_user_lists);

        initViews();
        initObjects();
    }



    private void initViews() {
        // textViewName1 = findViewById(R.id.workername);
        recyclerViewLegalUsers = (RecyclerView) findViewById(R.id.recyclerViewLegalUsers);
    }

    private void initObjects() {

        legalUserslist = new ArrayList<>();

        legalUserRecyclerAdapter = new LegalUserRecyclerAdapter(legalUserslist);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerViewLegalUsers.setLayoutManager(mLayoutManager);
        recyclerViewLegalUsers.setItemAnimator(new DefaultItemAnimator());
        recyclerViewLegalUsers.setHasFixedSize(true);
        recyclerViewLegalUsers.setAdapter(legalUserRecyclerAdapter);
        legalUserDatabaseHelper = new LegalUserDatabaseHelper(activity);


        recyclerViewLegalUsers.setAdapter(legalUserRecyclerAdapter);
        legalUserRecyclerAdapter.setClickListener(this);

//        String emailFromIntent = getIntent().getStringExtra("EMAIL");
//        textViewName1.setText(emailFromIntent);
        getDataFromSQLite();
    }


    private void getDataFromSQLite() {
        // AsyncTask is used that SQLite operation not blocks the UI Thread.
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... params) {
                legalUserslist.clear();
                legalUserslist.addAll(legalUserDatabaseHelper.getAllLegalUser());
                return null;
            }
            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                legalUserRecyclerAdapter.notifyDataSetChanged();
            }
        }.execute();
    }

    @Override
    public void onClick(View view, int position) {
        final LegalUser legalUser = legalUserslist.get(position);
        Intent i = new Intent(this, LegalUserDetailsAcivity.class);
        i.putExtra("name", legalUser.getName());
        i.putExtra("email", legalUser.getEmail());
        i.putExtra("phone", legalUser.getPhone());
        i.putExtra("location", legalUser.getLocation());
        startActivity(i);

    }
}