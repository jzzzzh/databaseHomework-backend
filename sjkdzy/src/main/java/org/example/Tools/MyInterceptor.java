package org.example.Tools;

import lombok.val;
import org.example.component.RedisUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

public class MyInterceptor implements HandlerInterceptor {
    @Resource
    private RedisTemplate redisTemplate;

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        val mytoken = request.getHeader("token");
        //System.out.println(RedisUtils.get(mytoken));
        if(mytoken != null && RedisUtils.get(mytoken) != null)
        {
            val id = token.verify(mytoken).getClaim("id").asString();
            RedisUtils.resetTime(mytoken, 18000);
            //System.out.println(id);
            if(RedisUtils.get(mytoken).equals(id))
            {
//                System.out.println("yes");
                response.setStatus(200);
                return true;
            }
            response.setStatus(401);
            return false;
        }
        else
        {
            response.setStatus(401);
            return false;
        }
    }
}
