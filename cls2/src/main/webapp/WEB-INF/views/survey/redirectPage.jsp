<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="/cls2/js/jquery-3.6.0.min.js"></script>
<script type="text/javascript">
	$(function() {
		$('#frm').submit();
	});
</script>
</head>
<body>
	<form method="POST" action="${PATH}" id="frm" name="frm">
		<input type="hidden" name="sino" value="${SINO}">
		<input type="hidden" name="title" value="${TITLE}">
	</form>
</body>
</html>