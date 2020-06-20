package cn.daoyun.entity;

import java.sql.Date;

public class Classes {
	private String classId;
	private String className;
	private String teacher;
	private String classBeginDate;
	private String classEndDate;
	private String createTime;
	private String updateTime;
	private String startSignin;
	private String endSignin;
	
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
	
	public String getClassBeginDate() {
		return classBeginDate;
	}
	public void setClassBeginDate(String classBeginDate) {
		this.classBeginDate = classBeginDate;
	}
	
	public String getClassEndDate() {
		return classEndDate;
	}
	public void setClassEndDate(String classEndDate) {
		this.classEndDate = classEndDate;
	}
	
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	
	public String getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}
	
	public String getStartSignin() {
		return startSignin;
	}
	public void setStartSignin(String startSignin) {
		this.startSignin = startSignin;
	}
	
	public String getEndSignin() {
		return endSignin;
	}
	public void setEndSignin(String endSignin) {
		this.endSignin = endSignin;
	}
}
