/*==================================
   PositionInsertController.java
   - 사용자 정의 컨트롤러 클래스
   - 직위 입력(등록) 액션 처리
===================================*/


package com.test.mvc;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

// ※ Spring 의 『Controller』 인터페이스를 구현하는 방법을 통해
//    사용자의 정의 컨트롤러 클래스를 구현한다.

public class PositionInsertController implements Controller
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
      
      // 세션 처리과정 추가 --------------------------------------------
      HttpSession session = request.getSession();
      
      if (session.getAttribute("name") == null)
      {
      	mav.setViewName("redirect:loginform.action");
      	return mav;
      }
      else if (session.getAttribute("admin") == null)
      {
      	mav.setViewName("redirect:logout.action");
      	return mav;
      }
      // ---------------------------------------------------------------
		
      String positionName = request.getParameter("positionName");
      
      try
      {
    	 Position position = new Position();
    	 position.setPositionName(positionName);
         
         dao.add(position);
         mav.setViewName("redirect:positionlist.action");   
      } catch (Exception e)
      {
         System.out.println(e.toString());
      }
      
      return mav;

   }
   
}