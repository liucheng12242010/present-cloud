package cn.daoyun.mobile;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.daoyun.entity.util.DbUtil;

/**
 * Servlet implementation class ListServlet
 */
public class List2Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public List2Servlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String classname = URLDecoder.decode(request.getParameter("classname"),"UTF-8");
		String teacher = URLDecoder.decode(request.getParameter("teacher"),"UTF-8");
		String sql = "select c.CLASSNAME,c.CLASSID,c.TEACHER from classes c ";
		if(classname!=null&&!classname.equals("")){
			sql+=" and c.CLASSNAME like '%"+classname+"'%";
		} if(teacher!=null&&!teacher.equals("")){
			sql+=" and c.TEACHER like '%"+teacher+"%'";
		} 
		DbUtil util = new DbUtil();
		Connection conn = null;
		try {
			conn = util.getCon();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		String result = "";
		System.out.println("sql ="+sql);
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			int i = 0;
			while(rs.next()) {
				String teacher1 =rs.getString("TEACHER");
				String classname1=rs.getString("CLASSNAME");
				String classId=rs.getString("CLASSID");
 				if(i!=0){
					result+="@";
				}
				result+=classname1;
				result+=",";
				result+=teacher1;
				result+=",";
				result+=classId;
				i++;
			}
		}catch (SQLException e) {
			result = "0";
			e.printStackTrace();
		} finally {
			try {
				util.closeCon(conn);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}	
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		out.print(result);
		
	}

}
