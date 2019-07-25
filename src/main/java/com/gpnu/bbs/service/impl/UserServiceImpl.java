package com.gpnu.bbs.service.impl;

import com.gpnu.bbs.mapper.CommentMapper;
import com.gpnu.bbs.mapper.UserMapper;
import com.gpnu.bbs.model.Comment;
import com.gpnu.bbs.model.HostHolder;
import com.gpnu.bbs.model.User;
import com.gpnu.bbs.service.UserService;
import com.gpnu.bbs.util.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.Date;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @Author: PurcellHuang
 * @Date: 2019-07-24 0:24
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private RedisTemplate redisTemplate;

    @Resource(name = "redisTemplate")
    private ValueOperations string;

    @Resource(name = "redisTemplate")
    private HashOperations<String,String,User> hash;

    @Autowired
    private HostHolder hostHolder;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private CommentMapper commentMapper;

    @PostConstruct
    private void init(){
        redisTemplate.setKeySerializer(StringRedisSerializer.UTF_8);
    }

    @Override
    public String createTicket(String key){
        User user = hostHolder.getUser();
        String ticket = UUID.randomUUID().toString().replace("-","");
        string.set(key,ticket);
        hash.put("user",ticket,user);
        redisTemplate.expire(key,3, TimeUnit.DAYS);
        return ticket;
    }



    @Override
    public User login(String email, String password) {
        String pwd = MD5Util.md5(password+MD5Util.salt);
        //System.out.println(pwd);
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

    @Override
    public User updateUserInfo(User user) {
        User u = hostHolder.getUser();
        user.setId(u.getId());
        user.setPassword(MD5Util.md5(user.getPassword()+MD5Util.salt));
        user.setEmail(u.getEmail());
        user.setReg_date(u.getReg_date());
        userMapper.updateByPrimaryKey(user);
        return user;
    }

    @Override
    public Comment doComment(Comment comment) {
        comment.setPublish_date(new Date());
        comment.setU_id(hostHolder.getUser().getId());
        commentMapper.insert(comment);
        return comment;
    }

    @Override
    public int getTicketExpried(String key) {
        int time = redisTemplate.getExpire(key).intValue();
        //redisTemplate.expire(key,3, TimeUnit.DAYS);
        return time;
    }

}
