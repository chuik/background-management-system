package com.hzlx.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface UserInfoService {
    /**
     * 用户登录的请求
     * @param request http 请求，用于获取用户提交的数据
     * @return json 字符串
     */
    String login(HttpServletRequest request);

    //查询框
    void getUserList(HttpServletRequest request);

    //根据角色ID查询角色对象
    String getUserById(HttpServletRequest request);

    //新增和修改
    String editUser(HttpServletRequest request);

    //删除
    String updateUserStatus(HttpServletRequest request,Integer flag);
    //启用
    String enableUsers(HttpServletRequest request);

    //新增
    String addUser(HttpServletRequest request);
}
