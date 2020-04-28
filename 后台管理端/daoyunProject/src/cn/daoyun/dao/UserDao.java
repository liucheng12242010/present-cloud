package cn.daoyun.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import cn.daoyun.entity.Dict;
import cn.daoyun.entity.User;

/**
 * 用户登录 的dao  
 * @author cdfengyang
 *
 */
public class UserDao {

	/**
	 * @param con 数据库连接
	 * @param user 用户实体类
	 * @return 返回当前登录用户
	 * @throws Exception
	 */
	public ArrayList<User> selectUser(Connection con,String type) throws Exception{
	    ArrayList<User> result = new ArrayList<User>();
	    ResultSet rs;
    	String sql="select * from user where role='"+type+"'";
		PreparedStatement pstmt=con.prepareStatement(sql);
		rs=pstmt.executeQuery();
		while(rs.next()){
			User userDomp = new User();
			userDomp.setUserId(rs.getString("userId"));
			userDomp.setUserName(rs.getString("userName"));
			userDomp.setRole("学生");
			userDomp.setSchool(rs.getString("school"));
			userDomp.setGender(rs.getString("gender"));
			userDomp.setCollege(rs.getString("college"));
			result.add(userDomp);
		}
		return result;
}
	
	public User login(Connection con,User user) throws Exception{
		User currentUser=null;
		String sql="select * from user where userId=? and password=?";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setString(1, user.getUserId());
		pstmt.setString(2, user.getPassword());
		ResultSet rs=pstmt.executeQuery();
		
		if(rs.next()){
			currentUser = new User();
			currentUser.setUserName(rs.getString("userId"));
			currentUser.setPassword(rs.getString("password"));
		}
		return currentUser;
	}
}
