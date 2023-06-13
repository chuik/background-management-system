package com.hzlx.dao;

import com.hzlx.entity.MenuInfo;

import java.util.List;
import java.util.Map;

public interface MenuInfoDao {

    /**
     * 根据用户ID和菜单pid  查询菜单集合
     * @param userId 用户ID
     * @param pId 父级id
     * @return
     */
    List<MenuInfo> getMenuInfoListByPid(Integer userId, Integer pId);

    List<Map<String,Object>> getMenuAll(String keyword);

    MenuInfo getMenuInfoById(Integer id);

    int updateMenuInfoById(Integer id, String title, String icon,String href, Integer pId);

    int deleteMenuInfoById(Integer id);

    int saveMenu(String title,String icon,String href,String pId);

    List<MenuInfo> getMenuInfoByPid(Integer pId);

    int batchUpdateMenuStatus(String[] ids);
}
