package com.increpas.cls2.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/reboard")
public class ReBoard {
	
	public boolean isLogin(HttpSession session) {
		String sid = (String) session.getAttribute("SID");
		
		return (sid == null) ? false : true;
	}
	
	@RequestMapping("/reBoardList.cls")
	public ModelAndView reBoardList(ModelAndView mv) {
		return mv;
	}
	
	@RequestMapping("reBoardWrite.cls")
	public ModelAndView reBoardWrite(HttpSession session, ModelAndView mv) {
		if(isLogin(session)) {
			mv.setViewName("/cls2/");
		}
		return mv;
	}
	
	@RequestMapping("reBoardEdit.cls")
	public ModelAndView reBoardEdit(HttpSession session, ModelAndView mv) {
		if(isLogin(session)) {
			mv.setViewName("/cls2/");
		}
		return mv;
	}
}
