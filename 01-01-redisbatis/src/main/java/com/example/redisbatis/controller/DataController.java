package com.example.redisbatis.controller;

import com.example.redisbatis.model.Data;
import com.example.redisbatis.service.DataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/data")
public class DataController {
    @Autowired
    private DataService dataService;

    @RequestMapping("/getDataById")
    public Data getDataById(@RequestParam(value = "ID") int ID){
        return dataService.getDataById(ID);
    }

    @RequestMapping("/updateData")
    public void updateData(Data data){
        dataService.updateData(data);
    }
}
