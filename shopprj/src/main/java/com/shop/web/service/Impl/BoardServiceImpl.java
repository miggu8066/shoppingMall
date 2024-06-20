package com.shop.web.service.Impl;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.shop.web.dao.BoardDao;
import com.shop.web.dto.BoardDto;
import com.shop.web.service.BoardService;

@Service
public class BoardServiceImpl implements BoardService{
	
	@Autowired
	private BoardDao boardDao;

	@Override
	public void writeBoard(BoardDto boardDto, String userName, List<String> uploadFileNames) {
		// 유저 키 가져오기
		Integer storedUserKey = boardDao.getUserkeyByUsername(userName);
		
		// 게시글 정보 설정
		boardDto.setUserKey(storedUserKey); 
		boardDto.setRegDate(new Date());
		boardDto.setDelState(1);

		
		boardDao.insertBoard(boardDto, uploadFileNames);
	}

	@Override
	public List<BoardDto> getBoardList() {

		
		return boardDao.listBoard();
	}

	@Override
	public BoardDto getBoardByUserId(int userId, int boardId) {
		BoardDto boarddto = boardDao.detailBoard(userId, boardId);
		
		return boarddto;
	}

	@Override
	public int findUserkeyBySession(String sessionId, int userId) {
		
		Integer storedUserKey = boardDao.getUserkeyByUsername(sessionId);
		
		if(storedUserKey != 0 && storedUserKey.equals(userId)) {
			return storedUserKey;
		}
		
		return storedUserKey;
	}

	@Override
	public List<String> saveFiles(BoardDto boardDto) {
		List<MultipartFile> imgFiles =  boardDto.getImg();
		List<String> uploadFileNames = new ArrayList<>();
		
		String uploadFolder = "C:\\Users\\gksru\\git\\shoppingMall\\shopprj\\src\\main\\webapp\\static\\files\\boardFiles";
		
		
		for(MultipartFile file : imgFiles) {
			if(!file.isEmpty()) {
				String fileRealName = file.getOriginalFilename();
				long size = file.getSize();
				
				UUID uuid = UUID.randomUUID();
				String[] uuids = uuid.toString().split("-");
				String uniqueUuids = uuids[0];
				
				String fileExtension = fileRealName.substring(fileRealName.lastIndexOf("."), fileRealName.length());
				String uploadFilePath = uploadFolder + "\\" + uniqueUuids + fileExtension;
				String uniqueName = uniqueUuids + fileExtension;

				try {
					File saveFiles = new File(uploadFilePath);
					file.transferTo(saveFiles);
					uploadFileNames.add(uniqueName);
				} catch (IllegalStateException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			} else {
				System.out.println("파일이 없습니다 : " + file.getOriginalFilename());
			}
		}
		
		return uploadFileNames;
	}

	// 게시판 수정 serviceImpl 2024-06-19 이한결
	@Override
	public void modifyBoard(BoardDto boardDto, List<String> uploadFileNames) {
		
		boardDto.setRegDate(new Date());
		
		boardDao.updateBoard(boardDto, uploadFileNames);
	}

}
