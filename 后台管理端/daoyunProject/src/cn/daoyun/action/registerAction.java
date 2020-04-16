package cn.daoyun.action;

import java.sql.Connection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

import cn.daoyun.dao.UserDao;
import cn.daoyun.dao.registerDao;
import cn.daoyun.entity.User;
import cn.daoyun.entity.util.DbUtil;
import cn.daoyun.entity.util.StringUtil;

public class registerAction extends ActionSupport {

	private static final long serialVersionUID = 1L;
	private User user;
	private String error;
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	DbUtil dbUtil=new DbUtil();
    registerDao register=new registerDao();
	@Override
	public String execute() throws Exception {
		HttpServletRequest request=ServletActionContext.getRequest();
		HttpSession session=request.getSession();
		Connection con=null;
		try{
			con=dbUtil.getCon();
			register.register(con, user);
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
