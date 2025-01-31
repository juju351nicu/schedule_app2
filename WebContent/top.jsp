<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<jsp:include page="nav.jsp" />
<jsp:include page="header.jsp" />
<link rel="stylesheet" type="text/css" href="css/stylesheet.css">
<title>ダッシュボード画面</title>
<script type="text/javascript">
	$(function() {
		$('tr[data-href]', 'table.table-clickable').on('click', function() {
			location.href = $(this).data('href');
		});
	});
</script>
</head>
<br>
<div class="alert alert-danger" role="alert">
	<c:forEach items="${errorMessages}" var="errorMessage">
		${errorMessage}<br />
	</c:forEach>
</div>
<div class="right">
	<h3>ようこそ${user.name_sei}さん</h3>
</div>
<h4>
	<font color="red">${message} </font>
</h4>
<body>
	${user.password } ${user.id } ${user.last_login }
	<br>
	<form action="/Member/member/logout" method="Get">
		<input type="submit" value="ログアウト" class="btn btn-success">
	</form>
	<br>
	<%
		String notDone = (String) request.getAttribute("notDone");
		String done = (String) request.getAttribute("done");
	%>
	<form action="" method="Post">
		<input type="text" name="title" placeholder="タイトル" value="${searchTitle}" />
		<input type="text" name="date_from" placeholder="日付(date_from)" value="${searchDateFrom}" />
		<input type="text" name="date_to" placeholder="日付(date_to)" value="${searchDateTo}" />
		<input type="checkbox" name="not_done" id="id_not_done" <%=notDone != null ? "checked" : "" %>/><label for="id_not_done">未完了のみ</label>
		<input type="checkbox" name="done" id="id_done" <%=done != null ? "checked" : "" %>/><label for="id_done">完了のみ</label>
			<input class="btn-primary" type="submit"value="検索">

	</form>


<br>
	<form action="/Member/member/update" method="Get">
		<input name="id" type="hidden" value="<c:out value="${user.id }"/>" />
		<input type="submit" value="メンバーを情報を更新します" class="btn btn-danger">
	</form>

	<div>
		<a href="../member/list" class="btn btn-warning">メンバー一覧外面へ</a>
	</div>
	<div>
		<a href="../member/add" class="btn btn-info">メンバー新規登録画面</a>
	</div>
	<div>
		<a href="../todo/add" class="btn btn-primary">todoの追加画面</a>
	</div>

	<br>
	<div class="container">
		<table
			class="table table-clickable table-bordered table-hover table-striped">

			<tr>
				<th>日付(date from)</th>
				<th>日付(date to)</th>
				<th>期限</th>
				<th>タイトル</th>
				<th>詳細情報</th>
				<th>完了フラグ</th>
				<th>更新する</th>
			</tr>

			<c:forEach items="${todoList}" var="dto" varStatus="status">

				<%-- <tr style="background-color:${status.index % 2 == 0 ? '#66ffff' : '#99ff66'};"> --%>
				<tr data-href="/Member/todo/readOnly?id=<c:out value="${dto.id }"/>"
					style="background-color:${status.index % 2 == 0 ? '#00ff7f' : '#afeeee'};">
					<td>${dto.date_from}</td>
					<td>${dto.date_to}</td>
					<td>残り${dto.remainingDays}日間</td>
					<td>${dto.title }</td>
					<td>${fn:substring(dto.detail,0,10) }</td>
					<td>${dto.done_flag}</td>
					<td><div style="display: inline-flex">
							<form action="/Member/todo/update" method="Get">
								<input name="id" type="hidden"
									value="<c:out value="${dto.id }"/>" /> <input type="submit"
									value="更新" class="btn-success">
							</form>
							<form action="/Member/todo/doneFlag" method="Get">
								<input name="id" type="hidden"
									value="<c:out value="${dto.id }"/>" /> <input type="submit"
									value="完了" class="btn-success">
							</form>
						</div></td>
				</tr>
			</c:forEach>
		</table>
	</div>
	<%
		String error = (String) request.getAttribute("error");
		if (error != null) {
	%>
	<p style="color: red; font-size: larger; textalign: center;"><%=error%></p>

	<%
		}
	%>
	<br>



</body>
</html>