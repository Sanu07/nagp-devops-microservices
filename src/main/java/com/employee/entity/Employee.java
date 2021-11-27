package com.employee.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "EMPLOYEE_DETAILS")
public class Employee {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "_ID")
	private Long id;

	@Column(name = "EMPLOYEE_ID", unique = true)
	private String employeeId;

	@Column(name = "EMPLOYEE_NAME")
	private String name;
	
	@Column(name = "GENDER")
	private char gender;

	@Column(name = "ORGANISATION")
	private String organisation;

}
