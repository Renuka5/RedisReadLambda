package com.sample.RedisReadLambda;

import java.util.Map;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.exceptions.JedisException;
 
import java.util.HashMap;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

/**
 * Try and connect to Redis
 *
 */

public class RedisLambdaReadHandler implements RequestHandler<Map<String,String>, Object> {

	public Object handleRequest(Map<String, String> input, Context context) {

	    //address of redis server
		final String redisHost = "localhost"; 
				
		final Integer redisPort = 6379;
	 
	    //the jedis connection pool..
	   //configure our pool connection
	    JedisPool pool = new JedisPool(redisHost, redisPort);  
	 
		return readHash(input,pool);
	}
	
 
    public Map<String, String> readHash(Map<String, String> input,JedisPool pool) {        
 
    	Map<String, String> retrieveMap = null;
        Jedis jedis = pool.getResource();
        
        try {
        	String key =input.get("key");
        	
        	//retrieving data added in redis
            retrieveMap = jedis.hgetAll(key);            
            
            	for (String keyMap : retrieveMap.keySet()) {
                    System.out.println(keyMap + " " + retrieveMap.get(keyMap));
                    
                }
            	
            
        } catch (JedisException e) {
            //return Jedis instance to the pool
            if (null != jedis) {
                pool.returnBrokenResource(jedis);
                jedis = null;
            }
        } finally {
            ///return Jedis instance to the pool
            if (null != jedis)
                pool.returnResource(jedis);
        }
        return retrieveMap;
    }
}

