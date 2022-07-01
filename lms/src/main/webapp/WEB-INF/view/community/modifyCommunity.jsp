<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    
<!DOCTYPE html>
<html>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
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
  <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
 <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
 <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js" integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>
   <script src="https://code.jquery.com/jquery-3.5.1.min.js" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>

    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js" integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>

    <link href="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote-bs4.min.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote-bs4.min.js"></script>
  <style>
  	.bottom {margin-bottom : 30px;}
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
            <div class="col-lg-10 grid-margin stretch-card">
              <div class="card">
                <div class="card-body"><h3 class="bottom">[커뮤니티 게시글 수정]</h3>
                  <div class="table-responsive">
                  <form id="modifyForm" method="post" action="${pageContext.request.contextPath}/loginCheck/modifyCommunity" enctype="multipart/form-data">
	                   <table class="table">
		                    <colgroup>
		                    	<col width="20%">
		                    	<col width="*">
		                    </colgroup>
							<tr>
								<th>번호</th>
								<td><input type="text" name="communityNo" value="${community.communityNo}" readonly="readonly" class="form-control"></td>
							</tr>				        
							<tr>
								<th>제목</th>
								<td><input type="text" name="communityTitle" value="${community.communityTitle}" class="form-control"></td>
							</tr>				        
							<tr>
								<th>비밀번호</th>
								<td><input type="password" name="communityPw" class="form-control"></td>
							</tr>				        
							<tr>
								<th>작성자</th>
								<td><input type="text" name="loginId" value="${community.loginId}" readonly="readonly" class="form-control"></td>
							</tr>				        
							<tr>
								<th>내용</th>
								<td>
									<textarea id="summernote" name="communityContent">${community.communityContent}</textarea>
									<script>
										$('#summernote').summernote({
											  tabsize: 2,
											  height: 400
											});
										$(".note-editor button[aria-label='Picture']").hide();
										$(".note-editor button[aria-label='Video']").hide();
									</script>
								</td>
							</tr>				        
							<tr>
								<th>작성날짜</th>
								<td>${community.createDate}</td>
							</tr>
							<tr>
								<th rowspan="2" >첨부 파일</th>
								<td class="file Section">
									<button class="btn btn-inverse-primary bottom" type="button" id="modifyFileupload">파일업로드 추가</button>
									<div id="fileSection">
										<!-- 파일 업로드 input 추가 할 위치 -->
									</div>
								<!-- 파일 부분 -->
									<c:forEach var="cf" items="${communityFileList}">
										<span id="cf${cf.getCommunityFileNo()}"> ${cf.getCommunityFileName()} <input type="button" onclick="javascript:fileClick(${cf.getCommunityFileNo()},'${cf.getCommunityFileName()}')" value="삭제"><br/></span>
									</c:forEach>
								</td>
							</tr>
						</table>
					<button type="button" id="modifyCommunityBt">수정하기</button>
					</form>
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
  
  <script type="text/javascript">
  
		$('#modifyFileupload').click(function(){
			var flag = true;
			
			$('.communityFileList').each(function(){ 
				if($(this).val() == '') {
					flag = false;
				}
			});
			
			if(flag) {
				$('#fileSection').append("<div><input class='communityFileList' type='file' name='communityFileList'><div>");
			} else {
				alert('파일이 첨부되지 않은 communityFileList가 존재합니다');
			}
		});
		
		$('#modifyCommunityBt').click(function(){
			if($('#communityTitle').val() == '') {
				alert('communityTitle 입력하세요');
			} else if($('#communityContent').val() == '') {
				alert('communityContent 입력하세요');
			} else {
				var flag2 = true;
				$('.communityFileList').each(function(){ // each함수를 이용한 반복
					if($(this).val() == '') {
						flag2 = false;
					}
				});
				if(flag2) {
					$('#modifyForm').submit();
				} else {
					alert('파일이 첨부되지 않은 communityFileList가 존재합니다');
				}
			}
		});
		

	function fileClick(communityFileNo, communityFileName) {
		
		var url = "${pageContext.request.contextPath}";
		$.ajax({
			type: "GET", //요청 메소드 방식(post, get)
			url: url+"/removeCommunityfileOne", // Controller url 주소 (@GetMapping("/url") / @PostMapping("/url"))
			data : "communityFileNo="+communityFileNo+"&communityFileName="+communityFileName,
			dataType:"json", //서버가 요청 URL을 통해서 응답하는 내용의 타입 (return값)
			success : function(result){ // 응답성공시 실행할 
				//서버의 응답데이터가 클라이언트에게 도착하면 자동으로 실행되는함수(콜백)
				$("#cf"+communityFileNo).remove();
			},
			error : function(a, b, c){
				//통신 실패시 발생하는 함수(콜백)
				alert(a + b + c);
			}
		});
	 
	}

			
  
  </script>
</body>
</html>

