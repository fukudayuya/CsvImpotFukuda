package com.example.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.example.domain.Student;

@Repository
public class StudentRepository {
	
	@Autowired
	private NamedParameterJdbcTemplate template;
	
	/**
	 * insert
	 * @param student
	 */
	public void insert(Student student) {
		SqlParameterSource param = new BeanPropertySqlParameterSource(student);
		String sql = "INSERT INTO student ( name, email ) VALUES (:name, :email)";
		template.update(sql, param);
	}

}
