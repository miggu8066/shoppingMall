package com.shop.web.service;

import java.util.List;

import com.shop.web.dto.BoardDto;

public interface BoardService {
	public void writeBoard(BoardDto boardDto, String userName, List<String> uploadFileNames);
	public List<BoardDto> getBoardList();
	public BoardDto getBoardByUserId(int userId, int boardId);
	public int findUserkeyBySession(String sessionId, int userId);
	public List<String> saveFiles(BoardDto boardDto);
	public void modifyBoard(BoardDto boarDto, List<String> uploadFileNames);
	
}
