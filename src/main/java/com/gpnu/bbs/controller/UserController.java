package com.gpnu.bbs.controller;

import com.gpnu.bbs.model.Comment;
import com.gpnu.bbs.model.HostHolder;
import com.gpnu.bbs.model.User;
import com.gpnu.bbs.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * @Author: PurcellHuang
 * @Date: 2019-07-24 11:19
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private HostHolder hostHolder;

    /**
     * @Author: PurcellHuang
     * @Date: 2019-07-24 16:39
     * 修改用户信息
     */
    @PostMapping("/update")     //  URI=  ***********/user/update?id=3
    public Object updateUser(User user){

        user = userService.updateUserInfo(user);
        return user;
    }

    @GetMapping("/get")   //  URI=  ***********/user/get?id=3
    public Object getUser(@RequestParam(name = "id") String id){
        return hostHolder.getUser();
    }

    @PostMapping("/comment")
    public Object doComment(Comment comment){
        return userService.doComment(comment);
    }

}
