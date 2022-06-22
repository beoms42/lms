<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
  </style>
</head>

<body>
  <div class="container-scroller">
    <div class="container-fluid page-body-wrapper full-page-wrapper">
      <div class="content-wrapper d-flex align-items-center auth px-0">
        <div class="row w-100 mx-0">
          <div class="col-lg-4 mx-auto">
            <div class="auth-form-light text-left py-5 px-4 px-sm-5">
              <div class="brand-logo">
              	LMS-TFT
              </div>
              <h4>Hello! let's get started</h4>
              <h6 class="font-weight-light">Sign in to continue.</h6>
              <form id="signupForm" class="pt-3" method="post" action="${pageContext.request.contextPath}/login">
                <div class="form-group">
                  <input type="text" name="loginId" class="form-control form-control-lg" id="loginId" placeholder="Username" value="${cookieId}">
                  <span id="idHelper"></span>
                </div>
                <div class="form-group">
                  <input type="password" name="loginPw" class="form-control form-control-lg" id="loginPw" placeholder="Password">
                  <span id="pwHelper"></span>
                </div>
                <div class="mt-3">
                  <button class="btn btn-block btn-primary btn-lg font-weight-medium auth-form-btn" id="signIn">SIGN IN</button>
                </div>
                <div class="top left">
                    <label class="form-check-label text-muted">
                      <input type="checkbox" name="idSave" class="form-check-input">
                      아이디 저장
                    </label>
                  <span class="float-right">
	                  <a href="${pageContext.request.contextPath}/searchLoginId" class="auth-link text-black">아이디 찾기 /</a>
	                  <a href="#" class="auth-link text-black">비밀번호 찾기</a>
                  </span>
               	</div>
               
                <div class="text-center mt-4 font-weight-light">
                  <a href="${pageContext.request.contextPath}/addMember" class="text-primary">회원가입하기</a>
                </div>
              </form>
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
