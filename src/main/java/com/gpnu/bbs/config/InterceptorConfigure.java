package com.gpnu.bbs.config;

import com.gpnu.bbs.interceptor.PassportInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @Author: PurcellHuang
 * @Date: 2019-07-24 11:10
 */
@Configuration
public class InterceptorConfigure extends WebMvcConfigurerAdapter {

    @Autowired
    private PassportInterceptor passportInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // addPathPatterns 用于添加拦截规则
        // excludePathPatterns 用户排除拦截
        // 映射为 user 的控制器下的所有映射
        registry.addInterceptor(passportInterceptor).addPathPatterns("/user/*").excludePathPatterns("/index", "/");
        super.addInterceptors(registry);
    }

}
