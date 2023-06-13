package com.hzlx.service.impl;

import com.hzlx.component.BgmsConfig;
import com.hzlx.dao.UserInfoDao;
import com.hzlx.dao.impl.UserInfoDaoImpl;
import com.hzlx.entity.UserInfo;
import com.hzlx.service.UserInfoService;
import com.hzlx.utils.BaseResult;
import com.hzlx.utils.MD5Utils;
import com.mysql.cj.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class UserInfoServiceImpl implements UserInfoService {

    //引入dao层，用户查询数据库
    private UserInfoDao userInfoDao= new UserInfoDaoImpl();

    @Override
    public String login(HttpServletRequest request) {
        //从请求中获取用户名和密码
        String userName = request.getParameter("userName");
        String password = request.getParameter("password");
        //用户名和密码做非空校验
        if (StringUtils.isNullOrEmpty(userName)|| StringUtils.isNullOrEmpty(password)){
            return BaseResult.error(10001,"用户名或者密码不能为空");
        }
        //给密码加密
        String encryptPwd =  MD5Utils.encryptMD5(password,userName);
        //拿加密后的密码和用户名去数据库里查询用户信息;
        UserInfo userInfo = userInfoDao.getUserInfoByUserNameAndPassword(userName, encryptPwd);
        //如果查询到的 userInfo 为空，则说明用户名和密码不存在，判定为账号或密码错误;
        if (userInfo==null) {
            return BaseResult.error(10002, "账号或密码错误");
        }
        //用户名登录成功之后，把用户名信息存储下来放到 session作用域中，用户名后续使用
        request.getSession().setAttribute(BgmsConfig.SESSION_USER_KEY,userInfo);
        return BaseResult.success();
    }

    @Override
    public void getUserList(HttpServletRequest request) {
        String keyword = request.getParameter("keyword");
        request.setAttribute(BgmsConfig.KEYWORD,keyword);
        request.setAttribute(BgmsConfig.USER_LIST,userInfoDao.getUserInfoAllByName(keyword));

        List<UserInfo> userInfos= userInfoDao.getUserInfoAll(keyword);
        request.getSession().setAttribute(BgmsConfig.USER_LIST,userInfos);

    }
    //获取数据库的数据   在页面展示出来
    @Override
    public String getUserById(HttpServletRequest request) {
        //获取前端传送过来的 ID
        try {
            Integer id = Integer.parseInt(request.getParameter("id"));
            return BaseResult.success(userInfoDao.getUserInfoById(id));
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return BaseResult.error(3001,"参数异常");
        }
    }

    //修改
    public String editUser(HttpServletRequest request){
        String id = request.getParameter("id");
        String username = request.getParameter("username");
        String nickname = request.getParameter("nickname");
        String tel = request.getParameter("tel");
        String address= request.getParameter("address");
        String sex = request.getParameter("sex");
        //TODO 参数校验 如果非空字段为空 给出错误提示
        int rows = userInfoDao.updateUserInfoById(id,username,nickname,tel,address,sex);
        if (rows>0){
            return BaseResult.success();
        }else {
            return BaseResult.error(2002,"修改菜单失败");
        }
    }

    //启用
    @Override
    public String enableUsers(HttpServletRequest request) {
        String idsStr = request.getParameter("ids");
        String[] ids  = idsStr.substring(0,idsStr.length() - 1).split(",");
        int rows = userInfoDao.batchUpdateUserStatus(ids,1);
        if (rows>0){
            return BaseResult.success();
        }else {
            return BaseResult.error(3003,"启用失败");
        }
    }

    //新增
    @Override
    public String addUser(HttpServletRequest request) {
        UserInfo userInfo=new UserInfo();
        //TODO  全部去取字段
        userInfo.setUserName(request.getParameter("username"));
        userInfo.setPassword(MD5Utils.encryptMD5( request.getParameter("password"),request.getParameter("username")));
        userInfo.setNickName(request.getParameter("nickName"));
        userInfo.setTel(request.getParameter("tel"));
        userInfo.setAddress(request.getParameter("address"));
        userInfo.setSex(Integer.valueOf(request.getParameter("sex")));
        int rows = userInfoDao.addUserInfo(userInfo);
        if (rows>0){
            return BaseResult.success();
        }else {
            return BaseResult.error(3003,"新增失败");
        }
    }

    //删除
    @Override
    public String updateUserStatus(HttpServletRequest request,Integer flag){
        try {
            String id= request.getParameter("id");
            String[] ids=new String[1];
            if (id.contains(",")){
                ids=id.split(",");
            }else {
                ids[0]=id;
            }
            int rows = userInfoDao.batchUpdateUserStatus(ids,flag);
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
