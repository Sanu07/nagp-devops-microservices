package com.employee.service;

import com.employee.dto.EmployeeDTO;
import com.employee.dto.Employees;
import com.employee.entity.Employee;

public interface EmployeeService {

	Employees findAll();

	EmployeeDTO getEmployee(Long id);

	Employee saveEmployeeDetails(Employee employee);

	Employee updateEployeeDetails(Employee employee);
	
	void deleteEmployee(Long id);
}
