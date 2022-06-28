<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>

 <script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>
 <script src="https://code.jquery.com/jquery-3.5.1.min.js" crossorigin="anonymous"></script>
 <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
 <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
 <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js" integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>
   <script src="https://code.jquery.com/jquery-3.5.1.min.js" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>

    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js" integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>

    <link href="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote-bs4.min.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote-bs4.min.js"></script>  <!-- endinject -->
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
  	table{
  		text-align:center;
  	}
  </style>
</head>
<body>
  <div class="container-scroller">
    <!-- partial:partials/_navbar.html -->
    <nav class="navbar col-lg-12 col-12 p-0 fixed-top d-flex flex-row">
      <div class="text-center navbar-brand-wrapper d-flex align-items-center justify-content-center">
        <a class="navbar-brand brand-logo mr-5" href="${pageContext.request.contextPath}/loginCheck/main">LMS-TFT</a>
        <a class="navbar-brand brand-logo-mini" href="${pageContext.request.contextPath}/loginCheck/main">LMS</a>
      </div>
      <div class="navbar-menu-wrapper d-flex align-items-center justify-content-end">
        <button class="navbar-toggler navbar-toggler align-self-center" type="button" data-toggle="minimize">
          <span class="icon-menu"></span>
        </button>
        <ul class="navbar-nav mr-lg-2">
          <li class="nav-item nav-search d-none d-lg-block">
            <div class="input-group">
            	
            </div>
          </li>
        </ul>
        <ul class="navbar-nav navbar-nav-right">
          <li class="nav-item dropdown">
            <a class="nav-link count-indicator dropdown-toggle" id="notificationDropdown" href="#" data-toggle="dropdown">
              <i class="icon-bell mx-0"></i>
              <span class="count"></span>
            </a>
            <div class="dropdown-menu dropdown-menu-right navbar-dropdown preview-list" aria-labelledby="notificationDropdown">
              <p class="mb-0 font-weight-normal float-left dropdown-header">Notifications</p>
              <a class="dropdown-item preview-item">
                <div class="preview-thumbnail">
                  <div class="preview-icon bg-success">
                    <i class="ti-info-alt mx-0"></i>
                  </div>
                </div>
                <div class="preview-item-content">
                  <h6 class="preview-subject font-weight-normal">Application Error</h6>
                  <p class="font-weight-light small-text mb-0 text-muted">
                    Just now
                  </p>
                </div>
              </a>
              <a class="dropdown-item preview-item">
                <div class="preview-thumbnail">
                  <div class="preview-icon bg-warning">
                    <i class="ti-settings mx-0"></i>
                  </div>
                </div>
                <div class="preview-item-content">
                  <h6 class="preview-subject font-weight-normal">Settings</h6>
                  <p class="font-weight-light small-text mb-0 text-muted">
                    Private message
                  </p>
                </div>
              </a>
              <a class="dropdown-item preview-item">
                <div class="preview-thumbnail">
                  <div class="preview-icon bg-info">
                    <i class="ti-user mx-0"></i>
                  </div>
                </div>
                <div class="preview-item-content">
                  <h6 class="preview-subject font-weight-normal">New user registration</h6>
                  <p class="font-weight-light small-text mb-0 text-muted">
                    2 days ago
                  </p>
                </div>
              </a>
            </div>
          </li>
          <li class="nav-item nav-profile dropdown">
            <a class="nav-link dropdown-toggle" href="#" data-toggle="dropdown" id="profileDropdown">
              <img src="${pageContext.request.contextPath}/images/tftace.jpg" alt="profile"/>
            </a>
            <div class="dropdown-menu dropdown-menu-right navbar-dropdown" aria-labelledby="profileDropdown">
              <a class="dropdown-item">
                <i class="ti-settings text-primary"></i>
                Settings
              </a>
              <a class="dropdown-item">
                <i class="ti-power-off text-primary"></i>
                Logout
              </a>
            </div>
          </li>
        </ul>
        <button class="navbar-toggler navbar-toggler-right d-lg-none align-self-center" type="button" data-toggle="offcanvas">
          <span class="icon-menu"></span>
        </button>
      </div>
    </nav>
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
					<div class="container col_12">
					  <div class="row">
					            <div class="col-lg-12 grid-margin stretch-card">
					              <div class="card">
					                <div class="card-body">
					                   <h4 class="card-title">${lectureName} 과제</h4>
					                  <p class="card-description">
					                  </p>
					                  <div class="table-responsive">
											<table class="table table-striped">
										        <tbody>
										        	<c:forEach var="n" items="${assignmentList}">
											                <tr>
													        	<td>제목</td>
											                	<td>${n.assignmentExamTitle}</td>
											               </tr>
											                <tr>
											                	<td>제출 기한</td>
											                	<td>${n.assignmentDeadLine}</td>
											                </tr> 
												                <tr>
												                	<td>내용</td>
														            <td>
														            	${n.assignmentExamContent}
																		<script>
																			$('#summernote').summernote({
																			  tabsize: 2,
																			  height: 400
																			});
																			$(".note-editor button[aria-label='Picture']").hide();
																			$(".note-editor button[aria-label='Video']").hide();
																			$(".note-editor .note-view").hide();
																		</script>
																	</td>
														        </tr>
													      
												        </c:forEach>
										        </tbody>
						
										    </table>
										    <c:if test="${level>=2 }">
								      			<a href="${pageContext.request.contextPath}/loginCheck/modifyAssignment?assignmentExamNo=${n.assignmentExamNo}" class="btn btn-primary">수정</a>
								      			<a href="${pageContext.request.contextPath}/loginCheck/deleteAssignment?assignmentExamNo=${n.assignmentExamNo}" class="btn btn-danger">삭제</a>
											 </c:if>
										  </div>
										  </div>
										  </div>
										  </div>
										  </div>

											<c:if test="${level >=2 }">
											<div class="col-lg-12 grid-margin stretch-card">
								              <div class="card">
								                <div class="card-body">
								                  <h4 class="card-title">제출 내역</h4>
								                  <p class="card-description"></p>
								                  <div class="table-responsive">
														<c:forEach var="m" items="${assignmentSubmitTeacher}">
															<table class="table table-hover">
																	<thead>
																		<tr>
																			<th>작성자</th>
																			<th>내용</th>
																			<th>제출 시간</th>
																			<th>싸인</th>
																			<th>파일</th>
																		</tr>
																	</thead>
																	<tbody>
																		<tr>
																			<td>${m.loginId}</td>
																			<td>${m.assignmentSubmitContent}</td>
																			<td>${m.createDate}</td>
																			<td><img src="${m.assignmentSignfileURL}" width="100%"></td>
																			<c:forEach var="f" items="${fileList}">
																  				<c:if test="${f.assignmentFileType.equals('image/jpeg') || f.assignmentFileType.equals('image/gif') || f.assignmentFileType.equals('image/PNG') || f.assignmentFileType.equals('image/webp')}">
																	  				<td><img src="${pageContext.request.contextPath}/file/assignmentSubmitFile/${f.assignmentFileName}" width="100%"></td>
																  				</c:if>
																  				<c:if test="${f.assignmentFileType.equals('application/octet-stream')}">
																  					<td><a href="${pageContext.request.contextPath}/file/assignmentSubmitFile/${f.assignmentFileName}">${f.assignmentFileOriginName}</a></td>
																  				</c:if>
																  				<c:if test="${fn:length(fileList) = 0}">
																  					<td></td>
																  				</c:if>
																			</c:forEach>
																		</tr>
																	</tbody>
																</table>
														</c:forEach>
														
														</div>
														</div>
														</div>
														</div>
													</c:if>
													<c:if test="${level < 2 }">
														    <c:if test="${fn:length(assignmentSubmit) == 0}">
														    <div class="col-lg-12 grid-margin stretch-card">
												              <div class="card">
												                <div class="card-body">
												                  <h4 class="card-title">제출</h4>
												                  <p class="card-description"></p>
												                  <div class="table-responsive">
															  	<form method="POST" action="${pageContext.request.contextPath}/loginCheck/getAssignmentOne?assignmentExamNo=${assignmentExamNo}" id="addForm" enctype="multipart/form-data">
																  <div>
																	   <textarea name="assignmentSubmitContent" id="summernote" class="assignmentSubmitContent"></textarea>
																		<script>
																			$('#summernote').summernote({
																			  tabsize: 2,
																			  height: 400
																			});
																			$(".note-editor button[aria-label='Picture']").hide();
																			$(".note-editor button[aria-label='Video']").hide();
																			$(".note-editor .note-view").hide();
																		</script>
																	</div>
																	
																	<div>
																		<button type="button" id="addFileupload" class="btn btn-primary">파일 업로드 추가</button>
																	</div>
																	<div id="fileSection">
																			<!-- 파일 업로드 input 태그가 추가될 영역 -->
																	</div>
																	<div>
																		<button type="button" class="btn btn-primary" id="addAssignment">제출</button>
																	</div>
																	</form>
																	</div>
																	</div>
																	</div>
																	</div>
																</c:if>
													 											
														<c:if test="${fn:length(assignmentSubmit) != 0}">
															
															<div class="col-lg-12 grid-margin stretch-card">
												              <div class="card">
												                <div class="card-body">
												                  <h4 class="card-title">제출</h4>
												                  <p class="card-description"></p>
												                  <div class="table-responsive">	
															<table class="table table-hover">
																<thead>
																	<tr>
																		<th>작성자</th>
																		<th>내용</th>
																		<th>제출 시간</th>
																		<th>싸인</th>
																		<th>파일</th>
																	</tr>
																</thead>
																<tbody>
																	<tr>
																		<c:forEach var="m" items="${assignmentSubmit}">
																			<td>${loginId}</td>
																			<td>${m.assignmentSubmitContent}</td>
																			<td>${m.createDate}</td>
																			<td><img src="${m.assignmentSignfileURL}" width="100%"></td>
																		</c:forEach>
																		<c:forEach var="f" items="${fileList}">
														  					<c:if test="${f.assignmentFileType.equals('image/jpeg') || f.assignmentFileType.equals('image/gif') || f.assignmentFileType.equals('image/PNG') || f.assignmentFileType.equals('image/webp')}">
																  				<td><img src="${pageContext.request.contextPath}/file/assignmentSubmitFile/${f.assignmentFileName}" width="100%"></td>
															  				</c:if>
															  				
															  				<c:if test="${f.assignmentFileType.equals('application/octet-stream')}">
															  					<td><a href="${pageContext.request.contextPath}/file/assignmentSubmitFile/${f.assignmentFileName}">${f.assignmentFileOriginName}</a></td>
															  				</c:if>
																		</c:forEach>
																	</tr>
													
																</tbody>
															</table>
															</div>
															</div>
															</div>
															</div>
														</c:if>
													
											</c:if>
									
							
							<BR>	

							
					
					</div>

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

	$('#addFileupload').click(function(){
		let flag = true;

		$('.assignmentSubmitFileList').each(function(){
			if($(this).val() == ''){
					flag = false;
			}
		});
		
		if(flag){
			$('#fileSection').append("<div><input type='file' class='assignmentSubmitFileList form-control-file border' name='assignmentSubmitFileList'></div>");
		}
	});
	
	let flag = true;
	
	$('#addAssignment').click(function(){
			if(flag){
				$('#addForm').submit();
				return;
			} else{
				alert('파일이 첨부되지 않았습니다.');
			}
		});
	
	
</script>
</body>

</html>

