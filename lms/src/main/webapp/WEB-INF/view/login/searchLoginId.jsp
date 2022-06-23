<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">

<head>
  <!-- Required meta tags -->
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  <title>search login id</title>
  <!-- plugins:css -->
  <link rel="stylesheet" href="${pageContext.request.contextPath}/vendors/feather/feather.css">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/vendors/ti-icons/css/themify-icons.css">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/vendors/css/vendor.bundle.base.css">
  <!-- endinject -->
  <!-- Plugin css for this page -->
  <link rel="stylesheet" href="${pageContext.request.contextPath}/vendors/select2/select2.min.css">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/vendors/select2-bootstrap-theme/select2-bootstrap.min.css">
  <!-- End plugin css for this page -->
  <!-- inject:css -->
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/vertical-layout-light/style.css">
  <!-- endinject -->
  <link rel="shortcut icon" href="${pageContext.request.contextPath}/images/favicon.png" />
  <style>
  	.bottom {margin-bottom : 40px;}
  	.auth .auth-form-light select {
  		color: #4C4C4C;
  	}
    .boxShadow {box-shadow: 0 20px 25px -5px rgb(0 0 0 / 10%);}
  </style>
</head>

<body>
	<div class="container-scroller">
		<div class="container-fluid page-body-wrapper full-page-wrapper">
			<div class="content-wrapper d-flex align-items-center auth px-0">
				<div class="row w-100 mx-0">
					<div class="col-lg-7 mx-auto">
						<ul class="nav nav-tabs nav-justified">
							<li class = "nav-item">
								<c:choose>
									<c:when test="${msg eq 'student'}">
										<a class= "nav-link active" href="${pageContext.request.contextPath}/searchLoginId?msg=student">Student</a>		
									</c:when>
									<c:otherwise>
										<a class= "nav-link" href="${pageContext.request.contextPath}/searchLoginId?msg=student">Student</a>
									</c:otherwise>
								</c:choose>
							</li>
							<li class="nav-item">
								<c:choose>
									<c:when test="${msg eq 'teacher'}">
										<a class="nav-link active" href="${pageContext.request.contextPath}/searchLoginId?msg=teacher">Teacher</a>		
									</c:when>
									<c:otherwise>
										<a class="nav-link" href="${pageContext.request.contextPath}/searchLoginId?msg=teacher">Teacher</a>
									</c:otherwise>
								</c:choose>
							</li>
							<li class="nav-item">
								<c:choose>
									<c:when test="${msg eq 'manager'}">
										<a class="nav-link active" href="${pageContext.request.contextPath}/searchLoginId?msg=manager">Manager</a>		
									</c:when>
									<c:otherwise>
										<a class="nav-link" href="${pageContext.request.contextPath}/searchLoginId?msg=manager">Manager</a>
									</c:otherwise>
								</c:choose>
							</li>
						</ul>
						<div class="auth-form-light text-left py-5 px-4 px-sm-5 boxShadow">
							<div class="card-body">
					            <h4 class="card-title bottom">아이디 찾기</h4>
					          
					            <form id="searchLoginIdForm" method="post" action="${pageContext.request.contextPath}/searchLoginId" class="forms-sample">
						            <div class="form-group">
						            <input type="hidden" name="msg" value="${msg}">
							            <label>Name</label>
							            <input type="text" name="name" class="form-control" placeholder="name" id="name">
							            <span id="nameHelper"></span>
						            </div>
						            <div class="form-group">
							            <label>E-mail</label>
							            <input type="text" name="email" class="form-control" placeholder="email" id="email">
							            <span id="emailHelper"></span>
						            </div>
						            
						            <c:if test="${loginId == null}">
						            	<button id="searchId" type="button" class="btn btn-primary mr-2">아이디 찾기</button>
						            	<a href="${pageContext.request.contextPath}/login" class="btn btn-light" type="button">로그인 화면으로</a>
						            </c:if>
						         
						             <c:if test="${loginId != null}"> ${loginId}
						             	<br><br><a href="${pageContext.request.contextPath}/login" class="btn btn-primary mr-2" type="button">로그인 하러가기</a>
						             	<a href="${pageContext.request.contextPath}/searchLoginPw" class="btn btn-light" type="button">비밀번호 찾기</a>
						             </c:if>
					            </form>
					        </div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
  <!-- container-scroller -->
  <!-- plugins:js -->
  <script src="${pageContext.request.contextPath}/vendors/js/vendor.bundle.base.js"></script>
  <!-- endinject -->
  <!-- Plugin js for this page -->
  <script src="${pageContext.request.contextPath}/vendors/typeahead.js/typeahead.bundle.min.js"></script>
  <script src="${pageContext.request.contextPath}/vendors/select2/select2.min.js"></script>
  <!-- End plugin js for this page -->
  <!-- inject:js -->
  <script src="${pageContext.request.contextPath}/js/off-canvas.js"></script>
  <script src="${pageContext.request.contextPath}/js/hoverable-collapse.js"></script>
  <script src="${pageContext.request.contextPath}/js/template.js"></script>
  <script src="${pageContext.request.contextPath}/js/settings.js"></script>
  <script src="${pageContext.request.contextPath}/js/todolist.js"></script>
  <!-- endinject -->
  <!-- Custom js for this page-->
  <script src="${pageContext.request.contextPath}/js/file-upload.js"></script>
  <script src="${pageContext.request.contextPath}/js/typeahead.js"></script>
  <script src="${pageContext.request.contextPath}/js/select2.js"></script>
  <!-- End custom js for this page-->
  
  <script type="text/javascript">
  	
  	$('#name').blur(function(){
		if ($('#name').val() == ''){
			$('#nameHelper').text('이름을 입력하세요');
		} else {
			$('#nameHelper').text('');
		}
	});
  
  
	$('#email').blur(function(){
		if ($('#email').val() == ''){
			$('#emailHelper').text('이메일을 입력하세요');
		} else if($('#email').val().indexOf('@') == -1 || $('#email').val().indexOf('.') == -1) {
			$('#emailHelper').text('이메일 형식이 다릅니다.');
		} else {
			$('#emailHelper').text('');
		}
	});

  
  	$('#searchId').click(function(){
  		if($('#name').val() == ''){
  			$('#nameHelper').text('이름을 입력하세요.');
  		} else if($('#email').val() == ''){
  			$('#nameHelper').text('');
  			$('#emailHelper').text('이메일을 입력하세요.');
  		} else{
  			$('#searchLoginIdForm').submit();
  		}
  	});
  
  </script>
  
</body>



</html>
