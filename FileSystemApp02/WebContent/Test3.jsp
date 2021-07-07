<%@page import="java.io.DataInputStream"%>
<%@page import="java.util.Enumeration"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	// Test3.jsp
	request.setCharacterEncoding("UTF-8");
	String cp = request.getContextPath();
	
	String contentType = request.getContentType();
	Enumeration e = request.getHeaderNames();
	
	out.println("전송받은 헤더 정보...<br>");
	while(e.hasMoreElements())
	{
		String key = (String)e.nextElement();
		String value = request.getHeader(key);
		out.println(key + " : " + value + "<br>");
	}
	
	out.println("<br>");
	out.println("전송받은 데이터...<br>");
	DataInputStream is = new DataInputStream(request.getInputStream());
	String str;
	
	while((str = is.readLine()) != null)
		out.println(new String(str.getBytes("ISO-8859-1"), "UTF-8") + "<br>");
	
	
	/* 전달받은 파일에 대한 분석 결과 출력 확인 */
	
	/*
	전송받은 헤더 정보...
	host : localhost:8090
	connection : keep-alive
	content-length : 427
	cache-control : max-age=0
	sec-ch-ua : " Not;A Brand";v="99", "Google Chrome";v="91", "Chromium";v="91"
	sec-ch-ua-mobile : ?0
	upgrade-insecure-requests : 1
	origin : http://localhost:8090
	content-type : multipart/form-data; boundary=----WebKitFormBoundaryQrAQMpFGg8VfBDtn
	user-agent : Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.124 Safari/537.36
	accept : text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,;q=0.8,application/signed-exchange;v=b3;q=0.9
	sec-fetch-site : same-origin
	sec-fetch-mode : navigate
	sec-fetch-user : ?1
	sec-fetch-dest : document
	referer : http://localhost:8090/FileSystemApp02/Test1.jsp
	accept-encoding : gzip, deflate, br
	accept-language : ko-KR,ko;q=0.9,en-US;q=0.8,en;q=0.7
	cookie : JSESSIONID=BCCB7CEB374A06D3BB675E3714A8E5CF; _ga=GA1.1.1038055621.1623285092; _ga_DJSCM50Q4S=GS1.1.1623285091.1.1.1623285114.0
	
	전송받은 데이터...
	------WebKitFormBoundaryQrAQMpFGg8VfBDtn
	Content-Disposition: form-data; name="name"
	
	전송파일
	------WebKitFormBoundaryQrAQMpFGg8VfBDtn
	Content-Disposition: form-data; name="upload"; filename="ex.txt"
	Content-Type: text/plain
	
	이몸이 한가하여
	공부를 하려하니
	무식이 태산이오
	지식이 티끝이라
	짜증이 절로나니
	잠이나 자려하오
	------WebKitFormBoundaryQrAQMpFGg8VfBDtn--
	*/
%>