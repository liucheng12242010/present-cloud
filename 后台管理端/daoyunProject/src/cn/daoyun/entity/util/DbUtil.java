package cn.daoyun.entity.util;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * 数据库连接工具类
 * @author cdfengyang
 *
 */
public class DbUtil {

	private String url="jdbc:mysql://129.204.242.132:3306/presentcloud";
	private String user="root";
	private String password="Csj301059121";
	private String driver="com.mysql.jdbc.Driver";
	
	public Connection getCon() throws Exception{
		Class.forName(driver);
		Connection con=DriverManager.getConnection(url,user,password);
		return con;
	}

	public void closeCon(Connection con) throws Exception{
		if(con!=null){
			con.close();
		}
	}

	public static void main(String[] args) {
		DbUtil dbUtil=new DbUtil();
		Connection con=null;
		try {
			con=dbUtil.getCon();
			System.out.println("连接成功");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				dbUtil.closeCon(con);
				System.out.println("关闭连接");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
