package cn.itcast.jedis.util;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.io.InputStream;
import java.util.Properties;

public class JedisPoolUtils {

    private static JedisPool jedisPool;

    static {
        //读取配置文件
        InputStream is = JedisPoolUtils.class.
                getClassLoader().getResourceAsStream("jedis.properties");
        //创建Properties对象
        Properties pro = new Properties();

        //关联文件
        try {
            pro.load(is);
        } catch (Exception e) {
            e.printStackTrace();
        }

        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxTotal(Integer.parseInt(pro.getProperty("maxTotal")));
        config.setMaxIdle(Integer.parseInt(pro.getProperty("maxIdle")));
        //初始化JedisPool
        jedisPool = new JedisPool(config, pro.getProperty("host"),
                Integer.parseInt(pro.getProperty("port")));

        }
        public static Jedis getJedis(){
            return jedisPool.getResource();
        }


}
