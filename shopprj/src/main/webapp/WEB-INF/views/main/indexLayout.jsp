<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<!DOCTYPE html>
<html>
<head>
	
	<tiles:insertAttribute name="metaData" />
	
<script>

	function fnRegister(){
		var email = document.registerForm.email.value;
		var name = document.registerForm.name.value;
		var id = document.registerForm.id.value;
		var pwd = document.registerForm.pwd.value;
		
		if(!email){
			document.registerForm.email.focus();
			alert("이메일을 입력해주세요.");
            return false; 
		}
		
		if(!name){
			document.registerForm.name.focus();
			alert("이름을 입력해주세요.");
            return false; 
		}
		
		if(!id){
			document.registerForm.id.focus();
			alert("아이디를 입력해주세요.");
            return false; 
		}
		
		if(!pwd){
			document.registerForm.pwd.focus();
			alert("비밀번호를 입력해주세요.");
            return false; 
		}
		
		var regConfirm = confirm("가입하시겠습니까?");
		
		if(regConfirm){
			alert("로그인 후 이용해주세요!");
 		document.registerForm.submit();
		}

		
	}
	
	function fnLogin(){
		var userId = document.loginForm.userId.value;
		var userPwd = document.loginForm.userPwd.value;
		
		if(!userId){
			document.loginForm.userId.focus();
			alert("아이디를 입력해주세요.");
			return false;
		}
		
		if(!userPwd){
			document.loginForm.userPwd.focus();
			alert("비밀번호를 입력해주세요.");
			return false;
		}
		
 		document.loginForm.submit();
 	}
	
	function fnBoardWrite() {
		window.location.href = "/home/board/write";
	}
	
	function fnDoWrite() {
		var title = document.writeForm.title.value;
		var content = document.writeForm.content.value;
		console.log(title);
		console.log(content);
		if(!title){
			document.writeForm.title.focus();
			alert("제목을 입력해주세요.");
			return false;
		}
		
		if(!content){
			document.writeForm.content.focus();
			alert("내용을 입력해주세요.");
			return false;
		}
		
		var writeConfirm = confirm("게시글을 등록 하시겠습니까?");
		
		if(writeConfirm){
			alert("등록이 완료돼었습니다.");
			document.writeForm.submit();
		}
	}
	
	function previewImages(event) {
		var files = event.target.files;
		var preview = document.getElementById("imagePreview");
		
		preview.innerHTML = "";
		
		for(var i=0; i<files.length; i++){
			var file = files[i];
			var reader = new FileReader();
			
			reader.onload = function(event){
				var img = document.createElement("img");
				img.src = event.target.result;
				
				preview.appendChild(img);
			};
			
			reader.readAsDataURL(file);
		}
		
		/* for(var image of event.target.files){
			var reader = new FileReader();
			
			reader.onload = function(event) {
				var img = document.createElement("img");
				img.setAttribute("src", event.target.result);
				document.querySelector("textarea#imagePreview").appendChild(img);
			};
			
			console.log(image);
			reader.readAsDataURL(image);
		} */
		
	}
	
	
</script>

	<style>
		th {
			background-color: #F4F4F4;
			text-align: center;
            vertical-align: middle !important;
		}
	</style>
</head>
<body>

	<!-- header 부분 -->
	<tiles:insertAttribute name="header" />
	
	<!-- content 부분 -->
	<tiles:insertAttribute name="content" />
	
	<!-- footer 부분 -->
	<tiles:insertAttribute name="footer" />
	
</body>
</html>