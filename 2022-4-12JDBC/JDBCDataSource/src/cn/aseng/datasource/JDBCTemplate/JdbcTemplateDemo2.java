package cn.aseng.datasource.JDBCTemplate;

import cn.aseng.jdbc.Emp;
import com.sun.org.apache.xalan.internal.xsltc.compiler.Template;
import org.junit.Test;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import utils.JDBCUtils;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class JdbcTemplateDemo2 {
    /*
    查询所有记录，将其封装为List
     */
    private JdbcTemplate template=  new JdbcTemplate((JDBCUtils.getDataSource()));

    @Test
    public void test4(){
        String sql="insert into emp(id,ename,dept_id) values(?,?,?)";
        int count =template.update(sql, 1015, "郭靖", 10);
        System.out.println(count);
    }

    @Test
    public void test5(){
        String sql="select * from emp";
        List<Map<String,Object>> list= template.queryForList(sql);

        for(Map<String,Object>stringObjectMap:list){
            System.out.println(stringObjectMap);
        }

    }

    @Test
    public void test6(){
        String sql ="select * from emp";
        List<Emp>list =template.query(sql, new RowMapper<Emp>() {
            @Override
            public Emp mapRow(ResultSet resultSet, int i) throws SQLException {
                Emp emp=new Emp();
                return emp;
            }
        });
    }

    @Test
    public void test7(){
        String sql="select *from emp";
        List<Emp> list=template.query(sql,new BeanPropertyRowMapper<Emp>(Emp.class));
        for(Emp emp:list){
            System.out.println(emp);
        }
    }

    @Test
    public void test8(){
        String sql ="select count(id) from emp";
        Long total = template.queryForObject(sql,Long.class);
        System.out.println(total);
    }
}
