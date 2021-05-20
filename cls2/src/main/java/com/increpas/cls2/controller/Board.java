package com.increpas.cls2.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/board")
public class Board {
	
	public boolean isLogin(HttpSession session) {
		String sid = (String) session.getAttribute("SID");
		
		return (sid == null) ? false : true;
	}
	
	@RequestMapping("/boardList.cls")
	public ModelAndView boardList(ModelAndView mv) {
		return mv;
	}
	
	@RequestMapping("/boardWrite.cls")
	public ModelAndView boardWrite(HttpSession session, ModelAndView mv) {
		if(isLogin(session)) {
			mv.setViewName("/cls2/");
		}
		
		return mv;
	}
	
	@RequestMapping("/boardDetail.cls")
	public ModelAndView boardDetail(ModelAndView mv) {
		return mv;
	}
	
	@RequestMapping("/boardEdit.cls")
	public ModelAndView boardEdit(HttpSession session, ModelAndView mv) {
		if(isLogin(session)) {
			mv.setViewName("/cls2/");
		}
		
		return mv;
	}
}
