<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<jsp:include page="nav.jsp" />
<jsp:include page="header.jsp" />
<title>一覧画面</title>
</head>
<body>
<div class="container">
<table class="table table-striped table-bordered">
	<tr>
		<th>日付(date from)</th>
		<th>日付(date to)</th>
		<th>タイトル</th>
		<th>詳細情報</th>
		<th>完了フラグ</th>



	</tr>
	<c:forEach items="${selectList}" var="dto">
	<tr>
		<td>${dto.date_from}</td>
		<td>${dto.date_to}</td>
		<td>${dto.title }</td>
		<td>${dto.detail }</td>
		<td>${dto.done_flag}</td>
			</c:forEach>
</table>
</div>



</body>
</html>