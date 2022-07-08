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
                  <h1>[강의 일정]</h1>
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
            <div class="col-lg-11 grid-margin stretch-card">
              <div class="card">
                <div class="card-body">
                  <c:if test="${sessionLv <= 2}">
                  	<h4 class="card-title">${lectureName} 시간표</h4>
                  </c:if>
                  <c:if test="${sessionLv > 2}">
                  	<h4 class="card-title">전체 시간표</h4>
                  </c:if>
                  <p class="card-description">
                  </p>
                  <div class="table-responsive">
                    <h2>${y}년 ${m}월</h2>
	<div>
		<a class="btn bg-dark text-white" href="${pageContext.request.contextPath}/loginCheck/getSheduleListByMonth?y=${y}&m=${m-1}">이전달</a>&nbsp;&nbsp;
		<a class="btn bg-dark text-white" href="${pageContext.request.contextPath}/loginCheck/getSheduleListByMonth?y=${y}&m=${m+1}">다음달</a>
		<div class="float-right bottom">
      		<c:if test="${sessionLv > 2}">
      			<button type="button" class="btn bg-dark text-white" data-toggle="modal" data-target="#addScheduleModal">일정추가</button>
      		</c:if>
		</div>
	<table class="table table-hover">
		<thead>
			<tr>
				<th class="text-danger">일</th>
				<th>월</th>
				<th>화</th>
				<th>수</th>
				<th>목</th>
				<th>금</th>
				<th class="text-primary">토</th>
			</tr>
		</thead>
		<tbody>
			<tr>
				<c:forEach var="i" begin="1" end="${totalTd}" step="1">
					<c:choose>
						<c:when test="${(i - startBlank) > 0 && i <= endDay+startBlank}">
							<c:choose>
								<c:when test="${i%7==0 }">
									<c:set var="e" value="text-primary"/>
								</c:when>
								<c:when test="${i%7==1 }">
									<c:set var="e" value="text-danger"/>
								</c:when>
								<c:otherwise>
									<c:set var="e" value=""/>
								</c:otherwise>
							</c:choose>
							<td class="${e}" style="height : 90px;" width="15%">
								${i - startBlank}
								<div>
									<c:forEach var="c" items="${list}">
										<c:if test="${(c.scheduleDateDay) ==  (i - startBlank)}">
											<span id="c${c.scheduleNo}">
											<input name="scheduleNo" class="btn btn-sm bg-white text-dark" type="button" data-toggle="modal" data-target="#ScheduleOneModal" onclick="javascript:Click(${c.scheduleNo})" value="[${c.lectureName}]${c.subjectName}">
											<c:if test="${sessionLv > 2}">
												<div class="btn-group">
												<a class=" btn-sm btn-primary" href="${pageContext.request.contextPath}/loginCheck/modifySchedule?scheduleNo=${c.scheduleNo}&y=${y}&m=${m}">수정</a>
												<a class=" btn-sm btn-danger" href="${pageContext.request.contextPath}/loginCheck/removeSchedule?scheduleNo=${c.scheduleNo}&y=${y}&m=${m}">삭제</a>
												</div><br>
											</c:if>
											</span>
											
										</c:if>
									</c:forEach>
								</div>
							</td>
						</c:when>
						<c:when test="${(i - startBlank) < 1 }">
							<td> </td>
						</c:when>
						<c:when test="${i > endDay}">
							<td> </td>
						</c:when>
					</c:choose>
				<c:if test="${i%7 == 0}">
					</tr><tr>
				</c:if>
				</c:forEach>			
			</tr>
		</tbody>
	</table>
