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
  	.top {margin-top : 40px;}
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
						            	<input type="hidden" name="addChk" id="addChk" value="${addChk}">
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
							            <label>비밀번호 (비밀번호는 8자 이상, 특수문자 포함해주셔야 합니다.)</label>
							            <input type="password" name="loginPw" class="form-control" placeholder="비밀번호" id="pw">
							            <span id="pwHelper"></span>
						            </div>
						            <div class="form-group">
							            <label>비밀번호 확인</label>
							            <input type="password" class="form-control" placeholder="비밀번호 확인" id="pwCk">
							            <span id="pwCkHelper"></span>
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
  									<div class="row top">
  										<div class="form-group col">
								            <label>이메일 중복 검사</label>
								            <input type="email" class="form-control button-bottom" placeholder="이메일을 입력해주세요." id="email">
								            <span id="emailHelper"></span>
									        <button type="button" class="float-right btn btn-primary mr-2" id="emailChk">이메일 중복 검사</button>
							            </div>
							            <div class="form-group col">
								            <label>이메일</label>
								            <input type="email" name="email" class="form-control" placeholder="이메일" id="realEmail" readonly="readonly">
							            </div>
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
						            
						            <button type="button" class="btn btn-primary mr-2" id="addMemberBtn" onclick="doSearch()">회원 가입</button>
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
  
  	// 아이디 중복 체크
  	$('#idChk').click(function() {
  		if($('#id').val().length > 3) {
  			$.ajax({
  				type:'get'
  				, url : url+'/id'
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
  	
  	// 이메일 중복 체크
  	$('#emailChk').click(function() {
  		if($('#email').val().length > 8) {
  			$.ajax({
  				type:'get'
  				, url : url +'/email'
  				, data : {email:$('#email').val(), addChk:$('#addChk').val()}
  				, success: function(ck) {
  					console.log('ck : ', ck);
  					if(ck=='false') {
  						$('#emailHelper').text('이미 사용중인 이메일 입니다.');
  						$('#realEmail').val('');
  					} else {	
  						$('#emailHelper').text('사용가능한 이메일 입니다.');
  						$('#email').val(ck);
  						$('#realEmail').val(ck);
  					}
  				}
  			})
  		} else {
  			$('#emailHelper').text('이메일은 최소 8자 이상 입력해주셔야합니다.');
  		}
  	});
  	
  	// 주소검색
  	$('#searchAddrList').hide();
  	$('#searchAddr').click(function() {
  		$('#searchAddrList').show();
		$('#searchAddrList').empty();
  		if($('#addr').val().length > 1) {
  			$.ajax({
  				type:'get'
  				, url : url+'/searchAddr'
  				, data : {keyword:$('#addr').val()} //검색한 키워드
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
  	
  	// 파일 타입 체크
 	$('#customFile').blur(function() {
  		if($('#customFile').val() != '') {
  			var file = $('#customFile').val();
  			console.log("file",file);
  			if(file.indexOf('jpg') != -1 || file.indexOf('jpeg') != -1 || file.indexOf('png') != -1 || file.indexOf('PNG') != -1) {
  				console.log("성공여부", file.indexOf("jpg"));
  			} else {
  				$('#imageHelper').text('사진 형식이 아닙니다.');
  			}
  	  	}
  	}); 
  	
  	var spc = RegExp(/[~!@#$%^&*()|<>?:{}]/); // RegExp는 정규표현식을 사용하기 위한 객체  , 정규표현식은 /와 /사이에 식을 넣어서 사용한다.
  	
  	//유효성 
  	$('#pw').blur(function() {
  		if($('#pw').val()=='') {
  			$('#pwHelper').text('비밀번호를 입력해주세요.');
  		} else if(!spc.test($('#pw').val())) { // pw에 spc가 없다면
  			$('#pwHelper').text('비밀번호에 특수문자를 포함해주세요.');
  		} else if($('#pw').val().length <9) {
  			$('#pwHelper').text('비밀번호를 8자 이상 입력해주세요.');
  		} else {
  			$('#pwHelper').text('');
  		}
  	})
  	
  	$('#pwCk').blur(function() {
  		 if($('#pwCk').val()=='') {
   			$('#pwCkHelper').text('확인 할 비밀번호를 입력하세요');
  		 } else if($('#pw').val() != $('#pwCk').val()) {
   			$('#pwCkHelper').text('비밀번호와 일치하지 않습니다.');
  		 } else {
  			$('#pwCkHelper').text('');
  		 }
  	})
  	
  	$('#name').blur(function() {
  		if($('#name').val()=='') {
  			$('#nameHelper').text('이름을 입력해주세요.');
  		} else {
  			$('#nameHelper').text('');
  		}
  	})
  	
  	$('#birth').blur(function() {
  		if($('#birth').val()=='') {
  			$('#birthHelper').text('생년월일을 입력해주세요.');
  		} else {
  			$('#birthHelper').text('');
  		}
  	})
  	
  	$('#email').blur(function() {
  		if($('#email').val()=='') {
  			$('#realEmail').val('');
  			$('#emailHelper').text('이메일을 입력해주세요.');
  		} else if($('#email').val().indexOf('@') == -1 || $('#email').val().indexOf('.') == -1) {
  			$('#realEmail').val('');
  			$('#emailHelper').text('이메일 형식이 다릅니다.');	
  		} else {
  			$('#emailHelper').text('');
  		}
  	})
  	
  	$('#phone').blur(function() {
  		if($('#phone').val()=='') {
  			$('#phoneHelper').text('휴대폰 번호를 입력해주세요.');
  		} else if($('#phone').val().indexOf('-') != -1) {
  			$('#phoneHelper').text('-을 제외해서 입력해주세요.');
  		} else {
  			$('#phoneHelper').text('');
  		}
  	})
  	
  	$('#addr').blur(function() {
  		if($('#addr').val()=='') {
  			$('#addrHelper').text('주소를 검색해주세요.');
  		} else {
  			$('#addrHelper').text('');
  		}
  	})
  	
  	$('#detailAddr').blur(function() {
  		if($('#detailAddr').val()=='') {
  			$('#detailAddrHelper').text('상세 주소를 입력해주세요.');
  		} else {
  			$('#detailAddrHelper').text('');
  		}
  	})
  	
  	// 버튼 눌렀을시 유효성 검사
  	$('#addMemberBtn').click(function() {
  		var file = $('#customFile').val();
		console.log("file",file);
  		if($('#realId').val()=='') {
  			alert('아이디 중복 검사 해주세요');
  		} else if($('#pw').val()=='') {
  			$('#pwHelper').text('비밀번호를 입력해주세요.');
  		} else if(!spc.test($('#pw').val())) { // pw에 spc가 없다면
  			$('#pwHelper').text('비밀번호에 특수문자를 포함해주세요.');
  		} else if($('#pw').val().length <9) {
  			$('#pwHelper').text('비밀번호를 8자 이상 입력해주세요.');
  		} else if($('#pwCk').val()=='') {
  			$('#pwHelper').text('');
  			$('#pwCkHelper').text('확인 할 비밀번호를 입력하세요');
  		} else if($('#pw').val() != $('#pwCk').val()) {
  			$('#pwCkHelper').text('비밀번호와 일치하지 않습니다.');
  		} else if($('#name').val()=='') {
  			$('#pwCkHelper').text('');
  			$('#nameHelper').text('이름을 입력해주세요.');
  		} else if($('#birth').val()==''){
  			$('#nameHelper').text('');
  			$('#birthHelper').text('생년월일을 입력해주세요.');
  		} else if($('#email').val()=='') {
  			$('#realEmail').val('');
  			$('#birthHelper').text('');
  			$('#emailHelper').text('이메일을 입력해주세요.');
  		} else if($('#realEmail').val()=='') {
  			$('#realEmail').val('');
  			$('#birthHelper').text('');
  			$('#emailHelper').text('');	
  			alert('이메일 중복 검사 해주세요.');	
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
  			$('#phoneHelper').text('');
  			$('#addrHelper').text('');
  			$('#detailAddrHelper').text('상세 주소를 입력해주세요.');
  		} else if($('#customFile').val()=='') {
  			$('#detailAddrHelper').text('');
  			$('#imageHelper').text('사진을 등록해주세요.');
  		} else if(file.indexOf('jpg') == -1 && file.indexOf('jpeg') == -1 && file.indexOf('png') == -1 && file.indexOf('PNG') == -1) {
  			$('#imageHelper').text('사진 형식이 아닙니다.');
  		} else {
  			$('#addMemberForm').submit();
  		}
  	});

  	// enter키 눌렀을때 유효성 검사
  	$(document).keydown(function(event){
  		if(event.keyCode==13) {
  			event.preventDefault();
  			var file = $('#customFile').val();
  			console.log("file",file);
  			if($('#realId').val()=='') {
  	  			alert('아이디 중복 검사 해주세요');
  	  		} else if($('#pw').val()=='') {
  	  			$('#pwHelper').text('비밀번호를 입력해주세요.');
  	  		} else if(!spc.test($('#pw').val())) { // pw에 spc가 없다면
  	  			$('#pwHelper').text('비밀번호에 특수문자를 포함해주세요.');
  	  		} else if($('#pw').val().length <9) {
  	  			$('#pwHelper').text('비밀번호를 8자 이상 입력해주세요.');
  	  		} else if($('#pwCk').val()=='') {
  	  			$('#pwHelper').text('');
  	  			$('#pwCkHelper').text('확인 할 비밀번호를 입력하세요');
  	  		} else if($('#pw').val() != $('#pwCk').val()) {
  	  			$('#pwCkHelper').text('비밀번호와 일치하지 않습니다.');
  	  		} else if($('#name').val()=='') {
  	  			$('#pwCkHelper').text('');
  	  			$('#nameHelper').text('이름을 입력해주세요.');
  	  		} else if($('#birth').val()==''){
  	  			$('#nameHelper').text('');
  	  			$('#birthHelper').text('생년월일을 입력해주세요.');
  	  		} else if($('#email').val()=='') {
  	  			$('#realEmail').val('');
  	  			$('#birthHelper').text('');
  	  			$('#emailHelper').text('이메일을 입력해주세요.');
  	  		} else if($('#realEmail').val()=='') {
  	  			$('#realEmail').val('');
  	  			$('#birthHelper').text('');
  	  			$('#emailHelper').text('');	
  	  			alert('이메일 중복 검사 해주세요.');	
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
  	  			$('#phoneHelper').text('');
  	  			$('#addrHelper').text('');
  	  			$('#detailAddrHelper').text('상세 주소를 입력해주세요.');
  	  		} else if($('#customFile').val()=='') {
  	  			$('#detailAddrHelper').text('');
  	  			$('#imageHelper').text('사진을 등록해주세요.');
  	  		} else if(file.indexOf('jpg') == -1 && file.indexOf('jpeg') == -1 && file.indexOf('png') == -1 && file.indexOf('PNG') == -1) {
  	  			$('#imageHelper').text('사진 형식이 아닙니다.');
  	  		} else {
  	  			$('#addMemberForm').submit();
  	  		}
  		};
  	})
  	
  	// custom - file 사용위함 
  	$(".custom-file-input").on("change", function() {
	  	  var fileName = $(this).val().split("\\").pop();
	  	  $(this).siblings(".custom-file-label").addClass("selected").html(fileName);
  	});
  	
  </script>
</body>

</html>
