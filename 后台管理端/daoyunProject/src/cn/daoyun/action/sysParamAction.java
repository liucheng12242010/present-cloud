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
import cn.daoyun.dao.sysParamDao;
import cn.daoyun.entity.Dict;
import cn.daoyun.entity.SysParam;
import cn.daoyun.entity.User;
import cn.daoyun.entity.util.DbUtil;
import cn.daoyun.entity.util.StringUtil;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * 该类用户管理用户登录信息
 *
 */
public class sysParamAction extends ActionSupport {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String error;

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	DbUtil dbUtil=new DbUtil();
	sysParamDao sysParamDao=new sysParamDao();
	
	public String sysParamSelect(){
    	HttpServletRequest request=ServletActionContext.getRequest();
		HttpSession session=request.getSession();
		Connection con=null;
    	try{
    		con=dbUtil.getCon();
    		ArrayList<SysParam> List = sysParamDao.selectSysParam(con);
    		
    		JSONArray array = JSONArray.fromObject(List);
    		Map<String,Object> result = new HashMap<String,Object>();
    		result.put("code", 0);
    		result.put("msg", "");
    		result.put("count", List.size());
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
