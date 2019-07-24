package com.gpnu.bbs.controller;

import com.gpnu.bbs.model.HostHolder;
import com.gpnu.bbs.model.User;
import com.gpnu.bbs.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Author: PurcellHuang
 * @Date: 2019-07-24 11:19
 */
@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private HostHolder hostHolder;

    @GetMapping("/get")
    public User getUser(){
        return hostHolder.getUser();
    }


}
