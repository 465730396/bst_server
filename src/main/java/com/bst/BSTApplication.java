package com.bst;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@SpringBootApplication
public class BSTApplication {

	public static void main(String[] args) {
		SpringApplication.run(BSTApplication.class, args);
	}
}
