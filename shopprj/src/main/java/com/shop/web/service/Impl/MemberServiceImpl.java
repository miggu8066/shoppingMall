package com.shop.web.service.Impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shop.web.dao.MemberDao;
import com.shop.web.dto.MemberDto;
import com.shop.web.service.MemberService;

@Service
public class MemberServiceImpl implements MemberService{
	
	@Autowired
	private MemberDao memberDao;
	
	
	public void registerMember(MemberDto memberDto) {
		memberDto.setDate(new Date());
		memberDto.setDelState(1);
		memberDao.insertMember(memberDto);
	}
}
