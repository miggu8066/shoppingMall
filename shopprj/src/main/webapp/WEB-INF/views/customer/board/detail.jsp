<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
		</tbody>
	</table>
</div>