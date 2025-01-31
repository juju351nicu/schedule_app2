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
<title>メンバー詳細画面</title>
<jsp:include page="header.jsp"/>
</head>
<body>
<h3>Todo詳細読み取り専用画面</h3>

	<table class="table">
		<tr>
			<th>番号</th>
			<td><c:out value="${dto.id}" /></td>
		</tr>
		<tr>
			<th>日付(date_from)</th>
			<td><input type="date" name="date_from" value="<c:out value="${dto.date_from }" />" size="10" readonly /></td>
		</tr>
		<tr>
			<th>日付(date_to)</th>
			<td><input type="date" name="date_to" value="<c:out value="${dto.date_to}" />" size="10" readonly /></td>
		</tr>
		<tr>
			<th>タイトル</th>
			<td><input type="text" name="title" value="<c:out value="${dto.title}" />" size="10" readonly/></td>
		</tr>
		<tr>
			<th>詳細</th>
			<td><textarea rows="10" cols="25" name="detail" readonly>
                      <c:out value="${dto.detail}" /></textarea ></td>
		</tr>
		<tr>
		<th>Done_flag</th>
		<td>
			<input type="text" name="Done_flag" value="<c:out value="${dto.done_flag}" />" size="10" readonly/>
		</td>
		</tr>
	</table>

<br>
<form id="main" method="post" name="main" action="" onsubmit="redirect(this);">
    <input type="hidden" name="submit"/>
</form>
<input class="btn btn-warning" type="submit" onclick="location.href='/Member/todo/Top'" value="トップページへ">

<div>
	<a class="btn btn-primary" href="javascript:history.back()">戻る</a>
</div>
</body>
</html>