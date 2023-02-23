package com.newcoder.community.dao;

import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.ResponseBody;

@Repository("alphaHibernate")
public class AlphaHibernateImpl implements AlphaDao{//配置Repository后可以自动扫描并装配
    @Override
    public String select() {
        return "Hibernate";
    }
}
