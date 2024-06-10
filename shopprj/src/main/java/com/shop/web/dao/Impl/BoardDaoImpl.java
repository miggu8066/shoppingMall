package com.shop.web.dao.Impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.shop.web.dao.BoardDao;
import com.shop.web.dto.BoardDto;

@Repository
public class BoardDaoImpl implements BoardDao{
	
	@Autowired
	private DataSource dataSource;
	
	String insertSql 
			= "INSERT INTO board"
			+ "("
			+ "user_key,"
			+ "board_title,"
			+ "board_content,"
			+ "board_img,"
			+ "board_regdate,"
			+ "delState"
			+ ") "
			+ " VALUE "
			+ "(?, ?, ?, ?, ?, ?)";
	
	String selectListSql
			= "SELECT * FROM board "
			+ "WHERE delState = ? "
			+ "ORDER BY board_regdate DESC";
	
	String selectMemberNameQuery
			= "SELECT * FROM member "
			+ "WHERE user_key = ? ";

	String getBoardByUserIdQuery = "SELECT * FROM board where user_key = ? and board_key = ?";
	
	String getUserkeyByUsernameQuery = "SELECT user_key FROM member where user_id = ?";
	
	@Override
	public void insertBoard(BoardDto boardDto, List<String> uploadFileNames) {
		try {
			Connection conn = dataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(insertSql);
			
			Date utilDate = boardDto.getRegDate();
			java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
			
			String joinedFileNames = String.join(", ", uploadFileNames);
			
			pstmt.setInt(1, boardDto.getUserKey());
			pstmt.setString(2, boardDto.getTitle());
			pstmt.setString(3, boardDto.getContent());
			pstmt.setString(4, joinedFileNames);
			pstmt.setDate(5, sqlDate);
			pstmt.setInt(6, boardDto.getDelState());
			
			pstmt.executeUpdate();
			
			pstmt.close();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public List<BoardDto> listBoard() {
		List<BoardDto> list = new ArrayList<>();
		
		try {
			Connection conn = dataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(selectListSql);
			pstmt.setInt(1, 1);
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				int boardKey 	= rs.getInt("board_key");
				int userKey 	= rs.getInt("user_key");
				String title 	= rs.getString("board_title");
				java.sql.Date regDate 	= rs.getDate("board_regdate");
				
				BoardDto boardDto = new BoardDto(boardKey, userKey, title, regDate);
				
				PreparedStatement Pstmt2 = conn.prepareStatement(selectMemberNameQuery);
				Pstmt2.setInt(1, userKey);
				ResultSet rs2 = Pstmt2.executeQuery();
				if(rs2.next()) {
					String userName = rs2.getString("user_name");
					boardDto.setUserName(userName);
				}
				
				list.add(boardDto);
				
				Pstmt2.close();
			}
			
			pstmt.close();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		return list;
	}

	@Override
	public BoardDto detailBoard(int userId, int boardId) {
		BoardDto boardDto = null;
		try {
			Connection conn = dataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(getBoardByUserIdQuery);
			
			pstmt.setInt(1, userId);
			pstmt.setInt(2, boardId);
			
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				int userkey = rs.getInt("user_key");
				String title = rs.getString("board_title");
				String content = rs.getString("board_content");
				java.sql.Date regDate 	= rs.getDate("board_regdate");
				String db_img = rs.getString("board_img");
				
				boardDto = new BoardDto(userkey, title, content, regDate, db_img);
				
				PreparedStatement Pstmt2 = conn.prepareStatement(selectMemberNameQuery);
				Pstmt2.setInt(1, userId);
				ResultSet rs2 = Pstmt2.executeQuery();
				if(rs2.next()) {
					String userName = rs2.getString("user_name");
					boardDto.setUserName(userName);
				}
				
				Pstmt2.close();
				
			}
			
			pstmt.close();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return boardDto;
	}

	@Override
	public int getUserkeyByUsername(String sessionId) {
		int getUserKey = 0;
		try {
			Connection conn = dataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(getUserkeyByUsernameQuery);
			pstmt.setString(1, sessionId);
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()) {
				getUserKey = rs.getInt("user_key");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return getUserKey;
	}

	

}
