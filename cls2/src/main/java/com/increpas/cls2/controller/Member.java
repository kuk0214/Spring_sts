package com.increpas.cls2.controller;

import java.util.*;
import java.io.IOException;

import javax.servlet.http.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.*;
import org.springframework.web.servlet.view.RedirectView;

import com.increpas.cls2.dao.MemberDao;
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
	@Autowired
	MemberDao mDao;
	
	private static final Logger log2 = LoggerFactory.getLogger(Member.class);
	
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
	
	// 로그인 처리
	@RequestMapping("/loginProc.cls")
	public ModelAndView loginProc(MemberVO mVO, ModelAndView mv, HttpSession session, RedirectView rv) {
		rv.setUrl("/cls2/");
		if(isLogin(session)) {
		} else {
			int cnt = mDao.loginProc(mVO);
			if(cnt == 1) {
				session.setAttribute("SID", mVO.getId());
			} else {
				rv.setUrl("/cls2/member/login.cls");
			}
			
		}
		mv.setView(rv);
		log2.info("*** " + mVO.getId() + " ] 님 로그인 ***");
		return mv;
	}
	
	// 로그아웃 처리
	@RequestMapping("/logout.cls")
	public ModelAndView logout(HttpSession session, ModelAndView mv, RedirectView rv) {
		String sid = (String) session.getAttribute("SID");
		log2.info("*** " + sid + " ] 님 로그아웃 ***");
		session.removeAttribute("SID");
		rv.setUrl("/cls2/");
		mv.setView(rv);
		return mv;
	}
	
	// 회원가입 아이디체크 요청 처리
	@RequestMapping(value="/idCheck.cls", method=RequestMethod.POST, params="id")
	@ResponseBody
	public HashMap idCheck(String id) {
		int cnt = mDao.getIdCnt(id);
		/*
		JSP 프로젝트에서는 외부 API를 사용해서 간단하게 비동기 통신의 결과문서를
		만들어 줄수도 있었지만 우리의 경우는
		직접 응답 문서를 만들어주는 코드를 작성해서 응답했었다.
			
			예 ]
			
				result 라는 키값을 갖는 JSON 데이터를 만들경우
				pw.println("{");
				pw.println("\"result\": \"" + 결과값변수 + "\"");
				pw.println("}");
			
			의 형태로 작업을 했었는데
			이 작업이 상당히 불편하다.
			
			자바의 객체를 비동기 통신 응답문서(JSON 데이터)로 만들어주는 외부 API가 있는데
			gson, jackson 이 있다.
			
			두가지 모두 자바 객체를 응답 문서로 만들어주는 역할을 하고 있다.
			
			스프링에서는
			jackson-core 에서 이 응답문서를 만들어주는 기능을 편하게 제공해주고 있고
			사용하는 방법은
				1. 처리함수에 @ResonseBody 어노테이션을 붙여서 처리
				2. 변환값에 사용할 변수 앞에 @ResponseBody 라고 붙여주는 방법
				
				예 ]
					1.
					@ResponseBody
					public String getData() {
						String sid = "euns";
						return sid;
					}
					
					==> 응답 문서 내용
							euns
					
					2.
					@ResponseBody
					public HashMap getData() {
						String sid = "euns";
						HashMap map = new HashMap();
						map.put("id", sid);
						return map;
					}
					
					==> 응답 문서 내용
							{
								"id": "euns"
							}
						
		 */
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("result", "NO");
		map.put("id", id);
		if(cnt != 1) {
			map.put("result", "OK");
		}
		
		return map;
	}
	
	public boolean isLogin(HttpSession session) {
		String sid = (String) session.getAttribute("SID");
		
		return (sid == null) ? false : true;
	}
	
	// 회원가입 폼보기 요청 처리함수
	@RequestMapping("/join.cls")
	public ModelAndView getJoin(ModelAndView mv, HttpSession session, RedirectView rv) {
		String view = "member/join";
		if(isLogin(session)) {
			rv.setUrl("/cls2/");
			mv.setView(rv);
		} else {
			mv.setViewName(view);			
		}
		return mv;
	}
	
	// 회원리스트 보기 요청 처리함수
	@RequestMapping("/memberList.cls")
	public ModelAndView membList(ModelAndView mv) {
		// 데이터베이스에서 리스트 조회하고
		List list = mDao.membList();
		
		// 리스트 뷰에 심고
		mv.addObject("LIST", list);
		/*
			spring에서 뷰에 데이터를 전달 하는 방법
				adObject("키값", 데이터);
				==>
					req.setAttribute("키값", 데이터);
					와 동일한 역할을 하는 함수
		 */
		mv.setViewName("member/memberList");
		return mv;
	}
	
	// 회원정보조회 요청 처리함수
	@RequestMapping(value="/memberInfo.cls", params="mno", method=RequestMethod.POST)
	public ModelAndView membInfo(ModelAndView mv, int mno) {
		MemberVO mVO = mDao.membInfo(mno);
		mv.addObject("DATA", mVO);
		mv.setViewName("member/memberInfo");
		return mv;
	}
	
	// 내정보조회 요청 처리함수
	@RequestMapping("/myInfo.cls")
	public ModelAndView myInfo(ModelAndView mv, HttpSession session, RedirectView rv) {
		if(isLogin(session)) {
			String id = (String) session.getAttribute("SID");
			MemberVO mVO = mDao.myInfo(id);
			mv.addObject("DATA", mVO);
			mv.setViewName("member/memberInfo");
		} else {
			rv.setUrl("/cls2/member/login.cls");
			mv.setView(rv);
		}
		
		return mv;
	}
	
	// 내 정보 수정 폼보기 요청 처리함수
	@RequestMapping("/myInfoEdit.cls")
	public ModelAndView myInfoEdit(ModelAndView mv, HttpSession session, RedirectView rv) {
		if(!isLogin(session)) {
			rv.setUrl("/cls2/member/login.cls");
			mv.setView(rv);
			return mv;
		}
		String id = (String) session.getAttribute("SID");
		MemberVO mVO = mDao.myInfo(id);
		List list = mDao.avtInfo(mVO.getGen());
		mv.addObject("DATA", mVO);
		mv.addObject("LIST", list);
		mv.setViewName("member/myInfoEdit");
		return mv;
	}
	
	// 내정보 수정 요청 처리함수
	@RequestMapping(value="/myInfoEditProc.cls")
	public ModelAndView myInfoEditProc(ModelAndView mv, HttpSession session, RedirectView rv, MemberVO mVO) {
		if(!isLogin(session)) {
			rv.setUrl("/cls2/member/login.cls");
			mv.setView(rv);
			return mv;
		}
		int cnt = mDao.myInfoEditProc(mVO);
		if(cnt == 1) {
			rv.setUrl("/cls2/member/myInfo.cls");
		} else {
			rv.setUrl("/cls2/member/myinfoEdit.cls");
		}
		mv.setView(rv);
		return mv;
	}
	
	// 회원가입 요청 처리함수
	@RequestMapping(value="/joinProc.cls")
	public ModelAndView joinProc(ModelAndView mv, HttpSession session, RedirectView rv, MemberVO mVO) {
		if(isLogin(session)) {
			rv.setUrl("/cls2/");
		} else {
			int cnt = mDao.addMember(mVO);
			if(cnt == 1) {
				session.setAttribute("SID", mVO.getId());
				rv.setUrl("/cls2/gboard/gBoardWrite.cls");
			} else {	
				rv.setUrl("/cls2/member/join.cls");
			}
		}
		mv.setView(rv);
		return mv;
	}
}
