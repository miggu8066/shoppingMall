package com.shop.web.service;

import java.util.List;

import com.shop.web.dto.BoardDto;

public interface BoardService {
	public void writeBoard(BoardDto boardDto, String userName, List<String> uploadFileNames);
	List<BoardDto> getBoardList();
	BoardDto getBoardByUserId(int userId, int boardId);
	public int findUserkeyBySession(String sessionId, int userId);
	List<String> saveFiles(BoardDto boardDto);
	
}
