package com.hzlx.service;

import javax.servlet.http.HttpServletRequest;

public interface BusinessInfoService {
    // 商家端登录用的
    String login(HttpServletRequest request);
    //映射画面
    void getBusinessList(HttpServletRequest request);
    //修改
    String editBusiness(HttpServletRequest request);
    //新增
    String addBusiness(HttpServletRequest request);
    //启用
    String updateBusinessStatus(HttpServletRequest request, Integer flag);
    //查询
    String getBusinessById(HttpServletRequest request);

    //批量删除
    String batchDeletBusiness(HttpServletRequest request);
}
