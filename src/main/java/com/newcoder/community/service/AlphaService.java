package com.newcoder.community.service;

import com.newcoder.community.dao.AlphaDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Service //业务层注解
//@Scope("prototype")
public class AlphaService {
    @Autowired
    private AlphaDao alphaDao;
    public AlphaService(){
        System.out.println("实例化AlphaService");
    }
    @PostConstruct//在构造方法之后执行
    public void init(){
        System.out.println("初始化AlphaService");
    }
    @PreDestroy//在销毁对象之前调用
    public void destory(){
        System.out.println("销毁AlphaService");
    }
    public String find(){
        return alphaDao.select();
    }
}
