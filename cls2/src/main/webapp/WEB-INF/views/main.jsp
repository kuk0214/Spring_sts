<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>cls Main</title>
<link rel="stylesheet" type="text/css" href="/cls2/css/w3.css">
<link rel="stylesheet" type="text/css" href="/cls2/css/user.css">
<script type="text/javascript" src="/cls2/js/jquery-3.6.0.min.js"></script>
<script type="text/javascript" src="/cls2/js/w3color.js"></script>
<style type="text/css"></style>
<script type="text/javascript">
	$(document).ready(function() {
		// cls2 login 버튼 이벤트 처리
		$('.cls2MBtn').click(function() {
			var tid = $(this).attr('id');
			var url = '';
			switch(tid) {
			case 'cls2Login':
				url = 'member/login.cls';
				break;
			case 'cls2Join':
				url = 'member/join.cls';
				break;
			case 'cls2MyInfo':
				url = 'member/myInfo.cls';
				break;
			case 'cls2Logout':
				url = 'member/logout.cls';
				break;
			case 'membList':
				url = 'member/memberList.cls';
				break;
			}
			$(location).attr('href', url);
		});
		
		// cls2 gboard 버튼 이벤트 처리
		$('.cls2GBtn').click(function() {
			var tid = $(this).attr('id');
			var url = '';
			switch(tid) {
			case 'gboard':
				url = '/cls2/gboard/gBoardList.cls'
				break;
			}
			$(location).attr('href', url);
		});
		
		// cls2 reboard 버튼 이벤트 처리
		$('.cls2RBtn').click(function() {
			var tid = $(this).attr('id');
			var url = '';
			switch(tid) {
			case 'reboard':
				url = '/cls2/reboard/reBoardList.cls'
				break;
			}
			$(location).attr('href', url);
		});
		
		// cls2 board 버튼 이벤트 처리
		$('.cls2BBtn').click(function() {
			var tid = $(this).attr('id');
			var url = '';
			switch(tid) {
			case 'board':
				url = '/cls2/board/boardList.cls'
				break;
			}
			$(location).attr('href', url);
		});
	});
</script>
</head>
<body>
	<!--  데이터 전송용 form tag -->
	<form method="post" id="frm" name="frm">
	</form>

	<div class="w3-content mxw700 w3-center w3-margin-top">
		<h1 class="w3-margin-top w3-blue w3-card-4 w3-padding">Spring Project</h1>
			
		<!--  수업 예제 링크 버튼 추가 장소 -->
			<div class="w3-col w3-padding w3-border-bottom">
				<h4 class="w3-col s4 w3-text-grey">Member Request</h4>
				<div class="w3-col s8">
				<c:if test="${empty SID }">
					<div class="w3-col w3-deep-purple w3-margin-bottom w3-button cls2MBtn" id="cls2Login">
						<h4>cls2 Login</h4>
					</div>
					<div class="w3-col w3-blue w3-margin-bottom w3-button cls2MBtn" id="cls2Join">
						<h4>cls2 Join</h4>
					</div>
				</c:if>
				<c:if test="${not empty SID }">
					<div class="w3-col w3-purple w3-margin-bottom w3-button cls2MBtn" id="cls2MyInfo">
						<h4>cls2 내 정보보기</h4>
					</div>
					<div class="w3-col w3-deep-purple w3-margin-bottom w3-button cls2MBtn" id="cls2Logout">
						<h4>cls2 로그아웃</h4>
					</div>
					<div class="w3-col w3-blue w3-margin-bottom w3-button cls2MBtn" id="membList">
						<h4>회원 목록 보기</h4>
					</div>
				</c:if>
				</div>
			</div>
			
		<!--  방명록 링크 버튼 추가 장소 -->
			<div class="w3-col w3-padding w3-border-bottom">
				<h4 class="w3-col s4 w3-text-grey">방명록</h4>
				<div class="w3-col s8">
					<div class="w3-col w3-blue w3-margin-bottom w3-button cls2GBtn" id="gboard">
						<h4>방명록 리스트</h4>
					</div>
				</div>
			</div>
			
		<!--  댓글 게시판 링크 버튼 추가 장소 -->
			<div class="w3-col w3-padding w3-border-bottom">
				<h4 class="w3-col s4 w3-text-grey">댓글 게시판</h4>
				<div class="w3-col s8">
					<div class="w3-col w3-cyan w3-margin-bottom w3-button cls2RBtn" id="reboard">
						<h4>댓글 게시판 리스트</h4>
					</div>
				</div>
			</div>
			
		<!--  파일 게시판 링크 버튼 추가 장소 -->
			<div class="w3-col w3-padding w3-border-bottom">
				<h4 class="w3-col s4 w3-text-grey">파일 게시판</h4>
				<div class="w3-col s8">
					<div class="w3-col w3-green w3-margin-bottom w3-button cls2BBtn" id="board">
						<h4>파일 게시판 리스트</h4>
					</div>
				</div>
			</div>
	</div>
</body>
</html>