<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>공지사항 등록</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css" integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp" crossorigin="anonymous">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<script type="text/javascript">
$(document).ready(function(){ // html페이지를 다 로드시키고 매개변수함수를 실행
	$('#addFileupload').click(function(){
		let flag = true;
		// 추가된 communityFileList안에 파일이 첨부되지 않았다면 새로운 communityFileList 추가 X
		
		// jquery api 사용
		$('.communityFileList').each(function(){ // each함수를 이용한 반복
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
	
	$('#addCommunity').click(function(){
		if($('#communityTitle').val() == '') {
			$('#communityTitleHelper').text('제목을 입력하세요');
		} else if($('#communityPw').val() == '') {
			$('#communityTitleHelper').text('');
			$('#communityPwHelper').text('비밀번호를 입력하세요');
		} else if($('#communityContent').val() == ''){
			$('#communityPwHelper').text('');
			$('#communityContentHelper').text('내용을 입력하세요');
		} else {
			let flag2 = true;
			$('.communityFileList').each(function(){ // each함수를 이용한 반복
				if($(this).val() == '') {
					flag2 = false;
				}
			});
			if(flag2) {
				$('#addCommunityForm').submit();
			} else {
				alert('파일이 첨부되지 않은 communityFileList가 존재합니다');
			}
		}
	});
	
});	
</script>
</head>
<body>
   <div class="container">
      <h1>입력</h1>
      <form id="addCommunityForm" method="post" action="${pageContext.request.contextPath}/loginCheck/addCommunity" enctype="multipart/form-data">
         
         <input type="hidden" name="communityNo" value="${community.communityNo}">
         
         <table class="table table-striped">
            <tr>
               <td>제목</td>
               <td><input type="text" name="communityTitle" id="communityTitle"> <span id="communityTitleHelper"></span></td>
               
            </tr>
            <tr>
               <td>비밀번호</td>
               <td><input type="password" name="communityPw" id="communityPw"><span id="communityPwHelper"></span></td>
                
            </tr>
            <tr>
               <td>작성자</td>
                 <td><input type="text" name="loginId" id="loginId" value="${sessionId}" readonly="readonly"></td>
            </tr>
            <tr>
               <td>내용</td>
               <td><textarea row="5" cols="50" name="communityContent" id="communityContent"></textarea><span id="communityContentHelper"></span></td>
               
            </tr>
            <tr>
               <td><button type="button" id="addFileupload">파일 업로드 추가</button></td>
               <td id="fileSection">
                  <!-- 파일 업로드 input 태그가 추가될 영역 -->
               </td>
            </tr>
            <tr>
               <td colspan="2">
                  <button type="button" id="addCommunity">입력</button>
               </td>
            </tr>
         </table>
      </form>
   </div>
</body>
</html>