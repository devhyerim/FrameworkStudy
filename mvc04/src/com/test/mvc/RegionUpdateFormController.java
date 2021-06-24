/*==================================
   RegionUpdateFormController.java
   - 사용자 정의 컨트롤러 클래스
   - 사용자가 지역 수정할 수 있는 화면으로 이동
   - 저장되어 있는 지역 정보를 가지고 뷰 화면으로 이동
===================================*/

package com.test.mvc;



import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;


public class RegionUpdateFormController implements Controller
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
		
		// 세션 처리과정 추가 (관리자만 접근) -----------------------------------------------------
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
		// 세션 처리과정 추가 ----------------------------------------------------------------------
		
		try
		{
			Region region = new Region();
			
			// 데이터 수신 → RegionList.jsp로부터 regionId 수신
			String regionId = request.getParameter("regionId");
			
			region = dao.searchId(regionId);
			
			mav.addObject("region", region);
			mav.setViewName("WEB-INF/views/RegionUpdateForm.jsp");
			
		} catch (Exception e)
		{
			System.out.println(e.toString());
		}
		
		return mav;
	}
}
