package com.example.tp15009314.hackaton2016;


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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.google.android.gms.analytics.internal.zzy.ch;


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

        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            private boolean scroll = false;
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);

                if (newState == RecyclerView.SCROLL_STATE_SETTLING) {
                    scroll = true;
                } else {
                    scroll = false;
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if(scroll && dy > 0)
                    loadMore();
            }
        });

        loadMore();
    }


    public void loadMore() {

        myRef.startAt(null, Integer.toString(last)).limitToFirst(15).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot child : dataSnapshot.getChildren()){
                    Map<String, Object> map = (Map<String, Object>) child.getValue();
                    Map<String, String> fields = (Map<String, String>) map.get("fields");
                    fields.put("0", child.child("geometry").child("coordinates").child("1").getValue().toString());
                    fields.put("1", child.child("geometry").child("coordinates").child("0").getValue().toString());

                    Event event = new Event(fields);
                    events.add(event);

                    System.out.println(child.getRef());

                    last++;

                }

                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}





