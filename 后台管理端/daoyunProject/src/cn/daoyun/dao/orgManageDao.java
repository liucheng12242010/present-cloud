package cn.daoyun.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import cn.daoyun.entity.Organization;
import cn.daoyun.entity.util.StringUtil;

public class orgManageDao {

	/**
	 * @param con 数据库连接
	 * @param user 用户实体类
	 * @return 返回当前登录用户
	 * @throws Exception
	 */
	public String insertOrg(Connection con,Organization org) throws Exception{
		String sql="insert into organization(orgId,school,college,major,grade) values(?,?,?,?,?)"
				;
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setString(1, org.getOrgId());
		pstmt.setString(2,org.getSchool());
		pstmt.setString(3, org.getCollege());
		pstmt.setString(4, org.getMajor());
		pstmt.setString(5, org.getGrade());
		int result = pstmt.executeUpdate();
		return "success";
	}
	
	public ArrayList<Organization> selectOrg(Connection con,Organization org) throws Exception{
		    ArrayList<Organization> result = new ArrayList<Organization>();
		    ResultSet rs;
		    String sql="select * from organization";
		    int flag=0;
		    if(!StringUtil.isEmpty(org.getSchool()))
			{
		    	if(flag==0)
		    	{
		    		sql=sql+" where school='"+org.getSchool()+"'"; 
		    		flag=1;
		    	}
			}
		    if(!StringUtil.isEmpty(org.getCollege()))
	    	{
		    	if(flag==0)
		    	{
		    		sql=sql+" where college='"+org.getCollege()+"'";
		    		flag=1;
		    	}
		    	else
		    		sql=sql+" and college='"+org.getCollege()+"'";
		    		
	    	}
		    if(!StringUtil.isEmpty(org.getMajor()))
	    	{
		    	if(flag==0)
		    	{
		    		sql=sql+" where major='"+org.getMajor()+"'";
		    		flag=1;
		    	}
		    	else
		    		sql=sql+" and major='"+org.getMajor()+"'";
		    		
	    	}
		    if(!StringUtil.isEmpty(org.getGrade()))
	    	{
		    	if(flag==0)
		    	{
		    		sql=sql+" where grade='"+org.getGrade()+"'";
		    		flag=1;
		    	}
		    	else
		    		sql=sql+" and grade='"+org.getGrade()+"'";
		    		
	    	}
			PreparedStatement pstmt=con.prepareStatement(sql);
			rs=pstmt.executeQuery();
			while(rs.next()){
				Organization Domp = new Organization();
				Domp.setOrgId(rs.getString("orgId"));
				Domp.setSchool(rs.getString("school"));
				Domp.setCollege(rs.getString("college"));
				Domp.setMajor(rs.getString("major"));
				Domp.setGrade(rs.getString("grade"));
				result.add(Domp);
			}
			return result;
	}
}
