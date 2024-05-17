<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<title></title>
	
	<!-- Google Fonts -->
	<link href='http://fonts.googleapis.com/css?family=Titillium+Web:400,200,300,700,600' rel='stylesheet' type='text/css'>
	<link href='http://fonts.googleapis.com/css?family=Roboto+Condensed:400,700,300' rel='stylesheet' type='text/css'>
	<link href='http://fonts.googleapis.com/css?family=Raleway:400,100' rel='stylesheet' type='text/css'>
	
	<!-- Bootstrap -->
	<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css">
	
	<!-- Font Awesome -->
	<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css">
	
	<!-- Custom CSS -->
	<link rel="stylesheet" href="css/owl.carousel.css">
	<link rel="stylesheet" href="css/style.css">
	<link rel="stylesheet" href="css/responsive.css">
	
	<!-- Latest jQuery form server -->
	<script src="https://code.jquery.com/jquery.min.js"></script>
	
	<!-- Bootstrap JS form CDN -->
	<script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/js/bootstrap.min.js"></script>
	
	<!-- jQuery sticky menu -->
	<script src="js/owl.carousel.min.js"></script>
	<script src="js/jquery.sticky.js"></script>
	
	<!-- jQuery easing -->
	<script src="js/jquery.easing.1.3.min.js"></script>
	
	<!-- Main Script -->
	<script src="js/main.js"></script>
	
	<!-- 상단 로그인 및 회원가입 -->
	<link rel="stylesheet" href="css/register.css">
	<script src="js/register.js"></script>
	
	<script>
	
		function fnBoardWrite() {
			window.location.href = "/home/board/write";
		}
		
		function fnDoWrite() {
			var title = document.writeForm.title.value;
			var content = document.writeForm.content.value;
			
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
	
	</script>
	
	<style>
		th {
			background-color : #F4F4F4;
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