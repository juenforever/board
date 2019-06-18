<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div id="div" class="col-sm-3 col-md-2 sidebar">
	<ul class="nav nav-sidebar">
		<li class="active"><a
			href="${pageContext.request.contextPath}/boardCreate?userId=${userId}">게시판생성</a></li>
		<br>
		<c:forEach items="${boardList}" var="board">
			<c:if test="${board.board_usage eq 'yes' }">
				<li class="active"><a
					href="${pageContext.request.contextPath}/postPagingList?board_id=${board.board_id}">${board.board_name}</a></li>
			</c:if>
		</c:forEach>
	</ul>
</div>


