package com.gpnu.bbs.model;

import org.springframework.stereotype.Component;

@Component
public class HostHolder {
    private ThreadLocal<User> users = new ThreadLocal<User>();
    public User getUser(){
        return users.get();
    }
    public void setUser(User u){
        users.set(u);
    }

    public void clear(){
        users.remove();
    }

}
