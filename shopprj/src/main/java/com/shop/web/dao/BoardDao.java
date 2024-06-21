package com.shop.web.dao;

import java.util.List;

import com.shop.web.dto.BoardDto;

public interface BoardDao {
	public void insertBoard(BoardDto boardDto, List<String> uploadFileNames);
	public List<BoardDto> listBoard();
	public BoardDto detailBoard(int userId, int boardId);
	public int getUserkeyByUsername(String sessionId);
	public void updateBoard(BoardDto boardDto, List<String> uploadFileNames);
	public void deleteBoardById(BoardDto boardDto);
}