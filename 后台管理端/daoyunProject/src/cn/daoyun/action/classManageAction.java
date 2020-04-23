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
	private String error;
	
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
    		ArrayList<Classes> classesList = classManage.selectClasses(con,classes);
    		session.setAttribute("classeslist", classesList);
    		
    		JSONArray array = JSONArray.fromObject(new ArrayList[]{classesList});
    		Map<String,Object> result = new HashMap<String,Object>();
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
