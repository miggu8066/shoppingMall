package com.shop.web.page;

// 페이지 세팅 2024-06-22 이한결
public class Paging {
	// 현재 페이지 번호
	private int nowPage;
	// 한페이지당 보여줄 게시글 갯수
	private int perPageNum;
	
	// 기본 생성자 (기본 페이지 생성시)
	public Paging() {
		this.nowPage = 1;
		this.perPageNum = 10;
	}
	
	// 현재 페이지의 시작 행 번호를 반환
	public int getPageStart() {
		return (this.nowPage-1)*perPageNum;
	}
	
	public int getNowPage() {
		return nowPage;
	}
	
	// 현재 페이지 번호를 설정, 음수나 0일 경우 1로 설정
	public void setNowPage(int nowPage) {
		if(nowPage <= 0) {
			this.nowPage = 1;
		} else {
			this.nowPage = nowPage;
		}
	}

	public int getPerPageNum() {
		return perPageNum;
	}

	public void setPerPagenum(int pageCount) {
		int cnt = this.perPageNum;
		if(pageCount != cnt) {
			this.perPageNum = cnt;
		} else {
			this.perPageNum = pageCount;
		}
	}
	
	
}
