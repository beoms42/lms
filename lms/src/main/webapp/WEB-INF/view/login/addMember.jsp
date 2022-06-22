<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">

<head>
  <!-- Required meta tags -->
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  <title>Skydash Admin</title>
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
  </style>
</head>

<body>
	<div class="container-scroller">
		<div class="container-fluid page-body-wrapper full-page-wrapper">
			<div class="content-wrapper d-flex align-items-center auth px-0">
				<div class="row w-100 mx-0">
					<div class="col-lg-8 mx-auto">
						<ul class="nav nav-tabs nav-justified">
							<li class = "nav-item">
								<c:choose>
									<c:when test="${addChk eq 'student'}">
										<a class= "nav-link active" href="${pageContext.request.contextPath}/addMember?addChk=student">Student</a>		
									</c:when>
									<c:otherwise>
										<a class= "nav-link" href="${pageContext.request.contextPath}/addMember?addChk=student">Student</a>
									</c:otherwise>
								</c:choose>
							</li>
							<li class="nav-item">
								<c:choose>
									<c:when test="${addChk eq 'teacher'}">
										<a class="nav-link active" href="${pageContext.request.contextPath}/addMember?addChk=teacher">Teacher</a>		
									</c:when>
									<c:otherwise>
										<a class="nav-link" href="${pageContext.request.contextPath}/addMember?addChk=teacher">Teacher</a>
									</c:otherwise>
								</c:choose>
							</li>
							<li class="nav-item">
								<c:choose>
									<c:when test="${addChk eq 'manager'}">
										<a class="nav-link active" href="${pageContext.request.contextPath}/addMember?addChk=manager">Manager</a>		
									</c:when>
									<c:otherwise>
										<a class="nav-link" href="${pageContext.request.contextPath}/addMember?addChk=manager">Manager</a>
									</c:otherwise>
								</c:choose>
							</li>
						</ul>
						<div class="auth-form-light text-left py-5 px-4 px-sm-5">
							<div class="card-body">
					            <h4 class="card-title bottom">회원 가입</h4>
					            
					            <form class="forms-sample">
					            <div class="row">
						            <div class="form-group col">
							            <label>Id Check</label>
							            <input type="text" class="form-control" placeholder="Id를 입력해주세요" id="id">
							            <span id="idHelper"></span>
							            <button type="button" class="float-right btn-inverse-primary" id="idChk">아이디 중복 검사</button>
						            </div>
						            <div class="form-group col">
							            <label>Id</label>
							            <input type="text" class="form-control" placeholder="Id" id="realId" readonly="readonly">
						            </div>
						         </div>
						            <div class="form-group">
							            <label>Password</label>
							            <input type="password" class="form-control" placeholder="Password">
						            </div>
						            <div class="form-group">
							            <label>Name</label>
							            <input type="text" class="form-control" placeholder="Name">
						            </div>
						            <div class="form-group">
							            <label>Gender</label>
							            <select class="form-control">
						                    <option>Male</option>
						                    <option>Female</option>
					                  	</select>
						          	</div>
						            <div class="form-group">
							            <label>Email address</label>
							            <input type="email" class="form-control" placeholder="Email">
						            </div>
						            <div class="form-group">
							            <label>Phone</label>
							            <input type="number" class="form-control" placeholder="-를 제외하고 입력해주세요">
						            </div>
						            <div class="form-group">
							            <label>Address</label>
							            <input type="text" class="form-control" placeholder="주소를 입력해주세요" id="Addr">
							            <button type="button" class="float-right btn-inverse-primary" id="searchAddr">주소 검색</button>
						            </div>
						            <br>
						            <div class="form-group">
							            <input type="text" class="form-control" placeholder="Address" readonly="readonly">
						            </div>
						            
						            <div class="form-group">
						            	<label>detail Address</label>
							            <input type="text" class="form-control" placeholder="상세주소를 입력해주세요">
						            </div>
						            
						            <!-- 학생과 강사만 -->
						            <c:if test="${addChk eq 'student' || addChk eq 'teacher'}">
							            <div class="form-group">
								            <label>Military</label>
								            <select class="form-control">
								             	<option>해당없음</option>
							                    <option>군필</option>
							                    <option>미필</option>
					                  		</select>
							            </div>
						            </c:if>
						            <!-- 학생만 -->
						            <c:if test="${addChk eq 'student'}">
							            <div class="form-group">
								            <label>Education</label>
								            <select class="form-control">
								             	<option>고졸</option>
							                    <option>초대졸</option>
							                    <option>대졸</option>
					                  		</select>
							            </div>
						            </c:if>
						            <button type="submit" class="btn btn-primary mr-2">Submit</button>
						            <button class="btn btn-light">Cancel</button>
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
  <script>
  var url="${pageContext.request.contextPath}";
  	$('#idChk').click(function() {
  		if($('#id').val().length > 3) {
  			$.ajax({
  				type:'post'
  				, url : url+'/idCheck'
  				, data : {id:$('#id').val()}
  				, success:function(ck) {
  					console.log('ck:', ck);
  					if(ck=='false') {
  						$('#idHelper').text('이미 사용중인 아이디 입니다.');
  						$('#realId').val('');
  					} else {	
  						$('#idHelper').text('사용가능한 아이디 입니다.');
  						$('#id').val(ck);
  						$('#realId').val(ck);
  						
  					}
  				}
  			})
  		} else {
			$('#idHelper').text('id는 4자 이상 입력해주셔야합니다.');
  		}
  	});
  	
  	$('#searchAddr').click(function() {
  		if($('#Addr').val().length > 1) {
  			$.ajax({
  				type:'get'
  				, url : url+'/searchAddr'
  				, data : {addr:$('#addr').val()}
  				, success:function(a){
  					
  				}
  			})
  		}
  	});
  </script>
</body>

</html>
