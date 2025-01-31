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
<form id="sender" action="M_delete" method="Post">
<table  class="table table-striped">
	<thead>
	<tr>
		<th>ID</th>
		<th>苗字</th>
		<th>名前</th>
		<th>ログインID</th>
		<th>パスワード</th>
		<th>登録日</th>
		<th>更新日</th>
		<th>最終ログインした時刻</th>
		<th>削除フラッグ</th>
	</tr>
	</thead>
	<c:forEach items="${MemberList}" var="dto">
	<tbody>
	<tr>
		<td><input id="${dto.id }" name="id" value="${dto.id }" type="checkbox"></td>

		<td >${dto.name_sei }</td>
		<td>${dto.name_mei }</td>
		<td>${dto.login_id }</td>
		<td>${dto.password }</td>
		<td>${dto.registered_date }</td>
		<td>${dto.updated_date }</td>
		<td>${dto.last_login }</td>
		<td>${dto.delete_flag }</td>
	</tr>
	</tbody>
	</c:forEach>
</table>
<input type="submit" class="btn btn-warning" value="削除する">
</form>
</div>
</body>
</html>