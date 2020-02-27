package com.example.redisannotation.service;


import com.example.redisannotation.model.Data;

public interface DataService {
    Data getDataById(int ID);
    Data updateData(Data data);
    void deleteData(int ID);
}
