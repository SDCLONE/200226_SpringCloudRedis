package com.example.redisbatis.service;

import com.example.redisbatis.dao.DataMapper;
import com.example.redisbatis.model.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class DataServiceImpl implements DataService {

    @Autowired
    private DataMapper dataMapper;

    @Autowired
    private RedisTemplate redisTemplate;

    /*
     * redisTemplate使用前必须进行bean configuration
     * redisTemplate中提供下列几个方法
     * redisTemplate.opsForValue()  操作字符串
     * redisTemplate.opsForHash()   操作hash
     * redisTemplate.opsForList()   操作list
     * redisTemplate.opsForSet()    操作set
     * redisTemplate.opsForZSet()   操作有序set
     *
     */

    @Override
    public Data getDataById(int ID) {
        //定义redis操作operation
        boolean exists=redisTemplate.hasKey("ID_"+ID);
        Data data;
        if (exists){
            //缓存存在从缓存中拿数据
            System.out.println("从缓存拿数据");
            data= (Data) redisTemplate.opsForValue().get("ID_"+ID);
        }
        else{
            //缓存不存在就从数据库拿数据
            System.out.println("从数据库拿数据");
            data=dataMapper.getDataById(ID);
            //更新缓存
            redisTemplate.opsForValue().set("ID_"+ID,data,1,TimeUnit.MINUTES);
        }
        return data;
    }
}
