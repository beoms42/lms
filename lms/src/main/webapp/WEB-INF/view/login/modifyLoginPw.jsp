<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">

<head>
  <!-- Required meta tags -->
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  <title>search login id</title>
  <!-- plugins:css -->
  <link rel="stylesheet" href="${pageContext.request.contextPath}/vendors/feather/feather.css">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/vendors/ti-icons/css/themify-icons.css">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/vendors/css/vendor.bundle.base.css">
  <!-- endinject -->
  <!-- Plugin css for this page -->
  <link rel="stylesheet" href="${pageContext.request.contextPath}/vendors/select2/select2.min.css">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/vendors/select2-bootstrap-theme/select2-bootstrap.min.css">
  <!-- End plugin css for this page -->
  <!-- inject:css -->
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/vertical-layout-light/style.css">
  <!-- endinject -->
  <link rel="shortcut icon" href="${pageContext.request.contextPath}/images/favicon.png" />
  <style>
  	.bottom {margin-bottom : 40px;}
  	.auth .auth-form-light select {
  		color: #4C4C4C;
  	}
  	.boxShadow {box-shadow: 0 20px 25px -5px rgb(0 0 0 / 10%);}
  </style>
</head>

<body>
	<div class="container-scroller">
		<div class="container-fluid page-body-wrapper full-page-wrapper">
			<div class="content-wrapper d-flex align-items-center auth px-0">
				<div class="row w-100 mx-0">
					<div class="col-lg-7 mx-auto">
						
						<div class="auth-form-light text-left py-5 px-4 px-sm-5 boxShadow">
							<div class="card-body">
					            <h4 class="card-title bottom">비밀번호 변경하기</h4>
					          
					            <form id="modifyLoginPwForm" method="post" action="${pageContext.request.contextPath}/modifyLoginPw" class="forms-sample">
						             <input type="hidden" name="loginId" value="${loginId}">
						            <div class="form-group">
						             	<span id="lastLoginPwCheckHelper" style="color:red;"></span><br/>
							            <label>Insert Password</label>
							            <input type="password" name="loginPw" class="form-control" placeholder="password" id="loginPw">
							            <span id="loginPwHelper"></span>
						            </div>
						            <div class="form-group">
							            <label>Check Password</label>
							            <input type="password" name="loginPwChk" class="form-control" placeholder="password" id="loginPwChk">
							            <span id="loginPwChkHelper"></span>
						            </div>
						            	<button id="changePw" type="button" class="btn btn-primary mr-2">비밀번호 변경</button>
					            </form>
					        </div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
  <!-- container-scroller -->
  <!-- plugins:js -->
  <script src="${pageContext.request.contextPath}/vendors/js/vendor.bundle.base.js"></script>
  <!-- endinject -->
  <!-- Plugin js for this page -->
  <script src="${pageContext.request.contextPath}/vendors/typeahead.js/typeahead.bundle.min.js"></script>
  <script src="${pageContext.request.contextPath}/vendors/select2/select2.min.js"></script>
  <!-- End plugin js for this page -->
  <!-- inject:js -->
  <script src="${pageContext.request.contextPath}/js/off-canvas.js"></script>
  <script src="${pageContext.request.contextPath}/js/hoverable-collapse.js"></script>
  <script src="${pageContext.request.contextPath}/js/template.js"></script>
  <script src="${pageContext.request.contextPath}/js/settings.js"></script>
  <script src="${pageContext.request.contextPath}/js/todolist.js"></script>
  <!-- endinject -->
  <!-- Custom js for this page-->
  <script src="${pageContext.request.contextPath}/js/file-upload.js"></script>
  <script src="${pageContext.request.contextPath}/js/typeahead.js"></script>
  <script src="${pageContext.request.contextPath}/js/select2.js"></script>
  <!-- End custom js for this page-->

