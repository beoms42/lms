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
  <style>
  	.helper {
  		color : #FF0000;
  	}
  </style>
</head>
<body>
  <div class="container-scroller">
    <!-- partial:partials/_navbar.html -->
    <nav class="navbar col-lg-12 col-12 p-0 fixed-top d-flex flex-row">
      <div class="text-center navbar-brand-wrapper d-flex align-items-center justify-content-center">
        <a class="navbar-brand brand-logo mr-5" href="${pageContext.request.contextPath}/index.html">LMS-TFT</a>
        <a class="navbar-brand brand-logo-mini" href="${pageContext.request.contextPath}/index.html">LMS</a>
      </div>
      <div class="navbar-menu-wrapper d-flex align-items-center justify-content-end">
        <button class="navbar-toggler navbar-toggler align-self-center" type="button" data-toggle="minimize">
          <span class="icon-menu"></span>
        </button>
        <ul class="navbar-nav mr-lg-2">
          <li class="nav-item nav-search d-none d-lg-block">
            <div class="input-group">
            	
            </div>
          </li>
        </ul>
        <ul class="navbar-nav navbar-nav-right">
          <li class="nav-item dropdown">
            <a class="nav-link count-indicator dropdown-toggle" id="notificationDropdown" href="#" data-toggle="dropdown">
              <i class="icon-bell mx-0"></i>
              <span class="count"></span>
            </a>
            <div class="dropdown-menu dropdown-menu-right navbar-dropdown preview-list" aria-labelledby="notificationDropdown">
              <p class="mb-0 font-weight-normal float-left dropdown-header">Notifications</p>
              <a class="dropdown-item preview-item">
                <div class="preview-thumbnail">
                  <div class="preview-icon bg-success">
                    <i class="ti-info-alt mx-0"></i>
                  </div>
                </div>
                <div class="preview-item-content">
                  <h6 class="preview-subject font-weight-normal">Application Error</h6>
                  <p class="font-weight-light small-text mb-0 text-muted">
                    Just now
                  </p>
                </div>
              </a>
              <a class="dropdown-item preview-item">
                <div class="preview-thumbnail">
                  <div class="preview-icon bg-warning">
                    <i class="ti-settings mx-0"></i>
                  </div>
                </div>
                <div class="preview-item-content">
                  <h6 class="preview-subject font-weight-normal">Settings</h6>
                  <p class="font-weight-light small-text mb-0 text-muted">
                    Private message
                  </p>
                </div>
              </a>
              <a class="dropdown-item preview-item">
                <div class="preview-thumbnail">
                  <div class="preview-icon bg-info">
                    <i class="ti-user mx-0"></i>
                  </div>
                </div>
                <div class="preview-item-content">
                  <h6 class="preview-subject font-weight-normal">New user registration</h6>
                  <p class="font-weight-light small-text mb-0 text-muted">
                    2 days ago
                  </p>
                </div>
              </a>
            </div>
          </li>
          <li class="nav-item nav-profile dropdown">
            <a class="nav-link dropdown-toggle" href="#" data-toggle="dropdown" id="profileDropdown">
              <img src="images/tftace.jpg" alt="profile"/>
            </a>
            <div class="dropdown-menu dropdown-menu-right navbar-dropdown" aria-labelledby="profileDropdown">
              <a class="dropdown-item">
                <i class="ti-settings text-primary"></i>
                Settings
              </a>
              <a class="dropdown-item">
                <i class="ti-power-off text-primary"></i>
                Logout
              </a>
            </div>
          </li>
        </ul>
        <button class="navbar-toggler navbar-toggler-right d-lg-none align-self-center" type="button" data-toggle="offcanvas">
          <span class="icon-menu"></span>
        </button>
      </div>
    </nav>
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
                  <h1>개인정보 수정하기</h1>
                  <form method="post" action="${pageContext.request.contextPath}/loginCheck/modifyStudent" id="modifyForm">
	                  <table class="table">
						<tr>
							<th>사진</th>
							<td><img src="${pageContext.request.contextPath}/file/memberPhoto/${memberFile.memberFileName}"></td>
						</tr>
						<tr>
							<th>아이디</th>
							<td><input type="text" name="loginId" value="${student.loginId}" readonly="readonly"></td>
						</tr>
						<tr>
							<th>이름</th>
							<td>
								<input type="text" name="studentName" value="${student.studentName}" id="name" placeholder="이름">&nbsp;&nbsp;&nbsp;
								<span id="nameHelper" class="helper"></span>
							</td>
						</tr>
						<tr>
							<th>생년월일</th>
							<td><input type="date" name="studentBirth" value="${student.studentBirth}"></td>
						</tr>
						<tr>
							<th>성별</th>
							<td>
								<input type="radio" name="studentGender" <c:if test="${student.studentGender eq '남'}">checked</c:if> value="남"/>남&nbsp;
								<input type="radio" name="studentGender" <c:if test="${student.studentGender eq '여'}">checked</c:if> value="여"/>여
							</td>
						</tr>
					 <tr>
				          <th>주소</th>
				          <div class="form-group">
				            <td><input type="text" class="form-control button-bottom" placeholder="${student.address}" id="addr">
				            <button type="button" class="float-right btn btn-primary mr-2 button-bottom" id="searchAddr">주소 검색</button>
				            <div id="addrHelper"></div>
						  </div>
			            	<select name="address"  class = "form-control" id="searchAddrList">
			            		<!-- 주소 들어올 공간 -->
			            	</select>
			            	</td>
						<tr>
							<th>상세주소</th>
							<td><input type="text" value="${student.detailAddress}" name="detailAddress"></td>
						</tr>
						<tr>
							<th>이메일</th>
							<td>
								<input type="text" value="${student.studentEmail}" name="studentEmail" id="email" placeholder="이메일">&nbsp;&nbsp;&nbsp;
								<span id="emailHelper" class="helper"></span>
							</td>
						</tr>
						<tr>
							<th>전화번호</th>
							<td>
								<input type="text" value="${student.studentPhone}" name="studentPhone" id="phone" placeholder="-를 제외해서 입력해주세요." >&nbsp;&nbsp;&nbsp;
								<span id="phoneHelper" class="helper"></span>
							</td>
						</tr>
						<tr>
							<th>병역유무</th>
							<td>
								<input type="radio" name="militaryStatus" <c:if test="${student.militaryStatus eq '군필'}">checked</c:if> value="군필"/>군필&nbsp;
								<input type="radio" name="militaryStatus" <c:if test="${student.militaryStatus eq '미필'}">checked</c:if> value="미필"/>미필&nbsp;
								<input type="radio" name="militaryStatus" <c:if test="${student.militaryStatus eq '해당없음'}">checked</c:if> value="해당없음"/>해당없음
							</td>
						</tr>
						<tr>
							<th>학력</th>
							<td>
								<input type="radio" name="graduate" <c:if test="${student.graduate eq '고졸'}">checked</c:if> value="고졸"/>고졸&nbsp;
								<input type="radio" name="graduate" <c:if test="${student.graduate eq '초대졸'}">checked</c:if> value="초대졸"/>초대졸&nbsp;
								<input type="radio" name="graduate" <c:if test="${student.graduate eq '대졸'}">checked</c:if> value="대졸"/>대졸
							</td>
						</tr>
					</table>
					<button id="modify">수정</button>
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
</body>
<script>
	$('#name').blur(function() {
		if($('#name').val().length == 0 ) {
			$('#nameHelper').text('이름을 입력해주세요');
		} else {
			$('#nameHelper').text('');
		}
	});
	$('#email').blur(function() {
		if($('#email').val().length == 0 ) {
			$('#emailHelper').text('이메일을 입력해주세요');
		} else {
			$('#emailHelper').text('');
		}
	});
	$('#phone').blur(function(){
		if($('#phone').val().indexOf('-') != -1 ){
			$('#phone').val().replace(/-/g, ''); // 입력한 '-' 한자리 지움
		   	$('#phoneHelper').text('-를 제외해서 입력해주세요.');
		}
	});
	
	$('#modify').click(function() {
		if($('#name').val().length == 0 ) {
			$('#nameHelper').text('이름을 입력해주세요');
		} else if($('#email').val().length == 0 ) {
			$('#emailHelper').text('이메일을 입력해주세요');
		} else if($('#phone').val().indexOf('-') != -1 ){
			$('#phone').val().replace(/-/g, ''); // 입력한 '-' 한자리 지움
		   	$('#phoneHelper').text('-를 제외해서 입력해주세요.');
		} else {
			$('modifyForm').submit();
		}
	})
  var url="${pageContext.request.contextPath}";
	// 주소검색
	$('#searchAddrList').hide();
	$('#searchAddr').click(function() {
		$('#searchAddrList').show();
		$('#searchAddrList').empty();
		if($('#addr').val().length > 1) {
			$.ajax({
				type:'get'
				, url : url+'/searchAddr'
				, data : {keyword:$('#addr').val()} //검색한 키워드
				, success:function(a){
					console.log(a);
					console.log(typeof(a));
					var a2 = JSON.parse(a);
					console.log(typeof(a2));
					console.log(a2);
					
					let arr = a2.results.juso;
					console.log(arr);
					for(let i=0; i<arr.length; i++) {
						$('#searchAddrList').append('<option>'+arr[i].roadAddrPart1+'</option>');
					}
				}
		})
		} else {
			$('#searchAddrList').val('');
			$('#addrHelper').text('검색할 주소를 입력해주세요.');
		}
	});
		// enter키 눌렀을때 유효성 검사
  	$(document).keydown(function(event){
  		if(event.keyCode==13) {
  			event.preventDefault();
  			if($('#realId').val()=='') {
  	  			alert('아이디 중복 검사 해주세요');
  	  		} else if($('#pw').val()=='') {
  	  			$('#pwHelper').text('비밀번호를 입력해주세요.');
  	  		} else if($('#name').val()=='') {
  	  			$('#pwHelper').text('');
  	  			$('#nameHelper').text('이름을 입력해주세요.');
  	  		} else if($('#birth').val()==''){
  	  			$('#nameHelper').text('');
  	  			$('#birthHelper').text('생년월일을 입력해주세요.');
  	  		} else if($('#email').val()=='') {
  	  			$('#nameHelper').text('');
  	  			$('#emailHelper').text('이메일을 입력해주세요.');
  	  		} else if($('#email').val().indexOf('@') == -1 || $('#email').val().indexOf('.') == -1) {
  	  			$('#emailHelper').text('');	
  	  			$('#emailHelper').text('이메일 형식이 다릅니다.');	
  	  		} else if($('#phone').val()=='') {
  	  			$('#emailHelper').text('');
  	  			$('#phoneHelper').text('휴대폰 번호를 입력해주세요.');
  	  		} else if($('#phone').val().indexOf('-') != -1) {
  	  			$('#phoneHelper').text('');
  	  			$('#phoneHelper').text('-을 제외해서 입력해주세요.');
  	  		} else if($('#addr').val()=='') {
  	  			$('#phoneHelper').text('');
  	  			$('#addrHelper').text('주소를 검색해주세요.');
  	  		} else if($('#detailAddr').val()=='') {
  	  			$('#addrHelper').text('');
  	  			$('#detailAddrHelper').text('상세 주소를 입력해주세요.');
  	  		} else if($('#customFile').val()=='') {
  	  			$('#detailAddrHelper').text('');
  	  			$('#imageHelper').text('사진을 등록해주세요.');
  	  		} else {
  	  			$('#addMemberForm').submit();
  	  		}
  		};
  	})
  			
  	// 주소검색
  	$('#searchAddr').click(function() {
  		if($('#addr').val().length > 1) {
  			$.ajax({
  				type:'get'
  				, url : url+'/searchAddr'
  				, data : {keyword:$('#addr').val()} //검색한 키워드
  				, success:function(a){
  					console.log(a);
  					console.log(typeof(a));
  					var a2 = JSON.parse(a);
  					console.log(typeof(a2));
  					console.log(a2);
  					
  					let arr = a2.results.juso;
  					console.log(arr);
  					for(let i=0; i<arr.length; i++) {
  						$('#searchAddrList').append('<option>'+arr[i].roadAddrPart1+'</option>');
  					}
  				}
  			})
  		} else {
  			$('#searchAddrList').val('');
  			$('#addrHelper').text('검색할 주소를 입력해주세요.');
  		}
  	});
</script>

</html>




	