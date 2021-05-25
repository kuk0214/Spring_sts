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
<style type="text/css">
	.rebtn {
		padding: 3px 10px;
	}
</style>
<script type="text/javascript">
	$(document).ready(function() {
		// 각각의 태그에 이벤트를 적용시키는 방법(<== 해당 태그를 선택해서 처리하는 방법)
		/* $('#hbtn').click(function() {
			$(location).attr('href', '/cls2/main.cls2');
		});
		
		$('#wbtn').click(function() {
			$(location).attr('href', '/cls2/gboard/gBoardWrite.cls2');
		});
		
		$('#outbtn').click(function() {
			$(location).attr('href', '/cls2/member/logout.cls2');
		});
		
		$('#lbtn').click(function() {
			$(location).attr('href', '/cls2/member/login.cls2');
		});
		
		$('#jbtn').click(function() {
			$(location).attr('href', '/cls2/member/join.cls2');
		}); */
		
		// 여러태그를 동시에 이벤트를 처리하는 방법
		$('.w150.w3-button').click(function() {
			// 이렇게 선택하면 클래스에 w3-button 이 추가되어있는 태그는 모두 선택하고
			// 선택된 태그들에 클릭 이벤트를 등록하게 된다.
			var tid = $(this).attr('id');
			var url = '';
			switch (tid) {
			case 'hbtn':
				url = '/cls2/main.cls';
				break;
			case 'wbtn': 
				url = '/cls2/reboard/reBoardWrite.cls'; // 원글 쓰기 버튼 이벤트 처리
				break;
			case 'outbtn': 
				url = '/cls2/member/logout.cls';
				break;
			case 'lbtn': 
				url = '/cls2/member/login.cls';
				break;
			case 'jbtn': 
				url = '/cls2/member/join.cls';
				break;
			}
			$(location).attr('href', url);
		});
		
		$('.rebtn').click(function() {
			// 어떤 버튼이 클릭되었는지 알아낸다.
			// 먼저 아이디 속성값 얻어오고
			var tid = $(this).attr('id');
			var pre = tid.substring(0, 1);
			var tno = tid.substring(1);
			
			if(pre == 'r') {
				// 댓글버튼 클릭한 경우
				
				// input 태그에 채울 데이터 준비한다.
				var tmp = '#t' + tno;
				var title = $(tmp).html();

				// 입력태그에 입력해준다.
				$('#upno').val(tno);
				$('#title').val(title);

				$('#frm').submit();
	
			} else if(pre == 'e') {
				$('#rno').val(tno);
			
				$('#frm').attr('action', '/cls2/reboard/reBoardEdit.cls');
				$('#frm').submit();
			
			} else if(pre == 'd') {
				$('#rno').val(tno);
				
				$('#frm').attr('action', '/cls2/reboard/reBoardDel.cls');
				$('#frm').submit();				
			}
		})
		
		// 페이지 버튼 이벤트 처리
		$('.pbtn').click(function() {
			var spage = $(this).html();
			switch (spage) {
			case 'pre':
				$('#nowPage').val('${PAGE.startPage - 1}');
				break;
			case 'next':
				$('#nowPage').val('${PAGE.endPage + 1}');
				break;
			default:
				$('#nowPage').val(spage);
			}
			$('#frm').attr('action', '/cls2/reboard/reBoardList.cls');
			$('#frm').submit();
		});
	});
</script>
</head>
<body>
<c:if test="${not empty param.msg}">
	<script type="text/javascript">
		alert('수정 작업에 실패했습니다!');
	</script>
</c:if>
	<form method="POST" action="/cls2/reboard/reBoardReply.cls" id="frm" name="frm">
		<input type="hidden" name="nowPage" id="nowPage" value="${PAGE.nowPage}">
		<input type="hidden" name="rno" id="rno">
		<input type="hidden" name="upno" id="upno">
		<input type="hidden" name="title" id="title">
	</form>
	<div class="w3-content mxw750 w3-margin-top">
		<header class="w3-col w3-card-4 mgb20">
			<h1 class="w3-green w3-center w3-padding mg0">댓글 게시판</h1>
			<nav class="w3-bar w3-pale-yellow">
				<div class="w3-col w150 w3-left w3-button w3-small w3-amber" id="hbtn">home</div>
