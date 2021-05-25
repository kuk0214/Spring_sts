package com.increpas.cls2.controller;

import java.util.*;

import javax.servlet.http.*;

import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.*;
import org.springframework.web.servlet.view.RedirectView;

import com.increpas.cls2.dao.*;
import com.increpas.cls2.util.*;
import com.increpas.cls2.vo.BoardVO;

@Controller
@RequestMapping("/reboard")
public class ReBoard {
	@Autowired
	ReBoardDao rDao;
	@Autowired
	GuestBoardDao gDao;
	
	public boolean isLogin(HttpSession session) {
		String sid = (String) session.getAttribute("SID");
		
		return (sid == null) ? false : true;
	}
	
	// 댓글게시판 글 리스트 보기 요청 처리함수
	@RequestMapping("/reBoardList.cls")
	public ModelAndView reBoardList(ModelAndView mv, PageUtil page) {
		int nowPage = page.getNowPage();
		if(nowPage == 0) {
			nowPage = 1;
		}
		int total = rDao.getTotal();
		page.setPage(nowPage, total, 3, 3);
		List list = rDao.reBoardList(page);
		mv.addObject("LIST", list);
		mv.addObject("PAGE", page);
		return mv;
	}
	
	// 게시글 등록 폼보기 요청 처리함수
	@RequestMapping("/reBoardWrite.cls")
	public ModelAndView reBoardWrite(HttpSession session, ModelAndView mv, RedirectView rv) {
		if(!isLogin(session)) {
			rv.setUrl("/cls2/member/login.cls");
			mv.setView(rv);
			return mv;
		}
		String id = (String) session.getAttribute("SID");
		BoardVO bVO = gDao.writerInfo(id);
		mv.addObject("DATA", bVO);
		
		return mv;
	}
	
	// 게시글 등록 요청 처리함수
	@RequestMapping("/reBoardWriteProc.cls")
	public ModelAndView reBoardWriteProc(ModelAndView mv, HttpSession session, RedirectView rv, BoardVO bVO) {
		if(!isLogin(session)) {
			rv.setUrl("/cls2/member/login.cls");
			mv.setView(rv);
			return mv;
		}
		int cnt = rDao.reBoardWriteProc(bVO);
		if(cnt != 1) {
			rv.setUrl("/cls2/reboard/reBoardWrite.cls");
		} else {
			rv.setUrl("/cls2/reboard/reBoardList.cls");
		}
		mv.setView(rv);
		return mv;
	}
	
	// 댓글 쓰기 폼보기 요청 처리함수
	@RequestMapping("/reBoardReply.cls")
	public ModelAndView reBoardReply(ModelAndView mv, String title, int upno, int nowPage, HttpSession session, RedirectView rv) {
		if(!isLogin(session)) {
			rv.setUrl("/cls2/member/login.cls");
			mv.setView(rv);
			return mv;
		}
		String id = (String) session.getAttribute("SID");
		
		BoardVO bVO = gDao.writerInfo(id);
		bVO.setTitle(title);
		bVO.setUpno(upno);
		
		mv.addObject("DATA", bVO);
		mv.addObject("nowPage", nowPage);

		return mv;
	}
	
	// 댓글 등록 요청 처리함수
	@RequestMapping("/reBoardReplyProc.cls")
	public ModelAndView reBoardReplyProc(BoardVO bVO, ModelAndView mv, HttpSession session, RedirectView rv) {
		if(!isLogin(session)) {
			rv.setUrl("/cls2/member/login.cls");
			mv.setView(rv);
			return mv;
		}
		int cnt = rDao.reBoardReplyProc(bVO);
		if(cnt != 1) {
			rv.setUrl("/cls2/reboard/reBoardReply.cls");
		} else {
			rv.setUrl("/cls2/reboard/reBoardList.cls");
		}
		mv.setView(rv);
		return mv;
	}
	
	// 게시글 수정 폼보기 처리함수
	@RequestMapping("/reBoardEdit.cls")
	public ModelAndView reBoardEdit(HttpSession session, ModelAndView mv, RedirectView rv, int rno) {
		if(!isLogin(session)) {
			rv.setUrl("/cls2/member/login.cls");
			mv.setView(rv);
			return mv;
		}
		String id = (String) session.getAttribute("SID");
		BoardVO bVO = rDao.rnoReBoard(rno);
		mv.addObject("DATA", bVO);
		bVO = gDao.writerInfo(id);
		mv.addObject("data", bVO);
		return mv;
	}
	
	// 게시글 수정 요청 처리함수
	@RequestMapping("/reBoardEditProc.cls")
	public ModelAndView reBoardEditProc(HttpSession session, ModelAndView mv, RedirectView rv, BoardVO bVO) {
		if(!isLogin(session)) {
			rv.setUrl("/cls2/member/login.cls");
			mv.setView(rv);
			return mv;
		}
		int cnt = rDao.reBoardEditProc(bVO);
		if(cnt != 1) {
			rv.setUrl("/cls2/reboard/reBoardEdit.cls");
		} else {
			rv.setUrl("/cls2/reboard/reBoardList.cls");
		}
		mv.setView(rv);
		return mv;
	}
	
	// 글 삭제 요청 처리함수
	@RequestMapping("/reBoardDel.cls")
	public ModelAndView reBoardDel(ModelAndView mv, HttpSession session, RedirectView rv, int rno) {
		if(!isLogin(session)) {
			rv.setUrl("/cls2/member/login.cls");
			mv.setView(rv);
			return mv;
		}
		int cnt = rDao.reBoardDel(rno);
		rv.setUrl("/cls2/reboard/reBoardList.cls");
		mv.setView(rv);
		return mv;
	}
}
