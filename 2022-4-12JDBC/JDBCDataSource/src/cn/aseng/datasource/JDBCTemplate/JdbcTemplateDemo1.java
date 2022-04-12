package cn.aseng.datasource.JDBCTemplate;

import org.springframework.jdbc.core.JdbcTemplate;
import utils.JDBCUtils;

public class JdbcTemplateDemo1 {

    public static void main(String[] args) {
        //导入jar包
        //创建JDBCTemplate对象
        JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());

        String sql="update account set balance= 5000 where id=?";
        int count =template.update(sql,3);
        System.out.println(count);
    }
}
