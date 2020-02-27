package com.example.redisbatis.dao;

import com.example.redisbatis.model.Data;


public interface DataMapper {
    Data getDataById(int ID);
    void updateData(Data data);
    void deleteData(int ID);
}
