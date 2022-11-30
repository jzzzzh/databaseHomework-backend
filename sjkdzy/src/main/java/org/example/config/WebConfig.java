package org.example.config;

import org.example.Tools.MyInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 对所有访问路径，都通过MyInterceptor类型的拦截器进行拦截
        registry.addInterceptor(new MyInterceptor()).addPathPatterns("/*/**")
                .excludePathPatterns("/teacher/register", "/teacher/checkPassword","/student/register", "/student/checkPassword");
        //放行登录页，登陆操作，静态资源
    }
}
