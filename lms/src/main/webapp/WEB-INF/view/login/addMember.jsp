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
  	.auth .auth-form-light select {color: #4C4C4C;}
  	.button-bottom {margin-bottom : 10px;}
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
						<div class="auth-form-light text-left py-5 px-4 px-sm-5 boxShadow">
							<div class="card-body">
					            <h3 class="card-title bottom">회원 가입</h3>
					            <form class="forms-sample" id="addMemberForm" method="post" action="${pageContext.request.contextPath}/addMember" enctype="multipart/form-data">
						            <div class="row">
						            	<input type="hidden" name="addChk" value="${addChk}">
							            <div class="form-group col">
								            <label>ID 중복 검사</label>
								            <input type="text" class="form-control button-bottom" placeholder="ID를 입력해주세요." id="id">
								            <span id="idHelper"></span>
								            <button type="button" class="float-right btn btn-primary mr-2" id="idChk">아이디 중복 검사</button>
							            </div>
							            <div class="form-group col">
								            <label>ID</label>
								            <input type="text" name="loginId" class="form-control" placeholder="ID" readonly="readonly" id="realId">
							            </div>
							        </div>
						            <div class="form-group">
							            <label>비밀번호</label>
							            <input type="password" name="loginPw" class="form-control" placeholder="비밀번호" id="pw">
							            <span id="pwHelper"></span>
						            </div>
						            <div class="form-group">
							            <label>이름</label>
							            <input type="text" name="name" class="form-control" placeholder="이름" id="name">
							            <span id="nameHelper"></span>
						            </div>
						            <div class="form-group">
							            <label>성별</label>
							            <select name="gender" class="form-control">
						                    <option>남</option>
						                    <option>여</option>
					                  	</select>
						          	</div>
						            <div class="form-group">
							            <label>생년월일</label>
							            <input type="date" class="form-control" name="birth" id="birth">
							            <span id="birthHelper"></span>
						          	</div>
						            <div class="form-group">
							            <label>이메일</label>
							            <input type="email" name="email" class="form-control" placeholder="이메일" id="email">
							            <span id="emailHelper"></span>
						            </div>
						            <div class="form-group">
							            <label>휴대폰 번호</label>
							            <input type="text" name="phone" class="form-control" placeholder="-를 제외하고 입력해주세요." id="phone">
							            <span id="phoneHelper"></span>
						            </div>
						            <div class="form-group">
							            <label>주소</label>
							            <input type="text" class="form-control button-bottom" placeholder="주소를 입력해주세요." id="addr">
							            <button type="button" class="float-right btn btn-primary mr-2 button-bottom" id="searchAddr">주소 검색</button>
							            <div id="addrHelper"></div>
						            </div>
						            <div class="form-group">
						            	<select name="address"  class = "form-control" id="searchAddrList">
						            		<!-- 주소 들어올 공간 -->
						            	</select>
						            </div>
						            <div class="form-group">
						            	<label>상세 주소</label>
							            <input type="text" name="detailAddr" class="form-control" placeholder="상세주소를 입력해주세요." id="detailAddr">
							            <span id="detailAddrHelper"></span>
						            </div>
						            <div class="form-group">
						            	<label>회원 사진</label>
						            	<div class="custom-file">
	   										<input type="file" class="custom-file-input" id="customFile" name="customFile">
	   										<label class="custom-file-label" for="customFile">회원 사진으로 등록할 사진을 넣어주세요.</label>
	 									</div>
	 									<span id="imageHelper"></span>
						            </div>
						            
						            <!-- 학생과 강사 -->
						            <c:if test="${addChk eq 'student' || addChk eq 'teacher'}">
							            <div class="form-group">
								            <label>학력</label>
								            <select class="form-control" name="graduate">
								             	<option>고졸</option>
							                    <option>초대졸</option>
							                    <option>대졸</option>
					                  		</select>
							            </div>
						            </c:if>
						            
						            <!-- 학생만 -->
						            <c:if test="${addChk eq 'student'}">
							            <div class="form-group">
								            <label>병역 여부</label>
								            <select class="form-control" name="military">
								             	<option>해당없음</option>
							                    <option>군필</option>
							                    <option>미필</option>
					                  		</select>
							            </div>
						            </c:if>
						            
						            <!-- 매니저만 -->
						            <c:if test="${addChk eq 'manager'}">
							            <div class="form-group">
								            <label>부서</label>
								            <select class="form-control" name="deptNo">
								             	<c:forEach var="d" items="${dept}">
								             		<option value="${d.deptNo}">${d.deptName}</option>
								             	</c:forEach>
					                  		</select>
							            </div>
							            <div class="form-group">
								            <label>직급</label>
								            <select class="form-control" name="positionNo">
								             	<c:forEach var="p" items="${position}">
								             		<option value="${p.positionNo}">${p.positionName}</option>
								             	</c:forEach>
					                  		</select>
							            </div>
						            </c:if>
						            
						            <button type="button" class="btn btn-primary mr-2" id="addMemberBtn">회원 가입</button>
						            <button class="btn btn-light">입력 취소</button>
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
  		if($('#addr').val().length > 1) {
  			$.ajax({
  				type:'get'
  				, url : url+'/searchAddr'
  				, data : {keyword:$('#addr').val()}
  				, success:function(a){
  					console.log(a);
  					console.log(typeof(a));
  					var a2 = JSON.parse(a);
  					console.log(typeof(a2));
  					console.log(a2);
  					
  					let arr = a2.results.juso;
  					console.log(arr);
  					for(let i=0; i<arr.length; i++) {
  						$('#searchAddrList').append('<option>'+arr[i].roadAddrPart1+'</option>');
  					}
  				}
  			})
  		} else {
  			$('#searchAddrList').val('');
  			$('#addrHelper').text('검색할 주소를 입력해주세요.');
  		}
  	});
  	
  	$('#addMemberBtn').click(function() {
  		if($('#realId').val()=='') {
  			alert('아이디 중복 검사 해주세요');
  		} else if($('#pw').val()=='') {
  			$('#pwHelper').text('비밀번호를 입력해주세요.');
  		} else if($('#name').val()=='') {
  			$('#pwHelper').text('');
  			$('#nameHelper').text('이름을 입력해주세요.');
  		} else if($('#birth').val()==''){
  			$('#nameHelper').text('');
  			$('#birthHelper').text('생년월일을 입력해주세요.');
  		} else if($('#email').val()=='') {
  			$('#nameHelper').text('');
  			$('#emailHelper').text('이메일을 입력해주세요.');
  		} else if($('#email').val().indexOf('@') == -1 || $('#email').val().indexOf('.') == -1) {
  			$('#emailHelper').text('');	
  			$('#emailHelper').text('이메일 형식이 다릅니다.');	
  		} else if($('#phone').val()=='') {
  			$('#emailHelper').text('');
  			$('#phoneHelper').text('휴대폰 번호를 입력해주세요.');
  		} else if($('#phone').val().indexOf('-') != -1) {
  			$('#phoneHelper').text('');
  			$('#phoneHelper').text('-을 제외해서 입력해주세요.');
  		} else if($('#addr').val()=='') {
  			$('#phoneHelper').text('');
  			$('#addrHelper').text('주소를 검색해주세요.');
  		} else if($('#detailAddr').val()=='') {
  			$('#addrHelper').text('');
  			$('#detailAddrHelper').text('상세 주소를 입력해주세요.');
  		} else if($('#customFile').val()=='') {
  			$('#detailAddrHelper').text('');
  			$('#imageHelper').text('사진을 등록해주세요.');
  		} else {
  			$('#addMemberForm').submit();	
  		}
  	});
  	
  	$(".custom-file-input").on("change", function() {
  	  var fileName = $(this).val().split("\\").pop();
  	  $(this).siblings(".custom-file-label").addClass("selected").html(fileName);
  	});
  	
  </script>
</body>

</html>
