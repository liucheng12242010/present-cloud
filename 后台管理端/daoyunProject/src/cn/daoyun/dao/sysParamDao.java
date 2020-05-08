package cn.daoyun.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import cn.daoyun.entity.SysParam;

/**
 * 用户登录 的dao  
 * @author cdfengyang
 *
 */
public class sysParamDao {

	/**
	 * @param con 数据库连接
	 * @param user 用户实体类
	 * @return 返回当前登录用户
	 * @throws Exception
	 */
	public ArrayList<SysParam> selectSysParam(Connection con) throws Exception{
	    ArrayList<SysParam> result = new ArrayList<SysParam>();
	    ResultSet rs;
    	String sql="select * from sysparam where 1=1 ";
		PreparedStatement pstmt=con.prepareStatement(sql);
		rs=pstmt.executeQuery();
		while(rs.next()){
			SysParam Domp = new SysParam();
			Domp.setExperience(rs.getString("experience"));
			Domp.setDistance(rs.getString("distance"));
			Domp.setDuration(rs.getString("duration"));
			Domp.setAdminId(rs.getString("adminId"));
			Domp.setAdminPassword(rs.getString("adminPassword"));
			result.add(Domp);
		}
		return result;
	}
}
