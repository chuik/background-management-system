package com.hzlx.dao;

import com.hzlx.entity.BusinessInfo;

import java.util.List;
import java.util.Map;

public interface BusinessInfoDao {
    BusinessInfo getBusinessById(Integer id);
    //渲染页面
    List<Map<String,Object>> getBusinessAll();
    //修改
    int updateBusinessInfoById(String id, String username, String tel, String address, String avatar, String status);

    //新增
    int addBusinessInfo(BusinessInfo businessInfo);
    //删除
    int batchUpdateBusinessStatus(String[] ids, Integer flag);

    //模糊查询
    List<BusinessInfo> getBusinessAllByName(String name);

    //批量删除
    int batchDeletBusinessInfoById(String[] ids);
}
