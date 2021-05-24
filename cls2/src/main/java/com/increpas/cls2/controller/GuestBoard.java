package com.increpas.cls2.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.increpas.cls2.dao.GuestBoardDao;

@Controller
@RequestMapping("/gboard")
public class GuestBoard {
	@Autowired
	GuestBoardDao gDao;
	
	public boolean isLogin(HttpSession session) {
		String sid = (String) session.getAttribute("SID");
		
		return (sid == null) ? false : true;
	}
	
	@RequestMapping("/gBoardList.cls") 
	public ModelAndView gBoardList(ModelAndView mv, HttpSession session) { 
		List list = gDao.gBoardList(); 
		session.setAttribute("LIST", list);
		return mv; 
	}
	 
	
	@RequestMapping("/gBoardWrite.cls")
	public ModelAndView gBoardWrite(HttpSession session, ModelAndView mv, RedirectView rv) {
		if(!isLogin(session)) {
			rv.setUrl("/cls2/member/login.cls");
			mv.setView(rv);
			return mv;
		}
		
		return mv;
	}
}
