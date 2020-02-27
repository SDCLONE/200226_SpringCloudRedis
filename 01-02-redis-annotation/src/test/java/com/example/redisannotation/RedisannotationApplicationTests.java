package com.example.redisannotation;

import com.example.redisannotation.controller.DataController;
import com.example.redisannotation.model.Data;
import com.example.redisannotation.service.DataService;
import com.netflix.discovery.converters.Auto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class RedisannotationApplicationTests {

    @Test
    void contextLoads() {
    }

    @Autowired
    private DataService dataService;

    @Test
    void getDataByIdTest(){
        DataController dataController=new DataController(dataService);
        Data oneData = dataController.getDataById(2);
        if (oneData==null){
            System.out.println();
        }
        System.out.println(oneData);
    }
}
