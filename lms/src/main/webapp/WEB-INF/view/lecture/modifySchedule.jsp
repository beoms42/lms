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
                  <h3>시간표수정</h3>
                  <form id="modifyForm" method="post" action="${pageContext.request.contextPath}/loginCheck/modifySchedule">
           			<!-- 히든으로 년월 번호 보내기 -->
           			<input type="hidden" name="m" value="${m}">
					<input type="hidden" name="y" value="${y}">
					<input type="number" name="scheduleNo" value="${scheduleNo}" hidden="hidden">
                  <table class="table">
						<tr>
							<td>
								<span id="modifyFormHelper" class="helper"></span>
							</td>
						</tr>
						<tr>
							<th>날짜</th>
							<td>
								<input class = "form-control" id="scheduleDate" type="date" name="scheduleDate" value="${scheduleDate}">
							</td>
						</tr>
				     	<tr>
				     		<th>[강의과목]</th>
					     	<td>
					     	 	<select  class = "form-control" id="lectureSubjectNo" name="lectureSubjectNo">
					     	 		<option value="">[강의]과목을 선택하세요.</option>
					     	 		<c:forEach var="a" items="${lectureSubjectList}">
					     	 			<option value="${a.lectureSubjectNo}"
						     	 			<c:if test ="${a.lectureSubjectNo eq lectureSubjectNo}">selected</c:if>>
					     	 				[${a.lectureName}]${a.subjectName}
					     	 			</option>
					     	 		</c:forEach>
					     	 	</select>
					    	</td>
				    	</tr>
				</table>
					<div><button class="btn btn-primary" type="button" id="btn">수정</button>
				</form>
					<a class="btn btn-Warning" href="${pageContext.request.contextPath}/loginCheck/getScheduleOne?scheduleNo=${c.scheduleNo}&m=${m}&y=${y}">취소</a></div>
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
	<!-- 유효성 검사 -->
	$('#btn').click(function(){
		if($('#scheduleDate').val() == '') {
			$('#modifyFormHelper').text('날짜를 선택하세요.');
		} else if($('#lectureSubjectNo').val() == '') {
			$('#modifyFormHelper').text('[강의]과목을 선택하세요.');
		} else {
			$('#modifyFormHelper').text('');
			$('#modifyForm').submit();
		}
	});
</script>
</html>




	