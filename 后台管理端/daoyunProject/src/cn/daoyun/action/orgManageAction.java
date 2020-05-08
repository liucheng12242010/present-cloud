package cn.daoyun.action;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import cn.daoyun.dao.orgManageDao;
import cn.daoyun.entity.Organization;
import cn.daoyun.entity.util.DbUtil;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;


public class orgManageAction extends ActionSupport {

	private static final long serialVersionUID = 1L;
	private Organization organization=new Organization();
	private String school;
	private String college;
	private String major;
	private String grade;
	private String error;
	private int page=1;
	private int limit=10;
	
	public Organization getOrganization() {
		return organization;
	}
	public void setOrganization(Organization organization) {
		this.organization = organization;
	}
	
	public String getSchool() {
		return school;
	}
	public void setSchool(String school) {
		this.school = school;
	}
	
	public String getCollege() {
		return college;
	}
	public void setCollege(String college) {
		this.college = college;
	}
	
	public String getMajor() {
		return major;
	}
	public void setMajor(String major) {
		this.major = major;
	}
	
	public String getGrade() {
		return grade;
	}
	public void setGrade(String grade) {
		this.grade = grade;
	}
	
	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	DbUtil dbUtil=new DbUtil();
    orgManageDao orgManage=new orgManageDao();
    
    public String select(){
    	HttpServletRequest request=ServletActionContext.getRequest();
		HttpSession session=request.getSession();
		Connection con=null;
    	try{
    		con=dbUtil.getCon();
    		organization.setSchool(school);
    		organization.setCollege(college);
    		organization.setMajor(major);
    		organization.setGrade(grade);
    		ArrayList<Organization> orgList = orgManage.selectOrg(con, organization);
    		
    		JSONArray array = JSONArray.fromObject(orgList);
    		Map<String,Object> result = new HashMap<String,Object>();
    		result.put("code", 0);
    		result.put("msg", "");
    		result.put("count", orgList.size());
    		result.put("data", array);
    		ActionContext.getContext().getValueStack().set("jsonData", JSONObject.fromObject(result));
    		
    		return "select";
    	}catch(Exception e){
    		e.printStackTrace();
			System.out.print(e);
    	}finally{
    		try {
				dbUtil.closeCon(con);
			} catch (Exception e) {
				e.printStackTrace();
				System.out.print(e);
			}
    	}
    	return "select";
    }
    
    public String insert(){
    	HttpServletRequest request=ServletActionContext.getRequest();
		HttpSession session=request.getSession();
		Connection con=null;
    	try{
    		con=dbUtil.getCon();
    		orgManage.insertOrg(con, organization);
    		return "insert";
    	}catch(Exception e){
    		e.printStackTrace();
			System.out.print(e);
    	}finally{
    		try {
				dbUtil.closeCon(con);
			} catch (Exception e) {
				e.printStackTrace();
				System.out.print(e);
			}
    	}
    	return "insert";
    }
}
