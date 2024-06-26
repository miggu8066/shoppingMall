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
	public List<BoardDto> listBoard(int pageStart, int perPageNum, int searchType, String searchBoard) {
		List<BoardDto> list = new ArrayList<>();
		int paramIndex = 1;
		StringBuilder selectListSql = new StringBuilder(
				"SELECT b.*, m.user_name " + 
				"FROM board b LEFT JOIN member m " + 
				"ON b.user_key = m.user_key " + 
				"WHERE b.delState = ? ");
		
		if(searchBoard != null && !searchBoard.trim().isEmpty()) {
			if(searchType == 2) {
				selectListSql.append("AND b.board_title LIKE ? ");
			} else if(searchType == 3) {
				selectListSql.append("AND m.user_name LIKE ? ");
			} else {
				selectListSql.append("AND (b.board_title LIKE ? OR m.user_name LIKE ?) ");
			}
		}
		
		selectListSql.append("ORDER BY b.board_regdate DESC LIMIT ?, ? ");
		
		try {
			Connection conn = dataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(selectListSql.toString());
			
			pstmt.setInt(paramIndex++, 1);
			
			if(searchBoard != null && !searchBoard.trim().isEmpty()) {
				String searchWord = "%" + searchBoard.trim() + "%";
				if(searchType == 2 || searchType == 3) {
					pstmt.setString(paramIndex++, searchWord);
				} else {
					pstmt.setString(paramIndex++, searchWord);
					pstmt.setString(paramIndex++, searchWord);
				}
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
	public int selectTotalBoardCount(int searchType, String searchBoard) {
		int count = 0;
		int paramIndex = 1;
		
		StringBuilder selectTotalBoardCountQuery = new StringBuilder("SELECT COUNT(*) FROM board b LEFT JOIN member m ON b.user_key = m.user_key WHERE b.delState = ? ");
		
		if(searchBoard != null && !searchBoard.trim().isEmpty()) {
			if(searchType == 2) {
				selectTotalBoardCountQuery.append("AND b.board_title LIKE ? ");
			} else if(searchType == 3) {
				selectTotalBoardCountQuery.append("AND m.user_name LIKE ? ");
			} else {
				selectTotalBoardCountQuery.append("AND (b.board_title LIKE ? OR m.user_name LIKE ?) ");
			}
		}
		
		
		try {
			Connection conn = dataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(selectTotalBoardCountQuery.toString());
			
			pstmt.setInt(paramIndex++, 1);
			if(searchBoard != null && !searchBoard.trim().isEmpty()) {
				String searchWord = "%" + searchBoard.trim() + "%";
				if(searchType == 2) {
					pstmt.setString(paramIndex++, searchWord);
				} else if(searchType == 3) {
					pstmt.setString(paramIndex++, searchWord);
				} else {
					pstmt.setString(paramIndex++, searchWord);
					pstmt.setString(paramIndex++, searchWord);
				}
			}
			
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
