<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<h2>formatDate</h2>
<c:set var = "dt" value ="<%=new Date()%>"/>
dt : ${dt }<br>
dt : <fmt:formatDate value = "${dt }"/><br>
<fmt:setLocale value = "de_DE"/>


</body>
</html>