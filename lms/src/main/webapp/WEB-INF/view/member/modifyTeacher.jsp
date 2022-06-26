<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
  <script  src="http://code.jquery.com/jquery-latest.min.js"></script>
</head>
<body>
  <div class="container-scroller">
    <!-- partial:partials/_navbar.html -->
    <nav class="navbar col-lg-12 col-12 p-0 fixed-top d-flex flex-row">
      <div class="text-center navbar-brand-wrapper d-flex align-items-center justify-content-center">
        <a class="navbar-brand brand-logo mr-5" href="${pageContext.request.contextPath}/loginCheck/main">LMS-TFT</a>
        <a class="navbar-brand brand-logo-mini" href="index.html">LMS</a>
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
              <img src="${pageContext.request.contextPath}/images/tftace.jpg" alt="profile"/>
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
                  <h3>수정</h3>
                </div>
                <div class="col-12 col-xl-4">
                 <div class="justify-content-end d-flex">
                 </div>
                </div>
              </div>
            </div>
          </div>
              
          <!-- 강의개설 실제부분 --> 
          <div class="row">
            <div class="col-lg-10 grid-margin stretch-card">
              <div class="card">
                <div class="card-body">
                  <h4 class="card-title"><img src="<%=request.getContextPath()%>/images/member/${fileName}" width="100" height="100">${teacher.teacherName}강사님</h4>
                  <p class="card-description">
                  </p>   
                  <div class="table-responsive">
                   <form method="post" action="${pageContext.request.contextPath}/loginCheck/modifyTeacher">
                    <table class="table">
                      <thead>
                        <tr>
                          <td>프로필 사진</td>
                          <td><img src="<%=request.getContextPath()%>/images/member/${fileName}" width="500" height="500"></td>
                        </tr> 
                        <tr>
                          <td>ID</td>
                          <td><input type="text" name="loginId" value="${teacher.loginId}" readonly="readonly"></td>
                        </tr> 
                       <tr> 
                          <td>성명</td>
                          <td><input type="text" name="teacherName" value="${teacher.teacherName}"></td>
                        </tr>  
                        <tr>
                          <td>생년월일</td>
                          <td><input type="date" name="teacherBirth" value="${teacher.teacherBirth}"></td>
                        </tr>
                         <tr> 
                          <td>성별</td>
                          <td>
                        	<select name="teacherGender">
                                <option value="남">남</option>
                                <option value="여">여</option>
                            </select>
                            </td>
                        </tr>
                           <tr>
				          <td>주소</td>
				          <div class="form-group">
				            <td><input type="text" class="form-control button-bottom" placeholder="${teacher.address}" id="addr">
				            <button type="button" class="float-right btn btn-primary mr-2 button-bottom" id="searchAddr">주소 검색</button>
				            <div id="addrHelper"></div>
						  </div>
			            	<select name="address"  class = "form-control" id="searchAddrList">
			            		<!-- 주소 들어올 공간 -->
			            	</select>
			            	</td>
                        <tr>  
                          <td>상세주소</td>
                          <td><input type="text" name="detailAddr" value="${teacher.detailAddr}"></td>
                        </tr>
                        <tr> 
                          <td>E-mail</td>
                          <td><input type="text" name="teacherEmail" value="${teacher.teacherEmail}"></td>
                        </tr>
                        <tr>  
                          <td>연락처</td>
                          <td><input type="text" name="teacherPhone" value="${teacher.teacherPhone}"></td>
                       </tr>
                        <tr>
                          <td>학력</td>
                          <td>
                        	<select name="graduate">
                                <option value="고졸">고졸</option>
                                <option value="초대졸">초대졸</option>
                                <option value="대졸">대졸</option>
                            </select>
                            </td>
                        </tr>
                        
                      </thead>
                    </table>
                     	<div>
                     	<input class="btn btn-primary"  type ="submit" value="수정">
					   <a href="#"><button class="btn btn-danger"href="${pageContext.request.contextPath}/deleteTeacherOne?loginId=${manager.loginId}">회원탈퇴</button></a>
					  </form>
					</div>
                  </div>
                </div>
              </div>
            </div>
          </div>
        <!-- content-wrapper ends -->
        <!-- partial:partials/_footer.html -->
        <!-- partial -->
      </div>
      <!-- main-panel ends -->
    </div>   
    <!-- page-body-wrapper ends -->
  </div>
  <!-- container-scroller -->

  <!-- plugins:js -->
  <script src="${pageContext.request.contextPath}/vendors/js/vendor.bundle.base.js"></script>
  <!-- endinject -->
  <!-- Plugin js for this page -->
  <script src="${pageContext.request.contextPath}/vendors/chart.js/Chart.min.js"></script>
  <script src="${pageContext.request.contextPath}/vendors/datatables.net/jquery.dataTables.js"></script>
  <script src="${pageContext.request.contextPath}/vendors/datatables.net-bs4/dataTables.bootstrap4.js"></script>
  <script src="${pageContext.request.contextPath}/js/dataTables.select.min.js"></script>

  <!-- End plugin js for this page -->
  <!-- inject:js -->
  <script src="${pageContext.request.contextPath}/js/off-canvas.js"></script>
  <script src="${pageContext.request.contextPath}/js/hoverable-collapse.js"></script>
  <script src="${pageContext.request.contextPath}/js/template.js"></script>
  <script src="${pageContext.request.contextPath}/js/settings.js"></script>
  <script src="${pageContext.request.contextPath}/js/todolist.js"></script>
  <!-- endinject -->
  <!-- Custom js for this page-->
  <script src="${pageContext.request.contextPath}/js/dashboard.js"></script>
  <script src="${pageContext.request.contextPath}/js/Chart.roundedBarCharts.js"></script>
  <!-- End custom js for this page-->
<script>
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
</body>
</html>
