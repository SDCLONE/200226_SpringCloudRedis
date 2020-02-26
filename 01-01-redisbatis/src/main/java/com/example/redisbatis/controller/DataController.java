package com.example.redisbatis.controller;

import com.example.redisbatis.model.Data;
import com.example.redisbatis.service.DataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
}
