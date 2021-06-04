package com.increpas.cls2.controller;

import java.util.*;

import javax.servlet.http.*;

import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.*;
import org.springframework.web.servlet.view.*;

import com.increpas.cls2.dao.*;
import com.increpas.cls2.service.*;
import com.increpas.cls2.vo.*;

/**
 * 	이 클래스는 설문조사 관련 요청을 전담처리할 클래스
 * @author	조경국
 * @since	2021.06.06
 * @version v.1.0
 * @see
 * 			작업 이력 ]
 * 					2021/06/01	-	담당자		: 조경국
 * 									작업내용	: 클래스 제작
 * 												  설문조사 리스트 요청 함수 제작
 * 												  설문조사 페이지 요청 함수 제작
 * 
 */

@Controller
@RequestMapping("/survey")
public class Survey {
	@Autowired
	SurveyDao sDao;
	@Autowired
	SurveyService sSrvc;
	
	// 진행중인 설문 리스트 요청 처리함수
	@RequestMapping("/surveyList.cls")
	public ModelAndView surveyList(ModelAndView mv, HttpSession session, RedirectView rv) {
		List list = sDao.getList();
		mv.addObject("LIST", list);
		return mv;
	}
	
	// 설문조사 페이지 요청 처리함수
	@RequestMapping("/survey.cls")
	public ModelAndView surveyDetail(SurveyVO sVO, ModelAndView mv, HttpSession session, RedirectView rv) {
		int cnt = sDao.answerCnt(sVO);
		if(cnt == 1) {
			mv.addObject("PATH", "/cls2/survey/surveyResult.cls");
			mv.addObject("SINO", sVO.getSino());
			mv.addObject("TITLE", sVO.getTitle());
			mv.setViewName("survey/redirectPage");
			return mv;
		}
		ArrayList<SurveyVO> list = (ArrayList<SurveyVO>) sDao.questList(sVO.getSino());
		
		for(SurveyVO s : list) {
			int qno = s.getQno();
			ArrayList<SurveyVO> l = (ArrayList<SurveyVO>) sDao.exList(qno);
			s.setList(l);
		}
		
		mv.addObject("TITLE", sVO.getTitle());
		mv.addObject("LIST", list);
		mv.addObject("LEN", list.size());
		
		return mv;
	}
	
	// 설문응답 추가 요청 처리함수
	@RequestMapping("/surveyProc.cls")
	public ModelAndView surveyProc(SurveyVO sVO, ModelAndView mv, RedirectView rv, HttpSession session) {
		try {
			sSrvc.addSrvyService(sVO, rv, session);
		} catch(Exception e) {
			rv.setUrl("/cls2/survey/survey.cls");
			e.printStackTrace();
		}
		
		mv.setView(rv);
		return mv;
	}
	
	// 설문 응답 결과 조회 요청 처리함수
	@RequestMapping(value="/surveyResult.cls", params = {"title", "sino"}, method=RequestMethod.POST)
	public ModelAndView surveyResult(SurveyVO sVO, ModelAndView mv) {
		ArrayList<SurveyVO> list = (ArrayList<SurveyVO>) sDao.questList(sVO.getSino());
		
		for(SurveyVO s : list) {
			int qno = s.getQno();
			ArrayList<SurveyVO> l = (ArrayList<SurveyVO>) sDao.getResult(s);
			s.setList(l);
		}
		
		mv.addObject("TITLE", sVO.getTitle());
		mv.addObject("LIST", list);
		mv.addObject("LEN", list.size());
		return mv;
	}
}
