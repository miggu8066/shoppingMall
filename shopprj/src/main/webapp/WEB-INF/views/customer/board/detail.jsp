<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>

<h2>상세글</h2>

<div class="container">
	<table class="table table-bordered">
		<colgroup>
			<col width="20%">
			<col width="20%">
			<col width="20%">
			<col width="20%">
		</colgroup>
		<tbody>
			<tr>
				<th>작성자</th>
				<td>${boardDetail.userName}</td>
				<th>작성일자</th>
				<td>${boardDetail.regDate}</td>
			</tr>
			<tr>
				<th>제목</th>
				<td colspan="3">${boardDetail.title}</td>
			</tr>
			<tr>
				<th>내용</th>
				<td colspan="3">${boardDetail.content}</td>
			</tr>
			<tr>
				<th>상품이미지</th>
				<td colspan="3">${boardDetail.img}</td>
			</tr>
			<c:choose>
				<c:when test="${canEdit}">
					<a href="/board/edit?uid=${boardDetail.id}" class="btn btn-primary">수정</a>
					<a href="/board/delete?uid=${boardDetail.id}" class="btn btn-danger">삭제</a>
				</c:when>
				<c:otherwise>
					<span>수정 및 삭제 권한이 없습니다.</span>
				</c:otherwise>
			</c:choose>
		</tbody>
	</table>
</div>