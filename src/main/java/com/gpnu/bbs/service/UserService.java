package com.gpnu.bbs.service;

import com.gpnu.bbs.model.Comment;
import com.gpnu.bbs.model.User;

/**
 * @Author: PurcellHuang
 * @Date: 2019-07-24 0:23
 */
public interface UserService {
    String createTicket(String key);
    User login(String email, String password);
    User regist(User user);
    User updateUserInfo(User user);
    Comment doComment(Comment comment);
    int getTicketExpried(String key);
}
