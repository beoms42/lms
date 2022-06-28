<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
  <script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
  <style>
     .helper {
        color : #FF0000;
     }
  </style>
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
                      		<div class="card">
                        		<div class="card-body">
                        		<h3 style="font-weight: bold;">개인정보 수정하기</h3><br>
                       			<c:choose>
                     			<c:when test = "${sessionLv==1}">
                       			<form method="post" action="${pageContext.request.contextPath}/loginCheck/modifyStudent" id="modifyForm">
                      			<table class="table">
                  					<tr style="height: 15px;">
                     					<th>아이디</th>
                     					<td>${member.loginId}</td>
                     					<td rowspan="4" style="width: 70px;">
                        					<img src="${pageContext.request.contextPath}/file/memberPhoto/${memberFile.memberFileName}" style="border-radius: 0%; width: 130px; height: 150px; display: block; margin: 0 auto;" ><br><br>
                     					</td>
                  					</tr>
                  					<tr>
                     					<th>이름</th>
                     					<td>
                        					<input type="text" class="form-control-df" name="studentName" value="${member.studentName}" id="name" placeholder="이름을 입력하세요.">&nbsp;&nbsp;&nbsp;
                        					<span id="nameHelper" class="helper"></span>
                     					</td>
                  					</tr>
                  					<tr>
                     					<th>생년월일</th>
                     					<td>
                     						<input type="date" class="form-control-df" name="studentBirth" value="${member.studentBirth}">
                     						<span id="birthHelper" class="helper"></span>
                     					</td>
                  					</tr>
					                <tr>
					                    <th>성별</th>
					                    <td>
					                    	<input type="radio" name="studentGender" <c:if test="${member.studentGender eq '남'}">checked</c:if> value="남"/>남&nbsp;
					                        <input type="radio" name="studentGender" <c:if test="${member.studentGender eq '여'}">checked</c:if> value="여"/>여
					                    </td>
					                </tr>
				                   	<tr>
				                       	<th>주소</th>
				                       	<td colspan="2">
				                       	<div class="input-group mb-3">
				                        	<input type="text" class="form-control text-body" placeholder="${member.address}" id="addr">
				                        	<button type="button" class="btn btn-primary" id="searchAddr" style="border-radius: 0% 4px 4px 0%;">주소 검색</button>              	
				                       	</div>
				                       	<div id="addrHelper"></div>
				                           	<select name="address"  class="form-control-df text-body" id="searchAddrList">
				                              <!-- 주소 들어올 공간 -->
				                           	</select>
				                     	</td>    
				                     </tr>   
				                  	 <tr>
					                    <th>상세주소</th>
					                    <td colspan="2"><input type="text" class="form-control-df" value="${member.detailAddress}" name="detailAddress"></td>
					                 </tr>
					                 <tr>
					                    <th>이메일</th>
					                    <td colspan="2">
					                    	<input type="text" class="form-control-df" value="${member.studentEmail}" name="studentEmail" id="email" placeholder="이메일">&nbsp;&nbsp;&nbsp;
					                        <span id="emailHelper" class="helper"></span>
					                    </td>
					                 </tr>
					                 <tr>
					                 	<th>전화번호</th>
					                    <td colspan="2">
					                    	<input type="text" class="form-control-df" value="${member.studentPhone}" name="studentPhone" id="phone" placeholder="-를 제외해서 입력해주세요." >&nbsp;&nbsp;&nbsp;
					                        <span id="phoneHelper" class="helper"></span>
					                    </td>
					                 </tr>
					                 <tr>
					                 	<th>병역유무</th>
					                    <td colspan="2">
					                       <select name="militaryStatus" class="form-control-df">
					                       <option value="해당없음" <c:if test="${member.militaryStatus eq '해당없음'}">selected="selected"</c:if>>해당없음</option>&nbsp;
					                       <option value="군필" <c:if test="${member.militaryStatus eq '군필'}">selected="selected"</c:if>>군필</option>&nbsp;
					                       <option value="미필" <c:if test="${member.militaryStatus eq '미필'}">selected="selected"</c:if>>미필</option>&nbsp;
					                       </select>
					                    </td>
					                 </tr>
					                 <tr>
					                    <th>학력</th>
					                    <td colspan="2">
					                       <select name="graduate" class="form-control-df">
					                       <option value="고졸" <c:if test="${member.graduate eq '고졸'}">selected="selected"</c:if>>고졸</option>&nbsp;
					                       <option value="초대졸" <c:if test="${member.graduate eq '초대졸'}">selected="selected"</c:if>>초대졸</option>&nbsp;
					                       <option value="대졸" <c:if test="${member.graduate eq '대졸'}">selected="selected"</c:if>>대졸</option>&nbsp;
					                       </select> 
					                     </td>
					                 </tr>
					             </table><br>
               					 <button class="btn btn-primary"  id="modify">수정</button>
               					 </form>
               					 </c:when>
				                 <c:when test = "${sessionLv==2}">
				                 <form method="post" action="${pageContext.request.contextPath}/loginCheck/modifyTeacher" id="modifyForm">
				                 <table class="table">
				                 	<tr style="height: 15px;">
				                    	<th>아이디</th>
				                     	<td>${member.loginId}</td>
				                     	<td rowspan="4" style="width: 70px;">
				                        	<img src="${pageContext.request.contextPath}/file/memberPhoto/${memberFile.memberFileName}" style="border-radius: 0%; width: 130px; height: 150px; display: block; margin: 0 auto;" ><br><br>
				                     	</td>
				                  	</tr>
				                 	<tr >
				                     	<th>이름</th>
				                     	<td>
				                        	<input type="text" class="form-control-df" name="teacherName" value="${member.teacherName}" id="name" placeholder="이름">&nbsp;&nbsp;&nbsp;
				                        	<span id="nameHelper" class="helper"></span>
				                     	</td>
				                  	</tr>
				                  	<tr>
				                     	<th>생년월일</th>
				                     	<td><input type="date" class="form-control-df" name="teacherBirth" value="${member.teacherBirth}"></td>
				                  	</tr>
				                  	<tr>
				                     	<th>성별</th>
				                     	<td>
				                        	<input type="radio" name="teacherGender" <c:if test="${member.teacherGender eq '남'}">checked</c:if> value="남"/>남&nbsp;
				                        	<input type="radio" name="teacherGender" <c:if test="${member.teacherGender eq '여'}">checked</c:if> value="여"/>여
				                     	</td>
				                  	</tr>
				                   	<tr>
				                       	<th>주소</th>
				                       	<td colspan="2">
				                       	<div class="input-group mb-3">
				                        	<input type="text" class="form-control text-body" placeholder="${member.address}" id="addr">
				                        	<button type="button" class="btn btn-primary" id="searchAddr" style="border-radius: 0% 4px 4px 0%;">주소 검색</button>              	
				                       	</div>
				                       	<div id="addrHelper"></div>
				                           	<select name="address"  class="form-control-df text-body" id="searchAddrList">
				                              <!-- 주소 들어올 공간 -->
				                           	</select>
				                     	</td>    
				                    </tr>   
				                  	<tr>
				                    	<th>상세주소</th>
				                     	<td colspan="2"><input type="text" class="form-control-df" value="${member.detailAddr}" name="detailAddress"></td>
				                  	</tr>
				                  	<tr>
				                     	<th>이메일</th>
				                     	<td colspan="2">
				                        	<input type="text" class="form-control-df" value="${member.teacherEmail}" name="teacherEmail" id="email" placeholder="이메일">&nbsp;&nbsp;&nbsp;
				                        	<span id="emailHelper" class="helper"></span>
				                     	</td>
				                  	</tr>
				                  	<tr>
				                     	<th>전화번호</th>
				                     	<td colspan="2">
				                        	<input type="text" class="form-control-df" value="${member.teacherPhone}" name="teacherPhone" id="phone" placeholder="-를 제외해서 입력해주세요." >&nbsp;&nbsp;&nbsp;
				                        	<span id="phoneHelper" class="helper"></span>
				                     	</td>
				                  	</tr>
				                  	<tr>
				                     	<th>학력</th>
				                     	<td colspan="2">
				                        	<select name="graduate" class="form-control-df">
				                        	<option value="고졸" <c:if test="${member.graduate eq '고졸'}">selected="selected"</c:if>>고졸</option>&nbsp;
				                        	<option value="초대졸" <c:if test="${member.graduate eq '초대졸'}">selected="selected"</c:if>>초대졸</option>&nbsp;
				                        	<option value="대졸" <c:if test="${member.graduate eq '대졸'}">selected="selected"</c:if>>대졸</option>&nbsp;
				                        	</select> 
				                     	</td>
				                  	</tr>
				               		</table><br>
				               		<button class="btn btn-primary"  id="modify">수정</button>
				               	</form>
				               	</c:when>
				               	<c:when test = "${sessionLv==3}">
				               	<form method="post" action="${pageContext.request.contextPath}/loginCheck/modifyManager" id="modifyForm">
				                <table class="table table-bordered">
				                	<tr style="height: 15px;">
				                    	<th>아이디</th>
				                     	<td>${member.loginId}</td>
				                     	<td rowspan="4" style="width: 70px;">
				                        	<img src="${pageContext.request.contextPath}/file/memberPhoto/${memberFile.memberFileName}" style="border-radius: 0%; width: 140px; height: 170px; display: block; margin: 0 auto;" ><br><br>
				                     	</td>
				                  	</tr>
				                  	<tr>
				                     	<th>이름</th>
				                     	<td>
				                        	<input type="text" class="form-control-df" name="managerName" value="${member.managerName}" id="name" placeholder="이름">&nbsp;&nbsp;&nbsp;
				                        	<span id="nameHelper" class="helper"></span>
				                     	</td>
				                  	</tr>
				                  	<tr>
				                     	<th>생년월일</th>
				                     	<td><input type="date" class="form-control-df" name="managerBirth" value="${member.managerBirth}"></td>
				                  	</tr>
				                  	<tr>
				                     	<th>성별</th>
				                     	<td>
				                        	<input type="radio" class="form" name="managerGender" <c:if test="${member.managerGender eq '남'}">checked</c:if> value="남"/>남&nbsp;
				                        	<input type="radio" class="form" name="managerGender" <c:if test="${member.managerGender eq '여'}">checked</c:if> value="여"/>여
				                     	</td>
				                  	</tr>
				                   	<tr>
				                       	<th>주소</th>
				                       	<td colspan="2">
				                       	<div class="input-group mb-3">
				                        	<input type="text" class="form-control text-body" placeholder="${member.address}" id="addr">
				                        	<button type="button" class="btn btn-primary" id="searchAddr" style="border-radius: 0% 4px 4px 0%;">주소 검색</button>              	
				                       	</div>
				                       	<div id="addrHelper"></div>
				                           	<select name="address"  class="form-control-df text-body" id="searchAddrList">
				                              <!-- 주소 들어올 공간 -->
				                           	</select>
				                     	</td>   
				                     </tr>   
				                  	 <tr>
				                     	<th>상세주소</th>
				                     	<td colspan="2"><input type="text" class="form-control-df" value="${member.detailAddr}" name="detailAddress"></td>
				                  	 </tr>
				                     <tr>
				                     	<th>이메일</th>
				                     	<td colspan="2">
				                        	<input type="text" class="form-control-df" value="${member.managerEmail}" name="managerEmail" id="email" placeholder="이메일">&nbsp;&nbsp;&nbsp;
				                        	<span id="emailHelper" class="helper"></span>
				                     	</td>
				                  	 </tr>
				                  	 <tr>
				                     	<th>전화번호</th>
				                     	<td colspan="2">
				                        	<input type="text" class="form-control-df" value="${member.managerPhone}" name="managerPhone" id="phone" placeholder="-를 제외해서 입력해주세요." >&nbsp;&nbsp;&nbsp;
				                        	<span id="phoneHelper" class="helper"></span>
				                     	</td>
				                  	 </tr>
				                  	 <tr>
				                         <th>부서</th>
				                         <td colspan="2">
				                         	<select name="deptNo" class="form-control-df text-body">
				                            <c:forEach var="d" items="${deptList}">
				                            	<option value="${d.deptNo}">${d.deptName}</option>
				                            </c:forEach>
				                            </select>
				                         </td>
				                      </tr>
				                      <tr>
				                         <th>직급</th>
				                         <td colspan="2" >
				                         	<select name="positionNo" class="form-control-df text-body">
				                            <c:forEach var="p" items="${positionList}">
				                           		<option value="${p.positionNo}">${p.positionName}</option>                                
				                            </c:forEach>
				                            </select>
				                         </td>
				                      </tr>
				               		  </table>
				               		  <br>
				               		  <button class="btn btn-primary"  id="modify">수정</button>
				               		  </form>
				               		  </c:when>
				               		  </c:choose>
            					</div>
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

  <!-- plugins:js -->
  <script src="vendors/js/vendor.bundle.base.js"></script>
  <!-- endinject -->
  <!-- Plugin js for this page -->
  <script src="vendors/chart.js/Chart.min.js"></script>
  <script src="vendors/datatables.net/jquery.dataTables.js"></script>
  <script src="vendors/datatables.net-bs4/dataTables.bootstrap4.js"></script>
  <script src="js/dataTables.select.min.js"></script>

  <!-- End plugin js for this page -->
  <!-- inject:js -->
  <script src="js/off-canvas.js"></script>
  <script src="js/hoverable-collapse.js"></script>
  <script src="js/template.js"></script>
  <script src="js/settings.js"></script>
  <script src="js/todolist.js"></script>
  <!-- endinject -->
  <!-- Custom js for this page-->
  <script src="js/dashboard.js"></script>
  <script src="js/Chart.roundedBarCharts.js"></script>
  <!-- End custom js for this page-->
