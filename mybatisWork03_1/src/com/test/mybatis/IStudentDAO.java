package com.test.mybatis;

import java.util.ArrayList;

public interface IStudentDAO
{
	public ArrayList<StudentDTO> list();       //-- 학생 조회 메소드
	public int add(StudentDTO dto);            //-- 학생 등록 메소드
}
