package com.shop.web.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.shop.web.dto.LoginDto;

public class UserInfoInterceptor extends HandlerInterceptorAdapter{
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		HttpSession session = request.getSession(false);
		
		if(session != null && session.getAttribute("user") != null) {
			LoginDto sessionUser = (LoginDto) session.getAttribute("user");
			request.setAttribute("userName", sessionUser.getUserId());
			return true;
		} else if (request.getRequestURI().equals(request.getContextPath() + "/home/board/write")){
			return true;
		}
		
		return true;
	}
	
	
}
