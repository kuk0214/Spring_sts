package com.increpas.home;

import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * 이 클래스는 WEB-INF 하위의 jsp 파일 호출 예제...
 * @author	조경국
 * @since	2021.05.17
 * @version v.1.0
 */
@Controller
public class Test01 {
	
	@RequestMapping("/test.tst")
	public String getTest() {
		return "test01/Test01";
	}
	
	@RequestMapping("/test02/kieun.tst")
	public void getKieun() {
		System.out.println("######### 김기은 요청 #########");
	}
}
