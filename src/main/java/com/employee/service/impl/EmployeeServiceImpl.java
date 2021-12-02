package com.employee.service.impl;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.employee.dao.EmployeeDao;
import com.employee.dto.EmployeeDTO;
import com.employee.dto.Employees;
import com.employee.entity.Employee;
import com.employee.service.EmployeeService;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	private EmployeeDao employeeDao;

	@Override
	public Employees findAll() {
		List<Employee> employeeList = employeeDao.findAll();
		List<EmployeeDTO> employees = employeeList.stream()
				.map(emp -> EmployeeDTO.builder().name(emp.getName()).id(emp.getId()).employeeId(emp.getEmployeeId())
						.gender(emp.getGender()).organisation(emp.getOrganisation()).build())
				.collect(Collectors.toUnmodifiableList());
		Map<Character, Long> genderMap = employeeList.stream()
				.collect(Collectors.groupingBy(Employee::getGender, Collectors.counting()));
		return Employees.builder().employees(employees).totalEmployees(Long.valueOf(employees.size())).male(genderMap.getOrDefault('M', 0L))
				.female(genderMap.getOrDefault('F', 0L)).build();
	}

	@Override
	public EmployeeDTO getEmployee(Long id) {
		Optional<Employee> employee = employeeDao.findById(id);
		EmployeeDTO empDto = EmployeeDTO.builder().build();
		if (employee.isPresent()) {
			BeanUtils.copyProperties(employee.get(), empDto);
			return empDto;
		}
		return null;
	}

	@Override
	public Employee saveEmployeeDetails(Employee employee) {
		return employeeDao.save(employee);
	}

	@Override
	public Employee updateEployeeDetails(Employee employee) {
		return employeeDao.save(employee);
	}

	@Override
	public void deleteEmployee(Long id) {
		employeeDao.deleteById(id);
	}
}
