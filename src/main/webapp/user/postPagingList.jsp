<%@page import="kr.or.ddit.paging.model.PageVo"%>
<%@page import="kr.or.ddit.user.model.UserVo"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
<meta name="description" content="">
<meta name="author" content="">
<link rel="icon" href="../../favicon.ico">

<title>사용자페이징리스트</title>


<!-- css, js -->
<%@include file="/common/basicLib.jsp"%>

<style>
.userTr:hover {
	cursor: pointer;
}
</style>
<script>
	$(document).ready(function() {
		$(".userTr").on("click", function() {
			console.log("userTr click");
			var userId = $(this).find(".userId").text();
			$("#userId").val(userId);
			$("#frm").submit();
		});
	});
</script>
</head>

<body>
	<%@include file="/common/header.jsp"%>
	<div class="container-fluid">
		<div class="row">
			<%@include file="/common/left.jsp"%>
			<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
				<div class="row">
					<div class="col-sm-8 blog-main">
						<h2 class="sub-header">userPagingList.jsp</h2>
						
						<form id="frm" action="${pageContext.request.contextPath }/user"
							method="get">
							<input type="hidden" id="userId" name="userId" />
						</form>
						<div class="table-responsive">
							<table class="table table-striped">
								<tr>
									<th>게시글 번호</th>
									<th>제목</th>
									<th>작성자 아이디</th>
									<th>작성일시</th>
								</tr>
								<c:forEach items="${postList }" var="post" varStatus="status">
									<tr class="userTr" data-userid="${board.userId }">
										<td class="userId">${board.userId }</td>
										<td>${board.name }</td>
										<td>${vo.alias }</td>
										<td></td>
									</tr>
								</c:forEach>
							</table>
						</div>

						<a href="${pageContext.request.contextPath }/userForm"
							class=" btn
							btn-default pull-right">사용자 등록</a>
						<div class="text-center">
							<ul class="pagination">
								<c:choose>
									<c:when test="${pageVo.page == 1 }">
										<li class="disabled"><span>«</span></li>
									</c:when>
									<c:otherwise>
										<li><a
											href="${pageContext.request.contextPath}/userPagingList?page=${pageVo.page - 1}&pageSize=${pageVo.pageSize}">«</a>
										</li>
									</c:otherwise>
								</c:choose>
								<c:forEach begin="1" end="${paginationSize }" var="i">
									<c:choose>
										<c:when test="${pageVo.page == i }">
											<li class="active"><span>${i }</span></li>
										</c:when>
										<c:otherwise>
											<li><a
												href="${pageContext.request.contextPath}/userPagingList?page=${i }&pageSize=${pageVo.pageSize}">${i }</a>
											</li>
										</c:otherwise>
									</c:choose>
								</c:forEach>
								<c:choose>
									<c:when test="${pageVo.page == paginationSize}">
										<li class="disabled"><span>»</span></li>
									</c:when>
									<c:otherwise>
										<li><a
											href="${pageContext.request.contextPath}/userPagingList?page=${pageVo.page+1}&pageSize=${pageVo.pageSize}">»</a>
										</li>
									</c:otherwise>
								</c:choose>
							</ul>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>
