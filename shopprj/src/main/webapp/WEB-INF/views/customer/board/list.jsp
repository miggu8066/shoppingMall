<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<h2 class="section-title" >product board</h2>
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
		<c:forEach items="${boardList}" var="board">
			<tr>
				<td>${board.boardKey}</td>
				<td>
					<a href="/home/board/detail?uid=${board.userKey}">${board.title}</a>
				</td>
				<td>${board.userName}</td> 
				<td>${board.regDate}</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
</div>
<div class="container">
    <div class="row">
        <div class="col-md-12 text-right"> <!-- 오른쪽 정렬 -->
            <input type="button" name="" value="글쓰기" onclick="fnBoardWrite();" class="btn btn-secondary"/>	
        </div>
    </div>
</div>
