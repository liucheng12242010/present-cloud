package cn.daoyun.mobile;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.daoyun.entity.util.DbUtil;

/**
 * Servlet implementation class ListServlet
 */
public class JoinServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public JoinServlet() {
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
		
		String classId = URLDecoder.decode(request.getParameter("classid"),"UTF-8");
		String id = URLDecoder.decode(request.getParameter("id"),"UTF-8");
		
		DbUtil util = new DbUtil();
		Connection conn = null;
		try {
			conn = util.getCon();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		String result = "";
		try {
			String sql = "select * from classes  where  CLASSID = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,classId);
			ResultSet rs = pstmt.executeQuery();
			rs.last();
			if(rs.getRow()>0) {
				sql = "select c.CLASSNAME,c.CLASSID,c.TEACHER from user u,classes c ,studyrecord s where c.CLASSID = s.classid and u.USERID = s.userid and s.USERID = ? and s.classid = ?";
				PreparedStatement pstmtSelect=conn.prepareStatement(sql);
				pstmtSelect.setString(1, id);
				pstmtSelect.setString(2, classId);
				ResultSet rs2 = pstmtSelect.executeQuery();
				rs2.last();
				if(rs2.getRow()>0) {
					result = "3";
				}else {
					sql="insert into studyrecord(CLASSID,USERID,EXPERIENCE,STATUS) values(?,?,?,?)";
					PreparedStatement pstmt2=conn.prepareStatement(sql);
					pstmt2.setString(1, classId);
					pstmt2.setString(2,id);
					pstmt2.setString(3, "0");
					pstmt2.setString(4, "0");
					int result2 = pstmt2.executeUpdate();
					result = "1";
				}
				
			}else {
				result = "0";//找不到课程号对应的课程
			}
		}catch (SQLException e) {
			result = "2";
			e.printStackTrace();
		}  finally {
			try {
				util.closeCon(conn);
			} catch (Exception e) {
				result = "3";
				e.printStackTrace();
			}
		}	
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		out.print(result);
		
	}

}
