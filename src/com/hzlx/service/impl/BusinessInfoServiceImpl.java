package com.hzlx.service.impl;

import com.hzlx.component.BgmsConfig;
import com.hzlx.dao.BusinessInfoDao;
import com.hzlx.dao.impl.BusinessInfoDaoImpl;
import com.hzlx.entity.BusinessInfo;
import com.hzlx.service.BusinessInfoService;
import com.hzlx.utils.BaseResult;
import com.hzlx.utils.MD5Utils;

import javax.servlet.http.HttpServletRequest;

public class BusinessInfoServiceImpl implements BusinessInfoService {

    private BusinessInfoDao businessInfoDao = new BusinessInfoDaoImpl();

    //商家端登录  账号密码判断用的
    @Override
    public String login(HttpServletRequest request) {
        return null;
    }

    //映射画面
    @Override
    public void getBusinessList(HttpServletRequest request) {
        String keyword = request.getParameter("keyword");
        request.setAttribute(BgmsConfig.KEYWORD,keyword);
        request.setAttribute(BgmsConfig.BUSINESS_LIST,businessInfoDao.getBusinessAllByName(keyword));

//        request.setAttribute(BgmsConfig.BUSINESS_LIST,businessInfoDao.getBusinessAll());
//        List<BusinessInfo> businesss = businessInfoDao.getBusinessInfoAll();
//        request.getSession().setAttribute(BgmsConfig.BUSINESS_LIST.businesss);
    }
    //数据库的数据映射到页面上
    public String getBusinessById(HttpServletRequest request){
        //获取前端传送过来的ID
        try {
            Integer id=Integer.parseInt(request.getParameter("id"));
            return BaseResult.success(businessInfoDao.getBusinessById(id));
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return BaseResult.error(4001,"参数异常");
        }
    }
    //批量删除
    @Override
    public String batchDeletBusiness(HttpServletRequest request) {
        String id = request.getParameter("id");
        String[] ids = id.substring(0, id.length() - 1).split(",");
        int rows = businessInfoDao.batchDeletBusinessInfoById(ids);
        if (rows>0){
            return BaseResult.success();
        }else{
            return BaseResult.error(4002,"修改菜单失败");
        }
    }

    //修改
    @Override
    public String editBusiness(HttpServletRequest request) {
        String id = request.getParameter("id");
        String username=request.getParameter("username");
        String tel = request.getParameter("tel");
        String address= request.getParameter("address");
        String avatar= request.getParameter("avatar");
        String status= request.getParameter("status");
        //TODO参数校验 如果非空字段为空 给出错误提示
        int rows = businessInfoDao.updateBusinessInfoById(id,username,tel,address,avatar,status);
        if (rows>0){
            return BaseResult.success();
        }else{
            return BaseResult.error(4002,"修改菜单失败");
        }
    }
    //新增
    @Override
    public String addBusiness(HttpServletRequest request) {
        BusinessInfo businessInfo = new BusinessInfo();
        businessInfo.setUsername(request.getParameter("username"));
        businessInfo.setPassword(MD5Utils.encryptMD5(request.getParameter("password"),request.getParameter("username")) );
        businessInfo.setTel(request.getParameter("tel"));
        businessInfo.setAddress(request.getParameter("address"));
        businessInfo.setAvatar(request.getParameter("avatar"));
        int rows = businessInfoDao.addBusinessInfo(businessInfo);
        if (rows>0){
            return BaseResult.success();
        }else {
            return BaseResult.error(4003,"新增失败");
        }
    }
    //删除
    @Override
    public String updateBusinessStatus(HttpServletRequest request, Integer flag) {
        try {
            String id= request.getParameter("id");
            String[] ids=new String[1];
            if (id.contains(",")){
                ids=id.split(",");
            }else {
                ids[0]=id;
            }
            int rows = businessInfoDao.batchUpdateBusinessStatus(ids,flag);
            if (rows>0){
                return BaseResult.success();
            }else{
                return BaseResult.error(3003,"删除失败");
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return BaseResult.error(30001,"参数异常");
        }
    }


}
