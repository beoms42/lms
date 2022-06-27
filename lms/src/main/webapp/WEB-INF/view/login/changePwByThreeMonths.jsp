<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<style type="text/css">
   .msgbox{
	   position: fixed;
	   top:10px;
	   left: 35%;
	   background-color: #ffffff;
	   padding-top: 100px;
	   padding-bottom: 100px;
	   padding-left: 50px;
	   padding-right: 50px;
	   width: 500px;
	   height: 600px;
   }
</style>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div class="msgbox">
       비밀번호를 변경 3개월 경과하였습니다. <br>
       개인정보 보호를 위해 비밀번호를 변경하시겠습니까?
       <br>
       <div class="text-center">
       <a href="${pageContext.request.contextPath}/changePw?loginId=${loginId}&changePwLater=on" class="btn">3개월 더 연장하기</a>
       <a href="${pageContext.request.contextPath}/changePw?loginId=${loginId}" class="btn">비밀번호 변경하기</a>
       </div>
    </div>
</body>
</html>