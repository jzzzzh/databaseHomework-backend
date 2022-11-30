package org.example;

import static org.junit.Assert.assertTrue;

import jdk.nashorn.internal.parser.Token;
import lombok.val;
import org.example.Tools.token;
import org.example.component.RedisUtils;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;

import javax.annotation.Resource;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    /**
     * Rigorous Test :-)
     */
    @Resource
    private RedisTemplate<String, String> redisTemplate;

    @Resource
    private StringRedisTemplate stringRedisTemplate;
    @Test
    public void shouldAnswerWithTrue()
    {
        System.out.println(RedisUtils.get("eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJwYXNzd29yZCI6IjEyMzQiLCJpZCI6IjEiLCJleHAiOjE2Njg0MzM0MzMsInVzZXIiOiJzdHVkZW50In0.lCjl9otRNFZO-62Aadk211sXt6-H0RQSbtBmzO8xCpw"));
    }
}
