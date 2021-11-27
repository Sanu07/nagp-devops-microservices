package com.employee.integrationtest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.TestPropertySource;

import com.employee.EmployeeServiceApplication;
import com.employee.dto.EmployeeDTO;
import com.employee.dto.Employees;
import com.employee.entity.Employee;
import com.fasterxml.jackson.core.JsonProcessingException;

@SpringBootTest(classes = EmployeeServiceApplication.class, webEnvironment = WebEnvironment.RANDOM_PORT)
@TestPropertySource(locations = "classpath:application-test.properties")
@TestMethodOrder(OrderAnnotation.class)
class EmployeeControllerIT {

	@Autowired
	private TestRestTemplate testRestTemplate;

	@Test
	@Order(1)
	void givenEmployee_whenSaveEmployee_thenReturnSavedEmployeeWithId() throws JsonProcessingException, Exception {
		Employee employee = Employee.builder().id(1L).name("Andrew").employeeId("12355").gender('M')
				.organisation("Nagarro").build();
		HttpEntity<Employee> entity = new HttpEntity<Employee>(employee);
		ResponseEntity<EmployeeDTO> response = testRestTemplate.postForEntity("/employees", entity, EmployeeDTO.class);
		assertNotNull(response.getBody().getId());
		assertEquals("Andrew", response.getBody().getName());
		assertEquals("12355", response.getBody().getEmployeeId());
	}

	@Test
	@Order(2)
	void whenFindAllEmployees_thenReturnAllEmployeesInDB() throws JsonProcessingException, Exception {
		ResponseEntity<Employees> employees = testRestTemplate.getForEntity("/employees", Employees.class);
		assertNotNull(employees);
		assertEquals(1L, employees.getBody().getTotalEmployees());
		assertEquals(1L, employees.getBody().getMale());
	}

	@Test
	@Order(3)
	void givenEmployeeId_whenDeleteEmployee_thenReturnAcceptedResponse() throws JsonProcessingException, Exception {
		testRestTemplate.delete("/employees/1");
		ResponseEntity<EmployeeDTO> response = testRestTemplate.getForEntity("/employees/1", EmployeeDTO.class);
		assertNull(response.getBody());
	}

	@Test
	@Order(4)
	void whenFindAllEmployees_thenReturnZeroEmployees() throws JsonProcessingException, Exception {
		ResponseEntity<Employees> employees = testRestTemplate.getForEntity("/employees", Employees.class);
		assertEquals(0L, employees.getBody().getTotalEmployees());
		assertEquals(0L, employees.getBody().getMale());
	}
}
