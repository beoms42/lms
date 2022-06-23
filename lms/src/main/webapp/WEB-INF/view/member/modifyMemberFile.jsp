<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form method="post" action="${pageContext.request.contextPath}/member/modifyMemberFile">
		<input type="file" name = "insertMemberFile" >
		<input type="text" name = "deleteMemberFileName" value="${memberFileName}" hidden="hidden">
		<button>사진수정</button>
	</form>
</body>
</html>