package com.employee.dto;

import java.util.List;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Employees {

	private List<EmployeeDTO> employees;
	private Long totalEmployees;
	private Long male;
	private Long female;
}
