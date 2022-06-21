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
        <a class="navbar-brand brand-logo mr-5" href="index.html">LMS-TFT</a>
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
                  <h1>[강의개설]</h1>
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
            <div class="col-12 grid-margin stretch-card">
              <div class="card">
                <div class="card-body">
                  <h4 class="card-title">Basic form elements</h4>
                  <p class="card-description">
                    Basic form elements
                  </p>
                  <form class="forms-sample" method="post" action="${pageContext.request.contextPath}/addLecture">
                    <div class="form-group">
                      <label for="exampleInputName1">개설 강의명</label>
                      <input type="text" class="form-control" id="exampleInputName1" placeholder="강의명" name="lectureName">
                    </div>
                    <div class="form-group">
                      <label for="exampleInputEmail3">강의 기간</label>
                      <br>
                      시작일 : <input type="date" name="lectureStartDate"> 끝나는 날 : <input type="date" name="lectureEndDate">
                    </div>
                    <div class="form-group">
                      <label for="exampleInputPassword4">담당 강사</label>
                      <div class="input-group">
                      
	                      <div class="input-group-prepend">
	                        <select class="teacherNames">
	                          <c:forEach var="n" items="${teacherNameList}">
	                          	<option value="${n}">${n}</option>
	                          </c:forEach>
	                         </select>
	                        </div>
	                      </div>
	                      
                      	<input type="text" class="form-control" aria-label="Text input with dropdown button" readonly="readonly" id="teacherName" name="teacherName">
                   	  </div>
                    </div>
                    <div class="form-group">
                      <label for="exampleInputPassword4">담당 매니저</label>
                      <div class="input-group">
                      
	                      <div class="input-group-prepend">
	                        <select class="managerNames">
	                          <c:forEach var="n" items="${managerNameList}">
	                          	<option value="${n}">${n}</option>
	                          </c:forEach>
	                        </select>
	                      </div>
	                      
                      	<input type="text" class="form-control" aria-label="Text input with dropdown button" readonly="readonly" id="managerName" name="managerName">
                   	  </div>
                   	  
                    </div>                    
                      
                    <div class="form-group">
                      <label for="exampleInputPassword4">강의실</label>
                      <div class="input-group">
	                      <div class="input-group-prepend">
	                      
	                      <select class="lectureRoomNames">
	                          <c:forEach var="n" items="${lectureRoomList}">
	                          	<option value="${n.lectureRoomName}">강의실 : ${n.lectureRoomName} / 정원 : ${n.lectureRoomAdmit}명</option>
	                          </c:forEach>
	                        </select>
	                        
	                        
	                        
	                      </div>
                      	<input type="text" class="form-control" aria-label="Text input with dropdown button" readonly="readonly" id="lectureRoomName" name="lectureRoomName">
                   	  </div>
                    </div>
                    <div class="form-group">
                      <label for="exampleInputCity1">학생 정원</label>
                      <input type="number" class="form-control" id="exampleInputCity1" placeholder="" max="30" name="maxStudent">
                    </div>
                    <div class="form-group">
                      <label for="exampleInputPassword4">강의 개설자</label>
                      <input type="text" class="form-control" id="exampleInputPassword4" value="${loginId}" name="loginId">
                    </div>
                    <button type="submit" class="btn btn-primary mr-2">Submit</button>
                    <button class="btn btn-light">Cancel</button>
                  </form>
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
  <script type="text/javascript">
	$('.clickTeacherMenu').click(function() {
			console.log('dsdsdsdsdsd');
			$('#teacherName').val($('.clickTeacherMenu').text());
	});
	
	$('.teacherNames').change(function() {
	    var value = $(this).val();
	    $('#teacherName').val(value);
	});
	
	$('.managerNames').change(function() {
	    var value = $(this).val();
	    $('#managerName').val(value);
	});
	
	$('.lectureRoomNames').change(function() {
	    var value = $(this).val();
	    $('#lectureRoomName').val(value);
	});
  </script>
</body>
</html>

