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
	.avtimg {
		width: 100px;
		height: 100px;
	}
	.avtbox {
		display: inline-block;
		width: 102px;
		height: 117px;
	}
	#mavt, #favt, #avtfr, #idmsg, #pwmsg, #repwmsg {
		display: none;
	}
	
</style>
<script type="text/javascript">
	$(document).ready(function(){
		$('[name="gen"]').change(function(){
			var sgen = $(this).val();
			if(sgen == 'M'){
				$('#favt').css('display', 'none');
				$('#mavt').css('display', 'block');
				$('#avtfr').css('display', 'block');
			} else {
				$('#mavt').css('display', 'none');
				$('#favt').css('display', 'block');
				$('#avtfr').css('display', 'block');
			}
		});
		
		$('#rbtn').click(function() {
			document.frm.reset();
		});
		
		$('#hbtn').click(function() {
			$(location).attr('href', '/cls2/main.cls');
		});
		
		$('#idck').click(function() {
			$('#idmsg').stop().slideUp(300);
			// 할일
			// 아이디 읽고
			var sid = $('#id').val();
			
			$.ajax({
				url: '/cls2/member/idCheck.cls',
				type: 'post',
				dataType: 'json', // 'text'
				data: {
					id: sid
				},
				success: function(data) {
					var result = data.result; // dataType: 'text' 인 경우 data;
					if(result == 'OK') {
						$('#idmsg').html('*** 사용 가능한 아이디 입니다. ***');
						$('#idmsg').removeClass('w3-text-red').addClass('w3-text-blue');
						$('#idmsg').stop().slideDown(500);
						if(!sid) {
							$('#idmsg').html('### 사용 불가능한 아이디 입니다. ###');
							$('#idmsg').removeClass('w3-text-blue').addClass('w3-text-red');
							$('#idmsg').stop().slideDown(500);
						}
					} else {
						$('#idmsg').html('### 사용 불가능한 아이디 입니다. ###');
						$('#idmsg').removeClass('w3-text-blue').addClass('w3-text-red');
						$('#idmsg').stop().slideDown(500);
					}
				},
				error: function() {
					alert('### 통신 실패 ###');
				}
			});
			
		});
		
		$('#jbtn').click(function() {
			var sname = $('#name').val();
			var sid = $('#id').val();
			var spw = $('#pw').val();
			var smail = $('#mail').val();
			var stel = $('#tel').val();
			var sgen = $('[name="gen"]:checked').val();
			var savt = $('[name="ano"]:checked').val();
			
			if (!(sname && sid && spw && smail && stel && sgen && savt)) {
				alert('필수입력사항을 확인하세요!');
				return;
			}
			
			$('#frm').submit();
		});
	});
