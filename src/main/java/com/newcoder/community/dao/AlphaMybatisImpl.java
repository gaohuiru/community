package com.newcoder.community.dao;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

@Repository
@Primary //优先级注解
public class AlphaMybatisImpl implements AlphaDao{
    @Override
    public String select() {
        return "mybatis";
    }
}
