<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>getCommunityOne</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css">
</head>
<body class="container">
	<div class="container p-3 my-3 bg-success text-white">
		<h1 align="center">커뮤니티 게시글 확인하기</h1>
	</div>
	<a href="${pageContext.request.contextPath}/loginCheck/removeCommunity?communityNo=${community.communityNo}">삭제</a>
	<a href="${pageContext.request.contextPath}/loginCheck/modifyCommunity?communityNo=${community.communityNo}">수정</a>
	<br><br>
	게시글 비밀번호(${community.communityPw}) : <input type="text" name="communityPw" value="${community.communityPw}"><br>
	<br><br>
	<table class="table table-bordered">
		<tr>
			<td>No.</td>
			<td>${community.communityNo}</td>
		</tr>
		<tr>
			<td>제목</td>
			<td>${community.communityTitle}</td>
		</tr>
		<tr>
			<td>내용</td>
			<td>${community.communityContent}</td>
		</tr>
		<tr>
			<td>작성일자</td>
			<td>${community.createDate}</td>
		</tr>
		<tr>
			<td>수정일자</td>
			<td>${community.updateDate}</td>
		</tr>
	</table>

	<c:forEach var="cf" items="${communityFileList}">
		<c:choose>
			<c:when test="${cf.getCommunityFileType() eq 'image/gif'
                         || cf.getCommunityFileType() eq 'image/png'
                         || cf.getCommunityFileType() eq 'image/jpeg'
                         || cf.getCommunityFileType() eq 'image/bmp'}">
				<img src="${pageContext.request.contextPath}/file/communityFile/${cf.getCommunityFileName()}" width="720" height="500"><br>
			</c:when>
			<c:otherwise>
				<a href="${pageContext.request.contextPath}/file/communityFile/${f.getCommunityFileName()}" target="blank">${f.getCommunityFileName()}</a><br>
			</c:otherwise>
		</c:choose>
	</c:forEach>
</body>
</html>
