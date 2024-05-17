package com.shop.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.shop.web.dto.BoardDto;
import com.shop.web.dto.MemberDto;
import com.shop.web.service.BoardService;
import com.shop.web.service.MemberService;

@Controller
@RequestMapping("/home")
public class HomeController {
	
	@Autowired
	private MemberService memberService;
	
	@Autowired
	private BoardService boardService;
	
	@GetMapping("/main")
	public String main() {
		
		return "main";
	}
	
	@PostMapping("/register")
	public String register(@ModelAttribute MemberDto memberDto) {
		System.out.println("email : " + memberDto.getEmail());
		System.out.println("name : " + memberDto.getName());
		System.out.println("Id : " + memberDto.getId());
		System.out.println("pwd : " + memberDto.getPwd());
		
		memberService.registerMember(memberDto);
		
		return "redirect:/home/main";
	}
	
	@GetMapping("/board")
	public String board(Model model) {
		
		List<BoardDto> boardList = boardService.getBoardList();
		model.addAttribute("boardList", boardList);
		
		return "board.list";
	}
	
	@GetMapping("/board/write")
	public String boardWrite() {
		
		return "board.write";
	}
	
	@PostMapping("/board/writeProcess")
	public String writeProcess(@ModelAttribute BoardDto boardDto) {
		System.out.println("title : " + boardDto.getTitle());
		System.out.println("content : " + boardDto.getContent());
		
		boardService.writeBoard(boardDto);
		
		return "redirect:/home/board";
	}
	
	@GetMapping("/board/detail")
	public String boardDetail(@RequestParam("uid") int userId, Model model) {
		
		BoardDto boardDetailList = boardService.getBoardByUserId(userId);
		model.addAttribute("boardDetail", boardDetailList);

		return "board.detail";
		
	}
}
