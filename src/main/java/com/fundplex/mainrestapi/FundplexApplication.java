package com.fundplex.mainrestapi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
@EnableWebMvc
public class FundplexApplication {
	@Autowired

	public static void main(String[] args) {
		SpringApplication.run(FundplexApplication.class, args);
	}

}
