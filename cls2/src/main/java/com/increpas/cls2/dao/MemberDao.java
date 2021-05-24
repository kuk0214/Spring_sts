package com.increpas.cls2.dao;

import java.util.*;

import org.mybatis.spring.*;
import org.springframework.beans.factory.annotation.*;

import com.increpas.cls2.vo.MemberVO;

public class MemberDao {
	@Autowired
	SqlSessionTemplate sqlSession;
	
	// 아이디 중복 체크
	public int getIdCnt(String sid) {
		return sqlSession.selectOne("mSQL.idCheck", sid);
	}
	
	// 로그인 데이터베이스 전담 조회 처리함수
	public int loginProc(MemberVO mVO) {
		return sqlSession.selectOne("mSQL.loginProc", mVO);
	}
	
	/*
		참고 ]
			select 질의명령을 처리하는 마이바티스 함수
				selectOne()		: 질의명령의 실행 결과가 한행인 경우에 사용하는 함수
				
				selectList()	: 질의명령의 실행 결과가 다행인 경우에 사용하는 함수
								==> 반환값이 List 타입이다.
				
				형식 ]
					
					selectOne("질의명령xml파일 namespace.질의명령id"[,질의명령을 완성하기 위해 채워줄 데이터]) 
	 */
	
	// 회원 리스트 조회 전담 처리함수
	public List membList() { // 이 리스트에는 MemberVo 들이 담겨있다.
		return sqlSession.selectList("mSQL.membList");
	}
	
	// 회원정보 조회(mno) 전담 처리함수
	public MemberVO membInfo(int mno) {
		return sqlSession.selectOne("mSQL.memberNoInfo", mno);
	}
	
	// 회원정보 조회(id) 전담 처리함수
	public MemberVO myInfo(String id) {
		return sqlSession.selectOne("mSQL.memberIdInfo", id);
	}
	
	// 아바타 정보 조회 전담 처리함수
	public List avtInfo(String gen) {
		return sqlSession.selectList("mSQL.avtInfo", gen);
	}
	
	// 회원가입 입력 전담 처리함수
	public int addMember(MemberVO mVO) {
		return sqlSession.insert("mSQL.addMember", mVO);
	}
	
	// 회원정보 수정 전담 처리함수
	public int myInfoEditProc(MemberVO mVO) {
		return sqlSession.update("mSQL.myInfoEditProc", mVO);
	}
}
