package com.example.redisannotation.controller;


import com.example.redisannotation.model.Data;
import com.example.redisannotation.service.DataService;
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

    @Autowired
    public DataController(DataService dataService){
        this.dataService=dataService;
    }

    @RequestMapping("/getDataById")
    public Data getDataById(@RequestParam(value = "ID") int ID){
//        return dataService.getDataById(ID)!=null?dataService.getDataById(ID):new Data(0,0,0,0);
        return dataService.getDataById(ID);

    }

    @RequestMapping("/updateData")
    public void updateData(Data data){
        dataService.updateData(data);
    }

    @RequestMapping("/deleteData")
    public void deleteData(@RequestParam(value = "ID") int ID){
        dataService.deleteData(ID);
    }
}
