package cn.daoyun.action;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import cn.daoyun.dao.UserDao;
import cn.daoyun.entity.Dict;
import cn.daoyun.entity.User;
import cn.daoyun.entity.util.DbUtil;
import cn.daoyun.entity.util.StringUtil;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * 该类用户管理用户登录信息
 *
 */
public class UserAction extends ActionSupport {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private User user=new User();
	private String error;
	private String userId;
	private String userName;
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	
	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	DbUtil dbUtil=new DbUtil();
	UserDao userDao=new UserDao();
	
	public String studentSelect(){
    	HttpServletRequest request=ServletActionContext.getRequest();
		HttpSession session=request.getSession();
		Connection con=null;
    	try{
    		con=dbUtil.getCon();
    		user.setUserId(userId);
    		user.setUserName(userName);
    		ArrayList<User> userList = userDao.selectUser(con,user);
    		session.setAttribute("dictList", userList);
    		
    		JSONArray array = JSONArray.fromObject(userList);
    		Map<String,Object> result = new HashMap<String,Object>();
    		result.put("code", 0);
    		result.put("msg", "");
    		result.put("count", userList.size());
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
	
	
	@Override
	public String execute() throws Exception {
		HttpServletRequest request=ServletActionContext.getRequest();
		HttpSession session=request.getSession();
		if(StringUtil.isEmpty(user.getUserId())||StringUtil.isEmpty(user.getPassword())){
			error="用户账号或者密码为空！";
			return ERROR;
		}
		Connection con=null;
		try{
			con=dbUtil.getCon();
			User currentUser=userDao.login(con, user);
			if(currentUser==null){
				error="用户账号或者密码错误！";
				return ERROR;
			}else{
				session.setAttribute("currentUser", currentUser);
				return SUCCESS;
			}
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				dbUtil.closeCon(con);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return SUCCESS;
	}	
}
