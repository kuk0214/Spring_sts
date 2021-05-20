package com.increpas.cls2.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/gboard")
public class GuestBoard {
	
	public boolean isLogin(HttpSession session) {
		String sid = (String) session.getAttribute("SID");
		
		return (sid == null) ? false : true;
	}
	
	@RequestMapping("/gBoardList.cls")
	public ModelAndView gBoardList(ModelAndView mv) {
		return mv;
	}
	
	@RequestMapping("/gBoardWrite.cls")
	public ModelAndView gBoardWrite(HttpSession session, ModelAndView mv) {
		if(isLogin(session)) {
			mv.setViewName("/cls2/");
		}
		return mv;
	}
}
