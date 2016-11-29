package com.example.tp15009314.hackaton2016;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class RVAdapter extends RecyclerView.Adapter<RVAdapter.EvenementViewHolder>{

    List<Evenement> evts;

    public RVAdapter(List<Evenement> evts) {
        this.evts = evts;
    }

    @Override
    public EvenementViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card, parent, false);
        EvenementViewHolder evh = new EvenementViewHolder(v);
        return evh;
    }

    @Override
    public void onBindViewHolder(EvenementViewHolder holder, int position) {
        holder.eventName.setText(evts.get(position).name);
        holder.eventAge.setText(evts.get(position).age);
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
        TextView eventAge;
        ImageView eventPhoto;

        EvenementViewHolder(View itemView) {
            super(itemView);
            cv = (CardView)itemView.findViewById(R.id.cv);
            eventName = (TextView)itemView.findViewById(R.id.event_name);
            eventAge = (TextView)itemView.findViewById(R.id.event_age);
            eventPhoto = (ImageView)itemView.findViewById(R.id.event_photo);
        }
    }

}
