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
public class SignServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SignServlet() {
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
		
		String classId = URLDecoder.decode(request.getParameter("classId"),"UTF-8");
		String teacher = URLDecoder.decode(request.getParameter("teacher"),"UTF-8");
		
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
		String exp = "";
		try {
			String sql = "select EXPERIENCE from sysparam";
			PreparedStatement pstmtEx = conn.prepareStatement(sql);
			ResultSet rsEx = pstmtEx.executeQuery();
			if(rsEx.first()) {
				exp =rsEx.getString("EXPERIENCE");
			}
			sql = "select STATUS from studyrecord where USERID =? and CLASSID = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2,classId);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				result=rs.getString("STATUS");
				if(!result.equals("1")) {
					result = "当前非签到时间或您已签到";
				}else {
					sql = "select startsignin,endsignin from classes where CLASSID = ?";
					PreparedStatement pstmt2 = conn.prepareStatement(sql);
					pstmt2.setString(1,classId);
					ResultSet rs2 = pstmt2.executeQuery();
					while(rs2.next()) {
						String startTime = rs2.getString("startsignin")!=null?rs2.getString("startsignin"):"";
						String endTime = rs2.getString("endsignin")!=null?rs2.getString("endsignin"):"";
						SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
						Date date1 = format.parse(startTime);
						Date date2 = format.parse(endTime);
						Date date3 = new Date();
						if(date3.getTime()>date1.getTime() && date3.getTime()<date2.getTime()) {
							sql = "update studyrecord set EXPERIENCE =EXPERIENCE+? , STATUS = 2 where USERID = ? and CLASSID = ?";
							PreparedStatement pstmt3 = conn.prepareStatement(sql);
							if(exp!="" && exp!=null) {
								pstmt3.setInt(1, Integer.parseInt(exp));
							}else {
								pstmt3.setInt(1, 2);
								exp = "2";
							}
							pstmt3.setString(2,id);
							pstmt3.setString(3,classId);
							int rs3 = pstmt3.executeUpdate();
							result = "签到成功获得"+exp+"经验值";
						}
					}
					
				}
			}
			
			
		}catch (SQLException e) {
			result = "签到失败，请联系管理员";
			e.printStackTrace();
		} catch (ParseException e) {
			result = "签到失败，请联系管理员";
			// TODO Auto-generated catch block
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
