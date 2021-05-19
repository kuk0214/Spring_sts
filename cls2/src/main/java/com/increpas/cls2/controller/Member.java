package com.increpas.cls2.controller;

import java.io.IOException;

import javax.servlet.http.*;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.*;
import org.springframework.web.servlet.view.RedirectView;

import com.increpas.cls2.vo.MemberVO;


/**
 * 이 클래스는 스프링 수업의 회원관련된 요청 처리를 할 클래스
 * @author	조경국
 * @since	2021.05.18
 * @version v.1.0
 *
 */

@Controller // 이 클래스가 요청을 처리할 컨트롤러의 역할을 할 컨트롤러 클래스로 만들어주는 어노테이션
@RequestMapping("/member") 
// 이 클래스의 함수를 요청할 때 공통적으로 회원관련된 요청을 할 것이고 
// 그 때마다 앞에 붙여줄 경로는 컨트롤러에서 공통적으로 처리하기로 한다.
public class Member {
	
	@RequestMapping("/login.cls")
	public ModelAndView getLogin(HttpSession session, ModelAndView mv, RedirectView rv) {
		if(isLogin(session)) {
			// 이 경우는 이미 로그인이 되어있는 경우이고
			// 로그인 페이지를 부르면 안되는 경우이다.
			// 따라서 메인페이지로 돌려보낸다.
			
			/*
				참고 ]
					스프링에서 redirect 방식으로 뷰를 처리하는 방법
						
						1.
						RedirectView 객체에 
						rv.seturl("요청주소")
						
						2.
						ModelAndView 에
						mv.setview(rv);
						
			 */
			rv.setUrl("/cls2/");
			mv.setView(rv);
		} else {
			String view = "member/login";
			mv.setViewName(view);
		}
		
		return mv;
	}
	
	@RequestMapping("/loginProc.cls")
	public ModelAndView loginProc(/*String id, String pw*/ MemberVO mVO, ModelAndView mv, HttpSession session, RedirectView rv) {
		String view = "/cls2/";
		if(isLogin(session)) {
		} else {
			// 이 부분에서 로그인 처리...
		}
		// 파라미터 데이터 출력
		/*
		 * System.out.println("*********** parameter id : " + id);
		 * System.out.println("*********** parameter pw : " + pw);
		 */
		System.out.println("*********** parameter id : " + mVO.getId());
		System.out.println("*********** parameter pw : " + mVO.getPw());
		System.out.println("*********** parameter ano : " + mVO.getAno());
		System.out.println(mVO);
		rv.setUrl(view);
		
		mv.setView(rv);
		return mv;
	}
	
	public boolean isLogin(HttpSession session) {
		String sid = (String) session.getAttribute("SID");
		
		return (sid == null) ? false : true;
	}
	
	@RequestMapping("/join.cls")
	public ModelAndView getjoin(ModelAndView mv, HttpSession session) {
		if(isLogin(session)) {
			mv.setViewName("/cls2/");
		}
		
		return mv;
	}
}