</script>
</head>
<body>
	<div class="w3-content w3-margin-top mxw700">
		<!-- 타이틀 -->
		<h1 class="w3-pink w3-center w3-padding w3-card-4">Cls 회원가입</h1>
		<form method="POST" action="/cls2/member/joinProc.cls" name="frm" id="frm"
			class="w3-col w3-margin-top w3-margin-bottom w3-padding w3-card-4">
			<div>
				<label for="name" class="w3-col s3 w3-right-align w3-margin-top clrgrey ft14 mgb10 pdt5">회원이름 : </label>
				<input type="text" name="name" id="name" class="w3-col s8 w3-margin-top mgl10 w3-input w3-border mgb10">
			</div>
			<div>
				<label for="id" class="w3-col s3 w3-right-align clrgrey ft14 mgb10 pdt5">아 이 디 : </label>
				<div class="w3-col s8 mgl10">
					<input type="text" name="id" id="id" class="w3-col w3-input w3-border mgb10" style="width: 329px;">
					<div class="w3-col s3 w3-button w3-blue w3-right" id="idck">id check</div>
					<span class="w3-col mgb10" id="idmsg"></span>
				</div>
			</div>
			<div>
				<label for="pw" class="w3-col s3 w3-right-align clrgrey ft14 mgb10 pdt5">비밀번호 : </label>
				<div class="w3-col s8 mgl10 mgb10">
					<input type="password" name="pw" id="pw" class="w3-col w3-input w3-border">
					<span class="w3-col w3-text-red" id="pwmsg"># 비밀번호는 12345 만 가능합니다.</span>
				</div>
			</div>
			<div>
				<label for="repw" class="w3-col s3 w3-right-align clrgrey ft14 mgb10 pdt5">pw check : </label>
				<div class="w3-col s8 mgl10 mgb10">
					<input type="password" name="repw" id="repw" class="w3-col w3-input w3-border">
					<span class="w3-col w3-text-red" id="repwmsg"># 비밀번호가 일치하지 않습니다.</span>
				</div>
			</div>
			<div>
				<label for="mail" class="w3-col s3 w3-right-align clrgrey ft14 mgb10 pdt5">회원메일 : </label>
				<input type="text" name="mail" id="mail" class="w3-col s8 mgl10 w3-input w3-border mgb10">
			</div>
			<div>
				<label for="tel" class="w3-col s3 w3-right-align clrgrey ft14 mgb10 pdt5">전화번호 : </label>
				<input type="text" name="tel" id="tel" class="w3-col s8 mgl10 w3-input w3-border mgb10">
			</div>
			<div>
				<label class="w3-col s3 w3-right-align clrgrey ft14 mgb10">회원성별 : </label>
				<div class="w3-col s8 mgl10 mgb10 w3-center">
					<div class="w3-half">
						<input type="radio" name="gen" id="mgen" class="w3-radio" value="M"> <label for="mgen"> 남자</label>
					</div>
					<div class="w3-half">
						<input type="radio" name="gen" id="fgen" class="w3-radio" value="F"> <label for="fgen"> 여자</label>
					</div>
				</div>
			</div>
			<div id="avtfr">
				<label class="w3-col s3 w3-right-align clrgrey ft14 mgb10 mgt10 pdt5">아 바 타 : </label>
				<div class="w3-col s8 mgl10 mgb10 w3-center">
						<div class="avtboxfr w3-center w3-margin-top" id="mavt">
					<c:forEach var="idx" begin="1" end="3">
						 	<div class="avtbox">
						 		<label for="mavt${idx}">
						 			<img src="/cls2/img/avatar/img_avatar${idx}.png" class="w3-col avtimg">
						 		</label>
						 		<input type="radio" name="ano" id="mavt${idx}" value="1${idx}">
						 	</div>
				 	</c:forEach>
						 </div>
						 <div class="avtboxfr w3-center w3-margin-top" id="favt">
					<c:forEach var="idx" begin="4" end="6">
						 	<div class="avtbox">
						 		<label for="favt${idx}">
						 			<img src="/cls2/img/avatar/img_avatar${idx}.png" class="w3-col avtimg">
						 		</label>
						 		<input type="radio" name="ano" id="favt${idx}" value="1${idx}">
						 	</div>
				 	</c:forEach>
				 		</div>
				</div>
			</div>
		</form>
			<!-- 버튼 -->
			<div class="w3-col w3-margin-top w3-card-4 w3-center">
				<div class="w3-third w3-padding w3-red w3-hover-orange" id="rbtn">reset</div>
				<div class="w3-third w3-padding w3-green w3-hover-lime" id="hbtn">home</div>
				<div class="w3-third w3-padding w3-blue w3-hover-aqua" id="jbtn">join</div>
			</div>
			
	<!-- 회원가입 처리 실패시 메세지 처리 -->
	<c:if test="${not empty MSG}">
		<div id="msgWin" class="w3-modal">
			<div class="w3-modal-content mxw600 w3-card-4">
				<header class="w3-container w3-red">
					<span class="w3-button w3-display-topright" id="closeBtn">&times;</span>
					<h2>회원가입 실패</h2>
				</header>
				<div class="w3-container w3-margin-bottom">
					<h3 class="w3-padding w3-text-red">회원가입에 실패했습니다!</h3>
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