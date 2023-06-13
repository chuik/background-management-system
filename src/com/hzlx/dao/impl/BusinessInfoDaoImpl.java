package com.hzlx.dao.impl;

import com.hzlx.dao.BusinessInfoDao;
import com.hzlx.entity.BusinessInfo;
import com.hzlx.entity.UserInfo;
import com.hzlx.utils.BaseDao;
import org.apache.commons.lang.StringUtils;

import java.util.List;
import java.util.Map;

public class BusinessInfoDaoImpl extends BaseDao<BusinessInfo> implements BusinessInfoDao {

    //查询
    @Override
    public BusinessInfo getBusinessById(Integer id) {
        String sql ="select * from t_business_info where id=?";
        return selectOne(sql, BusinessInfo.class,id);
    }

    //查询全部
    @Override
    public List<Map<String, Object>> getBusinessAll() {
        String sql ="select * from t_business_info";
        return selectListForMap(sql);
    }

    //修改
    @Override
    public int updateBusinessInfoById(String id, String username, String tel, String address, String avatar, String status) {
        String sql ="update t_business_info set user_name=?,tel=?,address=?,avatar=?,status=? where id=?";
        return executeUpdate(sql,username,tel,address,avatar,status,id);

    }
    //新增
    @Override
    public int addBusinessInfo(BusinessInfo businessInfo) {
        String sql = "insert into t_business_info values(default,?,?,?,?,?,now(),default)";
        return executeUpdate(sql,
                businessInfo.getUsername(),
                businessInfo.getPassword(),
                businessInfo.getTel(),
                businessInfo.getAddress(),
                businessInfo.getAvatar()
        );
    }
    //删除
    @Override
    public int batchUpdateBusinessStatus(String[] ids, Integer flag) {
        String sql = "update t_business_info set `status`=? where id in(";
        for (int i = 0; i < ids.length; i++) {
            if (i == ids.length - 1) {
                sql += " ?";
            } else {
                sql += " ?,";
            }
        }
        sql += ")";
        return executeUpdate(sql, flag,ids);
    }

    //模糊查询
    @Override
    public List<BusinessInfo> getBusinessAllByName(String name) {
        String sql="select * from t_business_info ";
        if (StringUtils.isNotEmpty(name)){
            sql+=" where user_name like concat('%',?,'%')";
            return selectListForObject(sql, BusinessInfo.class,name);
        }
        return selectListForObject(sql, BusinessInfo.class);
    }

    @Override
    public int batchDeletBusinessInfoById(String[] ids) {
        String sql="update t_business_info set status=0 where id in(";
        for (int i = 0; i < ids.length; i++) {
            if (i == ids.length - 1) {
                sql += " ?";
            } else {
                sql += " ?,";
            }
        }
        sql += ")";
        return executeUpdate(sql,ids);

    }
}
