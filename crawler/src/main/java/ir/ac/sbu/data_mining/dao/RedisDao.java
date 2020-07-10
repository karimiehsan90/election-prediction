package ir.ac.sbu.data_mining.dao;

import redis.clients.jedis.Jedis;

public class RedisDao {
    private Jedis jedis;

    public RedisDao(Jedis jedis) {
        this.jedis = jedis;
    }

    public boolean contains(String key) {
        return jedis.exists(key);
    }

    public void add(String key) {
        jedis.set(key, "");
    }
}
