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
 * Servlet implementation class LoginServlet
 */
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		DbUtil dbUtil=new DbUtil();
		Connection conn = null;
		String userName = URLDecoder.decode(request.getParameter("userName"),"UTF-8");
		String password = URLDecoder.decode(request.getParameter("password"),"UTF-8");
		
		String sql="select * from user where userid=? and password=?";
		try {
			conn = dbUtil.getCon();
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		String result = "0";
		try {
			PreparedStatement pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, userName);
			pstmt.setString(2, password);
			ResultSet rs=pstmt.executeQuery();
			while(rs.next()) {
				result=rs.getInt("userid")+"";
			}
			
		}catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				dbUtil.closeCon(conn);
			} catch (Exception e) {
				e.printStackTrace();
			};
		}	
		PrintWriter out = response.getWriter();
		out.print(result);
	}

}
