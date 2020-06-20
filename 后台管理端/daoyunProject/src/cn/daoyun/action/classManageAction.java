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

import cn.daoyun.dao.classManageDao;
import cn.daoyun.entity.Classes;
import cn.daoyun.entity.util.DbUtil;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;


public class classManageAction extends ActionSupport {

	private static final long serialVersionUID = 1L;
	private Classes classes=new Classes();
	private String classId;
	private String className;
	private String teacher;
	private String error;
	private int page=1;
	private int limit=10;
	
	public String getClassId(){
		return classId;
	}
	public void setClassId(String classId){
		this.classId=classId;
	}
	
	public String getClassName(){
		return className;
	}
	public void setClassName(String className){
		this.className=className;
	}
	
	public String getTeacher(){
		return teacher;
	}
	public void setTeacher(String teacher){
		this.teacher=teacher;
	}

	
	public int getPage(){
		return page;
	}
	public void setPage(int page){
		this.page=page;
	}

	public int getLimit(){
		return limit;
	}
	public void setLimit(int limit){
		this.limit=limit;
	}
	
	public Classes getClasses() {
		return classes;
	}
	public void setClasses(Classes classes) {
		this.classes = classes;
	}
	
	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	DbUtil dbUtil=new DbUtil();
    classManageDao classManage=new classManageDao();
    
    public String select(){
    	HttpServletRequest request=ServletActionContext.getRequest();
		HttpSession session=request.getSession();
		Connection con=null;
    	try{
    		con=dbUtil.getCon();
    		classes.setClassId(classId);
    		classes.setClassName(className);
    		classes.setTeacher(teacher);
    		ArrayList<Classes> classesList = classManage.selectClasses(con,classes);
    		session.setAttribute("classeslist", classesList);
    		
    		JSONArray array = JSONArray.fromObject(classesList);
    		Map<String,Object> result = new HashMap<String,Object>();
    		result.put("code", 0);
    		result.put("msg", "");
    		result.put("count", classesList.size());
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
    		classManage.insertClass(con, classes);
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
    
    public String signin(){
    	HttpServletRequest request=ServletActionContext.getRequest();
		HttpSession session=request.getSession();
		Connection con=null;
    	try{
    		con=dbUtil.getCon();
    		classes.setClassId(classId);
    		classManage.signin(con, classes);
    		return "signin";
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
    	return "signin";
    }
    
    public String delete(){
    	HttpServletRequest request=ServletActionContext.getRequest();
		HttpSession session=request.getSession();
		Connection con=null;
    	try{
    		con=dbUtil.getCon();
    		classManage.deleteClass(con, classes);
    		return "delete";
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
    	return "delete";
    }
    
	@Override
	public String execute() throws Exception {
		HttpServletRequest request=ServletActionContext.getRequest();
		HttpSession session=request.getSession();
		Connection con=null;
		try{
			con=dbUtil.getCon();
			//register.register(con, user);
			return SUCCESS;
		}catch (Exception e) {
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
		return SUCCESS;
	}	
}
