<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
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
  <link rel="stylesheet" href="${pageContext.request.contextPath}/vendors/datatables.net-bs4/dataTables.bootstrap4.css">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/vendors/ti-icons/css/themify-icons.css">
  <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/js/select.dataTables.min.css">
  <!-- End plugin css for this page -->
  <!-- inject:css -->
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/vertical-layout-light/style.css">
  <!-- endinject -->
  <link rel="shortcut icon" href="${pageContext.request.contextPath}/images/tftace.jpg" />
  <script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
</head>
<body>
  <div class="container-scroller">
    <!-- partial:partials/_navbar.html -->
    <jsp:include page="/inc/topbar.jsp"/>
    <!-- partial -->
    <div class="container-fluid page-body-wrapper">
      <!-- partial:partials/_settings-panel.html -->
      
      <!-- partial -->
      <!-- partial:partials/_sidebar.html -->
      <nav class="sidebar sidebar-offcanvas" id="sidebar">
        <jsp:include page="/inc/sidebar.jsp"/>
      </nav>
      <!-- partial -->
      <div class="main-panel">
        <div class="content-wrapper">
          <div class="row">
            <div class="col-md-12 grid-margin">
              <div class="row">
                <div class="col-12 col-xl-8 mb-4 mb-xl-0">
                  <h3 style="font-weight: bold;">비밀번호 변경</h3><br>
	                <form method="post" action="${pageContext.request.contextPath}/loginCheck/modifyPw" id="loginPwForm">
						<table class="table">
							<tr>
								<th>아이디</th>
								<td><input type="text" class="form-control-df" name="loginId" value="${loginId}" readonly="readonly"></td>
							</tr>
							<tr>
								<th>변경할 비밀번호</th>
								<td><input type="password" class="form-control-df" id="tryPw"> 
								<span id="tryPwHelper"></span></td>
							</tr>
							<tr>
								<th>변경할 비밀번호 확인</th>
								<td><input type="password" class="form-control-df" name="loginPw" id="loginPw">
								<span id="loginPwHelper"></span></td>
							</tr>
						</table><br>
						<button class="btn btn-primary" id="loginPwBtn" style="border-radius: 4 4 4 4;">변경하기</button>
					</form>
                </div>
                <div class="col-12 col-xl-4">
                 <div class="justify-content-end d-flex">
                 </div>
                </div>
              </div>
            </div>
          </div>
          
          
        </div>
        <!-- content-wrapper ends -->
        <!-- partial:partials/_footer.html -->
        <footer class="footer">
          <div class="d-sm-flex justify-content-center justify-content-sm-between">
            <span class="text-muted text-center text-sm-left d-block d-sm-inline-block">Copyright © 2021.  Premium <a href="https://www.bootstrapdash.com/" target="_blank">Bootstrap admin template</a> from BootstrapDash. All rights reserved.</span>
            <span class="float-none float-sm-right d-block mt-1 mt-sm-0 text-center">Hand-crafted & made with <i class="ti-heart text-danger ml-1"></i></span>
          </div>
          <div class="d-sm-flex justify-content-center justify-content-sm-between">
            <span class="text-muted text-center text-sm-left d-block d-sm-inline-block">Distributed by <a href="https://www.themewagon.com/" target="_blank">Themewagon</a></span> 
          </div>
        </footer> 
        <!-- partial -->
      </div>
      <!-- main-panel ends -->
    </div>   
    <!-- page-body-wrapper ends -->
  </div>
  <!-- container-scroller -->

  <!-- plugins:js -->
  <script src="vendors/js/vendor.bundle.base.js"></script>
  <!-- endinject -->
  <!-- Plugin js for this page -->
  <script src="vendors/chart.js/Chart.min.js"></script>
  <script src="vendors/datatables.net/jquery.dataTables.js"></script>
  <script src="vendors/datatables.net-bs4/dataTables.bootstrap4.js"></script>
  <script src="js/dataTables.select.min.js"></script>

  <!-- End plugin js for this page -->
  <!-- inject:js -->
  <script src="js/off-canvas.js"></script>
  <script src="js/hoverable-collapse.js"></script>
  <script src="js/template.js"></script>
  <script src="js/settings.js"></script>
  <script src="js/todolist.js"></script>
  <!-- endinject -->
  <!-- Custom js for this page-->
  <script src="js/dashboard.js"></script>
  <script src="js/Chart.roundedBarCharts.js"></script>
  <!-- End custom js for this page-->
  <script>
  
    var spc = RegExp(/[~!@#$%^&*()|<>?:{}]/); // RegExp는 정규표현식을 사용하기 위한 객체  , 정규표현식은 /와 /사이에 식을 넣어서 사용한다.
	
    //유효성 
	$('#tryPw').blur(function() {
		if($('#tryPw').val()=='') {
			alert('dddddd');
			$('#tryPwHelper').text('비밀번호를 입력해주세요.');
		} else if(!spc.test($('#tryPw').val())) { // tryPw에 spc가 없다면
			alert('dddddd');
			$('#tryPwHelper').text('비밀번호에 특수문자를 포함해주세요.');
		} else if($('#tryPw').val().length <9) {
			alert('dddddd');
			$('#tryPwHelper').text('비밀번호를 8자 이상 입력해주세요.');
		} else {
			$('#tryPwHelper').text('');
		}
	})
	
	$('#loginPw').blur(function() {
  		 if($('#loginPw').val()=='') {
   			$('#loginPwHelper').text('확인 할 비밀번호를 입력하세요');
  		 } else if($('#tryPw').val() != $('#loginPw').val()) {
   			$('#loginPwHelper').text('비밀번호와 일치하지 않습니다.');
  		 } else {
  			$('#loginPwHelper').text('');
  		 }
  	})
  	
  	// 버튼 눌렀을시 유효성 검사
  	$('#loginPwBtn').click(function() {
  		if($('#tryPw').val()=='') {
  			$('#tryPwHelper').text('비밀번호를 입력해주세요.');
  		} else if(!spc.test($('#tryPw').val())) { // tryPw에 spc가 없다면
  			$('#tryPwHelper').text('비밀번호에 특수문자를 포함해주세요.');
  		} else if($('#tryPw').val().length <9) {
  			$('#tryPwHelper').text('비밀번호를 8자 이상 입력해주세요.');
  		} else if($('#loginPw').val()=='') {
  			$('#tryPwHelper').text('');
  			$('#loginPwHelper').text('확인 할 비밀번호를 입력하세요');
  		} else if($('#tryPw').val() != $('#loginPw').val()) {
  			$('#loginPwHelper').text('비밀번호와 일치하지 않습니다.');
  		} else {
  			$('#loginPwForm').submit();
  		}
  	}
  	
 	// enter키 눌렀을때 유효성 검사
  	$(document).keydown(function(event){
  		if(event.keyCode==13) {
  			event.preventDefault();
  			if($('#tryPw').val()=='') {
  	  			$('#tryPwHelper').text('비밀번호를 입력해주세요.');
  	  		} else if(!spc.test($('#tryPw').val())) { // tryPw에 spc가 없다면
  	  			$('#tryPwHelper').text('비밀번호에 특수문자를 포함해주세요.');
  	  		} else if($('#tryPw').val().length <9) {
  	  			$('#tryPwHelper').text('비밀번호를 8자 이상 입력해주세요.');
  	  		} else if($('#loginPw').val()=='') {
  	  			$('#tryPwHelper').text('');
  	  			$('#loginPwHelper').text('확인 할 비밀번호를 입력하세요');
  	  		} else if($('#tryPw').val() != $('#loginPw').val()) {
  	  			$('#loginPwHelper').text('비밀번호와 일치하지 않습니다.');
  	  		}
  		}
  	}
  </script>
</body>

</html>



