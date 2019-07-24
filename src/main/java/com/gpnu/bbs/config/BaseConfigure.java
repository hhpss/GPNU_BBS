package com.gpnu.bbs.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @Author: PurcellHuang
 * @Date: 2019-07-24 11:14
 */
@Configuration
public class BaseConfigure extends WebMvcConfigurerAdapter {
    /**
     * 过去要访问一个页面需要先创建个Controller控制类，再写方法跳转到页面
     * 在这里配置后就不需要那么麻烦了，直接访问http://localhost:8080/toLogin就跳转到login.jsp页面了
     * @param registry
     */
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/index").setViewName("index");
        super.addViewControllers(registry);
    }
}
