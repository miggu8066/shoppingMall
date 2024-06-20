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
<h2 class="section-title" >product board</h2>

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
			</tbody>
		</table>
	</div>
</form>

<div class="container">
    <div class="row">
        <div class="col-md-12 text-right"> <!-- 오른쪽 정렬 -->
            <input type="button" name="" value="글쓰기" onclick="fnBoardWrite();" class="btn btn-secondary"/>	
        </div>
    </div>
</div>
