package com.increpas.home.dao;

import java.util.*;

import org.mybatis.spring.*;
import org.springframework.beans.factory.annotation.*;

/**
 * 이 클래스는 설문조사 관련 데이터베이스 작업을 전담해서 처리할 클래스
 * @author	조경국
 * @since	2021.06.01
 * @version	v.1.0
 * @see		
 * 			작업이력 ]
 * 				2021.06.01	- 	담당자		:	조경국
 *								작업내용	:	클래스제작, 현재 진행중인 설문조사 갯수 조회 처리
 *												
 */
public class SurveyDao {
	@Autowired
	SqlSessionTemplate sqlSession;
	
	// 현재 진행중인 설문조사 갯수 조회 전담 처리함수 
	public int getPCount() {
		return sqlSession.selectOne("sSQL.psntCount");
	}
}
