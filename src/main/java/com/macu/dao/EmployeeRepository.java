package com.macu.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.macu.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
	
	@Modifying
	@Query("update Employee u set u.firstName = ?2, u.lastName = ?3 , u.email = ?4 where u.id = ?1")
	void setUserInfoById(Integer id, String firstname, String lastname, String email);
}
