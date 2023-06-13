package com.hzlx.dao.impl;

import com.hzlx.dao.MenuInfoDao;
import com.hzlx.entity.MenuInfo;
import com.hzlx.utils.BaseDao;
import com.mysql.cj.util.StringUtils;

import java.util.List;
import java.util.Map;

public class MenuInfoDaoImpl extends BaseDao<MenuInfo> implements MenuInfoDao {
    public List<MenuInfo> getMenuInfoListByPid(Integer userId, Integer pId){
//        String sql = "select * from t_menu_info where `status`=1 and p_id=?";
        String sql="select tmi.*\n" +
                "from t_menu_info tmi\n" +
                "left join t_menu_role_info tmri on tmri.menu_id=tmi.id\n" +
                "left join t_user_role_info turi  on turi.role_id=tmri.role_id\n" +
                "where turi.user_id=? and tmi.`status`=1 and tmi.p_id=?";
        return selectListForObject(sql, MenuInfo.class,userId,pId);
    }

    @Override
    public List<Map<String,Object>>  getMenuAll(String keyword) {
        String sql = "SELECT tmi1.*,tmi2.title as pName\n" +
                "FROM t_menu_info tmi1 \n" +
                "LEFT JOIN t_menu_info tmi2 on tmi2.id =tmi1.p_id";
        if (!StringUtils.isNullOrEmpty(keyword)){
            sql+=" where tmi1.title like concat('%',?,'%')";
            return  selectListForMap(sql, keyword);
        }
        return selectListForMap(sql);
    }

    @Override
    public MenuInfo getMenuInfoById(Integer id) {
        String sql =" select * from t_menu_info where id=?";
        return selectOne(sql,MenuInfo.class,id);
    }

    @Override
    public int updateMenuInfoById(Integer id, String title, String icon,String href, Integer pId) {
        String sql = "update t_menu_info set title=?, icon=?,href=?, p_id = ? where id=?";
        return executeUpdate(sql,title,icon,href,pId,id);
    }

    @Override
    public int deleteMenuInfoById(Integer id) {
        String sql = "update t_menu_info set `status`=0 where id=?";
        return executeUpdate(sql,id);
    }

    @Override
    public int saveMenu(String title ,String icon,String href,String pId) {
        String sql = "insert into t_menu_info values(default,?,?,?,?,now(),default)";
        return executeUpdate(sql, title, icon, href, pId);
    }

    @Override
    public List<MenuInfo> getMenuInfoByPid(Integer pId) {
        String sql = "select * from t_menu_info where `status`=1 and p_id=?";
        return selectListForObject(sql, MenuInfo.class,pId);
    }

    @Override
    public int batchUpdateMenuStatus(String[] ids) {
        String sql = "update t_menu_info set `status`=1 where id in(";
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
