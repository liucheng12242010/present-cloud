package cn.daoyun.entity;

/**
 * 部门实体类  用于封装部门信息
 * @author cdfengyang 
 *
 */
public class Department {

	private int departmentId;
	private String departmentName;
	private String departmentDesc;
	public int getDepartmentId() {
		return departmentId;
	}
	public void setDepartmentId(int departmentId) {
		this.departmentId = departmentId;
	}
	public String getDepartmentName() {
		return departmentName;
	}
	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}
	public String getDepartmentDesc() {
		return departmentDesc;
	}
	public void setDepartmentDesc(String departmentDesc) {
		this.departmentDesc = departmentDesc;
	}
	
}
