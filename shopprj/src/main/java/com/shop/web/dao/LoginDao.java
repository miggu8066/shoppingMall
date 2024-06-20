package com.shop.web.dao;

import com.shop.web.dto.LoginDto;

public interface LoginDao {
	public LoginDto findUserByMember(LoginDto loginDto);
}
