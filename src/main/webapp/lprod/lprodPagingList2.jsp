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
<!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
<meta name="description" content="">
<meta name="author" content="">
<link rel="icon" href="../../favicon.ico">

<title>Jsp</title>

<%@include file="/common/basicLib.jsp"%>
<body>
	<%@include file="/common/header.jsp"%>
	<nav class="navbar navbar-inverse navbar-fixed-top">
		<div class="container-fluid">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle collapsed"
					data-toggle="collapse" data-target="#navbar" aria-expanded="false"
					aria-controls="navbar">
					<span class="sr-only">Toggle navigation</span> <span
						class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
				<a class="navbar-brand" href="#">JSP/SPRING</a>
			</div>
			<div id="navbar" class="navbar-collapse collapse">
				<ul class="nav navbar-nav navbar-right">
					<li><a href="#">Dashboard</a></li>
					<li><a href="#">Settings</a></li>
					<li><a href="#">Profile</a></li>
					<li><a href="#">Help</a></li>
				</ul>
				<form class="navbar-form navbar-right">
					<input type="text" class="form-control" placeholder="Search...">
				</form>
			</div>
		</div>
	</nav>
	<div class="container-fluid">
		<div class="row">
			<!-- 		left -->
			<%@include file="/common/left.jsp"%>

			<div class="col-sm-3 col-md-2 sidebar">
				<ul class="nav nav-sidebar">
					<li class="active"><a href="#">Main <span class="sr-only">(current)</span></a></li>
					<li class="active"><a
						href="${pageContext.request.contextPath}/lprodPagingList">사용자리스트</a></li>
				</ul>
			</div>
			<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
				<div class="row">
					<div class="col-sm-8 blog-main">
						<h2 class="sub-header">사용자</h2>
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
								<%
									PageVo pageVo = (PageVo) request.getAttribute("pageVo");
									pageVo = (PageVo) request.getAttribute("pageVo");
									int paginationSize = (Integer) request
											.getAttribute("paginationSize");
									if (pageVo.getPage() == 1) {
								%>

								<li class="disabled"><a href="#" aria-label="Previous"><span
										aria-hidden="true">«</span></a></li>
								<%
									} else {
								%>
								<li><a
									href="${pageContext.request.contextPath}/lprodPagingList?page=
									${pageVo.page-1 }
									&pageSize=
									${pageVo.pageSize}"
									aria-label="Previous"><span aria-hidden="true">«</span></a></li>

								<%
									}
								%>

								<%
									//내가 현재 몇번째 페이지에 있는가?
									for (int i = 1; i <= paginationSize; i++) {
								%>

								<%
									if (pageVo.getPage() == i) {
								%>
								<li class="active"><span><%=i%></span></li>
								<%
									} else {
								%>
								<li><a
									href="${pageContext.request.contextPath}/lprodPagingList?page=<%=i%>
									&pageSize=${pageVo.pageSize }">
										<%=i%></a></li>
								<%
									}
								%>
								<%
									}
								%>

								<c:choose>
									<c:when test="${pageVo.page == paginationSize }"></c:when>
									<li class="disabled"><a href="#"><span
											aria-hidden="true">»</span></a></li>
									<c:otherwise>
										<li><a
											href="${pageContext.request.contextPath}/lprodPagingList?page=
									${pageVo.page+1 }&pageSize=${pageVo.pageSize }"><span
												aria-hidden="true">»</span> </a></li>
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
