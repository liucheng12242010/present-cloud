package cn.daoyun.action;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import cn.daoyun.dao.UserDao;
import cn.daoyun.entity.Dict;
import cn.daoyun.entity.User;
import cn.daoyun.entity.util.DbUtil;
import cn.daoyun.entity.util.StringUtil;
import cn.daoyun.entity.util.TokenUtil;
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
	TokenUtil tokenUtil = new TokenUtil();
	
	@Override
	public String execute() throws Exception {
		HttpServletRequest request=ServletActionContext.getRequest();
		HttpSession session=request.getSession();
		HttpServletResponse reps=ServletActionContext.getResponse();
		Cookie[] cookies = request.getCookies();
		User currentUser = new User();
		String accessToken;
		Connection con=dbUtil.getCon();
		try{
			if(cookies!=null && cookies.length>0)
			{
				for(Cookie domp : cookies)
				{
					accessToken = userDao.selectToken(con, domp.getValue());
					if(accessToken != null && accessToken != "false")
					{
						//currentUser.setUserId(cookie.);
						String result = tokenUtil.validateJWT(accessToken);
						if(result != null)
						{
							currentUser.setUserId(result);
							currentUser.setUserName(result);
							session.setAttribute("currentUser", currentUser);
							return SUCCESS;
						}
						userDao.deleteToken(con, domp.getValue());
					}
				}
			}
			if(StringUtil.isEmpty(user.getUserId())||StringUtil.isEmpty(user.getPassword())){
				error="用户账号或者密码为空！";
				return ERROR;
			}
			currentUser=userDao.login(con, user);
			if(currentUser==null){
				error="用户账号或者密码错误！";
				return ERROR;
			}else{	
				accessToken = tokenUtil.createJWT(user,60*60*1000);
				userDao.addToken(con, user, accessToken);
				session.setAttribute("currentUser", currentUser);
				//session.setAttribute("token", accessToken);
				Cookie cookie = new Cookie("token",accessToken);
				reps.addCookie(cookie);
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
}
