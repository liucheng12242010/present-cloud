package cn.daoyun.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import cn.daoyun.entity.Dict;
import cn.daoyun.entity.User;
import cn.daoyun.entity.util.StringUtil;

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
	public ArrayList<User> selectUser(Connection con,User user) throws Exception{
	    ArrayList<User> result = new ArrayList<User>();
	    ResultSet rs;
    	String sql="select * from user where 1=1 ";
	    if(!StringUtil.isEmpty(user.getUserId()))
	    {
	    	sql = sql + " and userId='"+user.getUserId()+"'";
	    }
	    if(!StringUtil.isEmpty(user.getUserName()))
	    {
	    	sql = sql + " and userName='"+user.getUserName()+"'";
	    }
		PreparedStatement pstmt=con.prepareStatement(sql);
		rs=pstmt.executeQuery();
		while(rs.next()){
			User userDomp = new User();
			userDomp.setUserId(rs.getString("userId"));
			userDomp.setUserName(rs.getString("userName"));
			if(rs.getString("role").equals("0"))
				userDomp.setRole("学生");
			if(rs.getString("role").equals("1"))
				userDomp.setRole("老师");
			if(rs.getString("role").equals("2"))
				userDomp.setRole("助教");
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
	
	public String addToken(Connection con,User user,String token) throws Exception{
		String sql="insert into tokenTable(userid,token,createTime) values(?,?,?)"
				;
		long nowMillis = System.currentTimeMillis();
		Date d = new Date(nowMillis);
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setString(1, user.getUserId());
		pstmt.setString(2,token);
		pstmt.setDate(3,d);
		int result = pstmt.executeUpdate();
		return "success";
	}
	
	public String selectToken(Connection con,String token) throws Exception{
		String sql="select * from tokenTable where token = ?"
				;
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setString(1, token);
		ResultSet rs=pstmt.executeQuery();
		
		if(rs.next()){
			return token;
		}
		return "false";
	}
	
	public String deleteToken(Connection con,String token) throws Exception{
		String sql="delete from tokenTable where token = ?"
				;
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setString(1, token);
		int result = pstmt.executeUpdate();
		return "false";
	}
}
