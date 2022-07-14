<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<!-- Required meta tags -->
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<title>LMS-TFT</title>
<!-- plugins:css -->
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/vendors/feather/feather.css">
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/vendors/ti-icons/css/themify-icons.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/vendors/css/vendor.bundle.base.css">
<!-- endinject -->
<!-- Plugin css for this page -->
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/vendors/aables.net-bs4/aables.bootstrap4.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/vendors/ti-icons/css/themify-icons.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/js/select.aables.min.css">
<!-- End plugin css for this page -->
<!-- inject:css -->
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/vertical-layout-light/style.css">
<!-- endinject -->
<link rel="shortcut icon"
	href="${pageContext.request.contextPath}/images/tftace.jpg" />
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<style type="text/css">/* Chart.js */
@
keyframes chartjs-render-animation {
	from {opacity: .99
}

to {
	opacity: 1
}

}
.chartjs-render-monitor {
	animation: chartjs-render-animation 1ms
}

.chartjs-size-monitor, .chartjs-size-monitor-expand,
	.chartjs-size-monitor-shrink {
	position: absolute;
	direction: ltr;
	left: 0;
	top: 0;
	right: 0;
	bottom: 0;
	overflow: hidden;
	pointer-events: none;
	visibility: hidden;
	z-index: -1
}

.chartjs-size-monitor-expand>div {
	position: absolute;
	width: 1000000px;
	height: 1000000px;
	left: 0;
	top: 0
}

.chartjs-size-monitor-shrink>div {
	position: absolute;
	width: 200%;
	height: 200%;
	left: 0;
	top: 0
}
</style>

</head>

<body>
	<div class="container-scroller">
		<!-- partial:partials/_navbar.html -->
		<jsp:include page="/inc/topbar.jsp" />
		<!-- partial -->
		<div class="container-fluid page-body-wrapper">
			<!-- partial:partials/_settings-panel.html -->

			<!-- partial -->
			<!-- partial:partials/_sidebar.html -->
			<nav class="sidebar sidebar-offcanvas" id="sidebar">
				<jsp:include page="/inc/sidebar.jsp" />
			</nav>

			<!-- partial -->

			<div class="content-wrapper">
				<div class="row">
					<div class="main-panel">
					
						<div class="col-lg-6 grid-margin stretch-card">
							<div class="card">
								<div class="card-body">
									<div >
										<select id="statsSelect">
											<option value="/lms/genderStats" >남녀 비율</option>
											<option value="/lms/addAvgScore">반 점수 평균</option>
											<option value="/lms/addDropList">반 중도 탈퇴 인원 수</option>
											<option value="/lms/ageAvg">반 나이 평균</option>
											<option value="/lms/addPerClass">반 당 인원 수</option>
											<option value="/lms/graduate">학력 비교</option>
											<option value="/lms/militaryStatus" selected="selected">군필 여부</option>
										</select>
							
									</div>
									<div id="myChart" width="100%"></div>
								</div>
							</div>
						</div>
						<!-- main-panel ends -->
					</div>
					<!-- page-body-wrapper ends -->
				</div>
				<!-- container-scroller -->
				<!-- plugins:js -->
				<script
					src="${pageContext.request.contextPath}/vendors/js/vendor.bundle.base.js"></script>
				<!-- endinject -->
				<!-- Plugin js for this page -->
				<script
					src="${pageContext.request.contextPath}/vendors/chart.js/Chart.min.js"></script>
				<!-- End plugin js for this page -->
				<!-- inject:js -->
				<script src="${pageContext.request.contextPath}/js/off-canvas.js"></script>
				<script
					src="${pageContext.request.contextPath}/js/hoverable-collapse.js"></script>
				<script src="${pageContext.request.contextPath}/js/template.js"></script>
				<script src="${pageContext.request.contextPath}/js/settings.js"></script>
				<script src="${pageContext.request.contextPath}/js/todolist.js"></script>
				<!-- endinject -->
				<!-- Custom js for this page-->
				<!-- End custom js for this page-->
				<script type="text/javascript"
					src="https://www.gstatic.com/charts/loader.js"></script>
 <script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
 <script>
		  //차트에서 사용할 모델(데이터)로 가공
	
	
		
		$('#statsSelect').change(function() {
			
			var url = $(this).val();
		
		// 데이터를 호출
		$(function(){
		var myData =  [['lable','data']];
		$.ajax({
		   type:'get'
		   ,url: url
		   ,success:function(jsonData) {
		      for(var i=0; i<jsonData.length; i++) {
		       
		         myData.push([jsonData[i].lable,jsonData[i].data])
		      }
		
		
		console.log(myData);
		
		
		// 차트를 그리는 로직
		
		google.charts.load('current', {'packages':['corechart']});
		google.charts.setOnLoadCallback(drawChart);
		
		function drawChart() {
		   var data = google.visualization.arrayToDataTable(myData);
			
		      var options = {
		    	        width: 600,
		    	        height: 400,
		    	        legend: { position: 'top', maxLines: 3 },
		    	        bar: { groupWidth: '75%' },
		    	        isStacked: true,
		    	      };
			var chart = new google.visualization.ColumnChart(document.getElementById('myChart'));
			  chart.draw(data, options);
			};

		
		}
		});
		});
		});
	
	

</script>

</body>
</html>