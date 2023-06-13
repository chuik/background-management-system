package com.hzlx.dao;

import com.hzlx.entity.UserInfo;

import java.util.List;


public interface UserInfoDao {

    /**
     * 根据用户名和密码  查询用户信息
     * @param userName 用户名
     * @param password 密码
     * @return 用户对象
     */
    UserInfo getUserInfoByUserNameAndPassword(String userName , String password);

    List <UserInfo> getUserInfoAllByName(String name);

    UserInfo getUserInfoById(Integer id);

    int addUserInfo(UserInfo userInfo);

    List<UserInfo> getUserInfoAll(String userName);

    int batchUpdateUserStatus(String[] ids,Integer flag);
    //修改
    int updateUserInfoById(String id, String username, String nickname, String tel, String address,String sex);

}
