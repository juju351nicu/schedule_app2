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
<link rel="Bootstrap" type="text/css" href="css/Bootstrap.css">
<title>ダッシュボード画面</title>
</head>
<body>
	${user.password }
	${user.id }
	${user.last_login }
	<br>
	<form action="/Member/LogoutServlet" method="Get">
		<input type="submit" value="ログアウト" class="btn btn-success">
	</form>
	<br>
	<form action="/Member/member/T_select" method="Get">
		<input type="text" name="title" placeholder="タイトル" />
		<input type="text" name="date_from"  placeholder="日付"/>
		<input type="submit" value="検索">
	</form>

	<br>

	<form action="/Member/member/updetail" method="Get">
		<input name="id" type="hidden" value="<c:out value="${user.id }"/>" />
		<input type="submit" value="メンバーを情報を更新します" class="btn btn-success">
	</form>

	<div>
		<a href="../member/M_search" class="btn btn-primary">メンバー一覧外面へ</a>
	</div>
	<div>
		<a href="../member/M_input" class="btn btn-primary">メンバー新規登録画面</a>
	</div>
	<div>
		<a href="../member/T_input" class="btn btn-primary">todoの追加画面</a>
	</div>

	<br>
	<div class="container">
		<table class="table table-striped table-bordered">


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
					<tr style="background-color:${status.index % 2 == 0 ? '#00ff7f' : '#afeeee'};">
					<td>${dto.date_from}</td>
					<td>${dto.date_to}</td>
					<td>残り${dto.toString()}日間</td>
					<td>${dto.title }</td>
					<td>${fn:substring(dto.detail,0,10) }</td>
					<td>${dto.done_flag}</td>
					<td><div style="display:inline-flex">
					<form action="/Member/member/T_updetail" method="Get">
						<input name="id" type="hidden" value="<c:out value="${dto.id }"/>" />
						<input type="submit" value="更新" class="btn-success">
					</form>
					<form action="/Member/member/T_Done_flag" method="Get">
						<input name="id" type="hidden" value="<c:out value="${dto.id }"/>" />
						<input type="submit" value="完了" class="btn-success">
					</form>
					</div>
					</td>
				</tr>

			</c:forEach>
		</table>
	</div>



</body>
</html>