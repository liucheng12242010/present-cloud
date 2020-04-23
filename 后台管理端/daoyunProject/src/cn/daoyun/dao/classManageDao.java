package cn.daoyun.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import cn.daoyun.entity.Classes;
import cn.daoyun.entity.User;
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
		pstmt.setDate(4, classes.getClassBeginDate());
		pstmt.setDate(5,classes.getClassEndDate());
		int result = pstmt.executeUpdate();
		return "success";
	}
	
	public ArrayList<Classes> selectClasses(Connection con,Classes classes) throws Exception{
		    List<Classes> result = new ArrayList<Classes>();
		    ResultSet rs;
		    if(!StringUtil.isEmpty(classes.getClassId()) && !StringUtil.isEmpty(classes.getClassName()) && !StringUtil.isEmpty(classes.getTeacher())){
		    	String sql="select * from classes where classId=? and className=? and teacher=?";
				PreparedStatement pstmt=con.prepareStatement(sql);
				pstmt.setString(1, classes.getClassId());
				pstmt.setString(2,classes.getClassName());
				pstmt.setString(3, classes.getTeacher());
				rs=pstmt.executeQuery();
				if(rs.next()){
					Classes classDomp = new Classes();
					classDomp.setClassId(rs.getString("classId"));
					classDomp.setClassName(rs.getString("ClassName"));
					classDomp.setTeacher(rs.getString("Teacher"));
					classDomp.setClassBeginDate(rs.getDate("ClassBeginDate"));
					classDomp.setClassEndDate(rs.getDate("ClassEndDate"));
					result.add(classDomp);
				}
		    }
		    else if(!StringUtil.isEmpty(classes.getClassId()) && !StringUtil.isEmpty(classes.getClassName())){
		    	String sql="select * from classes where classId=? and className=?";
				PreparedStatement pstmt=con.prepareStatement(sql);
				pstmt.setString(1, classes.getClassId());
				pstmt.setString(2,classes.getClassName());
				rs=pstmt.executeQuery();
				if(rs.next()){
					Classes classDomp = new Classes();
					classDomp.setClassId(rs.getString("classId"));
					classDomp.setClassName(rs.getString("ClassName"));
					classDomp.setTeacher(rs.getString("Teacher"));
					classDomp.setClassBeginDate(rs.getDate("ClassBeginDate"));
					classDomp.setClassEndDate(rs.getDate("ClassEndDate"));
					result.add(classDomp);
				}
		    }
		    else if(!StringUtil.isEmpty(classes.getClassId()) && !StringUtil.isEmpty(classes.getTeacher())){
		    	String sql="select * from classes where classId=? and teacher=?";
				PreparedStatement pstmt=con.prepareStatement(sql);
				pstmt.setString(1, classes.getClassId());
				pstmt.setString(2, classes.getTeacher());
				rs=pstmt.executeQuery();
				if(rs.next()){
					Classes classDomp = new Classes();
					classDomp.setClassId(rs.getString("classId"));
					classDomp.setClassName(rs.getString("ClassName"));
					classDomp.setTeacher(rs.getString("Teacher"));
					classDomp.setClassBeginDate(rs.getDate("ClassBeginDate"));
					classDomp.setClassEndDate(rs.getDate("ClassEndDate"));
					result.add(classDomp);
				}
		    }
		    else if(!StringUtil.isEmpty(classes.getClassName()) && !StringUtil.isEmpty(classes.getTeacher())){
		    	String sql="select * from classes where className=? and teacher=?";
				PreparedStatement pstmt=con.prepareStatement(sql);
				pstmt.setString(1,classes.getClassName());
				pstmt.setString(2, classes.getTeacher());
				rs=pstmt.executeQuery();
				if(rs.next()){
					Classes classDomp = new Classes();
					classDomp.setClassId(rs.getString("classId"));
					classDomp.setClassName(rs.getString("ClassName"));
					classDomp.setTeacher(rs.getString("Teacher"));
					classDomp.setClassBeginDate(rs.getDate("ClassBeginDate"));
					classDomp.setClassEndDate(rs.getDate("ClassEndDate"));
					result.add(classDomp);
				}
		    }
		    else if(!StringUtil.isEmpty(classes.getClassId())){
		    	String sql="select * from classes where classId=?";
				PreparedStatement pstmt=con.prepareStatement(sql);
				pstmt.setString(1, classes.getClassId());
				rs=pstmt.executeQuery();
				if(rs.next()){
					Classes classDomp = new Classes();
					classDomp.setClassId(rs.getString("classId"));
					classDomp.setClassName(rs.getString("ClassName"));
					classDomp.setTeacher(rs.getString("Teacher"));
					classDomp.setClassBeginDate(rs.getDate("ClassBeginDate"));
					classDomp.setClassEndDate(rs.getDate("ClassEndDate"));
					result.add(classDomp);
				}
		    }
		    else if(!StringUtil.isEmpty(classes.getClassName())){
		    	String sql="select * from classes where className=?";
				PreparedStatement pstmt=con.prepareStatement(sql);
				pstmt.setString(1,classes.getClassName());
				rs=pstmt.executeQuery();
				if(rs.next()){
					Classes classDomp = new Classes();
					classDomp.setClassId(rs.getString("classId"));
					classDomp.setClassName(rs.getString("ClassName"));
					classDomp.setTeacher(rs.getString("Teacher"));
					classDomp.setClassBeginDate(rs.getDate("ClassBeginDate"));
					classDomp.setClassEndDate(rs.getDate("ClassEndDate"));
					result.add(classDomp);
				}
		    }
		    else if(!StringUtil.isEmpty(classes.getTeacher())){
		    	String sql="select * from classes where teacher=?";
				PreparedStatement pstmt=con.prepareStatement(sql);
				pstmt.setString(1,classes.getTeacher());
				rs=pstmt.executeQuery();
				if(rs.next()){
					Classes classDomp = new Classes();
					classDomp.setClassId(rs.getString("classId"));
					classDomp.setClassName(rs.getString("ClassName"));
					classDomp.setTeacher(rs.getString("Teacher"));
					classDomp.setClassBeginDate(rs.getDate("ClassBeginDate"));
					classDomp.setClassEndDate(rs.getDate("ClassEndDate"));
					result.add(classDomp);
				}
		    }
		    else{
		    	String sql="select * from classes";
				PreparedStatement pstmt=con.prepareStatement(sql);
				rs=pstmt.executeQuery();
				while(rs.next()){
					Classes classDomp = new Classes();
					classDomp.setClassId(rs.getString("classId"));
					classDomp.setClassName(rs.getString("ClassName"));
					classDomp.setTeacher(rs.getString("Teacher"));
					classDomp.setClassBeginDate(rs.getDate("ClassBeginDate"));
					classDomp.setClassEndDate(rs.getDate("ClassEndDate"));
					result.add(classDomp);
				}
		    }
		    
			return result;
	}
}
