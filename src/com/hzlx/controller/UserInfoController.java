package com.hzlx.controller;

import com.hzlx.annotation.Controller;
import com.hzlx.annotation.RequestMapping;
import com.hzlx.annotation.RequestParameter;
import com.hzlx.annotation.ResponseBody;
import com.hzlx.service.UserInfoService;
import com.hzlx.service.impl.UserInfoServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/api/user")
public class UserInfoController {

    private UserInfoService userInfoService = new UserInfoServiceImpl();

    //客户登录  账号密码判断
    @RequestMapping("/login")
    @ResponseBody
    public String login(HttpServletRequest request, HttpServletResponse response) {
        return userInfoService.login(request);
    }

    //渲染页面
    @RequestMapping("/userList")
    public String userList(HttpServletRequest request, HttpServletResponse response) {
        userInfoService.getUserList(request);
        return "pages/user_list";
    }

    //查询
    @RequestMapping("/getUser")
    @ResponseBody
    public String getUserById(HttpServletRequest request, HttpServletResponse response){
        return userInfoService.getUserById(request);
    }
    //修改
    @RequestMapping("/editUser")
    @ResponseBody
    public String editUser(HttpServletRequest request, HttpServletResponse response){
        return userInfoService.editUser(request);
    }

    //添加
    @RequestMapping("/addUser")
    @ResponseBody
    public String addUser(HttpServletRequest request, HttpServletResponse response){
        return userInfoService.addUser(request);
    }



    //删除
    @RequestMapping("/updateUserStatus")
    @ResponseBody
    public String updateUserStatus(HttpServletRequest request, HttpServletResponse response){
        return userInfoService.updateUserStatus(request,0);
    }
    //启用
    @RequestMapping("/enableUsers")
    @ResponseBody
    public String enableUsers(HttpServletRequest request, HttpServletResponse response){
//        return userInfoService.enableUsers(request);
        return userInfoService.updateUserStatus(request,1);
    }

}
