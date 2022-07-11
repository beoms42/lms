<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri= "http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>
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
   <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.8.2/css/all.min.css">
  <style>
  	.title-bottom {margin-bottom : 50px;}
  	.round {border-radius: 100%;
  			margin-right:30px;}
  	.smalltitle-bottom {margin-bottom : 40px;}
  	.bottom {margin-bottom : 20px;}
  	.left {margin-left : 20px;}
  	.top {margin-top : 30px;}
  	.right {margin-right : 30px;}
  	.font-size {font-size : 20px;}
   .boxShadow {box-shadow: 0 20px 25px -5px rgb(0 0 0 / 10%);}
  	.msgbox{
	   position: fixed;
	   border-radius: 30px;
	   top:130px;
	   left: 35%;
	   background-color: #ffffff;
	   padding-top: 100px;
	   padding-bottom: 100px;
	   padding-left: 50px;
	   padding-right: 50px;
	   width: 400px;
	   height: 300px;
	   box-shadow: 0 20px 25px -5px rgb(0 0 0 / 30%);
    }
    .modal_background{
	  position: fixed;
	  top:0; left: 0; bottom: 0; right: 0;
	  background: rgba(0, 0, 0, 0.8);
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
	            <div class="col-lg-12 grid-margin stretch-card">
	              <div class="card">
	                <div class="card-body"><h1 class="title-bottom">[커뮤니티 게시글]</h1>
	                  <div class="table-responsive">
	                  <h2 class="smalltitle-bottom">${communityMember.communityTitle}</h2>
		                  <div class="row bottom">
		                  	<div class="col-sm-1">
		                  		<img src="${pageContext.request.contextPath}/file/memberPhoto/${communityMember.memberFileName}" class="round left" width="50" height="50">	
		                  	</div>
		                  	<div class="col-sm-11 font-size">
		                  		${communityMember.loginId}
								<div>${communityMember.createDate}</div>	
								<div style="text-align: right">
									<br><a href="${pageContext.request.contextPath}/loginCheck/recommandAction?communityNo=${communityMember.communityNo}" type="button" id="recommandBtn"><i class="fa fa-solid fa-thumbs-up"></i></a>
									<br>추천수 : ${recommendCnt}
									
									<c:if test="${checkRow == 1}">
									 <div class="modal_background">
										<div class="msgbox">
							                 <h4 class="text-center">이미 추천한 게시물 입니다.</h4>
							                 <hr>
							                 <div class="text-center">
							                 	 <a href="${pageContext.request.contextPath}/loginCheck/getCommunityOne?communityNo=${communityMember.communityNo}" class="btn btn-secondary" type="button">확인</a>
							                 </div>
							                 </div>
							            </div>    	
									</c:if>
								</div>                  	
		                  	</div>
		                  </div>
		                  <div class="left">
		                  <hr>
		                  <div>${communityMember.communityContent}</div>
		                  <hr>
							<!-- 파일 부분 -->
							<c:if test="${communityFileList != null && fn:length(communityFileList) > 0}">
								<div class="bottom font-size">첨부 파일</div>
									<c:forEach var="cf" items="${communityFileList}">
										<c:choose>
											<c:when test="${cf.communityFileType eq 'image/jpeg' 
														|| cf.communityFileType eq 'image/png' 
														|| cf.communityFileType eq 'image/gif'}">
												<img src="${pageContext.request.contextPath}/file/communityFile/${cf.communityFileName}" width="100" height="100">
												<br>
												<br>
											</c:when>
											<c:otherwise>
												<a href="${pageContext.request.contextPath}/file/communityFile/${cf.communityFileName}">${cf.communityFileName}</a>
												<br>
												<br>
											</c:otherwise>
										</c:choose>
									</c:forEach>
							</c:if>
						</div>
							<c:if test="${sessionId eq communityMember.loginId}">
							<div class="float-right top bottom right">
						    <a class="btn btn-info" href="${pageContext.request.contextPath}/loginCheck/modifyCommunity?communityNo=${communityMember.communityNo}">수정</a>
						    <a class="btn btn-danger" href="${pageContext.request.contextPath}/loginCheck/removeCommunity?communityNo=${communityMember.communityNo}">삭제</a>
					    </div>
					    	</c:if>
	                  </div>
	                </div>
	              </div>
	            </div>
	        </div>
	        
	        <c:forEach var="ccl" items="${communityCommentList}" varStatus="idx">
				<form id="modifyCommentForm" method="post" action="${pageContext.request.contextPath}/loginCheck/modifyCommunityComment">
					<table class="table table-bordered table-sm">
						<tr>
							<td>작성자</td>
							<td><input type="text" name="loginId" id="commentLoginId${idx.count}" value="${ccl.loginId}" readonly="readonly" class="form-control"></td>
							<td>작성일자</td>
							<td><input type="text" name="createDate" id="commentCreateDate${idx.count}" value="${ccl.createDate}" readonly="readonly" class="form-control"></td>
							<td style="width: 20%">
							<c:if test="${sessionId eq ccl.loginId}">
								<button type="button" id="modifyBtn${idx.count}" class="btn btn-info btn-sm" onclick="fn_modify_form('${idx.count}')">수정</button>
								<button type="submit" id="modifySaveBtn${idx.count}" class="btn btn-success btn-sm" hidden="hidden">저장</button>
								<button type="button" data-toggle="modal" class="btn btn-danger btn-sm" data-target="#deleteCommunityCommentModal">삭제</button>
							</c:if>
							</td>
						</tr>
						<tr>
							<td>댓글달기</td>
							<td colspan="4"><textarea name="communityCommentContent" id="commentContent${idx.count}" rows="2" cols="135" disabled="disabled" class="form-control">${ccl.communityCommentContent}</textarea></td>
						</tr>
					</table>
					<div style="text-align: right">
					<span id="modifyCommentContentHelper" class="top"></span>
					</div>
					<input type="hidden" name="communityNo" value="${ccl.communityNo}" readonly="readonly">
					<input type="hidden" name="updateDate">
				</form>
				
			<!-- Modal start-->
	        <div class="modal fade" id="deleteCommunityCommentModal" tabindex="-1" role="dialog" aria-labelledby="deleteDogModalLabel" aria-hidden="true">
			  <div class="modal-dialog" role="document">
			    <div class="modal-content">
			      <div class="modal-header">
			        <h5 class="modal-title" id="deleteDogModalLabel">댓글 삭제</h5>
			        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
			          <span aria-hidden="true">&times;</span>
			        </button>
			      </div>
			      <div class="modal-body">
			       	댓글을 삭제하시겠습니까?
			      </div>
			<form action="${pageContext.request.contextPath}/loginCheck/removeCommunityComment">	
			      <div class="modal-footer">
					<input type="hidden" name="loginId" value="${ccl.loginId}">
					<input type="hidden" name="communityCommentContent" value="${ccl.communityCommentContent}">
					<input type="hidden" name="createDate" value="${ccl.createDate}">
					<input type="hidden" name="communityNo" value="${communityNo}">
					
					<button type="submit" class="btn btn-danger">삭제하기</button>
					<button type="button" class="btn btn-secondary" data-dismiss="modal">취소하기</button>
			      </div>
			</form>	
			    </div>
			  </div>
			</div>	
			<!-- Modal end -->
			
			</c:forEach>
			<c:if test="${commentCurrentPage > 1}">
				<a href="${pageContext.request.contextPath}/loginCheck/getCommunityOne?communityNo=${communityNo}&commentCurrentPage=${commentCurrentPage-1}">이전</a>
			</c:if>			        
			<c:if test="${commentCurrentPage < commentLastPage}">
				<a href="${pageContext.request.contextPath}/loginCheck/getCommunityOne?communityNo=${communityNo}&commentCurrentPage=${commentCurrentPage+1}">다음</a>
			</c:if>	
			
				<form id ="addCommentForm" method="post" action="${pageContext.request.contextPath}/loginCheck/addCommunityComment">
					<br>
					<table class="table table-bordered table-sm">
						<tr>
							<td>작성자</td>
							<td><input type="text" name="loginId" value="${sessionId}" readonly="readonly" class="form-control"></td>
						</tr>
						<tr>
							<td>댓글달기</td>
							<td><textarea id="addCommentContent" name="communityCommentContent" rows="2" cols="135" class="form-control"></textarea></td>
						</tr>
					</table>
					<div style="text-align: right">
					<span id="commentContentHelper" class="top"></span>
					</div>
					<input type="hidden" name="communityNo" value="${communityNo}" readonly="readonly">
					<input type="hidden" name="updateDate">
					<div style="text-align: right">
					<br><button id="addCommentBt" type="button" class="btn btn-success btn-sm">댓글입력</button>
					</div>
				</form>
			
	        <!-- content-wrapper ends -->
	        <!-- partial:partials/_footer.html -->
	        <jsp:include page="/inc/footer.jsp"/>
	        <!-- partial -->
        </div>
      <!-- main-panel ends -->
      </div>   
    <!-- page-body-wrapper ends -->
    </div>
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
  
  
  <!-- 
	JSTL [c:forEach] varStatus 관련 참고 사이트
	https://jetalog.net/20
	
	.prop() 속성값 가져오거나 추가 관련 참고 사이트
	https://www.codingfactory.net/10341
	
	java script onclick 관련 참고 사이트
	https://lightair.tistory.com/entry/onclick-%EC%9D%B4%EB%B2%A4%ED%8A%B8-%EC%82%AC%EC%9A%A9%EB%B2%95-%EB%B0%8F-%EA%B0%84%EB%8B%A8%ED%95%9C-%EC%98%88%EC%A0%9C%EC%9E%90%EB%B0%94%EC%8A%A4%ED%8A%B8%EB%A6%BD%ED%8A%B8-%EC%9D%B4%EB%B2%A4%ED%8A%B8
   -->
  
  <script type="text/javascript">
  
  function fn_modify_form(cnt){
	  $('#commentContent'+cnt).prop("disabled", false);
	  $('#modifyBtn'+cnt).prop('hidden', 'hidden');
	  $('#modifySaveBtn'+cnt).prop('hidden', false);
  }
  
  $('#checkRowChk').click(function () {
		$('.msgbox').css('display','none');
	});
  
  $('#addCommentBt').click(function () {
	  if($('#addCommentContent').val() == '') {
			$('#commentContentHelper').text('댓글을 입력하세요');
		} else {
			$('#addCommentForm').submit();
		}
	});
  
  $(document).keydown(function(event){
		if(event.keyCode==13) {
			if($('#addCommentContent').val() == '') {
				$('#commentContentHelper').text('댓글을 입력하세요');
			} else {
				$('#addCommentForm').submit();
			}
		}
	});
  
 		
  </script>
</body>
</html>

