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
                  <h1>[QnA문의]</h1>
                </div>
                <div class="col-12 col-xl-4">
                 <div class="justify-content-end d-flex">
                 </div>
                </div>
              </div>
            </div>
          </div>
          
          <div class="row">
          	<div class="col-md-2 grid-margin stretch-card">
              <div class="card">
                <div class="card-body">
                  	<h4 class="display-4" style="text-align: center;">첨부사진</h4>
                  	<br>
                    <c:forEach var="picture" items="${fileList}"> 
                    	<blockquote class="blockquote blockquote-primary" style="text-align: center;">
                    		<img alt="" src="${pageContext.request.contextPath}/file/communityFile/${picture}" width="150px" height="150px">
                    	</blockquote>
                    </c:forEach>
                 
                </div>
              </div>
            </div>
            <div class="col-md-6 grid-margin stretch-card">
              <div class="card">
                <div class="card-body">
                	<h4 class="display-4" style="text-align: center;">문의</h4>
                	<br>
                  <blockquote class="blockquote" style="text-align: center;">
	                  <h2 class="card-title">${qnaInquiry.qnaTitle}</h2>
	                  <div class="row">
	                    <div class="col-md-12">
	                      <address>
	                        <p class="font-weight-bold">${qnaInquiry.qnaContent}</p>
	                      </address>
	                      <br>
	                      <br>
	                      <br>
	                      <div style="width: 100%; text-align: right;"><p class="font-weight-bold"> 작성일 : ${qnaInquiry.createDate}</p></div>
	                    </div>
	                  </div>
	               </blockquote>
                </div>
                <div class="card-body">
                  <h4 class="display-4" style="text-align: center;">답변</h4>
                  <br>
                  <!-- 답변이 존재할경우 -->
                  <c:if test="${!empty qnaAnswer}">
	                  <blockquote class="blockquote" style="text-align: center;">
		                  <h2 class="card-title">${qnaAnswer.qnaTitle}</h2>
		                  <div class="row">
		                    <div class="col-md-12">
		                      <address>
		                        <p class="font-weight-bold">${qnaAnswer.qnaContent}</p>
		                      </address>
		                      <br>
		                      <br>
		                      <br>
		                      <div style="width: 100%; text-align: right;"><p class="font-weight-bold"> 작성일 : ${qnaAnswer.createDate}</p></div>
		                    </div>
		                  </div>
	                  </blockquote>
                  </c:if>
                  
                  <!-- 답변이 없을땐 -->
                   <c:if test="${empty qnaAnswer}">
                   	<c:if test="${loginLv >= 3}">
                   	<form action="${pageContext.request.contextPath}/loginCheck/qnaListOneAnswer" method="post">
	                  <blockquote class="blockquote" style="text-align: center;">
	                  	<h1>답변제목</h1>
		                  <h2 class="card-title">
		                  	<input type="text" name="qnaNo" value="${qnaInquiry.qnaNo}" readonly="readonly" hidden="hidden">
		                  	<br>
		                  	<input type="text" name="qnaTitle">
		                  </h2>
		                  <div class="row">
		                    <div class="col-md-12">
		                      <address>
		                      	<h2>답변컨텐츠</h2>
		                        <p class="font-weight-bold"><input type="text" name="qnaContent"></p>
		                      </address>
		                      <br>
		                      <br>
		                      <br>
		                      <div style="width: 100%; text-align: right;"><p class="font-weight-bold"></p></div>
		                    </div>
		                  </div>
	                  </blockquote>
	                 </c:if>
                  </c:if>
                 <div style="width: 100%; text-align: right; margin-top: 40px">
                 <!-- 관리자 이상만 답변이 가능해야하고 답변이 비어있어야함 -->
                 <c:if test="${empty qnaAnswer}">
                 	<c:if test="${loginLv >= 3}">
                 		<button type="submit" class="btn btn-inverse-warning btn-fw">답변</button>
                 		</form>
                 	</c:if>
                 </c:if>
                 <c:if test="${loginLv >= 3}">
                 	<button type="button" class="btn btn-inverse-danger btn-fw">삭제</button>
                 </c:if>
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

</body>
</html>

