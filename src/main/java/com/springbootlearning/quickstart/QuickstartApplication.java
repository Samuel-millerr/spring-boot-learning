package com.springbootlearning.quickstart;

import lombok.extern.java.Log;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;

@SpringBootApplication
public class QuickstartApplication {
	public static void main(String[] args) {
		SpringApplication.run(QuickstartApplication.class, args);
	}
}
