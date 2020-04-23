package cn.daoyun.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import cn.daoyun.entity.Dict;

public class dictDao {

	/**
	 * @param con 数据库连接
	 * @param user 用户实体类
	 * @return 返回当前登录用户
	 * @throws Exception
	 */
	public String insertDict(Connection con,Dict dict) throws Exception{
		String sql="insert into dict(dicttype,dictname,dictorder,dictstatus) values(?,?,?,?)"
				;
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setString(1, dict.getType());
		pstmt.setString(2,dict.getName());
		pstmt.setString(3, dict.getOrder());
		pstmt.setString(4, dict.getStatus());
		int result = pstmt.executeUpdate();
		return "success";
	}
	
	public ArrayList<Dict> selectDict(Connection con,String type) throws Exception{
		    ArrayList<Dict> result = new ArrayList<Dict>();
		    ResultSet rs;
	    	String sql="select * from dict where dicttype='"+type+"' and dictstatus='T'";
			PreparedStatement pstmt=con.prepareStatement(sql);
			rs=pstmt.executeQuery();
			while(rs.next()){
				Dict dictDomp = new Dict();
				dictDomp.setType(rs.getString("dicttype"));
				dictDomp.setName(rs.getString("dictname"));
				dictDomp.setOrder(rs.getString("dictorder"));
				dictDomp.setStatus(rs.getString("dictstatus"));
				result.add(dictDomp);
			}
			return result;
	}
}
