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
<script type="text/javascript" src="/cls2/js/w3color.js"></script>
<style type="text/css"></style>
<script type="text/javascript">
	$(document).ready(function() {
		$('#hbtn').click(function() {
			$(location).attr('href', '/cls2/main.cls');
		});
		
		$('#rbtn').click(function() {
			document.frm.reset();
		});
		
		$('#rebtn').click(function() {
			var mno = $(document.frm.mno).val();
			var title = $('#title').val().trim();
			var body = $('#body').val().trim();
			if(!(title && body)) {
				alert('# 제목 & 내용을 입력해주세요! #');
				return;
			}
			$('#frm').submit();
		});
	});
</script>
</head>
<body>
	<div class="w3-content mxw750 w3-maring-top">
		<h1 class="w3-green w3-center w3-padding w3-margin-bottom">댓글 작성</h1>
		
		<form method="POST" action="/cls2/reboard/reBoardReplyProc.cls" name="frm" id="frm"
				class="w3-col w3-round-large w3-card-4 w3-margin-bottom">
			<input type="hidden" name="nowPage" value="${PAGE.nowPage}">
			<input type="hidden" name="mno" value="${DATA.mno}">
			<input type="hidden" name="upno" value="${DATA.upno}">
			<div class="w3-col w3-center box120 pdAll10">
				<img src="/cls2/img/avatar/${DATA.avatar}" class="inblock w3-circle avtBox100 border3px w3-card-2">
				<span class="w3-col ft10"><b>${SID}</b></span>
			</div>
			<div class="w3-rest w3-padding">
				<div class="w3-col">
					<span class="w3-col "><label>원글제목</label></span>
					<input class="w3-input w3-text-blue w3-margin-bottom noresize" value="${DATA.title}" disabled>
				</div>
				<div class="w3-col w3-border-bottom">
					<input class="w3-input noresize" id="title" name="title" placeholder="제목을 입력하세요!">
				</div>
				<div class="w3-col w3-margin-top">
					<textarea rows="7" class="w3-input w3-border noresize" id="body" name="body" placeholder="남기고 싶은 글을 작성하세요!"></textarea>
				</div>
			</div>
		</form>
		<div class="w3-col w3-margin-top">
			<div class="w3-third w3-button w3-small w3-left w3-amber" id="hbtn">Home</div>
			<div class="w3-third w3-button w3-small w3-left w3-yellow " id="rbtn">reset</div>
			<div class="w3-third w3-button w3-small w3-left w3-lime" id="rebtn">댓글작성</div>
		</div>
	</div>
</body>
</html>