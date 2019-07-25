package com.gpnu.bbs.controller;

import com.gpnu.bbs.model.HostHolder;
import com.gpnu.bbs.model.User;
import com.gpnu.bbs.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: PurcellHuang
 * @Date: 2019-07-24 0:23
 */
@RestController
public class LoginController {

    @Autowired
    private HostHolder hostHolder;

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public Map login(String email, String password, HttpServletResponse response){
        Map<String,Object> result = new HashMap<>();
        User user = userService.login(email,password);
        if(user != null){
            hostHolder.setUser(user);
            result.put("success",true);
            result.put("user",user);
            String ticket = userService.createTicket(User.getTicketKey(user.getId()));
            Cookie cookie = new Cookie("ticket",ticket);
            cookie.setMaxAge(userService.getTicketExpried(User.getTicketKey(user.getId())));
            //在Postman中调试
            result.put("cookie",cookie);
            cookie.setPath("/");
            response.addCookie(cookie);
            System.out.println(cookie);
        }else{
            result.put("success",false);
            result.put("msg","email或者password错误！");
        }
        return result;
    }

    @PostMapping("/regist")
    public Map regist(User user){
        Map<String,Object> result = new HashMap<>();
        User u = userService.regist(user);
        if(u != null){
            result.put("success",true);
            result.put("user",u);
        }else{
            result.put("success",false);
            result.put("msg","email已存在！");
        }
        return result;
    }


}
