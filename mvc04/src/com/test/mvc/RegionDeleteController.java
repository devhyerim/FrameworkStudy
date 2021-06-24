/*==================================
   RegionDeleteController.java
   - 사용자 정의 컨트롤러 클래스
   - 지역 삭제 액션 처리 클래스
===================================*/

package com.test.mvc;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;


public class RegionDeleteController implements Controller
{
	private IRegionDAO dao;
	
	public void setDao(IRegionDAO dao)
	{
		this.dao = dao;
	}

	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		ModelAndView mav = new ModelAndView();
		
		// 세션 확인 처리 ----------------------------------------
		
		HttpSession session = request.getSession();
				
		if(session.getAttribute("name")==null)
		{
			mav.setViewName("redirect:loginform.action");
			return mav;
		}
		else if(session.getAttribute("admin")==null)
		{
			mav.setViewName("redirect:logout.action");
			return mav;
		}		
		// 세션 확인 처리 ----------------------------------------
		
		// 데이터 수신 → regionList.jsp로부터 regionId 수신
		String regionId = request.getParameter("regionId");
		
		try
		{
			dao.remove(regionId);
			
			mav.setViewName("redirect:regionlist.action");
			
		} catch (Exception e)
		{
			System.out.println(e.toString());
		}
		
		return mav;
	}
}
