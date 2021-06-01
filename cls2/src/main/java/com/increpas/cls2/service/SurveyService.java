package com.increpas.cls2.service;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.*;
import org.springframework.transaction.annotation.*;
import org.springframework.web.servlet.view.*;

import com.increpas.cls2.dao.*;
import com.increpas.cls2.vo.*;

public class SurveyService {
	@Autowired
	SurveyDao sDao;
	
	// 설문응답 추가 서비스 함수
	@Transactional
	public void addSrvyService(SurveyVO sVO, RedirectView rv, HttpSession session) {
		rv.setUrl("/cls2/survey/survey.cls");
		String sid = (String) session.getAttribute("SID");
		sVO.setId(sid);
		int[] arr = sVO.getQnolist();
		for(int i = 0 ; i < arr.length; i++ ) {
			sVO.setQno(arr[i]);
			sDao.insertAnswer(sVO);
		}
		rv.setUrl("/cls2/survey/surveyList.cls");
	}
}
