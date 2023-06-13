package com.hzlx.dao.impl;


import com.hzlx.dao.UserInfoDao;
import com.hzlx.entity.UserInfo;
import com.hzlx.utils.BaseDao;
import org.apache.commons.lang.StringUtils;

import java.util.List;

public class UserInfoDaoImpl extends BaseDao<UserInfo> implements UserInfoDao {

    @Override
    public UserInfo getUserInfoByUserNameAndPassword(String userName, String password) {
        String sql="select * from t_user_info where user_name=? and `password`=?";
        return selectOne(sql, UserInfo.class,userName,password);
    }

    @Override
    public List<UserInfo> getUserInfoAll(String userName) {
        String sql="select * from t_user_info ";
        if (StringUtils.isNotEmpty(userName)){
            sql += " where `user_name` like concat('%',?,'%')";
            return selectListForObject(sql, Readable.class,userName);
        }
        return selectListForObject(sql, UserInfo.class);
    }

    @Override
    public List<UserInfo> getUserInfoAllByName(String name) {
        String sql="select * from t_user_info";
        if (StringUtils.isNotEmpty(name)){
            sql+=" where nick_name like concat('%',?,'%')";
            return selectListForObject(sql, UserInfo.class,name);
        }
        return selectListForObject(sql, UserInfo.class);
    }

    //查询
    @Override
    public UserInfo getUserInfoById(Integer id) {
        String sql = "select * from t_user_info where id =?";
        return selectOne(sql, UserInfo.class,id);
    }

    //添加
    @Override
    public int addUserInfo(UserInfo userInfo) {
        String sql = "insert into t_user_info values(default,?,?,?,?,?,?,null,now(),default);";
        return executeUpdate(sql,
                userInfo.getUserName(),
                userInfo.getPassword(),
                userInfo.getNickName(),
                userInfo.getTel(),
                userInfo.getAddress(),
                userInfo.getSex()
                );
    }

    //
    @Override
    public int batchUpdateUserStatus(String[] ids,Integer flag) {
            String sql = "update t_user_info set `status`=? where id in(";
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

    @Override
    public int updateUserInfoById(String id, String username, String nickname, String tel, String address,String sex) {
        String sql = "update t_user_info set user_name =?,nick_name=?,tel=?,address=? , sex=?where id =?";
        return executeUpdate(sql,username,nickname,tel,address, sex,id);
    }


}