package com.shop.web.dao;

import java.util.List;

import com.shop.web.dto.BoardDto;

public interface BoardDao {
	void insertBoard(BoardDto boardDto, List<String> uploadFileNames);
	List<BoardDto> listBoard();
	BoardDto detailBoard(int userId, int boardId);
	public int getUserkeyByUsername(String sessionId);
}