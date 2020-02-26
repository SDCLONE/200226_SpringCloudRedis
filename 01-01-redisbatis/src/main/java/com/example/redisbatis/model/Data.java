package com.example.redisbatis.model;

import java.io.Serializable;

@lombok.Data
//貌似可以不加Serializable
public class Data implements Serializable{
    private int ID;
    private float Temperature;
    private int Humidity;
    private int Voice_in;
    public Data(){

    }

    public Data(int ID, float temperature, int humidity, int voice_in) {
        this.ID = ID;
        Temperature = temperature;
        Humidity = humidity;
        Voice_in = voice_in;
    }
}
