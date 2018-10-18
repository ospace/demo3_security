package com.example.demo3;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@SpringBootApplication
public class Demo3SecurityApplication {
	public static void main(String[] args) {
		SpringApplication.run(Demo3SecurityApplication.class, args);
	}
}
