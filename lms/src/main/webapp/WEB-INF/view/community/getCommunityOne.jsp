<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri= "http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>
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
  <style>
  	.title-bottom {margin-bottom : 50px;}
  	.round {border-radius: 100%;
  			margin-right:30px;}
  	.smalltitle-bottom {margin-bottom : 40px;}
  	.bottom {margin-bottom : 20px;}
  	.left {margin-left : 20px;}
  	.top {margin-top : 30px;}
  	.right {margin-right : 30px;}
  	.font-size {font-size : 20px;}
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
	            <div class="col-lg-12 grid-margin stretch-card">
	              <div class="card">
	                <div class="card-body"><h1 class="title-bottom">[커뮤니티 게시글]</h1>
	                  <div class="table-responsive">
	                  <h2 class="smalltitle-bottom">${community.communityTitle}</h2>
		                  <div class="row bottom">
		                  	<div class="col-sm-1">
		                  		<img src="${pageContext.request.contextPath}/file/memberPhoto/${memberFileName}" class="round left" width="50" height="50">	
		                  	</div>
		                  	<div class="col-sm-11 font-size">
		                  		${community.loginId}
								<div>${community.createDate}</div>	                  	
		                  	</div>
		                  </div>
		                  <div class="left">
		                  <hr>
		                  <div>${community.communityContent}</div>
		                  <hr>
							<!-- 파일 부분 -->
							<c:if test="${communityFileList != null && fn:length(communityFileList) > 0}">
								<div class="bottom font-size">첨부 파일</div>
									<c:forEach var="cf" items="${communityFileList}">
										<c:choose>
											<c:when test="${cf.communityFileType eq 'image/jpeg' 
														|| cf.communityFileType eq 'image/png' 
														|| cf.communityFileType eq 'image/gif'}">
												<img src="${pageContext.request.contextPath}/file/communityFile/${cf.communityFileName}" width="100" height="100">
												<br>
												<br>
											</c:when>
											<c:otherwise>
												<a href="${pageContext.request.contextPath}/file/communityFile/${cf.communityFileName}">${cf.communityFileName}</a>
												<br>
												<br>
											</c:otherwise>
										</c:choose>
									</c:forEach>
							</c:if>
						</div>
							<div class="float-right top bottom right">
						    <a class="btn btn-info" href="${pageContext.request.contextPath}/loginCheck/modifyCommunity?communityNo=${community.communityNo}">수정</a>
						    <a class="btn btn-danger" href="${pageContext.request.contextPath}/loginCheck/removeCommunity?communityNo=${community.communityNo}">삭제</a>
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
</body>
</html>

