package cn.daoyun.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import cn.daoyun.entity.User;

public class registerDao {

	/**
	 * @param con 数据库连接
	 * @param user 用户实体类
	 * @return 返回当前登录用户
	 * @throws Exception
	 */
	public String register(Connection con,User user) throws Exception{
			String sql="insert into user(userid,username,password,role,remarks,school,gender,college) values(?,?,?,?,?,?,?,?)"
					;
			PreparedStatement pstmt=con.prepareStatement(sql);
			pstmt.setString(1, user.getUserId());
			pstmt.setString(2,user.getUserName());
			pstmt.setString(3, user.getPassword());
			pstmt.setString(4, user.getRole());
			pstmt.setString(5,user.getRemarks());
			pstmt.setString(6,user.getSchool());
			pstmt.setString(7,user.getGender());
			pstmt.setString(8,user.getCollege());
			int result = pstmt.executeUpdate();
			return "success";
	}
}
