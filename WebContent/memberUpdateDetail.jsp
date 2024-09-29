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
<title>メンバー更新画面</title>
<jsp:include page="header.jsp"/>
</head>
<body>
<form id="sender" action="M_update" method="POST">
<table class="table">
		<tr>
			<th>id</th>
			<td>${user.id }</td>
		</tr>
		<tr>
			<th>苗字</th>
			<td><input type="text" name="name_sei" value="<c:out value="${dto.name_sei }" />" size="10" required /></td>
		</tr>
		<tr>
			<th>名前</th>
			<td><input type="text" name="name_mei" value="<c:out value="${dto.name_mei }" />" size="10" required /></td>
		</tr>
		<tr>
			<th>ログインID</th>
			<td><input type="text" name="login_id" value="<c:out value="${dto.login_id }" />" size="10" required /></td>
		</tr>
		<tr>
			<th>パスワード</th>
			<td><input type="text" name="password" value="<c:out value="${dto.password }" />" size="10" required /></td>
		</tr>
		<tr>
			<th>登録日</th>
			<td><input type="datetime-local" name="registered_date" value="<fmt:formatDate value="${dto.registered_date }" pattern="yyyy-MM-dd"/>" size="10"/>
		</tr>
		<tr>
			<th>更新日</th>
			<td><input type="datetime-local" name="updated_date" value="<fmt:formatDate value="${dto.updated_date }" pattern="yyyy-MM-dd"/>" size="10"/>
		</tr>
		<tr>
			<th>最終ログイン時刻</th>
			<td><input type="datetime-local" name="last_login" value="<fmt:formatDate value="${dto.last_login }" pattern="yyyy-MM-dd"/>" size="10"/>
		</tr>

		<tr>
		<th>削除フラッグ</th>
		<td>
			<select name="delete_flag" id="delete_flag">
				<option value="1">true</option>
				<option value="0">false</option>
			</select>
		</td>
		</tr>
	</table>
	<input type="hidden" name="id" value="<c:out value="${user.id }" />" />
	<input type="submit" class="btn btn-success" value="更新する" />
</form>
<br>

</body>
</html>