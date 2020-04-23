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
import cn.daoyun.dao.dictDao;
import cn.daoyun.entity.Classes;
import cn.daoyun.entity.Dict;
import cn.daoyun.entity.util.DbUtil;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;


public class dictAction extends ActionSupport {

	private static final long serialVersionUID = 1L;
	private Dict dict=new Dict();
	private String error;
	private int page=1;
	private int limit=10;
	
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
	
	public Dict getDict() {
		return dict;
	}
	public void setDict(Dict dict) {
		this.dict = dict;
	}
	
	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	DbUtil dbUtil=new DbUtil();
    dictDao dictDao=new dictDao();
    
    public String selectRole(){
    	HttpServletRequest request=ServletActionContext.getRequest();
		HttpSession session=request.getSession();
		Connection con=null;
    	try{
    		con=dbUtil.getCon();
    		ArrayList<Dict> dictList = dictDao.selectDict(con,"用户角色");
    		session.setAttribute("dictList", dictList);
    		
    		JSONArray array = JSONArray.fromObject(dictList);
    		Map<String,Object> result = new HashMap<String,Object>();
    		result.put("code", 0);
    		result.put("msg", "");
    		result.put("count", dictList.size());
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
    
    public String selectSchool(){
    	HttpServletRequest request=ServletActionContext.getRequest();
		HttpSession session=request.getSession();
		Connection con=null;
    	try{
    		con=dbUtil.getCon();
    		ArrayList<Dict> dictList = dictDao.selectDict(con,"学校分类");
    		session.setAttribute("dictList", dictList);
    		
    		JSONArray array = JSONArray.fromObject(dictList);
    		Map<String,Object> result = new HashMap<String,Object>();
    		result.put("code", 0);
    		result.put("msg", "");
    		result.put("count", dictList.size());
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
    
    public String selectCollege(){
    	HttpServletRequest request=ServletActionContext.getRequest();
		HttpSession session=request.getSession();
		Connection con=null;
    	try{
    		con=dbUtil.getCon();
    		ArrayList<Dict> dictList = dictDao.selectDict(con,"学院分类");
    		session.setAttribute("dictList", dictList);
    		
    		JSONArray array = JSONArray.fromObject(dictList);
    		Map<String,Object> result = new HashMap<String,Object>();
    		result.put("code", 0);
    		result.put("msg", "");
    		result.put("count", dictList.size());
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
    
    public String selectCourseStatus(){
    	HttpServletRequest request=ServletActionContext.getRequest();
		HttpSession session=request.getSession();
		Connection con=null;
    	try{
    		con=dbUtil.getCon();
    		ArrayList<Dict> dictList = dictDao.selectDict(con,"课程状态");
    		session.setAttribute("dictList", dictList);
    		
    		JSONArray array = JSONArray.fromObject(dictList);
    		Map<String,Object> result = new HashMap<String,Object>();
    		result.put("code", 0);
    		result.put("msg", "");
    		result.put("count", dictList.size());
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
    
    public String insertRole(){
    	HttpServletRequest request=ServletActionContext.getRequest();
		HttpSession session=request.getSession();
		Connection con=null;
    	try{
    		con=dbUtil.getCon();
    		dictDao.insertDict(con, dict);
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
