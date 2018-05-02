package com.cmb.startup.dao;

import com.cmb.startup.SoundContent;
import com.mysql.jdbc.Statement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.util.Date;
import java.util.List;


@Repository
public class SoundContentDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;


    // 新增一条记录，返回自增主键值
    public Long add(SoundContent content){
        Date datetime = new Date();
        String s_datetime = new java.sql.Date(datetime.getTime()) + " " + new java.sql.Time(datetime.getTime());
        String sql = "INSERT INTO `sound_db`.`sound_content` (`b_user_id`, `b_user_name`, `content_type`, `content`, `create_time`, `updatetime`, `status`) " +
                "VALUES ("+
                "'"+content.getbUserId() + "'," +
                "'"+content.getbUserName() + "'," +
                "'"+content.getType() + "'," +
                "'"+content.getContent() + "'," +
                "'"+s_datetime + "'," +
                "'"+s_datetime + "','A');";


        KeyHolder keyHolder = new GeneratedKeyHolder();
        PreparedStatementCreator preparedStatementCreator = con -> {
            PreparedStatement ps = con.prepareStatement
                    (sql, Statement.RETURN_GENERATED_KEYS);
            return ps;
        };
        jdbcTemplate.update(preparedStatementCreator, keyHolder);

        return keyHolder.getKey().longValue();
//         jdbcTemplate.update
//                ("INSERT INTO `sound_db`.`sound_content` (`b_user_id`, `b_user_name`, `content_type`, `content`, `create_time`, `updatetime`, `status`) " +
//                                "VALUES (?, ?, ?, ?, ?, ?, ?);",
//                content.getbUserId(),
//                content.getbUserName(),
//                content.getType(),
//                content.getContent(),
//                datetime.toString(),
//                datetime.toString(),
//                'A');
    }
    public int updateEventId(Long keyid,String eventId){
        return jdbcTemplate.update
                ("UPDATE `sound_db`.`sound_content` SET `eventId`=? WHERE `id`=?", eventId, keyid);

    }
    public SoundContent findByEventId(String eventId){
        List<SoundContent> list = jdbcTemplate.query(
                "select * from sound_content where eventId = ?", new Object[]{eventId},
                new BeanPropertyRowMapper(SoundContent.class));
        if(null != list && list.size()>0){
            SoundContent soundContent = list.get(0);
            return soundContent;
        }else{
            return null;
        }
    }


}
