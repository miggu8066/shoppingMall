package com.shop.web.dao.Impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.shop.web.dao.MemberDao;
import com.shop.web.dto.MemberDto;

@Repository
public class MemberDaoImpl implements MemberDao{
	
	@Autowired
	private DataSource dataSource;
	
	public void insertMember(MemberDto memberDto) {
		String sql = 
				"INSERT INTO member "
				+ "(user_name, user_id, user_pwd, user_email, create_date, delState) "
				+ "VALUE"
				+ "(?, ?, ?, ?, ?, ?)";
		
		try {
			Connection conn = dataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, memberDto.getName());
			pstmt.setString(2, memberDto.getId());
			pstmt.setString(3, memberDto.getPwd());
			pstmt.setString(4, memberDto.getEmail());
			pstmt.setTimestamp(5, new Timestamp(memberDto.getDate().getTime()));
			pstmt.setInt(6, memberDto.getDelState());
			pstmt.executeUpdate();
			
			pstmt.close();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	
}



//public void checkConnection() {
//try (Connection conn = dataSource.getConnection()) {
//	if (conn != null) {
//        System.out.println("데이터베이스 연결 성공!");
//    } else {
//        System.out.println("데이터베이스 연결 실패.");
//    }
//} catch (SQLException e) {
//	// TODO Auto-generated catch block
//	e.printStackTrace();
//}
//}