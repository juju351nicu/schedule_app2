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
<meta http-equiv="Content-Script-Type" content="text/javascript">
<script type="text/javascript" src="js/jquery-3.1.0.min.js"></script>
<title>メンバー更新画面</title>
<jsp:include page="header.jsp"/>
</head>
<body>
<h3>Todo更新詳細画面</h3>
<form id="sender" action="update" method="POST">
<table class="table">
		<tr>
			<th>番号</th>
			<td>${dto.id}</td>
		</tr>
		<tr>
			<th>日付(date_from)</th>
			<td><input type="date" name="date_from" value="<c:out value="${dto.date_from }" />" size="10"  /></td>
		</tr>
		<tr>
			<th>日付(date_to)</th>
			<td><input type="date" name="date_to" value="<c:out value="${dto.date_to}" />" size="10"   /></td>
		</tr>
		<tr>
			<th>タイトル</th>
			<td><input type="text" name="title" value="<c:out value="${dto.title}" />" size="10" /></td>
		</tr>
		<tr>
			<th>詳細</th>
			<td><textarea rows="20" cols="20" name="detail">
                      <c:out value="${dto.detail}" /></textarea></td>
		</tr>
		<tr>
		<th>Done_flag</th>
		<td>
			<select name="done_flag" id="done_flag">
				<c:choose>
					<c:when test="${dto.done_flag}">
						<option value="0">false</option>
						<option value="1" selected="selected">true</option>
					</c:when>
					<c:otherwise>
						<option value="0" selected="selected">false</option>
						<option value="1">true</option>
					</c:otherwise>
				</c:choose>
			</select>
		</td>
		</tr>
</table>


<%--  <input type="hidden" name="userId" value="<c:out value="${user.id}" />" /> --%>
 <input type="hidden" name="id" value="<c:out value="${dto.id}" />" />
 <input type="submit" class="btn btn-success" value="更新する" />
</form>
<br>
<div class="alert alert-danger" role="alert">
	<c:forEach items="${errorMessages}" var="errorMessage">
		${errorMessage}<br />
		<h4><font color="red" >${message} </font></h4>
	</c:forEach>
</div>
<br>
<div>
	<a class="btn btn-primary" href="javascript:history.back()">戻る</a>
</div>
</body>
</html>