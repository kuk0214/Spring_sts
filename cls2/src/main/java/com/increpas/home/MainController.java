package com.increpas.home;

import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.*;

import com.increpas.home.dao.*;

@Controller
public class MainController {
	@Autowired
	SurveyDao sDao;
	
	@RequestMapping("/")
	public ModelAndView getMain(ModelAndView mv) {
		
		int cnt = sDao.getPCount();
		mv.addObject("SCOUNT", cnt);
		mv.setViewName("main");
		return mv;
	}
}
