<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script>
	function modifyContent(){
		
		if(confirm("수정하시겠습니까?")){
			document.getElementById("contentDisplay").style.display = "none";
			document.getElementById("modifyButton").style.display = "none";
			document.getElementById("deleteButton").style.display = "none";
			
			document.getElementById("editForm").style.display = "block";
			document.getElementById("saveButton").style.display = "inline-block";
		} else {
			return false;
		}
	}
	
	function saveContent() {
		var regDate = document.editForm.regDate.value;
		var title = document.editForm.title.value;
		var content = document.editForm.content.value;
		
		var img = document.editForm.img.files;
		var imgPv = document.getElementById("imagePreview");
		var imgTag = imgPv.getElementsByTagName("img");
		
		
		
		
		if(!regDate){
			alert("작성일자를 입력해주세요.");
			document.editForm.regDate.focus();
			return false;
		}
		
		if(!title){
			alert("제목을 입력해주세요.");
			document.editForm.title.focus();
			return false;
		}
		
		if(!content){
			alert("내용을 입력해주세요.");
			document.editForm.content.focus();
			return false;
		}
		
		// 기존이미지 는 있고 파일은 없을때
		if(imgTag.length > 0 && img.length <= 0){
			if(!confirm("기존이미지가 그대로 유지됍니다 진행하시겠습니까?")){
				return false;
			}
		}
		
		// 기존이미지 & 파일 둘다 없을때
		if(img.length <= 0 && imgTag.length <= 0){
			alert("파일을 선택해주세요.");
			document.editForm.img.focus();
			return false;
		} 
		
		if(confirm("수정완료 하시겠습니까?")){
			document.editForm.submit();
		}
		
	}

</script>
<h2>상세글</h2>

<div class="container text-right" >
	<%-- <c:if test="${canEdit == boardDetail.userKey}"> --%>
		<button id="modifyButton" name="modifyButton" class="btn btn-primary" onclick="modifyContent()">수정</button>
		<%-- <a href="/home/board/edit?uid=${boardDetail.userKey}" class="btn btn-primary" onclick="modifyContent()">수정</a> --%>
		<button id="deleteButton" name="deleteButton" class="btn btn-danger" onclick="">삭제</button>
		<%-- <a href="/home/board/delete?uid=${boardDetail.userKey}" class="btn btn-danger">삭제</a> --%>
		<button id="saveButton" name="saveButton" class="btn btn-success" onclick="saveContent()" style="display:none;">완료</button>
	<%-- </c:if> --%>
</div>

<div class="container">
	<div id="contentDisplay">
		<table class="table table-bordered">
			<colgroup>
				<col width="20%">
				<col width="20%">
				<col width="20%">
				<col width="20%">
			</colgroup>
			<tbody>
				<tr >
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
				    <td colspan="3">
			            <c:if test="${not empty boardDetail.db_img}">
			                <c:forEach var="imgPath" items="${boardDetail.db_img}">
			                    <c:choose>
			                        <c:when test="${not empty imgPath}">
			                            <img src="/files/boardFiles/${imgPath}" alt="이미지" />
			                        </c:when>
			                        <c:otherwise>
			                            상품 이미지가 없습니다.
			                        </c:otherwise>
			                    </c:choose>
			                </c:forEach>
			            </c:if>
				    </td>
				</tr>
			</tbody>
		</table>
	</div>


	<form id="editForm" name="editForm" method="post" action="/home/board/modifyProcess" enctype="multipart/form-data" style="display:none;" >
	    <input type="hidden" name="userKey" value="${boardDetail.userKey}" />
	    <input type="hidden" name="boardKey" value="${boardDetail.boardKey}" />
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
	                <td>
	                	${boardDetail.userName}
	                </td>
	                <th><span style="color: red;">*</span> 수정일자</th>
	                <td>
	                	<input type="hidden" class="form-control" name="regDate" value="${boardDetail.regDate}" />
	                	<span style="color: red;">현재 시간 기준</span>
                	</td>
	            </tr>
	            <tr>
	                <th><span style="color: red;">*</span> 제목</th>
	                <td colspan="3"><input type="text" class="form-control" name="title" value="${boardDetail.title}" /></td>
	            </tr>
	            <tr>
	                <th><span style="color: red;">*</span> 내용</th>
	                <td colspan="3"><textarea class="form-control" name="content" style="width:100%; height: 6.25em; resize: none;">${boardDetail.content}</textarea></td>
	            </tr>
	            <tr>
	                <th>상품이미지</th>
	                <td colspan="3">
	                    <input type="file" class="form-control" id="img" name="img" accept="image/*" onchange="previewImages(event)" multiple="multiple" />
	                    <div id="imagePreview">
	                        <c:if test="${not empty boardDetail.db_img}">
	                            <c:forEach var="imgPath" items="${boardDetail.db_img}">
	                            	<c:choose>
	                            		<c:when test="${not empty imgPath}">
	                            			<input type="hidden" name="existingImgs" value="${imgPath}" />
	                            			<img src="/files/boardFiles/${imgPath}" alt="이미지" />
	                            		</c:when>
	                            		<c:otherwise>
	                            			상품 이미지가 없습니다.
	                            		</c:otherwise>
	                            	</c:choose>
	                            </c:forEach>
	                        </c:if>
	                    </div>
	                </td>
	            </tr>
	        </tbody>
	    </table>
	</form>
</div>