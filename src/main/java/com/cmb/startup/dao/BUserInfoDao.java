package com.cmb.startup.dao;


import com.cmb.startup.BUserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BUserInfoDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public BUserInfo findBUserById(long id){
        List<BUserInfo> list = jdbcTemplate.query("select * from b_user_info where id = ?", new Object[]{id},
                new BeanPropertyRowMapper(BUserInfo.class));
        if(null != list && list.size()>0){
            BUserInfo bUserInfo = list.get(0);
            return bUserInfo;
        }else{
            return null;
        }
    }
}
