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

    public String getRecordid(){
        return recordid;
    }

}
