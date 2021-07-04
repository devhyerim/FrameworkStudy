package com.test.mybatis;

import java.util.ArrayList;

public interface IGradeDAO
{
	public ArrayList<GradeDTO> list();       //-- 성적 조회 메소드
	public int add(GradeDTO dto);            //-- 성적 등록 메소드
}
