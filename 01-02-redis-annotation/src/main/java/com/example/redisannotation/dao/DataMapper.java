package com.example.redisannotation.dao;


import com.example.redisannotation.model.Data;

public interface DataMapper {
    Data getDataById(int ID);
    void updateData(Data data);
    void deleteData(int ID);
}
