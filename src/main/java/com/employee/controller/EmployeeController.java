package com.employee.controller;

import java.util.Objects;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.employee.dto.EmployeeDTO;
import com.employee.dto.Employees;
import com.employee.entity.Employee;
import com.employee.service.impl.EmployeeServiceImpl;

@RestController
@RequestMapping("employees")
public class EmployeeController {

	@Autowired
	private EmployeeServiceImpl employeeService;
	
	@GetMapping
	public ResponseEntity<Employees> findAll() {
		Employees employees = employeeService.findAll();
		return ResponseEntity.ok(employees);
	}
	
	@GetMapping("{id}")
	public ResponseEntity<EmployeeDTO> getEmployee(@PathVariable Long id) {
		EmployeeDTO employee = employeeService.getEmployee(id);
		if (Objects.nonNull(employee)) {
			return ResponseEntity.ok(employee);
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
	}
	
	@PostMapping
	public ResponseEntity<EmployeeDTO> saveEmployee(@RequestBody Employee employee) {
		Employee empEntity = new Employee();
		BeanUtils.copyProperties(employee, empEntity);
		Employee savedEmployee = employeeService.saveEmployeeDetails(empEntity);
		EmployeeDTO empDto = new EmployeeDTO();
		BeanUtils.copyProperties(savedEmployee, empDto);
		return ResponseEntity.status(HttpStatus.CREATED).body(empDto);
	}
	
	@PutMapping
	public ResponseEntity<EmployeeDTO> updateEmployee(@RequestBody Employee employee) {
		Employee empEntity = new Employee();
		BeanUtils.copyProperties(employee, empEntity);
		Employee savedEmployee = employeeService.updateEployeeDetails(empEntity);
		EmployeeDTO empDto = new EmployeeDTO();
		BeanUtils.copyProperties(savedEmployee, empDto);
		return ResponseEntity.ok(empDto);
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity<Void> deleteEmployee(@PathVariable Long id) {
		employeeService.deleteEmployee(id);
		return ResponseEntity.status(HttpStatus.ACCEPTED).build();
	}
}
