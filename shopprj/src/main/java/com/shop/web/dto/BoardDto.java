package com.shop.web.dto;

import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

public class BoardDto {
	private int boardKey;
	private int userKey;
	private String title;
	private String content;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date regDate;
	
	private List<MultipartFile> img; // 파일 업로드
	private List<String> db_img; // 데이터베이스 조회 이미지 경로 저장용
	private int delState;
	private String userName;
	
	public BoardDto() {
		// TODO Auto-generated constructor stub
	}

	public BoardDto(int boardKey, int userKey, String title, String content, Date regDate, List<String> db_img) {
		super();
		this.boardKey = boardKey;
		this.userKey = userKey;
		this.title = title;
		this.content = content;
		this.regDate = regDate;
		this.db_img = db_img;
	}

	public BoardDto(int boardKey, int userKey, String title, Date regDate) {
		this.boardKey = boardKey;
		this.userKey = userKey;
		this.title = title;
		this.regDate = regDate;
	}

	/*
	 * public BoardDto(int userKey, String title, String content, Date regDate,
	 * List<MultipartFile> img, int delState) { this.userKey = userKey; this.title =
	 * title; this.content = content; this.regDate = regDate; this.img = img;
	 * this.delState = delState; }
	 */
	
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

	public List<MultipartFile> getImg() {
		return img;
	}

	public void setImg(List<MultipartFile> img) {
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
	

	public List<String> getDb_img() {
		return db_img;
	}

	public void setDb_img(List<String> db_img) {
		this.db_img = db_img;
	}

	@Override
	public String toString() {
		return "BoardDto [boardKey=" + boardKey + ", userKey=" + userKey + ", title=" + title + ", content=" + content
				+ ", regDate=" + regDate + ", img=" + img + ", delState=" + delState
				+ ", userName=" + userName + "]";
	}

	


	
	
}
