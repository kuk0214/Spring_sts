package com.increpas.cls2.controller;

import java.util.*;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.increpas.cls2.dao.GuestBoardDao;
import com.increpas.cls2.vo.BoardVO;

@Controller
@RequestMapping("/gboard")
public class GuestBoard {
	@Autowired                                                                          
	GuestBoardDao gDao;
	
	public boolean isLogin(HttpSession session) {
		String sid = (String) session.getAttribute("SID");
		
		return (sid == null) ? false : true;
	}
	
	// 방명록 리스트 보기 요청 처리함수
	@RequestMapping("/gBoardList.cls") 
	public ModelAndView gBoardList(ModelAndView mv, HttpSession session) { 
		String id = (String) session.getAttribute("SID");
		if(id != null) {
			int cnt = gDao.writeCount(id);
			mv.addObject("CNT", cnt);			
		}
		List list = gDao.gBoardList();
		mv.addObject("LIST", list);
		return mv; 
	}
	 
	// 방명록 글쓰기 폼보기 요청 처리함수
	@RequestMapping("/gBoardWrite.cls")
	public ModelAndView gBoardWrite(HttpSession session, ModelAndView mv, RedirectView rv) {
		if(!isLogin(session)) {
			rv.setUrl("/cls2/member/login.cls");
			mv.setView(rv);
			return mv;
		}
		String id = (String) session.getAttribute("SID");
		int cnt = gDao.writeCount(id);
		if(cnt != 0) {
			rv.setUrl("/cls2/gboard/gBoardList.cls");
			mv.setView(rv);
			return mv;
		}
		BoardVO bVO = gDao.writerInfo(id);
		mv.addObject("DATA", bVO);
		
		return mv;
	}
	
	// 방명록 글등록 요청 처리 함수
	@RequestMapping("/gBoardWriteProc.cls")
	public ModelAndView gBoardWriteProc(ModelAndView mv, HttpSession session, RedirectView rv, BoardVO bVO) {
		if(!isLogin(session)) {
			rv.setUrl("/cls2/member/login.cls");
			mv.setView(rv);
			return mv;
		}
		int cnt = gDao.gBoardWriteProc(bVO);
		if(cnt == 1) {
			rv.setUrl("/cls2/gboard/gBoardList.cls");
		} else {
			rv.setUrl("/cls2/gboard/gBoardWrite.cls");
		}
		
		mv.setView(rv);
		return mv;
	}
}
