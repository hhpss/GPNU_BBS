package com.gpnu.bbs.mapper;

import com.gpnu.bbs.model.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    User selectByPrimaryKey(Integer id);

    User selectByEmail(String email);

    User selectByEmailAndPassword (@Param("email") String email, @Param("password") String password);

    List<User> selectAll();

    int updateByPrimaryKey(User record);
}