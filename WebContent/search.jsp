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

		<th>苗字</th>
		<th>名前</th>
		<th>ログインID</th>
		<th>パスワード</th>
		<th>登録日</th>
		<th>更新日</th>
		<th>最終ログインした日付</th>
		<th>削除フラッグ</th>

	</tr>
	<c:forEach items="${MemberList}" var="dto">
	<tr>
		<td><c:out value="${dto.name_sei }"></c:out></td>
		<td><c:out value="${dto.name_mei }"></c:out></td>
		<td><c:out value="${dto.login_id }"></c:out></td>
		<td><c:out value="${dto.password }"></c:out></td>
		<td><c:out value="${dto.registered_date }"></c:out></td>
		<td><c:out value="${dto.updated_date }"></c:out></td>
		<td><c:out value="${dto.last_login }"></c:out></td>
		<td><c:out value="${dto.delete_flag }"></c:out></td>
	</tr>
	</c:forEach>
</table>
</div>
</body>
</html>