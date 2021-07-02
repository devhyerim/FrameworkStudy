/*=====================
    GradeDTO.java 
=======================*/

package com.test.mybatis;

public class GradeDTO
{
	private String sid, name, sub1, sub2, sub3, tot, avg, ch;
	//-- 아이디, 이름, 과목1점수, 과목2점수, 과목3점수, 총점, 평균, 등급

	// ※ 원칙적으로는 각 데이터 타입이 있고
	//    DB에 맞춰 속성 타입을 지정하는 것이 맞다.
	//    (String, int, double 등..)
	//    하지만 오라클에서 NUMBER로 구성되어 있는 정수 및 실수를
	//    숫자로 구성할 때 null에 대한 컨트롤이 까다로워질 수 있다.
	//    이와 같은 이유로 실무에서는 편의상 String으로 구성하는 경우가 많다.
	//    해당 데이터에 대한 연산이 필요한 경우가 발생하면
	//    변환을 수행하여 처리하게 된다.
	//    날짜(DATE) 타입도 이에 해당한다.
	
	//    즉, sub1, sub2, sub3, tot, avg와 같이
	//    상황에 따라 null 이 적용될 가능성이 농후한 칼럼의 경우
	//    String 자료형을 사용하는 것이 데이터 컨트롤 과정에서
	//    좀 더 유연할 수 있다.
	
	public String getSid()
	{
		return sid;
	}

	public void setSid(String sid)
	{
		this.sid = sid;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public String getSub1()
	{
		return sub1;
	}

	public void setSub1(String sub1)
	{
		this.sub1 = sub1;
	}

	public String getSub2()
	{
		return sub2;
	}

	public void setSub2(String sub2)
	{
		this.sub2 = sub2;
	}

	public String getSub3()
	{
		return sub3;
	}

	public void setSub3(String sub3)
	{
		this.sub3 = sub3;
	}

	public String getTot()
	{
		return tot;
	}

	public void setTot(String tot)
	{
		this.tot = tot;
	}

	public String getAvg()
	{
		return avg;
	}

	public void setAvg(String avg)
	{
		this.avg = avg;
	}

	public String getCh()
	{
		return ch;
	}

	public void setCh(String ch)
	{
		this.ch = ch;
	}	
}
