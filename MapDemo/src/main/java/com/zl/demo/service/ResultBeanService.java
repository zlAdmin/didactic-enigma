package com.zl.demo.service;

import com.zl.demo.model.BasePosition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Service
public class ResultBeanService {
    @Autowired
    JdbcTemplate jdbcTemplate;

    public List<BasePosition> query(){
        String sql = "SELECT  longitude,latitude,count(1) as co from  position  where time>(DATE_SUB(CURRENT_TIMESTAMP(),INTERVAL 50 minute)) GROUP BY longitude,latitude";

        //select UNIX_TIMESTAMP(DATE_SUB(CURRENT_TIMESTAMP(),INTERVAL 10 minute)) from  dual;

        //SELECT  longitude,latitude,count(1) from  position where time>(DATE_SUB(CURRENT_TIMESTAMP(),INTERVAL 10 minute)) GROUP BY longitude,latitude;

        return(List<BasePosition>) jdbcTemplate.query(sql, new RowMapper<BasePosition>() {
            @Override
            public BasePosition mapRow(ResultSet resultSet, int arg1) throws SQLException {
                BasePosition bean = new BasePosition();
                bean.setLng(resultSet.getDouble("longitude"));
                bean.setLat(resultSet.getDouble("latitude"));
                bean.setCount(resultSet.getLong("co"));
                return bean;
            }
        });
    };
}
