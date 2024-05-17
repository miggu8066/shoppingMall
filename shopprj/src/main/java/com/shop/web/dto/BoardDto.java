package com.shop.web.dto;

import java.util.Date;

public class BoardDto {
	private int boardKey;
	private int userKey;
	private String title;
	private String content;
	private Date regDate;
	private String img;
	private int delState;
	private String userName;
	
	public BoardDto() {
		// TODO Auto-generated constructor stub
	}
	
	
	
	public BoardDto(int userKey, String title, String content, Date regDate, String img) {
		super();
		this.userKey = userKey;
		this.title = title;
		this.content = content;
		this.regDate = regDate;
		this.img = img;
	}



	public BoardDto(int boardKey, int userKey, String title, Date regDate) {
		super();
		this.boardKey = boardKey;
		this.userKey = userKey;
		this.title = title;
		this.regDate = regDate;
	}

	public BoardDto(int userKey, String title, String content, Date regDate, String img, int delState) {
		this.userKey = userKey;
		this.title = title;
		this.content = content;
		this.regDate = regDate;
		this.img = img;
		this.delState = delState;
	}
	
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public int getUserKey() {
		return userKey;
	}

	public void setUserKey(int userKey) {
		this.userKey = userKey;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getRegDate() {
		return regDate;
	}

	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	public int getDelState() {
		return delState;
	}

	public void setDelState(int delState) {
		this.delState = delState;
	}
	
	
	
	public int getBoardKey() {
		return boardKey;
	}


	public void setBoardKey(int boardKey) {
		this.boardKey = boardKey;
	}


	@Override
	public String toString() {
		return "BoardDto [userKey=" + userKey + ", title=" + title + ", content=" + content + ", regDate=" + regDate
				+ ", img=" + img + ", delState=" + delState + "]";
	}


	
	
}