</div>
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
	<!-- addSchedule modal start -->
	<div class="modal fade" id="addScheduleModal" tabindex="-1" role="dialog" aria-labelledby="addScheduleModalLabel" aria-hidden="true">
	  <div class="modal-dialog" role="document">
	    <div class="modal-content">
	      <div class="modal-header">
	        <h5 class="modal-title" id="addScheduleModalLabel">일정추가</h5>
	        <button type="button" id= "close" class="close" data-dismiss="modal" aria-label="Close">
	          <span aria-hidden="true">&times;</span>
	        </button>
	      </div>
	       	 <form id="addScheduleForm" method="post" action="${pageContext.request.contextPath}/loginCheck/addSchedule">
	      	 <div class="modal-body">
	      	 	<span id="addScheduleHelper" class="helper"></span>
			<div>강의기간</div>
			<div> </div>
	      	 	<div>강의시작 : <input id="scheduleStartDate" type="date" name="scheduleStartDate"></div>
	      	 	<div>강의종료 : <input id="scheduleEndDate" type="date" name="scheduleEndDate"></div>
	      	 	<div>강의과목 : 
	       	 	<select id="lectureSubjectNo" name="lectureSubjectNo">
	       	 		<option value="" selected>[강의]과목을 선택하세요.</option>
	       	 		<c:forEach var="a" items="${lectureSubjectList}">
	       	 			<option value="${a.lectureSubjectNo}">[${a.lectureName}]${a.subjectName}</option>
	       	 		</c:forEach>
	       	 	</select>
	      	 	</div>
	      	 	<div><input type="number" name = "m" value="${m}" hidden="hidden"></div>
	       	 <div><input type="number" name="y" value="${y}" hidden="hidden"></div>
			 </div>
			 <div class="modal-footer">
				<button id="addScheduleBtn"type="button" class="btn btn-danger">추가</button>
				<button type="button" id="btn"class="btn btn-secondary" data-dismiss="modal">취소</button>
			 </div>
	      </form>
	    </div>
	  </div>
	</div>
	<!-- addSchedule modal end -->
	<!-- ScheduleOne modal start -->
	<div class="modal fade" id="ScheduleOneModal" tabindex="-1" role="dialog" aria-labelledby="ScheduleOneModalLabel" aria-hidden="true">
	  <div class="modal-dialog" role="document">
	    <div class="modal-content">
	      <div class="modal-header">
	        <h5 class="modal-title" id="ScheduleOneModalLabel">강의상세보기</h5>
	        <button type="button" id= "close" class="close" data-dismiss="modal" aria-label="Close">
	          <span aria-hidden="true">&times;</span>
	        </button>
	      </div>
	       	 <div class="modal-body">
	       	 <div> 강의 : <span id="lectureName"></span></div>
			 <div> 과목 : <span id="subjectName"></span></div>
			 <div> 시간표 : <span id="scheduleDateOne"></span></div>
			 </div>
			 <div class="modal-footer">
				<!-- 요기다 좀 찾아라 hidden="hidden" -->
				<button type="button" id="btn"class="btn btn-secondary" data-dismiss="modal">확인</button>
			 </div>
	    </div>
	  </div>
	</div>
	<!-- ScheduleOne modal end -->
 <script>
	<!-- 유효성 검사-->
	$('#addScheduleBtn').click(function(){
		if($('#scheduleDate').val() == '') {
			$('#addScheduleHelper').text('날짜를 선택하세요.');
		} else if($('#lectureSubjectNo').val() == '') {
			$('#addScheduleHelper').text('[강의]과목을 선택하세요.');
		} else if($('#scheduleStartDate').val() > $('#scheduleEndDate').val()) {
			$('#addScheduleHelper').text('시작날짜가 종료날짜보다 늦게 되어있습니다.');
		} else {
			$('#addScheduleHelper').text('');
			$('#addScheduleForm').submit();
		}
	});
	<!-- 일정추가 하다가 취소버튼 누르면 초기화-->
	$('#btn').click(function(){
		$('#scheduleDate').val('')
		$('#lectureSubjectNo').val('')	
	});
	$('#close').click(function(){
		$('#scheduleDate').val('')
		$('#lectureSubjectNo').val('')	
	});
	
	
	<!-- 상세보기 AJAX-->
	function Click(scheduleNo) {
		
		var url = "${pageContext.request.contextPath}";
		$.ajax({
			type: "GET", //요청 메소드 방식(post, get)
			url: url+"/getReferenceOne", // Controller url 주소 (@GetMapping("/url") / @PostMapping("/url"))
			data :  "scheduleNo="+scheduleNo,
			dataType:"json", //서버가 요청 URL을 통해서 응답하는 내용의 타입 (return값)
			success : function(result){ // 응답성공시 실행할 
				//서버의 응답데이터가 클라이언트에게 도착하면 자동으로 실행되는함수(콜백)
			console.log(result);
				$('#lectureName').text(result.lectureName)
				$('#subjectName').text(result.subjectName)
				$('#scheduleDateOne').text(result.scheduleDate)
			},
			error : function(a, b, c){
				//통신 실패시 발생하는 함수(콜백)
				alert(a + b + c);
			}
		});
	}
 </script>
</body>
</html>

