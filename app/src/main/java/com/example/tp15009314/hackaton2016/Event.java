package com.example.tp15009314.hackaton2016;

import java.util.Map;

/**
 * Created by greg on 29/11/16.
 */

public class Event {

    private String datasetid;
    private Map<String, String> fields;
    private String recordid;

    public Event(){}

    public Event(String datasetid, Map<String, String> fields, String recordid){
        this.datasetid = datasetid;
        this.fields = fields;
        this.recordid = recordid;
    }

    public String getDatasetid(){
        return datasetid;
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
        return (String) fields.get("horaires_detailles_fr:");
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
    public String getRecordid(){
        return recordid;
    }

}
