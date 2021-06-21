/*========================================================
    #09. EmployeeDAO.java
    - 데이터베이스 액션 처리 클래스
    - 직원 데이터 입력/출력/수정/삭제 액션
      Connection 객체에 대한 의존성 주입 위한 대비
      → setter injection: 인터페이스 형태의 자료형 구성
                           setter 메소드 정의
=========================================================*/

package com.test.mvc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.sql.DataSource;


public class EmployeeDAO implements IEmployeeDAO
{
	// 인터페이스 자료형 구성
	private DataSource dataSource;

	// setter 메소드 구성
	public void setDataSource(DataSource dataSource)
	{
		this.dataSource = dataSource;
	}

	// 인터페이스에서 선언된 메소드 재정의
	
	// 직원 전체 리스트 출력
	@Override
	public ArrayList<Employee> list() throws SQLException
	{
		ArrayList<Employee> result = new ArrayList<Employee>();
		
		Connection conn = dataSource.getConnection();
		
		String sql = "SELECT EMPLOYEEID, NAME, SSN, BIRTHDAY, LUNAR, LUNARNAME, TELEPHONE"
				+ ", DEPARTMENTID, DEPARTMENTNAME, POSITIONID, POSITIONNAME, REGIONID"
				+ ", REGIONNAME, BASICPAY, EXTRAPAY, PAY, GRADE FROM EMPLOYEEVIEW ORDER BY 1";
		
		PreparedStatement pstmt = conn.prepareStatement(sql);
		ResultSet rs = pstmt.executeQuery();
		
		while(rs.next())
		{
			Employee emp = new Employee();
			//emp.setEmployeeId(rs.getString("EMPLOYEEID"));
			//-- ※ DB에서 employeeId는 NUMBER 속성이지만
			//      DTO의 속성은 String이다.
			//      따라서 이걸 getString으로 받으면 나중에 출력 시 NumberFormatException이 발생한다.
			//      즉, int로 받아 String으로 형변환 시켜줘야 한다.
			emp.setEmployeeId(Integer.toString(rs.getInt("EMPLOYEEID")));
			emp.setName(rs.getString("NAME"));
			emp.setSsn(rs.getString("SSN"));
			emp.setBirthday(rs.getString("BIRTHDAY"));
			emp.setLunar(rs.getInt("LUNAR"));
			emp.setLunarName(rs.getString("LUNARNAME"));
			emp.setTelephone(rs.getString("TELEPHONE"));
			emp.setDepartmentId(rs.getString("DEPARTMENTID"));
			emp.setDepartmentName(rs.getString("DEPARTMENTNAME"));
			emp.setPositionId(rs.getString("POSITIONID"));
			emp.setPositionName(rs.getString("POSITIONNAME"));
			emp.setRegionId(rs.getString("REGIONID"));
			emp.setRegionName(rs.getString("REGIONNAME"));
			emp.setBasicPay(rs.getInt("BASICPAY"));
			emp.setExtraPay(rs.getInt("EXTRAPAY"));
			emp.setPay(rs.getInt("PAY"));
			emp.setGrade(rs.getInt("GRADE"));
			
			result.add(emp);
		}
		rs.close();
		pstmt.close();
		conn.close();
		
		return result;
	}

	// 지역 전체 리스트 출력
	@Override
	public ArrayList<Region> regionList() throws SQLException
	{
		ArrayList<Region> result = new ArrayList<Region>();
		
		Connection conn = dataSource.getConnection();
		
		String sql = "SELECT REGIONID, REGIONNAME, DELCHECK FROM REGIONVIEW ORDER BY 1";
		
		PreparedStatement pstmt = conn.prepareStatement(sql);
		ResultSet rs = pstmt.executeQuery();
		
		while(rs.next())
		{
			Region region = new Region();
			
			region.setRegionId(rs.getString("REGIONID"));
			region.setRegionName(rs.getString("REGIONNAME"));
			region.setDelCheck(rs.getInt("DELCHECK"));
			
			result.add(region);
		}
		rs.close();
		pstmt.close();
		conn.close();
		
		return result;
	}

	// 부서 전체 리스트 출력
	@Override
	public ArrayList<Department> departmentList() throws SQLException
	{
		ArrayList<Department> result = new ArrayList<Department>();
		
		Connection conn = dataSource.getConnection();
		
		String sql = "SELECT DEPARTMENTID, DEPARTMENTNAME, DELCHECK FROM DEPARTMENTVIEW ORDER BY 1";
		
		PreparedStatement pstmt = conn.prepareStatement(sql);
		ResultSet rs = pstmt.executeQuery();
		
		while(rs.next())
		{
			Department dm = new Department();
			
			dm.setDepartmentId(rs.getString("DEPARTMENTID"));
			dm.setDepartmentName(rs.getString("DEPARTMENTNAME"));
			dm.setDelCheck(rs.getInt("DELCHECK"));
			
			result.add(dm);
		}
		rs.close();
		pstmt.close();
		conn.close();
		
		return result;
	}
	
