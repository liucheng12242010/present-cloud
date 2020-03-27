package cn.tedu.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import cn.tedu.entity.Department;
import cn.tedu.entity.Page;
import cn.tedu.entity.util.StringUtil;

/**
 * 部门 dao
 * @author cdfengyang
 *
 */
public class DepartmentDao {

	/**
	 * 通过查询返回 部门列表
	 * @param con
	 * @param page
	 * @param department
	 * @return
	 * @throws Exception
	 */
	public ResultSet departmentList(Connection con,Page page,Department department) throws Exception{
		StringBuffer sb=new StringBuffer("select * from t_department");
		if(department!=null&&!StringUtil.isEmpty(department.getDepartmentName())){
			sb.append(" and departmentName like '%"+department.getDepartmentName()+"%'");
		}
		if(page!=null){
			sb.append(" limit "+page.getStart()+","+page.getRows());
		}
		PreparedStatement pstmt=con.prepareStatement(sb.toString().replaceFirst("and", "where"));
		return pstmt.executeQuery();		
	}
	
	/**
	 * 查询返回部门的数量
	 * @param con
	 * @return
	 * @throws Exception
	 */
	public int departmentCount(Connection con)throws Exception{
		String sql="select count(*) as total from t_department";
		PreparedStatement pstmt=con.prepareStatement(sql);
		ResultSet rs=pstmt.executeQuery();
		if(rs.next()){
			return rs.getInt("total");
		}else{
			return 0;
		}
	}
	
	/**
	 * 增加新部门
	 * @param con
	 * @param department
	 * @return
	 * @throws Exception
	 */
	public int departmentAdd(Connection con,Department department) throws Exception{
		String sql="insert into t_department values(null,?,?)";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setString(1, department.getDepartmentName());
		pstmt.setString(2, department.getDepartmentDesc());
		return pstmt.executeUpdate();		
	}
	
	/**
	 * 删除部门
	 * @param con
	 * @param delIds
	 * @return
	 * @throws Exception
	 */
	public int departmentDelete(Connection con,String delIds)throws Exception{
		String sql="delete from t_department where departmentId in("+delIds+")";
		PreparedStatement pstmt=con.prepareStatement(sql);
		return pstmt.executeUpdate();
	}
	
	/**
	 * 修改部门的信息
	 * @param con
	 * @param department
	 * @return
	 * @throws Exception
	 */
	public int departmentModify(Connection con,Department department)throws Exception{
		String sql="update t_department set departmentName=?,departmentDesc=? where departmentId=?";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setString(1, department.getDepartmentName());
		pstmt.setString(2, department.getDepartmentDesc());
		pstmt.setInt(3, department.getDepartmentId());
		return pstmt.executeUpdate();
	}
}
