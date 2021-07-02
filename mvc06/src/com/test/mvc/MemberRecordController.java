/*===============================
    MemberRecordController.java
    - 사용자 정의 컨트롤러
================================*/

package com.test.mvc;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MemberRecordController
{
	// ※ 액션 처리를 위한 메소드는 사용자 지정
	//@RequestMapping("/URL 매핑 주소")
	
	// 데이터 베이스의 데이터를 읽어오는 과정
	@RequestMapping("/memberlist.action")
	public String memberList(Model model)
	{
		String result = "";
		
		ArrayList<MemberDTO> list = new ArrayList<MemberDTO>();
		MemberDAO dao = new MemberDAO();
		
		try
		{
			dao.connection();
			list = dao.lists();
			
		} catch (Exception e)
		{
			System.out.println(e.toString());
		}
		finally
		{
			try
			{
				dao.close();
				
			} catch (Exception e2)
			{
				System.out.println(e2.toString());
			}
		}
		
		model.addAttribute("list", list);
		result = "WEB-INF/views/MemberList.jsp";
		
		return result;
	}
	
	// 데이터 입력(회원 등록) 폼 요청 관련 액션 메소드 정의
	@RequestMapping("/memberinsertform.action")
	public String memberInsertForm()
	{
		String result = "";
		
		result = "WEB-INF/views/MemberInsertForm.jsp";
		
		return result;
	}
	
	@RequestMapping("/memberinsert.action")
	public String memberInsert(MemberDTO dto) //id, pw, name, tel, email을 자동으로 넘겨받음
	{
		String result = "";
		
		// MemberInsertForm.jsp로부터 데이터 수신
		//-- request.getParameter 할 필요 없다.
		//   이미 MemberDTO로 자동으로 정보들을 받았다.

		MemberDAO dao = new MemberDAO();
		
		try
		{
			dao.connection();
			dao.insertQuery(dto);
			
		} catch (Exception e)
		{
			System.out.println(e.toString());
		}
		finally
		{
			try
			{
				dao.close();
				
			} catch (Exception e2)
			{
				System.out.println(e2.toString());
			}
		}
		
		result = "redirect:memberlist.action";
		
		return result;
	}
	
	/* 내가 푼 것
	// 데이터 입력(회원 등록) 액션 메소드 정의
	@RequestMapping("/memberinsert.action")
	public ModelAndView memberInsert(HttpServletRequest request)
	{
		// redirect 를 위한 ModealAndView 객체 생성
		ModelAndView mav = new ModelAndView();
		
		// MemberInsertForm.jsp로부터 데이터 수신
		String id = request.getParameter("id");
		String name = request.getParameter("name");
		String pw = request.getParameter("pw");
		String tel = request.getParameter("tel");
		String email = request.getParameter("email");
		
		MemberDTO dto = new MemberDTO();
		dto.setId(id);
		dto.setName(name);
		dto.setPw(pw);
		dto.setTel(tel);
		dto.setEmail(email);
		
		MemberDAO dao = new MemberDAO();
		
		try
		{
			dao.connection();
			dao.insertQuery(dto);
			
		} catch (Exception e)
		{
			System.out.println(e.toString());
		}
		finally
		{
			try
			{
				dao.close();
				
			} catch (Exception e2)
			{
				System.out.println(e2.toString());
			}
		}
		
		mav.setViewName("redirect:memberlist.action");
		
		return mav;
	}
	*/
	
}
