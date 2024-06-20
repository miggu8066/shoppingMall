package com.shop.web.dto;

public class LoginDto {
	private String userId;
	private String userPwd;
	
	public LoginDto() {
		// TODO Auto-generated constructor stub
	}

	public LoginDto(String userId, String userPwd) {
		this.userId = userId;
		this.userPwd = userPwd;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserPwd() {
		return userPwd;
	}

	public void setUserPwd(String userPwd) {
		this.userPwd = userPwd;
	}

	@Override
	public String toString() {
		return "LoginDto [userId=" + userId + ", userPwd=" + userPwd + "]";
	}
	
}
