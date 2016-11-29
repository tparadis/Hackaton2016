package com.example.tp15009314.hackaton2016;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tp15009314 on 29/11/16.
 */
public class Evenement implements Parcelable
{

    String titre;
    String description;
    String themes;
    String horaires;
    String telephone;
    String email;
    String scolaire;
    String facebook;
    String adresse;
    String image;

   public Evenement(Event event) {
        this.titre = event.getTitre();
        this.description = event.getDescription();
        this.themes = event.getThemes();
        this.horaires = event.getHoraires();
        this.telephone = event.getTelephone();
        this.email = event.getEmail();
        this.scolaire = event.getScolaire();
        this.facebook = event.getFacebook();
        this.adresse = event.getAdresse();
        this.image = event.getImage();
    }

    protected Evenement(Parcel in) {
        titre = in.readString();
        description = in.readString();
        themes = in.readString();
        horaires = in.readString();
        telephone = in.readString();
        email = in.readString();
        scolaire = in.readString();
        facebook = in.readString();
        adresse = in.readString();
        image = in.readString();
    }

    public static final Creator<Evenement> CREATOR = new Creator<Evenement>() {
        @Override
        public Evenement createFromParcel(Parcel in) {
            return new Evenement(in);
        }

        @Override
        public Evenement[] newArray(int size) {
            return new Evenement[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(titre);
        dest.writeString(description);
        dest.writeString(themes);
        dest.writeString(horaires);
        dest.writeString(telephone);
        dest.writeString(email);
        dest.writeString(scolaire);
        dest.writeString(facebook);
        dest.writeString(adresse);
        dest.writeString(image);
    }

    public String getTitre() {
        return titre;
    }

    public String getDescription() {
        return description;
    }

    public String getThemes() {
        return themes;
    }

    public String getHoraires() {
        return horaires;
    }

    public String getTelephone() {
        return telephone;
    }

    public String getEmail() {
        return email;
    }

    public String getScolaire() {
        return scolaire;
    }

    public String getFacebook() {
        return facebook;
    }

    public String getAdresse() {
        return adresse;
    }

    public String getImage() {
        return image;
    }
}

