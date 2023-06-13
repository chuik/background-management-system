package com.hzlx.service;


import javax.servlet.http.HttpServletRequest;

public interface MenuInfoService {
    /**
     * 根据用户id  获取用户角色对应的菜单数
     * @param request 请求对象  用户获取请求中的参数
     * @return
     */
    String showMenu(HttpServletRequest request );

    /**
     * 查询全部菜单
     * @param request
     */
    void getMenuList(HttpServletRequest request);

    String getMenu(HttpServletRequest request);

    String editMenu(HttpServletRequest request);

    String deleteMenu(HttpServletRequest request);

    String addMenu(HttpServletRequest request);

    String enableMenu(HttpServletRequest request);
}
