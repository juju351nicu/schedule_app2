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
<script type="text/javascript">
	$(function() {
		$('tr[data-href]', 'table.table-clickable').on('click', function() {
			location.href = $(this).data('href');
		});
	});
</script>
</head>
<body>
	<div class="container">
		<form id="sender" action="" method="Post">
			<table  class="table table-clickable table-striped table-bordered table-hover ">
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
					<tr data-href="/Member/member/update?id=<c:out value="${dto.id }"/>">
						<td><input name="id" value="${dto.id }" type="checkbox"></td>
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
<br>
<form id="main" method="post" name="main" action="" onsubmit="redirect(this);">
    <input type="hidden" name="submit"/>
</form>
<input class="btn btn-primary" type="submit" onclick="location.href='/Member/todo/Top'" value="トップページへ">

<div>
	<a class="btn btn-primary" href="javascript:history.back()">戻る</a>
</div>
</body>
</html>