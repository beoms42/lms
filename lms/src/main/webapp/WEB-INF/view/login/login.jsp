<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">

<head>
  <!-- Required meta tags -->
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  <title>LMS-TFT</title>
  <!-- plugins:css -->
  <link rel="stylesheet" href="${pageContext.request.contextPath}/vendors/feather/feather.css">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/vendors/ti-icons/css/themify-icons.css">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/vendors/css/vendor.bundle.base.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
  <!-- endinject -->
  <!-- Plugin css for this page -->
  <!-- End plugin css for this page -->
  <!-- inject:css -->
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/vertical-layout-light/style.css">
  <!-- endinject -->
  <link rel="shortcut icon" href="${pageContext.request.contextPath}/images/favicon.png" />
  <style>
  	.top {margin-top : 10px;}
  	.left {margin-left : 20px;}
  	.bottom {margin-bottom : 30px;}
  	.up {margin-top : 50px;}
  	.check-up {margin-top : 20px;}
  	.boxShadow {box-shadow: 0 20px 25px -5px rgb(0 0 0 / 10%);}
  	.bg-white {
 	   --bs-bg-opacity: 1;
    	background-color: rgba(var(--bs-white-rgb), var(--bs-bg-opacity)) !important;
	}
	.msgbox{
	   position: fixed;
	   background-color: #222222;
	   color: #ffffff;
	   top:120px;
	   left:34%;
	   padding-top:70px;
	   text-align:center;
	   width: 614px;
	   height: 700px;
	   font-weight:600;
	   font-size:18px;
	   border: 1px solid #000000;
	   opacity: 0.9;   
  </style>
</head>

<body>
  <div class="container-scroller">
    <div class="container-fluid page-body-wrapper full-page-wrapper">
      <div class="content-wrapper d-flex align-items-center auth px-0">
        <div class="row w-100 mx-0">
           <div class="col-lg-4 mx-auto">
            <div class="auth-form-light text-left py-5 px-4 px-sm-5 boxShadow">
              <h4 class = "bottom">Login</h4>
              <form id="signupForm" class="pt-3" method="post" action="${pageContext.request.contextPath}/login">
	              <div class="form-group">
	               	ID
	               	 <c:choose>
	               	 	<c:when test="${cookieId != null}">
			                <input type="text" name="loginId" class="form-control form-control-lg" id="loginId" placeholder="아이디" value="${cookieId}">
			                <span id="idHelper"></span>
	               	 	</c:when>
	               	 	<c:otherwise>
	               	 		<input type="text" name="loginId" class="form-control form-control-lg" id="loginId" placeholder="아이디" value="admin">
			                <span id="idHelper"></span>
	               	 	</c:otherwise>
	               	 </c:choose>
	               </div>
	               <div class="form-group">
	               	 Password
	                 <input type="password" name="loginPw" class="form-control form-control-lg" id="loginPw" placeholder="비밀번호" value="1234">
	                 <span id="pwHelper"></span>
	               </div>
	               <div>
	               	  <c:choose>
	               	  	<c:when test="${loginActive eq 0}">
	               	  		<span>비활성화 된 계정입니다.</span>
	               	  	</c:when>
	               	  	<c:when test="${loginActive eq 2}">
		               	  	<span>승인되지 않은 아이디입니다.</span>
	               	  	</c:when>
	               	  	<c:when test="${loginActive eq 3}">
	               	  		<span>승인 거절된 아이디입니다.</span>
	               	  	</c:when>
	               	  	<c:otherwise>
	               	  		<span></span>
	               	  	</c:otherwise>
	               	  </c:choose>
	               </div>
	               <div>
	                   <h4>managerID : manager</h4>
	                   <h4>teacherID : teacher</h4>
	                   <h4>studentID : student</h4>
	               </div>
	               <div class="mt-3">
	                 <button class="btn btn-block btn-primary btn-lg font-weight-medium auth-form-btn up" id="signIn">SIGN IN</button>
	               </div>
	               <hr class="bg-white">
	               <div class="mb-2">
	                   	<a href="${pageContext.request.contextPath}/addMember" class="btn btn-block btn-facebook auth-form-btn">회원가입하기</a>
                   </div>
	               <div class="top left check-up">
	                   <label class="form-check-label text-muted">
	                     <input type="checkbox" name="idSave" class="form-check-input">
	                     아이디 저장
	                   </label>
	                 <span class="float-right">
	                  <a href="${pageContext.request.contextPath}/searchLoginId" class="auth-link text-black">아이디 찾기 /</a>
	                  <a href="${pageContext.request.contextPath}/searchLoginPw" class="auth-link text-black">비밀번호 찾기</a>
	                 </span>
	              </div>
              </form>
              <c:if test="${msg != null}">
              	<div class="msgbox">
			       비밀번호를 변경 3개월 경과하였습니다. <br>
			       개인정보 보호를 위해 비밀번호를 변경하시겠습니까?
			       <br>
			       <br>
			       <br>
			       <div class="text-center">
			       <a href="${pageContext.request.contextPath}/changePw?loginId=${loginId}&changePwLater=on" class="btn text-white btn-danger">3개월 더 연장하기</a>
			       <a href="${pageContext.request.contextPath}/changePw?loginId=${loginId}" class="btn text-white btn-info">비밀번호 변경하기</a>
			       </div>
			    </div>
              </c:if>
            </div>
          </div>
        </div>
      </div>
      <!-- content-wrapper ends -->
    </div>
    <!-- page-body-wrapper ends -->
  </div>
  <!-- container-scroller -->
  <!-- plugins:js -->
  <script src="../../vendors/js/vendor.bundle.base.js"></script>
  <!-- endinject -->
  <!-- Plugin js for this page -->
  <!-- End plugin js for this page -->
  <!-- inject:js -->
  <script src="${pageContext.request.contextPath}/js/off-canvas.js"></script>
  <script src="${pageContext.request.contextPath}/js/hoverable-collapse.js"></script>
  <script src="${pageContext.request.contextPath}/js/template.js"></script>
  <script src="${pageContext.request.contextPath}/js/settings.js"></script>
  <script src="${pageContext.request.contextPath}/js/todolist.js"></script>
  <!-- endinject -->
  <script>
  	let msg2 = '${msg2}';
  	console.log(msg2);
  	
  	if(msg2 == 'fail') {
  		console.log("성공했어요");
  		$('#pwHelper').text('아이디나 비밀번호가 틀렸습니다.');
  	} else {
  		console.log("실패했어요");
  		$('#pwHelper').text('');
  	}
  	
  	$('#signIn').click(function() {
  		if($('#loginId').val() == '') {
  			$('#idHelper').text('id를 입력하세요');
  		} else if($('#loginPw').val() == '') {
  			$('#idHelper').text('');
  			$('#pwHelper').text('pw를 입력하세요');
  		} else {
  			$('#signupForm').submit();
  		}
  	});
  	
  </script>
</body>

</html>
