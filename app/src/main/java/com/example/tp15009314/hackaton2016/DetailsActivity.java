package com.example.tp15009314.hackaton2016;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.media.Image;
import android.media.Rating;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import static android.R.attr.start;
import static android.R.attr.value;
import static android.icu.lang.UCharacter.GraphemeClusterBreak.T;
import static com.example.tp15009314.hackaton2016.R.id.email;
import static com.example.tp15009314.hackaton2016.R.id.ratingBar;
import static com.example.tp15009314.hackaton2016.R.id.textView;

public class DetailsActivity extends AppCompatActivity {

    private Evenement evt;
    HashMap<String, String> fields;
    private String facebook;
    private String mail;
    private FirebaseDatabase database = FirebaseDatabase.getInstance();
    private DatabaseReference myRef = database.getReference("");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        Bundle extra = getIntent().getExtras();
        evt = extra.getParcelable("evt");
        fields = (HashMap<String, String>) extra.getSerializable("map");
        System.out.println(fields.toString());

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
        if (evt.getTelephone() != null) {
            TextView tel = (TextView) findViewById(R.id.event_telephone);
            tel.setText("Phone : " + evt.getTelephone());
            tel.setTextColor(Color.BLUE);
        }

        // System.out.println("INTERNET : " + evt.getInternet());
        TextView internet = (TextView) findViewById(R.id.event_internet);
        internet.setText(evt.getInternet());
        internet.setTextColor(Color.BLUE);

        facebook = findFacebook();
        mail = findMail();

        ImageView img = (ImageView) findViewById(R.id.event_image);
        Glide.with(this).load(evt.getImage()).override(500, 500).into(img);

        final RatingBar ratingBar = (RatingBar) findViewById(R.id.ratingBar) ;
        ratingBar.setRating(Float.parseFloat(fields.get("note")));

        ratingBar.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_UP) {
                   rating(ratingBar);

                    v.setPressed(false);
                }
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    v.setPressed(true);
                }

                if (event.getAction() == MotionEvent.ACTION_CANCEL) {
                    v.setPressed(false);
                }




                return true;
            }});

    }

    public void launchMap(View view) {
        Intent intent = new Intent(this, MapsActivity.class);
        intent.putExtra("LAT", evt.getLat());
        intent.putExtra("LON", evt.getLon());
        intent.putExtra("NOM", evt.getTitre());
        startActivityForResult(intent, 1);
    }

    public void callNumber(View view) {
        String number = evt.getTelephone();
        if (number != null) {
            Intent callIntent = new Intent(Intent.ACTION_DIAL);
            callIntent.setData(Uri.parse("tel:" + number));
            startActivity(callIntent);
        }
    }

    public void goToTheInternet(View view) {
        String internet = evt.getInternet();
        if (internet != null) {
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(Uri.parse(internet));
            startActivity(intent);
        }
    }


    public String findFacebook() {
        Iterator it = fields.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<String, String> entry = (Map.Entry<String, String>) it.next();
            String key = entry.getKey();
            if (!key.equals("geolocalisation")) {
                String value = entry.getValue().toString();
                if (value.contains("facebook")) {
                    ImageButton fb = (ImageButton) findViewById(R.id.event_facebook);
                    Glide.with(this).load(R.drawable.fb).override(1, 150).into(fb);
                    fb.setVisibility(View.VISIBLE);
                    return value;
                }
            }
        }
        return "";
    }

    public String findMail() {
        Iterator it = fields.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<String, String> entry = (Map.Entry<String, String>) it.next();
            String key = entry.getKey();
            if (!key.equals("geolocalisation")) {
                String value = entry.getValue().toString();
                if (value.contains("@")) {
                    ImageButton mail = (ImageButton) findViewById(R.id.event_mail);
                    Glide.with(this).load(R.drawable.gmail).override(150, 150).into(mail);
                    mail.setVisibility(View.VISIBLE);
                    return value;
                }
            }
        }
        return "";
    }


    public void goToFacebook(View view) {
        if (facebook != "") {
            boolean sucess = true;
            try {
                getApplicationContext().getPackageManager().getPackageInfo("com.facebook.katana", 0);
                System.out.println(facebook);
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("fb://facewebmodal/f?href=" + facebook));
                startActivity(intent);
            } catch (PackageManager.NameNotFoundException e) {
                sucess = false;
                e.printStackTrace();
            }

            if (!sucess) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(facebook));
                startActivity(intent);
            }
        }
    }

    public void goToMail(View view) {
        if (mail != "") {
            Intent intent = new Intent(Intent.ACTION_VIEW);
            Uri data = Uri.parse("mailto:?to=" + mail);
            intent.setData(data);
            startActivity(intent);
        }
    }

    public void rating (RatingBar rate) {
        System.out.println("RATING TROLOL");
        float note = Float.parseFloat(fields.get("" +
                ""));
        int nbVotes= Integer.parseInt(fields.get("nbVotes"));
        float myRate = rate.getRating();
System.out.println(note);
        note = (note + myRate);
        if(nbVotes == 0) {
            System.out.println("aa");
            rate.setRating(0);
        }
        else {
            System.out.println("bb");
            float finalNote = (note / nbVotes);
            rate.setRating(finalNote);
        }
        myRef.child(fields.get("id")).child("fields").child("note").setValue(Float.toString(note));
        myRef.child(fields.get("id")).child("fields").child("nbVotes").setValue(Integer.toString(++nbVotes));

    }
}
