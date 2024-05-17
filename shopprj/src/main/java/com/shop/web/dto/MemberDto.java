package com.shop.web.dto;

import java.util.Date;

public class MemberDto {
	private String name;
	private String id;
	private String pwd;
	private String email;
	private Date date;
	private int delState;
	
	public MemberDto() {
		// TODO Auto-generated constructor stub
	}

	public MemberDto(String name, String id, String pwd, String email, Date date, int delState) {
		this.name = name;
		this.id = id;
		this.pwd = pwd;
		this.email = email;
		this.date = date;
		this.delState = delState;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public int getDelState() {
		return delState;
	}

	public void setDelState(int delState) {
		this.delState = delState;
	}

	@Override
	public String toString() {
		return "MemberDto [name=" + name + ", id=" + id + ", pwd=" + pwd + ", email=" + email + ", date=" + date
				+ ", delState=" + delState + "]";
	}
	
	
}
