<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="Pragma" content="no-cache">
<meta http-equiv="Chache-Control" content="no-cache">
<meta http-equiv="Expires" content="0">
<title>更新詳細画面</title>
<jsp:include page="header.jsp"/>
</head>
<body>
<jsp:include page="nav.jsp" />
<div class="container">
<form id = "update" action="update" method="POST">
	<table class="table">
		<tr>
			<th>番号</th>
			<td><c:out value="${dto.id}" /></td>
		</tr>
		<tr>
			<th>フリガナ</th>
			<td><input type="text" name="furigana" value="<c:out value="${dto.furigana }" />" size="10" required /></td>
		</tr>
		<tr>
			<th>名前</th>
			<td><input type="text" name="name" value="<c:out value="${dto.name }" />" size="10" required /></td>
		</tr>
		<tr>
			<th>項目名</th><td><input type="text" name="task" value="<c:out value="${dto.task }" />" size="10" required  /></td>
		</tr>
		<tr>
		<th>作業期限</th>
		<td>
		<c:choose>
			<c:when test="${dto.inputLimitdate != null }">
				<input type="text" name="limitdate" value="<c:out value="${dto.inputLimitdate}" />" size="10"/>
			</c:when>
			<c:otherwise>
				<input type="text" name="limitdate" value="<fmt:formatDate value="${dto.limitdate }" pattern="yyyy-MM-dd"/>" size="10"/>
			</c:otherwise>
		</c:choose>
		</td>
	</tr>
	</table>
	<br/>
	<br/>


		<input type="hidden" name="id" value="<c:out value="${dto.id}" />"/>
		<input type="submit" class="btn btn-primary" value="更新する" />
	</form>
</div>
<div class="container">
	<a href="javascript:history.back()"class="btn-success">戻る</a>
</div>
</body>
</html>