	// 직위 전체 리스트 출력
	@Override
	public ArrayList<Position> positionList() throws SQLException
	{
		ArrayList<Position> result = new ArrayList<Position>();
		
		Connection conn = dataSource.getConnection();
		
		String sql = "SELECT POSITIONID, POSITIONNAME, MINBASICPAY, DELCHECK FROM POSITIONVIEW ORDER BY 1";
		
		PreparedStatement pstmt = conn.prepareStatement(sql);
		ResultSet rs = pstmt.executeQuery();
		
		while(rs.next())
		{
			Position p = new Position();
			
			p.setPositionId(rs.getString("POSITIONID"));
			p.setPositionName(rs.getString("POSITIONNAME"));
			p.setMinBasicPay(rs.getInt("MINBASICPAY"));
			p.setDelCheck(rs.getInt("DELCHECK"));
			
			result.add(p);
		}
		rs.close();
		pstmt.close();
		conn.close();
		
		return result;
	}

	// 직위 아이디에 따른 최소 기본급 확인, 검색
	@Override
	public int getMinBasicPay(String positionId) throws SQLException
	{
		int result = 0;
		
		Connection conn = dataSource.getConnection();
		
		String sql = "SELECT MINBASICPAY FROM POSITION WHERE POSITIONID=?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		// pstmt.setString(1, positionId);
		pstmt.setInt(1, Integer.parseInt(positionId));
		
		ResultSet rs = pstmt.executeQuery();
		
		if(rs.next())
		{
			result = rs.getInt("MINBASICPAY");
		}

		rs.close();
		pstmt.close();
		conn.close();
		
		return result;
	}

	// 직원 데이터 추가
	@Override
	public int employeeAdd(Employee employee) throws SQLException
	{
		int result = 0;
		
		Connection conn = dataSource.getConnection();
		
		String sql = "INSERT INTO EMPLOYEE(EMPLOYEEID, NAME, SSN1, SSN2, BIRTHDAY, LUNAR"
				+ ", TELEPHONE, DEPARTMENTID, POSITIONID"
				+ ", REGIONID, BASICPAY, EXTRAPAY)"
				+ " VALUES(EMPLOYEESEQ.NEXTVAL, ?, ?"
				+ ", CRYPTPACK.ENCRYPT(?, ?)"
				+ ", TO_DATE(?, 'YYYY-MM-DD')"
				+ ", ?, ?, ?, ?, ?, ?, ?)";
		
		PreparedStatement pstmt = conn.prepareStatement(sql);
		
		pstmt.setString(1, employee.getName());
		pstmt.setString(2, employee.getSsn1());
		pstmt.setString(3, employee.getSsn2());
		pstmt.setString(4, employee.getSsn2());
		pstmt.setString(5, employee.getBirthday());
		pstmt.setInt(6, employee.getLunar());
		pstmt.setString(7, employee.getTelephone());
		pstmt.setInt(8, Integer.parseInt(employee.getDepartmentId()));
		pstmt.setInt(9, Integer.parseInt(employee.getPositionId()));
		pstmt.setInt(10, Integer.parseInt(employee.getRegionId()));
		pstmt.setInt(11, employee.getBasicPay());
		pstmt.setInt(12, employee.getExtraPay());
		
		result = pstmt.executeUpdate();
		pstmt.close();
		conn.close();
		
		return result;
	}

	// 직원 데이터 삭제
	@Override
	public int remove(String employeeId) throws SQLException
	{
		int result = 0;
		
		Connection conn = dataSource.getConnection();
		
		String sql = "DELETE FROM EMPLOYEE WHERE EMPLOYEEID=?";
		
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, Integer.parseInt(employeeId));
		
		result = pstmt.executeUpdate();
		pstmt.close();
		conn.close();
		
