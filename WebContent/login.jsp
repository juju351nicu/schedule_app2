<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>ログイン画面</title>
</head>
<body>
<form action="./LoginServlet" method="post">
<div align="center">
<h2 class="glyphicon glyphicon-scissors">ログインページ</h2>


<table>
	<tr>
		<th>ユーザID</th>
		<th><input type="text" name="login_id" pattern="^[0-9A-Za-z]+$" size="16" maxlength="16" placeholder="半角英数字で入力"autofocus required/></th>
	</tr>
	<tr>
		<td>パスワード</td>
		<td><input type="password" name="password" pattern="^[0-9A-Za-z]+$" size="16" maxlength="16" placeholder="半角英数字で入力"autofocus required/></td>
		<td><input type="submit" value="ログイン"></td>

	</tr>

</table>

</div>
</form>
<% String error = (String)request.getAttribute("error");
	if(error != null){
%>
	<p style="color:red; font-size: larger; textalign:center;"><%= error  %></p>

<% }%>
<br>

</body>
</html>