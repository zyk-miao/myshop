package com.zyk.service;

import com.zyk.mapper.UserMapper;
import com.zyk.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    UserMapper userMapper;
    public void addUser(String username,String password, String name,String email,String addr){
        userMapper.addUser(username,password,name,email,addr);
    }
    public boolean loginCheck(String username,String password){
        List<User> list=userMapper.loginCheck(username,password);
        if(list==null||list.size()<=0) return false;
        else return true;

    }
}