<c:if test="${not empty SID}">
				<div class="w3-col w150 w3-left w3-button w3-small w3-lime w3-right" id="wbtn">글작성</div>
				<div class="w3-col w150 w3-left w3-button w3-small w3-light-green w3-right" id="outbtn">logout</div>
</c:if>
<c:if test="${empty SID}">
				<div class="w3-col w150 w3-left w3-button w3-small w3-deep-orange w3-right" id="lbtn">login</div>
				<div class="w3-col w150 w3-left w3-button w3-small w3-yellow w3-right" id="jbtn">join</div>
</c:if>
		</nav>
		</header>
		
<c:forEach var="data" items="${LIST}">
		<div class="w3-col" style="padding-left: ${(data.step <= 2) ? data.step * 70 : 140}px">
			<div class="w3-col w3-round-large w3-card-4 w3-margin-bottom w3-padding">
				<div class="w3-col w120 w3-center pdAll10">
					<img src="/cls2/img/avatar/${data.avatar}" class="inblock w3-circle avtBox100 border3px w3-card-2" id="a${data.bno}">
					<span class="w3-col mgt10 ft10"><b>${data.id}</b></span>
				</div>
				<div class="w3-rest w3-padding h100per">
					<div class="w3-col w3-border-bottom w3-border-grey">
						<div class="w3-col w3-border-bottom">
							<span class="mgt10 ft10"><b id="t${data.bno}">${data.title}</b></span>
							<span class="w3-right mgb10 ft10">${data.wdate}</span>
						</div>
						<div class="w3-col w3-margin-top">
							<span class="w3-col w3-padding ft12 bdminH">${data.ebody}</span>
						</div>
					</div>
					<div class="w3-col mgt10">
						<span class="w3-button w3-blue w3-small w3-left w3-round-medium rebtn" id="r${data.bno}">댓글달기</span><!-- r100001 -->
		<c:if test="${SID eq data.id}">
						<span class="w3-button w3-green w3-small w3-right w3-round-medium mgl10 rebtn" id="e${data.bno}">수정하기</span>
						<span class="w3-button w3-red w3-small w3-right w3-round-medium rebtn" id="d${data.bno}">삭제하기</span>
		</c:if>			
					</div>
				</div>
			</div>
		</div>
</c:forEach>
		<div class="w3-center w3-margin-bottom">
			<div class="w3-bar w3-border w3-round w3-margin-top">
	<c:if test="${PAGE.startPage == 1}">
			  <span class="w3-bar-item w3-grey">pre</span>
	</c:if>
	<c:if test="${PAGE.startPage != 1}">
			  <span class="w3-bar-item w3-button w3-hover-aqua pbtn">pre</span>
	</c:if>
<c:forEach var="page" begin="${PAGE.startPage}" end="${PAGE.endPage}">
	<c:if test="${PAGE.nowPage == page}">
			  <span class="w3-bar-item w3-button w3-hover-aqua w3-blue pbtn">${page}</span>
  	</c:if>
		<c:if test="${PAGE.nowPage != page}">
			  <span class="w3-bar-item w3-button w3-hover-aqua pbtn">${page}</span>
  	</c:if>
</c:forEach>
	<c:if test="${PAGE.endPage == PAGE.totalPage}">
			  <span class="w3-bar-item w3-grey">next</span>
	</c:if>
	<c:if test="${PAGE.endPage != PAGE.totalPage}">
			  <span class="w3-bar-item w3-button w3-hover-aqua pbtn">next</span>
	</c:if>
			</div>
		</div>
	</div>
</body>
</html>