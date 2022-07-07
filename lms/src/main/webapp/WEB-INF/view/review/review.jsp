<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
  <link href="/assets/css/star.css" rel="stylesheet"/>
<link href="/assets/css/star.css" rel="stylesheet"/> 
<link
	href="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote-bs4.min.css"
	rel="stylesheet">
<script
	src="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote-bs4.min.js"></script>
<style>
	.bottom {margin-bottom : 40px;}
	.top {margin-top : 30px;}
	
	#myform fieldset{
    display: inline-block;
    direction: rtl;
    border:0;
}
#myform fieldset legend{
    text-align: right;
}
#myform input[type=radio]{
    display: none;
}
#myform label{
    font-size: 3em;
    color: transparent;
    text-shadow: 0 0 0 #f0f0f0;
}
#myform label:hover{
    text-shadow: 0 0 0 rgba(250, 208, 0, 0.99);
}
#myform label:hover ~ label{
    text-shadow: 0 0 0 rgba(250, 208, 0, 0.99);
}
#myform input[type=radio]:checked ~ label{
    text-shadow: 0 0 0 rgba(250, 208, 0, 0.99);
}
#reviewContents {
    width: 100%;
    height: 150px;
    padding: 10px;
    box-sizing: border-box;
    border: solid 1.5px #D3D3D3;
    border-radius: 5px;
    font-size: 16px;
    resize: none;
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
           	<h1>Review</h1>
	          <div class="row">
	            <div class="col-lg-16 grid-margin stretch-card">
	              <div class="card">
	                <div class="card-body">
	                <table class="table">
		                		<thead>
		                			<tr>
		                				<th>아이디//앞에 2자리만?</th>
		                				<th>리뷰</th>
		                				<th>평점</th>
		                				<th>작성날짜</th>
		                			</tr>
		                		</thead>
		                		<tbody>
			                		<c:forEach var="ED" items="${list}">
			                			<tr>
			                				<td></td>
		                					<td>${ED.educationReviewContent}</td>
		                					<td>${ED.educationReviewStar}</td>
		                					<td>${ED.createDate}</td>
		                				</tr>
			                	   </c:forEach>
			                     </tbody>
			                  </table>
	                
					<form class="mb-3" name="myform" id="myform" action="${pageContext.request.contextPath}/loginCheck/addReview" method="post">
						<input type="hidden" id="lectureName" value="${lectureName}">
			            <div>
						<textarea class="col-auto form-control" type="text" id="educationReviewContent" name="educationReviewContent"
					 		 placeholder="좋은 수강평을 남겨주시면 감사"></textarea>
						</div>
					
						<fieldset>
							<input type="radio" name="educationReviewStar" value="5" id="rate1"><label
								for="rate1">★</label>
							<input type="radio" name="educationReviewStar" value="4" id="rate2"><label
								for="rate2">★</label>
							<input type="radio" name="educationReviewStar" value="3" id="rate3"><label
								for="rate3">★</label>
							<input type="radio" name="educationReviewStar" value="2" id="rate4"><label
								for="rate4">★</label>
							<input type="radio" name="educationReviewStar" value="1" id="rate5"><label
								for="rate5">★</label>
						</fieldset>
						<div style="text-align:right">
							<button type="submit" class="btn btn-primary">리뷰 등록</button>
						</div>
					</form>
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

			
  </script>
</body>
</html>

