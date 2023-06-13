package com.hzlx.controller;

import com.hzlx.annotation.Controller;
import com.hzlx.annotation.RequestMapping;
import com.hzlx.annotation.ResponseBody;
import com.hzlx.service.BusinessInfoService;
import com.hzlx.service.impl.BusinessInfoServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 根据函数走的路径
 */
@Controller
@RequestMapping("/api/business")
public class BusinessController {
    private BusinessInfoService businessInfoService = new BusinessInfoServiceImpl();

    //商家登录用的
    @RequestMapping("/login")
    @ResponseBody
    public String login(HttpServletRequest request, HttpServletResponse response){
        return businessInfoService.login(request);
    }

    //渲染页面
    @RequestMapping("/businessList")
    public String businessList(HttpServletRequest request, HttpServletResponse response){
       businessInfoService.getBusinessList(request);
       return "pages/business_list";
    }
    //修改
    @RequestMapping("/editBusiness")
    @ResponseBody
    public String editBusiness(HttpServletRequest request,HttpServletResponse response){
        return businessInfoService.editBusiness(request);
    }
    //新增
    @RequestMapping("/addBusiness")
    @ResponseBody
    public String addBusiness(HttpServletRequest request,HttpServletResponse response){
        return businessInfoService.addBusiness(request);
    }

    //删除
    @RequestMapping("/updateBusinessStatus")
    @ResponseBody
    public String updateBusinessStatus(HttpServletRequest request, HttpServletResponse response){
        return businessInfoService.updateBusinessStatus(request,0);
    }

    //启用
    @RequestMapping("/enableBusiness")
    @ResponseBody
    public String enableBusiness(HttpServletRequest request, HttpServletResponse response){
        return businessInfoService.updateBusinessStatus(request,1);
    }
    //查询
    @RequestMapping("/getBusiness")
    @ResponseBody
    public String getBusinessById(HttpServletRequest request, HttpServletResponse response){
        return businessInfoService.getBusinessById(request);
    }

    //批量删除
    @RequestMapping("/batchDeletBusiness")
    @ResponseBody
    public String batchDeletBusiness(HttpServletRequest request,HttpServletResponse response){
        return businessInfoService.batchDeletBusiness(request);
    }

}
