package com.shop.web.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shop.web.dao.LoginDao;
import com.shop.web.dto.LoginDto;
import com.shop.web.service.LoginService;

@Service
public class LoginServiceImpl implements LoginService{
	
	@Autowired
	private LoginDao loginDao;

	@Override
	public LoginDto loginUser(LoginDto loginDto) {
		
		return loginDao.findUserByMember(loginDto);
	}

}
