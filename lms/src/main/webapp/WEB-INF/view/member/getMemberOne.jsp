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
</head>
<body>
  <div class="container-scroller">
    <!-- partial:partials/_navbar.html -->
    
    <!-- partial -->
    
       <div class="container-fluid page-body-wrapper">
         <!-- partial:partials/_settings-panel.html -->
         	<jsp:include page="/inc/topbar.jsp"/>
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
                  		
                  			<c:choose>
                  			<c:when test = "${level==1}">
                        	<h3 style="font-weight: bold;">${member.loginId}님의 학생정보</h3><br>
                        	<table class="table">
	                     		<tr style="height: 15px;">
	                        		<th>아이디</th>
	                        		<td>${member.loginId}</td>
			                        <td rowspan="4" >
			                           <img src="${pageContext.request.contextPath}/file/memberPhoto/${memberFile.memberFileName}" style="border-radius: 0%; width: 130px; height: 150px; display: block; margin: 0 auto;" ><br><br>
			                           	<a href="${pageContext.request.contextPath}/loginCheck/modifyMemberFile?memberFileName=${memberFile.memberFileName}"} style="padding-left: 70px;">사진수정</a>
			                        </td>
	                    		 </tr>
	                    		 <tr style="height: 15px;">
			                        <th>비밀번호</th>
			                        <td>****
			                        	<a href="${pageContext.request.contextPath}/loginCheck/modifyPwPwCheck">비밀번호 수정</a>
			                        </td>
			                     </tr>
			                     <tr style="height: 15px;">
			                        <th>이름</th>
			                        <td>${member.studentName}</td>
			                     </tr>
			                     <tr style="height: 15px;">
			                        <th>생년월일</th>
			                        <td>${member.studentBirth}</td>
			                     </tr>
			                     <tr>
			                        <th>성별</th>
			                        <td>${member.studentGender}</td>
			                        
			                     </tr>
			                     <tr>
			                        <th>주소</th>
			                        <td>${member.address} ${member.detailAddress}</td> 
			                        <td></td>
			                     </tr>
			                     <tr>
			                        <th>이메일</th>
			                        <td>${member.studentEmail}</td>
			                        <td></td>
			                     </tr>
			                     <tr>
			                        <th>연락처</th>
			                        <td>${member.studentPhone}</td>
			                        <td></td>
			                     </tr>
			                     <tr>
			                        <th>병역유무</th>
			                        <td>${member.militaryStatus}</td>
			                        <td></td>
			                     </tr>
			                     <tr>
			                        <th>학력</th>
			                        <td>${member.graduate}</td>
			                        <td></td>
			                     </tr>
			                     <tr>
			                        <th>가입일</th>
			                        <td>${member.createDate}</td>
			                        <td></td>
			                     </tr>
		                 	 </table><br>
		               		 </c:when>
		              
		              		 <c:when test="${level==2}">
		                     <h3 style="font-weight: bold;">${member.loginId}님의 강사정보</h3><br>
		                     <table class="table">
			                     <tr style="height: 15px;">
			                     	<th>아이디</th>
			                        <td>${member.loginId}</td>
			                        <td rowspan="4" >
			                           <img src="${pageContext.request.contextPath}/file/memberPhoto/${memberFile.memberFileName}" style="border-radius: 0%; width: 130px; height: 150px; display: block; margin: 0 auto;" ><br><br>
			                           <a href="${pageContext.request.contextPath}/loginCheck/modifyMemberFile?memberFileName=${memberFile.memberFileName}"} style="padding-left: 70px;">사진수정</a>
			                        </td>
			                     </tr>
			                     <tr style="height: 15px;">
			                        <th>비밀번호</th>
			                        <td>****</td>
			                     </tr>
		                         <tr> 
		                             <th>이름</th>
		                             <td>${member.teacherName}</td>
		                         </tr>  
		                         <tr>
		                             <th>생년월일</th>
		                             <td>${member.teacherBirth}</td>
		                         </tr>
		                         <tr> 
		                             <th>성별</th>
		                             <td>${member.teacherGender}</td>
		                         </tr>
		                         <tr> 
		                             <th>주소</th>
		                             <td>${member.address} ${member.detailAddr}</td>
		                             <td></td>
		                         </tr>
		                         <tr> 
	    	                         <th>이메일</th>
		                             <td>${member.teacherEmail}</td>
		                             <td></td>
		                         </tr>
		                         <tr>  
		                             <th>연락처</th>
		                             <td>${member.teacherPhone}</td>
		                             <td></td>
		                         </tr>
		                         <tr>
		                             <th>학력</th>
		                             <td>${member.graduate}</td>
		                             <td></td>
		                         </tr>
		                         <tr>   
		                             <th>가입일</th>
		                             <td>${member.createDate}</td>
	                                 <td></td>
	                             </tr>
	     	                 </table><br>
		               		 </c:when>
		               
		               		 <c:when test="${level==3}">
		                     <h3 style="font-weight: bold;">${member.loginId}님의 매니저정보</h3><br>
		                     <table class="table">
		                          <tr style="height: 15px;">
		                          	  <th>아이디</th>
		                              <td>${member.loginId}</td>
		                        	  <td rowspan="4" >
		                           		<img src="${pageContext.request.contextPath}/file/memberPhoto/${memberFile.memberFileName}" style="border-radius: 0%; width: 130px; height: 150px; display: block; margin: 0 auto;" ><br><br>
		                           		<a href="${pageContext.request.contextPath}/loginCheck/modifyMemberFile?memberFileName=${memberFile.memberFileName}"} style="padding-left: 70px;">사진수정</a>
		                        	  </td>
		                     	  </tr>
		                     	  <tr style="height: 15px;">
			                        <th>비밀번호</th>
			                        <td>****</td>
			                     </tr>
		                          <tr> 
		                              <th>이름</th>
		                              <td>${member.managerName}</td>
		                          </tr>  
		                          <tr>
		                              <th>생년월일</th>
		                              <td>${member.managerBirth}</td>
		                          </tr>
		                          <tr> 
		                              <th>성별</th>
		                              <td>${member.managerGender}</td>
		                          </tr>
		                          <tr> 
		                              <th>주소</th>
		                              <td>${member.address} ${member.detailAddr}</td>
		                              <td></td>
		                          </tr>
		                          <tr> 
		                              <th>이메일</th>
		                              <td>${member.managerEmail}</td>
		                              <td></td>
		                          </tr>
		                          <tr>  
		                              <th>연락처</th>
		                              <td>${member.managerPhone}</td>
		                              <td></td>
		                          </tr>
		                          <tr>
		                              <th>부서</th>
		                              <td>${member.deptName}</td>
		                              <td></td>
		                          </tr>
		                          <tr>
		                              <th>직급</th>
		                              <td>${member.positionName}</td>
		                              <td></td>
		                          </tr>
		                          <tr>   
		                              <th>가입일</th>
		                              <td>${member.createDate}</td>
		                              <td></td>
		                          </tr>
		                  	 </table><br>
		               		 </c:when>
		               		 <c:when test="${level==4}">
		                     <h3 style="font-weight: bold;">${member.loginId}님의 관리자정보</h3><br>
		                     <table class="table">
		                          <tr style="height: 15px;">
		                          	  <th>아이디</th>
		                              <td>${member.loginId}</td>
		                        	  <td rowspan="4" >
		                           		<img src="${pageContext.request.contextPath}/file/memberPhoto/dd.png" style="border-radius: 0%; width: 130px; height: 150px; display: block; margin: 0 auto;" ><br><br>
		                           		<a href="${pageContext.request.contextPath}/loginCheck/modifyMemberFile?memberFileName=${memberFile.memberFileName}"} style="padding-left: 70px;">사진수정</a>
		                        	  </td>
		                     	  </tr>
		                          <tr> 
		                              <th>이름</th>
		                              <td>${member.adminName}</td>
		                          </tr>  
		                          <tr>
		                              <th>생년월일</th>
		                              <td>${member.adminBirth}</td>
		                          </tr>
		                          <tr> 
		                              <th>성별</th>
		                              <td>${member.adminGender}</td>
		                          </tr>
		                          <tr> 
		                              <th>이메일</th>
		                              <td>${member.adminEmail}</td>
		                              <td></td>
		                          </tr>
		                          <tr>  
		                              <th>연락처</th>
		                              <td>${member.adminPhone}</td>
		                              <td></td>
		                          </tr>
		                          <tr>   
		                              <th>가입일</th>
		                              <td>${member.adminCreateDate}</td>
		                              <td></td>
		                          </tr>
		                  	 </table><br>
		               		 </c:when>
		                     </c:choose> 
			                 <c:if test="${sessionLv!=4}">
			                 <div>
			                     <a class="btn btn-outline-secondary" href="${pageContext.request.contextPath}/loginCheck/modifyPwCheck">수정</a>
			                     <a class="btn btn-outline-secondary" href="${pageContext.request.contextPath}/loginCheck/removePwCheck">삭제</a>
			                 </div>
			                 </c:if>
		                 </div>
		                 </div>
		                 </div>
                   
                   </div>
                   <div class="col-12 col-xl-4">
                    <div class="justify-content-end d-flex"></div>
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

</html>
