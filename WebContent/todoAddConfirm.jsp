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
<title>「Todo登録内容確認画面」または「Todo更新内容確認画面」</title>
<jsp:include page="header.jsp"/>
</head>
<body>
<h3>「Todo登録内容確認画面」または「Todo更新内容確認画面」</h3>

	<table class="table">
		<tr>
			<th>番号</th>
			<td>${dto.id}</td>
		</tr>
		<tr>
			<th>日付(date_from)</th>
			<td>${dto.date_from}</td>
		</tr>
		<tr>
			<th>日付(date_to)</th>
			<td>${dto.date_to}</td>
		</tr>
		<tr>
			<th>タイトル</th>
			<td>${dto.title}</td>
		</tr>
		<tr>
			<th>詳細</th>
			<td>${dto.detail}</td>
		</tr>
		<tr>
			<th>Done_flag</th>
			<td>${dto.done_flag}</td>
		</tr>
	</table>

	<form action="" method="Post">
		<input type="hidden" name="id" value="${dto.id}">
		<input type="hidden" name="date_from" value="${dto.date_from}">
		<input type="hidden" name="date_to" value="${dto.date_to}">
		<input type="hidden" name="title" value="${dto.title}">
		<input type="hidden" name="detail" value="${dto.detail}">
		<input type="hidden" name="done_flag" value="${dto.done_flag}">
		<input class="btn-success" type="submit" name="button" value="登録">
		<input class="btn-success" type="submit" name="button" value="修正">
	</form>
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