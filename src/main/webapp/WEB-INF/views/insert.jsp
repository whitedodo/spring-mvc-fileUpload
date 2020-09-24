<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html>
<head>
	<title>삽입</title>
</head>
<body>
<h1>
	Hello world!  
</h1>

<!-- 파일 업로드 영역 -->
<h3>파일 업로드</h3>
<form method="POST" action="uploadResult" enctype="multipart/form-data">
	<input type="file" name="uploadFile" multiple />
	<input type="submit" value="로그인" />
</form>
</body>
</html>
