/*==================================
   LoginController.java
   - 사용자 정의 컨트롤러 클래스
   - 로그인 액션 처리 전용 클래스
===================================*/

package com.test.mvc;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

public class LoginController implements Controller
{
	private IEmployeeDAO dao;
	
	public void setDao(IEmployeeDAO dao)
	{
		this.dao = dao;
	}

	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		ModelAndView mav = new ModelAndView();
		
		// 데이터 수신 → LoginForm.jsp 로부터 넘어온 데이터 수신
		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
		String admin = request.getParameter("admin");
		
		String name = null;
		
		try
		{
			// 로그인 처리 → 일반/관리자에 따라 로그인 처리 방식 구분
			if(admin==null)
			{
				// 일반 사원 로그인
				// 로그인 시 이름을 반환하도록 dao 구성
				name = dao.login(id, pw);
			}else
			{
				name = dao.loginAdmin(id, pw);
			}
			
			// 로그인 수행에 따른 성공 여부 확인 및 구분
			if(name==null)
			{
				// 로그인 실패 → 로그인 폼 다시 요청하도록 안내
				mav.setViewName("redirect:loginform.action");
			}else
			{
				// 로그인 성공 → 세션구성
				HttpSession session = request.getSession();
				session.setAttribute("name", name);
				
				// 관리자 여부 확인
				// 관리자   → employeelist.action 요청하고,
				// 일반직원 → emplist.action 을 요청하도록 안내
				if(admin==null)
				{
					mav.setViewName("redirect:emplist.action");
				}
				else
				{
					session.setAttribute("admin", "");
					mav.setViewName("redirect:employeelist.action");
				}
			}
			
		} catch (Exception e)
		{
			System.out.println(e.toString());
		}
		
		return mav;
	}
}
