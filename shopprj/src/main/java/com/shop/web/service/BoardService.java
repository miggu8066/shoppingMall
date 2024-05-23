package com.shop.web.service;

import java.util.List;

import com.shop.web.dto.BoardDto;

public interface BoardService {
	public void writeBoard(BoardDto boardDto, String loginDto);
	List<BoardDto> getBoardList();
	BoardDto getBoardByUserId(int userId);
	public int findUserkeyBySession(String sessionId, int userId);
	
}
