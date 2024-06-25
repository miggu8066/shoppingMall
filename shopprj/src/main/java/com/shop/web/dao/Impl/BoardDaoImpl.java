package com.shop.web.dao.Impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
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
			
			String joinedFileNames = String.join(", ", uploadFileNames);
			
			pstmt.setInt(1, boardDto.getUserKey());
			pstmt.setString(2, boardDto.getTitle());
			pstmt.setString(3, boardDto.getContent());
			pstmt.setString(4, joinedFileNames);
			pstmt.setTimestamp(5, new Timestamp(boardDto.getRegDate().getTime()));
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
	public List<BoardDto> listBoard(int pageStart, int perPageNum, Integer searchType, String searchBoard) {
		List<BoardDto> list = new ArrayList<>();
		String realSearchType = null;
		int paramIndex = 1;
		StringBuilder selectListSql = new StringBuilder("SELECT * FROM board where delState = ? ");
		
		if(searchBoard != null && !searchBoard.isEmpty() && !searchBoard.trim().isEmpty()) {
			selectListSql.append("AND ? LIKE %?% ");
			
			if(searchType == 1) {
				realSearchType = "board_title";
			}
			
			if(searchType == 2) {
				realSearchType = "board_title";
			}
			
			/*if(searchType == 3) {
				realSearchType = "user_Key";
			}*/
		}
		
		if(searchType == 1 || searchType == 2 || searchType == 3) {
			selectListSql.append("ORDER BY board_regdate DESC LIMIT ?, ?");
		}
		
		try {
			Connection conn = dataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(selectListSql.toString());
			
			pstmt.setInt(paramIndex++, 1);
			
			if(realSearchType != null) {
				pstmt.setString(paramIndex++, realSearchType);
				pstmt.setString(paramIndex++, searchBoard);
			}
			
			pstmt.setInt(paramIndex++, pageStart);
			pstmt.setInt(paramIndex++, perPageNum);
			
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
				int boardKey = rs.getInt("board_key");
				int userkey = rs.getInt("user_key");
				String title = rs.getString("board_title");
				String content = rs.getString("board_content");
				java.sql.Date regDate 	= rs.getDate("board_regdate");
				String db_img = rs.getString("board_img");
				
				List<String> imgList = new ArrayList<>();
				for(String img : db_img.split(",")) {
					imgList.add(img);
				}
				
				boardDto = new BoardDto(boardKey, userkey, title, content, regDate, imgList);
				 
				PreparedStatement Pstmt2 = conn.prepareStatement(selectMemberNameQuery);
				Pstmt2.setInt(1, userId);
				ResultSet rs2 = Pstmt2.executeQuery();
				if (rs2.next()) {
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

	// 게시글 수정 Dao 구현체 2024-06-20 이한결
	@Override
	public void updateBoard(BoardDto boardDto, List<String> uploadFileNames) {
		try {
			StringBuilder updateQuery = new StringBuilder("UPDATE board SET board_moddate = ?, board_title = ?, board_content = ?");
			String joinedFileNames = null;
			int paramIndex = 1;
			
			if(!uploadFileNames.isEmpty()) {
				updateQuery.append(", board_img = ?");
				joinedFileNames = String.join(", ", uploadFileNames);
			}
			
			updateQuery.append(" WHERE board_key = ? and user_key = ?");
			
			Connection conn = dataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(updateQuery.toString());
			
			pstmt.setTimestamp(paramIndex++, new Timestamp(boardDto.getRegDate().getTime()));
			pstmt.setString(paramIndex++, boardDto.getTitle());
			pstmt.setString(paramIndex++, boardDto.getContent());
			
			if(!uploadFileNames.isEmpty()) {
				pstmt.setString(paramIndex++, joinedFileNames);
			}
			
			pstmt.setInt(paramIndex++, boardDto.getBoardKey());
			pstmt.setInt(paramIndex++, boardDto.getUserKey());
			
			pstmt.executeUpdate();
			
			pstmt.close();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	// 게시글 삭제 Dao 구현체 (delState = 0) 2024-06-21 이한결
	@Override
	public void deleteBoardById(BoardDto boardDto) {
		
		String deleteBoardQuery = "UPDATE board SET delState = ? WHERE board_key = ? and user_key = ?";
		
		try {
			Connection conn = dataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(deleteBoardQuery);
			
			pstmt.setInt(1, 0);
			pstmt.setInt(2, boardDto.getBoardKey());
			pstmt.setInt(3, boardDto.getUserKey());
			
			pstmt.executeUpdate();
			
			pstmt.close();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

	@Override
	public int selectTotalBoardCount() {
		int count = 0;
		
		String selectTotalBoardCountQuery = "SELECT COUNT(*) FROM board WHERE delState = ? ";
		
		try {
			Connection conn = dataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(selectTotalBoardCountQuery);
			
			pstmt.setInt(1, 1);
			
			ResultSet rs = pstmt.executeQuery();
			
			if(rs.next()) {
				count = rs.getInt("COUNT(*)");
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return count;
	}

	

}
