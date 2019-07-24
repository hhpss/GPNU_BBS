package com.gpnu.bbs.service.impl;

import com.gpnu.bbs.mapper.UserMapper;
import com.gpnu.bbs.model.User;
import com.gpnu.bbs.service.UserService;
import com.gpnu.bbs.util.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @Author: PurcellHuang
 * @Date: 2019-07-24 0:24
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User login(String email, String password) {
        String pwd = MD5Util.md5(password+MD5Util.salt);
        System.out.println(pwd);
        User u = userMapper.selectByEmailAndPassword(email,pwd);
        return u;
    }

    @Override
    public User regist(User user) {
        User u = userMapper.selectByEmail(user.getEmail());

        if (u != null){
            return null;
        }
        user.setPassword(MD5Util.md5(user.getPassword()+MD5Util.salt));
        user.setReg_date(new Date());
        userMapper.insert(user);
        System.out.println(user);
        return user;
    }

}
