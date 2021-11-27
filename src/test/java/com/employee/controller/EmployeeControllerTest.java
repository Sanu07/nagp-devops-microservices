package com.employee.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.employee.dto.EmployeeDTO;
import com.employee.dto.Employees;
import com.employee.entity.Employee;
import com.employee.service.impl.EmployeeServiceImpl;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(controllers = EmployeeController.class)
class EmployeeControllerTest {

	@Autowired
	private MockMvc mockMvc;
	@Autowired
	private ObjectMapper mapper;
	@MockBean
	private EmployeeServiceImpl employeeService;

	private List<Employee> employees;
	private List<EmployeeDTO> savedEmployees;

	@BeforeEach
	void setUp() {
		employees = new ArrayList<>();
		employees.add(Employee.builder().id(1L).name("Andrew").employeeId("12345").gender('M').organisation("Nagarro")
				.build());
		employees.add(Employee.builder().id(2L).name("Emilly").employeeId("24680").gender('F').organisation("Google")
				.build());
		employees.add(Employee.builder().id(3L).name("Chandler").employeeId("13579").gender('M').organisation("Nagarro")
				.build());
		savedEmployees = employees.stream().map(emp -> {
			EmployeeDTO empDto = new EmployeeDTO();
			BeanUtils.copyProperties(emp, empDto);
			return empDto;
		}).collect(Collectors.toList());
	}

	@Test
	void whenSaveEmployee_theReturnSavedEmployee() throws JsonProcessingException, Exception {
		when(employeeService.saveEmployeeDetails(employees.get(0))).thenReturn(employees.get(0));
		this.mockMvc
				.perform(post("/employees").contentType(MediaType.APPLICATION_JSON)
						.content(mapper.writeValueAsString(employees.get(0))).characterEncoding(StandardCharsets.UTF_8))
				.andExpect(jsonPath("$.name", is(employees.get(0).getName())))
				.andExpect(jsonPath("$.organisation", is(employees.get(0).getOrganisation())))
				.andExpect(jsonPath("$.id", notNullValue())).andReturn();
	}

	@Test
	void whenGetEmployee_theReturnEmployeeWithProvidedId() throws JsonProcessingException, Exception {
		when(employeeService.getEmployee(1L)).thenReturn(savedEmployees.get(0));
		this.mockMvc
				.perform(get("/employees/{id}", 1).contentType(MediaType.APPLICATION_JSON)
						.content(mapper.writeValueAsString(savedEmployees.get(0)))
						.characterEncoding(StandardCharsets.UTF_8))
				.andExpect(jsonPath("$.name", is(savedEmployees.get(0).getName())))
				.andExpect(jsonPath("$.organisation", is(savedEmployees.get(0).getOrganisation())))
				.andExpect(jsonPath("$.id", is(1))).andReturn();
	}

	@Test
	void whenFindAllEmployees_theReturnAllEmployees() throws JsonProcessingException, Exception {
		when(employeeService.findAll()).thenReturn(
				Employees.builder().employees(savedEmployees).female(1L).male(2L).totalEmployees(3L).build());
		this.mockMvc
				.perform(get("/employees").contentType(MediaType.APPLICATION_JSON)
						.content(mapper.writeValueAsString(savedEmployees)).characterEncoding(StandardCharsets.UTF_8))
				.andExpect(jsonPath("$.totalEmployees", is(3))).andExpect(jsonPath("$.female", is(1)))
				.andExpect(jsonPath("$.male", is(2))).andReturn();
	}

	@Test
	void whenDeleteEmployee_theReturnAcceptResponseCode() throws Exception {
		this.mockMvc.perform(delete("/employees/{id}", 1L)
				.contentType(MediaType.APPLICATION_JSON)
				.characterEncoding("utf-8"));
		verify(employeeService, times(1)).deleteEmployee(1L);
	}

	@Test
	void whenUpdateEmployee_theReturnUpdatedEmployee() throws JsonProcessingException, Exception {
		when(employeeService.updateEployeeDetails(employees.get(0))).thenReturn(employees.get(0));
		this.mockMvc
				.perform(put("/employees").contentType(MediaType.APPLICATION_JSON)
						.content(mapper.writeValueAsString(employees.get(0))).characterEncoding(StandardCharsets.UTF_8))
				.andExpect(jsonPath("$.name", is(employees.get(0).getName())))
				.andExpect(jsonPath("$.organisation", is(employees.get(0).getOrganisation())))
				.andExpect(jsonPath("$.id", is(1))).andReturn();
	}

}