<script>


	$('#loginPw').blur(function(){
		if ($('#loginPw').val().trim() == ''){
			$('#loginPwHelper').text('비밀번호를 입력해주세요.');
		} else if (!spc.test($('#loginPw').val().trim())){
			$('#loginPwHelper').text('비밀번호에 특수문자를 포함해주세요..');
		} else if($('#loginPw').val().trim().length < 8){
			$('#loginPwHelper').text('비밀번호를 8자 이상 입력해주세요.');
		} else {
			$('#loginPwHelper').text('');
		}
	});
	
	$('#loginPwChk').blur(function(){
		if ($('#loginPwChk').val().trim() == ''){
			$('#loginPwChkHelper').text('비밀번호를 입력해주세요.');
		} else if (!spc.test($('#loginPw').val().trim())){
			$('#loginPwChkHelper').text('비밀번호에 특수문자를 포함해주세요..');
		} else if($('#loginPwChk').val().trim().length < 8){
			$('#loginPwChkHelper').text('비밀번호를 8자 이상 입력해주세요.');
		} else {
			$('#loginPwChkHelper').text('');
		}
	});


	var url='${pageContext.request.contextPath}';
	var spc = RegExp(/[~!@#$%^&*()|<>?:{}]/);
	
	$('#changePw').click(function(){
		
		if ($('#loginPw').val().trim() == ''){
			$('#loginPwHelper').text('비밀번호를 입력해주세요.');
		} else if (!spc.test($('#loginPw').val().trim())){
			$('#loginPwHelper').text('비밀번호에 특수문자를 포함해주세요..');
		} else if($('#loginPw').val().trim().length < 8){
			$('#loginPwHelper').text('비밀번호를 8자 이상 입력해주세요.');
		} else if($('#loginPwChk').val().trim() == ''){
			$('#loginPwHelper').text('');
			$('#loginPwChkHelper').text('비밀번호를 입력해주세요.');
		} else if (!spc.test($('#loginPw').val().trim())){
			$('#loginPwChkHelper').text('비밀번호에 특수문자를 포함해주세요..');
		} else if($('#loginPwChk').val().trim().length < 8){
			$('#loginPwChkHelper').text('비밀번호를 8자 이상 입력해주세요.');
		} else {
  			$.ajax({
  				type : "POST"
  				, url : url+"/lastLoginPwCheck"
  				, data : {loginPw:$('#loginPw').val().trim(), loginId:"${loginId}"}
  				, success : function(ck) {
  					console.log('ck:', ck);
  					if(ck=='false') {
  						$('#lastLoginPwCheckHelper').text('이전 비밀번호와 같은 비밀번호 입니다. 다른비밀번호를 설정해주세요.');
  					}else{
  						$('#modifyLoginPwForm').submit();
  					}
  				}
  			})
  		}
	});
	
	
	$(document).keydown(function(event){
		if(event.keyCode==13) {
			if ($('#loginPw').val().trim() == ''){
				$('#loginPwHelper').text('비밀번호를 입력해주세요.');
			} else if (!spc.test($('#loginPw').val())){
				$('#loginPwHelper').text('비밀번호에 특수문자를 포함해주세요..');
			} else if($('#loginPw').val().trim().length < 8){
				$('#loginPwHelper').text('비밀번호를 8자 이상 입력해주세요.');
			} else if($('#loginPwChk').val().trim() == ''){
				$('#loginPwHelper').text('');
				$('#loginPwChkHelper').text('비밀번호를 입력해주세요.');
			} else if (!spc.test($('#loginPw').val())){
				$('#loginPwChkHelper').text('비밀번호에 특수문자를 포함해주세요..');
			} else if($('#loginPwChk').val().trim().length < 8){
				$('#loginPwChkHelper').text('비밀번호를 8자 이상 입력해주세요.');
			} else {
	  			$.ajax({
	  				type : "POST"
	  				, url : url+"/lastLoginPwCheck"
	  				, data : {loginPw:$('#loginPw').val().trim(), loginId:"${loginId}"}
	  				, success : function(ck) {
	  					console.log('ck:', ck);
	  					if(ck=='false') {
	  						$('#lastLoginPwCheckHelper').text('이전 비밀번호와 같은 비밀번호 입니다. 다른비밀번호를 설정해주세요.');
	  					} else{
	  						$('#modifyLoginPwForm').submit();
	  					}
	  				}
	  			})
	  		}
		}
	});

</script>
</body>
</html>
