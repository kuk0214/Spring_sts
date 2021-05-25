package com.increpas.cls2.dao;

import java.util.*;

import org.mybatis.spring.*;
import org.springframework.beans.factory.annotation.*;

import com.increpas.cls2.vo.BoardVO;
import com.increpas.cls2.vo.MemberVO;

public class GuestBoardDao {
	@Autowired
	SqlSessionTemplate sqlSession;
	
	// 방명록 글 리스트 조회 전담 처리함수
	public List gBoardList() {
		return sqlSession.selectList("gSQL.gBoardList");
	}
	
	// 방명록 글 카운트 조회 전담 처리함수
	public int writeCount(String id) {
		return sqlSession.selectOne("gSQL.writeCount", id);
	}
	
	// 작성자 정보 조회 전담 처리함수
	public BoardVO writerInfo(String id) {
		return sqlSession.selectOne("gSQL.writerInfo", id);
	}
	
	// 방명록 글등록 전담 처리함수
	public int gBoardWriteProc(BoardVO bVO) {
		return sqlSession.insert("gSQL.gBoardWriteProc", bVO);
	}
}
