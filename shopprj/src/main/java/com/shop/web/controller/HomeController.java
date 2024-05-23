package com.shop.web.controller;

import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.shop.web.dto.BoardDto;
import com.shop.web.dto.LoginDto;
import com.shop.web.dto.MemberDto;
import com.shop.web.service.BoardService;
import com.shop.web.service.LoginService;
import com.shop.web.service.MemberService;

@Controller
@RequestMapping("/home")
public class HomeController {
	
	@Autowired
	private MemberService memberService;
	
	@Autowired
	private BoardService boardService;
	
	@Autowired
	private LoginService loginService;
	
	@GetMapping("/main")
	public String main(HttpServletRequest request) {
		
		return "main";
	}
	
	@PostMapping("/register")
	public String register(@ModelAttribute MemberDto memberDto) {
		
		memberService.registerMember(memberDto);
		
		return "redirect:/home/main";
	}
	
	@PostMapping("/login")
	public String login(@ModelAttribute LoginDto loginDto, HttpServletRequest request, HttpServletResponse response, Model model) {
		LoginDto authenticatedUser = loginService.loginUser(loginDto);
		
		if(authenticatedUser != null && loginDto.getUserPwd().equals(authenticatedUser.getUserPwd())) {
			HttpSession session = request.getSession(true);
			session.setAttribute("user", authenticatedUser);
			session.setMaxInactiveInterval(1800);
			
			return "redirect:/home/main";
		} else {
            return "main";
		}
		
	}
	
	@GetMapping("/logout")
	public String logout(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession(false);
		
		if(session != null) {
			session.invalidate();
		}
		
		Cookie cookie = new Cookie("JSESSIONID", null);
		cookie.setMaxAge(0);
		cookie.setPath("/");
		response.addCookie(cookie);
		System.out.println("JSESSIONID cookie deleted.");
		
		return "redirect:/home/board";
	}
	
	@GetMapping("/board")
	public String board(HttpServletRequest request, Model model) {
		
		List<BoardDto> boardList = boardService.getBoardList();
		model.addAttribute("boardList", boardList);
		
		return "main.board.list";
	}
	
	@GetMapping("/board/write")
	public String boardWrite(HttpServletRequest request) {
		
		return "main.board.write";
	}
	
	@PostMapping("/board/writeProcess")
	public String writeProcess(HttpServletRequest request, @ModelAttribute BoardDto boardDto) {
		HttpSession session = request.getSession(false);
		
		if(session != null) {
			LoginDto loginDto = (LoginDto) session.getAttribute("user");
			if(loginDto != null) {
				boardService.writeBoard(boardDto, loginDto.getUserId());
				return "redirect:/home/board";
			}
		}
		
		return "redirect:/home/main";
	}
	
	@GetMapping("/board/detail")
	public String boardDetail(HttpServletRequest request, @RequestParam("uid") int userId, Model model) {
		int getFindKey = 0;
		HttpSession session = request.getSession(false);
		
		if(session != null) {
			LoginDto loginDto = (LoginDto) session.getAttribute("user");
			if(loginDto != null) {
				getFindKey = boardService.findUserkeyBySession(loginDto.getUserId(), userId);
				model.addAttribute("userName", loginDto.getUserId());
			}
		}
		
		
		BoardDto boardDetailList = boardService.getBoardByUserId(userId);
		model.addAttribute("boardDetail", boardDetailList);
		model.addAttribute("canEdit", getFindKey);


		return "main.board.detail";
		
	}
}
