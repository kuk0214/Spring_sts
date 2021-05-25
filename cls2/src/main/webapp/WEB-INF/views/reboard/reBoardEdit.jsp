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
		
		$('#ebtn').click(function() {
			var title = $('#title').val();
			var body = $('#body').val();
			if(!(title && body) || title == '${DATA.title}' && body == '${DATA.body}') {
				alert('*** 수정 내용을 확인하세요! ***');
				return;
			}
			
			if(title == '${DATA.title}') {
				// 이 경우는 제목을 수정안한 경우이므로
				// 이 태그를 전송하면 안된다.
				$('#title').prop('disabled', true);
			} else {
				$('#title').prop('disabled', false);
				// 글 제목의 정규표현식 검사를 한다.
				
				var exp = /^.{1,50}$/;
				var result = exp.test(title);
				if(!result) {
					// 정규표현식에 통과하지 못한 경우
					// 따라서 표현식에 맞도록 유도한다.
					alert('# 제목은 50글자 까지만 가능합니다! #');
					return;
				}
			}
			
			if(body == '${DATA.body}') {
				$('#body').prop('disabled', true);
			} else {
				$('#body').prop('disabled', false);				
			}
			
			// 이행을 실행하는 경우는 제목과 본문중 적어도 하나는 수정되었고
			// 제목의 글자수도 50자 이내로 작성한 경우이므로 처리페이지를 부른다.
			$('#frm').submit();
		});
	});
</script>
</head>
<body>
	<div class="w3-content mxw750 w3-maring-top">
		<h1 class="w3-green w3-center w3-padding w3-margin-bottom">글 수정</h1>
		
		<form method="POST" action="/cls2/reboard/reBoardEditProc.cls" name="frm" id="frm"
				class="w3-col w3-round-large w3-card-4 w3-margin-bottom">
			<input type="hidden" name="rno" id="rno" value="${DATA.rno}">
			<div class="w3-col w3-center box120 pdAll10">
				<img src="/cls2/img/avatar/${data.avatar}" class="inblock w3-circle avtBox100 border3px w3-card-2">
				<span class="w3-col ft10"><b>${SID}</b></span>
			</div>
			<div class="w3-rest w3-padding">
		<c:if test="${DATA.upno ne 0}">
			<input type="hidden" name="upno" id="upno" value="${DATA.upno}" disabled>
				<div class="w3-col">
					<span class="w3-col "><label>원글제목</label></span>
					<input class="w3-input w3-text-blue w3-margin-bottom noresize" value="${DATA.uptitle}" disabled>
				</div>
		</c:if>
				<div class="w3-col w3-border-bottom">
					<input class="w3-input noresize" id="title" name="title" placeholder="제목을 입력하세요!" value="${DATA.title}">
				</div>
				<div class="w3-col w3-margin-top">
					<textarea rows="7" class="w3-input w3-border noresize" id="body" name="body" placeholder="남기고 싶은 글을 작성하세요!">${DATA.body}</textarea>
				</div>
			</div>
		</form>
		<div class="w3-col w3-margin-top">
			<div class="w3-third w3-button w3-small w3-left w3-amber" id="hbtn">Home</div>
			<div class="w3-third w3-button w3-small w3-left w3-yellow " id="rbtn">reset</div>
			<div class="w3-third w3-button w3-small w3-left w3-lime" id="ebtn">글수정</div>
		</div>
	</div>
</body>
</html>