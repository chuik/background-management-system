package com.hzlx.dao.impl;

import com.hzlx.dao.RoleInfoDao;
import com.hzlx.entity.RoleInfo;
import com.hzlx.utils.BaseDao;
import org.apache.commons.lang.StringUtils;

import java.util.List;

public class RoleInfoDaoImpl extends BaseDao<RoleInfo> implements RoleInfoDao {


    @Override
    public List<RoleInfo> getRoleInfoAllByName(String name) {
        String sql ="select * from t_role_info";
        if (StringUtils.isNotEmpty(name)){
            sql += " where name like concat('%',?,'%')";
            return selectListForObject(sql, RoleInfo.class,name);
        }
        return selectListForObject(sql, RoleInfo.class);
    }


    @Override
    public RoleInfo getRoleInfoById(Integer id) {
        String sql = "select * from t_role_info where id =?";
        return selectOne(sql,RoleInfo.class,id);
    }

    @Override
    public int addRoleInfo(String name) {
        String sql = "insert into t_role_info values(null,?,now(),default)";
        return executeUpdate(sql, name);
    }

    @Override
    public int updateRoleById(String name, String id) {
        String sql = "update t_role_info set name=? where id=?";
        return executeUpdate(sql, name, id);
    }

    @Override
    public int updateRoleStatus(Integer id) {
        String sql = "update t_role_info set `status`=IF(`Status`=1,0,1) where id=?";
        return executeUpdate(sql,id);
    }

    @Override
    public int batchUpdateRoleStatus(String[] ids) {
        String sql = "update t_role_info set `status`=1 where id in(";
        for (int i = 0; i < ids.length; i++) {
            if (i == ids.length - 1) {
                sql += " ?";
            } else {
                sql += " ?,";
            }
        }
        sql += ")";
        return executeUpdate(sql, ids);
    }
}
