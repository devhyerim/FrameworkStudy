<%@page import="com.oreilly.servlet.multipart.DefaultFileRenamePolicy"%>
<%@page import="com.oreilly.servlet.MultipartRequest"%>
<%@page import="java.io.File"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	// Test4.jsp
	request.setCharacterEncoding("UTF-8");
	String cp = request.getContextPath();
	
	// 경로 얻어내기
	/* String root = request.getRealPath("/"); → 같은 코드. 예전 방식 */
	String root = pageContext.getServletContext().getRealPath("/");
	
	// 실제 물리적 주소
	System.out.println(root); //-- 웹에서 실행하고 콘솔 창에서 확인
	//--==>> C:\SpringMVC\.metadata\.plugins\org.eclipse.wst.server.core\tmp0\wtpwebapps\FileSystemApp03\
	//       워크스페이스\.metadata\.plugins\org.eclipse.wst.server.core\tmp0\wtpwebapps\FileSystemApp03\
	
	// 저장되는 위치와 폴더
	String path = root + "pds" + File.separator + "saveData";
	//--==>> \pds\savaData
	
	// 확인
	//System.out.println(path);
	//--==>> C:\SpringMVC\.metadata\.plugins\org.eclipse.wst.server.core\tmp0\wtpwebapps\FileSystemApp03\pds\saveData
			
	// 저장 대상(디렉터리 경로: \pds\savaData)이 존재하지 않으면 생성해라.
	File dir = new File(path);
	if(!dir.exists())
		dir.mkdirs();
	
	String encType="UTF-8";
	int maxFileSize = 5*1024*1024;		// 전송 최대 사이즈 5M
	
	try
	{
		MultipartRequest req = null;
		// request (그냥 request.getParameter로 받지 못하는) 를 매개변수로 넘겨주고
		// MultipartRequest를 구성해서 받은 것이 req 이다.
		// MultipartReuqest 객체를 생성해서 쪼개진 데이터를 바인딩한다.
		req = new MultipartRequest(request, path, maxFileSize, encType, new DefaultFileRenamePolicy());
		//-- new DefaultFileRenamePolicy() : 같은 파일명으로 저장될때 뒤에 1을 붙인다던지해서
		//   다른 이름의 형태로 저장되도록 만듦
		
		// 이렇게 MultipartReques 객체를 구성하지 않고, multipart/form-data로 form을 넘기면
		// request.getParameter("name") 시 null이 나온다.
		
		out.println("이름 : " + req.getParameter("name") + "<br>");
		out.println("서버에 저장된 파일명 : " + req.getFilesystemName("upload") + "<br>");
		out.println("업로드한 파일명 : " + req.getOriginalFileName("upload") + "<br>");
		out.println("파일 타입 : " + req.getContentType("upload") + "<br>");
		
		File file = req.getFile("upload");
		if(file != null)
			out.println("파일 길이 : " + file.length() + "<br>");
	}catch(Exception e)
	{
		System.out.println(e.toString());
	}
	
	/*
	이름 : 테스트
	서버에 저장된 파일명 : ex.txt
	업로드한 파일명 : ex.txt
	파일 타입 : text/plain
	파일 길이 : 142
	*/
%>