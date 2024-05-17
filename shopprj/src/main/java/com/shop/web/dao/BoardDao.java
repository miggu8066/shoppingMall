package com.shop.web.dao;

import java.util.List;

import com.shop.web.dto.BoardDto;

public interface BoardDao {
	void insertBoard(BoardDto boardDto);
	List<BoardDto> listBoard();
	BoardDto detailBoard(int userId);
}