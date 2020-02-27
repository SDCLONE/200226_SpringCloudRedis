package com.example.redisannotation.service;


import com.example.redisannotation.dao.DataMapper;
import com.example.redisannotation.model.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class DataServiceImpl implements DataService {

    @Autowired
    private DataMapper dataMapper;

    /*
    @Cacheable用于查询缓存数据
    @CachePut用于修改时修改缓存数据
    @CacheEvict用于删除时清空缓存数据
            value相当于前缀   key相当于唯一后缀 keyGenerator相当于自定义唯一后缀   unless="#result==null" 相当于当写入缓存的值是空则忽略缓存
            例如value="hello" key="#ID"  则最终键值就是hello::3
            例如value="hello" keyGenerator="customGenerator" 最总键值 hello::（自定义部分）

     */


    @Override
    //缓存中key用keyGenerator 例如QueryID::com.example.redisannotation.service.DataServiceImpl:getDataById:2
//    @Cacheable(value = "QueryID",keyGenerator = "keyGenerator",unless = "#result==null")
    //缓存生成的key是QueryID::1  相当于value::ID
    @Cacheable(value = "QueryID",key = "#ID",unless = "#result==null")
    public Data getDataById(int ID) {
        return dataMapper.getDataById(ID);
    }

    //调用方法的spEL表达式需要方法有一个返回值
    @Override
    @CachePut(value = "QueryID",key = "#data.getID()")
    public Data updateData(Data data) {
        dataMapper.updateData(data);
        return data;  //必须有这一个，否则键值为空
    }

    @Override
    @CacheEvict(value = "QueryID",key="#ID")
    public void deleteData(int ID) {
        dataMapper.deleteData(ID);
    }
}
