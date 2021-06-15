/*==============================================
    Main.java
    - main() 메소드가 포함된 테스트 클래스 
==============================================*/

package com.test.spr;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main
{
	public static void main(String[] args)
	{
		// 주 업무 실행에 대한 테스트 (Spring AOP 기법 적용 후)
		ApplicationContext context = new ClassPathXmlApplicationContext("config.xml");
		
		//Calculator cal = new CalculatorImpl();
		Calculator cal = context.getBean("proxy", Calculator.class);
		
		int add = cal.add(200, 200);
		System.out.println(add);
		
		/*
		int sub = cal.sub(10, 20);
		System.out.println(sub);
		
		int multi = cal.multi(10, 20);
		System.out.println(multi);
		
		int div = cal.div(10, 20);
		System.out.println(div);
		*/
	}
}
//--==>>
/*
정보: 처리 시간 측정 시작 ----------------------------
6월 15, 2021 10:22:21 오전 com.test.spr.CalculatorBeforeAdvice before
정보: Before Advice 실행 -----------------------------
6월 15, 2021 10:22:21 오전 com.test.spr.CalculatorBeforeAdvice before
정보: 주 업무 실행 전에 수행되어야 하는 작업
6월 15, 2021 10:22:21 오전 com.test.spr.CalculatorBeforeAdvice before
정보: ----------------------------- Before Advice 실행
6월 15, 2021 10:22:21 오전 com.test.spr.CalculatorAfterThrowing afterThrowing
정보: After Throwing Advice 수행----------------
6월 15, 2021 10:22:21 오전 com.test.spr.CalculatorAfterThrowing afterThrowing
정보: 주 업무 실행 과정에서 예외 발생 시 처리되는 사후 업무
6월 15, 2021 10:22:21 오전 com.test.spr.CalculatorAfterThrowing afterThrowing
정보: ----------------After Throwing Advice 수행
Exception in thread "main" java.lang.IllegalArgumentException: 100 보다 큰 인자를 전달한 예외 발생
:
:
*/
