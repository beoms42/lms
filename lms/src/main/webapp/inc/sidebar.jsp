<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
<link rel="stylesheet" href="vendors/feather/feather.css">
  <link rel="stylesheet" href="vendors/ti-icons/css/themify-icons.css">
  <link rel="stylesheet" href="vendors/css/vendor.bundle.base.css">
  <!-- endinject -->
  <!-- Plugin css for this page -->
  <link rel="stylesheet" href="vendors/datatables.net-bs4/dataTables.bootstrap4.css">
  <link rel="stylesheet" href="vendors/ti-icons/css/themify-icons.css">
  <link rel="stylesheet" type="text/css" href="js/select.dataTables.min.css">
  <!-- End plugin css for this page -->
  <!-- inject:css -->
  <link rel="stylesheet" href="css/vertical-layout-light/style.css">
  <!-- endinject -->
  <link rel="shortcut icon" href="images/favicon.png" />
</head>
<body>
   <ul class="nav">
          <li class="nav-item">
            <a class="nav-link" href="${pageContext.request.contextPath}/loginCheck/main">
              <i class="icon-grid menu-icon"></i>
              <span class="menu-title">HOME</span>
            </a>
          </li>
          <li class="nav-item">
            <a class="nav-link" data-toggle="collapse" href="#ui-basic" aria-expanded="false" aria-controls="ui-basic">
              <i class="icon-layout menu-icon"></i>
              <span class="menu-title">강의</span>
              <i class="menu-arrow"></i>
            </a>
               <div class="collapse" id="ui-basic">
                 <ul class="nav flex-column sub-menu">
                   <li class="nav-item"> <a class="nav-link" href="${pageContext.request.contextPath}/loginCheck/getSheduleListByMonth">내 강의</a></li>
                   <li class="nav-item"> <a class="nav-link" href="${pageContext.request.contextPath}/loginCheck/getAssignmentExam">과제</a></li>
                   <li class="nav-item"> <a class="nav-link" href="pages/ui-features/typography.html">성적</a></li>
                   <c:if test="${sessionLv <= 2}">
                      <li class="nav-item"> <a class="nav-link" href="${pageContext.request.contextPath}/loginCheck/getLectureReferenceList">자료실</a></li>
                   </c:if>
                   
                   <c:if test="${sessionLv <= 2}">
                      <li class="nav-item"> <a class="nav-link" href="${pageContext.request.contextPath}/loginCheck/getBooks">학생 - 교재수령</a></li>
                      <li class="nav-item"> <a class="nav-link" href="${pageContext.request.contextPath}/loginCheck/getEndOfLectureList">학생 - 종강한 강의</a></li>
                   </c:if>
                   <c:if test="${sessionLv == 2}">
                      <li class="nav-item"> <a class="nav-link" href="${pageContext.request.contextPath}/loginCheck/getAttendanceList">출결 관리</a></li>
                   </c:if>
                   <c:if test="${sessionLv >= 3}">
                      <li class="nav-item"> <a class="nav-link" href="${pageContext.request.contextPath}/loginCheck/addLecture">관리자 - 강의개설</a></li>
                  <li class="nav-item"> <a class="nav-link" href="${pageContext.request.contextPath}/loginCheck/getSubjectList">관리자 - 과목관리</a></li>
                      <li class="nav-item"> <a class="nav-link" href="${pageContext.request.contextPath}/loginCheck/manageLecture">관리자 - 강의관리</a></li>
                   </c:if>
                   <c:if test="${sessionLv == 4}">
                      <li class="nav-item"> <a class="nav-link" href="${pageContext.request.contextPath}/loginCheck/acceptLecture">관리자 - 강의승인</a></li>
                   </c:if>
                   
                   <c:if test="${sessionLv == 4}">
                     <li class="nav-item"> <a class="nav-link" href="${pageContext.request.contextPath}/loginCheck/subjectTextbookInsert">관리자 - 교재과목</a></li>
                   </c:if>
                   
                 </ul>
               </div>
           </li>
          <li class="nav-item">
            <a class="nav-link" data-toggle="collapse" href="#form-elements" aria-expanded="false" aria-controls="form-elements">
              <i class="icon-columns menu-icon"></i>
              <span class="menu-title">공지사항</span>
              <i class="menu-arrow"></i>
            </a>
            <div class="collapse" id="form-elements">
              <ul class="nav flex-column sub-menu">
                <li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/loginCheck/getNoticeListByPage">게시글</a></li>
              </ul>
            </div>
          </li>
          <li class="nav-item">
            <a class="nav-link" data-toggle="collapse" href="#charts" aria-expanded="false" aria-controls="charts">
              <i class="icon-bar-graph menu-icon"></i>
              <span class="menu-title">커뮤니티</span>
              <i class="menu-arrow"></i>
            </a>
            <div class="collapse" id="charts">
              <ul class="nav flex-column sub-menu">
                <li class="nav-item"> <a class="nav-link" href="${pageContext.request.contextPath}/loginCheck/getCommunityListByPage">게시글</a></li>
              </ul>
            </div>
          </li>
          <li class="nav-item">
            <a class="nav-link" data-toggle="collapse" href="#tables" aria-expanded="false" aria-controls="tables">
              <i class="icon-grid-2 menu-icon"></i>
              <span class="menu-title">문의사항</span>
              <i class="menu-arrow"></i>
            </a>
            <div class="collapse" id="tables">
              <ul class="nav flex-column sub-menu">
                <li class="nav-item"> <a class="nav-link" href="${pageContext.request.contextPath}/loginCheck/getQnaListByPage">문의하기</a></li>
              </ul>
            </div>
          </li>
          
          <li class="nav-item">
            <a class="nav-link" data-toggle="collapse" href="#icons" aria-expanded="false" aria-controls="icons">
              <i class="icon-contract menu-icon"></i>
              <span class="menu-title">통계</span>
              <i class="menu-arrow"></i>
            </a>
            <div class="collapse" id="icons">
              <ul class="nav flex-column sub-menu">
                <li class="nav-item"> <a class="nav-link" href="${pageContext.request.contextPath}/loginCheck/stats">통계</a></li>
              </ul>
            </div>
          </li>
          <c:if test="${sessionLv == 4 or sessionLv == 3}">
             <li class="nav-item">
               <a class="nav-link" data-toggle="collapse" href="#mana" aria-expanded="false" aria-controls="icons">
                 <i class="icon-contract menu-icon"></i>
                 <span class="menu-title">관리</span>
                 <i class="menu-arrow"></i>
               </a>
               <div class="collapse" id="mana">
                 <ul class="nav flex-column sub-menu">
                    <c:if test="${sessionLv == 4}">
                      <li class="nav-item"> <a class="nav-link" href="${pageContext.request.contextPath}/loginCheck/getMemberList?msg=student">학생 리스트</a></li>
                      <li class="nav-item"> <a class="nav-link" href="${pageContext.request.contextPath}/loginCheck/getMemberList?msg=teacher">강사 리스트</a></li>
                      <li class="nav-item"> <a class="nav-link" href="${pageContext.request.contextPath}/loginCheck/getMemberList?msg=manager">매니저 리스트</a></li>
                      <li class="nav-item"> <a class="nav-link" href="${pageContext.request.contextPath}/loginCheck/acceptAddMember">회원가입 승인</a></li>
                   </c:if>
                   <c:if test="${sessionLv == 3}">
                      <li class="nav-item"> <a class="nav-link" href="${pageContext.request.contextPath}/loginCheck/getEmploymentList">취업관리</a></li>
                   </c:if>
                 </ul>
               </div>
             </li>
          </c:if>
        </ul>
</body>
</html>