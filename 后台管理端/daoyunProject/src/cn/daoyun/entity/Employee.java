package cn.daoyun.entity;

import java.util.Date;


/**
 *员工实体类 封装了 员工信息
 * @author cdfengyang
 *
 */
public class Employee {

	private int employeeId;
	private String employeeNo;
	private String name;
	private String sex;
	private Date birthday;
	private String nationality;
	private String education;
	private String profession;
	private int departmentId=-1;
	private String position;
	private float baseMoney;
	private float overtime;
	private float age;
	private float check1;
	private float absent;
	private float safety;
	private String departmentNameSrc;
	
	public int getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}
	public String getEmployeeNo() {
		return employeeNo;
	}
	public void setEmployeeNo(String employeeNo) {
		this.employeeNo = employeeNo;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	public String getNationality() {
		return nationality;
	}
	public void setNationality(String nationality) {
		this.nationality = nationality;
	}
	public String getEducation() {
		return education;
	}
	public void setEducation(String education) {
		this.education = education;
	}
	public String getProfession() {
		return profession;
	}
	public void setProfession(String profession) {
		this.profession = profession;
	}
	public int getDepartmentId() {
		return departmentId;
	}
	public void setDepartmentId(int departmentId) {
		this.departmentId = departmentId;
	}
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	public float getBaseMoney() {
		return baseMoney;
	}
	public void setBaseMoney(float baseMoney) {
		this.baseMoney = baseMoney;
	}
	public float getOvertime() {
		return overtime;
	}
	public void setOvertime(float overtime) {
		this.overtime = overtime;
	}
	public float getAge() {
		return age;
	}
	public void setAge(float age) {
		this.age = age;
	}
	public float getCheck1() {
		return check1;
	}
	public void setCheck1(float check1) {
		this.check1 = check1;
	}
	public float getAbsent() {
		return absent;
	}
	public void setAbsent(float absent) {
		this.absent = absent;
	}
	public float getSafety() {
		return safety;
	}
	public void setSafety(float safety) {
		this.safety = safety;
	}
	public String getDepartmentNameSrc() {
		return departmentNameSrc;
	}
	public void setDepartmentNameSrc(String departmentNameSrc) {
		this.departmentNameSrc = departmentNameSrc;
	}
	
	
}
