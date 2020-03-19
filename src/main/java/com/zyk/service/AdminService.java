package com.zyk.service;

import com.zyk.entity.Admin;
import com.zyk.mapper.AdminMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.sound.midi.Soundbank;
import java.util.List;

@Service
public class AdminService {
    @Autowired
    AdminMapper adminMapper;
    public boolean adminCheck(String adminname, String adminpassword){
        List<Admin> list=adminMapper.adminLogin(adminname,adminpassword);
        if(list==null||list.size()<=0) return false;
        else return true;
    }
    public String updatePassword(String adminname,String password){
        try {
            adminMapper.updatePassword(adminname,password);
            return "success";
        }
        catch (Exception e){
            return "fail";
        }
    }
}
