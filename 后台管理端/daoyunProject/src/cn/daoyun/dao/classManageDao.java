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
	
	public String deleteClass(Connection con,Classes classes) throws Exception{
		String sql="delete from classes where classId=? and className=? and teacher=? and classBeginDate=? and classEndDate=?"
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
	
	public String signin(Connection con,Classes classes) throws Exception{
		String sql="update classes set startsignin=now(),endsignin=adddate(now(),interval 1 minute)"
				+ " where classId=?"
				;
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setString(1, classes.getClassId());
		int result = pstmt.executeUpdate();
	    sql="update studyrecord set status=1"
				+ " where classId=?"
				;
		pstmt=con.prepareStatement(sql);
		pstmt.setString(1, classes.getClassId());
		result = pstmt.executeUpdate();
		return "success";
	}
	
	public ArrayList<Classes> selectClasses(Connection con,Classes classes) throws Exception{
		    ArrayList<Classes> result = new ArrayList<Classes>();
		    ResultSet rs;
		    String sql="select * from classes";
		    int flag=0;
		    if(!StringUtil.isEmpty(classes.getClassId()))
			{
		    	if(flag==0)
		    	{
		    		sql=sql+" where classId='"+classes.getClassId()+"'"; 
		    		flag=1;
		    	}
			}
		    if(!StringUtil.isEmpty(classes.getClassName()))
	    	{
		    	if(flag==0)
		    	{
		    		sql=sql+" where className='"+classes.getClassName()+"'";
		    		flag=1;
		    	}
		    	else
		    		sql=sql+" and className='"+classes.getClassName()+"'";
		    		
	    	}
		    if(!StringUtil.isEmpty(classes.getTeacher()))
	    	{
		    	if(flag==0)
		    	{
		    		sql=sql+" where teacher='"+classes.getTeacher()+"'";
		    		flag=1;
		    	}
		    	else
		    		sql=sql+" and teacher='"+classes.getTeacher()+"'";
		    		
	    	}
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
