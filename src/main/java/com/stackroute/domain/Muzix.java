package com.stackroute.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Document
public class Muzix {

    //assigning primary key
    @Id
    private int trackId;
    private String trackName;
    private String comment;


    //toString method
    @Override
    public String toString() {
        return "Muzix{" +
                "trackId=" + trackId +
                ", trackName='" + trackName + '\'' +
                ", comment='" + comment + '\'' +
                '}';
    }


    //empty constructor
    public Muzix(){}


    //setters and getter for id,name and comment

    public int getTrackId() {
        return trackId;
    }

    public void setTrackId(int trackId) {
        this.trackId = trackId;
    }

    public String getTrackName() {
        return trackName;
    }

    public void setTrackName(String trackName) {
        this.trackName = trackName;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }


    //constructor for id,name and comment
    public Muzix(int trackId, String trackName, String comment) {
        this.trackId = trackId;
        this.trackName = trackName;
        this.comment = comment;
    }

}