</body>
<script>
	$('#name').blur(function() {
		if($('#name').val()=='') {
			$('#nameHelper').text('이름을 입력해주세요.');
		} else {
			$('#nameHelper').text('');
		}
	})
	
	$('#email').blur(function() {
  		if($('#email').val()=='') {
  			$('#emailHelper').text('이메일을 입력해주세요.');
  		} else if($('#email').val().indexOf('@') == -1 || $('#email').val().indexOf('.') == -1) {
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
   $('#modify').click(function() {
      if($('#name').val().length == 0 ) {
         $('#nameHelper').text('이름을 입력해주세요');
      } else if($('#email').val().length == 0 ) {
         $('#emailHelper').text('이메일을 입력해주세요');
      } else if($('#phone').val().indexOf('-') != -1 ){
         $('#phone').val().replace(/-/g, ''); // 입력한 '-' 한자리 지움
            $('#phoneHelper').text('-를 제외해서 입력해주세요.');
      } else {
         $('modifyForm').submit();
      }
   })
  
   var url="${pageContext.request.contextPath}";
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
	
    // enter키 눌렀을때 유효성 검사
    $(document).keydown(function(event){
        if(event.keyCode==13) {
           event.preventDefault();
           if($('#name').val()=='') {
                $('#nameHelper').text('이름을 입력해주세요.');
             } else if($('#birth').val()==''){
                $('#nameHelper').text('');
                $('#birthHelper').text('생년월일을 입력해주세요.');
             } else if($('#email').val().indexOf('@') == -1 || $('#email').val().indexOf('.') == -1) {
                $('#birthHelper').text('');   
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
             } else {
                $('#modify').submit();
             }
        };
     })
           
     
</script>

</html>




   