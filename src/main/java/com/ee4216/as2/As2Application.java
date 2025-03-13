package com.ee4216.as2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.JdbcTemplate;

import com.ee4216.as2.MovieAPI.TodoListDao;
import com.ee4216.as2.UserAPI.UserDao;

@SpringBootApplication
public class As2Application {

	public static void main(String[] args) {
		SpringApplication.run(As2Application.class, args);
	}

	@Bean
	public TodoListDao todoListDao(JdbcTemplate jdbcTemplate) {
		return new TodoListDao(jdbcTemplate);
	}

	@Bean
	public UserDao userDao(JdbcTemplate jdbcTemplate) {
		return new UserDao(jdbcTemplate);
	}

}
