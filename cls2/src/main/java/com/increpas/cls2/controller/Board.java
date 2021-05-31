package com.increpas.cls2.controller;

import java.util.*;

import javax.servlet.http.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.*;
import org.springframework.web.servlet.view.*;

import com.increpas.cls2.dao.*;
import com.increpas.cls2.service.*;
import com.increpas.cls2.util.*;
import com.increpas.cls2.vo.*;
import com.increpas.home.HomeController;

@Controller
@RequestMapping("/board")
public class Board {
	@Autowired
	BoardDao bDao;
	@Autowired
	FileUtil fUtil;
	@Autowired
	BoardService bSrvc;
	
	private static final Logger log1 = LoggerFactory.getLogger(Board.class);
	
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
		String sid = (String) session.getAttribute("SID");
		if(!isLogin(session)) {
			rv.setUrl("/cls2/member/login.cls");
			mv.setView(rv);
			return mv;
		}
		/*
		int cnt = bDao.addBoard(bVO);
		ArrayList<FileVO> list = null;
		if(cnt == 1) {
			try {
				int count = 0;
				list = fUtil.saveProc(bVO.getFile(), bVO.getBno(), "/img/upload/");
				System.out.println(list.size());
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
		*/
		try {
			bSrvc.insertBoard(bVO, rv);
			log1.info("*** " + sid + " ] 님 게시글 등록 성공 ***");
		} catch(Exception e) {
			log1.info("##### " + sid + " ] 님 게시글 등록 실패 #####");
//			System.out.println("#### 게시글 추가 실패 ####");
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
	
	// 게시글 수정 폼보기 요청 처리함수
	@RequestMapping("/boardEdit.cls")
	public ModelAndView boardEdit(BoardVO bVO, HttpSession session, ModelAndView mv, RedirectView rv) {
		if(!isLogin(session)) {
			rv.setUrl("/cls2/member/login.cls");
			mv.setView(rv);
			return mv;
		}
		bVO = bDao.boardDetail(bVO.getBno());
		List list = bDao.subFileList(bVO.getBno());
		mv.addObject("DATA", bVO);
		mv.addObject("LIST", list);
		return mv;
	}
	
	// 첨부파일 삭제 요청 처리함수
	@RequestMapping("/boardImgDel.cls")
	@ResponseBody
	public HashMap<String, String> boardImgDel(int fno, ModelAndView mv, HttpSession session) {
		HashMap<String, String> map = new HashMap<String, String>();
		if(!isLogin(session)) {
			map.put("result", "REDIRECT");
		}
		int cnt = bDao.delSub(fno);
		if(cnt == 1) {
			map.put("result", "YES");
		} else {
			map.put("result", "NO");
		}
		
		return map;
	}
	
	// 게시글 삭제 요청 처리함수
	@RequestMapping("/boardDel.cls")
	public ModelAndView boardDel(int nowPage, int bno, ModelAndView mv, HttpSession session, RedirectView rv) {
		if(!isLogin(session)) {
			rv.setUrl("/cls2/member/login.cls");
			mv.setView(rv);
			return mv;
		}
		
		int cnt = bDao.boardDel(bno);
		if(cnt == 1) {
			mv.addObject("PATH", "/cls2/board/boardList.cls");
		} else {
			mv.addObject("PATH", "/cls2/board/boardEidt.cls");
			mv.addObject("BNO", bno);
		}
		mv.addObject("nowPage", nowPage);
		mv.setViewName("board/redirectView");			
		return mv;
	}
	
	// 게시글 수정 요청 처리함수
	@RequestMapping("/boardEditProc.cls")
	public ModelAndView boardEditProc(BoardVO bVO, int bno, int nowPage, ModelAndView mv, HttpSession session, RedirectView rv) {
		if(!isLogin(session)) {
			rv.setUrl("/cls2/member/login.cls");
			mv.setView(rv);
			return mv;
		}
		
		mv.addObject("nowPage", nowPage);
		mv.addObject("BNO", bVO.getBno());
		/*
		if(bVO.getTitle() != null || bVO.getBody() != null) {
			int cnt = bDao.boardEdit(bVO);
			if(cnt != 1) {
				mv.addObject("PATH", "/cls2/board/boardEdit.cls");
				return mv;
			}
		}
		if(bVO.getFile() != null) {
			ArrayList<FileVO> list = null; 
					
			try {
				list = fUtil.saveProc(bVO.getFile(), bVO.getBno(), "/img/upload/");
				
				int count = 0;
				
				for(FileVO vo : list) {
					count += bDao.addFile(vo);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		*/
		try {
			bSrvc.editBoard(bVO, mv);			
		} catch(Exception e) {
			System.out.println("### 게시글 수정 실패 ###");
		}
		mv.addObject("PATH", "/cls2/board/boardDetail.cls");
		mv.setViewName("board/redirectView");
		return mv;
	}
}
