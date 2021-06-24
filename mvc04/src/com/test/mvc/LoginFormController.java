/*==================================
   LoginFormController.java
   - 사용자 정의 컨트롤러 클래스
   - 아마도, 사용자의 최초 요청 페이지
   - 단순히 로그인 폼이 구성된 페이지를 뷰View로 제시
===================================*/

package com.test.mvc;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;


public class LoginFormController implements Controller
{
	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		ModelAndView mav = new ModelAndView();
		
		mav.setViewName("/WEB-INF/views/LoginForm.jsp");
		
		return mav;
	}
}
