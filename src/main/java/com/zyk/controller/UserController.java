package com.zyk.controller;

import com.sun.xml.internal.messaging.saaj.packaging.mime.MessagingException;
import com.zyk.service.MailService;
import com.zyk.service.UserService;
import org.apache.ibatis.annotations.Insert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private MailService mailService;
    @RequestMapping(value = "/adduser",method = RequestMethod.POST)
    public @ResponseBody String addUser(HttpServletRequest request){
        String username=request.getParameter("username");
        String password=request.getParameter("password");
        String email=request.getParameter("email");
        String addr=request.getParameter("addr");
        String name=request.getParameter("name");
        try {
            userService.addUser(username,password,name,email,addr);
            new Thread(new Runnable() {
                @Override
                public void run() {
                    mailService.sendMail(email,"Registration success notification", "Welcome to join us");
                }
            }).start();
            return "success";
        }
        catch (Exception e){
            return "fail";
        }
    }
    @RequestMapping(value = "/logincheck",method = RequestMethod.POST)
    public @ResponseBody boolean loginCheck(HttpServletRequest request){
        String username=request.getParameter("username");
        String password=request.getParameter("password");
        return userService.loginCheck(username,password);
    }
}
