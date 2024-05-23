package com.shop.web.service.Impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shop.web.dao.BoardDao;
import com.shop.web.dto.BoardDto;
import com.shop.web.service.BoardService;

@Service
public class BoardServiceImpl implements BoardService{
	
	@Autowired
	private BoardDao boardDao;

	@Override
	public void writeBoard(BoardDto boardDto, String loginDto) {
		Integer storedUserKey = boardDao.getUserkeyByUsername(loginDto);
		
		boardDto.setUserKey(storedUserKey);
		boardDto.setRegDate(new Date());
		boardDto.setDelState(1);
		boardDto.setImg("이미지 테스트");
		
		boardDao.insertBoard(boardDto, loginDto);
	}

	@Override
	public List<BoardDto> getBoardList() {

		
		return boardDao.listBoard();
	}

	@Override
	public BoardDto getBoardByUserId(int userId) {
		
		return boardDao.detailBoard(userId);
	}

	@Override
	public int findUserkeyBySession(String sessionId, int userId) {
		
		Integer storedUserKey = boardDao.getUserkeyByUsername(sessionId);
		
		if(storedUserKey != 0 && storedUserKey.equals(userId)) {
			return storedUserKey;
		}
		
		return storedUserKey;
	}


	
}
