<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>
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
  <link rel="stylesheet" href="${pageContext.request.contextPath}/vendors/datatables.net-bs4/dataTables.bootstrap4.css">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/vendors/ti-icons/css/themify-icons.css">
  <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/js/select.dataTables.min.css">
  <!-- End plugin css for this page -->
  <!-- inject:css -->
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/vertical-layout-light/style.css">
  <!-- endinject -->
  <link rel="shortcut icon" href="${pageContext.request.contextPath}/images/tftace.jpg" />
  <style>
	.bottom {margin-bottom : 40px;}
	.top {margin-top : 30px;}
  </style>
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
	         <h1 class="bottom">[출결관리]</h1>
	          <div class="row">
	            <div class="col-lg-10 grid-margin stretch-card">
	              <div class="card">
	                <div class="card-body">
		                <h2>강의명 [${lectureName}]</h2>
		                <form id="attendanceDateForm" method="get" action="${pageContext.request.contextPath}/loginCheck/getAttendanceList">
		                	 <h3 class="float-right bottom">
		                	 <c:choose>
		                	 	<c:when test="${list[0].scheduleDate != null}">
		                	 		<input id="scheduleDate" type="date" name="scheduleDate" value="${list[0].scheduleDate}">
		                	 	</c:when>
		                	 	<c:when test="${ckDate ne ''}">
		                	 		<input id="scheduleDate" type="date" name="scheduleDate" value="${ckDate}">
		                	 	</c:when>
		                	 	<c:otherwise>
		                	 		<jsp:useBean id="now" class="java.util.Date" />
		                	 		<fmt:formatDate value="${now}" pattern="yyyy-MM-dd" var="date"/>
		                	 		<input id="scheduleDate" type="date" name="scheduleDate" value="${date}">
		                	 	</c:otherwise>
		                	 </c:choose>
		                	</h3>
		                </form>
		                <form method="get" action="${pageContext.request.contextPath}/loginCheck/modifyAttendanceList">
		                	<input type="hidden" name="scheduleDate" value="${list[0].scheduleDate}">
			                <table class="table">
		                		<thead>
		                			<tr>
		                				<th>학생아이디</th>
		                				<th>학생이름</th>
		                				<th>출결</th>
		                			</tr>
		                		</thead>
		                		<tbody> 
			                		<c:forEach var="a" items="${list}">
			                			<tr>
			                				<td>${a.loginId}</td>
		                					<td>${a.studentName}</td>
		                					<td>${a.attendanceRecord}</td>
		                				</tr>
			                		</c:forEach>
		                		</tbody>
		                	</table>
		                	<c:choose>
		                		<c:when test="${fn:length(list)==0}">
                					<h3 class="text-center top">해당 날짜와 일치하는 출결 데이터가 없습니다.</h3>
		                		</c:when>
		                		<c:otherwise>
		                			<button class="btn btn-primary float-right top">출결 변경</button>	
		                		</c:otherwise>
		                	</c:choose>
		                </form>
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
  </div>
  <!-- container-scroller -->

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
	$('#scheduleDate').change(function() {
		$('#attendanceDateForm').submit();		
	})
			
  </script>
</body>
</html>

