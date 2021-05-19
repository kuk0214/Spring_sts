<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="/cafe/css/w3.css">
<link rel="stylesheet" type="text/css" href="/cafe/css/user.css">
<script type="text/javascript" src="/cafe/js/jquery-3.6.0.min.js"></script>
<script type="text/javascript" src="/cafe/js/w3color.js"></script>
<style type="text/css"></style>
<script type="text/javascript">
	$(document).ready(function() {
		$('#hbtn').click(function() {
			$(location).attr('href', '/cafe/main.cafe');
		});
		
		$('#wbtn').click(function() {
			var mno = $(document.frm.mno).val();
			var body = $('#body').val();
			if(!(mno && body)) {
				alert('# 내용을 입력해주세요! #');
				return;
			}
			document.frm.submit();
		});
	});
</script>
</head>
<body>
	<div class="w3-content mxw650 w3-maring-top">
		<h1 class="w3-green w3-center w3-padding w3-margin-bottom">방명록 작성</h1>
		
		<form method="POST" action="/cafe/gboard/gBoardWriteProc.cafe" name="frm" id="frm"
				class="w3-col w3-round-large w3-card-4 w3-margin-bottom">
			<input type="hidden" name="mno" value="${DATA.mno}">
			<div class="w3-col box120 pdAll10 w3-border-right">
				<img src="/cafe/img/avatar/${DATA.avatar}" class="inblock avtBox100 w3-border w3-border-grey">
			</div>
			<div class="w3-rest w3-padding">
				<div class="w3-col w3-border-bottom">
					<span class="w3-text-left mgb10 ft10"><b>${SID}</b></span>
				</div>
				<div class="w3-col w3-margin-top">
					<textarea class="w3-input w3-border noresize" id="body" name="body" placeholder="남기고 싶은 글을 작성하세요!"></textarea>
				</div>
			</div>
		</form>
		<div class="w3-col w3-margin-top">
			<div class="w3-col m2 w3-button w3-green w3-card-4" id="hbtn">Home</div>
			<div class="w3-col m2 w3-button w3-blue w3-right w3-card-4" id="wbtn">Write</div>
		</div>
		
		<!-- 방명록 작성 처리 실패시 메세지 처리 -->
	<c:if test="${not empty MSG}">
		<div id="msgWin" class="w3-modal">
			<div class="w3-modal-content mxw600 w3-card-4">
				<header class="w3-container w3-red">
					<span class="w3-button w3-display-topright" id="closeBtn">&times;</span>
					<h2>방명록 작성 실패</h2>
				</header>
				<div class="w3-container w3-margin-bottom">
					<h3 class="w3-padding w3-text-red">방명록 작성에 실패했습니다!</h3>
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