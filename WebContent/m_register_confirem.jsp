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
<title>登録確認画面</title>
<jsp:include page="header.jsp"/>
<jsp:include page="nav.jsp" />
</head>
<h4><font color="red" >${message} </font></h4>
<body>
苗字:
<c:out value="${dto.name_sei }" ></c:out>
名前
<c:out value="${dto.name_mei }"></c:out>
ログインID:
<c:out value="${dto.login_id}"></c:out>
パスワード:
<c:out value="${dto.password }"></c:out>
登録日:
<c:out value="${dto.registered_date}"></c:out>
更新日:
<c:out value="${dto.updated_date}"></c:out>
最終ログイン日時:
<c:out value="${dto.last_login}"></c:out>
削除フラッグ:
<c:out value="${dto.delete_flag }"></c:out>
<br>

	<button type="button" class="btn btn-primary" data-toggle="modal" data-target=".bs-example-modal-lg">登録する</button>
<div class="modal fade bs-example-modal-lg" tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel" aria-hidden="true">
<div class="modal-dialog modal-lg">
<div class="modal-content">
<div style="padding: 15px;" class="text-center">
<p class="text-danger">こちらの内容で間違いありませんか？</p>
<div class="container">
<a href="../top.jsp" class="btn btn-success" >登録する</a>
</div>
</div>
</div>
</div>
</div>
<div>
	<a href="javascript:history.back()"class="btn-success">戻る</a>
</div>
</body>
</html>