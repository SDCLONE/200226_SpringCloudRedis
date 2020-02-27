package com.example.redisannotation.model;

import java.io.Serializable;

@lombok.Data
//貌似可以不加Serializable
public class Data implements Serializable{
    public int ID;
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

    @Override
    public String toString() {
        return "Data{" +
                "ID=" + ID +
                ", Temperature=" + Temperature +
                ", Humidity=" + Humidity +
                ", Voice_in=" + Voice_in +
                '}';
    }
}
