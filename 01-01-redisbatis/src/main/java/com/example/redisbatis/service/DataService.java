package com.example.redisbatis.service;

import com.example.redisbatis.model.Data;

public interface DataService {
    Data getDataById(int ID);
    void updateData(Data data);
    void deleteData(int ID);
}
