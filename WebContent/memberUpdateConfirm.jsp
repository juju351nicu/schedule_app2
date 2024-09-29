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
<h4><font color="red" >${message} </font></h4>
<body>
苗字:
${dto.name_sei }
名前
${dto.name_mei }
ログインID:
${dto.login_id}
パスワード:
${dto.password }
登録日:
${dto.registered_date}
更新日:
${dto.updated_date}
最終ログイン日時:
${dto.last_login}
削除フラッグ:
${dto.delete_flag }
</body>
</html>