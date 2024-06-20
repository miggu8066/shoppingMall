<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Context Path 테스트</title>
</head>
<body>

<% 
    // Java 코드를 사용하여 context path 출력
    String contextPath = request.getContextPath(); 
%>
<p>Context Path: <%= contextPath %></p>

</body>
</html>
