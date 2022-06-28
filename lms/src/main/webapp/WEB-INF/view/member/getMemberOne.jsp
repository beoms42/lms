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
    <nav class="navbar col-lg-12 col-12 p-0 fixed-top d-flex flex-row">
      <div class="text-center navbar-brand-wrapper d-flex align-items-center justify-content-center">
        <a class="navbar-brand brand-logo mr-5" href="${pageContext.request.contextPath}/loginCheck/main">LMS-TFT</a>
        <a class="navbar-brand brand-logo-mini" href="${pageContext.request.contextPath}/index.html">LMS</a>
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
               <c:choose>
                  <c:when test ="${level==1}">
                  <li class="nav-item nav-profile dropdown">
                      <a class="nav-link dropdown-toggle" href="#" data-toggle="dropdown" id="profileDropdown1">
                            <img src="${pageContext.request.contextPath}/file/memberPhoto/${memberFile.memberFileName}" alt="profile"/>
                       </a>
                      <div class="dropdown-menu dropdown-menu-right navbar-dropdown" aria-labelledby="profileDropdown1">   
                        <a class="dropdown-item" href="${pageContext.request.contextPath}/loginCheck/getStudentOne?loginId=${memberFile.loginId}">
                           <i class="ti-settings text-primary"></i>MyPage 
                        </a>
                        <a class="dropdown-item" href="${pageContextPath.request.getContextPath}/lms/loginCheck/logout">
                        <i class="ti-power-off text-primary"></i>
                             Logout
                         </a>
                       </div>
                  </li>   
                    </c:when>
                    <c:when test ="${level==2}">
                    <li class="nav-item nav-profile dropdown">
                      <a class="nav-link dropdown-toggle" href="#" data-toggle="dropdown" id="profileDropdown">
                            <img src="${pageContext.request.contextPath}/file/memberPhoto/${fileName}" alt="profile"/>
                       </a>
                      <div class="dropdown-menu dropdown-menu-right navbar-dropdown" aria-labelledby="profileDropdown">   
                       <a class="dropdown-item" href="${pageContext.request.contextPath}/loginCheck/getTeacherOne?loginId=${memberFile.loginId}">
                          <i class="ti-settings text-primary"></i>MyPage 
                       </a>
                       <a class="dropdown-item" href="${pageContextPath.request.getContextPath}/lms/loginCheck/logout">
                        <i class="ti-power-off text-primary"></i>
                             Logout
                         </a>
                       </div>
                  </li>   
                    </c:when>
                    <c:when test ="${level==3}">
                    <li class="nav-item nav-profile dropdown">
                      <a class="nav-link dropdown-toggle" href="#" data-toggle="dropdown" id="profileDropdown">
                            <img src="${pageContext.request.contextPath}/file/memberPhoto/${fileName}" alt="profile"/>
                       </a>
                      <div class="dropdown-menu dropdown-menu-right navbar-dropdown" aria-labelledby="profileDropdown">   
                       <a class="dropdown-item" href="${pageContext.request.contextPath}/loginCheck/getmanagerOne?loginId=${memberFile.loginId}">
                          <i class="ti-settings text-primary"></i>MyPage 
                       </a>
                       <a class="dropdown-item" href="${pageContextPath.request.getContextPath}/lms/loginCheck/logout">
                        <i class="ti-power-off text-primary"></i>
                             Logout
                         </a>
                       </div>
                  </li>   
                 </c:when>
               </c:choose>  
               
               <a class="dropdown-item" href="${pageContextPath.request.getContextPath}/lms/loginCheck/logout">
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
                  <c:choose>
                  <c:when test = "${sessionLv==1}">
                      <div class="col-12 col-xl-8 mb-4 mb-xl-0">
                      <div class="card">
                        <div class="card-body">
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
                  <div>
                     <a class="btn btn-outline-secondary" href="${pageContext.request.contextPath}/loginCheck/modifyPwCheck">수정</a>
                     <a class="btn btn-outline-secondary" href="${pageContext.request.contextPath}/loginCheck/removePwCheck">삭제</a>
                  </div>
                  </div>
                  </div>
                  </div>
               </c:when>
               <c:when test="${sessionLv==2}">
                  <div class="col-12 col-xl-8 mb-4 mb-xl-0">
                      <div class="card">
                        <div class="card-body">
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
                  <div>
                     <a class="btn btn-outline-secondary" href="${pageContext.request.contextPath}/loginCheck/modifyPwCheck">수정</a>
                     <a class="btn btn-outline-secondary" href="${pageContext.request.contextPath}/loginCheck/removePwCheck">삭제</a>
                  </div>
                  </div>
                  </div>
               </c:when>
               <c:when test="${sessionLv==3}">
                  <div class="col-12 col-xl-8 mb-4 mb-xl-0">
                        <div class="card">
                        <div class="card-body">
                      <h3 style="font-weight: bold;">${member.loginId}님의 관리자정보</h3><br>
                      <table class="table">
                           <tr style="height: 15px;">
                        <th>아이디</th>
                        <td>${member.loginId}</td>
                        <td rowspan="4" >
                           <img src="${pageContext.request.contextPath}/file/memberPhoto/${memberFile.memberFileName}" style="border-radius: 0%; width: 130px; height: 150px; display: block; margin: 0 auto;" ><br><br>
                           <a href="${pageContext.request.contextPath}/loginCheck/modifyMemberFile?memberFileName=${memberFile.memberFileName}"} style="padding-left: 70px;">사진수정</a>
                        </td>
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
                  <div>
                     <a class="btn btn-outline-dark" href="${pageContext.request.contextPath}/loginCheck/modifyPwCheck">수정</a>
                     <a class="btn btn-outline-dark" href="${pageContext.request.contextPath}/loginCheck/removePwCheck">삭제</a>
                  </div>
                  </div>
                  </div>
               </c:when>
                 </c:choose> 
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