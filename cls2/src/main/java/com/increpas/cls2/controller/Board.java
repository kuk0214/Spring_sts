package com.increpas.cls2.controller;

import java.util.*;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.increpas.cls2.dao.BoardDao;
import com.increpas.cls2.util.FileUtil;
import com.increpas.cls2.util.PageUtil;
import com.increpas.cls2.vo.*;

@Controller
@RequestMapping("/board")
public class Board {
	@Autowired
	BoardDao bDao;
	@Autowired
	FileUtil fUtil;
	
	public boolean isLogin(HttpSession session) {
		String sid = (String) session.getAttribute("SID");
		
		return (sid == null) ? false : true;
	}
	
	// 게시글 리스트 요청 처리함수
	@RequestMapping("/boardList.cls")
	public ModelAndView boardList(ModelAndView mv, PageUtil page) {
		int total = bDao.getTotal();
		// 데이터 만들고
		page.setPage(page.getNowPage(), total, 5, 5);
		
		List list = bDao.boardList(page);
		// 데이터 전달하고
		mv.addObject("PAGE", page);
		mv.addObject("LIST", list);

		return mv;
	}
	
	// 게시글 쓰기 폼보기 요청 처리함수
	@RequestMapping("/boardWrite.cls")
	public ModelAndView boardWrite(HttpSession session, ModelAndView mv, RedirectView rv) {
		if(!isLogin(session)) {
			rv.setUrl("/cls2/member/login.cls");
			mv.setView(rv);
			return mv;
		}
		return mv;
	}
	
	// 게시글 등록 요청 처리함수
	@RequestMapping("/boardWriteProc.cls")
	public ModelAndView boardWriteProc(BoardVO bVO, ModelAndView mv, HttpSession session, RedirectView rv) {
		if(!isLogin(session)) {
			rv.setUrl("/cls2/member/login.cls");
			mv.setView(rv);
			return mv;
		}
		int cnt = bDao.addBoard(bVO);
		ArrayList<FileVO> list = null; 
		if(cnt == 1) {
			try {
				int count = 0;
				list = fUtil.saveProc(bVO.getFile(), bVO.getBno(), "/img/upload");
				
				for(FileVO vo : list) {
					
					count = bDao.addFile(vo);
				}
				
				if(count == list.size()) {
					
				} else {
					
				}
			} catch (Exception e) {}
			rv.setUrl("/cls2/board/boardList.cls");			
		} else {
			rv.setUrl("/cls2/board/boardWrite.cls");
		}
		mv.setView(rv);
		return mv;
	}
	
	// 게시글 상세보기 요청 처리함수
	@RequestMapping("/boardDetail.cls")
	public ModelAndView boardDetail(int bno, int nowPage, ModelAndView mv) {
		BoardVO data = bDao.boardDetail(bno);
		data.setSdate();
		// 첨부파일 리스트 꺼내오고
		List list = bDao.subFileList(bno);
		// 데이터 전달하고
		mv.addObject("DATA", data);
		mv.addObject("LIST", list);
		mv.addObject("nowPage", nowPage);
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
