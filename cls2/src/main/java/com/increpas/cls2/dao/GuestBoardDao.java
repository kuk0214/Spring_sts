package com.increpas.cls2.dao;

import java.util.*;

import org.mybatis.spring.*;
import org.springframework.beans.factory.annotation.*;

import com.increpas.cls2.vo.MemberVO;

public class GuestBoardDao {
	@Autowired
	SqlSessionTemplate sqlSession;
	
	// 방명록 글 리스트 조회 전담 처리함수
	public List gBoardList() {
		return sqlSession.selectList("gSQL.gBoardList");
	}
	
	// 방명록 글 카운트 조회 전담 처리함수
	public int writeCount(int mno) {
		return sqlSession.selectOne("gSQL.writeCount", mno);
	}
}
