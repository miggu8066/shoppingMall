package com.shop.web.dao.Impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.shop.web.dao.LoginDao;
import com.shop.web.dto.LoginDto;

@Repository
public class LoginDaoImpl implements LoginDao{
	
	@Autowired
	private DataSource dataSource;

	@Override
	public LoginDto findUserByMember(LoginDto loginDto) {
		String selectUserQuery = "SELECT * FROM member WHERE user_id = ? and user_pwd = ?";
		
		try {
			Connection conn = dataSource.getConnection();
			
			PreparedStatement pstmt = conn.prepareStatement(selectUserQuery);
			
			pstmt.setString(1, loginDto.getUserId());
			pstmt.setString(2, loginDto.getUserPwd());
			
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()) {
				String userId = rs.getString("user_id");
				String userPwd = rs.getString("user_pwd");
				
				return loginDto = new LoginDto(userId, userPwd);
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
		
	}
	
}
