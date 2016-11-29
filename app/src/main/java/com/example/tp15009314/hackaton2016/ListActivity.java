package com.example.tp15009314.hackaton2016;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ListActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;

    private RecyclerView.LayoutManager mLayoutManager;
    private List<Event> events;

    // This method creates an ArrayList that has three Person objects
// Checkout the project associated with this tutorial on Github if
// you want to use the same images.
    private List<Event> initializeData() {
      final List<Event> events2 = new ArrayList<>();
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("");

        myRef.limitToFirst(15).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot child : dataSnapshot.getChildren()){
                    Map<String, Object> map = (Map<String, Object>) child.getValue();
                    String datasetid = (String) map.get("datasetid");
                    Map<String, String> fields = (Map<String, String>) map.get("fields");
                    String recordid = (String) map.get("recordid");
                    Event event = new Event(datasetid, fields, recordid);
                    events2.add(event);
                    System.out.println(datasetid);
                    System.out.println(fields.toString());
                    System.out.println("dkqzioduhsude" + recordid);

                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        return events2;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        mRecyclerView = (RecyclerView)findViewById(R.id.rv);
        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        events = initializeData();
        System.out.println("Size" + events.size());
        RVAdapter adapter = new RVAdapter(events);
        mRecyclerView.setAdapter(adapter);
    }


}





