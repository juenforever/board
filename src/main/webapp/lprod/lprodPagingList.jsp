<%@page import="kr.or.ddit.paging.model.PageVo"%>
<%@page import="kr.or.ddit.lprod.model.LprodVo"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">
<link rel="icon" href="../../favicon.ico">

<title>Jsp</title>

<%@include file="/common/basicLib.jsp"%>

<style>
.userTr:hover {
	cursor: pointer;
}
</style>
</head>
<body>
	<%@include file="/common/header.jsp"%>
	<div class="container-fluid">
		<div class="row">
			<%@include file="/common/left.jsp"%>
			<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
				<div class="row">
					<div class="col-sm-8 blog-main">
						<h2 class="sub-header">상품</h2>
						<form id="frm" action="${pageContext.request.contextPath }/lprod"
							method="get">
							<input type="hidden" id="userId" name="userId" />
						</form>

						<div class="table-responsive">
							<table class="table table-striped">
								<tr>
									<th>사용자 아이디</th>
									<th>사용자 이름</th>
									<th>사용자 별명</th>
									<th>등록일시</th>
								</tr>
								<c:forEach items="${lprodList }" var="lprod">
									<tr>
										<td>${lprod.lprod_id }</td>
										<td>${lprod.lprod_gu }</td>
										<td>${lprod.lprod_nm }</td>
										<td></td>
									</tr>
								</c:forEach>

							</table>
						</div>

						<a class="btn btn-default pull-right">사용자 등록</a>

						<div class="text-center">
							<ul class="pagination">
								<c:choose>
									<c:when test="${pageVo.page == '1' }">
										<li class="disabled"><a href="#" aria-label="Previous"><span
												aria-hidden="true">«</span></a></li>
									</c:when>
									<c:otherwise>
										<li><a
											href="${pageContext.request.contextPath}/lprodPagingList?page=${pageVo.page-1 }&pageSize=${pageVo.pageSize }"
											aria-label="Previous"><span aria-hidden="true">«</span></a></li>
									</c:otherwise>
								</c:choose>

								<c:forEach var="i" begin="1" end="${paginationSize}">
									<c:choose>
										<c:when test="${pageVo.page == i}">
											<li class="active"><span>${i}</span></li>
										</c:when>
										<c:otherwise>
											<li><a
												href="${pageContext.request.contextPath}/lprodPagingList?page=${i}&pageSize=${pageVo.pageSize}">${i}</a></li>
										</c:otherwise>
									</c:choose>
								</c:forEach>
								<c:choose>
									<c:when test="${pageVo.page == paginationSize }">
										<li class="disabled"><a href="#"><span
												aria-hidden="true">»</span></a></li>
									</c:when>
									<c:otherwise>
										<li><a
											href="${pageContext.request.contextPath}/lprodPagingList?page=${pageVo.page+1}&pageSize=${pageVo.pageSize}"><span
												aria-hidden="true">»</span></a></li>
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
