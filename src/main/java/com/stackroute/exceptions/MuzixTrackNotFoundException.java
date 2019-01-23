package com.stackroute.exceptions;


public class MuzixTrackNotFoundException extends Exception{
    public String message;

    //constructor

    public MuzixTrackNotFoundException(){}


    //exception constructor

    public MuzixTrackNotFoundException(String message){
        super(message);
        this.message=message;
    }
}

