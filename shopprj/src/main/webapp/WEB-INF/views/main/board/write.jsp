<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:if test="${empty userName}">
	<script type="text/javascript">
		alert("로그인이 필요합니다.");
		window.location.href = "/home/main";
	</script>
</c:if>

<form name="writeForm" method="post" action="/home/board/writeProcess" enctype="multipart/form-data">
	<div class="container">
		<div>
			<h2 style="margin-top: 15px;">글쓰기</h2>
		</div>
		<div class="mb-3">
			<h4>
				<label for="제목" class="form-label">제목</label>
			</h4>
			<input type="text" class="form-control" id="title" name="title" placeholder="제목">
		</div>
		
		<div class="mb-3">
			<label for="내용" class="form-label">내용</label>
			<textarea id="content" name="content" class="form-control" placeholder="내용" style="height: 500px; resize: none;"></textarea>
		</div>
		
		<div>
			<label for="img" class="form-label">이미지 업로드</label>
			<input type="file" id="img" name="img" accept="image/*" class="form-control" onchange="previewImages(event)" multiple="multiple"/>
		</div>
		
		<div id="imagePreview">
			<h4>이미지 미리보기</h4>
		</div>
		
		<div class="row">
			<div class="col-md-12 text-right" style="margin-top: 15px;">
				<input type="button" value="등록" onclick="fnDoWrite()" class="btn btn-success"/>	
			</div>
		</div>
	</div>
</form>


