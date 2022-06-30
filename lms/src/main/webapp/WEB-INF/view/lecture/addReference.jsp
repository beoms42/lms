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
                  <h3>자료실 입력</h3>
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
            <div class="col-lg-12 grid-margin stretch-card">
              <div class="card">
                <div class="card-body">
                  <p class="card-description">
                  </p>
                  <div class="table-responsive">
                   	<form id="addReferenceForm" method="post" action="${pageContext.request.contextPath}/loginCheck/addReference" enctype="multipart/form-data">
	                     <table class="table">
	                      	<input type="text" name="lectureName" value="${lectureName}" hidden="hidden">
	                      	<tr>
				               <td>제목</td>
				               <td><input type="text" name="referenceTitle" id="referenceTitle" class="form-control"></td>
				            </tr>
				            <tr>
				               <td>내용</td>
				               <td><textarea rows="5" cols="50" name="referenceContent" id="referenceContent" class="form-control"></textarea></td>
				            </tr>
				            <tr>
				               <td><button class="btn  btn-facebook auth-form-btn" type="button" id="addFileupload" >파일 업로드 추가</button></td>
				               <td id="fileSection">
				                  <!-- 파일 업로드 input 태그가 추가될 영역 -->
				               </td>
				            </tr>
				            <tr>
				               <td colspan="2">
				                  <button class="btn  btn-facebook auth-form-btn" type="button" id="addReferenceBtn">입력</button>
				               </td>
				            </tr>
                    	</table>
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
</body>
<script type="text/javascript">
   $(document).ready(function() { // html페이지를 다 로드시키고 매개변수함수를 실행
      $('#addFileupload').click(function() {
         // 추가된 referenceFileList안에 파일이 첨부되지 않았다면 새로운 referenceFileList 추가 X
         let flag = true; 
         
         // jquery api 사용
         $('.referenceFileList').each(function() { // each함수를 이용한 반복
            if($(this).val() == '') {
               flag = false;
            }
         });
         
         if(flag) {
            $('#fileSection').append("<div><input class='referenceFileList' type='file' name='referenceFileList' class='form-control'></div>");
         } else {
            alert('파일이 첨부되지 않은 referenceFileList가 존재합니다.');
         }
      });
   
      $('#addReferenceBtn').click(function() {
         if($('#referenceTitle').val() == '') {
            alert('referenceTitle 입력하세요');
         } else if($('#referenceContent').val() == '') {
            alert('referenceContent 입력하세요');
         } else {
            let flag2 = true;
            $('.referenceFileList').each(function() { // each함수를 이용한 반복
               if($(this).val() == '') {
                  flag2 = false;
               }
            });
            if(flag2) {
               $('#addReferenceForm').submit();
            } else {
               alert('파일이 첨부되지 않은 referenceFileList가 존재합니다.');
            }
         }
      });
   });
</script>
</html>
