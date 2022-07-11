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
            <div class="col-md-0 grid-margin">
                  <h1>[취업관리]</h1>
            </div>
          </div>
        <!-- content-wrapper ends -->
        <!-- partial:partials/_footer.html -->
        <!-- partial -->
        
          <div class="row">
            <div class="col-lg-10 grid-margin stretch-card">
              <div class="card">
                <div class="card-body">
                  <h4 class="card-title">취업관리</h4>
                <div align="right">
                <form action="${pageContext.request.contextPath}/loginCheck/getEmploymentList">
	                <input type="hidden" name="employmentCurrentPage" value="${employmentCurrentPage}">
	                <select name="lectureName" id="lectureName">
	                	<option value="">:: 강의 선택 ::</option>
	                	<c:forEach var="l" items="${lectureNameList}">
	                		<c:choose>
		                		<c:when test="${l eq lectureName}">
		                			<option selected="selected">${l}</option>
		                		</c:when>
		                		<c:otherwise>
		                			<option>${l}</option>
		                		</c:otherwise>
	                		</c:choose>
	                	</c:forEach>
	                </select>
	                <button type="submit">검색</button>
                </form>
                </div>
                  <p class="card-description">
                  </p>
                  <div class="table-responsive">
                    <table class="table">
                      <thead>
                        <tr>
                          <th>강의명</th>
                          <th>학생</th>
                          <th>취업상태</th>
                        </tr>
                      </thead>
                      <tbody>
                      <c:forEach var="e" items="${employmentList}">
                         <tr>
                            <td>${e.lectureName}</td>
                            <td>${e.studentName}(${e.loginId})</td>
                            <td>${e.employment}</td>
                         </tr>
                      </c:forEach>                
                      </tbody>
                    </table>
                    <div>
                    	<a class="float-right" href="${pageContext.request.contextPath}/loginCheck/modifyEmploymentByStudent">취업관리</a>
                    </div>
                  </div>
                </div>
              </div>
            </div>
          </div>
      <c:if test="${employmentCurrentPage > 1}">
         <a class="btn btn-success" href="${pageContext.request.contextPath}/loginCheck/getEmploymentList?employmentCurrentPage=${employmentCurrentPage-1}&lectureName=${lectureName}">이전</a>
      </c:if>
      
      <c:if test="${employmentCurrentPage < employmentLastPage}">
         <a class="btn btn-success" href="${pageContext.request.contextPath}/loginCheck/getEmploymentList?employmentCurrentPage=${employmentCurrentPage+1}&lectureName=${lectureName}">다음</a>
      </c:if>
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
