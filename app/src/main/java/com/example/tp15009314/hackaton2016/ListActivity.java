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

import static android.icu.lang.UCharacter.GraphemeClusterBreak.V;

public class ListActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private RVAdapter adapter;

    private FirebaseDatabase database = FirebaseDatabase.getInstance();
    private DatabaseReference myRef = database.getReference("");

    private RecyclerView.LayoutManager mLayoutManager;
    private List<Event> events = new ArrayList<Event>();

    private int last = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        mRecyclerView = (RecyclerView)findViewById(R.id.rv);

        adapter = new RVAdapter(events);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        mRecyclerView.setAdapter(adapter);

        loadMore();
    }


    public void loadMore() {

        myRef.startAt(null, Integer.toString(last)).limitToFirst(15).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot child : dataSnapshot.getChildren()){
                    Map<String, Object> map = (Map<String, Object>) child.getValue();
                    String datasetid = (String) map.get("datasetid");
                    Map<String, String> fields = (Map<String, String>) map.get("fields");
                    String recordid = (String) map.get("recordid");
                    Event event = new Event(datasetid, fields, recordid);
                    events.add(event);
                    System.out.println("\n \n \n");
                    System.out.println(child.getRef());
                    System.out.println(datasetid);
                    System.out.println(fields.toString());
                    System.out.println("recordid");
                    last++;

                }

                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    public void loadButton(View view) {
        loadMore();
    }
}





