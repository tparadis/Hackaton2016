package com.example.tp15009314.hackaton2016;

import android.content.Intent;
import android.os.Parcelable;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.example.tp15009314.hackaton2016.R.id.map;

public class RVAdapter extends RecyclerView.Adapter<RVAdapter.EvenementViewHolder>{

    List<Event> evts;

    public RVAdapter(List<Event> evts) {
        this.evts = evts;
    }

    @Override
    public EvenementViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card, parent, false);
        EvenementViewHolder evh = new EvenementViewHolder(v);
        return evh;
    }

    @Override
    public void onBindViewHolder(EvenementViewHolder holder,final int position) {
        holder.eventName.setText(evts.get(position).getTitre());
        holder.eventAdresse.setText(evts.get(position).getAdresse());
        holder.eventDate.setText(evts.get(position).getDates());
        Glide.with(holder.eventPhoto.getContext()).load(evts.get(position).getImage()).override(200,200).into(holder.eventPhoto);

        holder.cv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), DetailsActivity.class);
                intent.putExtra("evt",new Evenement(evts.get(position)));
                Map<String,String> map = (Map<String, String>) evts.get(position).getFields();
                HashMap<String, String> hashMap =
                        (map instanceof HashMap)
                                ? (HashMap) map
                                : new HashMap<String, String>(map);
                intent.putExtra("map", hashMap);
                v.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return evts.size();
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    public static class EvenementViewHolder extends RecyclerView.ViewHolder {
        CardView cv;
        TextView eventName;
        TextView eventDate;
        TextView eventAdresse;
        ImageView eventPhoto;

        EvenementViewHolder(View itemView) {
            super(itemView);
            cv = (CardView)itemView.findViewById(R.id.cv);
            eventName = (TextView)itemView.findViewById(R.id.event_name);
            eventDate = (TextView)itemView.findViewById(R.id.event_datelist);
            eventAdresse = (TextView)itemView.findViewById(R.id.event_adresselist);
            eventPhoto = (ImageView)itemView.findViewById(R.id.event_photo);
        }
    }

}
