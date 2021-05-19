<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="/cls2/css/w3.css">
<link rel="stylesheet" type="text/css" href="/cls2/css/user.css">
<script type="text/javascript" src="/cls2/js/jquery-3.6.0.min.js"></script>
<style type="text/css">

</style>
<script type="text/javascript">
	$(document).ready(function() {
		// btn1 click event
		$('#hbtn').click(function() {
			$(location).attr('href', '/cls2/main.cls');
		});
		
		// login 버튼 이벤트 처리
		$('#lbtn').click(function() {
			var sid = $(document.frm.id).val();
			var spw = $(document.frm.pw).val();
			
			if(!(sid && spw)) {
				alert('입력사항을 확인하세요!');
				return;
			} 

			$('#frm').submit();
		});
	});
</script>
</head>
<body>
	<div class="w3-content mxw650 w3-center">
		<h1 class="w3-blue w3-padding w3-card-4 w3-round-large">Login</h1>
		
		<form method="POST" action="/cls2/member/loginProc.cls" name="frm" id="frm"
				class="showFr">
			<input type="hidden" name="ano" value="1">
			<div class="w3-col w3-margin-top w3-card-4 w3-padding w3-round-large">
				<div class="w3-col w3-margin-top">
					<label for="id" class="w3-col m3 w3-right-align w3-text-grey">아이디 : </label>
					<input type="text" name="id" id="id" class="w3-col m8 mgl20">
				</div>
				<div class="w3-col pdt10 w3-margin-bottom">
					<label for="pw" class="w3-col m3 w3-right-align w3-text-grey">비밀번호 : </label>
					<input type="password" name="pw" id="pw" class="w3-col m8 mgl20">
				</div>
			</div>
		</form>
		
		<div class="w3-col w3-margin-top w3-card-4 showFr">
			<div class="w3-half w3-padding w3-red w3-hover-orange " id="hbtn">home</div>
			<div class="w3-half w3-padding w3-green w3-hover-lime " id="lbtn">login</div>
		</div>
		
	<!-- 로그인 처리 실패시 메세지 처리 -->
<%-- <c:if test="${not empty param.MSG}"> --%>
	<c:if test="${not empty MSG}">
		<div id="msgWin" class="w3-modal">
			<div class="w3-modal-content mxw600 w3-card-4">
				<header class="w3-container w3-red">
					<span class="w3-button w3-display-topright" id="closeBtn">&times;</span>
					<h2>로그인 실패</h2>
				</header>
				<div class="w3-container w3-margin-bottom">
					<h3 class="w3-padding w3-text-red">로그인에 실패했습니다!</h3>
				</div>
			</div>
		</div>
		<script type="text/javascript">
			$(function() {
				$('#msgWin').css('display', 'block');
				
				$('#closeBtn').click(function() {
					$('#msgWin').css('display', 'none');
				});
			});
		</script>
	</c:if>
		
	</div>
</body>
</html>