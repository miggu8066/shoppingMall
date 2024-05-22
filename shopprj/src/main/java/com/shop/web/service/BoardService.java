package com.shop.web.service;

import java.util.List;

import com.shop.web.dto.BoardDto;

public interface BoardService {
	void writeBoard(BoardDto boardDto);
	List<BoardDto> getBoardList();
	BoardDto getBoardByUserId(int userId);
	boolean findUserkeyBySession(String sessionId, int userId);
	
}
