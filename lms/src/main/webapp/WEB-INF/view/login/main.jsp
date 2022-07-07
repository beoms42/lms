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
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/vendors/ti-icons/css/themify-icons.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/vendors/css/vendor.bundle.base.css">
<!-- endinject -->
<!-- Plugin css for this page -->
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/vendors/datatables.net-bs4/dataTables.bootstrap4.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/vendors/ti-icons/css/themify-icons.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/js/select.dataTables.min.css">
<!-- End plugin css for this page -->
<!-- inject:css -->
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/vertical-layout-light/style.css">
<!-- endinject -->
<link rel="shortcut icon"
	href="${pageContext.request.contextPath}/images/tftace.jpg" />
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
			<div class="main-panel">
				<div class="content-wrapper">
					<div class="row">
						<div class="col-md-5 grid-margin stretch-card">
							<div class="card tale-bg">
								<div class="card-people mt-auto">
									<img
										src="${pageContext.request.contextPath}/images/dashboard/people.svg"
										alt="people">
									<div class="weather-info">
										<div class="d-flex">
											<div>
												<h4>${year}년${month}월${day}일${dayOfWeek}</h4>
												<br>
												<div style="display: flex;">
													<h2 class="mb-0 font-weight-normal">
														<span id="weather"></span><span id="tmp"></span><sup>ºC</sup>
													</h2>
													<div style="display: block;">
														<h4 class="location font-weight-normal float-right">금천구</h4>
														<br>
														<h4 class="font-weight-normal float-right">가산동</h4>
													</div>
												</div>
											</div>
										</div>
									</div>
								</div>
							</div>
						</div>
									<div class="col-lg-6 grid-margin stretch-card">
										<div class="card">
											<div class="card-body">
												<div class="chartjs-size-monitor">
													<div class="chartjs-size-monitor-expand">
														<div class=""></div>
													</div>
													<div class="chartjs-size-monitor-shrink">
														<div class=""></div>
													</div>
												</div>
												<h4 class="card-title">Grade Average</h4>
												<canvas id="barChart"
													style="display: block; width: 642px; height: 321px;"
													width="642" height="321" class="chartjs-render-monitor"></canvas>
											</div>
										</div>
									</div>
								
								<div class="main-panel">
									<div class="content-wrapper">
										<div class="row">
											<div class="col-lg-12 grid-margin">
												<div class="card">
													<div class="card-body">
														<h4 class="card-title">일자리 공고</h4>
														<div id="addJobListDivOne" class="row"></div>
													</div>

												</div>
												<div style="text-align: center;">
													<button id="reducePage" class="btn btn-outline-dark btn-fw">이전</button>
													<button id="addPage" class="btn btn-outline-dark btn-fw">다음</button>
												</div>
											</div>
										</div>
									</div>
								</div>
							</div>
							<!-- content-wrapper ends -->
					<!-- partial:partials/_footer.html -->
					<jsp:include page="/inc/footer.jsp" />
					<!-- partial -->
				</div>
				<!-- main-panel ends -->
			</div>
		</div>
	</div>
	<!-- plugins:js -->
	<script
		src="${pageContext.request.contextPath}/vendors/js/vendor.bundle.base.js"></script>
	<!-- endinject -->
	<!-- Plugin js for this page -->
	<script
		src="${pageContext.request.contextPath}/vendors/chart.js/Chart.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/vendors/datatables.net/jquery.dataTables.js"></script>
	<script
		src="${pageContext.request.contextPath}/vendors/datatables.net-bs4/dataTables.bootstrap4.js"></script>
	<script
		src="${pageContext.request.contextPath}/js/dataTables.select.min.js"></script>

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
	<script src="${pageContext.request.contextPath}/js/dashboard.js"></script>
	<script
		src="${pageContext.request.contextPath}/js/Chart.roundedBarCharts.js"></script>
	<!-- End custom js for this page-->
	<script type="text/javascript">
		$
				.ajax({
					url : '/lms/weather',
					type : 'get',
					timeout : 30000,
					contentType : 'application/json',
					dataType : 'json',
					success : function(data, status, xhr) {
						let dataHeader = data.result.response.header.resultCode;
						if (dataHeader == '00') {
							console.log('success ==>');
							console.log(data);
							let arr = data.result.response.body.items.item;

							if (arr[7].fcstValue >= 80) { // 습도가 80퍼 이상일 때
								if (arr[6].fcstValue == 0) { // 비가 아닐 때
									if (arr[5].fcstValue == 1) { // 맑음 상태일 때
										$('#weather')
												.append(
														'<i class="icon-sun mr-2" width="20"></i>');
									} else { // 흐림 상태일 때
										$('#weather')
												.append(
														'<i class="icon-cloud mr-2" width="20"></i>');
									}
								} else if (arr[6].fcstValue == 1) { // 비 상태일 때
									$('#weather')
											.append(
													'<i class="icon-umbrella mr-2" width="20"></i>');
								} else if (arr[6].fsctValue == 2
										|| arr[6].fsctValue == 3) { // 비/눈 or 눈/비 상태일 때
									if (arr[0].fcstValue < -5) { // 기온이 -5도 이하일 때
										$('#weather')
												.append(
														'<svg xmlns="http://www.w3.org/2000/svg" width="20" height="20" fill="currentColor" class="bi bi-snow" viewBox="0 0 16 16"><path d="M8 16a.5.5 0 0 1-.5-.5v-1.293l-.646.647a.5.5 0 0 1-.707-.708L7.5 12.793V8.866l-3.4 1.963-.496 1.85a.5.5 0 1 1-.966-.26l.237-.882-1.12.646a.5.5 0 0 1-.5-.866l1.12-.646-.884-.237a.5.5 0 1 1 .26-.966l1.848.495L7 8 3.6 6.037l-1.85.495a.5.5 0 0 1-.258-.966l.883-.237-1.12-.646a.5.5 0 1 1 .5-.866l1.12.646-.237-.883a.5.5 0 1 1 .966-.258l.495 1.849L7.5 7.134V3.207L6.147 1.854a.5.5 0 1 1 .707-.708l.646.647V.5a.5.5 0 1 1 1 0v1.293l.647-.647a.5.5 0 1 1 .707.708L8.5 3.207v3.927l3.4-1.963.496-1.85a.5.5 0 1 1 .966.26l-.236.882 1.12-.646a.5.5 0 0 1 .5.866l-1.12.646.883.237a.5.5 0 1 1-.26.966l-1.848-.495L9 8l3.4 1.963 1.849-.495a.5.5 0 0 1 .259.966l-.883.237 1.12.646a.5.5 0 0 1-.5.866l-1.12-.646.236.883a.5.5 0 1 1-.966.258l-.495-1.849-3.4-1.963v3.927l1.353 1.353a.5.5 0 0 1-.707.708l-.647-.647V15.5a.5.5 0 0 1-.5.5z"/></svg>');
									} else { // 기온이 -5도 이상일 때
										$('#weather')
												.append(
														'<i class="icon-umbrella mr-2" width="20"></i>');
									}
								} else { // 눈 상태일때
									$('#weather')
											.append(
													'<svg xmlns="http://www.w3.org/2000/svg" width="20" height="20" fill="currentColor" class="bi bi-snow" viewBox="0 0 16 16"><path d="M8 16a.5.5 0 0 1-.5-.5v-1.293l-.646.647a.5.5 0 0 1-.707-.708L7.5 12.793V8.866l-3.4 1.963-.496 1.85a.5.5 0 1 1-.966-.26l.237-.882-1.12.646a.5.5 0 0 1-.5-.866l1.12-.646-.884-.237a.5.5 0 1 1 .26-.966l1.848.495L7 8 3.6 6.037l-1.85.495a.5.5 0 0 1-.258-.966l.883-.237-1.12-.646a.5.5 0 1 1 .5-.866l1.12.646-.237-.883a.5.5 0 1 1 .966-.258l.495 1.849L7.5 7.134V3.207L6.147 1.854a.5.5 0 1 1 .707-.708l.646.647V.5a.5.5 0 1 1 1 0v1.293l.647-.647a.5.5 0 1 1 .707.708L8.5 3.207v3.927l3.4-1.963.496-1.85a.5.5 0 1 1 .966.26l-.236.882 1.12-.646a.5.5 0 0 1 .5.866l-1.12.646.883.237a.5.5 0 1 1-.26.966l-1.848-.495L9 8l3.4 1.963 1.849-.495a.5.5 0 0 1 .259.966l-.883.237 1.12.646a.5.5 0 0 1-.5.866l-1.12-.646.236.883a.5.5 0 1 1-.966.258l-.495-1.849-3.4-1.963v3.927l1.353 1.353a.5.5 0 0 1-.707.708l-.647-.647V15.5a.5.5 0 0 1-.5.5z"/></svg>');
								}
							} else { // 습도가 80퍼 미만일 때
								if (arr[5].fcstValue == 1) { // 맑음 상태일 때
									$('#weather')
											.append(
													'<i class="icon-sun mr-2" width="20"></i>');
								} else { // 흐림 상태일 때
									$('#weather')
											.append(
													'<i class="icon-cloud mr-2" width="20"></i>');
								}
							}

							for (let i = 0; i < arr.length; i++) {
								if (arr[i].category == 'TMP') {
									$('#tmp').append(arr[i].fcstValue);
								}
							}
						} else {
							console.log('fail ==>');
							console.log(data);
						}
					},
					error : function(e, status, xhr, data) {
						console.log('error ==>');
						console.log(e);
					}
				});
		$('#addJobListDivOne').ready(function() {

			//xhr.send('')
		});
		$(function() {
			var xhr = new XMLHttpRequest();
			var url = '${pageContext.request.contextPath}'; /*URL*/
			xhr.open('GET', url); //지정된 리소스에서 데이터를 요청합니다
			xhr.onreadystatechange = function() {
				if (this.readyState == xhr.DONE) { // <== 정상적으로 준비되었을때
					if (xhr.status == 200 || xhr.status == 201) { // <== 호출 상태가 정상적일때
						alert('Status: ' + this.status + '\nHeaders: '
								+ JSON.stringify(this.getAllResponseHeaders())
								+ '\nBody: ' + this.responseText);
					}
				}
			}
			//일자리 리스트 
			$
					.ajax({
						type : 'get',
						cache : "false",
						url : '/lms/adRestController',
						data : {
							currentPage : currentPage,
							rowPerPage : rowPerPage
						},
						success : function(a) {
							console.log(typeof (a));
							console.log(a);
							var a2 = JSON.parse(a);
							console.log(a2);
							var arr = a2.GetJobInfo.row;

							console.log(arr);
							totalCount = a2.GetJobInfo.list_total_count;
							console.log(totalCount);
							for (let i = 0; i < arr.length; i++) {

								$('#addJobListDivOne')
										.append(
												"<div class='col-lg-3 text-center' style='border : 1px solid #555555;'>\
  						<button class='btn btn-primary ' type='button'  aria-haspopup='true' aria-expanded='true'>"
														+ arr[i].CMPNY_NM
														+ "</button>\
  						<a class='dropdown-item' href='#'>"
														+ arr[i].BSNS_SUMRY_CN
														+ "</a>\
  						<a class='dropdown-item' href='#'>"
														+ arr[i].HOPE_WAGE
														+ "</a>\
  						<a class='dropdown-item' href='#'>"
														+ arr[i].RCEPT_CLOS_NM
														+ "</a><br>\
  						<a class='dropdown-item' href='#'>"
														+ arr[i].WORK_PARAR_BASS_ADRES_CN
														+ "</a></div>");
							}
							// currentPage 12, rowPerPage12 씩 증가
							currentPage += 12;
							rowPerPage += 12;
							console.log(currentPage);
							console.log(rowPerPage);
						}
					})
		});

		//일자리 리스트 보여주는 범위 설정 currentPage 1 ~ rowPerPage 12
		var totalCount;
		var currentPage = 1;
		var rowPerPage = 12;

		console.log(currentPage);
		if (currentPage == 1) {
			$('#reducePage').hide();
		}

		//리스트 다음 버튼
		$('#addPage')
				.click(
						function() {
							//	$('#reducePage').click(function() { 

							$('#addJobListDivOne').empty();
							$
									.ajax({
										type : 'get',
										cache : "false",
										url : '/lms/adRestController',
										data : {
											currentPage : currentPage,
											rowPerPage : rowPerPage
										},
										success : function(a) {
											console.log(typeof (a));
											console.log(a);
											var a2 = JSON.parse(a);
											console.log(a2);
											var arr = a2.GetJobInfo.row;

											console.log(arr);
											totalCount = a2.GetJobInfo.list_total_count;
											console.log(totalCount);
											for (let i = 0; i < arr.length; i++) {

												$('#addJobListDivOne')
														.append(
																"<div class='col-lg-3 text-center' style='border : 1px solid #555555;'>\
						<button class='btn btn-primary ' type='button'  aria-haspopup='true' aria-expanded='true'>"
																		+ arr[i].CMPNY_NM
																		+ "</button>\
						<a class='dropdown-item' href='#'>"
																		+ arr[i].BSNS_SUMRY_CN
																		+ "</a>\
						<a class='dropdown-item' href='#'>"
																		+ arr[i].HOPE_WAGE
																		+ "</a>\
						<a class='dropdown-item' href='#'>"
																		+ arr[i].RCEPT_CLOS_NM
																		+ "</a><br>\
						<a class='dropdown-item' href='#'>"
																		+ arr[i].WORK_PARAR_BASS_ADRES_CN
																		+ "</a></div>");
											}
											//currentPage을 +12=13, rowPerPage12 +12=24
											currentPage += 12;
											rowPerPage += 12;
											console.log(currentPage);
											console.log(rowPerPage);
											if (currentPage != 1) {
												$('#reducePage').show();
											}
										}
									})
						});
		//리스트 이전 버튼
		$('#reducePage')
				.click(
						function() {
							//$('#reducePage').click(function() { 

							$('#addJobListDivOne').empty();
							$
									.ajax({
										type : 'get',
										cache : "false",
										url : '/lms/adRestController',
										data : {
											currentPage : currentPage,
											rowPerPage : rowPerPage
										},
										success : function(a) {
											console.log(typeof (a));
											console.log(a);
											var a2 = JSON.parse(a);
											console.log(a2);
											var arr = a2.GetJobInfo.row;

											console.log(arr);
											totalCount = a2.GetJobInfo.list_total_count;
											console.log(totalCount);
											for (let i = 0; i < arr.length; i++) {

												$('#addJobListDivOne')
														.append(
																"<div class='col-lg-3 text-center' style='border : 1px solid #555555;'>\
					<button class='btn btn-primary ' type='button'  aria-haspopup='true' aria-expanded='true'>"
																		+ arr[i].CMPNY_NM
																		+ "</button>\
					<a class='dropdown-item' href='#'>"
																		+ arr[i].BSNS_SUMRY_CN
																		+ "</a>\
					<a class='dropdown-item' href='#'>"
																		+ arr[i].HOPE_WAGE
																		+ "</a>\
					<a class='dropdown-item' href='#'>"
																		+ arr[i].RCEPT_CLOS_NM
																		+ "</a><br>\
					<a class='dropdown-item' href='#'>"
																		+ arr[i].WORK_PARAR_BASS_ADRES_CN
																		+ "</a></div>");
											}

											//currentPage을 현재currentPage1 -12 rowPerPage12 +12=24
											currentPage -= 12;
											rowPerPage -= 12;
											if (currentPage == 1) {
												$('#reducePage').hide();
											}
											console.log(currentPage);
											console.log(rowPerPage);
										}
									})
						});
	</script>
	<script>
	var arr;
	$
			.ajax({
				type : 'get',
				url : '/lms/addAvgScore',
				success : function(jsonData) {
					let a = [];
					let b = [];
					arr = jsonData;
					console.log(arr);
					for (j = 0; j < jsonData.length; j++) {
						a.push(arr[j].className);
						b.push(arr[j].avg);
					}
					var data = {
						labels : a,
						datasets : [ {
							label : "genderRate",
							data : b,
							backgroundColor : [ 'rgba(255,192,203, 0.2)',
									'rgba(255,192,203, 0.2)',
									'rgba(255,192,202, 0.2)',
									'rgba(255,192,201, 0.2)',
									'rgba(255,192,200, 0.2)',
									'rgba(255,192,199, 0.2)' ],
							borderColor : [ 'rgba(255,192,203,1)',
									'rgba(255,192,203, 1)',
									'rgba(255,192,203, 1)',
									'rgba(255,192,203, 1)',
									'rgba(255,192,203, 1)',
									'rgba(255,192,203, 1)' ],
							borderWidth : 1,
							fill : false
						} ]
					}
					var multiLineData = {
						labels : [ "Red", "Blue", "Yellow", "Green",
								"Purple", "Orange" ],
						datasets : [ {
							label : 'Dataset 1',
							data : [ 10, 19, 3, 5, 2, 3 ],
							borderColor : [ '#587ce4' ],
							borderWidth : 2,
							fill : false
						}, {
							label : 'Dataset 2',
							data : [ 5, 23, 7, 12, 42, 23 ],
							borderColor : [ '#ede190' ],
							borderWidth : 2,
							fill : false
						}, {
							label : 'Dataset 3',
							data : [ 15, 10, 21, 32, 12, 33 ],
							borderColor : [ '#f44252' ],
							borderWidth : 2,
							fill : false
						} ]
					};
					var options = {
						scales : {
							yAxes : [ {
								ticks : {
									beginAtZero : true
								}
							} ]
						},
						legend : {
							display : false
						},
						elements : {
							point : {
								radius : 0
							}
						}

					};
					var doughnutPieData = {
						datasets : [ {
							data : [ 30, 40, 30 ],
							backgroundColor : [ 'rgba(255, 99, 132, 0.5)',
									'rgba(54, 162, 235, 0.5)',
									'rgba(255, 206, 86, 0.5)',
									'rgba(75, 192, 192, 0.5)',
									'rgba(153, 102, 255, 0.5)',
									'rgba(255, 159, 64, 0.5)' ],
							borderColor : [ 'rgba(255,99,132,1)',
									'rgba(54, 162, 235, 1)',
									'rgba(255, 206, 86, 1)',
									'rgba(75, 192, 192, 1)',
									'rgba(153, 102, 255, 1)',
									'rgba(255, 159, 64, 1)' ],
						} ],

						// These labels appear in the legend and in the tooltips when hovering different arcs
						labels : [ 'Pink', 'Blue', 'Yellow', ]
					};
					var doughnutPieOptions = {
						responsive : true,
						animation : {
							animateScale : true,
							animateRotate : true
						}
					};
					var areaData = {
						labels : [ "2013", "2014", "2015", "2016", "2017" ],
						datasets : [ {
							label : '# of Votes',
							data : [ 12, 19, 3, 5, 2, 3 ],
							backgroundColor : [ 'rgba(255, 99, 132, 0.2)',
									'rgba(54, 162, 235, 0.2)',
									'rgba(255, 206, 86, 0.2)',
									'rgba(75, 192, 192, 0.2)',
									'rgba(153, 102, 255, 0.2)',
									'rgba(255, 159, 64, 0.2)' ],
							borderColor : [ 'rgba(255,99,132,1)',
									'rgba(54, 162, 235, 1)',
									'rgba(255, 206, 86, 1)',
									'rgba(75, 192, 192, 1)',
									'rgba(153, 102, 255, 1)',
									'rgba(255, 159, 64, 1)' ],
							borderWidth : 1,
							fill : true, // 3: no fill
						} ]
					};

					var areaOptions = {
						plugins : {
							filler : {
								propagate : true
							}
						}
					}

					var multiAreaData = {
						labels : [ "Jan", "Feb", "Mar", "Apr", "May",
								"Jun", "Jul", "Aug", "Sep", "Oct", "Nov",
								"Dec" ],
						datasets : [
								{
									label : 'Facebook',
									data : [ 8, 11, 13, 15, 12, 13, 16, 15,
											13, 19, 11, 14 ],
									borderColor : [ 'rgba(255, 99, 132, 0.5)' ],
									backgroundColor : [ 'rgba(255, 99, 132, 0.5)' ],
									borderWidth : 1,
									fill : true
								},
								{
									label : 'Twitter',
									data : [ 7, 17, 12, 16, 14, 18, 16, 12,
											15, 11, 13, 9 ],
									borderColor : [ 'rgba(54, 162, 235, 0.5)' ],
									backgroundColor : [ 'rgba(54, 162, 235, 0.5)' ],
									borderWidth : 1,
									fill : true
								},
								{
									label : 'Linkedin',
									data : [ 6, 14, 16, 20, 12, 18, 15, 12,
											17, 19, 15, 11 ],
									borderColor : [ 'rgba(255, 206, 86, 0.5)' ],
									backgroundColor : [ 'rgba(255, 206, 86, 0.5)' ],
									borderWidth : 1,
									fill : true
								} ]
					};

					var multiAreaOptions = {
						plugins : {
							filler : {
								propagate : true
							}
						},
						elements : {
							point : {
								radius : 0
							}
						},
						scales : {
							xAxes : [ {
								gridLines : {
									display : false
								}
							} ],
							yAxes : [ {
								gridLines : {
									display : false
								}
							} ]
						}
					}

					var scatterChartData = {
						datasets : [
								{
									label : 'First Dataset',
									data : [ {
										x : -10,
										y : 0
									}, {
										x : 0,
										y : 3
									}, {
										x : -25,
										y : 5
									}, {
										x : 40,
										y : 5
									} ],
									backgroundColor : [ 'rgba(255, 99, 132, 0.2)' ],
									borderColor : [ 'rgba(255,99,132,1)' ],
									borderWidth : 1
								},
								{
									label : 'Second Dataset',
									data : [ {
										x : 10,
										y : 5
									}, {
										x : 20,
										y : -30
									}, {
										x : -25,
										y : 15
									}, {
										x : -10,
										y : 5
									} ],
									backgroundColor : [
											'rgba(54, 162, 235, 0.2)', ],
									borderColor : [
											'rgba(54, 162, 235, 1)', ],
									borderWidth : 1
								} ]

					}

					var scatterChartOptions = {
						scales : {
							xAxes : [ {
								type : 'linear',
								position : 'bottom'
							} ]
						}
					}

					// Get context with jQuery - using jQuery's .get() method.
					if ($("#barChart").length) {
						var barChartCanvas = $("#barChart").get(0)
								.getContext("2d");
						// This will get the first returned node in the jQuery collection.
						var barChart = new Chart(barChartCanvas, {
							type : 'bar',
							data : data,
							options : options
						});
					}

					if ($("#lineChart").length) {
						var lineChartCanvas = $("#lineChart").get(0)
								.getContext("2d");
						var lineChart = new Chart(lineChartCanvas, {
							type : 'line',
							data : data,
							options : options
						});
					}

					if ($("#linechart-multi").length) {
						var multiLineCanvas = $("#linechart-multi").get(0)
								.getContext("2d");
						var lineChart = new Chart(multiLineCanvas, {
							type : 'line',
							data : multiLineData,
							options : options
						});
					}

					if ($("#areachart-multi").length) {
						var multiAreaCanvas = $("#areachart-multi").get(0)
								.getContext("2d");
						var multiAreaChart = new Chart(multiAreaCanvas, {
							type : 'line',
							data : multiAreaData,
							options : multiAreaOptions
						});
					}

					if ($("#doughnutChart").length) {
						var doughnutChartCanvas = $("#doughnutChart")
								.get(0).getContext("2d");
						var doughnutChart = new Chart(doughnutChartCanvas,
								{
									type : 'doughnut',
									data : doughnutPieData,
									options : doughnutPieOptions
								});
					}

					if ($("#pieChart").length) {
						var pieChartCanvas = $("#pieChart").get(0)
								.getContext("2d");
						var pieChart = new Chart(pieChartCanvas, {
							type : 'pie',
							data : data,
							options : doughnutPieOptions
						});
					}

					if ($("#areaChart").length) {
						var areaChartCanvas = $("#areaChart").get(0)
								.getContext("2d");
						var areaChart = new Chart(areaChartCanvas, {
							type : 'line',
							data : areaData,
							options : areaOptions
						});
					}

					if ($("#scatterChart").length) {
						var scatterChartCanvas = $("#scatterChart").get(0)
								.getContext("2d");
						var scatterChart = new Chart(scatterChartCanvas, {
							type : 'scatter',
							data : scatterChartData,
							options : scatterChartOptions
						});
					}

					if ($("#browserTrafficChart").length) {
						var doughnutChartCanvas = $("#browserTrafficChart")
								.get(0).getContext("2d");
						var doughnutChart = new Chart(doughnutChartCanvas,
								{
									type : 'doughnut',
									data : browserTrafficData,
									options : doughnutPieOptions
								});
					}
				}
			});

	</script>
</body>
</html>