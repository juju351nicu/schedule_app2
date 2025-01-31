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
<title>更新確認画面</title>
<jsp:include page="header.jsp"/>
<jsp:include page="nav.jsp" />
</head>

<body>
<h4><font color="red" >${message} </font></h4>
<h1>以下の内容で更新します。よろしいでしょうか？</h1>
苗字:
${dto.name_sei }
名前
${dto.name_mei }
ログインID:
${dto.login_id}
パスワード:
${dto.password }

削除フラッグ:
${dto.delete_flag }
<form action="" method="Post">
		<%-- <input type="hidden" name="id" value="${dto.id}"> --%>
		<input type="hidden" name="name_sei" value="${dto.name_sei}">
		<input type="hidden" name="name_mei" value="${dto.name_mei}">
		<input type="hidden" name="login_id" value="${dto.login_id}">
		<input type="hidden" name="password" value="${dto.password}">
		<input type="hidden" name="delete_flag" value="${dto.delete_flag}">
		<input class="btn-success" type="submit" name="button" value="登録">
		<input class="btn-success" type="submit" name="button" value="修正">
	</form>
<br>
<form id="main" method="post" name="main" action="" onsubmit="redirect(this);">
    <input type="hidden" name="submit"/>
</form>
<input class="btn btn-warning" type="submit" onclick="location.href='/Member/todo/Top'" value="トップページへ">
<div>
	<a class="btn btn-info" href="javascript:history.back()">戻る</a>
</div>
</body>
</html>