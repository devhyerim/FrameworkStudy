/*======================
	MemberMain.java
	- 컨트롤러
=======================*/

package com.test.mybatis;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MemberMain
{
	// 인터페이스 SqlSession 속성 구성
	//-- 원래 setter나 생성자 구성해야되지만, 이미 dispatcher-servlet.xml에서 ref해서 생성한 바 있다.
	// SqlSession 인터페이스를 구현하는 것을 자동으로 알아서 찾고 주입시켜줘.
	// mybatis 객체 의존성 (자동) 주입!!
	@Autowired
	private SqlSession sqlSession;
	
	@RequestMapping(value="/memberlist.action", method=RequestMethod.GET)
	public String memberList(ModelMap model)  //-- 매개변수는 Model, ModelMap 모두 가능
	{
		// IMemberDAO dao = MemberDAO 객체 생성 => mybatis가 처리 완료
		//--IMemberDAO dao = MemberDAO(); (기존 방식)
		IMemberDAO dao = sqlSession.getMapper(IMemberDAO.class);
		//-- IMemberDAO dao = (IMemberDAO) sqlSession.getMapper(); 처럼
		//   형변환 해야하는 것을 안전성을 위해 미리 인터페이스 구현 클래스를 찾아서 가져온다.
		
		model.addAttribute("count", dao.count());
		model.addAttribute("list", dao.list());
		
		return "WEB-INF/views/MemberList.jsp";
	}
	
	// 데이터 입력 메소드 정의
	@RequestMapping(value="/memberinsert.action", method=RequestMethod.POST)
	public String memberInsert(MemberDTO m)
	{
		IMemberDAO dao = sqlSession.getMapper(IMemberDAO.class);
		
		dao.add(m);
		
		// 입력 후, 조회 페이지로 리다이렉트
		return "redirect:memberlist.action";
	}
	
	// 데이터 삭제 메소드 정의
	//-- 스크립트에서 URL이 get방식으로 요청됨 주의!!!
	@RequestMapping(value="/memberdelete.action", method=RequestMethod.GET)
	public String memberDelete(MemberDTO m)
	{
		IMemberDAO dao = sqlSession.getMapper(IMemberDAO.class);
		
		dao.remove(m); //-- mid만 들어있는 MemberDTO/mid를 받을 수도 있다.
		
		// 입력 후, 조회 페이지로 리다이렉트
		return "redirect:memberlist.action";
	}
	
	// 데이터 수정 메소드 정의
	@RequestMapping(value="/memberupdate.action", method=RequestMethod.POST)
	public String memberUpdate(MemberDTO m)
	{
		IMemberDAO dao = sqlSession.getMapper(IMemberDAO.class);
		
		dao.modify(m);
		
		return "redirect:memberlist.action";
	}
}
