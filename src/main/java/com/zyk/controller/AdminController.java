package com.zyk.controller;

import com.zyk.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.lang.model.element.VariableElement;
import javax.servlet.http.HttpServletRequest;

@RestController
public class AdminController {
    @Autowired
    AdminService adminService;

    @RequestMapping(value = "/admincheck", method = RequestMethod.POST)
    public @ResponseBody
    boolean adminCheck(HttpServletRequest request) {
        return adminService.adminCheck(request.getParameter("adminname"),request.getParameter("adminpassword"));
    }
    @RequestMapping(value = "/updatepassword",method = RequestMethod.POST)
    public @ResponseBody
    String updatePassword(HttpServletRequest request){
        return adminService.updatePassword(request.getParameter("adminname"),request.getParameter("password"));
    }
}
