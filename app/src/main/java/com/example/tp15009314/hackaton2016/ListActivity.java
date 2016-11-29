package com.example.tp15009314.hackaton2016;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class ListActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;

    private RecyclerView.LayoutManager mLayoutManager;
    private List<Evenement> persons;

    // This method creates an ArrayList that has three Person objects
// Checkout the project associated with this tutorial on Github if
// you want to use the same images.
    private void initializeData() {
        persons = new ArrayList<>();
        persons.add(new Evenement("Emma Wilson", "23 years old"));
        persons.add(new Evenement("Lavery Maiss", "25 years old"));
        persons.add(new Evenement("Lillie Watts", "35 years old"));
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        mRecyclerView = (RecyclerView)findViewById(R.id.rv);
        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        initializeData();
        RVAdapter adapter = new RVAdapter(persons);
        mRecyclerView.setAdapter(adapter);
    }

    public void AfficherDetails(View view) {
        Intent intent = new Intent(this, DetailsActivity.class);
        startActivityForResult(intent, 1);
    }
}





