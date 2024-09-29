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
<title>Todo追加画面</title>
<jsp:include page="header.jsp"/>
</head>
<body>
<h3>Todo追加画面</h3>
<form id="sender" action="T_register" method="POST">
<table class="table">
			<tr>
			<th>番号</th>
			<td><c:choose>
					<c:when test="${dto.id > 0 }">
						<c:out value="${dto.id}" />
					</c:when>
					<c:otherwise>
						（新規)
					</c:otherwise>
				</c:choose></td>
		</tr>
		<tr>
			<th>日付(date_from)</th>
			<td><input type="date" name="date_from" size="10" required /></td>
		</tr>
		<tr>
			<th>日付(date_to)</th>
			<td><input type="date" name="date_to" size="10" required /></td>
		</tr>
		<tr>
			<th>タイトル</th>
			<td><input type="text" name="title" size="10" required /></td>
		</tr>
		<tr>
			<th>詳細</th>
			<td><textarea rows="20" cols="20" name="detail"  ></textarea></td>
		</tr>
		<tr>
		<th>Done_flag</th>
		<td>
			<select name="done_flag" id="done_flag">
				<option value="1">true</option>
				<option value="0">false</option>
			</select>
		</td>
		</tr>

</table>

 <input type="hidden" name="id" value="<c:out value="${dto.id }" />" />
 <input type="hidden" name="userId" value="<c:out value="${user.id}" />" />
	<input type="submit" class="btn btn-success" value="登録する" />
</form>
</body>
</html>