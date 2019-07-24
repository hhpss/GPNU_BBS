package com.gpnu.bbs.service;

import com.gpnu.bbs.model.User;

/**
 * @Author: PurcellHuang
 * @Date: 2019-07-24 0:23
 */
public interface UserService {
    User login(String email, String password);
    User regist(User user);
}
