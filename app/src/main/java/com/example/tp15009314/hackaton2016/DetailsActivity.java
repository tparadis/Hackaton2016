package com.example.tp15009314.hackaton2016;

import android.content.Intent;
import android.graphics.Color;
import android.media.Image;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class DetailsActivity extends AppCompatActivity {

    private Evenement evt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        Bundle extra = getIntent().getExtras();
        evt =  extra.getParcelable("evt");

        TextView title = (TextView) findViewById(R.id.event_titre);
        title.setText(evt.getTitre());
        TextView desc = (TextView) findViewById(R.id.event_desc);
        desc.setText(evt.getDescription());
        TextView adresse = (TextView) findViewById(R.id.event_adresse);
        adresse.setText(evt.getAdresse());
        TextView horaires = (TextView) findViewById(R.id.event_horaires);
        horaires.setText(evt.getHoraires());
        TextView scolaire = (TextView) findViewById(R.id.event_scolaire);
        scolaire.setText(evt.getScolaire());
        TextView themes = (TextView) findViewById(R.id.event_themes);
        themes.setText(evt.getThemes());
        if(evt.getTelephone() != null) {
            TextView tel = (TextView) findViewById(R.id.event_telephone);
            tel.setText("Phone : " + evt.getTelephone());
            tel.setTextColor(Color.BLUE);
        }
        ImageView img = (ImageView) findViewById(R.id.event_image);
        Glide.with(this).load(evt.getImage()).override(500, 500).into(img);

/*
        ArrayList<String> listItem = new ArrayList<>();
        listItem.add("yolo");
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

    public void launchMap(View view){
        Intent intent = new Intent(this, MapsActivity.class);
        intent.putExtra("LAT", evt.getLat());
        intent.putExtra("LON", evt.getLon());
        startActivityForResult(intent, 1);
    }

    public void callNumber(View view) {
        String number = evt.getTelephone();
        Intent callIntent = new Intent(Intent.ACTION_DIAL);
        callIntent.setData(Uri.parse("tel:"+number));
        startActivity(callIntent);
    }
}