		return result;
	}

	// 일반 직원 데이터 수정
	@Override
	public int modify(Employee employee) throws SQLException
	{
		int result = 0;
		
		Connection conn = dataSource.getConnection();
		
		String sql = "UPDATE EMPLOYEE SET NAME=?"
				+ ", BIRTHDAY=TO_DATE(?, 'YYYY-MM-DD')"
				+ ", LUNAR=?, TELEPHONE=?"
				+ ", DEPARTMENTID=?, POSITIONID=?, REGIONID=?"
				+ ", BASICPAY=?, EXTRAPAY=?"
				+ ", SSN1=?, SSN2=CRYPTPACK.ENCRYPT(?, ?)"
				+ " WHERE EMPLOYEEID=?";
		
		PreparedStatement pstmt = conn.prepareStatement(sql);
		
		pstmt.setString(1, employee.getName());
		pstmt.setString(2, employee.getBirthday());
		pstmt.setInt(3, employee.getLunar());
		pstmt.setString(4, employee.getTelephone());
		pstmt.setInt(5, Integer.parseInt(employee.getDepartmentId()));
		pstmt.setInt(6, Integer.parseInt(employee.getPositionId()));
		pstmt.setInt(7, Integer.parseInt(employee.getRegionId()));
		pstmt.setInt(8, employee.getBasicPay());
		pstmt.setInt(9, employee.getExtraPay());
		pstmt.setString(10, employee.getSsn1());
		pstmt.setString(11, employee.getSsn2());
		pstmt.setString(12, employee.getSsn2());
		pstmt.setInt(13, Integer.parseInt(employee.getEmployeeId()));
		
		result = pstmt.executeUpdate();
		pstmt.close();
		conn.close();
		
		return result;
	}

	// 직원 검색 → 수정 폼 만들기 위해 텍스트박스에 정보가 들어있도록.
	// (수정이나 삭제를 위해 검색하는 것이므로 EMPLOYEEVIEW를 조회하는 것보다
	//  EMPLOYE 테이블을 조회하는 것이 더 바람직하다.)
	@Override
	public Employee searchId(String employeeId) throws SQLException
	{
		Employee emp = new Employee();
		
		Connection conn = dataSource.getConnection();
		
		String sql = "SELECT EMPLOYEEID, NAME, SSN1"
				+ ", TO_CHAR(BIRTHDAY, 'YYYY-MM-DD') AS BIRTHDAY"
				+ ", LUNAR, TELEPHONE, DEPARTMENTID, POSITIONID, REGIONID"
				+ ", BASICPAY, EXTRAPAY"
				+ " FROM EMPLOYEE"
				+ " WHERE EMPLOYEEID = ?";
		
		/* 뷰를 조회한 결과
		String sql = "SELECT EMPLOYEEID, NAME, SSN, BIRTHDAY, LUNAR, LUNARNAME"
				+ ", TELEPHONE, DEPARTMENTID, DEPARTMENTNAME, POSITIONID, POSITIONNAME"
				+ ", REGIONID, REGIONNAME, BASICPAY, EXTRAPAY, PAY, GRADE"
				+ " FROM EMPLOYEEVIEW"
				+ " WHERE EMPLOYEEID=?";
		*/
		
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, Integer.parseInt(employeeId));
		
		ResultSet rs = pstmt.executeQuery();
		
		while(rs.next())
		{
			emp.setEmployeeId(rs.getString("EMPLOYEEID"));
			emp.setName(rs.getString("NAME"));
			emp.setSsn1(rs.getString("SSN1"));
			emp.setBirthday(rs.getString("BIRTHDAY"));
			emp.setLunar(rs.getInt("LUNAR"));
			emp.setTelephone(rs.getString("TELEPHONE"));
			emp.setDepartmentId(rs.getString("DEPARTMENTID"));
			emp.setPositionId(rs.getString("POSITIONID"));
			emp.setRegionId(rs.getString("REGIONID"));
			emp.setBasicPay(rs.getInt("BASICPAY"));
			emp.setExtraPay(rs.getInt("EXTRAPAY"));
		}
		
		rs.close();
		pstmt.close();
		conn.close();
		
		return emp;
	}

	// 일반 사원 로그인
	@Override
	public String login(String id, String pw) throws SQLException
	{
		String result = null;
		
		Connection conn = dataSource.getConnection();
		
		String sql = "SELECT NAME FROM EMPLOYEE"
				+ " WHERE EMPLOYEEID=? AND SSN2 = CRYPTPACK.ENCRYPT(?, ?)";
		
		PreparedStatement pstmt = conn.prepareStatement(sql);
		
		//pstmt.setString(1, id); String id → DB에는 NUMBER 타입이므로 형변환 필요하다.
		pstmt.setInt(1, Integer.parseInt(id));
		pstmt.setString(2, pw);
		pstmt.setString(3, pw);
		
		ResultSet rs = pstmt.executeQuery();
		while(rs.next())
		{
			result = rs.getString("NAME");
		}
		rs.close();
		pstmt.close();
		conn.close();
		
		return result;
	}

	// 관리자 로그인
	@Override
	public String loginAdmin(String id, String pw) throws SQLException
	{
		String result = "";
		
		Connection conn = dataSource.getConnection();
		
		String sql = "SELECT NAME FROM EMPLOYEE"
				+ " WHERE EMPLOYEEID=? AND SSN2 = CRYPTPACK.ENCRYPT(?, ?) AND GRADE = 0";
		
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, Integer.parseInt(id));
		pstmt.setString(2, pw);
		pstmt.setString(3, pw);
		
		ResultSet rs = pstmt.executeQuery();
		if(rs.next())
		{
			result = rs.getString("NAME");
		}
		rs.close();
		pstmt.close();
		conn.close();
		
		return result;
	}
}
