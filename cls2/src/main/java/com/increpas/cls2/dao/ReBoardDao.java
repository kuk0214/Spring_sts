package com.increpas.cls2.dao;

import java.util.*;

import org.mybatis.spring.*;
import org.springframework.beans.factory.annotation.*;

import com.increpas.cls2.util.PageUtil;
import com.increpas.cls2.vo.BoardVO;
import com.increpas.cls2.vo.MemberVO;

public class ReBoardDao {
	@Autowired
	SqlSessionTemplate sqlSession;
	
	// 댓글게시판 글 리스트 전담 처리함수
	public List reBoardList(PageUtil page) {
		return sqlSession.selectList("rSQL.reBoardList", page);
	}
	
	// 총게시글 수 조회 전담 처리함수
	public int getTotal() {
		return sqlSession.selectOne("rSQL.getTotal");
	}

	// 게시글 등록 요청 전담 처리함수
	public int reBoardWriteProc(BoardVO bVO) {
		return sqlSession.insert("rSQL.reBoardWriteProc", bVO);
	}
	
	// 댓글 등록 요청 전담 처리함수
	public int reBoardReplyProc(BoardVO bVO) {
		return sqlSession.insert("rSQL.reBoardReplyProc", bVO);
	}
	
	// 글번호로 게시글 조회 전담 처리함수
	public BoardVO reBoardEdit(int rno) {
		return sqlSession.selectOne("rSQL.reBoardEdit", rno);
	}
	
	// 글 수정 요청 전담 처리함수
	public int reBoardEditProc(BoardVO bVO) {
		return sqlSession.update("rSQL.reBoardEditProc", bVO);
	}
	
	// 글 삭제 요청 전담 처리함수
	public int reBoardDel(int rno) {
		return sqlSession.update("rSQL.reBoardDel", rno);
	}
}