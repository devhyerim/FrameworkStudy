package com.test.mybatis;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class StudentController
{
	@Autowired
	private SqlSession sqlSession;
	
	// 학생 조회 메소드
	@RequestMapping(value="/studentlist.action", method=RequestMethod.GET)
	public String studentList(ModelMap model)
	{
		IStudentDAO dao = sqlSession.getMapper(IStudentDAO.class);
		//-- IMemberDAO dao = (IMemberDAO) sqlSession.getMapper(); 처럼
		//   형변환 해야하는 것을 안전성을 위해 미리 인터페이스 구현 클래스를 찾아서 가져온다.
		
		model.addAttribute("list", dao.list());
		
		return "WEB-INF/views/StudentList.jsp";
	}
	
	// 학생 등록 메소드
	@RequestMapping(value="/studentinsertform.action", method=RequestMethod.GET)
	public String studentInsertForm()
	{
		// 학생 등록 페이지로 이동
		return "WEB-INF/views/StudentInsertForm.jsp";
	}
	
	// 학생 등록 액션 처리 메소드
	@RequestMapping(value="/studentinsert.action", method=RequestMethod.POST)
	public String studentInsert(StudentDTO dto)
	{
		IStudentDAO dao = sqlSession.getMapper(IStudentDAO.class);
	
		dao.add(dto);
		
		// 등록 후 리다이렉트
		return "redirect:studentlist.action";
	}

}
