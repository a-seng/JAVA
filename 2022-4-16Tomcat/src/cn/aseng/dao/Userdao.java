package cn.aseng.dao;

import cn.aseng.domain.User;
import cn.aseng.util.JDBCUtils;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

public class Userdao {

    private JdbcTemplate template=new JdbcTemplate(JDBCUtils.getDataSource());

    public User login(User loginUser){

        try{
            String sql="select * from user where username = ? and password = ?";

            User user=template.queryForObject(sql,
                    new BeanPropertyRowMapper<>(User.class),
                    loginUser.getUsername(),
                    loginUser.getPassword());

            return user;
        }catch (DataAccessException e){
            e.printStackTrace();
            return null;
        }
    }
}
