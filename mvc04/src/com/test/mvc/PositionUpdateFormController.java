/*==================================
   PositionUpdateFormController.java
   - 사용자 정의 컨트롤러 클래스
   - 사용자가 직위 수정할 수 있는 화면으로 이동
   - 저장되어 있는 직위 정보를 가지고 뷰 화면으로 이동
===================================*/

package com.test.mvc;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;


public class PositionUpdateFormController implements Controller
{
	private IPositionDAO dao;
	
	public void setDao(IPositionDAO dao)
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
			Position position = new Position();
			
			// 데이터 수신 → PositionList.jsp로부터 PositionId 수신
			String positionId = request.getParameter("positionId");
			
			position = dao.searchId(positionId);
			
			mav.addObject("position", position);
			mav.setViewName("WEB-INF/views/PositionUpdateForm.jsp");
			
		} catch (Exception e)
		{
			System.out.println(e.toString());
		}
		
		return mav;
	}
}
