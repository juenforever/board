]<%@page import="kr.or.ddit.user.model.UserVo"%>
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
<!-- The above 3 meta tags *must* come first in the he]ad; any other head content must come *after* these tags -->
<meta name="description" content="">
<meta name="author" content="">
<link rel="icon" href="../../favicon.ico">

<title>userList.jsp</title>

<!-- css, js -->
<%@include file="/common/basicLib.jsp"%>

<script>
	$(document).ready(function() {
		$("#boardCreateBtn").on("click", function() {
			$("#frm").submit();
		});
		$("#frm2 button").on("click", function() {
			var user_id = $(this).find(".user_id").val();
			$("#board_usage").val(user_id);
			$("#frm2").submit();
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
						<h2 class="sub-header">boardCreate.jsp</h2>
						<div class="table-responsive">
							<ul>
								<li class="active">
									<form id="frm" method="post"
										action="${pageContext.request.contextPath }/boardCreate"
										enctype="multipart/form-data">
										<input name="board_name" type="text"> <select
											name="board_usage">
											<option value="yes">사용</option>
											<option value="no">미사용</option>
										</select>
										<button id="boardCreateBtn" type="submit"
											class="btn btn-default">생성</button>
									</form>
								</li>
								<c:forEach items="${boardList}" var="board">
									<form id="frm2" method="post"
										action="${pageContext.request.contextPath }/boardModify"
										enctype="multipart/form-data">
										<li class="active">${board.board_usage }${board.board_id }
											<label for="boardName" class="col-sm-5 control-label">
												게시판 이름 : ${board.board_name }</label> 
												<select name="board_usage" value="${board.board_usage}">
												<c:choose>
													<c:when test="${board.board_usage eq 'yes' }">
														<option value="yes" selected>사용</option>
														<option value="no">미사용</option>
													</c:when>
													<c:otherwise>
														<option value="yes">사용</option>
														<option value="no" selected>미사용</option>
													</c:otherwise>
												</c:choose>
										</select> <input type="hidden" name="board_id"
											value="${board.board_id }">
											<button id="boardModifyBtn" type="submit"
												class="btn btn-default">수정</button>
										</li>
									</form>
								</c:forEach>
							</ul>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>
