package cn.daoyun.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import cn.daoyun.entity.Classes;
import cn.daoyun.entity.util.StringUtil;

public class classManageDao {

	/**
	 * @param con 数据库连接
	 * @param user 用户实体类
	 * @return 返回当前登录用户
	 * @throws Exception
	 */
	public String insertClass(Connection con,Classes classes) throws Exception{
		String sql="insert into classes(classId,className,teacher,classBeginDate,classEndDate) values(?,?,?,?,?)"
				;
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setString(1, classes.getClassId());
		pstmt.setString(2,classes.getClassName());
		pstmt.setString(3, classes.getTeacher());
		pstmt.setString(4, classes.getClassBeginDate());
		pstmt.setString(5, classes.getClassEndDate());
		int result = pstmt.executeUpdate();
		return "success";
	}
	
	public ArrayList<Classes> selectClasses(Connection con,Classes classes) throws Exception{
		    ArrayList<Classes> result = new ArrayList<Classes>();
		    ResultSet rs;
	    	String sql="select * from classes";
			PreparedStatement pstmt=con.prepareStatement(sql);
			rs=pstmt.executeQuery();
			while(rs.next()){
				Classes classDomp = new Classes();
				classDomp.setClassId(rs.getString("classId"));
				classDomp.setClassName(rs.getString("ClassName"));
				classDomp.setTeacher(rs.getString("Teacher"));
				classDomp.setClassBeginDate(rs.getDate("ClassBeginDate").toString());
				classDomp.setClassEndDate(rs.getDate("ClassEndDate").toString());
				result.add(classDomp);
			}
			return result;
	}
}
