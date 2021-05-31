package com.increpas.cls2.service;

import java.util.*;

import org.springframework.beans.factory.annotation.*;
import org.springframework.transaction.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.*;

import com.increpas.cls2.dao.*;
import com.increpas.cls2.util.*;
import com.increpas.cls2.vo.*;

public class BoardService {
	@Autowired
	BoardDao bDao;
	@Autowired
	FileUtil fUtil;
/*
	트랜젝션 처리에 사용할 함수는
		@Transactional
	이라는 어노테이션을 붙여주기만 하면
	함수 내부의 데이터베이스 작업들이 트랜젝션 처리가 된다.
 */
	// 게시글 등록 서비스 함수
	@Transactional
	public boolean insertBoard(BoardVO bVO, RedirectView rv) {
		// 반환값 변수
		boolean bool = false;
		rv.setUrl("/cls2/board/boardWrite.cls");
		
		int cnt = bDao.addBoard(bVO);
		ArrayList<FileVO> list = null;
		if(cnt == 1) {
			int count = 0;
			list = fUtil.saveProc(bVO.getFile(), bVO.getBno(), "/img/upload/");
			for(FileVO vo : list) {
				
				count = bDao.addFile(vo);
			}

			if(count == list.size()) {
				
			} else {
				
			}
			
			rv.setUrl("/cls2/board/boardList.cls");
			bool = true;
		}
		
		return bool;
	}
	
	@Transactional
	public boolean editBoard(BoardVO bVO, ModelAndView mv) {
		boolean bool = false;
		
		if(bVO.getTitle() != null || bVO.getBody() != null) {
			int cnt = bDao.boardEdit(bVO);
			if(cnt != 1) {
				mv.addObject("PATH", "/cls2/board/boardEdit.cls");
				mv.setViewName("board/redirectView");
			}
		}
		if(bVO.getFile() != null) {
			ArrayList<FileVO> list = null; 
					
			list = fUtil.saveProc(bVO.getFile(), bVO.getBno(), "/img/upload/");
			
			int count = 0;
			
			for(FileVO vo : list) {
				count += bDao.addFile(vo);
			}
			
		}
		
		return bool;
	}
}
