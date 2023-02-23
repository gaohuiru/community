package com.newcoder.community.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.text.SimpleDateFormat;

@Configuration
public class AlphaConfig {
    //模拟第三方Bean
    @Bean//这个方法返回的对象将会被装配在容器里
    public SimpleDateFormat simpleDateFormat(){
        return new SimpleDateFormat("yyyy-MM-dd HH:mm");
    }
}
