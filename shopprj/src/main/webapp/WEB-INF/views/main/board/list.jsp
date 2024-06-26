<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<script>
    function submitDetailForm(uid, bid) {
    	document.getElementById("uid").value = uid;
    	document.getElementById("bid").value = bid;
        document.getElementById("listForm").submit();
    }
    
    
</script>


<form id="searchForm" name="searchForm" method="get" action="/home/board" class="form-inline">
	<div class="container text-right" style="margin-top : 20px;">
		<select name="searchType" class="form-control">
			<option value="1" ${searchType == 1 ? 'selected' : ''}>전체</option>
			<option value="2" ${searchType == 2 ? 'selected' : ''}>제목</option>
			<option value="3" ${searchType == 3 ? 'selected' : ''}>작성자</option>
		</select>
		<input type="text" id="searchBoard" name="searchBoard" class="form-control" value="${searchBoard != null ? searchBoard : ''}"  placeholder="검색어를 입력하세요" />
		<input type="submit" value="검색" />
	</div>
</form>

<form id="listForm" method="post" action="/home/board/detail" >
	<input type="hidden" id="uid" name="uid"/>
	<input type="hidden" id="bid" name="bid"/>
	<div class="container">
		<table class="table">
			<colgroup>
				<col width="5%">
				<col width="20%">
				<col width="5%">
				<col width="5%">
			</colgroup>
			<thead>
				<tr>
					<th>번호</th>
					<th>제목</th>
					<th>작성자</th>
					<th>작성일자</th>
				</tr>
			</thead>
			<tbody>
			<c:choose>
				<c:when test="${empty boardList}">
					<tr>
						<td colspan="4" style="text-align: center;">
							<span style="color: red;">검색어에 해당되는 게시글이 없습니다.</span>
						</td>
					</tr>
				</c:when>
				<c:otherwise>
					<c:forEach items="${boardList}" var="board">
						<tr>
							<td>
								${board.boardKey}
							</td>
							<td>
								<a href="javascript:void(0);" onclick="submitDetailForm(${board.userKey}, ${board.boardKey})">${board.title}</a>
							</td>
							<td>${board.userName}</td> 
							<td>${board.regDate}</td>
						</tr>
					</c:forEach>
				</c:otherwise>
			</c:choose>
			</tbody>
		</table>
	</div>
</form>

<div class="container text-center">
	<ul class="btn-group pagination">
	    <c:if test="${pageMaker.prev}">
	        <li>
	        	<a href='<c:url value="/home/board?nowPage=1&searchType=${searchType}&searchBoard=${searchBoard}"/>'><i class="fa fa-angle-double-left"></i></a>
	            <a href='<c:url value="/home/board?nowPage=${pageMaker.startPage-1}&searchType=${searchType}&searchBoard=${searchBoard}"/>'><i class="fa fa-chevron-left"> 이전</i></a>
	        </li>
	    </c:if>
	    <c:forEach begin="${pageMaker.startPage}" end="${pageMaker.endPage}" var="pageNum">
	        <li class="page-item ${pageNum == pageMaker.cri.nowPage ? 'active' : ''}">
	            <a href='<c:url value="/home/board?nowPage=${pageNum}&searchType=${searchType}&searchBoard=${searchBoard}"/>'><i class="fa">${pageNum}</i></a>
	        </li>
	    </c:forEach>
	    <c:if test="${pageMaker.next && pageMaker.endPage > 0}">
	        <li>
	            <a href='<c:url value="/home/board?nowPage=${pageMaker.endPage+1}&searchType=${searchType}&searchBoard=${searchBoard}"/>'><i class="fa fa-chevron-right"> 다음</i></a>
	            <a href='<c:url value="/home/board?nowPage=${pageMaker.tempEndPage}&searchType=${searchType}&searchBoard=${searchBoard}"/>'><i class="fa fa-angle-double-right"></i></a>
	        </li>
	    </c:if>
	</ul>
</div>

<div class="container">
    <div class="row">
        <div class="col-md-12 text-right"> <!-- 오른쪽 정렬 -->
            <input type="button" name="" value="글쓰기" onclick="fnBoardWrite();" class="btn btn-secondary"/>	
        </div>
    </div>
</div>
