package com.admin.service;

import com.admin.mapper.UserMapper;
import com.admin.pojo.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    UserMapper userMapper;

    public UserInfo findByUsername(String username){
        return userMapper.findByUsername(username);
    }

    public List<UserInfo> findAll(){
        return userMapper.findAll();
    }
}
