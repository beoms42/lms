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
                  <h1>[강의 시간표]</h1>
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
            <div class="col-lg-11 grid-margin stretch-card">
              <div class="card">
                <div class="card-body">
                  <h4 class="card-title">my 시간표</h4>
                  <p class="card-description">
                  </p>
                  <div class="table-responsive">
                    <h2>${y}년 ${m}월</h2>
	<div>
		<a class="btn bg-dark text-white" href="${pageContext.request.contextPath}/loginCheck/getSheduleListByMonth?y=${y}&m=${m-1}">이전달</a>&nbsp;&nbsp;
		<a class="btn bg-dark text-white" href="${pageContext.request.contextPath}/loginCheck/getSheduleListByMonth?y=${y}&m=${m+1}">다음달</a>
		<div class="float-right bottom">
      		<c:if test="${loginLv > 2}">
      			<button type="button" class="btn bg-dark text-white" data-toggle="modal" data-target="#addScheduleModal">일정추가</button>
      		</c:if>
		</div>
		<!-- addSchedule modal start -->
		<div class="modal fade" id="addScheduleModal" tabindex="-1" role="dialog" aria-labelledby="addScheduleModalLabel" aria-hidden="true">
		  <div class="modal-dialog" role="document">
		    <div class="modal-content">
		      <div class="modal-header">
		        <h5 class="modal-title" id="addScheduleModalLabel">일정추가</h5>
		        <button type="button" id= "close" class="close" data-dismiss="modal" aria-label="Close">
		          <span aria-hidden="true">&times;</span>
		        </button>
		      </div>
		       	 <form id="addScheduleForm" method="post" action="${pageContext.request.contextPath}/loginCheck/addSchedule">
		       	 <div class="modal-body">
		       	 	<span id="addScheduleHelper" class="helper"></span>
		       	 	<div><input id="scheduleDate" type="date" name="scheduleDate"></div>
		       	 	<div>
			       	 	<select id="lectureSubjectNo" name="lectureSubjectNo">
			       	 		<option value="" selected>[강의]과목을 선택하세요.</option>
			       	 		<c:forEach var="a" items="${lectureSubjectList}">
			       	 			<option value="${a.lectureSubjectNo}">[${a.lectureName}]${a.subjectName}</option>
			       	 		</c:forEach>
			       	 	</select>
		       	 	</div>
		       	 	<div><input type="number" name = "m" value="${m}" hidden="hidden"></div>
			       	 <div><input type="number" name="y" value="${y}" hidden="hidden"></div>
				 </div>
				 <div class="modal-footer">
					<button id="addScheduleBtn"type="button" class="btn btn-danger">추가</button>
					<button type="button" id="btn"class="btn btn-secondary" data-dismiss="modal">취소</button>
				 </div>
		      </form>
		    </div>
		  </div>
		</div>
		<!-- addSchedule modal end -->
	<table class="table table-hover">
		<thead>
			<tr>
				<th class="text-danger">일</th>
				<th>월</th>
				<th>화</th>
				<th>수</th>
				<th>목</th>
				<th>금</th>
				<th class="text-primary">토</th>
			</tr>
		</thead>
		<tbody>
			<tr>
				<c:forEach var="i" begin="1" end="${totalTd}" step="1">
					<c:choose>
						<c:when test="${(i - startBlank) > 0 && i <= endDay+startBlank}">
							<c:choose>
								<c:when test="${i%7==0 }">
									<c:set var="e" value="text-primary"/>
								</c:when>
								<c:when test="${i%7==1 }">
									<c:set var="e" value="text-danger"/>
								</c:when>
								<c:otherwise>
									<c:set var="e" value=""/>
								</c:otherwise>
							</c:choose>
							<td class="${e}" style="height : 90px;" width="15%">
								${i - startBlank}
								<div>
									<c:forEach var="c" items="${list}">
									<a href="${pageContext.request.contextPath}/loginCheck/getScheduleOne?scheduleNo=${c.scheduleNo}&m=${m}&y=${y}">
										<c:if test="${(c.scheduleDateDay) ==  (i - startBlank)}">
											<div>
												[${c.lectureName}]
												${c.subjectName}
											</div>
										</c:if>
									</a>
									</c:forEach>
								</div>
							</td>
						</c:when>
						<c:when test="${(i - startBlank) < 1 }">
							<td> </td>
						</c:when>
						<c:when test="${i > endDay}">
							<td> </td>
						</c:when>
					</c:choose>
				<c:if test="${i%7 == 0}">
					</tr><tr>
				</c:if>
				</c:forEach>			
			</tr>
		</tbody>
	</table>
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
	<!-- 유효성 검사-->
	$('#addScheduleBtn').click(function(){
		if($('#scheduleDate').val() == '') {
			$('#addScheduleHelper').text('날짜를 선택하세요.');
		} else if($('#lectureSubjectNo').val() == '') {
			$('#addScheduleHelper').text('[강의]과목을 선택하세요.');
		} else {
			$('#addScheduleHelper').text('');
			$('#addScheduleForm').submit();
		}
	})
	<!-- 일정추가 하다가 취소버튼 누르면 초기화-->
	$('#btn').click(function(){
		$('#scheduleDate').val('')
		$('#lectureSubjectNo').val('')	
	})
	$('#close').click(function(){
		$('#scheduleDate').val('')
		$('#lectureSubjectNo').val('')	
	})
 </script>
</body>
</html>

