package com.example.tp15009314.hackaton2016;

import java.util.Map;


public class Event {

    private Map<String, String> fields;

    public Event(){}

    public Event(Map<String, String> fields){
        this.fields = fields;
    }

    public Map<String, String> getFields(){
        return fields;
    }

    public String getTitre() {
        return (String) fields.get("titre_fr");
    }
    public String getDescription() {
        return (String) fields.get("description_fr");
    }
    public String getThemes() {
        return (String) fields.get("thematiques");
    }

    public String getHoraires() {
        return (String) fields.get("horaires_detailles_fr");
    }
    public String getDates() {
        return (String) fields.get("date_debut");
    }
    public String getTelephone() {
        return (String) fields.get("telephone_du_lieu");
    }
    public String getEmail() {
        return (String) fields.get("email");
    }
    public String getScolaire() {
        return (String) fields.get("publics_concernes");
    }
    public String getFacebook() {
        return (String) fields.get("liens_du_lieu");
    }
    public String getAdresse() {
        return (String) fields.get("adresse");
    }
    public String getImage(){
        return (String) fields.get("image");
    }
    public String getInternet(){
        return (String) fields.get("lien");
    }
    public String getLat() { return (String) fields.get("0"); }
    public String getLon() { return (String) fields.get("1"); }

}
