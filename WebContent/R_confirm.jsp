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
</head>
<body>
<jsp:include page="nav.jsp" />
</head>
<body>
<div class="container">
	<table class="table">
		<tr>
			<th>フリガナ</th>
			<td><c:out value="${dto.furigana }" /></td>
		</tr>
		<tr>
			<th>名前</th>
			<td><c:out value="${dto.name }" /></td>
		</tr>
		<tr>
			<th>職業</th>
			<td><c:out value="${dto.task }" /></td>
		</tr>
		<tr>
			<th>作業期限</th>
			<td><c:out value="${dto.inputLimitdate }"/></td>
		</tr>
	</table>
</div>
	<button type="button" class="btn btn-primary" data-toggle="modal" data-target=".bs-example-modal-lg">登録する</button>
<div class="modal fade bs-example-modal-lg" tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel" aria-hidden="true">
  <div class="modal-dialog modal-lg">
    <div class="modal-content">
      <div style="padding: 15px;" class="text-center">
        <p class="text-danger">こちらの内容で間違いありませんか？</p>
        <div class="container">
			<a href="./index.jsp" class="btn btn-success" >登録する</a>
		</div>
      </div>
    </div>
  </div>
</div>

<div>
	<a href="./index.jsp" class="btn btn-success">トップページに戻る</a>
</div>
<div>
	<a href="javascript:history.back()"class="btn-success">戻る</a>
</div>
</body>
</html>