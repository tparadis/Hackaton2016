package com.example.tp15009314.hackaton2016;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class DetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        ArrayList<String> listItem = new ArrayList<>();
        listItem.add("Yolo");
        listItem.add("blabla");
        ListView listView = (ListView) findViewById(R.id.event_listView);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,R.layout.donnees, R.id.donnee, listItem);
        listView.setAdapter(adapter);
        // give adapter to ListView UI element to render

        /*TextView textView1 = new TextView(this.getApplicationContext());
        textView1.setText("Blabla");

        TextView textView2 = new TextView(this.getApplicationContext());
        textView2.setText("YOLO");
        ListView listView = (ListView) findViewById(R.id.event_listView);
        listView.addView(textView1);
        listView.addView(textView2);
        */


    }
}
