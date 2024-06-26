package com.shop.web.controller;

import java.util.ArrayList;
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
import com.shop.web.page.PageMaker;
import com.shop.web.page.Paging;
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
	public String login(@ModelAttribute LoginDto loginDto, 
			HttpServletRequest request, 
			HttpServletResponse response, 
			Model model) {
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
	public String board(@ModelAttribute Paging paging, 
			Model model, 
			@RequestParam(value = "searchType", required = false, defaultValue = "0") int searchType, 
			@RequestParam(value = "searchBoard", required = false) String searchBoard) {
		
		// 게시글 리스트 가져오기(시작행 번호와 한페이지당 보여줄 게시글 수 기준)
		List<BoardDto> boardList = boardService.getBoardList(paging.getPageStart(), paging.getPerPageNum(), searchType, searchBoard);
		model.addAttribute("boardList", boardList);
		
		PageMaker pageMaker = new PageMaker();
		pageMaker.setCri(paging);
		
		int totalCount = boardService.getTotalBoardCount(searchType, searchBoard);
		pageMaker.setTotalCount(totalCount);
		
		model.addAttribute("pageMaker", pageMaker);
		model.addAttribute("searchType", searchType);
		model.addAttribute("searchBoard", searchBoard);
		
		return "main.board.list";
	}
	
	@GetMapping("/board/write")
	public String boardWrite(HttpServletRequest request, Model model) {
		
		return "main.board.write";
	}
	
	@PostMapping("/board/writeProcess")
	public String writeProcess(HttpServletRequest request, @ModelAttribute BoardDto boardDto) {
		String userName = (String) request.getAttribute("userName");

		if(userName == null) { 
			return "redirect:/home/main"; 
		} else {
			
			List<String> uploadFileNames = boardService.saveFiles(boardDto);
			
			boardService.writeBoard(boardDto, userName, uploadFileNames);
			
			return "redirect:/home/board";
		}
	}
	
	@PostMapping("/board/detail")
	public String boardDetail(HttpServletRequest request, 
			@RequestParam("uid") int userId, @RequestParam("bid") int boardId, 
			Model model) {
		int getFindKey = 0;
		
		String userName = (String) request.getAttribute("userName");

		getFindKey = boardService.findUserkeyBySession(userName, userId);
		
		BoardDto boardDetailList = boardService.getBoardByUserId(userId, boardId);
		
		model.addAttribute("boardDetail", boardDetailList);
		model.addAttribute("canEdit", getFindKey);

		return "main.board.detail";
		
	}
	
	// 게시글 수정 프로세스
	@PostMapping("/board/modifyProcess")
    public String boardModifyProcess(
    		@ModelAttribute BoardDto boardDto,
    		@RequestParam(value = "existingImgs", required = false) String existingImages) {
		List<String> uploadFileNames = new ArrayList<>();
        
        /*SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String formattedDate = sdf.format(regDate);*/
		
		if(existingImages == null) {
			uploadFileNames = boardService.saveFiles(boardDto);
		}
		boardService.modifyBoard(boardDto, uploadFileNames);

        return "redirect:/home/board";
    }
	
	// 게시글 삭제 프로세스
	@PostMapping("/board/deleteProcess")
	public String boardDeleteProcess(HttpServletRequest request, @ModelAttribute BoardDto boardDto) {
		String userName = (String) request.getAttribute("userName");
		
		if(userName == null) {
			return "redirect:/home/main";
		}
		
		boardService.deleteBoard(boardDto);
		
		return "redirect:/home/board";
	}
}
