/*==============================
    #05. IEmployeeDAO.java
    - 인터페이스
===============================*/
package com.test.mvc;

import java.sql.SQLException;
import java.util.ArrayList;

public interface IEmployeeDAO
{
	// 리스트 조회 (사용자가 선택할 때 조회할 수 있도록)
	public ArrayList<Employee> list() throws SQLException;
	public ArrayList<Region> regionList() throws SQLException;
	public ArrayList<Department> departmentList() throws SQLException;
	public ArrayList<Position> positionList() throws SQLException;
	
	public int getMinBasicPay(String positionId) throws SQLException;
	public int employeeAdd(Employee employee) throws SQLException;
	public int remove(String employeeId) throws SQLException;
	public int modify(Employee employee) throws SQLException;
	public Employee searchId(String employeeId) throws SQLException;
	
	public String login(String id, String pw) throws SQLException;
	public String loginAdmin(String id, String pw) throws SQLException;
}
