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

<title>メンバー追加画面</title>
<jsp:include page="header.jsp"/>
	<script type="text/javascript">
	function disp(){

		// 「OK」時の処理開始 ＋ 確認ダイアログの表示
		if(window.confirm('登録しますか')){

			/* location.href = "memberAddConfirm.jsp"; */

		}
		// 「OK」時の処理終了

		/* // 「キャンセル」時の処理開始
		else{

			window.alert('キャンセルされました'); // 警告ダイアログを表示

		}
		// 「キャンセル」時の処理終了 */

	}
/* <p><input type="button" value="確認ダイアログ" onClick="disp()"></p> */

</script>
</head>
<body>
<form id="sender" action="M_register" method="POST">
<table class="table">
		<tr>
			<th>番号</th>
			<td><c:choose>
					<c:when test="${dto.id > 0 }">
						<c:out value="${dto.id}" />
					</c:when>
					<c:otherwise>
						（新規)
					</c:otherwise>
				</c:choose></td>
		</tr>
		<tr>
			<th>苗字</th>
			<td><input type="text" name="name_sei" size="10" required /></td>
		</tr>
		<tr>
			<th>名前</th>
			<td><input type="text" name="name_mei"  size="10" required /></td>
		</tr>
		<tr>
			<th>ログインID</th>
			<td><input type="text" name="login_id"  size="10" required /></td>
		</tr>
		<tr>
			<th>パスワード</th>
			<td><input type="text" name="password"  size="10" required /></td>
		</tr>
		<tr>
			<th>登録日</th>
			<td><input type="datetime-local" name="registered_date" size="10" /></td>
		</tr>
		<tr>
			<th>更新日</th>
			<td><input type="datetime-local" name="updated_date" size="10" /></td>
		</tr>
		<tr>
			<th>最終ログイン時刻</th>
			<td><input type="datetime-local" name="last_login" size="10" /></td>
		</tr>
		<tr>
		<th>削除フラッグ</th>
		<td>
			<select name="delete_flag" id="delete_flag">
				<option value="1">true</option>
				<option value="0">false</option>
			</select>
		</td>
		</tr>
	</table>
	<input type="hidden" name="id" value="<c:out value="${dto.id }" />" />
	<input type="submit" class="btn btn-success" value="登録する" onClick="disp()" />


</form>
</body>
</html>