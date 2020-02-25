package com.example.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
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
		System.out.println("test:repo1");
		String sql = "INSERT INTO csvuser ( name, email ) VALUES (:name, :email)";//DBに合わせて変更
		SqlParameterSource param = new MapSqlParameterSource().addValue("name", student.getName())
										.addValue("email", student.getEmail());
//		SqlParameterSource param = new BeanPropertySqlParameterSource(student);//なぜか機能しなかった
		System.out.println("test:repo2");
		template.update(sql, param);
		System.out.println("test:repo3");
	}

}
