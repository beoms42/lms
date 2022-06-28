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
                  <h1>[강의수정]</h1>
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
            <div class="col-12 grid-margin stretch-card">
              <div class="card">
                <div class="card-body">
                  <h4 class="card-title">Basic form elements</h4>
                  <p class="card-description">
                    Basic form elements
                  </p>
                  <form class="forms-sample" method="post" action="${pageContext.request.contextPath}/loginCheck/updateLectureAction">
                    <div class="form-group">
                      <label for="exampleInputName1">개설 강의명[수정불가]</label>
                      <input type="text" class="form-control" id="exampleInputName1" placeholder="강의명" name="lectureName" value="${lect.lectureName}" readonly="readonly">
                    </div>
                    <div class="form-group">
                      <label for="exampleInputEmail3">강의 기간[상세 캘린더 설정시 수정불가]</label>
                      <br>
                      시작일 : <input type="date" name="lectureStartDate" value="${lect.lectureStartDate}"> 끝나는 날 : <input type="date" name="lectureEndDate" value="${lect.lectureEndDate}">
                    </div>
                    <div class="form-group">
                      <label for="exampleInputPassword4">담당 강사</label>
                      <div class="input-group">
                      
	                      <div class="input-group-prepend">
	                        <select class="teacherNames">
	                          <option value="">:::강사 선택:::</option>
	                          <c:forEach var="n" items="${teacherNameList}">
	                          	<option value="${n}">${n}</option>
	                          </c:forEach>
	                         </select>
	                        </div>
	                      </div>
	                      
                      	<input type="text" class="form-control" aria-label="Text input with dropdown button" readonly="readonly" id="teacherName" name="teacherName" value="${lect.teacher}">
                   	  </div>
                    <div class="form-group">
                      <label for="exampleInputPassword4">담당 매니저</label>
                      <div class="input-group">
                      
	                      <div class="input-group-prepend">
	                        <select class="managerNames">
	                        <option value="">:::매니저 선택:::</option>
	                          <c:forEach var="n" items="${managerNameList}">
	                          	<option value="${n}">${n}</option>
	                          </c:forEach>
	                        </select>
	                      </div>
	                     </div> 
                      	<input type="text" class="form-control" aria-label="Text input with dropdown button" readonly="readonly" id="managerName" name="managerName" value="${lect.manager}">
                   	  </div>
                   	  
                   
                      
                    <div class="form-group">
                      <label for="exampleInputPassword4">강의실</label>
                      <div class="input-group">
	                      <div class="input-group-prepend">
	                        <select class="lectureRoomNames">
	                            <option value="">:::강의실 선택:::</option>
	                          <c:forEach var="n" items="${lectureRoomList}">
	                          	<option value="${n.lectureRoomName}">강의실 : ${n.lectureRoomName} / 정원 : ${n.lectureRoomAdmit}명</option>
	                          </c:forEach>
	                        </select>
	                        </div>
	                      </div>
                      	<input type="text" class="form-control" aria-label="Text input with dropdown button" readonly="readonly" id="lectureRoomName" name="lectureRoomName" value="${lect.lectureRoomName}">
                   	  </div>
                    <div class="form-group">
                      <label for="exampleInputCity1">학생 정원</label>
                      <input type="number" class="form-control" id="exampleInputCity1" placeholder="" max="30" name="maxStudent" value="${lect.lectureStudentCapacity}">
                    </div>
                    <div class="form-group">
                      <label for="exampleInputPassword4">강의 수정자</label>
                      <input type="text" class="form-control" id="exampleInputPassword4" value="${lect.loginId}" name="loginId" readonly="readonly">
                    </div>
                    <button type="submit" class="btn btn-primary mr-2">Submit</button>
                    <button class="btn btn-light">Cancel</button>
                    </div>
                  </form>
                  </div> 
                </div>
              </div>
              
              <!-- 상세보기 실제부분 --> 
	          <div class="row">
	            <div class="col-lg-4 grid-margin stretch-card">
	              <div class="card">
	                <div class="card-body">
	                  <h4 class="card-title">과목관리</h4>
	                  <p class="card-description">
	                  </p>
	                  <div class="table-responsive">
	                    <table class="table">
	                      <thead>
	                        <tr>
	                          <th>과목</th>
	                          <th>삭제</th>
	                        </tr>
	                      </thead>
	                      <tbody>
	                      <c:forEach var="sub" items="${sublist}">
	                      	<tr>
								<td><button class="btn btn-outline-primary btn-fw">${sub}</button></td>
								<td><button class="btn btn-outline-dark btn-fw">X</button></td>
	                      	</tr>
	                      </c:forEach>                
	                      </tbody>
	                    </table>
	                  </div>
	                </div>
	              </div>
	            </div>
	            
	            <!--  책 리스트  -->
	             <div class="col-lg-7 grid-margin stretch-card">
	              <div class="card">
	                <div class="card-body">
	                  <h4 class="card-title">과목 - 책 리스트</h4>
	                  <p class="card-description">
	                  </p>
	                  <div class="table-responsive">
	                    <table class="table">
	                      <thead>
	                        <tr>
	                          <th>과목</th>
	                          <th>책 이름</th>
	                          <th>출판사</th>
	                        </tr>
	                      </thead>
	                      <tbody>
	                      <c:forEach var="book" items="${bookMapList}">
	                      	<tr>
								<td><button class="btn btn-outline-primary btn-fw">${book.subjectName}</button></td>
								<td><button class="btn btn-outline-dark btn-fw">${book.textbookName}</button></td>
								<td><button class="btn btn-outline-dark btn-fw">${book.textbookPublisher}</button></td>
	                      	</tr>
	                      </c:forEach>                
	                      </tbody>
	                    </table>
	                  </div>
	                </div>
	              </div>
	            </div>
	          
	          </div>
	          <!-- 상세보기 끝 -->
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
  <script type="text/javascript">
	$('.clickTeacherMenu').click(function() {
			console.log('dsdsdsdsdsd');
			$('#teacherName').val($('.clickTeacherMenu').text());
	});
	
	$('.teacherNames').change(function() {
	    var value = $(this).val();
	    $('#teacherName').val(value);
	});
	
	$('.managerNames').change(function() {
	    var value = $(this).val();
	    $('#managerName').val(value);
	});
	
	$('.lectureRoomNames').change(function() {
	    var value = $(this).val();
	    $('#lectureRoomName').val(value);
	});
  </script>
</body>
</html>

