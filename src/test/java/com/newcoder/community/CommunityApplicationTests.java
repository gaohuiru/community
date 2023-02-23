package com.newcoder.community;

import com.newcoder.community.dao.AlphaDao;
import com.newcoder.community.service.AlphaService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.test.context.ContextConfiguration;

import java.text.SimpleDateFormat;
import java.util.Date;

@SpringBootTest
@ContextConfiguration(classes = CommunityApplication.class) //以主文件作为配置类
class CommunityApplicationTests implements ApplicationContextAware {//BeanFatory为spring容器的主接口
    private ApplicationContext applicationContext;//记录spring 容器

    @Test
    void contextLoads() {
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
    @Test
    public void testApplicationContext(){
        System.out.println(applicationContext);

        AlphaDao alphaDao = applicationContext.getBean(AlphaDao.class);
        System.out.println(alphaDao.select());

        AlphaDao alphaDao1 = applicationContext.getBean("alphaHibernate",AlphaDao.class);
        System.out.println(alphaDao1.select());
    }
    @Test
    public void testBeanManagement(){
        AlphaService alphaService = applicationContext.getBean(AlphaService.class);//默认使用单例模式，只会实例化一次
        System.out.println(alphaService);

        alphaService = applicationContext.getBean(AlphaService.class);//默认使用单例模式，只会实例化一次
        System.out.println(alphaService);
    }
    @Test
    public void testBeanConfig(){
        SimpleDateFormat simpleDateFormat = applicationContext.getBean(SimpleDateFormat.class);
        System.out.println(simpleDateFormat.format(new Date()));
    }
    //依赖注入
    @Autowired
    @Qualifier("alphaHibernate")
    private AlphaDao alphaDao;//依赖接口，不和实现类耦合
    @Autowired
    private AlphaService alphaService;
    @Autowired
    private SimpleDateFormat simpleDateFormat;
    @Test
    public void testDI(){
        System.out.println(alphaDao);
        System.out.println(alphaService);
        System.out.println(simpleDateFormat);
    }
}
