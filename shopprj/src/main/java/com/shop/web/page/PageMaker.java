package com.shop.web.page;

//페이지 버튼 기능 계산 2024-06-22 이한결
public class PageMaker {
	private Paging cri;
	private int totalCount;
	private int startPage;
	private int endPage;
	private boolean prev;
	private boolean next;
	private int displayPageNum = 10;
	private int tempEndPage;
	
	public Paging getCri() {
		return cri;
	}
	public void setCri(Paging cri) {
		this.cri = cri;
	}
	public int getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
		calcData();
	}
	
	private void calcData() {
		endPage = (int) (Math.ceil(cri.getNowPage() / (double) displayPageNum) * displayPageNum);
		
		startPage = (endPage - displayPageNum) + 1;
		if(startPage <= 0)
			startPage = 1;
		
		tempEndPage = (int) (Math.ceil(totalCount / (double) cri.getPerPageNum()));

		if(endPage > tempEndPage) {
			endPage = tempEndPage;
		}
		
		prev = startPage == 1 ? false : true;
		next = endPage * cri.getPerPageNum() < totalCount ? true : false;
	}
	
	public int getStartPage() {
		return startPage;
	}
	public void setStartPage(int startPage) {
		this.startPage = startPage;
	}
	public int getEndPage() {
		return endPage;
	}
	public void setEndPage(int endPage) {
		this.endPage = endPage;
	}
	public boolean isPrev() {
		return prev;
	}
	public void setPrev(boolean prev) {
		this.prev = prev;
	}
	public boolean isNext() {
		return next;
	}
	public void setNext(boolean next) {
		this.next = next;
	}
	public int getDisplayPageNum() {
		return displayPageNum;
	}
	public void setDisplayPageNum(int displayPageNum) {
		this.displayPageNum = displayPageNum;
	}
	public int getTempEndPage() {
		return tempEndPage;
	}
	public void setTempEndPage(int tempEndPage) {
		this.tempEndPage = tempEndPage;
	}
	
	
	
}
