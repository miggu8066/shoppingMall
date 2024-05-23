package com.shop.web.dao;

import java.util.List;

import com.shop.web.dto.BoardDto;

public interface BoardDao {
	void insertBoard(BoardDto boardDto, String loginDto);
	List<BoardDto> listBoard();
	BoardDto detailBoard(int userId);
	public int getUserkeyByUsername(String sessionId);
}