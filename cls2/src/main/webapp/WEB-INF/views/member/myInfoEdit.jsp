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
<style type="text/css">
	label {
		font-size: 12pt;
	}
	
	.avtimg {
		width: 100px;
		height: 100px;
	}
	.avtbox {
		display: inline-block;
		width: 102px;
		height: 117px;
	}
	
</style>
<script type="text/javascript">
	$(document).ready(function() {
		/* $('[src="/cafe/img/avatar/${DATA.avatar}"]').parent().next().attr('checked', 'checked'); */
		$('#${DATA.ano}').prop('checked', 'true');
		
		var pwexp = /^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#*-_])[a-zA-Z][a-zA-Z0-9!@#*-_]{4,7}$/;
		$('#pw').keyup(function() {
			// 할일
			// 입력된 내용 읽고
			var spw = $(this).val();
			if(!spw) {
				$('#pwMsg').addClass('w3-text-red').removeClass('w3-hide').html('* 비밀번호를 입력하세요 *');
				$(this).focus();
				// 즉시 함수를 종료시킨다
				return;
			}
			
			// 정규 표현식 검사
			/*
				비밀번호의 경우 요사이 일반적으로
				영문대소문자 한글자씩,
				숫자 한개,
				특수문자 한개
				를 반드시 포함하도록 하고 있다.
				
				이런 경우는 전방탐색 또는 후방탐색으로 처리하면 편하다.
				
				전방탐색은 검사할 문자열의 맨 앞에서부터 뒤까지 검색하는 방법이고
				후방탐색은 맨끝에서 맨앞으로 진행하면서 검사하는 방법이다.
				
				사용하는 기호는
					전방탐색	: ?=
					후방탐색	: ?<=
						
				사용방법 ]
					
					전방탐색 ]
						
						/^(?=.*[찾을패턴])[첫부분에 올 패턴][이후패턴]{갯수}$/;
						
					후방탐색 ]
						
						/^[첫부분에 올 패턴][이후패턴]{갯수}(?<=[찾을패턴].*)$/;
			*/
			
			
			var result = pwexp.test(spw);
			
			if(!result) {
				// 비밀번호 형식이 맞지 않은 경우
				$('#pwMsg').addClass('w3-text-red').html('<small># 비밀번호 형식이 맞지 않습니다! #</small>').removeClass('w3-hide');
				return
			}
			$('#pwMsg').addClass('w3-hide').html('');
		});
		
		// 비밀번호 확인 이벤트
		$('#repw').keyup(function() {
			var spw = $('#pw').val();
			var srepw = $(this).val()
			if(!spw) {
				// 비밀번호를 입력하지 않은 경우이므로
				// 비밀번호 입력태그로 포커스를 이동시킨다.
				$(this).val('');
				$('#pw').focus();
				return;
			}
			
			if(spw != srepw) {
				$('#repwMsg').addClass('w3-text-red').html('<small># 비밀번호가 일치하지 않습니다! #</small>').removeClass('w3-hide');
				return;
			}
			
			// 이 행을 실행하는 경우는 비밀번호와 확인이 일치하는 경우이다.
			$('#repwMsg').addClass('w3-hide').removeClass('w3-text-red').html('');
		});
		
		$('#mail').focusout(function() {
			// 입력내용 읽고
			var smail = $(this).val();
			if(!smail) {
				$('#mailMsg').addClass('w3-text-red').html('<small>*** 메일이 입력되지 않았습니다! ***</small>');
				$(this).focus();
				return;
			}
			// 정규표현식 검사
			var exp = /^[a-zA-Z][a-zA-Z0-9#!*_]{1,14}\@[a-z]{2,15}\.[a-z]{2,3}([a-z]{2})?$/;
			
			var result = exp.test(smail);
			
			if(!result) {
				$(this).focus();
				$('#mailMsg').addClass('w3-text-red').html('<small>*** 메일 형식이 맞지 않았습니다! ***</small>');
				return;
			}
			$('#mailMsg').removeClass('w3-text-red').html('');
		});
		
		/* $('#telLbl').click(function() {
			$('#tel').prop('disabled', false);
		}); */
		
		$('#tel').focusout(function() {
			// 입력내용 읽고
			var stel = $(this).val();
			// 유효성 검사하고
			if(!stel) {
				$('#telMsg').addClass('w3-text-red').html('<small>*** 전화번호를 입력하세요! ***</small>');
				$(this).focus();
				return;
			}
			
			// 정규표현식 검사
			var exp = /^0[0-9]{1,2}\-[0-9]{3,4}\-[0-9]{4}$/;
			var result = exp.test(stel);
			if(!result) {
				// 전화번호 형식에 맞지 않은 경우...
				$(this).val(''); // 입력한 데이터 모두 지우고
				
				// 메세지 출력하고
				$('#telMsg').addClass('w3-text-red').html('<small>*** 전화번호 형식이 맞지 않습니다! 숫자-숫자-숫자 ***</small>');
				$(this).focus();
				return;
			}
			$('#telMsg').removeClass('w3-text-red').html('');
		});
		
		// Reset 버튼 이벤트 처리
		$('#rbtn').click(function() {
			document.frm.reset();
			$('.msg').removeClass('w3-text-red').addClass('w3-hide').html('');
			$('#${DATA.ano}').prop('checked', true);
		})
		
		// Home 버튼 이벤트 처리
		$('#hbtn').click(function() {
			$(location).attr('href', '/cafe/main.cafe'); 
		});
		
		// form submit 이벤트
		$('#sbtn').click(function() {
			// 비밀번호가 수정되었는지 확인
			var spw = $('#pw').val();
			var srepw = $('#repw').val();
			if(!spw) {
				// 이 경우는 비밀번호를 수정하지 않은 경우이다.
				$('#pw').prop('disabled', true);
				$('#repw').prop('disabled', true);
			}
			
			if(spw != srepw) {
				// 이 경우는 비밀번호 확인이 안된경우...
				// 따라서 확인을 다시 하도록한다.
				$('#repw').val('');
				$('#repw').focus();
				return;
			}
			
			var pwresult = pwexp.test(spw);
			if(spw && !pwresult) {
				// 비밀번호를 다시 입력하도록 한다.
				alert('* 비밀번호 형식이 맞지 않습니다! *');
				$('#pw').val('');
				$('#repw').val('');
				$('#pw').focus();
				
			} else {
				$('#repw').prop('disabled', true);
			}
			
			// 메일 확인
			var smail = $('#mail').val();
			if(!smail) {
				// 입력데이터가 없는 경우
				alert('* 메일을 입력하세요! *');
				$('#mail').focus();
				return;
			}
			
			// 정규표현식 검사
			var exp = /^[a-zA-Z][a-zA-Z0-9#!*_]{1,14}\@[a-z]{2,15}\.[a-z]{2,3}([a-z]{2})?$/;
			
			var result = exp.test(smail);
			
			if(!result) {
				$('#mail').val('');
				alert('* 메일 형식에 맞지 않습니다! *');
				$('#mail').focus();
				return;
			}
			
			if(smail == '${DATA.mail}') {
				// 메일을 수정하지 않은 경우
				// 따라서 이태그를 전송하지 않는다.
				$('#mail').prop('disabled', true);
			} 
			
			// 전화번호 확인
			var stel = $('#tel').val();
			if(!stel) {
				alert('* 전화번호를 입력하세요! *');
				$('#tel').focus();
				return;
			}
			
			var exp = /^0[0-9]{1,2}\-[0-9]{3,4}\-[0-9]{4}$/;
			var result = exp.test(stel);
			if(!result) {
				// 전화번호 형식에 맞지 않은 경우...
				$('#tel').val('');
				
				// 메세지 출력하고
				alert('* 전화번호 형식에 맞지 않습니다! *');
				$('#tel').focus();
				return;
			}
			
			if(stel == '${DATA.tel}') {
				$('#tel').prop('disabled', true);
			}
			
			// 아바타 수정 검사
			var savt = $(':radio:checked').val();
			if(!savt) {
				alert('아바타를 선택하세요!');
				return;
			}
			if(savt == '${DATA.ano}') {
				// 아바타 변경 안한경우...
				$(':radio').prop('disabled', true);
			}
			
			// 한개도 데이터를 수정하지 않은 경우는 뷰로 다시 돌려보내자.
			var result1 = spw;
			var result2 = (smail == '${DATA.mail}');
			var result3 = (stel == '${DATA.tel}');
			var result4 = (savt == '${DATA.ano}');
			
			if(!(result1 || !result2 || !result3 || !result4)) {
				// 이 경우는 수정한 데이터가 없는 경우이므로
				// 뷰로 다시 돌려보낸다.
				alert('# 수정된 데이터가 없습니다! #');
				$('#pw').prop('disabled', '');
				$('#repw').prop('disabled', '');
				$('#mail').prop('disabled', '');
				$('#tel').prop('disabled', '');
				$(':radio').prop('disabled', '');
				return;
			}
			
			// 여기를 실행하면 데이터 유효성 검사에 통과한 경우이므로
			// 폼태그를 전송한다.
			var result = confirm('수정작업을 진행할까요?');
			if(result) {
				$('#frm').submit();				
			}
		});
		
	});
</script>
</head>
<body>
	<div class="w3-content mxw650 w3-center">
		<h1 class="w3-padding w3-card-4 mgt20 w3-green">내 정보 수정</h1>
		
		<form method="POST" action="/cafe/member/myInfoEditProc.cafe" name="frm" id="frm">
			<div class="w3-col w3-margin-top w3-card-4 w3-padding w3-round-large">
				<div class="w3-col pdt10 w3-margin-top">
					<label class="w3-col m3 w3-rigth-align w3-text-grey">회원번호 : </label>
					<span id="mno" class="w3-col m9">${DATA.mno}</span>
				</div>
				<div class="w3-col pdt10 w3-margin-top">
					<label class="w3-col m3 w3-rigth-align w3-text-grey">회원이름 : </label>
					<span id="name" class="w3-col m9">${DATA.name}</span>
				</div>
				<div class="w3-col w3-margin-top">
					<label class="w3-col m3 w3-rigth-align w3-text-grey">아 이 디 : </label>
					<span id="id" class="w3-col m9">${DATA.id}</span>
				</div>
				<div class="w3-col w3-margin-top">
					<label class="w3-col m3 w3-rigth-align w3-text-grey">비밀번호 : </label>
					<input type="password" name="pw" id="pw" class="w3-col m9">
					<span id="pwMsg" class="w3-col w3-hide msg"></span>
				</div>
				<div class="w3-col w3-margin-top">
					<label class="w3-col m3 w3-rigth-align w3-text-grey">비번확인 : </label>
					<input type="password" id="repw" class="w3-col m9">
					<span id="repwMsg" class="w3-col w3-hide msg"></span>
				</div>
				<div class="w3-col w3-margin-top">
					<label id="mail1" class="w3-col m3 w3-rigth-align w3-text-grey">메일주소 : </label>
					<input type="text" name="mail" id="mail" class="w3-col m9" value="${DATA.mail}">
					<span id="mailMsg" class="w3-col msg"></span>
				</div>
				<div class="w3-col pdt10 w3-margin-top">
					<label class="w3-col m3 w3-rigth-align w3-text-grey" id="telLbl">전화번호 : </label>
					<input type="text" name="tel" id="tel" class="w3-col m9" value="${DATA.tel}">
					<span id="telMsg" class="w3-col msg"></span>
				</div>
				<div class="w3-col w3-margin-top">
					<label class="w3-col m3 w3-rigth-align w3-text-grey">회원성별 : </label>
					<span id="gen" class="w3-col m9">${DATA.gen}</span>
				</div>
				<div class="w3-col w3-margin-top" id="avtfr">
				<label class="w3-col m3 w3-rigth-align w3-text-grey">아 바 타 : </label>
				<div class="w3-col s8 mgl10 mgb10 w3-center">
					<div class="avtboxfr w3-center w3-margin-top">
					<c:forEach var="data" items="${LIST}">
						 	<div class="avtbox">
						 		<label for="${data.ano}">
						 			<img src="/cafe/img/avatar/${data.avatar}" class="w3-col avtimg">
						 		</label>
						 		<input type="radio" name="avt" id="${data.ano}" value="${data.ano}">
						 	</div>
				 	</c:forEach>
				 		</div>
				</div>
			</div>
			</div>
		</form>

		<div class="w3-col w3-margin-top w3-card-4 showFr">
			<div class="w3-third w3-padding w3-orange w3-button" id="rbtn">Reset</div>
			<div class="w3-third w3-padding w3-green w3-button" id="hbtn">Home</div>
			<div class="w3-third w3-padding w3-blue w3-hover-aqua w3-button" id="sbtn">수정하기</div>
		</div>		
	</div>
</body>
</html>