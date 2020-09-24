<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>업로드 - 결과</title>
</head>
<body>


<!-- 업로드 결과 (출력) -->
<c:set var="i" value="1" />
<c:set var="num" value="1" />

<table style="width:700px; border:1px;">
<c:forEach items="${list}" var="list">
	
	<c:choose>
		<c:when test="${i == 1}">
	<tr>
		<td colspan="2" style="background-color:#e2e2e2;">
			<c:out value="${num}" />
		</td>
	</tr>
		</c:when>
	</c:choose>
	
	<c:choose>
		<c:when test="${i <= 3}">
	<tr>
		<td><c:out value="${i}" /></td>
		<td>${list}</td>
		<c:set var="i" value="${i + 1}" />
	</tr>
		</c:when>
	
		<c:otherwise>
			<c:set var="i" value="1" />
			<c:set var="num" value="${num + 1}" />
		</c:otherwise>
		
	</c:choose>
</c:forEach>
</table>

</body>
</html>