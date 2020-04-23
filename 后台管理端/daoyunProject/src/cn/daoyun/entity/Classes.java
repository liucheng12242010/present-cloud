package cn.daoyun.entity;

import java.sql.Date;

public class Classes {
	private String classId;
	private String className;
	private String teacher;
	private Date classBeginDate;
	private Date classEndDate;
	private Date createTime;
	private Date updateTime;
	
	public String getClassId() {
		return classId;
	}
	public void setClassId(String classId) {
		this.classId = classId;
	}
	
	public String getClassName() {
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
	}
	
	public String getTeacher() {
		return teacher;
	}
	public void setTeacher(String teacher) {
		this.teacher = teacher;
	}
	
	public Date getClassBeginDate() {
		return classBeginDate;
	}
	public void setClassBeginDate(Date classBeginDate) {
		this.classBeginDate = classBeginDate;
	}
	
	public Date getClassEndDate() {
		return classEndDate;
	}
	public void setClassEndDate(Date classEndDate) {
		this.classEndDate = classEndDate;
	}
	
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
}
